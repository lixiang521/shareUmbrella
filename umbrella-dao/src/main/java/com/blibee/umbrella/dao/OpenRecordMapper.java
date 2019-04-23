package com.blibee.umbrella.dao;

import com.blibee.umbrella.pojo.OpenRecord;
import com.blibee.umbrella.pojo.OpenRecordExample;
import java.util.List;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.session.RowBounds;

public interface OpenRecordMapper {
    long countByExample(OpenRecordExample example);

    int deleteByExample(OpenRecordExample example);

    int deleteByPrimaryKey(Integer id);

    int insert(OpenRecord record);

    int insertSelective(OpenRecord record);

    List<OpenRecord> selectByExampleWithRowbounds(OpenRecordExample example, RowBounds rowBounds);

    List<OpenRecord> selectByExample(OpenRecordExample example);

    OpenRecord selectByPrimaryKey(Integer id);

    int updateByExampleSelective(@Param("record") OpenRecord record, @Param("example") OpenRecordExample example);

    int updateByExample(@Param("record") OpenRecord record, @Param("example") OpenRecordExample example);

    int updateByPrimaryKeySelective(OpenRecord record);

    int updateByPrimaryKey(OpenRecord record);
}