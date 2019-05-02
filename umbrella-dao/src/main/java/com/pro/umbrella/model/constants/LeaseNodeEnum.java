package com.pro.umbrella.model.constants;

/**
 * 订单节点enum
 *
 * @author longlong.zhang
 * @since 14 六月 2018
 */
public enum LeaseNodeEnum {

    /**
     * 后端收到押金支付请求
     */
    WXAPP_UMBRELLA_DEPOSIT_PAY_START("wxapp.umbrella.deposit_pay_start", "1.0", "押金支付或免押申请中"),

    /**
     * 达到最大时间仍未支付成功
     */
    WXAPP_UMBRELLA_DEPOSIT_PAY_OVERTIME("wxapp.umbrella.deposit_pay_overtime", "1.1", "押金超时未支付"),

    /**
     * 退出小程序后再次扫码
     */
    WXAPP_UMBRELLA_DEPOSIT_PAY_RESCAN("wxapp.umbrella.deposit_pay_rescan", "1.2", "支付中被结束"),

    /**
     * 押金支付成功
     */
    WXAPP_UMBRELLA_DEPOSIT_PAY_SUCCEED("wxapp.umbrella.deposit_pay_succeed", "2.0", "押金支付或免押成功"),

    /**
     * 达到最大时间仍未开始检查
     */
    WXAPP_UMBRELLA_CHECK_OVERTIME("wxapp.umbrella.check_overtime", "2.1", "出伞超时未开始"),

    /**
     * 看完操作介绍后、取伞前检查
     */
    WXAPP_UMBRELLA_CHECK_FROM_GUIDE("wxapp.umbrella.check_from_guide", "3.0", "出伞前检查"),

    /**
     * 检查时发现伞柜被占用
     */
    WXAPP_UMBRELLA_CHECK_LOCKED("wxapp.umbrella.check_locked", "3.1", "支付后伞柜被占用"),

    /**
     * 检查时发现伞柜不在线
     */
    WXAPP_UMBRELLA_CHECK_OFFLINE("wxapp.umbrella.check_offline", "3.2", "支付后伞柜不在线"),

    /**
     * 伞柜被占用后重试
     */
    WXAPP_UMBRELLA_LOCKED_RETRY("wxapp.umbrella.locked_retry", "3.1->3.0", "伞柜被占用后重试"),

    /**
     * 伞柜不在线后重试
     */
    WXAPP_UMBRELLA_OFFLINE_RETRY("wxapp.umbrella.offline_retry", "3.2->3.0", "伞柜不在线后重试"),

    /**
     * 伞柜被占用后超时未开始
     */
    WXAPP_UMBRELLA_LOCKED_NOT_START_OVERTIME("wxapp.umbrella.locked_not_start_overtime", "3.1.1", "伞柜被占用后超时未操作"),

    /**
     * 伞柜被占用后取消
     */
    WXAPP_UMBRELLA_LOCKED_CANCELLED("wxapp.umbrella.locked_cancelled", "3.1.2", "伞柜被占用后取消"),

    /**
     * 伞柜不在线后取消
     */
    WXAPP_UMBRELLA_OFFLINE_CANCELLED("wxapp.umbrella.offline_cancelled", "3.2.2", "伞柜不在线后取消"),

    /**
     * 后端向伞柜下发出伞指令
     */
    WXAPP_UMBRELLA_UMBRELLA_OUTPUT_START("wxapp.umbrella.umbrella_output_start", "4.0", "出伞中"),

    /**
     * 后端超时未收到伞柜的反馈
     */
    WXAPP_UMBRELLA_UMBRELLA_OUTPUT_OVERTIME("wxapp.umbrella.umbrella_output_overtime", "4.1", "出伞超时无反馈"),

    /**
     * 后端收到伞柜的反馈-失败
     */
    WXAPP_UMBRELLA_UMBRELLA_OUTPUT_FAILED("wxapp.umbrella.umbrella_output_failed", "4.2", "出伞失败"),

    /**
     * 后端收到伞柜的反馈-成功，但检查发现伞未注册
     */
    WXAPP_UMBRELLA_UMBRELLA_OUTPUT_UNREGISTERED("wxapp.umbrella.umbrella_output_unregistered", "4.3",
            "雨伞未注册"),

    /**
     * 出伞超时后重试
     */
    WXAPP_UMBRELLA_UMBRELLA_OUTPUT_OVERTIME_RETRY("wxapp.umbrella.umbrella_output_overtime_retry", "4.1->3.0",
            "出伞超时后重试"),

    /**
     * 出伞失败后重试
     */
    WXAPP_UMBRELLA_UMBRELLA_OUTPUT_FAILED_RETRY("wxapp.umbrella.umbrella_output_failed_retry", "4.2->3.0", "出伞失败后重试"),

    /**
     * 出伞超时后超时未操作
     */
    WXAPP_UMBRELLA_UMBRELLA_OUTPUT_OVERTIME_OVERTIME("wxapp.umbrella.umbrella_output_overtime_overtime", "4.1.1",
            "出伞超时后超时未操作"),

    /**
     * 出伞超时后取消
     */
    WXAPP_UMBRELLA_UMBRELLA_OUTPUT_OVERTIME_CANCELLED("wxapp.umbrella.umbrella_output_overtime_cancelled", "4.1.2",
            "出伞超时后取消"),

    /**
     * 出伞失败后超时未操作
     */
    WXAPP_UMBRELLA_UMBRELLA_OUTPUT_FAILED_OVERTIME("wxapp.umbrella.umbrella_output_failed_overtime", "4.2.1",
            "出伞失败后超时未操作"),

    /**
     * 出伞失败后取消
     */
    WXAPP_UMBRELLA_UMBRELLA_OUTPUT_FAILED_CANCELLED("wxapp.umbrella.umbrella_output_failed_cancelled", "4.2.2",
            "出伞失败后取消"),

    /**
     * 后端收到伞柜的反馈-成功，且检查发现伞已注册
     */
    WXAPP_UMBRELLA_UMBRELLA_OUTPUT_SUCCEED("wxapp.umbrella.umbrella_output_succeed", "5.0", "行程中"),

    /**
     * 计费金额达到押金金额
     */
    WXAPP_UMBRELLA_ORDER_CLOSED_OVERTIME("wxapp.umbrella.order_closed_overtime", "5.1", "超时结束"),

    /**
     * 出伞重复
     */
    WXAPP_UMBRELLA_OUT_DUPLICATE("wxapp.umbrella.out_duplicate", "5.2", "出伞重复"),

    /**
     * 售出结束
     */
    WXAPP_UMBRELLA_ORDER_CLOSED_SELL("wxapp.umbrella.order_closed_sell", "5.3", "售出结束"),

    /**
     * 后台操作减免结束且标记为其他故障
     */
    WXAPP_UMBRELLA_ORDER_CLOSED_ERROR_OTHER("wxapp.umbrella.order_closed_error_other", "5.4", "后台操作减免结束且标记为其他故障"),

    /**
     * 丢失结束
     */
    WXAPP_UMBRELLA_ORDER_CLOSED_DISCARD("wxapp.umbrella.order_closed_discard", "5.5", "丢失结束"),

    /**
     * 后端收到伞柜的还伞成功反馈
     */
    WXAPP_UMBRELLA_ORDER_CLOSED_NORMAL("wxapp.umbrella.order_closed_normal", "6.0", "已结束"),

    /**
     * 后端收到伞柜的还伞成功反馈后，又操作部分退款
     */
    WXAPP_UMBRELLA_ORDER_CLOSED_NORMAL_PART("wxapp.umbrella.order_closed_normal_part", "6.1", "结束后部分退款"),

    /**
     * 后端收到伞柜的还伞成功反馈后，又操作全额退款
     */
    WXAPP_UMBRELLA_ORDER_CLOSED_NORMAL_FREE("wxapp.umbrella.order_closed_normal_free", "6.2", "结束后全额退款"),

    /**
     * 后台操作结束
     */
    WXAPP_UMBRELLA_OPERATE_CLOSED("wxapp.umbrella.operate_closed", "7.0", "后台操作结束"),

    /**
     * 后台操作结束部分退款
     */
    WXAPP_UMBRELLA_OPERATE_CLOSED_PART_REFUND("wxapp.umbrella.operate_closed_part_refund", "7.1", "后台操作结束部分退款"),

    /**
     * 后台操作结束全额退款
     */
    WXAPP_UMBRELLA_OPERATE_CLOSED_ALL_REFUND("wxapp.umbrella.operate_closed_all_refund", "7.2", "后台操作结束全额退款");

    private String name;
    private String code;
    private String paraphrase;

    public String getName() {
        return name;
    }

    public String getCode() {
        return code;
    }

    public String getParaphrase() {
        return paraphrase;
    }

    LeaseNodeEnum(String name, String code, String paraphrase) {
        this.name = name;
        this.code = code;
        this.paraphrase = paraphrase;
    }

    /**
     * 根据代号获取释义
     * 
     * @param code
     * @return
     */
    public static String getParaphraseByCode(String code) {
        for (LeaseNodeEnum value : LeaseNodeEnum.values()) {
            if (code.equals(value.getCode())) {
                return value.getParaphrase();
            }
        }
        return null;
    }

}