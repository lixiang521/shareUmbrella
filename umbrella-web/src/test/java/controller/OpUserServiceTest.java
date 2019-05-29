package controller;

import com.pro.umbrella.api.json.JsonUtil;
import com.pro.umbrella.model.ro.OpUserRegisterReq;
import com.pro.umbrella.model.ro.UserRegisterReq;
import com.pro.umbrella.service.OpUserService;
import com.pro.umbrella.service.UserService;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import javax.annotation.Resource;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration("classpath:spring-mybatis.xml")
public class OpUserServiceTest {
    @Resource
    private OpUserService opUserService;


    @Test
    public void changePWD(){
        System.out.println(opUserService.changePWD(1,"654321"));
    }

//    @Test
//    public void detail(){
//        System.out.println(JsonUtil.toJson(userService.queryByUid(1l)));
//    }

    @Test
    public void add(){

        System.out.println(JsonUtil.toJson(opUserService.add(1,"13988888887","xiang.li3",(byte)2)));
    }
    @Test
    public void signin(){
        OpUserRegisterReq userRegisterReq = new OpUserRegisterReq();
        userRegisterReq.setUsername("xiang.li");
        userRegisterReq.setPassword("123456");
        System.out.println(JsonUtil.toJson(userRegisterReq));
        System.out.println(JsonUtil.toJson(opUserService.signin(userRegisterReq)));
    }
    @Test
    public void query(){
        System.out.println(JsonUtil.toJson(opUserService.query(null,null )));
        System.out.println(JsonUtil.toJson(opUserService.query((byte)2,"13988888887" )));
    }
}
