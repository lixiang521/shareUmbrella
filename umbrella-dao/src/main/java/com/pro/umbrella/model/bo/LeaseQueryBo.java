package com.pro.umbrella.model.bo;

import com.google.common.collect.Range;
import com.pro.umbrella.api.pojo.page.PageRequest;
import lombok.Data;

import java.util.Date;
import java.util.List;

/**
 * Created by longchaochen on 2017/10/20.
 */
@Data
public class LeaseQueryBo extends PageRequest {
    private static final long serialVersionUID = 393617083836354332L;
    private Long uid;
    private String umbrellaNumber;
    private String umbrellaCabinetNumber;
    private List<Byte> leaseStates;
    private List<Byte> tradeStates;
    private Range<Date> createTimeRange;
    private Range<Date> updateTimeRange;
}
