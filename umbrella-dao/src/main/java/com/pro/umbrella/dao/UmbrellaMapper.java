package com.pro.umbrella.dao;

import com.pro.umbrella.model.pojo.Umbrella;
import com.pro.umbrella.model.pojo.UmbrellaExample;
import java.util.List;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.session.RowBounds;

public interface UmbrellaMapper {
    long countByExample(UmbrellaExample example);

    int deleteByExample(UmbrellaExample example);

    int deleteByPrimaryKey(Integer id);

    int insert(Umbrella record);

    int insertSelective(Umbrella record);

    List<Umbrella> selectByExampleWithRowbounds(UmbrellaExample example, RowBounds rowBounds);

    List<Umbrella> selectByExample(UmbrellaExample example);

    Umbrella selectByPrimaryKey(Integer id);

    int updateByExampleSelective(@Param("record") Umbrella record, @Param("example") UmbrellaExample example);

    int updateByExample(@Param("record") Umbrella record, @Param("example") UmbrellaExample example);

    int updateByPrimaryKeySelective(Umbrella record);

    int updateByPrimaryKey(Umbrella record);
}