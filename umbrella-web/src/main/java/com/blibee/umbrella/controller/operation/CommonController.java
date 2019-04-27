package com.blibee.umbrella.controller.operation;

import com.blibee.umbrella.model.constants.JsonResult;
import com.blibee.umbrella.service.GlobalConfigService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

/**
 * Created by lixiang on 2019/04/27.
 */
@Controller
@RequestMapping("/common")
@ResponseBody
public class CommonController {
    @Autowired
    private GlobalConfigService globalConfigService;

    /**
     * 获取雨伞硬件列表
     *
     * @return
     */
    @RequestMapping("/umbrella/hardVer")
    public JsonResult umbrellaHardVer() {
        return JsonResult.success(globalConfigService.hardVers());
    }

}
