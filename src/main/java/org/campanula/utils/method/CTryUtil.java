package org.campanula.utils.method;

import org.campanula.utils.function.VoidMethod;
import org.campanula.utils.param.CExceptionHandle;

import java.util.function.Supplier;

/**
 * 用于封装try-catch
 */
public class CTryUtil {

    /**
     * @param tryMethod 要执行的正常方法
     * @param handle 异常后执行的方法
     * @code handle(() -> System.out.println("try"), CExceptionHandle.Blank.aBlank().add(Exception.class, () -> System.out.println("catch")).getHandle());
     */
    public static void handle(VoidMethod tryMethod, CExceptionHandle handle) {
        handle(tryMethod, handle, null);
    }

    /**
     * @param tryMethod 要执行的正常方法
     * @param handle 异常后执行的方法
     * @param finallyMethod finally要执行的方法
     * @code handle(() -> System.out.println("try"), CExceptionHandle.Blank.aBlank().add(Exception.class, () -> System.out.println("catch")).getHandle(), () -> System.out.println("finally"));
     */
    public static void handle(VoidMethod tryMethod, CExceptionHandle handle, VoidMethod finallyMethod) {
        try {
            tryMethod.method();
        }
        catch (Exception e) {
            handle.handle(e);
        }
        finally {
            if (finallyMethod != null) {
                finallyMethod.method();
            }
        }
    }

    /**
     * @param tryMethod 要执行的正常方法
     * @param handle 异常后执行的方法
     * @code handle(() -> "try", CExceptionHandle.Result.aResult().add(Exception.class, () -> "catch").getHandle());
     */
    public static <T> T handle(Supplier<T> tryMethod, CExceptionHandle handle) {
        return handle(tryMethod, handle, null);
    }

    /**
     * @param tryMethod 要执行的正常方法
     * @param handle 异常后执行的方法
     * @param finallyMethod finally要执行的方法
     * @code handle(() -> "try", CExceptionHandle.Result.aResult().add(Exception.class, () -> "catch").getHandle(), () -> System.out.println("finally"));
     */
    public static <T> T handle(Supplier<T> tryMethod, CExceptionHandle handle, VoidMethod finallyMethod) {
        try {
            return tryMethod.get();
        }
        catch (Exception e) {
            return (T) handle.handle(e);
        }
        finally {
            if (finallyMethod != null) {
                finallyMethod.method();
            }
        }
    }
}
