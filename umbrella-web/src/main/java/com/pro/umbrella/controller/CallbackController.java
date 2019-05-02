package com.pro.umbrella.controller;

import com.fasterxml.jackson.core.type.TypeReference;
import com.pro.umbrella.api.json.JsonUtil;
import com.pro.umbrella.api.pojo.Money;
import com.pro.umbrella.model.constants.TypeStateEnums;
import com.pro.umbrella.model.ro.CallbackResp;
import com.pro.umbrella.model.ro.RefundBasicResp;
import com.pro.umbrella.service.TradeFlowService;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.annotation.Resource;
import java.util.List;
import java.util.Map;

/**
 * Created by lixiang on 2019/5/1.
 */
@Controller
@RequestMapping("/callback")
@ResponseBody
public class CallbackController {

    @Resource
    private TradeFlowService tradeFlowService;

    /**
     * 支付回调
     *
     * @param
     * @return
     */
    @RequestMapping(value = "/tradeflow/payresult", method = RequestMethod.POST)
    public CallbackResp tradePayCallback(@RequestBody String payNumber) {

        return CallbackResp.success(tradeFlowService.paySuccess(Long.parseLong(payNumber)));
    }

    /**
     * 退款回调
     *
     * @param callbackParam
     * @return
     */
    @RequestMapping(value = "/tradeflow/refundresult", method = RequestMethod.POST)
    public CallbackResp tradeRefundCallback(@RequestBody RefundBasicResp callbackParam) {
        long payNumber = Long.valueOf(callbackParam.getPayNo());
        Money refundAmount = Money.of(callbackParam.getAmount());
        tradeFlowService.refundSuccess(TypeStateEnums.OpUser.PAY, payNumber, refundAmount);
        return CallbackResp.success(null);
    }

}
