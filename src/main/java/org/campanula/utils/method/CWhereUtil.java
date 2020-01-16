package org.campanula.utils.method;

import org.campanula.utils.exception.WhereNotPassRuntimeException;
import org.campanula.utils.function.VoidMethod;

import java.util.function.Supplier;

public class CWhereUtil {

    /**
     * 传入一组条件 如果条件全部通过 则执行成功的操作 有一个没有通过则执行失败的操作
     * @param success 成功的操作
     * @param fail 失败的操作
     * @param where 条件
     */
    public static void then(VoidMethod success, VoidMethod fail, Supplier<Boolean> ...where) {
        int length = where.length;

        for (int i = 0; i < length; i++) {
            if (!where[i].get()) {
                fail.method();
                return;
            }
        }

        success.method();
    }

    /**
     * 传入一组条件 如果条件全部通过 则执行成功的操作 有一个没有通过则执行失败的操作 并返回操作的返回值
     * @param success 成功的操作
     * @param fail 失败的操作
     * @param where 条件
     * @return 返回操作的返回值
     */
    public static <T> T then(Supplier<T> success, Supplier<T> fail, Supplier<Boolean> ...where) {
        int length = where.length;

        for (int i = 0; i < length; i++) {
            if (!where[i].get()) {
                return fail.get();
            }
        }

        return success.get();
    }

    /**
     * 传入一组条件 如果条件全部通过 则执行成功的操作 有一个没有通过则抛出WhereNotPassRuntimeException异常
     * @param success 成功的操作
     * @param where 条件
     */
    public static void thenThrow(VoidMethod success, Supplier<Boolean> ...where) {
        thenThrow(success, WhereNotPassRuntimeException::new, where);
    }

    /**
     * 传入一组条件 如果条件全部通过 则执行成功的操作 有一个没有通过则抛出WhereNotPassRuntimeException异常
     * @param success 成功的操作
     * @param failMessage 异常信息
     * @param where 条件
     */
    public static void thenThrow(VoidMethod success, String failMessage, Supplier<Boolean> ...where) {
        thenThrow(success, () -> new WhereNotPassRuntimeException(failMessage), where);
    }

    /**
     * 传入一组条件 如果条件全部通过 则执行成功的操作 有一个没有通过则抛出指定的异常
     * @param success 成功的操作
     * @param ex 指定的异常
     * @param where 条件
     */
    public static <EX extends RuntimeException> void thenThrow(VoidMethod success, Supplier<EX> ex, Supplier<Boolean> ...where) {
        int length = where.length;

        for (int i = 0; i < length; i++) {
            if (!where[i].get()) {
                throw ex.get();
            }
        }

        success.method();
    }

    /**
     * 传入一组条件 如果条件全部通过 则执行成功的操作 并返回 有一个没有通过则抛出WhereNotPassRuntimeException异常
     * @param success 成功的操作
     * @param where 条件
     * @return 返回操作的返回值
     */
    public static <T> T thenThrow(Supplier<T> success, Supplier<Boolean> ...where) {
        return thenThrow(success, WhereNotPassRuntimeException::new, where);
    }

    /**
     * 传入一组条件 如果条件全部通过 则执行成功的操作 并返回 有一个没有通过则抛出WhereNotPassRuntimeException异常
     * @param success 成功的操作
     * @param failMessage 异常信息
     * @param where 条件
     * @return 返回操作的返回值
     */
    public static <T> T thenThrow(Supplier<T> success, String failMessage, Supplier<Boolean> ...where) {
        return thenThrow(success, () -> new WhereNotPassRuntimeException(failMessage), where);
    }

    /**
     * 传入一组条件 如果条件全部通过 则执行成功的操作 并返回 有一个没有通过则抛出指定的异常
     * @param success 成功的操作
     * @param ex 指定的异常
     * @param where 条件
     * @return 返回操作的返回值
     */
    public static <T, EX extends RuntimeException> T thenThrow(Supplier<T> success, Supplier<EX> ex, Supplier<Boolean> ...where) {
        int length = where.length;

        for (int i = 0; i < length; i++) {
            if (!where[i].get()) {
                throw ex.get();
            }
        }

        return success.get();
    }
}
