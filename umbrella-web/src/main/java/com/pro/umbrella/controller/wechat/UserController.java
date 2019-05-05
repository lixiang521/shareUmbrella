package com.pro.umbrella.controller.wechat;

import com.pro.umbrella.model.constants.JsonResult;
import com.pro.umbrella.model.ro.OperationUmbrellaAddReq;
import com.pro.umbrella.model.ro.UserRegisterReq;
import com.pro.umbrella.service.UserService;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.annotation.Resource;

/**
 * Created by lixiang on 2019/5/1.
 */
@Controller
@RequestMapping("/wechat/user")
@ResponseBody
public class UserController {
    @Resource
    private UserService userService;

    @RequestMapping("/register")
    public JsonResult register(@RequestBody UserRegisterReq req) {
        return JsonResult.success(userService.register(req));
    }

    @RequestMapping("/signin")
    public JsonResult signin(@RequestBody UserRegisterReq req) {
        return JsonResult.success(userService.signin(req));
    }
}
