package com.pro.umbrella.service;

import com.google.common.base.Strings;
import com.google.common.collect.Maps;
import com.pro.umbrella.api.json.JsonUtil;
import com.pro.umbrella.api.link.constants.ConstantUtil;
import com.pro.umbrella.api.pojo.page.PageRequest;
import com.pro.umbrella.api.pojo.page.Pager;
import com.pro.umbrella.api.utils.TransformUtils;
import com.pro.umbrella.common.redis.JedisClient;
import com.pro.umbrella.common.util.DateFormatUtils;
import com.pro.umbrella.common.util.RedisAPI;
import com.pro.umbrella.common.util.WAssert;
import com.pro.umbrella.dao.UmbrellaCabinetMapper;
import com.pro.umbrella.es.controller.resp.EsCabinetResp;
//import com.pro.umbrella.es.service.EsCabinetService;
import com.pro.umbrella.es.service.bean.EsCabinet;
import com.pro.umbrella.es.service.param.CabinetQueryParam;
import com.pro.umbrella.fd.wclient.http.HttpRequests;
import com.pro.umbrella.fd.wclient.http.Response;
import com.pro.umbrella.model.constants.Constants;
import com.pro.umbrella.model.constants.DeviceEnums;
import com.pro.umbrella.model.constants.JsonResult;
import com.pro.umbrella.model.pojo.Umbrella;
import com.pro.umbrella.model.pojo.UmbrellaCabinet;
import com.pro.umbrella.model.pojo.UmbrellaCabinetExample;
import com.pro.umbrella.model.pojo.UmbrellaExample;
import com.pro.umbrella.model.ro.JsonCabinetEsResp;
import com.pro.umbrella.model.ro.OperationUmbrellaCabinetAddReq;
import com.pro.umbrella.model.ro.OperationUmbrellaCabinetPageReq;
import com.pro.umbrella.model.ro.OperationUmbrellaListResp;
import org.apache.commons.lang3.StringUtils;
import org.apache.commons.lang3.math.NumberUtils;
import org.apache.ibatis.session.RowBounds;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.annotation.Resource;
import java.io.IOException;
import java.util.*;
import java.util.stream.Collectors;

/**
 * Created by lixiang on 2019/04/27.
 */
@Component
public class UmbrellaCabinetService {
    @Autowired
    private UmbrellaCabinetMapper umbrellaCabinetMapper;
//    @Resource
//    private EsCabinetService esCabinetService;
    @Resource
    private UmbrellaService umbrellaService;
    @Resource
    private RedisAPI redisAPI;

    /**
     * 新增雨伞柜
     *
     * @param req
     */
    @Transactional
    public void add(OperationUmbrellaCabinetAddReq req) {

        UmbrellaCabinet umbrellaCabinet = new UmbrellaCabinet();
        BeanUtils.copyProperties(req, umbrellaCabinet);
        umbrellaCabinet.setMaxCap(Constants.MAX_CAP_INTERNAL);
        umbrellaCabinet.setPropertyCap((byte) (Constants.MAX_CAP_INTERNAL - 1));
        WAssert.isTrue(1 == umbrellaCabinetMapper.insertSelective(umbrellaCabinet), "添加雨伞柜失败");
    }

    /**
     * 根据伞柜id查询伞柜
     *
     * @param cabinetId
     * @return
     */
    public UmbrellaCabinet queryByCabinetId(String cabinetId) {
        if (StringUtils.isBlank(cabinetId)) {
            return null;
        }
        UmbrellaCabinetExample example = new UmbrellaCabinetExample();
        example.createCriteria().andUmbrellaCabinetNumberEqualTo(cabinetId);
        List<UmbrellaCabinet> umbrellaCabinets = umbrellaCabinetMapper.selectByExample(example);
        return (umbrellaCabinets != null && !umbrellaCabinets.isEmpty()) ? umbrellaCabinets.get(0) : null;
    }

    public Pager<UmbrellaCabinet> list(OperationUmbrellaCabinetPageReq req) {

        List<UmbrellaCabinet> umbrellaCabinet = queryList(req);
//        List<OperationUmbrellaListResp> resps = umbrellas.stream().map(umbrella -> {
//            OperationUmbrellaListResp resp = new OperationUmbrellaListResp();
//            BeanUtils.copyProperties(umbrella, resp);
//            return resp;
//        }).collect(Collectors.toList());

        return Pager.builder(umbrellaCabinet).total(umbrellaCabinet.size()).current(req.getPage()).create();

    }
    public List<UmbrellaCabinet> queryList(OperationUmbrellaCabinetPageReq page) {
        UmbrellaCabinetExample example = new UmbrellaCabinetExample();
        UmbrellaCabinetExample.Criteria criteria = example.createCriteria();
        if (page.getMinPutDate()!=null&&page.getMaxPutDate()!=null)
            criteria.andPutDateBetween(page.getMinPutDate(),page.getMaxPutDate() );
        if (page.getMinCap()!=null&&page.getMaxCap()!=null)
            criteria.andMaxCapBetween(Byte.parseByte(page.getMinCap().toString()),Byte.parseByte(page.getMaxCap().toString()) );
        if (page.getTransStates()!=null){
            List<Byte> list = new ArrayList<>();
            for (Integer i : page.getTransStates()){
                list.add(Byte.parseByte(i.toString()) );
            }
            criteria.andTransStateIn(list);
        }
        if (page.getSoftVers()!=null)
            criteria.andSoftVerIn(page.getSoftVers());
        if (page.getHardVers()!=null)
            criteria.andHardVerIn(page.getHardVers());
        if (page.getScene()!=null)
            criteria.andSceneIn(page.getScene());
        if (page.getDeviceId()!=null)
            criteria.andDeviceIdEqualTo(page.getDeviceId());
        if (page.getMinCsq()!=null&&page.getMaxCsq()!=null)
            criteria.andCsqBetween(page.getMinCsq(),page.getMaxCsq() );
        if (page.getMinVbat()!=null&&page.getMaxVbat()!=null)
            criteria.andVbatBetween(page.getMinVbat(),page.getMaxVbat() );
        RowBounds rowBounds = new RowBounds(page.getOffset(), page.getLimit());
        return umbrellaCabinetMapper.selectByExampleWithRowbounds(example, rowBounds);
    }

    @Transactional
    public JsonResult update(OperationUmbrellaCabinetAddReq req) {
        UmbrellaCabinet cabinetOld = WAssert
                .notNull(queryByCabinetId(req.getUmbrellaCabinetNumber()), "雨伞柜不存在");

        UmbrellaCabinet cabinetNew = new UmbrellaCabinet();
        BeanUtils.copyProperties(req, cabinetNew);
        cabinetNew.setLongitude(req.getLongitude());
        cabinetNew.setLatitude(req.getLatitude());
        cabinetNew.setCommentMessage(req.getCommentMessage());
        if (1 != updateByCabinetId(cabinetNew)) {
            return JsonResult.error("更新雨伞柜失败");
        }
        UmbrellaCabinet umbrellaCabinet = queryByCabinetId(cabinetNew.getUmbrellaCabinetNumber());
        updateEs(umbrellaCabinet);
        return JsonResult.success(null);

    }

    public int updateByCabinetId(UmbrellaCabinet record) {
        UmbrellaCabinetExample example = new UmbrellaCabinetExample();
        example.createCriteria().andUmbrellaCabinetNumberEqualTo(record.getUmbrellaCabinetNumber());
        record.setUpdateTime(new Date());
        int i = umbrellaCabinetMapper.updateByExampleSelective(record, example);
        return i;
    }

    public void updateEs(UmbrellaCabinet cabinetNew) {
        EsCabinet esCabinet = new EsCabinet();
        BeanUtils.copyProperties(cabinetNew, esCabinet);


        List<Umbrella> umbrellas = umbrellaService.queryByCabinetId(cabinetNew.getUmbrellaCabinetNumber());
        int umbrellaCount = umbrellas == null ? 0 : umbrellas.size();

        esCabinet.setId(cabinetNew.getUmbrellaCabinetNumber());
        esCabinet.setHardVer(cabinetNew.getHardVer());
        esCabinet.setSoftVer(cabinetNew.getSoftVer());
        esCabinet.setDeviceId(cabinetNew.getDeviceId());

//        esCabinet.setCapacity(umbrellaNormal == null ? 0 : umbrellaNormal.size());

        if (!org.elasticsearch.common.Strings.isNullOrEmpty(cabinetNew.getCsq()) && cabinetNew.getCsq().matches("^\\d+$")) {
            esCabinet.setCsq(Integer.parseInt(cabinetNew.getCsq()));
        }
        if (!org.elasticsearch.common.Strings.isNullOrEmpty(cabinetNew.getVbat())) {
            esCabinet.setVbat(TransformUtils.transformVabt(cabinetNew.getVbat()));
        }
        if (cabinetNew.getPutDate() != null) {
            esCabinet.setPutDate(cabinetNew.getPutDate().getTime());
        }
        if (cabinetNew.getOnlineState() != null) {
            esCabinet.setIsOnline(cabinetNew.getOnlineState().intValue());
        }
        esCabinet.setTransState(cabinetNew.getTransState().intValue());
        esCabinet.setCanLease(umbrellaCount > 0 ? 1 : 0);
        esCabinet.setCanReturn(cabinetNew.getMaxCap() > umbrellaCount ? 1 : 0);
//        esCabinetService.saveOrUpdate(esCabinet);
    }

    public boolean isOnline(String cabinet) {
        String serverName = redisAPI.get(cabinet);
        if (serverName != null) {
            UmbrellaCabinet umbrellaCabinet = JsonUtil.of(serverName, UmbrellaCabinet.class);
            return umbrellaCabinet.getOnlineState() == (byte)1 ? true : false;
        }
        return false;
    }

    @Transactional
    public Void updateUmbrellaCabinetOccupyState(String umbrellaCabinetNumber, byte preOccupyState,
                                                 byte currentOccupyState) {
        UmbrellaCabinet umbrellaCabinet = queryByCabinetId(umbrellaCabinetNumber);
        WAssert.isTrue(umbrellaCabinet != null, "雨伞柜编号不存在");
        // 更新雨伞使用状态
        UmbrellaCabinet umbrellaCabinetOld = umbrellaCabinet.cloneOne();
        umbrellaCabinet.setOccupyState(currentOccupyState);
        int result = updateOccupyState(umbrellaCabinet, preOccupyState);
        WAssert.isTrue(result > 0, "伞柜被占用中,请稍等"); // 雨伞柜占用状态更新失败
        return null;
    }

    public int updateOccupyState(UmbrellaCabinet record, Byte preOccupyState) {
        UmbrellaCabinetExample example = new UmbrellaCabinetExample();
        example.createCriteria().andUmbrellaCabinetNumberEqualTo(record.getUmbrellaCabinetNumber())
                .andOccupyStateEqualTo(preOccupyState);
        record.setUpdateTime(new Date());
        int i = umbrellaCabinetMapper.updateByExample(record, example);
        updateEs(record);
        return i;
    }

}
