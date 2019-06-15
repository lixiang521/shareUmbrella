package com.pro.umbrella.service;

import com.pro.umbrella.dao.FeedbackMapper;
import com.pro.umbrella.model.constants.StateEnums;
import com.pro.umbrella.model.pojo.Feedback;
import com.pro.umbrella.model.pojo.FeedbackExample;
import com.pro.umbrella.model.pojo.WorkOrder;
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

    public void refuse(Integer id){
        Feedback feedback = feedbackMapper.selectByPrimaryKey(id);
        feedback.setState(StateEnums.FeedbackState.REFUSE);
        feedbackMapper.updateByPrimaryKey(feedback);
    }

    public void refund(Integer id){
        Feedback feedback = feedbackMapper.selectByPrimaryKey(id);
        feedback.setState(StateEnums.FeedbackState.REFUND);
        feedbackMapper.updateByPrimaryKey(feedback);
    }

    public void end(Integer id){
        Feedback feedback = feedbackMapper.selectByPrimaryKey(id);
        feedback.setState(StateEnums.FeedbackState.END);
        feedbackMapper.updateByPrimaryKey(feedback);
    }

    public List<Feedback> selectAll(Integer id,Long selectUid, String content){
        FeedbackExample feedbackExample = new FeedbackExample();
        FeedbackExample.Criteria criteria = feedbackExample.createCriteria();
        if (id!=null)
            criteria.andIdEqualTo(id);
        if (selectUid!=null)
            criteria.andUidEqualTo(selectUid);
        if (content!=null&&!"".equals(content))
            criteria.andContentLike("%"+content+"%");
        return feedbackMapper.selectByExample(feedbackExample);
    }

    public long add(Feedback feedback,Long uid){
        add(feedback);
        return uid;
    }

    private void add(Feedback feedback){
        feedback.setState(StateEnums.FeedbackState.NOT_START);
        feedbackMapper.insertSelective(feedback);
    }
}
