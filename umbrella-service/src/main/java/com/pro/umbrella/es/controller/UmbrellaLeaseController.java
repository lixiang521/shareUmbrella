package com.pro.umbrella.es.controller;//package com.pro.umbrella.es.controller;
//
//import com.tempoon.common.utils.WAssert;
//import com.tempoon.umbrella.es.biz.EsLeaseBiz;
//import com.tempoon.umbrella.es.biz.param.LeaseGroupParam;
//import com.tempoon.umbrella.es.biz.param.LeaseQueryParam;
//import com.tempoon.umbrella.es.common.ParamUtils;
//import com.tempoon.umbrella.es.controller.param.LeaseGroupWebParam;
//import com.tempoon.umbrella.es.controller.param.LeaseQueryWebParam;
//import com.tempoon.umbrella.es.controller.resp.EsLeaseResp;
//import com.wormpex.api.pojo.JsonResult;
//import com.wormpex.api.pojo.page.PageRequest;
//import com.wormpex.api.pojo.page.Pager;
//import com.wormpex.common.metrics.Metrics;
//import com.wormpex.common.web.annotation.JsonResponseBody;
//import com.wormpex.monitor.WMonitor;
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
//@RequestMapping(value = "/lease")
//@ResponseBody
//public class UmbrellaLeaseController {
//    @Resource
//    private EsLeaseBiz esLeaseBiz;
//
//    /**
//     * 查询订单
//     *
//     * @param param
//     * @return
//     */
//    @RequestMapping("/query/v1")
//    public JsonResult<Pager<EsLeaseResp>> query(LeaseQueryWebParam param) {
//        long start = System.currentTimeMillis();
//
//        try {
//            return JsonResult.success(esLeaseBiz.query(transform(param)));
//        } catch (Exception e) {
//            WMonitor.recordMetricForCount(this.getClass().getName() + ".query_failed");
//            LOGGER.error("查询异常", e);
//            throw e;
//        } finally {
//            WMonitor.recordMetricForDuration(this.getClass().getName() + ".query", System.currentTimeMillis() - start);
//            Metrics.counter(this.getClass().getName() + ".query").get().inc();
//        }
//    }
//
//    /**
//     * 分组查询订单
//     *
//     * @param param
//     * @return
//     */
//    @RequestMapping("/group/v1")
//    public JsonResult<Map<String, Integer>> group(LeaseGroupWebParam param) {
//        WAssert.notNull(param.getGroupField(), "分组参数不能为空！");
//        return JsonResult.success(esLeaseBiz.group(transformGroup(param)));
//    }
//
//    private LeaseQueryParam transform(LeaseQueryWebParam it) {
//        LeaseQueryParam leaseQueryParam = new LeaseQueryParam();
//        PageRequest.Page page = new PageRequest.Page(it.getPageSize(), it.getPageNo());
//
//        leaseQueryParam.setId(it.getId());
//        leaseQueryParam.setUmbrellaNumber(it.getUmbrellaNumber());
//        leaseQueryParam.setStartCabinetNumber(it.getStartCabinetNumber());
//        leaseQueryParam.setEndCabinetNumber(it.getEndCabinetNumber());
//        leaseQueryParam.setUid(it.getUid());
//        leaseQueryParam.setPhone(it.getPhone());
//        leaseQueryParam.setLeaseState(ParamUtils.toIntList(it.getLeaseState()));
//        leaseQueryParam.setTradeState(ParamUtils.toIntList(it.getTradeState()));
//        leaseQueryParam.setTransState(ParamUtils.toIntList(it.getTransState()));
//        leaseQueryParam.setIsSameCabinet(ParamUtils.toInt(it.getIsSameCabinet()));
//        leaseQueryParam.setClientId(ParamUtils.toInt(it.getClientId()));
//        leaseQueryParam.setMinCreateTime(ParamUtils.toLong(it.getMinCreateTime()));
//        leaseQueryParam.setMaxCreateTime(ParamUtils.toLong(it.getMaxCreateTime()));
//        leaseQueryParam.setMinReturnTime(ParamUtils.toLong(it.getMinReturnTime()));
//        leaseQueryParam.setMaxReturnTime(ParamUtils.toLong(it.getMaxReturnTime()));
//        leaseQueryParam.setStartSceneType(ParamUtils.toList(it.getStartSceneType()));
//        leaseQueryParam.setStartScene(ParamUtils.toList(it.getStartScene()));
//        leaseQueryParam.setStartPointId(ParamUtils.toInt(it.getStartPointId()));
//        leaseQueryParam.setStartPointName(it.getStartPointName());
//        leaseQueryParam.setStartProvince(it.getStartProvince());
//        leaseQueryParam.setStartCity(ParamUtils.toList(it.getStartCity()));
//        leaseQueryParam.setStartDistrict(it.getStartDistrict());
//        leaseQueryParam.setStartLocation(it.getStartLocation());
//        leaseQueryParam.setEndSceneType(ParamUtils.toList(it.getEndSceneType()));
//        leaseQueryParam.setEndScene(ParamUtils.toList(it.getEndScene()));
//        leaseQueryParam.setEndPointId(it.getEndPointId());
//        leaseQueryParam.setEndPointName(it.getEndPointName());
//        leaseQueryParam.setEndProvince(it.getEndProvince());
//        leaseQueryParam.setEndCity(ParamUtils.toList(it.getEndCity()));
//        leaseQueryParam.setEndDistrict(it.getEndDistrict());
//        leaseQueryParam.setEndLocation(it.getEndLocation());
//        leaseQueryParam.setDuration(ParamUtils.toInt(it.getDuration()));
//        leaseQueryParam.setAmount(it.getAmount());
//        leaseQueryParam.setUserResource(it.getUserResource());
//        leaseQueryParam.setLeaseNode(ParamUtils.toList(it.getLeaseNode()));
//        leaseQueryParam.setEntrance(ParamUtils.toList(it.getEntrance()));
//        leaseQueryParam.setPayType(ParamUtils.toList(it.getPayType()));
//
//        leaseQueryParam.setPage(page);
//
//        return leaseQueryParam;
//    }
//
//    private LeaseGroupParam transformGroup(LeaseGroupWebParam it) {
//        LeaseGroupParam leaseCountParam = new LeaseGroupParam();
//        leaseCountParam.setId(it.getId());
//        leaseCountParam.setUmbrellaNumber(it.getUmbrellaNumber());
//        leaseCountParam.setStartCabinetNumber(it.getStartCabinetNumber());
//        leaseCountParam.setEndCabinetNumber(it.getEndCabinetNumber());
//        leaseCountParam.setUid(it.getUid());
//        leaseCountParam.setPhone(it.getPhone());
//        leaseCountParam.setLeaseState(ParamUtils.toIntList(it.getLeaseState()));
//        leaseCountParam.setTradeState(ParamUtils.toIntList(it.getTradeState()));
//        leaseCountParam.setTransState(ParamUtils.toIntList(it.getTransState()));
//        leaseCountParam.setIsSameCabinet(ParamUtils.toInt(it.getIsSameCabinet()));
//        leaseCountParam.setClientId(ParamUtils.toInt(it.getClientId()));
//        leaseCountParam.setMinCreateTime(ParamUtils.toLong(it.getMinCreateTime()));
//        leaseCountParam.setMaxCreateTime(ParamUtils.toLong(it.getMaxCreateTime()));
//        leaseCountParam.setMinReturnTime(ParamUtils.toLong(it.getMinReturnTime()));
//        leaseCountParam.setMaxReturnTime(ParamUtils.toLong(it.getMaxReturnTime()));
//        leaseCountParam.setStartSceneType(ParamUtils.toList(it.getStartSceneType()));
//        leaseCountParam.setStartScene(ParamUtils.toList(it.getStartScene()));
//        leaseCountParam.setStartPointId(ParamUtils.toInt(it.getStartPointId()));
//        leaseCountParam.setStartPointName(it.getStartPointName());
//        leaseCountParam.setStartProvince(it.getStartProvince());
//        leaseCountParam.setStartCity(ParamUtils.toList(it.getStartCity()));
//        leaseCountParam.setStartDistrict(it.getStartDistrict());
//        leaseCountParam.setStartLocation(it.getStartLocation());
//        leaseCountParam.setEndSceneType(ParamUtils.toList(it.getEndSceneType()));
//        leaseCountParam.setEndScene(ParamUtils.toList(it.getEndScene()));
//        leaseCountParam.setEndPointId(it.getEndPointId());
//        leaseCountParam.setEndPointName(it.getEndPointName());
//        leaseCountParam.setEndProvince(it.getEndProvince());
//        leaseCountParam.setEndCity(ParamUtils.toList(it.getEndCity()));
//        leaseCountParam.setEndDistrict(it.getEndDistrict());
//        leaseCountParam.setEndLocation(it.getEndLocation());
//        leaseCountParam.setUserResource(ParamUtils.toInt(it.getUserResource()));
//        leaseCountParam.setDuration(ParamUtils.toInt(it.getDuration()));
//        leaseCountParam.setGroupField(ParamUtils.toList(it.getGroupField()));
//        return leaseCountParam;
//    }
//}
