package io.github.campanula.utils.date;

import java.time.*;
import java.util.Date;

/**
 * @Author: Campanula
 * @Date 2019-12-14
 */
public final class CDateUtil {

    /**
     * Instant to Date
     * @param instant 要转换的Instant
     * @return Date
     */
    public static Date instant2Date(Instant instant) {
        return Date.from(instant);
    }

    /**
     * LocalDateTime to Date
     * @param localDateTime 要转换的LocalDateTime
     * @return Date
     */
    public static Date localDateTime2Date(LocalDateTime localDateTime) {
        ZonedDateTime zonedDateTime = localDateTime.atZone(zoneId());
        return Date.from(zonedDateTime.toInstant());
    }

    /**
     * LocalDate to Date (转换后的时间为当天的00:00:00)
     * @param localDate 要转换的LocalDate
     * @return Date
     */
    public static Date localDate2DateStart(LocalDate localDate) {
        ZonedDateTime zonedDateTime = localDate.atStartOfDay(zoneId());
        return Date.from(zonedDateTime.toInstant());
    }

    /**
     * LocalDate to Date (转换后的时间为当天的23:59:59)
     * @param localDate 要转换的LocalDate
     * @return Date
     */
    public static Date localDate2DateEnd(LocalDate localDate) {
        LocalDateTime localDateTime = LocalDateTime.of(localDate, LocalTime.MAX);
        return localDateTime2Date(localDateTime);
    }

    private static ZoneId zoneId() {
        return ZoneId.systemDefault();
    }
}
