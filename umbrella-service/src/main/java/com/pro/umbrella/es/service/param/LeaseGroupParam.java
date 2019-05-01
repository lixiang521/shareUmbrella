package com.pro.umbrella.es.service.param;

import lombok.Data;

import java.util.List;

@Data
public class LeaseGroupParam {
    private String id; // 订单号
    private String umbrellaNumber; // 雨伞编号
    private String startCabinetNumber; // 借出伞柜
    private String endCabinetNumber; // 归还伞柜
    private String uid; // 用户ID
    private String phone; // 电话
    private List<Integer> leaseState; // 订单状态
    private List<Integer> tradeState; // 支付状态
    private List<Integer> transState; // 流转状态
    private Integer isSameCabinet;
    private Integer clientId; // 客户ID
    private Long minCreateTime; // 下单时间
    private Long maxCreateTime; // 下单时间
    private Long minReturnTime; // 还伞时间
    private Long maxReturnTime; // 还伞时间
    private List<String> startSceneType; // 借出场景
    private List<String> startScene; // 借出场景
    private Integer startPointId; // 借出点位ID
    private String startPointName; // 借出点位名称
    private String startProvince; // 借出省
    private List<String> startCity; // 借出市
    private String startDistrict; // 借出区
    private String startLocation; // 借出位置
    private List<String> endSceneType; // 归还场景
    private List<String> endScene; // 归还场景
    private String endPointId;
    private String endPointName;
    private String endProvince;
    private List<String> endCity;
    private String endDistrict;
    private String endLocation;
    private Integer userResource;// 人员所属
    private Integer duration;
    private List<String> groupField;
}
