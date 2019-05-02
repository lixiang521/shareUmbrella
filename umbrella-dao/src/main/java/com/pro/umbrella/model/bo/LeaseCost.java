package com.pro.umbrella.model.bo;

import com.pro.umbrella.api.pojo.BaseBean;
import com.pro.umbrella.model.constants.LeaseStateEnums;
import lombok.Data;

import java.math.BigDecimal;

/**
 * Created by zhiwen.cao on 2017/10/20.
 */
@Data
public class LeaseCost extends BaseBean {
    private static final long serialVersionUID = -5955101339181360680L;
    /** 实际花费金额 **/
    private BigDecimal amount;
    private String amountUnit = LeaseStateEnums.UnitConstants.AMOUNT_UNIT;
    /** 优惠 */
    private BigDecimal reduceAmount;
    /** 总金额 */
    private BigDecimal totalAmount;
    /**
     * 优惠券金额
     */
    private BigDecimal couponAmount;
    /** 优惠文案 */
    private String reduceMsg;
}
