package com.pro.umbrella.es.service.bean;

import lombok.Data;

@Data
public class EsCabinet {
    private String id;
    private String hardVer; // 硬件版本号
    private String softVer; // 软件版本号
    private String scene; // 场景
    private String deviceId; // 设备ID
    private Integer capacity; // 容量
    private Integer csq; // 信号强度
    private Double vbat;// 电量
    private Long putDate; // 投放日期(毫秒数)
    private Integer isOnline;
    private Integer transState;
    private Integer canLease; //  可借
    private Integer canReturn; // 可还
    private String city; // 市
}
