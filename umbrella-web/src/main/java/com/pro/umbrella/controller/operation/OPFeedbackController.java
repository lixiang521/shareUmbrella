package com.pro.umbrella.controller.operation;

import com.pro.umbrella.common.util.WAssert;
import com.pro.umbrella.model.constants.JsonResult;
import com.pro.umbrella.model.pojo.Feedback;
import com.pro.umbrella.model.pojo.User;
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
@RequestMapping("/op/feedback")
@ResponseBody
public class OPFeedbackController {
    @Resource
    private FeedBackService feedBackService;

    @RequestMapping("/list")
    public JsonResult list(@RequestParam("id")Integer id ,@RequestParam("selectUid") Long selectUid,@RequestParam("content")String content) {
        return JsonResult.success(feedBackService.selectAll(id,selectUid,content));
    }

    @RequestMapping("/refuse")
    public JsonResult refuse(@RequestParam("id")Integer id){
        feedBackService.refuse(id);
        return JsonResult.success(null);
    }

    @RequestMapping("/refund")
    public JsonResult refund(@RequestParam("id")Integer id){
        feedBackService.refund(id);
        return JsonResult.success(null);
    }

    @RequestMapping("/end")
    public JsonResult end(@RequestParam("id")Integer id){
        feedBackService.end(id);
        return JsonResult.success(null);
    }
}
