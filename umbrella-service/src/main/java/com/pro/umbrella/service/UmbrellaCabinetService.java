package com.pro.umbrella.service;

import com.google.common.base.Strings;
import com.google.common.collect.Maps;
import com.pro.umbrella.api.json.JsonUtil;
import com.pro.umbrella.api.pojo.page.Pager;
import com.pro.umbrella.common.util.DateFormatUtils;
import com.pro.umbrella.common.util.WAssert;
import com.pro.umbrella.dao.UmbrellaCabinetMapper;
import com.pro.umbrella.es.controller.resp.EsCabinetResp;
import com.pro.umbrella.es.service.EsCabinetService;
import com.pro.umbrella.es.service.param.CabinetQueryParam;
import com.pro.umbrella.fd.wclient.http.HttpRequests;
import com.pro.umbrella.fd.wclient.http.Response;
import com.pro.umbrella.model.constants.Constants;
import com.pro.umbrella.model.pojo.UmbrellaCabinet;
import com.pro.umbrella.model.pojo.UmbrellaCabinetExample;
import com.pro.umbrella.model.ro.JsonCabinetEsResp;
import com.pro.umbrella.model.ro.OperationUmbrellaCabinetAddReq;
import com.pro.umbrella.model.ro.OperationUmbrellaCabinetPageReq;
import org.apache.commons.lang3.StringUtils;
import org.apache.commons.lang3.math.NumberUtils;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.io.IOException;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Created by lixiang on 2019/04/27.
 */
@Component
public class UmbrellaCabinetService {
    @Autowired
    private UmbrellaCabinetMapper umbrellaCabinetMapper;
    @Resource
    private EsCabinetService esCabinetService;

    /**
     * 新增雨伞柜
     *
     * @param req
     */
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

    public Pager<EsCabinetResp> queryList(OperationUmbrellaCabinetPageReq req) {

        CabinetQueryParam cabinetQueryParam = new CabinetQueryParam();
        BeanUtils.copyProperties(req, cabinetQueryParam);
        if (req.getMaxPutDate()!=null&&req.getMinPutDate()!=null){
        cabinetQueryParam.setMaxPutDate(req.getMaxPutDate().getTime());
        cabinetQueryParam.setMinPutDate(req.getMinPutDate().getTime());}
        Pager<EsCabinetResp> result = esCabinetService.queryResult(cabinetQueryParam);
        result.getData().forEach(x -> {
            x.setFormatPutDate(DateFormatUtils.format4y2M2d2h2m2s(x.getPutDate()));
            x.setFormatVbat(String.format("%.2f", x.getVbat()) + "V");
        });

        return result;

    }
}
