package com.pro.umbrella.es.controller.param;

import lombok.Data;

@Data
public class LeaseQueryWebParam {
    private int pageSize;
    private int pageNo;
    private String id; // 订单号
    private String umbrellaNumber; // 雨伞编号
    private String startCabinetNumber; // 借出伞柜
    private String endCabinetNumber; // 归还伞柜
    private String uid; // 用户ID
    private String phone; // 电话
    private String leaseState; // 订单状态
    private String tradeState; // 支付状态
    private String transState; // 流转状态
    private String isSameCabinet;
    private String clientId; // 客户ID
    private String minCreateTime; // 下单时间
    private String maxCreateTime; // 下单时间
    private String minReturnTime; // 还伞时间
    private String maxReturnTime; // 还伞时间
    private String startSceneType; // 借出场景类型
    private String startScene; // 借出场景
    private String startPointId; // 借出点位ID
    private String startPointName; // 借出点位名称
    private String startProvince; // 借出省
    private String startCity; // 借出市
    private String startDistrict; // 借出区
    private String startLocation; // 借出位置
    private String endSceneType; // 归还场景类型
    private String endScene; // 归还场景
    private String endPointId;
    private String endPointName;
    private String endProvince;
    private String endCity;
    private String endDistrict;
    private String endLocation;
    private Integer userResource;// 人员所属
    private String duration;
    private String leaseNode;
    private String amount;
    private String entrance;
    private String payType;
}
