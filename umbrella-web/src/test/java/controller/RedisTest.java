package controller;

import com.pro.umbrella.api.json.JsonUtil;
import com.pro.umbrella.common.util.RedisAPI;
import com.pro.umbrella.model.pojo.UmbrellaCabinet;
import com.pro.umbrella.model.ro.UserRegisterReq;
import com.pro.umbrella.service.UmbrellaCabinetService;
import com.pro.umbrella.service.UserService;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import javax.annotation.Resource;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration("classpath:spring-mybatis.xml")
public class RedisTest {
    @Resource
    private RedisAPI redisAPI;
    @Resource
    private UmbrellaCabinetService umbrellaCabinetService;

    @Test
    public void get(){
        System.out.println(redisAPI.get("a"));
        System.out.println(redisAPI.get("b"));
        System.out.println(redisAPI.get("1037553415"));

    }
    @Test
    public void set(){
        UmbrellaCabinet umbrellaCabinet = umbrellaCabinetService.queryByCabinetId("1037553415");
        redisAPI.set(umbrellaCabinet.getUmbrellaCabinetNumber(),JsonUtil.toJson(umbrellaCabinet) );
        System.out.println(redisAPI.get(umbrellaCabinet.getUmbrellaCabinetNumber()));
    }
}
