package com.pro.umbrella.controller.operation;

import com.pro.umbrella.model.constants.JsonResult;
import com.pro.umbrella.model.ro.OperationUmbrellaAddReq;
import com.pro.umbrella.model.ro.OperationUmbrellaCabinetAddReq;
import com.pro.umbrella.model.ro.OperationUmbrellaPageReq;
import com.pro.umbrella.service.UmbrellaService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.annotation.Resource;

/**
 * Created by lixiang on 2019/04/27.
 */
@Controller
@RequestMapping("/umbrella")
@ResponseBody
public class UmbrellaController {
    @Resource
    private UmbrellaService umbrellaService;

    /**
     * 新增雨伞
     *
     * @param req
     * @return
     */
    @RequestMapping("/add")
    public JsonResult add(@RequestBody OperationUmbrellaAddReq req) {
        umbrellaService.add(req);
        return JsonResult.success(null);
    }

    /**
     * 更新雨伞信息
     *
     * @param req
     * @return
     */
    @RequestMapping("/update")
    public JsonResult update(@RequestBody OperationUmbrellaAddReq req) {
        umbrellaService.update(req);
        return JsonResult.success(null);
    }


    /**
     * 查询列表，分页
     *
     * @param req
     * @return
     */
    @RequestMapping("/list")
    public JsonResult list(@RequestBody OperationUmbrellaPageReq req) {
        return JsonResult.success(umbrellaService.list(req));
    }

}
