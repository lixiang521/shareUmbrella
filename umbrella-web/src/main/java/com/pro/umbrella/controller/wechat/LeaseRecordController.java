package com.pro.umbrella.controller.wechat;

import com.pro.umbrella.model.constants.JsonResult;
import com.pro.umbrella.model.pojo.LeaseRecord;
import com.pro.umbrella.model.ro.EndLeaseReq;
import com.pro.umbrella.model.ro.LeaseDetailResp;
import com.pro.umbrella.model.ro.StartLeaseReq;
import com.pro.umbrella.service.LeaseRecordService;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.annotation.Resource;
import java.util.Date;

/**
 * Created by lixiang on 2019/5/2.
 */
@Controller
@RequestMapping("/wechat/lease/record")
@ResponseBody
public class LeaseRecordController {
    @Resource
    private LeaseRecordService leaseRecordService;

    @RequestMapping("/haveLeases")
    public JsonResult haveLeases(@RequestParam("uid") Long uid) {
        return JsonResult.success(leaseRecordService.haveByUid(uid));
    }

    @RequestMapping("/start/trade")
    public JsonResult startTrade(@RequestParam("uid") Long uid, @RequestParam("url") String url) {
        leaseRecordService.createTrade(uid, url);

        return JsonResult.success(null);
    }

    /**
     * 开始租赁，出伞
     *
     * @param
     * @return
     */
    @RequestMapping(value = "/start/lease")
    public JsonResult createLease(@RequestBody StartLeaseReq startLeaseReq) {
        return JsonResult.success(leaseRecordService.startLeaseV2(startLeaseReq.getLeaseNumber(),
                startLeaseReq.getRetryReson()));
    }

    @RequestMapping(value = "/end/lease")
    public JsonResult endLease(@RequestBody EndLeaseReq endLeaseReq) {
        return JsonResult.success(leaseRecordService.endLease(endLeaseReq.getUid().toString(), leaseRecordService.queryDetail(endLeaseReq.getLeaseNumber()), endLeaseReq.getCabinetId(), new Date(), endLeaseReq.getEndState()));
    }


    /**
     * 租赁详情
     *
     * @param leaseNumber
     * @return
     */
    @RequestMapping("/detail")
    public JsonResult<LeaseDetailResp> detail(@RequestParam("leaseNumber") Long leaseNumber) {
        return JsonResult.success(leaseRecordService.detail(leaseNumber));
    }

}
