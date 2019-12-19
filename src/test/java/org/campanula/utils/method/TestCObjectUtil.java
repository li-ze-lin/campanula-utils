package org.campanula.utils.method;

import org.campanula.utils.exception.MethodNullException;
import org.junit.Assert;
import org.junit.Rule;
import org.junit.Test;
import org.junit.rules.ExpectedException;


/**
 * @Author: Campanula
 * @Date 2019-12-15
 */
public class TestCObjectUtil {

    @Rule
    public ExpectedException thrown = ExpectedException.none();

    @Test
    public void testGet() {
        thrown.expect(MethodNullException.class);
        CObjectUtil.get(null, Object::new);
    }

    @Test
    public void testGet1() {
        String s = "1";
        String s1 = CObjectUtil.get(() -> s, null);
        Assert.assertEquals(s, s1);
    }

    @Test
    public void testGet2() {
        thrown.expect(MethodNullException.class);
        CObjectUtil.get(() -> null, null);
    }

    @Test
    public void testGet3() {
        String s = "1";
        String s1 = CObjectUtil.get(() -> null, () -> s);
        Assert.assertEquals(s, s1);
    }
}