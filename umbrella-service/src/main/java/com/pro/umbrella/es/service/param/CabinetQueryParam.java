package com.pro.umbrella.es.service.param;

import com.pro.umbrella.api.pojo.page.PageRequest;
import lombok.Data;

import java.util.List;

@Data
public class CabinetQueryParam extends PageRequest {
    private String id;
    private Integer minCap;
    private Integer maxCap;
    private List<Integer> transStates;
    private List<String> hardVers; // 硬件版本号
    private List<String> softVers; // 软件版本号
    private List<String> scene; // 场景
    private String deviceId; // 设备ID
    private Integer minCsq; // 最小信号强度
    private Integer maxCsq; // 最大信号强度
    private Double minVbat;// 最小电量
    private Double maxVbat;// 最大电量
    private Long minPutDate; // 最小投放日期(毫秒数)
    private Long maxPutDate; // 最大投放日期(毫秒数)
    private Integer isOnline;
    private Integer canLease; //  可借
    private Integer canReturn; // 可还
    private List<String> city; // 市
}
