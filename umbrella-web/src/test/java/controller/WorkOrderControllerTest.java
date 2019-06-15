package controller;

import com.pro.umbrella.api.json.JsonUtil;
import com.pro.umbrella.common.util.WAssert;
import com.pro.umbrella.model.constants.StateEnums;
import com.pro.umbrella.model.pojo.Feedback;
import com.pro.umbrella.model.pojo.HandleResult;
import com.pro.umbrella.model.pojo.User;
import com.pro.umbrella.model.pojo.WorkOrder;
import com.pro.umbrella.model.ro.WorkOrderResp;
import com.pro.umbrella.service.FeedBackService;
import com.pro.umbrella.service.HandleResultService;
import com.pro.umbrella.service.UserService;
import com.pro.umbrella.service.WorkOrderService;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import javax.annotation.Resource;
import java.util.List;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration("classpath:spring-mybatis.xml")
public class WorkOrderControllerTest {
    @Resource
    private WorkOrderService workOrderService;
    @Resource
    private HandleResultService handleResultService;

    @Test
    public void add(){
        WorkOrder workOrder = new WorkOrder();
        workOrder.setUmbrellaCabinetNumber("1027550397");
        workOrder.setPriority(StateEnums.WorkOrderPriority.LOW);
        workOrder.setFeedbackDesc("77777");
        workOrderService.add(workOrder);
    }

    @Test
    public void addResult(){
        HandleResult handleResult = new HandleResult();
        handleResult.setWorkId(1l);
        handleResult.setUmbrellaTotalNum("10");
        handleResult.setUmbrellaDamagedNum("2");
        handleResult.setCodeState((byte)1);
        handleResult.setBorrowState((byte)1);
        handleResult.setReturnState((byte)1);
        handleResult.setVoiceState((byte)1);
        handleResult.setPositionState((byte)1);
        handleResult.setHardwareState((byte)1);
        handleResult.setHandleState((byte)1);
//        handleResult.setPic(it.getPic());
//        handleResult.setVideo(it.getVideo());
        handleResultService.add(handleResult);
    }
    @Test
    public void list(){
        List<WorkOrderResp> list =   workOrderService.selectAll(null,null,null,null,1);
//        List<WorkOrderResp> list =   workOrderService.selectAll(null,null,"7",null);
//        List<WorkOrderResp> list =   workOrderService.selectAll(null,"1027550397",null,null);
//        List<WorkOrderResp> list =   workOrderService.selectAll(1,null,null,null);
       System.out.println(JsonUtil.toJson(list));
    }
//    @Test
//    public void refuse(){
//        feedBackService.refuse(1);
//    }
//    @Test
//    public void refund(){
//        feedBackService.refund(1);
//    }
//
//    @Test
//    public void end(){
//        feedBackService.end(1);
//    }
}
