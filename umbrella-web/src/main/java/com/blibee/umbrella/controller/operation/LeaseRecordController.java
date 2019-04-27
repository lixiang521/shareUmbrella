package com.blibee.umbrella.controller.operation;

import com.blibee.umbrella.model.JsonResult;
import com.blibee.umbrella.model.pojo.LeaseRecord;
import com.blibee.umbrella.model.ro.OperationUmbrellaCabinetAddReq;
import com.blibee.umbrella.service.LeaseRecordService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.List;

@Controller
@RequestMapping("/leaseRecord")
@ResponseBody
public class LeaseRecordController {
    @Autowired
    private LeaseRecordService leaseRecordService;

    @RequestMapping("/list")
    public JsonResult list(){
        return JsonResult.success(leaseRecordService.list());
    }

    @RequestMapping("/test")
    public List<LeaseRecord> test(){
        return leaseRecordService.list();
    }
}
