package com.pro.umbrella.dao;

import com.pro.umbrella.model.pojo.SendOrder;
import com.pro.umbrella.model.pojo.SendOrderExample;
import java.util.List;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.session.RowBounds;

public interface SendOrderMapper {
    long countByExample(SendOrderExample example);

    int deleteByExample(SendOrderExample example);

    int deleteByPrimaryKey(Integer id);

    int insert(SendOrder record);

    int insertSelective(SendOrder record);

    List<SendOrder> selectByExampleWithRowbounds(SendOrderExample example, RowBounds rowBounds);

    List<SendOrder> selectByExample(SendOrderExample example);

    SendOrder selectByPrimaryKey(Integer id);

    int updateByExampleSelective(@Param("record") SendOrder record, @Param("example") SendOrderExample example);

    int updateByExample(@Param("record") SendOrder record, @Param("example") SendOrderExample example);

    int updateByPrimaryKeySelective(SendOrder record);

    int updateByPrimaryKey(SendOrder record);
}