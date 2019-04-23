package com.blibee.umbrella.dao;

import com.blibee.umbrella.pojo.HandleResultLog;
import com.blibee.umbrella.pojo.HandleResultLogExample;
import java.util.List;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.session.RowBounds;

public interface HandleResultLogMapper {
    long countByExample(HandleResultLogExample example);

    int deleteByExample(HandleResultLogExample example);

    int deleteByPrimaryKey(Long id);

    int insert(HandleResultLog record);

    int insertSelective(HandleResultLog record);

    List<HandleResultLog> selectByExampleWithRowbounds(HandleResultLogExample example, RowBounds rowBounds);

    List<HandleResultLog> selectByExample(HandleResultLogExample example);

    HandleResultLog selectByPrimaryKey(Long id);

    int updateByExampleSelective(@Param("record") HandleResultLog record, @Param("example") HandleResultLogExample example);

    int updateByExample(@Param("record") HandleResultLog record, @Param("example") HandleResultLogExample example);

    int updateByPrimaryKeySelective(HandleResultLog record);

    int updateByPrimaryKey(HandleResultLog record);
}