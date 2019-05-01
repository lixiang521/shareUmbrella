package com.pro.umbrella.model.ro;

import com.pro.umbrella.api.pojo.page.PageRequest;
import lombok.Data;
import lombok.Getter;
import lombok.Setter;

import java.util.Date;
import java.util.List;

/**
 * Created by lixiang on 2019/04/27.
 */
@Data
public class OperationUmbrellaCabinetPageReq extends PageRequest {
    private static final long serialVersionUID = 65019492202231189L;
    private String id;
    private Integer minCap;    //最小雨伞数量
    private Integer maxCap;    //最大雨伞数量
    private List<Integer> transStates;   //流转状态
    private List<String>  softVers; // 固件版本号
    private List<String>  hardVers; // 硬件版本号
    private List<String>  scene; // 场景
    private String deviceId; // 设备ID
    private Integer minCsq; // 最小信号强度
    private Integer maxCsq; // 最大信号强度
    private Double minVbat;// 最小电量
    private Double maxVbat;// 最大电量
    private Date minPutDate; // 最小投放日期(毫秒数)
    private Date maxPutDate; // 最大投放日期(毫秒数)
    private Integer isOnline;   //是否在线
    private Integer canLease; //  可借
    private Integer canReturn; // 可还
    private List<String> city; // 市

}
