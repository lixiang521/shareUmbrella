package com.blibee.umbrella.model.ro;

import lombok.Data;

import java.math.BigDecimal;
import java.util.Date;

import org.springframework.format.annotation.DateTimeFormat;

/**
 * Created by lixiang on 2019/04/27.
 */
@Data
public class OperationUmbrellaCabinetAddReq {
    private String deviceId;    //设备id
    private String umbrellaCabinetNumber;   //伞柜id
    private BigDecimal longitude;   //经度
    private BigDecimal latitude;   //纬度
    private Byte transState;      //流转状态
    private String hardVer;      //硬件版本号
    private String softVer;      //软件版本号
    private String city;        //所在城市
    private String scene;       //所在场景
    private String commentMessage;    // 备注

    @DateTimeFormat(pattern = "yyyy-MM-dd")
    private Date putDate;    //投放日期
}
