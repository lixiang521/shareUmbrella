package controller;

import com.pro.umbrella.api.json.JsonUtil;
import com.pro.umbrella.api.pojo.page.PageRequest;

import com.pro.umbrella.model.constants.TransferStateEnums;
import com.pro.umbrella.model.ro.JsonCabinetEsResp;
import com.pro.umbrella.model.ro.OperationUmbrellaCabinetAddReq;
import com.pro.umbrella.model.ro.OperationUmbrellaCabinetPageReq;
import com.pro.umbrella.service.UmbrellaCabinetService;
import org.apache.commons.lang3.ArrayUtils;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

/**
 * Created by lixiang on 2019/04/27.
 */
@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration("classpath:spring-mybatis.xml")
public class UmbrellaCabinteControllerTest {
    @Autowired
    private UmbrellaCabinetService umbrellaCabinetService;

    /**
     * 新增雨伞柜
     */
    @Test
    public void add() {
        OperationUmbrellaCabinetAddReq req = new OperationUmbrellaCabinetAddReq();
        req.setDeviceId("000119");
        req.setUmbrellaCabinetNumber("2037000119");
        req.setLongitude(BigDecimal.valueOf(116.4666171));
        req.setLatitude(BigDecimal.valueOf(39.92865856));
        req.setTransState(TransferStateEnums.UmbrellaCabinetTransferState.WAIT_ONLINE);
        req.setHardVer("S3AE3.00");
        req.setSoftVer("blf_v302");
        req.setCity("上海");
        req.setScene("超级市场（批发城、家电城、家居城）");
        req.setCommentMessage("这是备注2");
        req.setPutDate(new Date());
        System.out.println(JsonUtil.toJson(req));
        umbrellaCabinetService.add(req);
    }

    @Test
    public void queryUmbrellaCabinet() {
        OperationUmbrellaCabinetPageReq req = new OperationUmbrellaCabinetPageReq();
//        req.setId("");
//        req.setMinCap(1);
//        req.setMaxCap(20);
//        List<Integer> list1 =  new ArrayList<>();
//        list1.add(1 );
//        list1.add(2 );
//        req.setTransStates(list1);
//        List<String> list2 =  new ArrayList<>();
//        list2.add("1");
//        list2.add("2");
//        req.setSoftVers(list2);
//        req.setHardVers(list2);
//        req.setScene(list2);
//        req.setDeviceId("");
//        req.setMinCsq(1);
//        req.setMaxCsq(20);
//        req.setMinVbat(1d);
//        req.setMaxVbat(20d);
//        req.setMinPutDate(new Date());
//        req.setMaxPutDate(new Date());
//        req.setIsOnline(1);
//        req.setCanLease(1);
//        req.setCanReturn(1);
//        req.setCity(list2);
        req.setPage(new PageRequest.Page(10, 1));
        System.out.println(JsonUtil.toJson(req));
        System.out.println(JsonUtil.toJson(umbrellaCabinetService.queryList(req)));

    }
    @Test
    public void update(){
        OperationUmbrellaCabinetAddReq req = new OperationUmbrellaCabinetAddReq();
        req.setDeviceId("000119");
        req.setUmbrellaCabinetNumber("2037000119");
        req.setLongitude(BigDecimal.valueOf(116.4666171));
        req.setLatitude(BigDecimal.valueOf(39.92865856));
        req.setTransState(TransferStateEnums.UmbrellaCabinetTransferState.WAIT_ONLINE);
        req.setHardVer("S3AE3.00");
        req.setSoftVer("blf_v666");
        req.setCity("上海");
        req.setScene("超级市场（批发城、家电城、家居城）");
        req.setCommentMessage("这是备注2");
        req.setPutDate(new Date());        req.setTransState(TransferStateEnums.UmbrellaCabinetTransferState.WAIT_ONLINE);
        System.out.println(JsonUtil.toJson(req));
        umbrellaCabinetService.update(req);
    }
}
