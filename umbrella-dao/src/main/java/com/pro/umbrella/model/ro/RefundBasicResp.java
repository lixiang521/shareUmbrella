package com.pro.umbrella.model.ro;

import com.pro.umbrella.api.pojo.BaseBean;
import lombok.Data;

import java.math.BigDecimal;

/**
 * Created by zhiwen.cao on 2017/10/24.
 */
@Data
public class RefundBasicResp extends BaseBean {
    private static final long serialVersionUID = -5215902919558043469L;
    private Long payNo;
    private BigDecimal amount;
}
