package org.campanula.utils.date;




import org.junit.jupiter.api.Test;

import java.time.LocalDate;
import java.util.Date;

/**
 * @Author: Campanula
 * @Date 2019-12-14
 */
class TestCDateUtil {

    @Test
    void instant2Date() {

    }

    @Test
    void localDateTime2Date() {
    }

    @Test
    void localDate2Date() {
        Date date = CDateUtil.localDate2Date(LocalDate.now());
        System.out.println(date);
    }
}