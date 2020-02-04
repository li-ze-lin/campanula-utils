package io.github.campanula.utils.method;

import io.github.campanula.utils.param.CExceptionHandle;
import org.junit.Assert;
import org.junit.Rule;
import org.junit.Test;
import org.junit.rules.ExpectedException;

public class TestCTryUtil {

    @Rule
    public ExpectedException thrown = ExpectedException.none();

    @Test
    public void handle() {
        CTryUtil.handle(() -> System.out.println("try"), CExceptionHandle.Blank.aBlank().add(Exception.class, e -> System.out.println("catch")).getHandle(), () -> System.out.println("finally"));
    }

    @Test
    public void handle1() {
        CTryUtil.handle(() ->  1/0,
                CExceptionHandle.Blank.aBlank().add(Exception.class, e -> System.out.println("catch")).getHandle(),
                () -> System.out.println("finally"));
    }

    @Test
    public void handle2() {
        CTryUtil.handle(() ->  1/0,
                CExceptionHandle.Blank.aBlank().add(NullPointerException.class, e -> System.out.println("NullPointerException")).add(Exception.class, e -> System.out.println("Exception")).getHandle(),
                () -> System.out.println("finally"));
    }

    @Test
    public void handle3() {
        thrown.expect(RuntimeException.class);
        CTryUtil.handle(() ->  1/0,
                CExceptionHandle.Blank.aBlank().add(NullPointerException.class, e -> System.out.println("catch")).getHandle(),
                () -> System.out.println("finally"));
    }

    @Test
    public void handle4() {
        CTryUtil.handle(() -> System.out.println("try"), CExceptionHandle.Blank.aBlank().add(Exception.class, e -> System.out.println("catch")).getHandle(), null);
    }

    @Test
    public void handle5() {
        String s = CTryUtil.handle(() -> "try", CExceptionHandle.Result.aResult().add(Exception.class, e -> "catch").getHandle(), () -> System.out.println("finally"));
        Assert.assertEquals(s, "try");
    }

    @Test
    public void handle6() {
        String s = CTryUtil.handle(() -> "try", CExceptionHandle.Result.aResult().add(Exception.class, e -> "catch").getHandle(), null);
        Assert.assertEquals(s, "try");
    }

    @Test
    public void handle7() {
        String s = CTryUtil.handle(() -> { int i = 1/0; return "try";}, CExceptionHandle.Result.aResult().add(Exception.class, e -> "catch").getHandle(), () -> System.out.println("finally"));
        Assert.assertEquals(s, "catch");
    }

    @Test
    public void handle8() {
        String s = CTryUtil.handle(() -> { int i = 1/0; return "try";}, CExceptionHandle.Result.aResult().add(NullPointerException.class, e -> "NullPointerException").add(Exception.class, e -> "catch").getHandle(), () -> System.out.println("finally"));
        Assert.assertEquals(s, "catch");
    }

    @Test
    public void handle9() {
        String s = CTryUtil.handle(() -> { int i = 1/0; return "try";}, CExceptionHandle.Result.aResult().add(ArithmeticException.class, e -> "ArithmeticException").add(Exception.class, e -> "catch").getHandle(), () -> System.out.println("finally"));
        Assert.assertEquals(s, "ArithmeticException");
    }

    @Test
    public void handle10() {
        Assert.assertThrows(RuntimeException.class, () -> CTryUtil.handle(() -> { int i = 1/0; return "try";}, CExceptionHandle.Result.aResult().add(NullPointerException.class, e -> "ArithmeticException").getHandle(), () -> System.out.println("finally")));
    }

}
