package controller;

import com.pro.umbrella.service.LeaseRecordService;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration("classpath:spring-mybatis.xml")
public class LeaseRecordControllerTest {
    @Autowired
    private LeaseRecordService leaseRecordService;
    @Test
    public void list(){
        System.out.println(leaseRecordService.list().get(0).getEndCity());
    }
}
