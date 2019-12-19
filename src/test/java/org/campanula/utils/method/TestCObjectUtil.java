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

    @Test
    public void nullRuntimeThrows() {
        thrown.expect(MethodNullException.class);
        CObjectUtil.nullRuntimeThrows(null, RuntimeException::new);
    }

    @Test
    public void nullRuntimeThrows1() {
        thrown.expect(RuntimeException.class);
        CObjectUtil.nullRuntimeThrows(() -> null, RuntimeException::new);
    }

    @Test
    public void nullRuntimeThrows2() {
        String s = "1";
        String s1 = CObjectUtil.nullRuntimeThrows(() -> s, RuntimeException::new);
        Assert.assertEquals(s, s1);
    }

    @Test
    public void nullRuntimeThrows3() {
        thrown.expect(NullPointerException.class);
        CObjectUtil.nullRuntimeThrows(() -> null, () -> null);
    }

    @Test
    public void process() {
        thrown.expect(MethodNullException.class);
        CObjectUtil.process(Object::new, null, Object::new);
    }

    @Test
    public void process1() {
        String process = CObjectUtil.process(() -> "1", (s) -> s.concat("2"), null);
        Assert.assertEquals(process, "12");
    }

    @Test
    public void process2() {
        thrown.expect(MethodNullException.class);
        String process = CObjectUtil.process(() -> null, (s) -> s.concat("2"), null);
    }

    @Test
    public void process3() {
        String process = CObjectUtil.process(() -> null, (s) -> s.concat("2"), () -> "1");
        Assert.assertEquals(process, "1");
    }
}