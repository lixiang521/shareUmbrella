package com.pro.umbrella.model.constants.id;


public interface IDGeneratorSeed {

    //@Diff("租赁编号")
    int LEASE_NUMBER = 1;

    /**
     * 支付金额为0时本地创建流水号
     */
    //@Diff("支付流水号")
    int PAYMENT_SERIAL_NUMBER = 2;

    //@Diff("退款流水号")
    int REFUND = 3;

    //@Diff("寻伞工单号")
    int OP_WORK_ID = 4;
}
