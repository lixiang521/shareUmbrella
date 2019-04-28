package com.pro.umbrella.dao;

import com.pro.umbrella.model.pojo.TradeFlow;
import com.pro.umbrella.model.pojo.TradeFlowExample;
import java.util.List;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.session.RowBounds;

public interface TradeFlowMapper {
    long countByExample(TradeFlowExample example);

    int deleteByExample(TradeFlowExample example);

    int deleteByPrimaryKey(Integer id);

    int insert(TradeFlow record);

    int insertSelective(TradeFlow record);

    List<TradeFlow> selectByExampleWithRowbounds(TradeFlowExample example, RowBounds rowBounds);

    List<TradeFlow> selectByExample(TradeFlowExample example);

    TradeFlow selectByPrimaryKey(Integer id);

    int updateByExampleSelective(@Param("record") TradeFlow record, @Param("example") TradeFlowExample example);

    int updateByExample(@Param("record") TradeFlow record, @Param("example") TradeFlowExample example);

    int updateByPrimaryKeySelective(TradeFlow record);

    int updateByPrimaryKey(TradeFlow record);
}