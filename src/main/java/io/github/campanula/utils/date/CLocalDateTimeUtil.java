package io.github.campanula.utils.date;

import java.time.*;
import java.util.Date;

import static java.time.format.DateTimeFormatter.ofPattern;

public final class CLocalDateTimeUtil {

    /**
     * Date 转成 LocalDateTime
     * @param date 要转换的Date
     * @return 转换后的LocalDateTime
     */
    public static LocalDateTime date2(Date date) {
        return LocalDateTime.ofInstant(date.toInstant(), getZoneId());
    }

    /**
     * @param instant 要转换的Instant
     * @return 转换后的LocalDateTime
     */
    public static LocalDateTime instant2(Instant instant) {
        return LocalDateTime.ofInstant(instant, getZoneId());
    }

    /**
     * @param localDate 要转换的LocalDate
     * @return 返回转换的当天的零点 00:00
     */
    public static LocalDateTime dayStart(LocalDate localDate) {
        return LocalDateTime.of(localDate, LocalTime.MIN);
    }

    /**
     * @param localDate 要转换的LocalDate
     * @return 返回转换的当天的最后时间 '23:59:59.999999999'
     */
    public static LocalDateTime dayEnd(LocalDate localDate) {
        return LocalDateTime.of(localDate, LocalTime.MAX);
    }

    /**
     * 获取指定日期的凌晨
     * @param localDateTime 指定日期
     * @return 返回转换的当天的零点 00:00
     */
    public static LocalDateTime dayStart(LocalDateTime localDateTime) {
        return LocalDateTime.of(localDateTime.toLocalDate(), LocalTime.MIN);
    }

    /**
     * 获取指定日期的最后时间 '23:59:59.999999999'
     * @param localDateTime 指定日期
     * @return 返回转换的当天的最后时间 '23:59:59.999999999'
     */
    public static LocalDateTime dayEnd(LocalDateTime localDateTime) {
        return LocalDateTime.of(localDateTime.toLocalDate(), LocalTime.MAX);
    }

    /**
     * 获取今天的凌晨
     * @return 返回今天的零点 00:00
     */
    public static LocalDateTime dayStart() {
        return LocalDateTime.of(LocalDate.now(), LocalTime.MIN);
    }

    /**
     * 获取今天的最后时间 '23:59:59.999999999'
     * @return 返回今天的最后时间 '23:59:59.999999999'
     */
    public static LocalDateTime dayEnd() {
        return LocalDateTime.of(LocalDate.now(), LocalTime.MAX);
    }

    /**
     * 获取当前时间 格式为 yyyyMMddHHmmss 的字符串
     * @return 格式化后的日期字符串
     */
    public static String yyyyMMddHHmmss() {
        return LocalDateTime.now().format(ofPattern("yyyyMMddHHmmss"));
    }

    /**
     * 获取当指定时间 格式为 yyyyMMddHHmmss 的字符串
     * @param date 指定的时间
     * @return 格式化后的日期字符串
     */
    public static String yyyyMMddHHmmss(LocalDateTime date) {
        return date.format(ofPattern("yyyyMMddHHmmss"));
    }

    /**
     * 获取当前时间 格式为 yyyy-MM-dd HH:mm:ss 的字符串
     * @return 格式化后的日期字符串
     */
    public static String yyyy_MM_ddHH_mm_ss() {
        return LocalDateTime.now().format(ofPattern("yyyy-MM-dd HH:mm:ss"));
    }

    /**
     * 获取当指定时间 格式为 yyyy-MM-dd HH:mm:ss 的字符串
     * @param date 指定的时间
     * @return 格式化后的日期字符串
     */
    public static String yyyy_MM_ddHH_mm_ss(LocalDateTime date) {
        return date.format(ofPattern("yyyy-MM-dd HH:mm:ss"));
    }

    private static ZoneId getZoneId() {
        return ZoneId.systemDefault();
    }
}
