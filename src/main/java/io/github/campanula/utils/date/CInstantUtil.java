package io.github.campanula.utils.date;

import java.time.Instant;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.ZoneId;
import java.util.Date;

import static java.time.format.DateTimeFormatter.ofPattern;

public final class CInstantUtil {

    /**
     * @param date 要转换的LocalDateTime
     * @return 转换后的Instant
     */
    public static Instant localDateTime2(LocalDateTime date) {
        return date.atZone(getZoneId()).toInstant();
    }

    /**
     * @param date 要转换的LocalDate
     * @return 转换后的Instant
     */
    public static Instant localDate2(LocalDate date) {
        return date.atStartOfDay(getZoneId()).toInstant();
    }

    private static ZoneId getZoneId() {
        return ZoneId.systemDefault();
    }
    
}
