import com.pro.umbrella.dao.LeaseRecordMapper;
import com.pro.umbrella.model.pojo.LeaseRecord;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration("classpath:spring-mybatis.xml")
public class LeaseTest {
    @Autowired
    private LeaseRecordMapper leaseRecordMapper;
    @Test
    public void list(){
        LeaseRecord leaseRecord = new LeaseRecord();
        leaseRecord.setEndCity("北京");
        leaseRecordMapper.insertSelective(leaseRecord);
    }

}
