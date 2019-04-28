package com.pro.umbrella.dao;

import com.pro.umbrella.model.pojo.TradeRefund;
import com.pro.umbrella.model.pojo.TradeRefundExample;
import java.util.List;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.session.RowBounds;

public interface TradeRefundMapper {
    long countByExample(TradeRefundExample example);

    int deleteByExample(TradeRefundExample example);

    int deleteByPrimaryKey(Integer id);

    int insert(TradeRefund record);

    int insertSelective(TradeRefund record);

    List<TradeRefund> selectByExampleWithRowbounds(TradeRefundExample example, RowBounds rowBounds);

    List<TradeRefund> selectByExample(TradeRefundExample example);

    TradeRefund selectByPrimaryKey(Integer id);

    int updateByExampleSelective(@Param("record") TradeRefund record, @Param("example") TradeRefundExample example);

    int updateByExample(@Param("record") TradeRefund record, @Param("example") TradeRefundExample example);

    int updateByPrimaryKeySelective(TradeRefund record);

    int updateByPrimaryKey(TradeRefund record);
}