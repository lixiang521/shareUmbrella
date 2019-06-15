package com.pro.umbrella.service;

import com.pro.umbrella.common.util.WAssert;
import com.pro.umbrella.dao.UmbrellaCabinetMapper;
import com.pro.umbrella.dao.WorkOrderMapper;
import com.pro.umbrella.model.constants.StateEnums;
import com.pro.umbrella.model.pojo.HandleResult;
import com.pro.umbrella.model.pojo.UmbrellaCabinet;
import com.pro.umbrella.model.pojo.WorkOrder;
import com.pro.umbrella.model.pojo.WorkOrderExample;
import com.pro.umbrella.model.ro.WorkOrderResp;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.List;
import java.util.stream.Collectors;

/**
 * Created by jiaoyang.ma on 2017/3/15.
 */
@Service
public class WorkOrderService {

    @Resource
    private WorkOrderMapper workOrderMapper;
    @Resource
    private UmbrellaCabinetService umbrellaCabinetService;
    @Resource
    private HandleResultService handleResultService;

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
    public List<WorkOrderResp> selectAll(Integer id, String cabinetId, String feedbackDec, Integer priority,Integer hasResult) {
        WorkOrderExample workOrderExample = new WorkOrderExample();
        WorkOrderExample.Criteria criteria = workOrderExample.createCriteria();
        if (id != null)
            criteria.andIdEqualTo(id);
        if (cabinetId != null && !"".equals(cabinetId))
            criteria.andUmbrellaCabinetNumberEqualTo(cabinetId);
        if (feedbackDec != null && !"".equals(feedbackDec))
            criteria.andFeedbackDescLike("%" + feedbackDec + "%");
        if (priority != null)
            criteria.andPriorityEqualTo(priority);
        List<WorkOrder> workOrderList = workOrderMapper.selectByExample(workOrderExample);
        List<WorkOrderResp> list = workOrderList.stream().map(x -> {
            WorkOrderResp workOrderResp = new WorkOrderResp();
            workOrderResp.setWorkOrder(x);
            List<HandleResult> handleResults = handleResultService.selectByWorkId(x.getId().longValue());
            workOrderResp.setHandleResult(handleResults);
            return workOrderResp;
        }).collect(Collectors.toList());
        if (hasResult==1&&!"".equals(hasResult))
            list = list.stream().filter(x->x.getHandleResult().size()>0).collect(Collectors.toList());
        return list;
    }


    public void add(WorkOrder workOrder) {
        UmbrellaCabinet umbrellaCabinet = umbrellaCabinetService.queryByCabinetId(workOrder.getUmbrellaCabinetNumber());
        WAssert.notNull(umbrellaCabinet, "雨伞柜不存在");
        workOrder.setState(StateEnums.WorkOrderState.NOT_START);
        workOrderMapper.insertSelective(workOrder);
    }
    public void update(WorkOrder workOrder) {
        workOrderMapper.updateByPrimaryKeySelective(workOrder);
    }

    public WorkOrder selectById(Integer id) {
        return workOrderMapper.selectByPrimaryKey(id);
    }
}
