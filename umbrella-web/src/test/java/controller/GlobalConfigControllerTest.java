package controller;

import com.pro.umbrella.service.GlobalConfigService;
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
public class GlobalConfigControllerTest {
    @Autowired
    private GlobalConfigService globalConfigService;

    /**
     * 新增全局配置
     */
    @Test
    public void add() {
        String name = "umbrella_hard_ver";
        String context = "[\"U1AD1.00\",\"U2AD2.00\",\"U2SD2.01\",\"U2CD2.00\",\"U2CD2.01\",\"U2CY2.03\",\"U2CT2.03\",\"U2CD2.03\",\"U3FD3.00\"]";
        String note = "雨伞硬件版本";
        globalConfigService.add(name, context, note);
    }
}
