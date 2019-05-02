package com.pro.umbrella.model.ro;

import com.pro.umbrella.api.pojo.BaseBean;
import lombok.Data;

/**
 * Created by zhiwen.cao on 2017/10/24.
 */
@Data
public class LeaseBasicResp extends BaseBean {
    private static final long serialVersionUID = -5215902919558043469L;
    private String leaseNumber;
    private String umbrellaCabinetNumber;
    private byte cabinetStatus;
    private byte tradeState;
    private String payType;
}
