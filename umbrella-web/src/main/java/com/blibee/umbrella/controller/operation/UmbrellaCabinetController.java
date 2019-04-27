package com.blibee.umbrella.controller.operation;

import com.blibee.umbrella.model.JsonResult;
import com.blibee.umbrella.model.pojo.LeaseRecord;
import com.blibee.umbrella.model.pojo.Umbrella;
import com.blibee.umbrella.model.pojo.UmbrellaCabinet;
import com.blibee.umbrella.model.ro.OperationUmbrellaCabinetAddReq;
import com.blibee.umbrella.service.LeaseRecordService;
import com.blibee.umbrella.service.UmbrellaCabinetService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.List;

@Controller
@RequestMapping("/umbrellaCabinet")
@ResponseBody
public class UmbrellaCabinetController {
    @Autowired
    private UmbrellaCabinetService umbrellaCabinetService;
    @RequestMapping("/add")
    public JsonResult add(@RequestBody OperationUmbrellaCabinetAddReq req){
        UmbrellaCabinet umbrellaCabinet = new UmbrellaCabinet();

        umbrellaCabinetService.add(umbrellaCabinet);
        return JsonResult.success(null);
    }
}
