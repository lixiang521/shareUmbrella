package com.pro.umbrella.controller.operation;

import com.pro.umbrella.common.util.WAssert;
import com.pro.umbrella.model.constants.JsonResult;
import com.pro.umbrella.model.pojo.Feedback;
import com.pro.umbrella.model.pojo.HandleResult;
import com.pro.umbrella.model.pojo.User;
import com.pro.umbrella.model.pojo.WorkOrder;
import com.pro.umbrella.service.FeedBackService;
import com.pro.umbrella.service.HandleResultService;
import com.pro.umbrella.service.WorkOrderService;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.annotation.Resource;

/**
 * Created by lixiang on 2019/5/1.
 */
@Controller
@RequestMapping("/op/workOrder")
@ResponseBody
public class WorkOrderController {
    @Resource
    private WorkOrderService workOrderService;
    @Resource
    private HandleResultService handleResultService;
    @RequestMapping("/add")
    public JsonResult add(@RequestBody WorkOrder req) {
        //传UmbrellaCabinetNumber Priority FeedbackDesc
        workOrderService.add(req);
        return JsonResult.success(null);
    }

    @RequestMapping("/update")
    public JsonResult update(@RequestBody WorkOrder req) {
        //id必传
        workOrderService.update(req);
        return JsonResult.success(null);
    }

    @RequestMapping("/list")
    public JsonResult list(@RequestParam("id") Integer id,@RequestParam("cabinetId")String  cabinetId,
                           @RequestParam("feedbackDec")String feedbackDec,@RequestParam("priority")Integer priority,
                           @RequestParam("hasResult")Integer hasResult) {
        //hasResult 1为有结果，0为全部
        return JsonResult.success(workOrderService.selectAll(id,cabinetId,feedbackDec,priority,hasResult));
    }

    @RequestMapping("/addResult")
    public JsonResult addResult(@RequestParam("req")HandleResult req){
        //传下面几项，状态0为正常，1为异常
//        handleResult.setWorkId(1l);
//        handleResult.setUmbrellaTotalNum("10");
//        handleResult.setUmbrellaDamagedNum("2");
//        handleResult.setCodeState((byte)1);
//        handleResult.setBorrowState((byte)1);
//        handleResult.setReturnState((byte)1);
//        handleResult.setVoiceState((byte)1);
//        handleResult.setPositionState((byte)1);
//        handleResult.setHardwareState((byte)1);
//        handleResult.setHandleState((byte)1);
        handleResultService.add(req);
        return JsonResult.success(null);
    }
//
//    @RequestMapping("/refund")
//    public JsonResult refund(@RequestParam("id")Integer id){
//        feedBackService.refund(id);
//        return JsonResult.success(null);
//    }
//
//    @RequestMapping("/end")
//    public JsonResult end(@RequestParam("id")Integer id){
//        feedBackService.end(id);
//        return JsonResult.success(null);
//    }
}
