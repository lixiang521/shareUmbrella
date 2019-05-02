package com.pro.umbrella.model.bo;

import com.pro.umbrella.api.pojo.BaseBean;
import lombok.Data;

/**
 * Created by longlong.zhang on 2018/5/4
 */
@Data
public class ChargeDetail extends BaseBean {
    private static final long serialVersionUID = -7712739987819901289L;

    // 计价策略
    private String strategy;
    // 计费时长单位（hour）
    private String unitTime;
    // 押金
    private String deposit;
    // 免费体验时间 分钟
    private int freeTimeMinute;
    // 价格
    private String cost;
    // 单日封顶价
    private String dayLimitCharge;
}
