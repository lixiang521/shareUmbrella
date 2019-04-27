package com.blibee.umbrella.service;

import com.blibee.umbrella.dao.LeaseRecordMapper;
import com.blibee.umbrella.model.pojo.LeaseRecord;
import com.blibee.umbrella.model.pojo.LeaseRecordExample;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class LeaseRecordService {
    @Autowired
    private LeaseRecordMapper leaseRecordMapper;
    public void add(){
        LeaseRecord leaseRecord = new LeaseRecord();
        leaseRecordMapper.insertSelective(leaseRecord);
    }


    public List<LeaseRecord> list(){
        LeaseRecordExample leaseRecordExample = new LeaseRecordExample();
        return leaseRecordMapper.selectByExample(leaseRecordExample);
    }
}
