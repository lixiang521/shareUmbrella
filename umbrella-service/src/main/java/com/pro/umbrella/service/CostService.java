package com.pro.umbrella.service;

import com.pro.umbrella.api.json.JsonUtil;
import com.pro.umbrella.api.pojo.Currency;
import com.pro.umbrella.api.pojo.Money;
import com.pro.umbrella.common.util.DateFormatUtils;
import com.pro.umbrella.model.bo.ChargeDetail;
import com.pro.umbrella.model.bo.LeaseCost;
import com.pro.umbrella.model.constants.Constants;
import com.pro.umbrella.model.pojo.LeaseRecord;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.math.NumberUtils;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.math.BigDecimal;
import java.util.Date;

/**
 * Created by zhiwen.cao on 2017/10/20.
 */
@Slf4j
@Service
public class CostService {

    private static final int MINUTES_PER_HOUR = 60;
    private static final int HOURS_PER_DAY = 24;
    private static final int MINUTES_PER_DAY = 1440;

    @Resource
    private GlobalConfigService globalConfigBiz;

    @Resource
    private UmbrellaCabinetService umbrellaCabinetService;

    public LeaseCost leaseRecordCost(LeaseRecord leaseRecord, Date tillTime) {
        if (leaseRecord == null) {
            return null;
        }
        LeaseCost leaseCost = new LeaseCost();
        BigDecimal reduceAmount = BigDecimal.ZERO, totalAmount, amount;
        String reduceMsg = "";

        // 如果有有优惠则可计算
        totalAmount = calculate(leaseRecord, tillTime).getAmount();
        amount = totalAmount.subtract(reduceAmount);

        leaseCost.setReduceAmount(reduceAmount);
        leaseCost.setTotalAmount(totalAmount);
        leaseCost.setAmount(amount);
        leaseCost.setReduceMsg(reduceMsg);
        return leaseCost;
    }

    public LeaseCost getLeaseCost(LeaseRecord leaseRecord) {
        LeaseCost leaseCost = new LeaseCost();
        leaseCost.setTotalAmount(leaseRecord.getTotalAmount());
        leaseCost.setReduceAmount(leaseRecord.getReduceAmount());
        leaseCost.setCouponAmount(leaseRecord.getCouponAmount());
        leaseCost.setAmount(leaseRecord.getAmount());
        leaseCost.setReduceMsg("");
        return leaseCost;
    }

    public ChargeDetail getChargeDetail(LeaseRecord leaseRecord) {
        return JsonUtil.of(globalConfigBiz.queryByName(Constants.EXAM_CHARGE).getContext(), ChargeDetail.class);
    }

    /**
     * 计价
     *
     * @param leaseRecord
     * @param tillTime
     * @return
     */
    private Money calculate(LeaseRecord leaseRecord, Date tillTime) {
        ChargeDetail chargeDetail = getChargeDetail(leaseRecord);

        Money totalAmount;
        // 计费周期
        int timePeriod = NumberUtils.createInteger(chargeDetail.getUnitTime());

        Date endTime = tillTime;

        int hour = DateFormatUtils.diffHour(leaseRecord.getStartTime(), endTime);

        int seconds = DateFormatUtils.diffSecond(leaseRecord.getStartTime(), endTime);

        int mins = (int) Math.ceil(seconds / (double) MINUTES_PER_HOUR);

        BigDecimal deposit = NumberUtils.createBigDecimal(chargeDetail.getDeposit());
        // 免费试用时长
        if (mins <= chargeDetail.getFreeTimeMinute()) {
            return Money.zero(Currency.CNY);
        } else {
            mins = mins - chargeDetail.getFreeTimeMinute();
        }

        switch (chargeDetail.getStrategy()) {
            // AB TEST B1 GROUP
            case "B1":
                /**
                 * 2元／半小时，24小时内封顶8元 先计算天数，在计算剩余分钟数 1<=minute<=30 按2元收费
                 */
                BigDecimal dayLimitCharge = NumberUtils.createBigDecimal(chargeDetail.getDayLimitCharge());
                BigDecimal cost = NumberUtils.createBigDecimal(chargeDetail.getCost());

                if (hour / HOURS_PER_DAY >= 1) {
                    totalAmount = Money.of(BigDecimal.valueOf(hour / HOURS_PER_DAY).multiply(dayLimitCharge));
                    if (hour % HOURS_PER_DAY != 0) {
                        mins -= hour / HOURS_PER_DAY * MINUTES_PER_DAY;
                        totalAmount = Money.of(totalAmount
                                .plus(BigDecimal.valueOf(mins % timePeriod == 0 ? mins / timePeriod : mins / timePeriod + 1)
                                        .multiply(cost).min(dayLimitCharge))
                                .getAmount().min(deposit));
                    }
                } else {
                    totalAmount = Money
                            .of(BigDecimal.valueOf((mins % timePeriod) == 0 ? mins / timePeriod : mins / timePeriod + 1)
                                    .multiply(cost).min(dayLimitCharge));
                }
                return totalAmount;
            default:
                // 6元一天
                // BigDecimal的构造器接收double时容易混乱
                totalAmount = Money.of(BigDecimal
                        .valueOf(Math.ceil(hour * 1d / timePeriod) * NumberUtils.createDouble(chargeDetail.getCost()))
                        .setScale(1, BigDecimal.ROUND_DOWN).min(deposit));
                return totalAmount;
        }
    }

}
