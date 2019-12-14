package org.campanula.utils.method;

import java.util.function.Consumer;
import java.util.function.Function;
import java.util.function.Supplier;

import org.campanula.utils.exception.EmptyException;
import org.campanula.utils.exception.EmptyRuntimeException;
import org.campanula.utils.function.VoidMethod;

/**
 * @Author: Campanula
 * @Date 2019-12-14
 */
public class CObjectUtil {

  private static final String OBJECT_IS_NULL = "object is null";

  /**
   * 获取方法返回的对象 如果为空 则返回定义的默认值
   * @param original 返回的对象方法
   * @param nullOr 当获取对象为空时返回的默认值
   * @return 返回对象 如果获取的和默认值都为空 则会返回空
   * @code getO(() -> selectObject(), Object::new)
   */
  public static <T> T getO(Supplier<T> original, Supplier<T> nullOr) {
    if (original == null) return assist(nullOr);
    T t = original.get();
    if (t == null) return assist(nullOr);
    return t;
  }

  /**
   * 获取方法返回的对象 如果为空 则会抛出EmptyRuntimeException
   * @param original 返回的对象方法
   * @return 返回对象
   * @throws EmptyRuntimeException
   * @code oNullRuntimeThrows(() -> selectObject())
   */
  public static <T> T oNullRuntimeThrows(Supplier<T> original) {
    return oNullRuntimeThrows(original, OBJECT_IS_NULL);
  }

  /**
   * 获取方法返回的对象 如果为空 则会抛出EmptyRuntimeException
   * @param original 返回的对象方法
   * @param exceptionMessage 异常的消息
   * @return 返回对象
   * @throws EmptyRuntimeException
   * @code oNullRuntimeThrows(() -> selectObject(), "object is null")
   */
  public static <T> T oNullRuntimeThrows(Supplier<T> original, String exceptionMessage) {
    return oNullRuntimeThrows(original, new EmptyRuntimeException(exceptionMessage));
  }

  /**
   * 获取方法返回的对象 如果为空 则会抛出指定RuntimeException子类异常
   * @param original 返回的对象方法
   * @param e 要抛出的指定RuntimeException子类异常
   * @return 返回对象
   * @throws RuntimeException
   * @code oNullRuntimeThrows(() -> selectObject(), RuntimeException::new)
   */
  public static <T, E extends RuntimeException> T oNullRuntimeThrows(Supplier<T> original, E e) {
    if (original == null) throw e;
    T t = original.get();
    if (t == null) throw e;
    return t;
  }

  /**
   * 获取方法返回的对象 如果为空 则会抛出Exception
   * @param original 返回的对象方法
   * @return 返回对象
   * @throws Exception
   * @code oNullThrows(() -> selectObject())
   */
  public static <T> T oNullThrows(Supplier<T> original) throws EmptyException {
    return oNullThrows(original, OBJECT_IS_NULL);
  }

  /**
   * 获取方法返回的对象 如果为空 则会抛出EmptyException
   * @param original 返回的对象方法
   * @param exceptionMessage 异常的消息
   * @return 返回对象
   * @throws EmptyException
   * @code oNullThrows(() -> selectObject(), "object is null")
   */
  public static <T> T oNullThrows(Supplier<T> original, String exceptionMessage) throws EmptyException {
    return oNullThrows(original, new EmptyException(exceptionMessage));
  }

  /**
   * 获取方法返回的对象 如果为空 则会抛出指定Exception子类异常
   * @param original 返回的对象方法
   * @param e 要抛出的指定Exception子类异常
   * @return 返回对象
   * @throws Exception
   * @code oNullThrows(() -> selectObject(), Exception::new)
   */
  public static <T, E extends Exception> T oNullThrows(Supplier<T> original, E e) throws E {
    if (original == null) throw e;
    T t = original.get();
    if (t == null) throw e;
    return t;
  }

  /**
   * 加工获取的对象 对象不为空时执行不为空的方法 为空时执行为空的方法
   * @param original 获取对象的方法
   * @param oNotNull 对象不为空执行的方法
   * @param oNull 对象为空执行的方法
   * @return 返回加工后的对象 如果如果加工方法为空则返回空
   * @code oProcess(() -> selectObject(), (o) -> process(o), () -> Object::new)
   */
  public static <T> T oProcess(Supplier<T> original, Function<T, T> oNotNull, Supplier<T> oNull) {
    T t = getO(original, null);
    if (t != null)
      if (oNotNull != null) return oNotNull.apply(t);
    else
      if (oNull != null) return oNull.get();
    return null;
  }

  /**
   * 加工获取的对象 对象不为空时执行不为空的方法 为空时抛出EmptyRuntimeException
   * @param original 获取对象的方法
   * @param oNotNull 对象不为空执行的方法
   * @throws EmptyRuntimeException
   * @return 返回加工后的对象
   * @code @code oProcessNullRuntimeThrows(() -> selectObject(), (o) -> process(o))
   */
  public static <T> T oProcessNullRuntimeThrows(Supplier<T> original, Function<T, T> oNotNull) {
    return oProcessNullRuntimeThrows(original, oNotNull, OBJECT_IS_NULL);
  }

  /**
   * 加工获取的对象 对象不为空时执行不为空的方法 为空时抛出EmptyRuntimeException
   * @param original 获取对象的方法
   * @param oNotNull 对象不为空执行的方法
   * @throws EmptyRuntimeException
   * @return 返回加工后的对象
   * @code @code oProcessNullRuntimeThrows(() -> selectObject(), (o) -> process(o), "object is null")
   */
  public static <T> T oProcessNullRuntimeThrows(Supplier<T> original, Function<T, T> oNotNull, String exceptionMessage) {
    return oProcessNullRuntimeThrows(original, oNotNull, new EmptyRuntimeException(exceptionMessage));
  }

  /**
   * 获取对象 对象不为空时执行不为空的方法 为空时抛出RuntimeException子类
   * @param original 获取对象的方法
   * @param oNotNull 对象不为空执行的方法
   * @param e 要抛出的指定RuntimeException子类异常
   * @throws RuntimeException
   * @code oProcessNullRuntimeThrows(() -> selectObject(), (o) -> process(o), RuntimeException::new)
   */
  public static <T, E extends RuntimeException> T oProcessNullRuntimeThrows(Supplier<T> original, Function<T, T> oNotNull, E e) {
    T t = getO(original, null);
    if (t != null)
      if (oNotNull != null) return oNotNull.apply(t);
      else return t;
    throw e;
  }

  /**
   * 加工获取的对象 对象不为空时执行不为空的方法 为空时抛出EmptyException
   * @param original 获取对象的方法
   * @param oNotNull 对象不为空执行的方法
   * @throws EmptyException
   * @return 返回加工后的对象
   * @code @code oProcessNullThrows(() -> selectObject(), process(o))
   */
  public static <T> T oProcessNullThrows(Supplier<T> original, Function<T, T> oNotNull) throws EmptyException {
    return oProcessNullThrows(original, oNotNull, OBJECT_IS_NULL);
  }

  /**
   * 加工获取的对象 对象不为空时执行不为空的方法 为空时抛出EmptyException
   * @param original 获取对象的方法
   * @param oNotNull 对象不为空执行的方法
   * @throws EmptyException
   * @return 返回加工后的对象
   * @code @code oProcessNullThrows(() -> selectObject(), (o) -> process(o), "object is null")
   */
  public static <T> T oProcessNullThrows(Supplier<T> original, Function<T, T> oNotNull, String exceptionMessage) throws EmptyException {
    return oProcessNullThrows(original, oNotNull, new EmptyException(exceptionMessage));
  }

  /**
   * 获取对象 对象不为空时执行不为空的方法 为空时抛出Exception子类
   * @param original 获取对象的方法
   * @param oNotNull 对象不为空执行的方法
   * @param e 要抛出的指定Exception子类异常
   * @throws Exception
   * @code oProcessNullThrows(() -> selectObject(), (o) -> process(o), RuntimeException::new)
   */
  public static <T, E extends Exception> T oProcessNullThrows(Supplier<T> original, Function<T, T> oNotNull, E e) throws E {
    T t = getO(original, null);
    if (t != null)
      if (oNotNull != null) return oNotNull.apply(t);
      else return t;
    else throw e;
  }

  /**
   * 获取对象 对象不为空时执行不为空的方法 为空时执行为空的方法
   * @param original 获取对象的方法
   * @param oNotNull 对象不为空执行的方法
   * @param oNull 对象为空执行的方法
   * @code oConsume(() -> selectObject(), (o) -> update(o), () -> System.out.println("object is null"))
   */
  public static <T> void oConsume(Supplier<T> original, Consumer<T> oNotNull, VoidMethod oNull) {
    T t = getO(original, null);
    if (t != null)
      if (oNotNull != null)
        oNotNull.accept(t);
      else
      if (oNull != null)
        oNull.method();
  }

  /**
   * 获取对象 对象不为空时执行不为空的方法 为空时抛出EmptyRuntimeException
   * @param original 获取对象的方法
   * @param oNotNull 对象不为空执行的方法
   * @throws EmptyRuntimeException
   * @code oConsumeNullRuntimeThrows(() -> selectObject(), (o) -> update(o))
   */
  public static <T> void oConsumeNullRuntimeThrows(Supplier<T> original, Consumer<T> oNotNull) {
    oConsumeNullRuntimeThrows(original, oNotNull, OBJECT_IS_NULL);
  }

  /**
   * 获取对象 对象不为空时执行不为空的方法 为空时抛出EmptyRuntimeException
   * @param original 获取对象的方法
   * @param oNotNull 对象不为空执行的方法
   * @param exceptionMessage 异常的消息
   * @throws EmptyRuntimeException
   * @code oConsumeNullRuntimeThrows(() -> selectObject(), (o) -> update(o), "object is null")
   */
  public static <T> void oConsumeNullRuntimeThrows(Supplier<T> original, Consumer<T> oNotNull, String exceptionMessage) {
    oConsumeNullRuntimeThrows(original, oNotNull, new EmptyRuntimeException(exceptionMessage));
  }

  /**
   * 获取对象 对象不为空时执行不为空的方法 为空时抛出RuntimeException子类
   * @param original 获取对象的方法
   * @param oNotNull 对象不为空执行的方法
   * @param e 要抛出的指定RuntimeException子类异常
   * @throws RuntimeException
   * @code oConsumeNullRuntimeThrows(() -> selectObject(), (o) -> update(o), RuntimeException::new)
   */
  public static <T, E extends RuntimeException> void oConsumeNullRuntimeThrows(Supplier<T> original, Consumer<T> oNotNull, E e) {
    T t = getO(original, null);
    if (t != null)
      if (oNotNull != null) oNotNull.accept(t);
    throw e;
  }

  /**
   * 获取对象 对象不为空时执行不为空的方法 为空时抛出EmptyException
   * @param original 获取对象的方法
   * @param oNotNull 对象不为空执行的方法
   * @throws EmptyException
   * @code oConsumeNullThrows(() -> selectObject(), (o) -> update(o))
   */
  public static <T> void oConsumeNullThrows(Supplier<T> original, Consumer<T> oNotNull) throws EmptyException {
    oConsumeNullThrows(original, oNotNull, OBJECT_IS_NULL);
  }

  /**
   * 获取对象 对象不为空时执行不为空的方法 为空时抛出EmptyException
   * @param original 获取对象的方法
   * @param oNotNull 对象不为空执行的方法
   * @param exceptionMessage 异常的消息
   * @throws EmptyException
   * @code oConsumeNullThrows(() -> selectObject(), (o) -> update(o), "object is null")
   */
  public static <T> void oConsumeNullThrows(Supplier<T> original, Consumer<T> oNotNull, String exceptionMessage) throws EmptyException {
    oConsumeNullThrows(original, oNotNull, new EmptyException(exceptionMessage));
  }

  /**
   * 获取对象 对象不为空时执行不为空的方法 为空时抛出Exception子类
   * @param original 获取对象的方法
   * @param oNotNull 对象不为空执行的方法
   * @param e 要抛出的指定Exception子类异常
   * @throws Exception
   * @code oConsumeNullRuntimeThrows(() -> selectObject(), (o) -> update(o), RuntimeException::new)
   */
  public static <T, E extends Exception> void oConsumeNullThrows(Supplier<T> original, Consumer<T> oNotNull, E e) throws E {
    T t = getO(original, null);
    if (t != null)
      if (oNotNull != null)
        oNotNull.accept(t);
      else throw e;
  }

  /**
   * 获取对象并转换 对象不为空时执行不为空的方法 为空时执行为空的方法
   * @param original 获取对象的方法
   * @param oNotNull 对象不为空执行的方法
   * @param oNull 对象为空执行的方法
   * @return 返回转换后的对象
   * @code oConvert(() -> selectObject(), (a) -> a2b(a), () -> B::new)
   */
  public static <T, R> R oConvert(Supplier<T> original, Function<T, R> oNotNull, Supplier<R> oNull) {
    T t = getO(original, null);
    if (t != null) return oNotNull.apply(t);
    return oNull.get();
  }

  /**
   * 获取对象并转换 对象不为空时执行不为空的方法 为空时抛出EmptyRuntimeException
   * @param original 获取对象的方法
   * @param oNotNull 对象不为空执行的方法
   * @throws EmptyRuntimeException
   * @return 返回转换后的对象
   * @code @code oConvertNullRuntimeThrows(() -> selectObject(), (a) -> a2b(a))
   */
  public static <T, R> R oConvertNullRuntimeThrows(Supplier<T> original, Function<T, R> oNotNull) {
    return oConvertNullRuntimeThrows(original, oNotNull, OBJECT_IS_NULL);
  }

  /**
   * 获取对象并转换 对象不为空时执行不为空的方法 为空时抛出EmptyRuntimeException
   * @param original 获取对象的方法
   * @param oNotNull 对象不为空执行的方法
   * @throws EmptyRuntimeException
   * @return 返回转换后的对象
   * @code @code oConvertNullRuntimeThrows(() -> selectObject(), (a) -> a2b(a), "object is null")
   */
  public static <T, R> R oConvertNullRuntimeThrows(Supplier<T> original, Function<T, R> oNotNull, String exceptionMessage) {
    return oConvertNullRuntimeThrows(original, oNotNull, new EmptyRuntimeException(exceptionMessage));
  }

  /**
   * 获取对象并转换 对象不为空时执行不为空的方法 为空时抛出RuntimeException子类
   * @param original 获取对象的方法
   * @param oNotNull 对象不为空执行的方法
   * @param e 要抛出的指定RuntimeException子类异常
   * @throws RuntimeException
   * @return 返回转换后的对象
   * @code oConvertNullRuntimeThrows(() -> selectObject(), (a) -> a2b(a), RuntimeException::new)
   */
  public static <T, R, E extends RuntimeException> R oConvertNullRuntimeThrows(Supplier<T> original, Function<T, R> oNotNull, E e) {
    T t = getO(original, null);
    if (t != null) return oNotNull.apply(t);
    throw e;
  }

  /**
   * 获取对象并转换 对象不为空时执行不为空的方法 为空时抛出EmptyException
   * @param original 获取对象的方法
   * @param oNotNull 对象不为空执行的方法
   * @throws EmptyException
   * @return 返回转换后的对象
   * @code @code oProcessNullThrows(() -> selectObject(), (a) -> a2b(a))
   */
  public static <T, R> R oConvertNullThrows(Supplier<T> original, Function<T, R> oNotNull) throws EmptyException {
    return oConvertNullThrows(original, oNotNull, OBJECT_IS_NULL);
  }

  /**
   * 获取对象并转换 对象不为空时执行不为空的方法 为空时抛出EmptyException
   * @param original 获取对象的方法
   * @param oNotNull 对象不为空执行的方法
   * @throws EmptyException
   * @return 返回转换后的对象
   * @code @code oConvertNullThrows(() -> selectObject(), (a) -> a2b(a), "object is null")
   */
  public static <T, R> R oConvertNullThrows(Supplier<T> original, Function<T, R> oNotNull, String exceptionMessage) throws EmptyException {
    return oConvertNullThrows(original, oNotNull, new EmptyException(exceptionMessage));
  }

  /**
   * 获取对象并转换 对象不为空时执行不为空的方法 为空时抛出Exception子类
   * @param original 获取对象的方法
   * @param oNotNull 对象不为空执行的方法
   * @param e 要抛出的指定Exception子类异常
   * @return 返回转换后的对象
   * @throws Exception
   * @code oConvertNullThrows(() -> selectObject(), (a) -> a2b(a), Exception::new)
   */
  public static <T, R, E extends Exception> R oConvertNullThrows(Supplier<T> original, Function<T, R> oNotNull, E e) throws E {
    T t = getO(original, null);
    if (t != null) return oNotNull.apply(t);
    throw e;
  }

  private static <T> T assist(Supplier<T> or) {
    if (or == null) return null;
    else return or.get();
  }
}
