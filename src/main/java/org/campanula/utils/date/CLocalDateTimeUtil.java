package org.campanula.utils.date;

import java.time.*;
import java.util.Date;

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

    private static ZoneId getZoneId() {
        return ZoneId.systemDefault();
    }
}
