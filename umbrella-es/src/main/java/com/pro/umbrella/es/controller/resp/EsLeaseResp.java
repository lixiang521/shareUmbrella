package com.pro.umbrella.es.controller.resp;

import lombok.Data;

@Data
public class EsLeaseResp {
    private Long id; // 订单号
    private String umbrellaNumber; // 雨伞编号
    private String startCabinetNumber; // 借出伞柜
    private String endCabinetNumber; // 归还伞柜
    private Long uid; // 用户ID
    private String phone; // 电话
    private Integer leaseState; // 订单状态
    private Integer tradeState; // 支付状态
    private Integer transState; // 流转状态
    private Integer isSameCabinet;

    private Long createTime; // 下单时间(毫秒)
    private Long returnTime; // 还伞时间(毫秒)
    private String startScene; // 借出场景
    private String startSceneType;
    private Integer startPointId; // 借出点位ID
    private String startPointName; // 借出点位名称
    private String startProvince; // 借出省
    private String startCity; // 借出市
    private String startDistrict; // 借出区
    private String startLocation; // 借出位置
    private String startClientId; // 客户ID
    private String endSceneType;
    private String endScene; // 归还场景
    private Integer endPointId;
    private String endPointName;
    private String endProvince;
    private String endCity;
    private String endDistrict;
    private String endLocation;
    private String endClientId; // 客户ID
    private Integer userResource;// 人员所属
    private int duration;
    private String leaseNode; //订单节点
    private String amount;
    private String entrance;
    private String payType;
}
