package com.blibee.umbrella.dao;

import com.blibee.umbrella.pojo.LeaseRecord;
import com.blibee.umbrella.pojo.LeaseRecordExample;
import java.util.List;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.session.RowBounds;

public interface LeaseRecordMapper {
    long countByExample(LeaseRecordExample example);

    int deleteByExample(LeaseRecordExample example);

    int deleteByPrimaryKey(Integer id);

    int insert(LeaseRecord record);

    int insertSelective(LeaseRecord record);

    List<LeaseRecord> selectByExampleWithRowbounds(LeaseRecordExample example, RowBounds rowBounds);

    List<LeaseRecord> selectByExample(LeaseRecordExample example);

    LeaseRecord selectByPrimaryKey(Integer id);

    int updateByExampleSelective(@Param("record") LeaseRecord record, @Param("example") LeaseRecordExample example);

    int updateByExample(@Param("record") LeaseRecord record, @Param("example") LeaseRecordExample example);

    int updateByPrimaryKeySelective(LeaseRecord record);

    int updateByPrimaryKey(LeaseRecord record);
}