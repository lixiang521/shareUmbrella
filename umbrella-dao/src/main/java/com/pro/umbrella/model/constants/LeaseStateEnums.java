package com.pro.umbrella.model.constants;


/**
 * Created by zhiwen.cao on 2017/10/20.
 */
public interface LeaseStateEnums {
    /**
     * 租赁状态
     */
    interface LeaseState {

        //@Diff("未开始")
        byte NOT_START = 1;

        //@Diff("行程中")
        byte LEASING = 2;

        //@Diff("已结束")
        byte END = 3;

//        //@Diff("租赁失败")
//        byte LEASE_FAIL = 4;
//
//        //@Diff("租赁超时")
//        byte OVER_TIME = 5;
//
//        //@Diff("丢失结束")
//        byte DISCARD_END = 6;
//
//        //@Diff("超时结束")
//        byte OVER_END = 7;
//
//        //@Diff("减免结束")
//        byte REDUCE_END = 8;
//
//        //@Diff("取消租赁")
//        byte CANCEL_END = 9;
//
//        //@Diff("雨伞未注册")
//        byte UMBRELLA_NOT_EXIST = 11;
//
//        //@Diff("废弃")
//        byte DISCARD = 12;
//
//        //@Diff("售出结束")
//        byte SELL_END = 13;

//        static boolean contains(Byte value) {
//            return value != null && value >= NOT_START && value <= UMBRELLA_NOT_EXIST;
//        }
//
//        static boolean endContains(Byte value) {
//            return value != null && (value == END || value >= DISCARD_END && value <= REDUCE_END || SELL_END == value);
//        }
//
//        static boolean leaseStateContains(Byte value) {
//            return LEASING == value || END == value || OVER_END == value || REDUCE_END == value || SELL_END == value;
//        }
//
//        static boolean refundContains(Byte value) {
//            return LEASE_FAIL == value || OVER_TIME == value;
//        }
//
//        static boolean isRefund(Byte value) {
//            return value != null && (value == END || value == OVER_END || value == SELL_END);
//        }
    }

    interface LeaseTradeState {

        /**
         * 租赁初始支付状态，租赁结束才进入支付状态，占位
         */
        //@Diff("待支付")
        byte WAIT_PAY = 0;
//
//        //@Diff("预付中")
//        byte PREPAYING = 1;
//
//        // 部分退款中
//        //@Diff("支付中")
//        byte PAYING = 3;

        // 部分退款结束
        //@Diff("已支付")
        byte PAYED = 4;

//        //@Diff("全额退款中")
//        byte REFUNDING = 5;
//
//        //@Diff("全额退款完成")
//        byte REFUND = 6;
//
//        //@Diff("部分退款中")
//        byte PARTREFUNDING = 8;
//
        //@Diff("部分退款完成")
        byte PARTREFUND = 9;
//
//        //@Diff("预授权免押申请")
//        byte AUTH_FREE = 10;
//
//        //@Diff("预授权免押申请成功")
//        byte AUTH_FREE_SUCC = 11;
//
//        //@Diff("预授权免押解冻")
//        byte AUTH_UNFREEZE = 12;
//
//        //@Diff("阿里授权模式下临时状态（支付失败）")
//        byte ALI_AUTH_PAY_ERROR = 13;
//
//        static boolean canRefund(Byte state) {
//            return state != null && (PAYED == state || PARTREFUND == state);
//        }
//
//        static boolean contains(Byte value) {
//            return value != null && value >= WAIT_PAY && value <= PARTREFUND;
//        }
//
//        static boolean prePayOrAuth(Byte value) {
//            return value != null && ( value == AUTH_FREE_SUCC);
//        }
//
//        static boolean prePayingOrAuthApply(Byte value) {
//            return value != null && (value == PREPAYING || value == AUTH_FREE);
//        }

    }

    interface TradeFlowState {

        //@Diff("待支付")
        byte WAIT_PAY = 0;

        //@Diff("预付中")
        byte PREPAYING = 1;

        // 部分退款中
        //@Diff("支付中")
        byte PAYING = 3;

        // 部分退款结束
        //@Diff("已支付")
        byte PAYED = 4;

        //@Diff("全额退款中")
        byte REFUNDING = 5;

        //@Diff("全额退款完成")
        byte REFUND = 6;

        //@Diff("订单关闭")
        byte CLOSED = 7;

        //@Diff("部分退款中")
        byte PARTREFUNDING = 8;

        //@Diff("部分退款完成")
        byte PARTREFUND = 9;

        //@Diff("预授权免押")
        byte AUTH_FREE = 10;

        //@Diff("预授权免押申请成功")
        byte AUTH_FREE_SUCC = 11;

        //@Diff("预授权免押解冻")
        byte AUTH_UNFREEZE = 12;

        //@Diff("阿里授权模式下临时状态（支付失败）")
        byte ALI_AUTH_PAY_ERROR = 13;
        
        static boolean prePayOrPay(Byte state) {
            return state != null && (state == PAYED);
        }
    }

    interface UnitConstants {
        String AMOUNT_UNIT = "元";
        String TIME_UNIT = "分钟";
    }

    interface RefundType {
        byte REFUND_FEE = 0; // 退租借费
        byte REFUND_PREPAY = 1; // 退预付款
        byte REFUND_ALL = 2; // 全额退款，发生在异常减免或者openFail
        byte REFUND_PART = 3; // 部分退款
    }

    interface AbnormalTradeState {

        // 待支付
        byte WAIT_PAY = 0;

        // 重试支付
        byte RETRY_PAY = 1;

        // 支付失败
        byte PAY_ERROR = 2;

        // 支付成功
        byte PAY_SUCC = 3;
    }

}
