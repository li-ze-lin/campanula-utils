
package io.github.campanula.utils.method;

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
        String s = CObjectUtil.get(() -> "1", () -> "2");
        Assert.assertEquals(s, "1");
        String s1 = CObjectUtil.get(() -> null, () -> "2");
        Assert.assertEquals(s1, "2");
    }

    @Test
    public void testNullRuntimeThrows() {
        String s = CObjectUtil.getThrows(() -> "1", RuntimeException::new);
        Assert.assertEquals(s, "1");
        thrown.expect(RuntimeException.class);
        CObjectUtil.getThrows(() -> null, RuntimeException::new);
    }

    @Test
    public void testProcess() {
        String process = CObjectUtil.process(() -> "1", (s) -> "2", () -> "3");
        Assert.assertEquals(process, "2");
        process = CObjectUtil.process(() -> null, (s) -> "2", () -> "3");
        Assert.assertEquals(process, "3");
    }

    @Test
    public void testProcessNullRuntimeThrows() {
        String process = CObjectUtil.processThrows(() -> "1", (s) -> "2", RuntimeException::new);
        Assert.assertEquals(process, "2");
        thrown.expect(RuntimeException.class);
        process = CObjectUtil.processThrows(() -> null, (s) -> "2", RuntimeException::new);
    }

    @Test
    public void testConsume() {
        CObjectUtil.consume(() -> "1", System.out::println, () -> System.out.println("2"));
        CObjectUtil.consume(() -> null, System.out::println, () -> System.out.println("2"));
    }

    @Test
    public void  testConsumeNullRuntimeThrows() {
        CObjectUtil.consumeThrows(() -> "1", System.out::println, RuntimeException::new);
        thrown.expect(RuntimeException.class);
        CObjectUtil.consumeThrows(() -> null, System.out::println, RuntimeException::new);
    }

    @Test
    public void testConvert() {
        Integer convert = CObjectUtil.convert(() -> "1", (s) -> Integer.valueOf(2), () -> Integer.valueOf(3));
        Assert.assertEquals(convert, Integer.valueOf(2));
        convert = CObjectUtil.convert(() -> null, (s) -> Integer.valueOf(2), () -> Integer.valueOf(3));
        Assert.assertEquals(convert, Integer.valueOf(3));
    }

    @Test
    public void  testConvertNullRuntimeThrows() {
        Integer integer = CObjectUtil.convertThrows(() -> "1", (s) -> Integer.valueOf(2), RuntimeException::new);
        Assert.assertEquals(integer, Integer.valueOf(2));
        thrown.expect(RuntimeException.class);
        CObjectUtil.convertThrows(() -> null, (s) -> Integer.valueOf(2), RuntimeException::new);
    }

}