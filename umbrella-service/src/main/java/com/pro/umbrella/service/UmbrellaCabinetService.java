package com.pro.umbrella.service;

import com.google.common.base.Strings;
import com.google.common.collect.Maps;
import com.pro.umbrella.api.json.JsonUtil;
import com.pro.umbrella.common.util.DateFormatUtils;
import com.pro.umbrella.common.util.WAssert;
import com.pro.umbrella.dao.UmbrellaCabinetMapper;
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
    private HttpRequests httpRequests;
    @Value("${es.url}")
    private String esServerHost;
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

    public JsonCabinetEsResp queryList(OperationUmbrellaCabinetPageReq req) {

        try {

            Response response = httpRequests.syncPost(esServerHost + "/cabinet/query/v1",
                    buildQueryMap(assembleParam(req)));

            JsonCabinetEsResp res = JsonUtil.of(response.body(), JsonCabinetEsResp.class);

            if (!res.isSuccess()) {
                throw new RuntimeException("返回码错误: " + res.getStatus());
            }
            res.getData().getData().forEach(x -> {
                x.setFormatMigrateDate(DateFormatUtils.format4y2M2d2h2m2s(x.getMigrateDate()));
                x.setFormatPutDate(DateFormatUtils.format4y2M2d2h2m2s(x.getPutDate()));
                if (!Strings.isNullOrEmpty(x.getVbat())) {
                    x.setVbat(String.format("%.2f", NumberUtils.createDouble(x.getVbat())) + "V");
                }
            });

            return res;
        } catch (IOException e) {
            System.out.println("请求es服务查询伞柜列表异常！" + e);
            WAssert.isTrue(false, "请求es服务查询伞柜列表异常！");
        }
        return null;
    }

    private OperationUmbrellaCabinetPageReq assembleParam(OperationUmbrellaCabinetPageReq req) {

        int[] transStatesArray = req.getTransStatesArray();
        String[] hardVersArray = req.getHardVersArray();
        String[] softVersArray = req.getSoftVersArray();
        String[] sceneType = req.getSceneTypeArray();
        String[] scene = req.getSceneArray();
        String[] cityArray = req.getCityArray();

        String minPutDate = req.getMinPutDate();
        String maxPutDate = req.getMaxPutDate();

        if (transStatesArray != null && transStatesArray.length > 0) {
            String transStatesStr = Arrays.toString(transStatesArray);
            req.setTransStates(transStatesStr.substring(1, transStatesStr.length() - 1).replaceAll(" ", ""));
        }
        if (hardVersArray != null && hardVersArray.length > 0) {
            String hardVerStr = Arrays.toString(hardVersArray);
            req.setHardVers(hardVerStr.substring(1, hardVerStr.length() - 1).replaceAll(" ", ""));
        }
        if (softVersArray != null && softVersArray.length > 0) {
            String softVerStr = Arrays.toString(softVersArray);
            req.setSoftVers(softVerStr.substring(1, softVerStr.length() - 1).replaceAll(" ", ""));
        }
        if (scene != null && scene.length > 0) {
            String sceneStr = Arrays.toString(scene);
            req.setScene(sceneStr.substring(1, sceneStr.length() - 1).replaceAll(" ", ""));
        }
        if (cityArray != null && cityArray.length > 0) {
            String cityStr = Arrays.toString(cityArray);
            req.setCity(cityStr.substring(1, cityStr.length() - 1).replaceAll(" ", ""));
        }
        if (StringUtils.isNotEmpty(minPutDate)) {
            req.setMinPutDate(DateFormatUtils.parse4y2M2d2h2m2s(minPutDate).getTime() + "");
        }
        if (StringUtils.isNotEmpty(maxPutDate)) {
            req.setMaxPutDate(DateFormatUtils.parse4y2M2d2h2m2s(maxPutDate).getTime() + "");
        }
        return req;
    }

    private Map<String, String> buildQueryMap(OperationUmbrellaCabinetPageReq req) {
        HashMap<String, String> map = Maps.newHashMapWithExpectedSize(35);
        map.put("pageSize", String.valueOf(req.getPage().getPageSize()));
        map.put("pageNo", String.valueOf(req.getPage().getPageNo()));
        map.put("minCap", req.getMinCap());
        map.put("maxCap", req.getMaxCap());
        map.put("transStates", req.getTransStates());
        map.put("id", req.getId());
        map.put("softVers", req.getSoftVers());
        map.put("hardVers", req.getHardVers());
        map.put("scene", req.getScene());
        map.put("deviceId", req.getDeviceId());
        map.put("minCsq", req.getMinCsq());
        map.put("maxCsq", req.getMaxCsq());
        map.put("minVbat", req.getMinVbat());
        map.put("maxVbat", req.getMaxVbat());
        map.put("minPutDate", req.getMinPutDate());
        map.put("maxPutDate", req.getMaxPutDate());
        map.put("isOnline", req.getIsOnline());
        map.put("city", req.getCity());
        return map;
    }
}
