package com.blibee.umbrella.dao;

import com.blibee.umbrella.pojo.PaymentCancelTask;
import com.blibee.umbrella.pojo.PaymentCancelTaskExample;
import java.util.List;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.session.RowBounds;

public interface PaymentCancelTaskMapper {
    long countByExample(PaymentCancelTaskExample example);

    int deleteByExample(PaymentCancelTaskExample example);

    int deleteByPrimaryKey(Integer id);

    int insert(PaymentCancelTask record);

    int insertSelective(PaymentCancelTask record);

    List<PaymentCancelTask> selectByExampleWithRowbounds(PaymentCancelTaskExample example, RowBounds rowBounds);

    List<PaymentCancelTask> selectByExample(PaymentCancelTaskExample example);

    PaymentCancelTask selectByPrimaryKey(Integer id);

    int updateByExampleSelective(@Param("record") PaymentCancelTask record, @Param("example") PaymentCancelTaskExample example);

    int updateByExample(@Param("record") PaymentCancelTask record, @Param("example") PaymentCancelTaskExample example);

    int updateByPrimaryKeySelective(PaymentCancelTask record);

    int updateByPrimaryKey(PaymentCancelTask record);
}