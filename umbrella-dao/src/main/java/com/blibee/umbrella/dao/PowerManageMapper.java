package com.blibee.umbrella.dao;

import com.blibee.umbrella.model.pojo.PowerManage;
import com.blibee.umbrella.model.pojo.PowerManageExample;
import java.util.List;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.session.RowBounds;

public interface PowerManageMapper {
    long countByExample(PowerManageExample example);

    int deleteByExample(PowerManageExample example);

    int deleteByPrimaryKey(Integer id);

    int insert(PowerManage record);

    int insertSelective(PowerManage record);

    List<PowerManage> selectByExampleWithRowbounds(PowerManageExample example, RowBounds rowBounds);

    List<PowerManage> selectByExample(PowerManageExample example);

    PowerManage selectByPrimaryKey(Integer id);

    int updateByExampleSelective(@Param("record") PowerManage record, @Param("example") PowerManageExample example);

    int updateByExample(@Param("record") PowerManage record, @Param("example") PowerManageExample example);

    int updateByPrimaryKeySelective(PowerManage record);

    int updateByPrimaryKey(PowerManage record);
}