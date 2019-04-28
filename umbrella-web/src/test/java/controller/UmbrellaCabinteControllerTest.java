package controller;

import com.pro.umbrella.api.json.JsonUtil;
import com.pro.umbrella.model.constants.TransferState;
import com.pro.umbrella.model.ro.OperationUmbrellaCabinetAddReq;
import com.pro.umbrella.service.UmbrellaCabinetService;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import java.math.BigDecimal;
import java.util.Date;

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
        req.setDeviceId("550397");
        req.setUmbrellaCabinetNumber("1027550397");
        req.setLongitude(BigDecimal.valueOf(116.4666171));
        req.setLatitude(BigDecimal.valueOf(39.92865856));
        req.setTransState(TransferState.UmbrellaCabinetTransferState.WAIT_ONLINE);
        req.setHardVer("S3AE3.00");
        req.setSoftVer("blf_v302");
        req.setCity("上海");
        req.setScene("超级市场（批发城、家电城、家居城）");
        req.setCommentMessage("这是备注2");
        req.setPutDate(new Date());
        System.out.println(JsonUtil.toJson(req));
        umbrellaCabinetService.add(req);
    }
}
