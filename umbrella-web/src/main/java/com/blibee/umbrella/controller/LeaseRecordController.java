package com.blibee.umbrella.controller;

import com.blibee.umbrella.pojo.LeaseRecord;
import com.blibee.umbrella.service.LeaseRecordService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
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
    public List<LeaseRecord> list(){
        return leaseRecordService.list();
    }
}
