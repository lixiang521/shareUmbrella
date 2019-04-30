package com.pro.umbrella.model.ro;

import lombok.Data;

/**
 * 伞柜列表 es 数据
 *
 * @author longlong.zhang
 * @since 30 十月 2018
 */
@Data
public class OperationCabinetEsResp {
    private String id;
    private String shopCode; //
    private String hardVer; // 硬件版本号
    private String softVer; // 软件版本号
    private String scene; // 场景
    private String sceneType; // 场景
    private String deviceId; // 设备ID
    private String clientId; // 客户ID
    private String clientName;// 客户名称
    private Integer pointId; // 点位ID
    private String pointName; // 点位名称
    private String location; // 位置
    private Integer capacity; // 容量
    private Integer csq; // 信号强度
    private String vbat;// 电量
    private Integer unsortedCapacity; // 排序错误数量
    private Long putDate; // 投放日期(毫秒数)
    private Long migrateDate; // 迁移日期(毫秒数)
    private Integer isOnline;
    private Integer transState;
    private Integer canLease; //  可借
    private Integer canReturn; // 可还
    private String province; // 省
    private String city; // 市
    private String district; // 区

    private String formatPutDate;
    private String formatMigrateDate;
}
