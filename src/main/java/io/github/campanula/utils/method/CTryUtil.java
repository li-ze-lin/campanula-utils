package io.github.campanula.utils.method;

import io.github.campanula.utils.function.VoidMethod;
import io.github.campanula.utils.param.CExceptionHandle;

import java.util.function.Supplier;

/**
 * 用于封装try-catch
 */
public final class CTryUtil {

    /**
     * @param tryMethod 要执行的正常方法
     * @param handle 异常后执行的方法
     */
    public static void handle(VoidMethod tryMethod, CExceptionHandle handle) {
        handle(tryMethod, handle, null);
    }

    /**
     * @param tryMethod 要执行的正常方法
     * @param handle 异常后执行的方法
     * @param finallyMethod finally要执行的方法
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
     * @param <T> 逾期获取的类型
     * @return 活预期的数据
     */
    public static <T> T handle(Supplier<T> tryMethod, CExceptionHandle handle) {
        return handle(tryMethod, handle, null);
    }

    /**
     * @param tryMethod 要执行的正常方法
     * @param handle 异常后执行的方法
     * @param finallyMethod finally要执行的方法
     * @param <T> 逾期获取的类型
     * @return 活预期的数据
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
