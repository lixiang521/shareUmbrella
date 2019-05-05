package controller;

import com.pro.umbrella.api.json.JsonUtil;
import com.pro.umbrella.common.util.CryptoUtils;
import com.pro.umbrella.model.ro.UserRegisterReq;
import com.pro.umbrella.service.UserService;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import javax.annotation.Resource;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration("classpath:spring-mybatis.xml")
public class UserServiceTest {
    @Resource
    private UserService userService;

    @Test
    public void phone(){
         System.out.println(userService.getPhone("MTM5OTgzNTg0MTI="));
        System.out.println(userService.getRealPhone("MTM5OTgzNTg0MTI="));

    }
    @Test
    public void register(){
        UserRegisterReq userRegisterReq = new UserRegisterReq();
        userRegisterReq.setPhone("17624005285");
        userRegisterReq.setPassword("123456");
        System.out.println(JsonUtil.toJson(userRegisterReq));
        userService.register(userRegisterReq);
    }
    @Test
    public void signin(){
        UserRegisterReq userRegisterReq = new UserRegisterReq();
        userRegisterReq.setPhone("17624005287");
        userRegisterReq.setPassword("123456");
        System.out.println(JsonUtil.toJson(userRegisterReq));
        userService.signin(userRegisterReq);
    }
}
