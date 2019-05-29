package com.pro.umbrella.controller.operation;

import com.pro.umbrella.model.constants.JsonResult;
import com.pro.umbrella.model.pojo.LeaseRecord;
import com.pro.umbrella.model.ro.OperationLeasePageReq;
import com.pro.umbrella.model.ro.OperationRefundUpdateReq;
import com.pro.umbrella.service.LeaseRecordService;
import com.pro.umbrella.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.annotation.Resource;
import java.util.List;

/**
 * Created by lixiang on 2019/04/27.
 */
@Controller
@RequestMapping("/lease/record")
@ResponseBody
public class OPLeaseRecordController {
    @Autowired
    private LeaseRecordService leaseRecordService;
    @Resource
    private UserService userService;

    @RequestMapping("/list")
    public JsonResult list(@RequestBody OperationLeasePageReq req) {
        return JsonResult.success(leaseRecordService.list(req));
    }

//    @RequestMapping("/phone")
//    public JsonResult phone(@RequestParam  String phone) {
//        return JsonResult.success(userService.getRealPhone(phone));
//    }

    @RequestMapping("/refund")
    public JsonResult update(@RequestBody OperationRefundUpdateReq request) {
        return JsonResult.success(leaseRecordService.updateFaultState(request));
    }

    @RequestMapping("/test")
    public List<LeaseRecord> test() {
        return null;
    }
}
