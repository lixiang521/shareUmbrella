package controller;

import com.pro.umbrella.api.json.JsonUtil;
import com.pro.umbrella.api.pojo.Money;
import com.pro.umbrella.common.util.WAssert;
import com.pro.umbrella.model.constants.TypeStateEnums;
import com.pro.umbrella.model.pojo.Feedback;
import com.pro.umbrella.model.pojo.User;
import com.pro.umbrella.model.ro.EndLeaseReq;
import com.pro.umbrella.model.ro.OperationLeasePageReq;
import com.pro.umbrella.model.ro.OperationRefundUpdateReq;
import com.pro.umbrella.model.ro.StartLeaseReq;
import com.pro.umbrella.service.FeedBackService;
import com.pro.umbrella.service.LeaseRecordService;
import com.pro.umbrella.service.TradeFlowService;
import com.pro.umbrella.service.UserService;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import javax.annotation.Resource;

import java.util.List;

import static com.pro.umbrella.model.constants.OperationMethodState.REFUND_OPERATION;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration("classpath:spring-mybatis.xml")
public class FeedbackControllerTest {
    @Resource
    private FeedBackService feedBackService;
    @Resource
    private UserService userService;

    @Test
    public void add(){
        Feedback feedback = new Feedback();
        feedback.setUid(1l);
        User user = userService.queryByUid(feedback.getUid());
        WAssert.notNull(user,"用户不存在");
        feedback.setContent("22");
        feedBackService.add(feedback,11l);
    }
    @Test
    public void list(){
//        List<Feedback> list =   feedBackService.selectAll(1l,null);
        List<Feedback> list =   feedBackService.selectAll(null,null,"2");
//       List<Feedback> list = feedBackService.selectAll(null,null);
       System.out.println(JsonUtil.toJson(list));
    }
    @Test
    public void refuse(){
        feedBackService.refuse(1);
    }
    @Test
    public void refund(){
        feedBackService.refund(1);
    }

    @Test
    public void end(){
        feedBackService.end(1);
    }
}
