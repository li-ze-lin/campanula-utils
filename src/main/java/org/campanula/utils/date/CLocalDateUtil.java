package org.campanula.utils.date;

import java.time.*;
import java.util.Date;

import static java.time.format.DateTimeFormatter.ofPattern;

public final class CLocalDateUtil {

    /**
     * Date 转成 LocalDateTime
     * @param date 要转换的Date
     * @return 转换后的LocalDateTime
     */
    public static LocalDate date2(Date date) {
        return LocalDateTime.ofInstant(date.toInstant(), getZoneId()).toLocalDate();
    }

    /**
     * @param instant 要转换的Instant
     * @return 转换后的LocalDateTime
     */
    public static LocalDate instant2(Instant instant) {
        return LocalDateTime.ofInstant(instant, getZoneId()).toLocalDate();
    }

    /**
     * 获取当前时间 格式为 yyyyMMdd 的字符串
     * @return 格式化后的日期字符串
     */
    public static String yyyyMMdd() {
        return LocalDate.now().format(ofPattern("yyyyMMdd"));
    }

    /**
     * 获取当指定时间 格式为 yyyyMMdd 的字符串
     * @param date 指定的时间
     * @return 格式化后的日期字符串
     */
    public static String yyyyMMdd(LocalDate date) {
        return date.format(ofPattern("yyyyMMdd"));
    }

    /**
     * 获取当前时间 格式为 yyyy-MM-dd 的字符串
     * @return 格式化后的日期字符串
     */
    public static String yyyy_MM_dd() {
        return LocalDate.now().format(ofPattern("yyyy-MM-dd"));
    }

    /**
     * 获取当指定时间 格式为 yyyy-MM-dd 的字符串
     * @param date 指定的时间
     * @return 格式化后的日期字符串
     */
    public static String yyyy_MM_dd(LocalDate date) {
        return date.format(ofPattern("yyyy-MM-dd"));
    }

    private static ZoneId getZoneId() {
        return ZoneId.systemDefault();
    }

}
