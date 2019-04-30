package com.pro.umbrella.es.service.param;

import lombok.Data;

import java.util.List;

@Data
public class CabinetGroupParam {
    private Integer minCap;
    private Integer maxCap;
    private List<Integer> transStates;
    private String id;
    private String shopCode; //
    private List<String> hardVers; // 硬件版本号
    private List<String> softVers; // 软件版本号
    private List<String> sceneType; // 场景大类
    private List<String> scene; // 场景
    private String deviceId; // 设备ID
    private String clientId; // 客户ID
    private String clientName;// 客户名称
    private String pointId; // 点位ID
    private String pointName; // 点位名称
    private String location; // 位置
    private Integer minCsq; // 最小信号强度
    private Integer maxCsq; // 最大信号强度
    private Double minVbat;// 最小电量
    private Double maxVbat;// 最大电量
    private Integer minUnsortedCap; // 最小排序错误数量
    private Integer maxUnsortedCap; // 最大排序错误数量
    private Long minPutDate; // 最小投放日期(毫秒数)
    private Long maxPutDate; // 最大投放日期(毫秒数)
    private Long minMigrateDate; // 最小迁移日期(毫秒数)
    private Long maxMigrateDate; // 最大迁移日期(毫秒数)
    private Integer isOnline;
    private Integer canLease; //  可借
    private Integer canReturn; // 可还
    private String province; // 省
    private String city; // 市
    private String district; // 区
    private List<String> groupField;
}
