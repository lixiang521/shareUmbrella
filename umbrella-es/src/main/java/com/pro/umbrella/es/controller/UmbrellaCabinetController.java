//package com.pro.umbrella.es.controller;
//
//import com.pro.umbrella.api.pojo.page.PageRequest;
//import com.pro.umbrella.api.pojo.page.Pager;
//import com.pro.umbrella.common.util.ParamUtils;
//import com.pro.umbrella.common.util.WAssert;
//import com.pro.umbrella.es.service.EsCabinetService;
//import com.pro.umbrella.es.service.param.CabinetQueryParam;
//import com.pro.umbrella.model.constants.JsonResult;
//import com.pro.umbrella.es.controller.param.CabinetGroupWebParam;
//import com.pro.umbrella.es.controller.param.CabinetQueryWebParam;
//import com.pro.umbrella.es.controller.resp.EsCabinetResp;
//import lombok.extern.slf4j.Slf4j;
//import org.springframework.stereotype.Controller;
//import org.springframework.web.bind.annotation.RequestMapping;
//import org.springframework.web.bind.annotation.ResponseBody;
//
//import javax.annotation.Resource;
//import java.util.Map;
//
//@Slf4j
//@Controller
//@RequestMapping(value = "/cabinet")
//@ResponseBody
//public class UmbrellaCabinetController {
//    @Resource
//    private EsCabinetService esCabinetService;
//
//    /**
//     * 查询伞架
//     *
//     * @param param
//     * @return
//     */
//    @RequestMapping("/query/v1")
//    public JsonResult<Pager<EsCabinetResp>> query(CabinetQueryWebParam param) {
//        long start = System.currentTimeMillis();
//        return JsonResult.success(esCabinetService.queryResult(transform(param)));
//    }
//
////    /**
////     * 分组统计雨伞柜
////     *
////     * @param param
////     * @return
////     */
////    @RequestMapping("/umbrellaCabinetGroup/v1")
////    public JsonResult<Map<Integer, Integer>> umbrellaCabinetGroup(CabinetGroupWebParam param) {
////        WAssert.notNull(param.getGroupField(), "分组条件不能为空！");
////        return JsonResult.success(esCabinetBiz.umbrellaCabinetGroup(transformGroup(param)));
////    }
////
////    /**
////     * 分组统计雨伞
////     *
////     * @param param
////     * @return
////     */
////    @RequestMapping("/umbrellaGroup/v1")
////    public JsonResult<Map<String, Integer>> umbrellaGroup(CabinetGroupWebParam param) {
////        WAssert.notNull(param.getGroupField(), "分组条件不能为空！");
////        return JsonResult.success(esCabinetBiz.umbrellaGroup(transformGroup(param)));
////    }
//
//    public static CabinetQueryParam transform(CabinetQueryWebParam it) {
//        CabinetQueryParam cabinetQueryParam = new CabinetQueryParam();
//        PageRequest.Page page = new PageRequest.Page(it.getPageSize(), it.getPageNo());
//
//        cabinetQueryParam.setMinCap(ParamUtils.toInt(it.getMinCap()));
//        cabinetQueryParam.setMaxCap(ParamUtils.toInt(it.getMaxCap()));
//        cabinetQueryParam.setTransStates(ParamUtils.toIntList(it.getTransStates()));
//        cabinetQueryParam.setId(it.getId());
//        cabinetQueryParam.setHardVers(ParamUtils.toList(it.getHardVers()));
//        cabinetQueryParam.setSoftVers(ParamUtils.toList(it.getSoftVers()));
//        cabinetQueryParam.setScene(ParamUtils.toList(it.getScene()));
//        cabinetQueryParam.setDeviceId(it.getDeviceId());
//        cabinetQueryParam.setMinCsq(ParamUtils.toInt(it.getMinCsq()));
//        cabinetQueryParam.setMaxCsq(ParamUtils.toInt(it.getMaxCsq()));
//        cabinetQueryParam.setMinVbat(ParamUtils.toDouble(it.getMinVbat()));
//        cabinetQueryParam.setMaxVbat(ParamUtils.toDouble(it.getMaxVbat()));
//        cabinetQueryParam.setMinPutDate(ParamUtils.toLong(it.getMinPutDate()));
//        cabinetQueryParam.setMaxPutDate(ParamUtils.toLong(it.getMaxPutDate()));
//        cabinetQueryParam.setIsOnline(ParamUtils.toInt(it.getIsOnline()));
//        cabinetQueryParam.setCanLease(ParamUtils.toInt(it.getCanLease()));
//        cabinetQueryParam.setCanReturn(ParamUtils.toInt(it.getCanReturn()));
//        cabinetQueryParam.setCity(ParamUtils.toList(it.getCity()));
//        cabinetQueryParam.setPage(page);
//
//        return cabinetQueryParam;
//    }
////
////    private CabinetGroupParam transformGroup(CabinetGroupWebParam it) {
////        CabinetGroupParam cabinetGroupParam = new CabinetGroupParam();
////        cabinetGroupParam.setMinCap(ParamUtils.toInt(it.getMinCap()));
////        cabinetGroupParam.setMaxCap(ParamUtils.toInt(it.getMaxCap()));
////        cabinetGroupParam.setTransStates(ParamUtils.toIntList(it.getTransStates()));
////        cabinetGroupParam.setId(it.getId());
////        cabinetGroupParam.setShopCode(it.getShopCode());
////        cabinetGroupParam.setHardVers(ParamUtils.toList(it.getHardVers()));
////        cabinetGroupParam.setSoftVers(ParamUtils.toList(it.getSoftVers()));
////        cabinetGroupParam.setSceneType(ParamUtils.toList(it.getSceneType()));
////        cabinetGroupParam.setScene(ParamUtils.toList(it.getScene()));
////        cabinetGroupParam.setDeviceId(it.getDeviceId());
////        cabinetGroupParam.setClientId(it.getClientId());
////        cabinetGroupParam.setClientName(it.getClientName());
////        cabinetGroupParam.setPointId(it.getPointId());
////        cabinetGroupParam.setPointName(it.getPointName());
////        cabinetGroupParam.setLocation(it.getLocation());
////        cabinetGroupParam.setMinCsq(ParamUtils.toInt(it.getMinCsq()));
////        cabinetGroupParam.setMaxCsq(ParamUtils.toInt(it.getMaxCsq()));
////        cabinetGroupParam.setMinVbat(ParamUtils.toDouble(it.getMinVbat()));
////        cabinetGroupParam.setMaxVbat(ParamUtils.toDouble(it.getMaxVbat()));
////        cabinetGroupParam.setMinUnsortedCap(ParamUtils.toInt(it.getMinUnsortedCap()));
////        cabinetGroupParam.setMaxUnsortedCap(ParamUtils.toInt(it.getMaxUnsortedCap()));
////        cabinetGroupParam.setMinPutDate(ParamUtils.toLong(it.getMinPutDate()));
////        cabinetGroupParam.setMaxPutDate(ParamUtils.toLong(it.getMaxPutDate()));
////        cabinetGroupParam.setMinMigrateDate(ParamUtils.toLong(it.getMinMigrateDate()));
////        cabinetGroupParam.setMaxMigrateDate(ParamUtils.toLong(it.getMaxMigrateDate()));
////        cabinetGroupParam.setIsOnline(ParamUtils.toInt(it.getIsOnline()));
////        cabinetGroupParam.setCanLease(ParamUtils.toInt(it.getCanLease()));
////        cabinetGroupParam.setCanReturn(ParamUtils.toInt(it.getCanReturn()));
////        cabinetGroupParam.setProvince(it.getProvince());
////        cabinetGroupParam.setCity(it.getCity());
////        cabinetGroupParam.setDistrict(it.getDistrict());
////        cabinetGroupParam.setGroupField(ParamUtils.toList(it.getGroupField()));
////        return cabinetGroupParam;
////    }
//}
