package com.blibee.umbrella.dao;

import com.blibee.umbrella.model.pojo.HandleResult;
import com.blibee.umbrella.model.pojo.HandleResultExample;
import java.util.List;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.session.RowBounds;

public interface HandleResultMapper {
    long countByExample(HandleResultExample example);

    int deleteByExample(HandleResultExample example);

    int deleteByPrimaryKey(Integer id);

    int insert(HandleResult record);

    int insertSelective(HandleResult record);

    List<HandleResult> selectByExampleWithRowbounds(HandleResultExample example, RowBounds rowBounds);

    List<HandleResult> selectByExample(HandleResultExample example);

    HandleResult selectByPrimaryKey(Integer id);

    int updateByExampleSelective(@Param("record") HandleResult record, @Param("example") HandleResultExample example);

    int updateByExample(@Param("record") HandleResult record, @Param("example") HandleResultExample example);

    int updateByPrimaryKeySelective(HandleResult record);

    int updateByPrimaryKey(HandleResult record);
}