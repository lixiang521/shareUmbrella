package com.pro.umbrella.model.constants;


/**
 * Created by zhiwen.cao on 2017/10/20.
 */
public interface StateEnums {
    /**
     * 租赁状态
     */
    interface FeedbackState {

        //@Diff("待处理")
        byte NOT_START = 1;

        //@Diff("已拒绝")
        byte REFUSE = 2;

        //@Diff("已退款")
        byte REFUND = 3;
        //@Diff("已处理")
        byte END = 4;
//
    }

    interface WorkOrderState {

        //@Diff("待处理")
        byte NOT_START = 1;

        //@Diff("已处理")
        byte END = 4;
//
    }

    interface WorkOrderPriority {

        //@Diff("低")
        Integer LOW = 1;

        //@Diff("中")
        Integer MIDDLE = 2;

        //@Diff("高")
        Integer HIGH = 3;
//
    }
}
