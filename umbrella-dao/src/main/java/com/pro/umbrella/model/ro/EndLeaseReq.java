package com.pro.umbrella.model.ro;

import com.pro.umbrella.api.pojo.BaseBean;
import lombok.Data;

/**
 * Created by longlong.zhang on 2018/3/29
 */
@Data
public class EndLeaseReq extends BaseBean {
    private static final long serialVersionUID = -3372262085484872448L;
    private Long leaseNumber;
    private Long uid;
    private String cabinetId;
}
