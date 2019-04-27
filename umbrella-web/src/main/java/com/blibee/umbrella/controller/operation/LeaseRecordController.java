package com.blibee.umbrella.controller.operation;

import com.blibee.umbrella.model.constants.JsonResult;
import com.blibee.umbrella.model.pojo.LeaseRecord;
import com.blibee.umbrella.service.LeaseRecordService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.List;

/**
 * Created by lixiang on 2019/04/27.
 */
@Controller
@RequestMapping("/leaseRecord")
@ResponseBody
public class LeaseRecordController {
    @Autowired
    private LeaseRecordService leaseRecordService;

    @RequestMapping("/list")
    public JsonResult list() {
        return JsonResult.success(leaseRecordService.list());
    }

    @RequestMapping("/test")
    public List<LeaseRecord> test() {
        return leaseRecordService.list();
    }
}
