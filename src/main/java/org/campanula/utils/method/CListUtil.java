package org.campanula.utils.method;

import org.campanula.utils.exception.ObjectEmptyRuntimeException;
import org.campanula.utils.function.VoidMethod;

import java.util.List;
import java.util.function.Consumer;
import java.util.function.Function;
import java.util.function.Supplier;

/**
 * @Author: Campanula
 * @Date 2019-12-14
 */
public final class CListUtil {

    /**
     * 获取方法返回的对象 如果为空 则返回定义的默认值
     * @param original 返回的对象方法
     * @param nullOr   当获取对象为空时返回的默认值
     * @return 返回对象 如果获取的和默认值都为空 则会返回空
     * @code get(() -> selectObject(), Object::new)
     */
    public static <T> List<T> get(Supplier<List<T>> original, Supplier<List<T>> nullOr) {
        List<T> t = original.get();
        if (t == null || t.isEmpty()) return nullOr.get();
        return t;
    }

    /**
     * 获取方法返回的对象 如果为空 则会抛出ObjectEmptyRuntimeException
     * @param original 返回的对象方法
     * @return 返回对象
     * @throws ObjectEmptyRuntimeException
     * @code getThrows(() -> selectObject())
     */
    public static <T> List<T> getThrows(Supplier<List<T>> original) {
        return getThrows(original, ObjectEmptyRuntimeException::new);
    }

    /**
     * 获取方法返回的对象 如果为空 则会抛出ObjectEmptyRuntimeException
     * @param original         返回的对象方法
     * @param exceptionMessage 异常的消息
     * @return 返回对象
     * @throws ObjectEmptyRuntimeException
     * @code getThrows(() -> selectObject(), "object is null")
     */
    public static <T> List<T> getThrows(Supplier<List<T>> original, final String exceptionMessage) {
        return getThrows(original, () -> new ObjectEmptyRuntimeException(exceptionMessage));
    }

    /**
     * 获取方法返回的对象 如果为空 则会抛出指定RuntimeException子类异常
     * @param original 返回的对象方法
     * @param e        要抛出的指定RuntimeException子类异常
     * @return 返回对象
     * @throws RuntimeException
     * @code getThrows(() -> selectObject(), RuntimeException::new)
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
     * @return 返回加工后的对象 如果如果加工方法为空则返回空
     * @code process(() -> selectObject(), (o) -> process(o), () -> Object::new)
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
     * @return 返回加工后的对象
     * @throws ObjectEmptyRuntimeException
     * @code processThrows(() -> selectObject(), (o) -> process(o))
     */
    public static <T> List<T> processThrows(Supplier<List<T>> original, Function<List<T>, List<T>> oNotNull) {
        return processThrows(original, oNotNull, ObjectEmptyRuntimeException::new);
    }

    /**
     * 加工获取的对象 对象不为空时执行不为空的方法 为空时抛出EmptyRuntimeException
     * @param original 获取对象的方法
     * @param oNotNull 对象不为空执行的方法
     * @return 返回加工后的对象
     * @throws ObjectEmptyRuntimeException
     * @code processThrows(() -> selectObject(), (o) -> process(o), "object is null")
     */
    public static <T> List<T> processThrows(Supplier<List<T>> original, Function<List<T>, List<T>> oNotNull, final String exceptionMessage) {
        return processThrows(original, oNotNull, () -> new ObjectEmptyRuntimeException(exceptionMessage));
    }

    /**
     * 获取对象 对象不为空时执行不为空的方法 为空时抛出RuntimeException子类
     * @param original 获取对象的方法
     * @param oNotNull 对象不为空执行的方法
     * @param e        要抛出的指定RuntimeException子类异常
     * @throws RuntimeException
     * @code processThrows(() -> selectObject(), (o) -> process(o), RuntimeException::new)
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
     * @code consume(() -> selectObject(), (o) -> update(o), () -> System.out.println("object is null"))
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
     * @throws ObjectEmptyRuntimeException
     * @code consumeThrows(() -> selectObject(), (o) -> update(o))
     */
    public static <T> void consumeThrows(Supplier<List<T>> original, Consumer<List<T>> oNotNull) {
        consumeThrows(original, oNotNull, ObjectEmptyRuntimeException::new);
    }

    /**
     * 获取对象 对象不为空时执行不为空的方法 为空时抛出EmptyRuntimeException
     * @param original         获取对象的方法
     * @param oNotNull         对象不为空执行的方法
     * @param exceptionMessage 异常的消息
     * @throws ObjectEmptyRuntimeException
     * @code consumeThrows(() -> selectObject(), (o) -> update(o), "object is null")
     */
    public static <T> void consumeThrows(Supplier<List<T>> original, Consumer<List<T>> oNotNull, final String exceptionMessage) {
        consumeThrows(original, oNotNull, () -> new ObjectEmptyRuntimeException(exceptionMessage));
    }

    /**
     * 获取对象 对象不为空时执行不为空的方法 为空时抛出RuntimeException子类
     * @param original 获取对象的方法
     * @param oNotNull 对象不为空执行的方法
     * @param e        要抛出的指定RuntimeException子类异常
     * @throws RuntimeException
     * @code consumeThrows(() -> selectObject(), (o) -> update(o), RuntimeException::new)
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
     * @return 返回转换后的对象
     * @code convert(() -> selectObject(), (a) -> a2b(a), () -> B::new)
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
     * @return 返回转换后的对象
     * @throws ObjectEmptyRuntimeException
     * @code convertThrows(() -> selectObject(), (a) -> a2b(a))
     */
    public static <T, R> List<R> convertThrows(Supplier<List<T>> original, Function<List<T>, List<R>> oNotNull) {
        return convertThrows(original, oNotNull, ObjectEmptyRuntimeException::new);
    }

    /**
     * 获取对象并转换 对象不为空时执行不为空的方法 为空时抛出ObjectEmptyRuntimeException
     * @param original 获取对象的方法
     * @param oNotNull 对象不为空执行的方法
     * @return 返回转换后的对象
     * @throws ObjectEmptyRuntimeException
     * @code convertThrows(() -> selectObject(), (a) -> a2b(a), "object is null")
     */
    public static <T, R> List<R> convertThrows(Supplier<List<T>> original, Function<List<T>, List<R>> oNotNull, final String exceptionMessage) {
        return convertThrows(original, oNotNull, () -> new ObjectEmptyRuntimeException(exceptionMessage));
    }

    /**
     * 获取对象并转换 对象不为空时执行不为空的方法 为空时抛出RuntimeException子类
     * @param original 获取对象的方法
     * @param oNotNull 对象不为空执行的方法
     * @param e        要抛出的指定RuntimeException子类异常
     * @return 返回转换后的对象
     * @throws RuntimeException
     * @code convertThrows(() -> selectObject(), (a) -> a2b(a), RuntimeException::new)
     */
    public static <T, R, EX extends RuntimeException> List<R> convertThrows(Supplier<List<T>> original, Function<List<T>, List<R>> oNotNull, Supplier<EX> e) {
        List<T> t = original.get();
        if (t == null || t.isEmpty()) throw e.get();
        return oNotNull.apply(t);
    }

}
