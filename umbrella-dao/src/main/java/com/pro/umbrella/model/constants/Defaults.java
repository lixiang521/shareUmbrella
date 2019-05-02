package com.pro.umbrella.model.constants;

import java.util.Date;

import com.pro.umbrella.common.util.DateFormatUtils;

/**
 * 默认值
 *
 * @author 谈诚 - cheng.tan
 * @since 18 五月 2018
 */
public interface Defaults {

    String DATE_STRING = "1971-01-01 00:00:00";

    Date DATE = DateFormatUtils.parse4y2M2d2h2m2s(Defaults.DATE_STRING);

    long TIMESTAMP = Defaults.DATE.getTime();

    static boolean isDefault(Date date) {
        return date == null || date.getTime() == TIMESTAMP;
    }
}
