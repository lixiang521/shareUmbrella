package com.pro.umbrella.service;

import com.pro.umbrella.common.util.WAssert;
import com.pro.umbrella.dao.HandleResultMapper;
import com.pro.umbrella.dao.WorkOrderMapper;
import com.pro.umbrella.model.constants.StateEnums;
import com.pro.umbrella.model.pojo.*;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.List;

/**
 * Created by jiaoyang.ma on 2017/3/15.
 */
@Service
public class HandleResultService {

    @Resource
    private HandleResultMapper handleResultMapper;
    @Resource
    private WorkOrderService workOrderService;
    @Resource
    private WorkOrderMapper workOrderMapper;

//    public int updateState(Feedback feedback) {
//        FeedbackExample example = new FeedbackExample();
//        example.createCriteria().andLeaseIdEqualTo(feedback.getLeaseId());
//        return feedbackMapper.updateByExampleSelective(feedback, example);
//    }
//
//    public void refuse(Integer id){
//        Feedback feedback = feedbackMapper.selectByPrimaryKey(id);
//        feedback.setState(FeedbackStateEnums.State.REFUSE);
//        feedbackMapper.updateByPrimaryKey(feedback);
//    }
//
//    public void refund(Integer id){
//        Feedback feedback = feedbackMapper.selectByPrimaryKey(id);
//        feedback.setState(FeedbackStateEnums.State.REFUND);
//        feedbackMapper.updateByPrimaryKey(feedback);
//    }
//
//    public void end(Integer id){
//        Feedback feedback = feedbackMapper.selectByPrimaryKey(id);
//        feedback.setState(FeedbackStateEnums.State.END);
//        feedbackMapper.updateByPrimaryKey(feedback);
//    }
//
//    public List<WorkOrder> selectAll(Integer id,String  cabinetId, String feedbackDec,Integer priority){
//        WorkOrderExample workOrderExample = new WorkOrderExample();
//        WorkOrderExample.Criteria criteria = workOrderExample.createCriteria();
//        if (id!=null)
//            criteria.andIdEqualTo(id);
//        if (cabinetId!=null&&!"".equals(cabinetId))
//            criteria.andUmbrellaCabinetNumberEqualTo(cabinetId);
//        if (feedbackDec!=null&&!"".equals(feedbackDec))
//            criteria.andFeedbackDescLike("%"+feedbackDec+"%");
//        if (priority!=null)
//            criteria.andPriorityEqualTo(priority);
//        return workOrderMapper.selectByExample(workOrderExample);
//    }


    public void add(HandleResult handleResult) {
        WorkOrder workOrder = workOrderService.selectById(Integer.parseInt(handleResult.getWorkId().toString()));
        WAssert.notNull(workOrder, "工单不存在");
        handleResultMapper.insertSelective(handleResult);
        workOrder.setState(StateEnums.WorkOrderState.END);
        workOrderMapper.updateByPrimaryKey(workOrder);
    }
    public List<HandleResult> selectByWorkId(Long workId) {
        HandleResultExample handleResultExample = new HandleResultExample();
        handleResultExample.createCriteria().andWorkIdEqualTo(workId);
        return handleResultMapper.selectByExample(handleResultExample);
    }
}
