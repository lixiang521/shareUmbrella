package com.pro.umbrella.service;

import com.google.common.collect.ImmutableList;
import com.pro.umbrella.api.pojo.Money;
import com.pro.umbrella.common.util.CryptoUtils;
import com.pro.umbrella.common.util.WAssert;
import com.pro.umbrella.dao.TradeFlowMapper;
import com.pro.umbrella.model.constants.LeaseNodeEnum;
import com.pro.umbrella.model.constants.LeaseStateEnums;
import com.pro.umbrella.model.constants.id.IDGenerator;
import com.pro.umbrella.model.constants.id.IDGeneratorSeed;
import com.pro.umbrella.model.pojo.LeaseRecord;
import com.pro.umbrella.model.pojo.TradeFlow;
import com.pro.umbrella.model.pojo.TradeFlowExample;
import com.pro.umbrella.model.pojo.TradeRefund;
import org.apache.commons.lang3.ObjectUtils;
import org.springframework.stereotype.Service;
import com.pro.umbrella.api.pojo.Currency;
import org.springframework.transaction.annotation.Transactional;

import javax.annotation.Resource;
import java.math.BigDecimal;
import java.util.*;

@Service
public class TradeFlowService {
    @Resource
    private IDGenerator idGenerator;
    @Resource
    private TradeFlowMapper tradeFlowMapper;
    @Resource
    private LeaseRecordService leaseRecordService;
    @Resource
    private TradeRefundService tradeRefundService;

//    public TradeFlow createTradeFlow(String operator, long uid, long leaseNumber, BigDecimal amount) {

//        LeaseRecord leaseRecord = leaseRecordService.queryDetail(leaseNumber);
//
//        TradeFlow waitFlow;
//        Byte nowState = leaseRecord.getTradeState();
//        // 行程没有支付过..
//        if (Objects.equals(nowState, LeaseStateEnums.LeaseTradeState.WAIT_PAY)
//                || (Objects.equals(nowState, LeaseStateEnums.LeaseTradeState.AUTH_FREE)
//        )) {
//            waitFlow = createPreFlowOrder(leaseRecord, operator, amount, uid);
//        } else {
//            waitFlow = WAssert
//                    .notNull(
//                            queryTradeByLeaseAndStates(leaseNumber,
//                                    ImmutableList.of(LeaseStateEnums.TradeFlowState.PREPAYING,
//                                            LeaseStateEnums.TradeFlowState.AUTH_FREE)),
//                            "租赁交易状态和流水交易状态不符合，请刷新重新查看.");
//        }

//        return waitFlow;
//
//    }

    public TradeFlow createPreFlowOrder(LeaseRecord leaseRecord, String operator, BigDecimal amount, long uid) {
        // 请求支付中心收单.
        Long payNo = CryptoUtils.getUUID();

        return createPreFlowOrder(leaseRecord, operator, amount, uid, payNo, true);
    }

    @Transactional
    public TradeFlow createPreFlowOrder(LeaseRecord leaseRecord, String operator, BigDecimal amount, long uid,
                                        Long payNo, boolean normal) {
        // 收单成功设置支付号, 预创建支付流水, 提交事务，交易停在 支付中（免押模式除外）.
        TradeFlow trade = createTradeFlow(uid, leaseRecord.getLeaseNumber(), payNo, amount,
                normal);

//        byte leaseTradeState = LeaseStateEnums.LeaseTradeState.PREPAYING;
        // 押金支付或免押申请中
        leaseRecordService.leaseNodeDataCollector(LeaseNodeEnum.WXAPP_UMBRELLA_DEPOSIT_PAY_START, uid,
                leaseRecord.getLeaseNumber());
        if (!normal) {
//            leaseTradeState = LeaseStateEnums.LeaseTradeState.AUTH_FREE;
        }
        // 预标记行程开始进入支付流程.
//        boolean updateLease = leaseRecordService.updateLeaseTradeState(leaseRecord.getLeaseNumber(),
//                leaseRecord.getTradeState(), leaseTradeState);
//        WAssert.isTrue(updateLease, "尝试标记租赁开始支付失败~请重试");
//        LeaseRecord leaseRecordNew = leaseRecord.cloneOne();
//        leaseRecordNew.setTradeState(leaseTradeState);
        return trade;
    }

    public TradeFlow createTradeFlow(long uid, long leaseNumber, Long tradeNumber, BigDecimal amount,
                                     boolean normal) {
        TradeFlow tradeFlow = new TradeFlow();
        tradeFlow.setUid(uid);
        tradeFlow.setTradeNumber(tradeNumber);
        tradeFlow.setLeaseNumber(leaseNumber);
        tradeFlow.setAmount(amount);
        tradeFlow.setState(LeaseStateEnums.TradeFlowState.WAIT_PAY);
        // 区分预授权免押和正常流程
        if (normal) {
            tradeFlow.setState(LeaseStateEnums.TradeFlowState.PREPAYING);
        } else {
            tradeFlow.setState(LeaseStateEnums.TradeFlowState.AUTH_FREE);
        }
        tradeFlow.setTradeTime(new Date());
        WAssert.isTrue(queryByTradeNumber(tradeNumber) == null, "支付流已冲突创建，请刷新重试");
        tradeFlowMapper.insertSelective(tradeFlow);
        return tradeFlow;
    }

    public TradeFlow queryByTradeNumber(long tradeNumber) {
        TradeFlowExample tradeFlowExample = new TradeFlowExample();
        tradeFlowExample.createCriteria().andTradeNumberEqualTo(tradeNumber);
        tradeFlowExample.setOrderByClause("create_time desc");
        List<TradeFlow> tradeFlows = tradeFlowMapper.selectByExample(tradeFlowExample);
        return tradeFlows.isEmpty() ? null : tradeFlows.get(0);
    }

    public TradeFlow queryTradeByLeaseAndStates(Long leaseNumber, List<Byte> state) {
        TradeFlowExample tradeFlowExample = new TradeFlowExample();
        tradeFlowExample.createCriteria().andLeaseNumberEqualTo(leaseNumber).andStateIn(state);
        tradeFlowExample.setOrderByClause("create_time desc limit 1");
        List<TradeFlow> tradeFlows = tradeFlowMapper.selectByExample(tradeFlowExample);
        return tradeFlows.isEmpty() ? null : tradeFlows.get(0);
    }


    @Transactional
    public Long paySuccess(long tradeNumber) {
        // 查找指定的流水支付记录.
        TradeFlow tradeFlow = queryByTradeNumber(tradeNumber);

        WAssert.notNull(tradeFlow, "错误的支付回调, 流水ID: {}", tradeNumber);

        LeaseRecord leaseRecordOld = WAssert
                .notNull(leaseRecordService.queryDetail(tradeFlow.getLeaseNumber()), "系统异常");

        byte preTradeState = LeaseStateEnums.TradeFlowState.PREPAYING;
        byte currTradeState = LeaseStateEnums.TradeFlowState.PAYED;

//        byte preLeaseTradeState = LeaseStateEnums.LeaseTradeState.PREPAYING;
        byte currLeaseTradeState = LeaseStateEnums.LeaseTradeState.PAYED;

        boolean updateTrade = updateState(tradeFlow.getTradeNumber(),
                Collections.singletonList(preTradeState), currTradeState);

        // TODO 这里的更新，没考虑并发情况，在更新前取出，然后更新，有可能在期间状态被改掉

        TradeFlow tradeFlowNew = tradeFlow.cloneOne();
        tradeFlowNew.setState(currTradeState);

        LeaseRecord leaseRecordNew = leaseRecordOld.cloneOne();
        leaseRecordNew.setTradeState(currLeaseTradeState);
        // 针对异常减免，重置相关字段
//        if (Money.of(leaseRecordNew.getAmount()).isZero()
//                && ObjectUtils.equals(LeaseStateEnums.LeaseState.REDUCE_END, leaseRecordNew.getLeaseState())) {
//            leaseRecordNew
//                    .setReduceAmount(leaseRecordNew.getTotalAmount().subtract(leaseRecordNew.getAmount()));
//            leaseRecordNew.setArchivedAmount(leaseRecordNew.getAmount());
//        }
//        int updateUser = leaseRecordService.updateByLeaseNumberAndLeaseTradeState(leaseRecordNew,
//                Collections.singletonList(preLeaseTradeState));
        // TODO 这里抛出异常，打算让调用方做什么。不抛出具体的异常信息，后端无法根据不同的异常信息，进行有效处理
//        WAssert.isTrue(updateUser > 0, "更新租赁{}预支付状态失败", tradeFlow.getLeaseNumber());
        return leaseRecordNew.getLeaseNumber();
    }

    public boolean updateState(long tradeNumber, List<Byte> prevState, Byte currState) {
        TradeFlowExample tradeFlowExample = new TradeFlowExample();
        tradeFlowExample.createCriteria().andTradeNumberEqualTo(tradeNumber);
        TradeFlow tradeFlow = new TradeFlow();
        tradeFlow.setTradeNumber(tradeNumber);
        tradeFlow.setState(currState);
        tradeFlow.setRefundTime(new Date());
        return tradeFlowMapper.updateByExampleSelective(tradeFlow, tradeFlowExample) == 1;
    }

    @Transactional
    public void refundTradeFlow(String operator, long tradeNumber, Money refundAmount, final byte refundType) {
        // 查询已经在支付的流水.
        TradeFlow tradeFlow = queryByTradeNumber(tradeNumber);
        WAssert.notNull(tradeFlow, "未能找到对应的流水{}", tradeNumber);
        WAssert.isTrue(tradeFlow.getState() == LeaseStateEnums.TradeFlowState.PAYED
                || tradeFlow.getState() == LeaseStateEnums.TradeFlowState.CLOSED
                || tradeFlow.getState() == LeaseStateEnums.TradeFlowState.PARTREFUND);
        // 默认退全款
        byte preTradeState = LeaseStateEnums.TradeFlowState.REFUNDING;
//        byte preLeaseTradeState = LeaseStateEnums.LeaseTradeState.REFUNDING;
        // 退预付款
        if (refundType == LeaseStateEnums.RefundType.REFUND_PREPAY) {
            preTradeState = LeaseStateEnums.TradeFlowState.PAYING;
//            preLeaseTradeState = LeaseStateEnums.LeaseTradeState.PAYING;
        }
        // 部分退款
        if (refundType == LeaseStateEnums.RefundType.REFUND_PART) {
            preTradeState = LeaseStateEnums.TradeFlowState.PARTREFUNDING;
//            preLeaseTradeState = LeaseStateEnums.LeaseTradeState.PARTREFUNDING;
        }
        // 开始尝试标记退款 默认预付款、部分退款字段为空
        Money refundPrepayAmount = null;
        Long refundPrepayTradeNumber = null;
        Money refundPartAmount = Money.zero(Currency.CNY);
        Long refundPartTradeNumber = null;

        // 退预付款
        if (refundType == LeaseStateEnums.RefundType.REFUND_PREPAY) {
            refundPrepayAmount = refundAmount;
            refundPrepayTradeNumber = idGenerator.generate(IDGeneratorSeed.PAYMENT_SERIAL_NUMBER);
        } else if (refundType == LeaseStateEnums.RefundType.REFUND_PART) { // 部分退款
            refundPartAmount = refundAmount;
            refundPartTradeNumber = idGenerator.generate(IDGeneratorSeed.REFUND);
        }

        if (refundType == LeaseStateEnums.RefundType.REFUND_PART) { // 部分退款
            TradeRefund tradeRefund = new TradeRefund();
            tradeRefund.setRefundAmount(refundAmount.getAmount());
            tradeRefund.setRefundTradeNumber(refundPartTradeNumber);
            tradeRefund.setLeaseNumber(tradeFlow.getLeaseNumber());
            tradeRefund.setTradeNumber(tradeFlow.getTradeNumber());
            tradeRefund.setState(preTradeState);
            tradeRefund.setOperator(operator);
            tradeRefundService.insert(tradeRefund);
        }

        boolean startRefund = updateRefundState(tradeFlow.getTradeNumber(),
                tradeFlow.getState(), preTradeState, refundPrepayTradeNumber, refundPrepayAmount,
                refundPartTradeNumber, refundPartAmount.plus(tradeFlow.getRefundPartAmount()));
        WAssert.isTrue(startRefund, "当前状态已无法操作退款, 请刷新重试~");

        TradeFlow tradeFlowNew = tradeFlow.cloneOne();
        tradeFlowNew.setState(preTradeState);
        if (refundPrepayAmount != null) {
            tradeFlowNew.setRefundPrepayAmount(refundPrepayAmount.getAmount());
        }
        tradeFlowNew.setRefundPrepayTradeNumber(refundPrepayTradeNumber);
        tradeFlowNew.setRefundPartTradeNumber(refundPartTradeNumber);
        tradeFlowNew.setRefundPartAmount(refundPartAmount.getAmount());

        // 预将租赁设置为部分退款中状态.
        LeaseRecord leaseRecordOld = leaseRecordService.queryDetail(tradeFlow.getLeaseNumber());
        LeaseRecord leaseRecordNew = leaseRecordOld.cloneOne();
//        leaseRecordNew.setTradeState(preLeaseTradeState);
        leaseRecordNew.setUpdateTime(new Date());
        Money toReduceFee = Money.of(BigDecimal.ZERO); // 要减掉的租赁费

        if (refundType != LeaseStateEnums.RefundType.REFUND_PREPAY) {
            if (refundType == LeaseStateEnums.RefundType.REFUND_PART) {
                BigDecimal newAmount = leaseRecordNew.getAmount().subtract(refundAmount.getAmount());
                leaseRecordNew.setAmount(newAmount);
                BigDecimal newReduceAmount = leaseRecordNew.getReduceAmount().add(refundAmount.getAmount());
                leaseRecordNew.setReduceAmount(newReduceAmount);
                toReduceFee = refundAmount;
                leaseRecordNew.setArchivedAmount(newAmount);
            } else {
                leaseRecordNew.setAmount(BigDecimal.ZERO);
                leaseRecordNew.setReduceAmount(leaseRecordNew.getTotalAmount());
                toReduceFee = Money.of(leaseRecordNew.getArchivedAmount());
                leaseRecordNew.setArchivedAmount(BigDecimal.ZERO);
            }
        }
        // 这里为什么不传入订单的当前状态。 传入当前状态可以避免状态修改的并发问题
        int result = leaseRecordService.updateByLeaseNumberAndLeaseState(leaseRecordNew, null);
        WAssert.isTrue(result > 0, "更新租赁{}交易状态失败", tradeFlow.getLeaseNumber());
        // 申请开始退款
        Long refundTradeNumber = tradeNumber;
        if (refundPrepayTradeNumber != null) {
            refundTradeNumber = refundPrepayTradeNumber;
        }
        if (refundPartTradeNumber != null) {
            refundTradeNumber = refundPartTradeNumber;
        }

        if (refundAmount.isZero()) {
            refundRecord(tradeFlow, operator, refundType, refundAmount);
        }
    }
    public boolean updateRefundState(long tradeNumber, Byte prevState, Byte currState, Long refundPrepayTradeNumber,
                                     Money refundPrepayAmount, Long refundPartTradeNumber, Money refundPartAmount) {
        TradeFlowExample tradeFlowExample = new TradeFlowExample();
        tradeFlowExample.createCriteria().andTradeNumberEqualTo(tradeNumber).andStateEqualTo(prevState);
        TradeFlow tradeFlow = new TradeFlow();
        tradeFlow.setState(currState);

        // 预付款
        tradeFlow.setRefundPrepayTradeNumber(refundPrepayTradeNumber);
        if (refundPrepayAmount != null) {
            tradeFlow.setRefundPrepayAmount(refundPrepayAmount.getAmount());
        }
        if (refundPrepayTradeNumber != null) {
            tradeFlow.setRefundPrepayTime(new Date());
        }

        // 部分退款
        tradeFlow.setRefundPartTradeNumber(refundPartTradeNumber);
        tradeFlow.setRefundPartAmount(refundPartAmount.getAmount());
        if (refundPartTradeNumber != null) {
            tradeFlow.setRefundPartTime(new Date());
        }

        if (refundPrepayTradeNumber == null && refundPartTradeNumber == null) {
            tradeFlow.setRefundTime(new Date());
        }

        return tradeFlowMapper.updateByExampleSelective(tradeFlow, tradeFlowExample) == 1;
    }

    private void refundRecord(TradeFlow tradeFlow, String operator, byte refundType, Money refundAmount) {
        // 默认全额退款
        byte preState = LeaseStateEnums.TradeFlowState.REFUNDING;
        byte curState = LeaseStateEnums.TradeFlowState.REFUND;
//        byte preLeaseTradeState = LeaseStateEnums.LeaseTradeState.REFUNDING;
//        byte curLeaseTradeState = LeaseStateEnums.LeaseTradeState.REFUND;

        // 退预付款
        if (refundType == LeaseStateEnums.RefundType.REFUND_PREPAY) {
            preState = LeaseStateEnums.TradeFlowState.PAYING;
            curState = LeaseStateEnums.TradeFlowState.PAYED;
//            preLeaseTradeState = LeaseStateEnums.LeaseTradeState.PAYING;
//            curLeaseTradeState = LeaseStateEnums.LeaseTradeState.PAYED;
        }

        // 部分退款
        if (refundType == LeaseStateEnums.RefundType.REFUND_PART) {
            preState = LeaseStateEnums.TradeFlowState.PARTREFUNDING;
            curState = LeaseStateEnums.TradeFlowState.PARTREFUND;
//            preLeaseTradeState = LeaseStateEnums.LeaseTradeState.PARTREFUNDING;
//            curLeaseTradeState = LeaseStateEnums.LeaseTradeState.PARTREFUND;
        }

        // 尝试变更支付状态为已退款.
        boolean updateTradeFlow = updateState(tradeFlow.getTradeNumber(), ImmutableList.of(preState),
                curState);
        // 如果无法扭转到退款已完成.
        if (!updateTradeFlow) {
            if (Objects.equals(tradeFlow.getState(), curState)) {
                System.out.println(tradeFlow.getTradeNumber()+"{}收到已经退款后收到支付中心的重复回调");
                return;
            } else {
                System.out.println("收到的TradeFlow记录{"+tradeFlow.getTradeNumber()+"}进入一个奇怪的状态{}"+ tradeFlow.getState());
                return;
            }
        }

        // 部分退款
        if (refundType == LeaseStateEnums.RefundType.REFUND_PART) {
//            tradeRefundService.updateState(tradeFlow.getRefundPartTradeNumber(), curLeaseTradeState, preLeaseTradeState);
        }

        TradeFlow tradeFlowNew = tradeFlow.cloneOne();
        tradeFlowNew.setState(curState);
        LeaseRecord leaseRecordOld = leaseRecordService.queryDetail(tradeFlow.getLeaseNumber());
//        boolean updateLease = leaseRecordServi
//        WAssert.isTrue(updateLease, "变更租赁{}状态为预支付／租赁流水退款失败", tradeFlow.getLeaseNumber());

        LeaseRecord leaseRecordNew = leaseRecordOld.cloneOne();
//        leaseRecordNew.setTradeState(curLeaseTradeState);
    }

    @Transactional
    public void refundSuccess(String operator, long tradeNumber, Money refundAmount) {
                // 查找的支付记录 默认查退全款记录
                TradeFlow tradeFlow = queryByTradeNumber(tradeNumber);
                byte refundType = LeaseStateEnums.RefundType.REFUND_FEE;
                // 退预付款
                if (tradeFlow == null) {
                    tradeFlow = queryByRefundPrepayTradeNumber(tradeNumber);
                    refundType = LeaseStateEnums.RefundType.REFUND_PREPAY;
                }
                // 部分退款
                if (tradeFlow == null) {
                    tradeFlow = queryByRefundPartTradeNumber(tradeNumber);
                    refundType = LeaseStateEnums.RefundType.REFUND_PART;
                }
                WAssert.notNull(tradeFlow, "非法的退款回调, 流水ID: {}未找到", tradeNumber);
                refundRecord(tradeFlow, operator, refundType, refundAmount);
    }
    public TradeFlow queryByRefundPrepayTradeNumber(long refundPrepayTradeNumber) {
        TradeFlowExample tradeFlowExample = new TradeFlowExample();
        tradeFlowExample.createCriteria().andRefundPrepayTradeNumberEqualTo(refundPrepayTradeNumber);
        List<TradeFlow> tradeFlows = tradeFlowMapper.selectByExample(tradeFlowExample);
        return tradeFlows.isEmpty() ? null : tradeFlows.get(0);
    }
    public TradeFlow queryByRefundPartTradeNumber(long refundPartTradeNumber) {
        TradeFlowExample tradeFlowExample = new TradeFlowExample();
        tradeFlowExample.createCriteria().andRefundPartTradeNumberEqualTo(refundPartTradeNumber);
        List<TradeFlow> tradeFlows = tradeFlowMapper.selectByExample(tradeFlowExample);
        return tradeFlows.isEmpty() ? null : tradeFlows.get(0);
    }
    public TradeFlow queryNewestInStateForLease(Long leaseNumber, List state) {
        TradeFlowExample tradeFlowExample = new TradeFlowExample();
        tradeFlowExample.createCriteria().andLeaseNumberEqualTo(leaseNumber).andStateIn(state);
        tradeFlowExample.setOrderByClause("create_time desc limit 1");
        List<TradeFlow> tradeFlows = tradeFlowMapper.selectByExample(tradeFlowExample);
        return tradeFlows.stream().findFirst().orElse(null);
    }
}
