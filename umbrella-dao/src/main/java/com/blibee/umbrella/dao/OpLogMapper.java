package com.blibee.umbrella.dao;

import com.blibee.umbrella.pojo.OpLog;
import com.blibee.umbrella.pojo.OpLogExample;
import java.util.List;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.session.RowBounds;

public interface OpLogMapper {
    long countByExample(OpLogExample example);

    int deleteByExample(OpLogExample example);

    int deleteByPrimaryKey(Integer id);

    int insert(OpLog record);

    int insertSelective(OpLog record);

    List<OpLog> selectByExampleWithBLOBsWithRowbounds(OpLogExample example, RowBounds rowBounds);

    List<OpLog> selectByExampleWithBLOBs(OpLogExample example);

    List<OpLog> selectByExampleWithRowbounds(OpLogExample example, RowBounds rowBounds);

    List<OpLog> selectByExample(OpLogExample example);

    OpLog selectByPrimaryKey(Integer id);

    int updateByExampleSelective(@Param("record") OpLog record, @Param("example") OpLogExample example);

    int updateByExampleWithBLOBs(@Param("record") OpLog record, @Param("example") OpLogExample example);

    int updateByExample(@Param("record") OpLog record, @Param("example") OpLogExample example);

    int updateByPrimaryKeySelective(OpLog record);

    int updateByPrimaryKeyWithBLOBs(OpLog record);

    int updateByPrimaryKey(OpLog record);
}