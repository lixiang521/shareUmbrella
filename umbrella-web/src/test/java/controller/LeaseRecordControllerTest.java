package controller;

import com.pro.umbrella.api.json.JsonUtil;
import com.pro.umbrella.api.pojo.Money;
import com.pro.umbrella.api.pojo.page.PageRequest;
import com.pro.umbrella.model.constants.TypeStateEnums;
import com.pro.umbrella.model.ro.EndLeaseReq;
import com.pro.umbrella.model.ro.OperationLeasePageReq;
import com.pro.umbrella.model.ro.OperationRefundUpdateReq;
import com.pro.umbrella.model.ro.StartLeaseReq;
import com.pro.umbrella.service.LeaseRecordService;
import com.pro.umbrella.service.TradeFlowService;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import javax.annotation.Resource;
import java.util.Date;

import static com.pro.umbrella.model.constants.LeaseStateEnums.LeaseState.END;
import static com.pro.umbrella.model.constants.OperationMethodState.REFUND_OPERATION;
import static com.pro.umbrella.model.constants.TypeStateEnums.EndType.NORMAL;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration("classpath:spring-mybatis.xml")
public class LeaseRecordControllerTest {
    @Autowired
    private LeaseRecordService leaseRecordService;
    @Resource
    private TradeFlowService tradeFlowService;

    @Test
    public void list() {
        OperationLeasePageReq operationLeasePageReq = new OperationLeasePageReq();
//        operationLeasePageReq.setId("323283067139530752");
//        operationLeasePageReq.setUmbrellaNumber("");
//        operationLeasePageReq.setStartCabinetNumber("");
//        operationLeasePageReq.setEndCabinetNumber("");
//        operationLeasePageReq.setUid("");
//        operationLeasePageReq.setLeaseState("");
//        operationLeasePageReq.setTradeState("");
//        operationLeasePageReq.setMinCreateTime("");
//        operationLeasePageReq.setMaxCreateTime("");
//        operationLeasePageReq.setMinReturnTime("");
//        operationLeasePageReq.setMaxReturnTime("");
//        operationLeasePageReq.setStartScene("");
//        operationLeasePageReq.setStartCity("");
//        operationLeasePageReq.setEndCity("");
//        operationLeasePageReq.setLeaseNode("");
//        String[] a = new String[10];
//        operationLeasePageReq.setLeaseStateArray(a);
//        operationLeasePageReq.setTradeStateArray(a);
//        operationLeasePageReq.setStartSceneTypeArray(a);
//        operationLeasePageReq.setStartSceneArray(a);
//        operationLeasePageReq.setStartCityArray(a);
//        operationLeasePageReq.setEndSceneTypeArray(a);
//        operationLeasePageReq.setEndSceneArray(a);
//        operationLeasePageReq.setEndCityArray(a);
//        operationLeasePageReq.setCodeArray(a);
//        operationLeasePageReq.setCabinetDetailNum("");
//        operationLeasePageReq.setEntranceArray(a);
//        operationLeasePageReq.setPayTypeArray(a);
//        operationLeasePageReq.setPage(new PageRequest.Page(10, 1));
        System.out.println(JsonUtil.toJson(operationLeasePageReq));
        System.out.println(JsonUtil.toJson(leaseRecordService.list(operationLeasePageReq)));
    }

    @Test
    public void createTrade() {
        System.out.println(JsonUtil.toJson(leaseRecordService.createTrade(1, "http://r.umbrella.com/u/?c=1037553415")));
    }

    @Test
    public void paySuccess() {
        System.out.println(JsonUtil.toJson(tradeFlowService.paySuccess(Long.parseLong("7707348943273002361"))));
    }

    @Test
    public void start() {
        StartLeaseReq startLeaseReq = new StartLeaseReq();
        startLeaseReq.setLeaseNumber(323399718364123136l);
        startLeaseReq.setUid(1l);
        System.out.println(JsonUtil.toJson(startLeaseReq));
        System.out.println(JsonUtil.toJson(leaseRecordService.startLeaseV2(startLeaseReq.getLeaseNumber(), startLeaseReq.getUid())));
    }

    @Test
    public void haveLease() {
        System.out.println(JsonUtil.toJson(leaseRecordService.haveByUid(1l)));
    }

    @Test
    public void endLease(){
        EndLeaseReq endLeaseReq = new EndLeaseReq();
        endLeaseReq.setLeaseNumber(323352276974706688l);
        endLeaseReq.setUid(1l);
        endLeaseReq.setCabinetId("2037000119");
        endLeaseReq.setEndState(NORMAL);
        System.out.println(JsonUtil.toJson(endLeaseReq));
        System.out.println(JsonUtil.toJson(leaseRecordService.endLease(endLeaseReq.getUid().toString(), leaseRecordService.queryDetail(endLeaseReq.getLeaseNumber()), endLeaseReq.getCabinetId(), new Date(), endLeaseReq.getEndState())));
    }
    @Test
    public void refundSuccess(){
        long payNumber = Long.valueOf("4540030072179819136");
        Money refundAmount = Money.of("49");
        tradeFlowService.refundSuccess(TypeStateEnums.OpUser.PAY, payNumber,refundAmount );
    }
    @Test
    public void opRefund(){
        OperationRefundUpdateReq request = new OperationRefundUpdateReq();
        request.setLeaseNumber("323352688144556032");
        request.setStatus(REFUND_OPERATION);
        request.setRefundAmount(2d);
        System.out.println(JsonUtil.toJson(request));
        System.out.println(JsonUtil.toJson(leaseRecordService.updateFaultState(request)));
    }
    @Test
    public void details(){
        System.out.println(JsonUtil.toJson(leaseRecordService.detail(323352688144556032l)));
    }
    @Test
    public void list1(){
        System.out.println(JsonUtil.toJson(leaseRecordService.listByUid(1l)));
    }
}
