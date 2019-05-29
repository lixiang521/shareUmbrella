//
//import javax.annotation.Resource;
//
//import com.pro.umbrella.api.json.JsonUtil;
//import com.pro.umbrella.api.pojo.page.PageRequest;
//import com.pro.umbrella.es.service.EsCabinetService;
//import com.pro.umbrella.es.service.param.CabinetQueryParam;
//import org.junit.Test;
//import org.junit.runner.RunWith;
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.test.context.ContextConfiguration;
//import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
//
//@RunWith(SpringJUnit4ClassRunner.class)
//@ContextConfiguration("classpath:spring-mybatis.xml")
//public class ClientTest {
//    @Autowired
//    private EsCabinetService esCabinetService;
//
//    @Test
//    public void queryCabinet() {
//        CabinetQueryParam param = new CabinetQueryParam();
////        PageRequest.Page page = new PageRequest.Page(2, 2);
////        param.setPage(page);
////        param.setMinCap(0);
////        param.setMaxCap(100);
//        param.setMaxCsq(26);
//        param.setMinCsq(20);
//        /*
//         * param.setMinCap(0); param.setMaxCap(100);
//         */
//        // List<Integer> list = new ArrayList<>();
//        // list.add(7);
//        // param.setTransStates(list);
//        // param.setDeviceId("222");
//        System.out.println("result : " + JsonUtil.toJson(esCabinetService.queryResult(param)));
//    }
//
//}
