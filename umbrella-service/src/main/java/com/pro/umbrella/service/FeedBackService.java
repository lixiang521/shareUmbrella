package com.pro.umbrella.service;

import com.pro.umbrella.dao.FeedbackMapper;
import com.pro.umbrella.model.pojo.Feedback;
import com.pro.umbrella.model.pojo.FeedbackExample;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.List;

/**
 * Created by jiaoyang.ma on 2017/3/15.
 */
@Service
public class FeedBackService  {

    @Resource
    private FeedbackMapper feedbackMapper;

    public int updateState(Feedback feedback) {
        FeedbackExample example = new FeedbackExample();
        example.createCriteria().andLeaseIdEqualTo(feedback.getLeaseId());
        return feedbackMapper.updateByExampleSelective(feedback, example);
    }

    public long add(Feedback feedback){
        feedbackMapper.insertSelective(feedback);
        return feedback.getUid();
    }
}
