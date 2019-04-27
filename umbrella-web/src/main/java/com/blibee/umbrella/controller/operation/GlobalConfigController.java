package com.blibee.umbrella.controller.operation;

import com.blibee.umbrella.model.constants.JsonResult;
import com.blibee.umbrella.service.GlobalConfigService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

/**
 * Created by lixiang on 2019/04/27.
 */
@Controller
@RequestMapping("/global/config")
@ResponseBody
public class GlobalConfigController {
    @Autowired
    private GlobalConfigService globalConfigService;

    /**
     * 新增全局配置
     *
     * @param name
     * @param context
     * @param note
     * @return
     */
    @RequestMapping("/add")
    public JsonResult add(@RequestParam("name") String name, @RequestParam("context") String context, @RequestParam("note") String note) {
        globalConfigService.add(name, context, note);
        return JsonResult.success(null);
    }
}
