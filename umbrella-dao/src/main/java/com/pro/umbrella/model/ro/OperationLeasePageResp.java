package com.pro.umbrella.model.ro;

import com.pro.umbrella.api.pojo.BaseBean;
import lombok.Data;

/**
 * Created by longchaochen on 2017/10/25.
 */
@Data
public class OperationLeasePageResp extends BaseBean {
    private static final long serialVersionUID = -2345205818459336596L;
    private String id; // 订单号
    private String umbrellaNumber; // 雨伞编号
    private String startCabinetNumber; // 借出伞柜
    private String endCabinetNumber; // 归还伞柜
    private String uid; // 用户ID
    private String phoneCode; // 电话
    private String phone; // 电话
    private Integer leaseState; // 订单状态
    private Integer tradeState; // 支付状态

    private String createTime; // 下单时间(毫秒)
    private String returnTime; // 还伞时间(毫秒)
    private String startScene; // 借出场景
    private String startCity; // 借出市
    private String endCity;
    private String amount;
    private String leaseNode;
}
