package com.pro.umbrella.common.util;

import java.time.ZoneId;
import java.util.Calendar;
import java.util.Date;
import java.util.List;

import org.apache.commons.lang3.StringUtils;
import org.joda.time.LocalDate;
import org.joda.time.LocalTime;
import org.joda.time.format.DateTimeFormat;
import org.joda.time.format.DateTimeFormatter;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.google.common.collect.ImmutableMap;
import com.google.common.collect.Lists;
import com.pro.umbrella.common.lang.DateFormatUtils;

/**
 * Created by jiaoyang.ma on 2017/3/8.
 */
public class DateUtil {
    private static final Logger logger = LoggerFactory.getLogger(DateUtil.class);

    private static final ImmutableMap<Integer, String> WEEKS = new ImmutableMap.Builder<Integer, String>().put(1, "周一")
            .put(2, "周二").put(3, "周三").put(4, "周四").put(5, "周五").put(6, "周六").put(7, "周日").build();

    public static final DateTimeFormatter DATE_TIME_FORMATTER = DateTimeFormat.forPattern("yyyy-MM-dd HH:mm:ss");
    public static final DateTimeFormatter DATE_FORMATTER = DateTimeFormat.forPattern("yyyy-MM-dd");
    public static final DateTimeFormatter TIME_FORMATTER = DateTimeFormat.forPattern("HH:mm:ss");
    public static final DateTimeFormatter SHORT_DATE_FORMATTER = DateTimeFormat.forPattern("MM/dd");

    public static Date toDateTime(String dateTime) {
        return DATE_TIME_FORMATTER.parseLocalDateTime(dateTime).toDate();
    }

    public static Date toDate(String date) {
        return DATE_FORMATTER.parseLocalDate(date).toDate();
    }

    public static LocalTime toTime(String time) {
        return TIME_FORMATTER.parseLocalTime(time);
    }

    public static String toDateTimeString(Date date) {
        return date == null ? null : DATE_TIME_FORMATTER.print(date.getTime());
    }

    public static String toDateString(Date date) {
        return date == null ? null : DATE_FORMATTER.print(date.getTime());
    }

    public static String toTimeString(Date date) {
        return date == null ? null : TIME_FORMATTER.print(date.getTime());
    }

    public static String toShortDateString(Date date) {
        return date == null ? null : SHORT_DATE_FORMATTER.print(date.getTime());
    }

    public static String toShortDateWeekString(Date date) {
        return date == null ? null
                : (SHORT_DATE_FORMATTER.print(date.getTime()) + " "
                        + WEEKS.get(LocalDate.fromDateFields(date).dayOfWeek().get()));
    }

    public static String toDateWeekString(Date date) {
        return date == null ? null
                : (DATE_FORMATTER.print(date.getTime()) + "("
                        + WEEKS.get(LocalDate.fromDateFields(date).dayOfWeek().get()) + ")");
    }

    /**
     * 订货单日期
     *
     * @param time 时分秒字符串
     * @return 订货单日期字符串
     */
    public static String getTodayOrderTime(String time) {
        return toDateString(getBusinessDate(time));
    }

    /**
     * 根据传入的是分秒 计算订货单对应的业务日期
     *
     * @param time 时分秒字符串
     * @return 订货单日期
     */
    public static Date getBusinessDate(String time) {
        try {
            String pointDate = toDateString(new Date()) + " " + time;
            long pointMilliseconds = toDateTime(pointDate).getTime();
            Date now = new Date();
            long currentMilliseconds = now.getTime();
            if (currentMilliseconds >= pointMilliseconds) {
                return addDate(now, 1);
            } else {
                return now;
            }
        } catch (Exception e) {
            logger.error("传入格式必需是日期类型 HH:mm:ss, 当前参数为:{}", time);
            return null;
        }
    }

    public static Date getOrderTime(String time) {
        return toDate(toDateString(getBusinessDate(time)));
    }

    public static Date getPointTime(String time) {
        try {
            String pointDate = toDateString(new Date()) + " " + time;
            long pointMilliseconds = toDateTime(pointDate).getTime();
            Date now = new Date();
            long currentMilliseconds = now.getTime();
            if (currentMilliseconds >= pointMilliseconds) {
                return addDate(toDateTime(pointDate), 1);
            } else {
                return toDateTime(pointDate);
            }
        } catch (Exception e) {
            logger.error("传入格式必需是日期类型 HH:mm:ss, 当前参数为:{}", time);
            return null;
        }
    }

    /**
     * 获取当日指定时间点
     *
     * @param time 时间字符串
     * @return 当日指定时间点
     */
    public static Date getTodayPointTime(String time) {
        try {
            String pointDate = toDateString(new Date()) + " " + time;
            return toDateTime(pointDate);
        } catch (Exception e) {
            return new Date();
        }
    }

    public static String getWeekOfDate(Date date) {
        String[] weekdaysCode = { "7", "1", "2", "3", "4", "5", "6" };
        Calendar calendar = Calendar.getInstance();
        calendar.setTime(date);
        int intWeek = calendar.get(Calendar.DAY_OF_WEEK) - 1;
        return weekdaysCode[intWeek];
    }

    /**
     * 获取当前是否可订货
     *
     * @param ordDataList 可订货日期
     * @param time 配置的下单截止时间点
     * @return 是否可订货
     */
    public static boolean getNowCanOrder(String ordDataList, String time) {
        Date businessDate = getBusinessDate(time);
        String weekOfDate = getWeekOfDate(businessDate);
        if (StringUtils.isEmpty(ordDataList) || ordDataList.contains(weekOfDate)) {
            return true;
        } else {
            return false;
        }
    }

    public static Date addDate(Date date, int days) {
        Calendar cal = Calendar.getInstance();
        cal.setTime(date);
        cal.add(Calendar.DATE, days);
        return cal.getTime();
    }

    /**
     * 获取当天日期
     *
     * @return 当天日期
     */
    public static Date getCurrentDate() {
        return toDate(toDateString(new Date()));
    }

    /**
     * 获取当天日期
     *
     * @return 当天日期
     */
    public static Date getNextDate() {
        return addDate(toDate(toDateString(new Date())), 1);
    }

    /**
     * 取得两个日期间的所有"日期"，包括开始、结束日期
     *
     * @author phoenixu
     * @param startDate
     * @param endDate
     * @return 日期格式为 yyyy_MM_dd
     */
    public static List<java.time.LocalDate> getDateListOfTwoDate(Date startDate, Date endDate) {
        java.time.LocalDate start = startDate.toInstant().atZone(ZoneId.systemDefault()).toLocalDate();
        java.time.LocalDate end = endDate.toInstant().atZone(ZoneId.systemDefault()).toLocalDate();
        List<java.time.LocalDate> dateList = Lists.newArrayList();
        while (start.compareTo(end) <= 0) {
            dateList.add(start);
            start = start.plusDays(1);
        }
        return dateList;
    }

    /**
     * 获取分钟
     *
     * @param beginDate
     * @param endDate
     * @return
     */
    public static String getMinutes(Date beginDate, Date endDate) {
        long time = endDate.getTime() - beginDate.getTime();
        /** 不满1分钟按1分钟计算 */
        return ((time / 1000 / 60) + (time / 1000 % 60 > 0 ? 1 : 0)) + "";
    }

    /**
     * 特殊天diff - 比较当前时间
     *
     * @param date
     * @return 几天前 or 几小时前
     */
    public static String advancedDiff(Date date) {
        return advancedDiff(date, new Date());
    }

    /**
     * 特殊天diff
     *
     * @return 几天前 or 几小时前
     */
    public static String advancedDiff(Date startDate, Date endDate) {
        int mins = diffMinutes(startDate, endDate);
        int hours = DateFormatUtils.diffHour(startDate, endDate);
        int days = DateFormatUtils.diff(startDate, endDate);

        if (Math.abs(mins) >= 0 && Math.abs(mins) < 60) {
            return Math.abs(mins) + "分钟" + (mins > 0 ? "前" : "后");
        } else if (Math.abs(hours) >= 0 && Math.abs(hours) < 24) {
            return Math.abs(hours) + "小时" + (hours > 0 ? "前" : "后");
        } else {
            return Math.abs(days) + "天" + (days > 0 ? "前" : "后");
        }
    }

    public static int diffMinutes(Date startDate, Date endDate) {
        return (int) Math.ceil((endDate.getTime() - startDate.getTime()) / 60000.0D);
    }

    public static int diffDaysCeil(Date startDate, Date endDate) {
        return (int) Math.ceil((endDate.getTime() - startDate.getTime()) / (3600 * 1000 * 24D));
    }

    public static Date getStartOfDay(Date date) {
        Calendar calendar = Calendar.getInstance();
        calendar.setTime(date);
        calendar.set(Calendar.HOUR_OF_DAY, 0);
        calendar.set(Calendar.MINUTE, 0);
        calendar.set(Calendar.SECOND, 0);
        return calendar.getTime();
    }

    public static Date getEndOfDay(Date date) {
        Calendar calendar = Calendar.getInstance();
        calendar.setTime(date);
        calendar.set(Calendar.HOUR_OF_DAY, 23);
        calendar.set(Calendar.MINUTE, 59);
        calendar.set(Calendar.SECOND, 59);
        return calendar.getTime();
    }

    /**
     * 返回时 分 秒字符串
     * 
     * @param mss
     * @return
     */
    public static String formatDuring(long mss) {
        Integer ss = 1000;
        Integer mi = ss * 60;
        Integer hh = mi * 60;
        Integer dd = hh * 24;

        Long day = mss / dd;
        Long hour = (mss - day * dd) / hh;
        Long minute = (mss - day * dd - hour * hh) / mi;
        Long second = (mss - day * dd - hour * hh - minute * mi) / ss;

        StringBuffer sb = new StringBuffer();
        if (day > 0) {
            sb.append(day + "天");
        }
        if (hour > 0) {
            sb.append(hour + "小时");
        }
        if (minute > 0) {
            sb.append(minute + "分钟");
        }
        if (ss < 1000 * 60 && day <= 0 && hour <= 0 && minute <= 0) {
            sb.append(second + "秒");
        }
        return sb.toString();
    }

    public static void main(String[] args) {
        System.out.println(advancedDiff(toDateTime("2017-07-23 15:10:10")));
        System.out.println(advancedDiff(toDateTime("2017-07-24 15:10:10")));
        System.out.println(advancedDiff(toDateTime("2017-07-24 16:10:10")));

        System.out.println(advancedDiff(toDateTime("2017-07-24 17:10:10")));
        System.out.println(advancedDiff(toDateTime("2017-07-24 18:10:10")));
        System.out.println(advancedDiff(toDateTime("2017-07-25 18:10:10")));
        /*
         * System.out.println(toDateTime("2017-01-01 20:10:10")); System.out.println(toDate("2017-01-01"));
         * System.out.println(toTime("20:10:10")); System.out.println(toDateTimeString(new Date()));
         * System.out.println(toDateString(new Date())); System.out.println(toTimeString(new Date()));
         * System.out.println(toShortDateString(new Date())); System.out.println(toDateString(addDate(new Date(),
         * -10))); System.out.println(toDateTimeString(getBusinessDate("25:59:59")));
         * System.out.println(getCurrentDate()); System.out.println(toDateTimeString(getPointTime("11:59:59"))); Date
         * pointDate = DateUtil.getPointTime("11:59:59"); long remain = pointDate.getTime() - new Date().getTime();
         * System.out.println(remain); System.out.println(toShortDateWeekString(new Date()));
         * System.out.println(toDateWeekString(new Date()));
         */
        System.out.println(getTodayPointTime("12:00:00"));
    }

}
