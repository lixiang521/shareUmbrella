package controller;

import com.blibee.umbrella.api.json.JsonUtil;
import com.blibee.umbrella.api.pojo.page.PageRequest;
import com.blibee.umbrella.model.constants.TransferState;
import com.blibee.umbrella.model.ro.OperationUmbrellaAddReq;
import com.blibee.umbrella.model.ro.OperationUmbrellaPageReq;
import com.blibee.umbrella.service.GlobalConfigService;
import com.blibee.umbrella.service.UmbrellaService;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

/**
 * Created by lixiang on 2019/04/27.
 */
@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration("classpath:spring-mybatis.xml")
public class UmbrellaControllerTest {
    @Autowired
    private UmbrellaService umbrellaService;
    @Autowired
    private GlobalConfigService globalConfigService;

    /**
     * 新增雨伞
     */
    @Test
    public void add() {
        OperationUmbrellaAddReq req = new OperationUmbrellaAddReq();
        req.setUmbrellaNumber("1037811005");
        req.setUmbrellaCabinetNumber("1027550397");
        req.setTransState(TransferState.UmbrellaTransferState.IN_CABINET);
        req.setHardVer("U1AD1.00");
        System.out.println(JsonUtil.toJson(req));
        umbrellaService.add(req);
    }

    /**
     * 雨伞更新
     */
    @Test
    public void update() {
        OperationUmbrellaAddReq req = new OperationUmbrellaAddReq();
        req.setUmbrellaNumber("1037811005");
        req.setUmbrellaCabinetNumber("1027550397");
        req.setTransState(TransferState.UmbrellaTransferState.IN_CABINET);
        req.setHardVer("U2AD2.00");
        System.out.println(JsonUtil.toJson(req));
        umbrellaService.update(req);
    }

    /**
     * 雨伞搜索列表
     */
    @Test
    public void list() {
        OperationUmbrellaPageReq req = new OperationUmbrellaPageReq();
        req.setUmbrellaNumber("");
        req.setTransState(TransferState.UmbrellaTransferState.BORROWING);
        req.setHardVer("");
        req.setPage(new PageRequest.Page(5, 1));

        System.out.println(JsonUtil.toJson(req));
        System.out.println(JsonUtil.toJson(umbrellaService.list(req)));
    }

    /**
     * 获取雨伞硬件列表
     */
    @Test
    public void hardVers() {
        System.out.println(JsonUtil.toJson(globalConfigService.hardVers()));
    }
}
