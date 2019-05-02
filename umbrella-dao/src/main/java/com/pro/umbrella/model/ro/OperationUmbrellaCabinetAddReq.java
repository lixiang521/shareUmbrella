package com.pro.umbrella.model.ro;

import com.pro.umbrella.api.pojo.BaseBean;
import lombok.Data;
import org.springframework.format.annotation.DateTimeFormat;

import java.math.BigDecimal;
import java.util.Date;

/**
 * Created by longchaochen on 2017/10/25.
 */
@Data
public class OperationUmbrellaCabinetAddReq extends BaseBean {
    private static final long serialVersionUID = -4931027097127775818L;

    private String deviceId;
    private String umbrellaCabinetNumber;
    private BigDecimal longitude;
    private BigDecimal latitude;
    private Byte transState;
    private String hardVer;
    private String softVer;
    private String city;
    private String scene;
    // 备注
    private String commentMessage;

    @DateTimeFormat(pattern = "yyyy-MM-dd")
    private Date putDate;
}
