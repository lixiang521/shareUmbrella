package com.pro.umbrella.controller.operation;

import com.pro.umbrella.model.constants.JsonResult;
import com.pro.umbrella.model.ro.JsonCabinetEsResp;
import com.pro.umbrella.model.ro.OperationUmbrellaCabinetAddReq;
import com.pro.umbrella.model.ro.OperationUmbrellaCabinetPageReq;
import com.pro.umbrella.service.UmbrellaCabinetService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

/**
 * Created by lixiang on 2019/04/27.
 */
@Controller
@RequestMapping("/umbrella/cabinet")
@ResponseBody
public class UmbrellaCabinetController {
    @Autowired
    private UmbrellaCabinetService umbrellaCabinetService;

    /**
     * 新增伞柜
     *
     * @param req
     * @return
     */
    @RequestMapping("/add")
    public JsonResult add(@RequestBody OperationUmbrellaCabinetAddReq req) {
        umbrellaCabinetService.add(req);
        return JsonResult.success(null);
    }

    /**
     * 查询列表，分页
     *
     * @param req
     * @return
     */
    @RequestMapping("/list/v1")
    public JsonResult list(@RequestBody OperationUmbrellaCabinetPageReq req) {
        return JsonResult.success(umbrellaCabinetService.queryList(req));
    }

}
