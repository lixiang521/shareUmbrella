package com.pro.umbrella.model.ro;

import com.pro.umbrella.api.pojo.BaseBean;
import lombok.Data;

/**
 * Created by zhiwen.cao on 2017/4/24.
 */
@Data
public class OperationRefundUpdateReq extends BaseBean {
    private static final long serialVersionUID = 7908183098426569697L;

    private String leaseNumber;
    private Integer status;
    private Double refundAmount;
}
