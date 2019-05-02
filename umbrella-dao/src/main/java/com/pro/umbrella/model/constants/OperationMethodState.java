package com.pro.umbrella.model.constants;

/**
 * Created by zhiwen.cao on 2017/6/27.
 */
public interface OperationMethodState {

    /**
     * 结束行程
     */
    //("结束行程")
    int END_LEASE_OPERATION = 1;

    /**
     * 可退款指定金额
     */
    //("退款")
    int REFUND_OPERATION = 2;

    /**
     * 所有操作
     */
    //("拒绝")
    int REJECT_OPERATION = 3;
}
