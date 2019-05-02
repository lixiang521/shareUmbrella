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
        String name = "exam_charge";
        String context = "{\"strategy\":\"B1\",\"unitTime\":\"30\",\"deposit\":\"49\",\"freeTimeMinute\":\"1\",\"cost\":\"2\",\"dayLimitCharge\":\"8\"}\t";
        String note = "雨伞硬件版本";
        globalConfigService.add(name, context, note);
    }
}
