package com.pro.umbrella.es.controller.param;

import lombok.Data;

@Data
public class CabinetQueryWebParam {
    private int pageSize;
    private int pageNo;
    private String minCap;
    private String maxCap;
    private String transStates;
    private String id;
    private String shopCode; //
    private String hardVers; // 硬件版本号
    private String softVers; // 软件版本号
    private String sceneType; // 场景
    private String scene; // 场景
    private String deviceId; // 设备ID
    private String clientId; // 客户ID
    private String clientName;// 客户名称
    private String pointId; // 点位ID
    private String pointName; // 点位名称
    private String location; // 位置
    private String minCsq; // 最小信号强度
    private String maxCsq; // 最大信号强度
    private String minVbat;// 最小电量
    private String maxVbat;// 最大电量
    private String minUnsortedCap; // 最小排序错误数量
    private String maxUnsortedCap; // 最大排序错误数量
    private String minPutDate; // 最小投放日期(毫秒数)
    private String maxPutDate; // 最大投放日期(毫秒数)
    private String minMigrateDate; // 最小迁移日期(毫秒数)
    private String maxMigrateDate; // 最大迁移日期(毫秒数)
    private String isOnline;
    private String canLease; //  可借
    private String canReturn; // 可还
    private String province; // 省
    private String city; // 市
    private String district; // 区
}
