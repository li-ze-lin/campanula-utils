package io.github.campanula.utils.method;

import io.github.campanula.utils.exception.ObjectEmptyRuntimeException;
import io.github.campanula.utils.function.VoidMethod;

import java.util.List;
import java.util.function.Consumer;
import java.util.function.Function;
import java.util.function.Supplier;

/**
 * Author Campanula
 * Date 2019-12-14
 */
public final class CListUtil {

    /**
     * 获取方法返回的对象 如果为空 则返回定义的默认值
     * @param original 返回的对象方法
     * @param nullOr   当获取对象为空时返回的默认值
     * @param <T> 获取的值类型
     * @return 返回对象 如果获取的和默认值都为空 则会返回空
     */
    public static <T> List<T> get(Supplier<List<T>> original, Supplier<List<T>> nullOr) {
        List<T> t = original.get();
        if (t == null || t.isEmpty()) return nullOr.get();
        return t;
    }

    /**
     * 获取方法返回的对象 如果为空 则会抛出ObjectEmptyRuntimeException
     * @param original 返回的对象方法
     * @param <T> 获取的值类型
     * @return 返回对象
     * @throws ObjectEmptyRuntimeException 预期的异常类型
     */
    public static <T> List<T> getThrows(Supplier<List<T>> original) {
        return getThrows(original, ObjectEmptyRuntimeException::new);
    }

    /**
     * 获取方法返回的对象 如果为空 则会抛出ObjectEmptyRuntimeException
     * @param original         返回的对象方法
     * @param exceptionMessage 异常的消息
     * @param <T> 获取的值类型
     * @throws ObjectEmptyRuntimeException 预期的异常类型
     * @return 获取集合
     */
    public static <T> List<T> getThrows(Supplier<List<T>> original, final String exceptionMessage) {
        return getThrows(original, () -> new ObjectEmptyRuntimeException(exceptionMessage));
    }

    /**
     * 获取方法返回的对象 如果为空 则会抛出指定RuntimeException子类异常
     * @param original 返回的对象方法
     * @param e        要抛出的指定RuntimeException子类异常
     * @param <T> 获取的值类型
     * @param <EX> 异常类型
     * @return 返回对象
     * @throws RuntimeException 预期的异常类型
     */
    public static <T, EX extends RuntimeException> List<T> getThrows(Supplier<List<T>> original, Supplier<EX> e) {
        List<T> t = original.get();
        if (t == null || t.isEmpty()) throw e.get();
        return t;
    }

    /**
     * 加工获取的对象 对象不为空时执行不为空的方法 为空时执行为空的方法
     * @param original 获取对象的方法
     * @param oNotNull 对象不为空执行的方法
     * @param oNull    对象为空执行的方法
     * @param <T> 获取的值类型
     * @return 返回加工后的对象 如果如果加工方法为空则返回空
     */
    public static <T> List<T> process(Supplier<List<T>> original, Function<List<T>, List<T>> oNotNull, Supplier<List<T>> oNull) {
        List<T> t = original.get();
        if (t == null || t.isEmpty()) return oNull.get();
        return oNotNull.apply(t);
    }

    /**
     * 加工获取的对象 对象不为空时执行不为空的方法 为空时抛出EmptyRuntimeException
     * @param original 获取对象的方法
     * @param oNotNull 对象不为空执行的方法
     * @param <T> 获取的值类型
     * @return 返回加工后的对象
     * @throws ObjectEmptyRuntimeException 预期的异常类型
     */
    public static <T> List<T> processThrows(Supplier<List<T>> original, Function<List<T>, List<T>> oNotNull) {
        return processThrows(original, oNotNull, ObjectEmptyRuntimeException::new);
    }

    /**
     * 加工获取的对象 对象不为空时执行不为空的方法 为空时抛出EmptyRuntimeException
     * @param original 获取对象的方法
     * @param oNotNull 对象不为空执行的方法
     * @param exceptionMessage 异常信息
     * @param <T> 获取的值类型
     * @return 返回加工后的对象
     * @throws ObjectEmptyRuntimeException 预期的异常类型
     */
    public static <T> List<T> processThrows(Supplier<List<T>> original, Function<List<T>, List<T>> oNotNull, final String exceptionMessage) {
        return processThrows(original, oNotNull, () -> new ObjectEmptyRuntimeException(exceptionMessage));
    }

    /**
     * 获取对象 对象不为空时执行不为空的方法 为空时抛出RuntimeException子类
     * @param original 获取对象的方法
     * @param oNotNull 对象不为空执行的方法
     * @param e        要抛出的指定RuntimeException子类异常
     * @param <T> 获取的值类型
     * @param <EX> 异常类型
     * @return 返回处理后的结果
     * @throws RuntimeException 预期的异常类型
     */
    public static <T, EX extends RuntimeException> List<T> processThrows(Supplier<List<T>> original, Function<List<T>, List<T>> oNotNull, Supplier<EX> e) {
        List<T> t = original.get();
        if (t == null || t.isEmpty()) throw e.get();
        return oNotNull.apply(t);
    }

    /**
     * 获取对象 对象不为空时执行不为空的方法 为空时执行为空的方法
     * @param original 获取对象的方法
     * @param oNotNull 对象不为空执行的方法
     * @param oNull    对象为空执行的方法
     * @param <T> 获取的值类型
     */
    public static <T> void consume(Supplier<List<T>> original, Consumer<List<T>> oNotNull, VoidMethod oNull) {
        List<T> t = original.get();
        if (t == null || t.isEmpty()) oNull.method();
        else oNotNull.accept(t);
    }

    /**
     * 获取对象 对象不为空时执行不为空的方法 为空时抛出EmptyRuntimeException
     * @param original 获取对象的方法
     * @param oNotNull 对象不为空执行的方法
     * @param <T> 处理数据类型
     * @throws ObjectEmptyRuntimeException 预期的异常类型
     */
    public static <T> void consumeThrows(Supplier<List<T>> original, Consumer<List<T>> oNotNull) {
        consumeThrows(original, oNotNull, ObjectEmptyRuntimeException::new);
    }

    /**
     * 获取对象 对象不为空时执行不为空的方法 为空时抛出EmptyRuntimeException
     * @param original         获取对象的方法
     * @param oNotNull         对象不为空执行的方法
     * @param exceptionMessage 异常的消息
     * @param <T> 处理数据类型
     * @throws ObjectEmptyRuntimeException 预期的异常类型
     */
    public static <T> void consumeThrows(Supplier<List<T>> original, Consumer<List<T>> oNotNull, final String exceptionMessage) {
        consumeThrows(original, oNotNull, () -> new ObjectEmptyRuntimeException(exceptionMessage));
    }

    /**
     * 获取对象 对象不为空时执行不为空的方法 为空时抛出RuntimeException子类
     * @param original 获取对象的方法
     * @param oNotNull 对象不为空执行的方法
     * @param e        要抛出的指定RuntimeException子类异常
     * @param <T> 处理数据类型
     * @param <EX> 期望抛出的异常类型
     * @throws RuntimeException 预期的异常类型
     */
    public static <T, EX extends RuntimeException> void consumeThrows(Supplier<List<T>> original, Consumer<List<T>> oNotNull, Supplier<EX> e) {
        List<T> t = original.get();
        if (t == null || t.isEmpty()) throw e.get();
        else oNotNull.accept(t);
    }

    /**
     * 获取对象并转换 对象不为空时执行不为空的方法 为空时执行为空的方法
     * @param original 获取对象的方法
     * @param oNotNull 对象不为空执行的方法
     * @param oNull    对象为空执行的方法
     * @param <T> 原始数据类型
     * @param <R> 处理后的数据类型
     * @return 返回转换后的对象
     */
    public static <T, R> List<R> convert(Supplier<List<T>> original, Function<List<T>, List<R>> oNotNull, Supplier<List<R>> oNull) {
        List<T> t = original.get();
        if (t == null || t.isEmpty()) return oNull.get();
        return oNotNull.apply(t);
    }

    /**
     * 获取对象并转换 对象不为空时执行不为空的方法 为空时抛出ObjectEmptyRuntimeException
     * @param original 获取对象的方法
     * @param oNotNull 对象不为空执行的方法
     * @param <T> 原始数据类型
     * @param <R> 处理后的数据类型
     * @return 返回转换后的对象
     * @throws ObjectEmptyRuntimeException 预期的异常类型
     */
    public static <T, R> List<R> convertThrows(Supplier<List<T>> original, Function<List<T>, List<R>> oNotNull) {
        return convertThrows(original, oNotNull, ObjectEmptyRuntimeException::new);
    }

    /**
     * 获取对象并转换 对象不为空时执行不为空的方法 为空时抛出ObjectEmptyRuntimeException
     * @param original 获取对象的方法
     * @param oNotNull 对象不为空执行的方法
     * @param exceptionMessage 期望的异常信息
     * @param <T> 原始数据类型
     * @param <R> 处理后的数据类型
     * @return 返回转换后的对象
     * @throws ObjectEmptyRuntimeException 预期的异常类型
     */
    public static <T, R> List<R> convertThrows(Supplier<List<T>> original, Function<List<T>, List<R>> oNotNull, final String exceptionMessage) {
        return convertThrows(original, oNotNull, () -> new ObjectEmptyRuntimeException(exceptionMessage));
    }

    /**
     * 获取对象并转换 对象不为空时执行不为空的方法 为空时抛出RuntimeException子类
     * @param original 获取对象的方法
     * @param oNotNull 对象不为空执行的方法
     * @param e        要抛出的指定RuntimeException子类异常
     * @param <T> 原始数据类型
     * @param <R> 处理后的数据类型
     * @param <EX> 期望的异常
     * @return 返回转换后的对象
     * @throws RuntimeException 预期的异常类型
     */
    public static <T, R, EX extends RuntimeException> List<R> convertThrows(Supplier<List<T>> original, Function<List<T>, List<R>> oNotNull, Supplier<EX> e) {
        List<T> t = original.get();
        if (t == null || t.isEmpty()) throw e.get();
        return oNotNull.apply(t);
    }

}
