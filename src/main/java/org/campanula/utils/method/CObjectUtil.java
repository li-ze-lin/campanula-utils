package org.campanula.utils.method;

import java.util.function.Consumer;
import java.util.function.Supplier;
import org.campanula.utils.function.VoidMethod;

public class CObjectUtil {

  /**
   * 获取方法返回的对象 如果为空 则返回定义的默认值
   * @param original 返回的对象方法
   * @param nullOr 当获取对象为空时返回的默认值
   * @param <T>
   * @return 返回对象 如果获取的和默认值都为空 则会返回空
   * @code getO(() -> selectObject(), Object::new)
   */
  public static <T> T getO(Supplier<T> original, Supplier<T> nullOr) {
    if (original == null) return assist(nullOr);
    T t = original.get();
    if (t == null) return assist(nullOr);
    else return t;
  }

  /**
   * 获取方法返回的对象 如果为空 则会抛出RuntimeException
   * @param original 返回的对象方法
   * @param <T>
   * @return 返回对象
   * @throws RuntimeException
   * @code oNullRuntimeThrows(() -> selectObject())
   */
  public static <T> T oNullRuntimeThrows(Supplier<T> original) {
    return oNullRuntimeThrows(original, "object is null");
  }

  /**
   * 获取方法返回的对象 如果为空 则会抛出RuntimeException
   * @param original 返回的对象方法
   * @param exceptionMessage 异常的消息
   * @param <T>
   * @return 返回对象
   * @throws RuntimeException
   * @code oNullRuntimeThrows(() -> selectObject(), "object is null")
   */
  public static <T> T oNullRuntimeThrows(Supplier<T> original, String exceptionMessage) {
    if (original == null) throw new RuntimeException(exceptionMessage);
    T t = original.get();
    if (t == null) throw new RuntimeException(exceptionMessage);
    return t;
  }

  /**
   * 获取方法返回的对象 如果为空 则会抛出指定RuntimeException子类异常
   * @param original 返回的对象方法
   * @param e 要抛出的指定RuntimeException子类异常
   * @param <T>
   * @return 返回对象
   * @throws RuntimeException
   * @code oNullRuntimeThrows(() -> selectObject(), RuntimeException::new)
   */
  public static <T> T oNullRuntimeThrows(Supplier<T> original, RuntimeException e) {
    if (original == null) throw e;
    T t = original.get();
    if (t == null) throw e;
    return t;
  }

  /**
   * 获取方法返回的对象 如果为空 则会抛出Exception
   * @param original 返回的对象方法
   * @param <T>
   * @return 返回对象
   * @throws Exception
   * @code oNullThrows(() -> selectObject())
   */
  public static <T> T oNullThrows(Supplier<T> original) throws Exception {
    return oNullThrows(original, "object is null");
  }

  /**
   * 获取方法返回的对象 如果为空 则会抛出Exception
   * @param original 返回的对象方法
   * @param exceptionMessage 异常的消息
   * @param <T>
   * @return 返回对象
   * @throws Exception
   * @code oNullThrows(() -> selectObject(), "object is null")
   */
  public static <T> T oNullThrows(Supplier<T> original, String exceptionMessage) throws Exception {
    if (original == null) throw new Exception(exceptionMessage);
    T t = original.get();
    if (t == null) throw new Exception(exceptionMessage);
    return t;
  }

  /**
   * 获取方法返回的对象 如果为空 则会抛出指定Exception子类异常
   * @param original 返回的对象方法
   * @param e 要抛出的指定Exception子类异常
   * @param <T>
   * @return 返回对象
   * @throws Exception
   * @code oNullThrows(() -> selectObject(), Exception::new)
   */
  public static <T> T oNullThrows(Supplier<T> original, Exception e) throws Exception {
    if (original == null) throw e;
    T t = original.get();
    if (t == null) throw e;
    return t;
  }

  /**
   * 获取对象 对象不为空时执行不为空的方法 为空时执行为空的方法
   * @param original 获取对象的方法
   * @param oNotNull 对象不为空执行的方法
   * @param oNull 对象为空执行的方法
   * @param <T>
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

  private static <T> T assist(Supplier<T> or) {
    if (or == null) return null;
    else return or.get();
  }
}
