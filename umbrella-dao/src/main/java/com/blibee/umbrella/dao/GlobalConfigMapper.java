package com.blibee.umbrella.dao;

import com.blibee.umbrella.model.pojo.GlobalConfig;
import com.blibee.umbrella.model.pojo.GlobalConfigExample;
import java.util.List;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.session.RowBounds;

public interface GlobalConfigMapper {
    long countByExample(GlobalConfigExample example);

    int deleteByExample(GlobalConfigExample example);

    int deleteByPrimaryKey(Integer id);

    int insert(GlobalConfig record);

    int insertSelective(GlobalConfig record);

    List<GlobalConfig> selectByExampleWithRowbounds(GlobalConfigExample example, RowBounds rowBounds);

    List<GlobalConfig> selectByExample(GlobalConfigExample example);

    GlobalConfig selectByPrimaryKey(Integer id);

    int updateByExampleSelective(@Param("record") GlobalConfig record, @Param("example") GlobalConfigExample example);

    int updateByExample(@Param("record") GlobalConfig record, @Param("example") GlobalConfigExample example);

    int updateByPrimaryKeySelective(GlobalConfig record);

    int updateByPrimaryKey(GlobalConfig record);
}