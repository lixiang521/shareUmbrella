package com.pro.umbrella.service;

import com.pro.umbrella.dao.TradeRefundMapper;
import com.pro.umbrella.model.pojo.TradeRefund;
import com.pro.umbrella.model.pojo.TradeRefundExample;
import org.springframework.stereotype.Service;
import javax.annotation.Resource;
import java.util.Date;

@Service
public class TradeRefundService {
    @Resource
    private TradeRefundMapper tradeRefundMapper;
    public void insert(TradeRefund tradeRefund) {
        int i = tradeRefundMapper.insertSelective(tradeRefund);
        if (i <= 0) {
            throw new RuntimeException("数据操作失败");
        }
    }
    public int updateState(long refundTradeNumber, byte toState, byte fromState) {
        TradeRefund tradeRefund = new TradeRefund();
        tradeRefund.setState(toState);
        tradeRefund.setRefundTime(new Date());

        TradeRefundExample example = new TradeRefundExample();
        example.createCriteria().andRefundTradeNumberEqualTo(refundTradeNumber).andStateEqualTo(fromState);
        return tradeRefundMapper.updateByExampleSelective(tradeRefund, example);
    }
}
