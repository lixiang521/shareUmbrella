package com.pro.umbrella.controller.operation;

import com.pro.umbrella.model.constants.JsonResult;
import com.pro.umbrella.model.pojo.OperationUser;
import com.pro.umbrella.model.ro.OpUserRegisterReq;
import com.pro.umbrella.model.ro.UserRegisterReq;
import com.pro.umbrella.service.OpUserService;
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
@RequestMapping("/op/user")
@ResponseBody
public class OpUserController {
    @Resource
    private OpUserService opUserService;


    /**
     * 登录
     * @param req
     * @return
     */
    @RequestMapping("/signin")
    public JsonResult signin(@RequestBody OpUserRegisterReq req) {
        return JsonResult.success(opUserService.signin(req));
    }

    /**
     * 新增
     * @param id
     * @param phone
     * @param username
     * @param roleId
     * @return
     */
    @RequestMapping("/add")
    public JsonResult add(@RequestParam("id") Integer id, @RequestParam("phone") String phone,
                          @RequestParam("username") String username, @RequestParam("roleId") Byte roleId) {
        return JsonResult.success(opUserService.add(id, phone, username, roleId));
    }

    /**
     * 新增
     * @param id
     * @return
     */
    @RequestMapping("/delete")
    public JsonResult delete(@RequestParam("id") Integer id,@RequestParam("deleteId") Integer deleteId) {
        return JsonResult.success(opUserService.delete(id, deleteId));
    }
    /**
     * s搜索
     * @param roleId
     * @param phone
     * @return
     */
    @RequestMapping("/query")
    public JsonResult query(@RequestParam("roleId") Byte roleId, @RequestParam("phone") String phone){
        return JsonResult.success(opUserService.query(roleId, phone));

    }


    /**
     * 修改密码
     *
     * @param id
     * @param pwd
     * @return
     */
    @RequestMapping("/changePWD")
    public JsonResult changePWD(@RequestParam("id") Integer id, @RequestParam("pwd") String pwd) {
        return JsonResult.success(opUserService.changePWD(id, pwd));
    }


    /**
     * 用户详情
     *
     * @param id
     * @return
     */
    @RequestMapping("/detail")
    public JsonResult detail(@RequestParam("id") Integer id) {
        return JsonResult.success(opUserService.queryById(id));
    }
}
