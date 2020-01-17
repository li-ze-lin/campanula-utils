package org.campanula.utils.method;

import org.campanula.utils.exception.WhereNotPassRuntimeException;
import org.campanula.utils.function.VoidMethod;

import java.util.function.BooleanSupplier;
import java.util.function.Supplier;

public final class CWhereUtil {

    /**
     * 传入一组条件 如果条件全部通过 则执行成功的操作 有一个没有通过则执行失败的操作
     * @param success 成功的操作
     * @param fail 失败的操作
     * @param where 条件
     */
    public static void then(VoidMethod success, VoidMethod fail, BooleanSupplier ...where) {
        int length = where.length;

        for (int i = 0; i < length; i++) {
            if (!where[i].getAsBoolean()) {
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
    public static <T> T then(Supplier<T> success, Supplier<T> fail, BooleanSupplier ...where) {
        int length = where.length;

        for (int i = 0; i < length; i++) {
            if (!where[i].getAsBoolean()) {
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
    public static void thenThrow(VoidMethod success, BooleanSupplier ...where) {
        thenThrow(success, new WhereNotPassRuntimeException(), where);
    }

    /**
     * 传入一组条件 如果条件全部通过 则执行成功的操作 有一个没有通过则抛出WhereNotPassRuntimeException异常
     * @param success 成功的操作
     * @param failMessage 异常信息
     * @param where 条件
     */
    public static void thenThrow(VoidMethod success, String failMessage, BooleanSupplier...where) {
        thenThrow(success, new WhereNotPassRuntimeException(failMessage), where);
    }

    /**
     * 传入一组条件 如果条件全部通过 则执行成功的操作 有一个没有通过则抛出指定的异常
     * @param success 成功的操作
     * @param ex 指定的异常
     * @param where 条件
     */
    public static <EX extends RuntimeException> void thenThrow(VoidMethod success, EX ex, BooleanSupplier ...where) {
        int length = where.length;

        for (int i = 0; i < length; i++) {
            if (!where[i].getAsBoolean()) {
                throw ex;
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
    public static <T> T thenThrow(Supplier<T> success, BooleanSupplier ...where) {
        return thenThrow(success, new WhereNotPassRuntimeException(), where);
    }

    /**
     * 传入一组条件 如果条件全部通过 则执行成功的操作 并返回 有一个没有通过则抛出WhereNotPassRuntimeException异常
     * @param success 成功的操作
     * @param failMessage 异常信息
     * @param where 条件
     * @return 返回操作的返回值
     */
    public static <T> T thenThrow(Supplier<T> success, String failMessage, BooleanSupplier ...where) {
        return thenThrow(success, new WhereNotPassRuntimeException(failMessage), where);
    }

    /**
     * 传入一组条件 如果条件全部通过 则执行成功的操作 并返回 有一个没有通过则抛出指定的异常
     * @param success 成功的操作
     * @param ex 指定的异常
     * @param where 条件
     * @return 返回操作的返回值
     */
    public static <T, EX extends RuntimeException> T thenThrow(Supplier<T> success, EX ex, BooleanSupplier ...where) {
        int length = where.length;

        for (int i = 0; i < length; i++) {
            if (!where[i].getAsBoolean()) {
                throw ex;
            }
        }

        return success.get();
    }
}
