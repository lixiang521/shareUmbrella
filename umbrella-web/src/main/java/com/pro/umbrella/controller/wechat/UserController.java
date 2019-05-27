package com.pro.umbrella.controller.wechat;

import com.pro.umbrella.model.constants.JsonResult;
import com.pro.umbrella.model.ro.OperationUmbrellaAddReq;
import com.pro.umbrella.model.ro.UserRegisterReq;
import com.pro.umbrella.service.UserService;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
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


    /**
     * 修改密码
     *
     * @param uid
     * @param pwd
     * @return
     */
    @RequestMapping("/changePWD")
    public JsonResult changePWD(@RequestParam("uid") Long uid, @RequestParam("pwd") String pwd) {
        return JsonResult.success(userService.changePWD(uid,pwd));
    }

    /**
     * 充值
     * @param uid
     * @param money
     * @return
     */
    @RequestMapping("/chongzhi")
    public JsonResult chongzhi(@RequestParam("uid") Long uid, @RequestParam("money") long money) {
        return JsonResult.success(userService.chongzhi(uid,money));
    }

    /**
     * 用户详情
     * @param uid
     * @return
     */
    @RequestMapping("/detail")
    public JsonResult detail(@RequestParam("uid") Long uid) {
        return JsonResult.success(userService.queryByUid(uid));
    }
}
