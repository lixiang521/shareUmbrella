package com.blibee.umbrella.model.ro;

import com.blibee.umbrella.api.pojo.page.PageRequest;
import lombok.Data;

/**
 * Created by lixiang on 2019/04/27.
 */
@Data
public class OperationUmbrellaCabinetPageReq extends PageRequest {
    private static final long serialVersionUID = 65019492202231189L;
    private String id;
    private String minCap;    //最小雨伞数量
    private String maxCap;    //最大雨伞数量
    private String transStates;   //流转状态
    private String softVers; // 固件版本号
    private String hardVers; // 硬件版本号
    private String scene; // 场景
    private String deviceId; // 设备ID
    private String minCsq; // 最小信号强度
    private String maxCsq; // 最大信号强度
    private String minVbat;// 最小电量
    private String maxVbat;// 最大电量
    private String minPutDate; // 最小投放日期(毫秒数)
    private String maxPutDate; // 最大投放日期(毫秒数)
    private String isOnline;   //是否在线
    private String canLease; //  可借
    private String canReturn; // 可还
    private String city; // 市
    private String commentMessage;
}
