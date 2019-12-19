package org.campanula.utils.method;

import java.util.function.Consumer;
import java.util.function.Function;
import java.util.function.Supplier;

import org.campanula.utils.exception.ObjectEmptyException;
import org.campanula.utils.exception.ObjectEmptyRuntimeException;
import org.campanula.utils.function.VoidMethod;

/**
 * @Author: Campanula
 * @Date 2019-12-14
 * 如果传入的方法为null 被调用到会抛出 MethodNullException
 */
public class CObjectUtil {

    /**
     * 获取方法返回的对象 如果为空 则返回定义的默认值
     *
     * @param original 返回的对象方法
     * @param nullOr   当获取对象为空时返回的默认值
     * @return 返回对象 如果获取的和默认值都为空 则会返回空
     * @code get(() -> selectObject(), Object::new)
     */
    public static <T> T get(Supplier<T> original, Supplier<T> nullOr) {
        T t = original.get();
        if (t == null) return nullOr.get();
        return t;
    }

    /**
     * 获取方法返回的对象 如果为空 则会抛出EmptyRuntimeException
     *
     * @param original 返回的对象方法
     * @return 返回对象
     * @throws ObjectEmptyRuntimeException
     * @code nullRuntimeThrows(() -> selectObject())
     */
    public static <T> T nullRuntimeThrows(Supplier<T> original) {
        return nullRuntimeThrows(original, ObjectEmptyRuntimeException::new);
    }

    /**
     * 获取方法返回的对象 如果为空 则会抛出EmptyRuntimeException
     *
     * @param original         返回的对象方法
     * @param exceptionMessage 异常的消息
     * @return 返回对象
     * @throws ObjectEmptyRuntimeException
     * @code nullRuntimeThrows(() -> selectObject(), "object is null")
     */
    public static <T> T nullRuntimeThrows(Supplier<T> original, final String exceptionMessage) {
        return nullRuntimeThrows(original, () -> new ObjectEmptyRuntimeException(exceptionMessage));
    }

    /**
     * 获取方法返回的对象 如果为空 则会抛出指定RuntimeException子类异常
     *
     * @param original 返回的对象方法
     * @param e        要抛出的指定RuntimeException子类异常
     * @return 返回对象
     * @throws RuntimeException
     * @code nullRuntimeThrows(() -> selectObject(), RuntimeException::new)
     */
    public static <T, EX extends RuntimeException> T nullRuntimeThrows(Supplier<T> original, Supplier<EX> e) {
        T t = original.get();
        if (t == null) throw e.get();
        return t;
    }

    /**
     * 获取方法返回的对象 如果为空 则会抛出Exception
     *
     * @param original 返回的对象方法
     * @return 返回对象
     * @throws Exception
     * @code nullThrows(() -> selectObject())
     */
    public static <T> T nullThrows(Supplier<T> original) throws ObjectEmptyException {
        return nullThrows(original, ObjectEmptyException::new);
    }

    /**
     * 获取方法返回的对象 如果为空 则会抛出EmptyException
     *
     * @param original         返回的对象方法
     * @param exceptionMessage 异常的消息
     * @return 返回对象
     * @throws ObjectEmptyException
     * @code nullThrows(() -> selectObject(), "object is null")
     */
    public static <T> T nullThrows(Supplier<T> original, final String exceptionMessage) throws ObjectEmptyException {
        return nullThrows(original, () -> new ObjectEmptyException(exceptionMessage));
    }

    /**
     * 获取方法返回的对象 如果为空 则会抛出指定Exception子类异常
     *
     * @param original 返回的对象方法
     * @param e        要抛出的指定Exception子类异常
     * @return 返回对象
     * @throws Exception
     * @code nullThrows(() -> selectObject(), Exception::new)
     */
    public static <T, EX extends Exception> T nullThrows(Supplier<T> original, Supplier<EX> e) throws EX {
        T t = original.get();
        if (t == null) throw e.get();
        return t;
    }

    /**
     * 加工获取的对象 对象不为空时执行不为空的方法 为空时执行为空的方法
     *
     * @param original 获取对象的方法
     * @param oNotNull 对象不为空执行的方法
     * @param oNull    对象为空执行的方法
     * @return 返回加工后的对象 如果如果加工方法为空则返回空
     * @code oProcess(() -> selectObject(), (o) -> process(o), () -> Object::new)
     */
    public static <T> T process(Supplier<T> original, Function<T, T> oNotNull, Supplier<T> oNull) {
        T t = original.get();
        if (t != null) return oNotNull.apply(t);
        return oNull.get();
    }

    /**
     * 加工获取的对象 对象不为空时执行不为空的方法 为空时抛出EmptyRuntimeException
     *
     * @param original 获取对象的方法
     * @param oNotNull 对象不为空执行的方法
     * @return 返回加工后的对象
     * @throws ObjectEmptyRuntimeException
     * @code @code processNullRuntimeThrows(() -> selectObject(), (o) -> process(o))
     */
    public static <T> T processNullRuntimeThrows(Supplier<T> original, Function<T, T> oNotNull) {
        return processNullRuntimeThrows(original, oNotNull, ObjectEmptyRuntimeException::new);
    }

    /**
     * 加工获取的对象 对象不为空时执行不为空的方法 为空时抛出EmptyRuntimeException
     *
     * @param original 获取对象的方法
     * @param oNotNull 对象不为空执行的方法
     * @return 返回加工后的对象
     * @throws ObjectEmptyRuntimeException
     * @code @code processNullRuntimeThrows(() -> selectObject(), (o) -> process(o), "object is null")
     */
    public static <T> T processNullRuntimeThrows(Supplier<T> original, Function<T, T> oNotNull, final String exceptionMessage) {
        return processNullRuntimeThrows(original, oNotNull, () -> new ObjectEmptyRuntimeException(exceptionMessage));
    }

    /**
     * 获取对象 对象不为空时执行不为空的方法 为空时抛出RuntimeException子类
     *
     * @param original 获取对象的方法
     * @param oNotNull 对象不为空执行的方法
     * @param e        要抛出的指定RuntimeException子类异常
     * @throws RuntimeException
     * @code processNullRuntimeThrows(() -> selectObject(), (o) -> process(o), RuntimeException::new)
     */
    public static <T, EX extends RuntimeException> T processNullRuntimeThrows(Supplier<T> original, Function<T, T> oNotNull, Supplier<EX> e) {
        T t = original.get();
        if (t != null) return oNotNull.apply(t);
        throw e.get();
    }

    /**
     * 加工获取的对象 对象不为空时执行不为空的方法 为空时抛出EmptyException
     *
     * @param original 获取对象的方法
     * @param oNotNull 对象不为空执行的方法
     * @return 返回加工后的对象
     * @throws ObjectEmptyException
     * @code @code processNullThrows(() -> selectObject(), process(o))
     */
    public static <T> T processNullThrows(Supplier<T> original, Function<T, T> oNotNull) throws ObjectEmptyException {
        return processNullThrows(original, oNotNull, ObjectEmptyException::new);
    }

    /**
     * 加工获取的对象 对象不为空时执行不为空的方法 为空时抛出EmptyException
     *
     * @param original 获取对象的方法
     * @param oNotNull 对象不为空执行的方法
     * @return 返回加工后的对象
     * @throws ObjectEmptyException
     * @code @code processNullThrows(() -> selectObject(), (o) -> process(o), "object is null")
     */
    public static <T> T processNullThrows(Supplier<T> original, Function<T, T> oNotNull, final String exceptionMessage) throws ObjectEmptyException {
        return processNullThrows(original, oNotNull, () -> new ObjectEmptyException(exceptionMessage));
    }

    /**
     * 获取对象 对象不为空时执行不为空的方法 为空时抛出Exception子类
     *
     * @param original 获取对象的方法
     * @param oNotNull 对象不为空执行的方法
     * @param e        要抛出的指定Exception子类异常
     * @throws Exception
     * @code processNullThrows(() -> selectObject(), (o) -> process(o), RuntimeException::new)
     */
    public static <T, EX extends Exception> T processNullThrows(Supplier<T> original, Function<T, T> oNotNull, Supplier<EX> e) throws EX {
        T t = original.get();
        if (t != null) return oNotNull.apply(t);
        throw e.get();
    }

    /**
     * 获取对象 对象不为空时执行不为空的方法 为空时执行为空的方法
     *
     * @param original 获取对象的方法
     * @param oNotNull 对象不为空执行的方法
     * @param oNull    对象为空执行的方法
     * @code oConsume(() -> selectObject(), (o) -> update(o), () -> System.out.println("object is null"))
     */
    public static <T> void consume(Supplier<T> original, Consumer<T> oNotNull, VoidMethod oNull) {
        T t = original.get();
        if (t != null) oNotNull.accept(t);
        else oNull.method();
    }

    /**
     * 获取对象 对象不为空时执行不为空的方法 为空时抛出EmptyRuntimeException
     *
     * @param original 获取对象的方法
     * @param oNotNull 对象不为空执行的方法
     * @throws ObjectEmptyRuntimeException
     * @code consumeNullRuntimeThrows(() -> selectObject(), (o) -> update(o))
     */
    public static <T> void consumeNullRuntimeThrows(Supplier<T> original, Consumer<T> oNotNull) {
        consumeNullRuntimeThrows(original, oNotNull, ObjectEmptyRuntimeException::new);
    }

    /**
     * 获取对象 对象不为空时执行不为空的方法 为空时抛出EmptyRuntimeException
     *
     * @param original         获取对象的方法
     * @param oNotNull         对象不为空执行的方法
     * @param exceptionMessage 异常的消息
     * @throws ObjectEmptyRuntimeException
     * @code consumeNullRuntimeThrows(() -> selectObject(), (o) -> update(o), "object is null")
     */
    public static <T> void consumeNullRuntimeThrows(Supplier<T> original, Consumer<T> oNotNull, final String exceptionMessage) {
        consumeNullRuntimeThrows(original, oNotNull, () -> new ObjectEmptyRuntimeException(exceptionMessage));
    }

    /**
     * 获取对象 对象不为空时执行不为空的方法 为空时抛出RuntimeException子类
     *
     * @param original 获取对象的方法
     * @param oNotNull 对象不为空执行的方法
     * @param e        要抛出的指定RuntimeException子类异常
     * @throws RuntimeException
     * @code consumeNullRuntimeThrows(() -> selectObject(), (o) -> update(o), RuntimeException::new)
     */
    public static <T, EX extends RuntimeException> void consumeNullRuntimeThrows(Supplier<T> original, Consumer<T> oNotNull, Supplier<EX> e) {
        T t = original.get();
        if (t != null) oNotNull.accept(t);
        else throw e.get();
    }

    /**
     * 获取对象 对象不为空时执行不为空的方法 为空时抛出EmptyException
     *
     * @param original 获取对象的方法
     * @param oNotNull 对象不为空执行的方法
     * @throws ObjectEmptyException
     * @code consumeNullThrows(() -> selectObject(), (o) -> update(o))
     */
    public static <T> void consumeNullThrows(Supplier<T> original, Consumer<T> oNotNull) throws ObjectEmptyException {
        consumeNullThrows(original, oNotNull, ObjectEmptyException::new);
    }

    /**
     * 获取对象 对象不为空时执行不为空的方法 为空时抛出EmptyException
     *
     * @param original         获取对象的方法
     * @param oNotNull         对象不为空执行的方法
     * @param exceptionMessage 异常的消息
     * @throws ObjectEmptyException
     * @code consumeNullThrows(() -> selectObject(), (o) -> update(o), "object is null")
     */
    public static <T> void consumeNullThrows(Supplier<T> original, Consumer<T> oNotNull, final String exceptionMessage) throws ObjectEmptyException {
        consumeNullThrows(original, oNotNull, () -> new ObjectEmptyException(exceptionMessage));
    }

    /**
     * 获取对象 对象不为空时执行不为空的方法 为空时抛出Exception子类
     *
     * @param original 获取对象的方法
     * @param oNotNull 对象不为空执行的方法
     * @param e        要抛出的指定Exception子类异常
     * @throws Exception
     * @code consumeNullRuntimeThrows(() -> selectObject(), (o) -> update(o), RuntimeException::new)
     */
    public static <T, EX extends Exception> void consumeNullThrows(Supplier<T> original, Consumer<T> oNotNull, Supplier<EX> e) throws EX {
        T t = original.get();
        if (t != null) oNotNull.accept(t);
        else throw e.get();
    }

    /**
     * 获取对象并转换 对象不为空时执行不为空的方法 为空时执行为空的方法 如果执行方法为空 则会抛出 MethodNullException
     *
     * @param original 获取对象的方法
     * @param oNotNull 对象不为空执行的方法
     * @param oNull    对象为空执行的方法
     * @return 返回转换后的对象
     * @code oConvert(() -> selectObject(), (a) -> a2b(a), () -> B::new)
     */
    public static <T, R> R convert(Supplier<T> original, Function<T, R> oNotNull, Supplier<R> oNull) {
        T t = original.get();
        if (t != null) return oNotNull.apply(t);
        return oNull.get();
    }

    /**
     * 获取对象并转换 对象不为空时执行不为空的方法 为空时抛出EmptyRuntimeException
     *
     * @param original 获取对象的方法
     * @param oNotNull 对象不为空执行的方法
     * @return 返回转换后的对象
     * @throws ObjectEmptyRuntimeException
     * @code @code convertNullRuntimeThrows(() -> selectObject(), (a) -> a2b(a))
     */
    public static <T, R> R convertNullRuntimeThrows(Supplier<T> original, Function<T, R> oNotNull) {
        return convertNullRuntimeThrows(original, oNotNull, ObjectEmptyRuntimeException::new);
    }

    /**
     * 获取对象并转换 对象不为空时执行不为空的方法 为空时抛出EmptyRuntimeException
     *
     * @param original 获取对象的方法
     * @param oNotNull 对象不为空执行的方法
     * @return 返回转换后的对象
     * @throws ObjectEmptyRuntimeException
     * @code @code convertNullRuntimeThrows(() -> selectObject(), (a) -> a2b(a), "object is null")
     */
    public static <T, R> R convertNullRuntimeThrows(Supplier<T> original, Function<T, R> oNotNull, final String exceptionMessage) {
        return convertNullRuntimeThrows(original, oNotNull, () -> new ObjectEmptyRuntimeException(exceptionMessage));
    }

    /**
     * 获取对象并转换 对象不为空时执行不为空的方法 为空时抛出RuntimeException子类
     *
     * @param original 获取对象的方法
     * @param oNotNull 对象不为空执行的方法
     * @param e        要抛出的指定RuntimeException子类异常
     * @return 返回转换后的对象
     * @throws RuntimeException
     * @code convertNullRuntimeThrows(() -> selectObject(), (a) -> a2b(a), RuntimeException::new)
     */
    public static <T, R, EX extends RuntimeException> R convertNullRuntimeThrows(Supplier<T> original, Function<T, R> oNotNull, Supplier<EX> e) {
        T t = original.get();
        if (t != null) return oNotNull.apply(t);
        throw e.get();
    }

    /**
     * 获取对象并转换 对象不为空时执行不为空的方法 为空时抛出EmptyException
     *
     * @param original 获取对象的方法
     * @param oNotNull 对象不为空执行的方法
     * @return 返回转换后的对象
     * @throws ObjectEmptyException
     * @code @code processNullThrows(() -> selectObject(), (a) -> a2b(a))
     */
    public static <T, R> R convertNullThrows(Supplier<T> original, Function<T, R> oNotNull) throws ObjectEmptyException {
        return convertNullThrows(original, oNotNull, ObjectEmptyException::new);
    }

    /**
     * 获取对象并转换 对象不为空时执行不为空的方法 为空时抛出EmptyException
     *
     * @param original 获取对象的方法
     * @param oNotNull 对象不为空执行的方法
     * @return 返回转换后的对象
     * @throws ObjectEmptyException
     * @code @code convertNullThrows(() -> selectObject(), (a) -> a2b(a), "object is null")
     */
    public static <T, R> R convertNullThrows(Supplier<T> original, Function<T, R> oNotNull, final String exceptionMessage) throws ObjectEmptyException {
        return convertNullThrows(original, oNotNull, () -> new ObjectEmptyException(exceptionMessage));
    }

    /**
     * 获取对象并转换 对象不为空时执行不为空的方法 为空时抛出Exception子类
     *
     * @param original 获取对象的方法
     * @param oNotNull 对象不为空执行的方法
     * @param e        要抛出的指定Exception子类异常
     * @return 返回转换后的对象
     * @throws Exception
     * @code convertNullThrows(() -> selectObject(), (a) -> a2b(a), Exception::new)
     */
    public static <T, R, EX extends Exception> R convertNullThrows(Supplier<T> original, Function<T, R> oNotNull, Supplier<EX> e) throws EX {
        T t = original.get();
        if (t != null) return oNotNull.apply(t);
        throw e.get();
    }

}
