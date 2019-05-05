package com.pro.umbrella.model.ro;

import com.pro.umbrella.api.pojo.BaseBean;

import com.pro.umbrella.model.bo.LeaseCost;
import lombok.Data;

/**
 * Created by zhiwen.cao on 2017/10/20.
 */
@Data
public class LeaseDetailResp extends BaseBean {
    private static final long serialVersionUID = -3078259430883263839L;
    private String umbrellaNumber;

    /**
     * 骑行时长（格式化）
     */
    private String leaseTime;
    private String leaseTimeUnit;

    private LeaseCost leaseCost;
    private int leaseState;
    private int tradeState;
    private String startTime;
    private String cabinetLendNumber;
    private String endTime;
}
