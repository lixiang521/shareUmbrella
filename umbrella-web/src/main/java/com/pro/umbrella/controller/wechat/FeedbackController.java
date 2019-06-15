package com.pro.umbrella.controller.wechat;

import com.pro.umbrella.common.util.WAssert;
import com.pro.umbrella.model.constants.JsonResult;
import com.pro.umbrella.model.pojo.Feedback;
import com.pro.umbrella.model.pojo.User;
import com.pro.umbrella.model.ro.UserRegisterReq;
import com.pro.umbrella.service.FeedBackService;
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
@RequestMapping("/wechat/feedback")
@ResponseBody
public class FeedbackController {
    @Resource
    private FeedBackService feedBackService;
    @Resource
    private UserService userService;

    @RequestMapping("/add")
    public JsonResult add(@RequestBody Feedback req,@RequestParam("uid")Long uid) {
        User user = userService.queryByUid(req.getUid());
        WAssert.notNull(user,"用户不存在");
        //uid content必传，其他选传
        return JsonResult.success(feedBackService.add(req,uid));
    }


}
