package org.campanula.utils.date;

import java.time.*;
import java.util.Date;

/**
 * @Author: Campanula
 * @Date 2019-12-14
 */
public class CDateUtil {

    public static Date instant2Date(Instant instant) {
        return Date.from(instant);
    }

    public static Date localDateTime2Date(LocalDateTime localDateTime) {
        ZonedDateTime zonedDateTime = localDateTime.atZone(zoneId());
        return Date.from(zonedDateTime.toInstant());
    }

    public static Date localDate2Date(LocalDate localDate) {
        ZonedDateTime zonedDateTime = localDate.atStartOfDay(zoneId());
        return Date.from(zonedDateTime.toInstant());
    }

    public static Date localDate2DateEnd(LocalDate localDate) {
        LocalDateTime localDateTime = LocalDateTime.of(localDate, LocalTime.MAX);
        return localDateTime2Date(localDateTime);
    }

    private static ZoneId zoneId() {
        return ZoneId.systemDefault();
    }
}
