package org.campanula.utils.method;

import java.util.List;
import java.util.function.Supplier;

public class CListUtil {

  public static <T> List<T> listIsEmpty(Supplier<List<T>> original, Supplier<List<T>> or) {
    if (original == null) return assist(or);
    List<T> list = original.get();
    if (list == null || list.isEmpty()) return assist(or);
    return list;
  }

  public static <T> List<T> listIsEmptyRuntimeThrows(Supplier<List<T>> original) {
    return listIsEmptyRuntimeThrows(original, "list is empty");
  }

  public static <T> List<T> listIsEmptyRuntimeThrows(Supplier<List<T>> original, String exceptionMessage) {
    if (original == null) throw new RuntimeException(exceptionMessage);
    List<T> list = original.get();
    if (list == null || list.isEmpty()) throw new RuntimeException(exceptionMessage);
    return list;
  }

  public static <T> List<T> listIsEmptyRuntimeThrows(Supplier<List<T>> original, RuntimeException e) {
    if (original == null) throw e;
    List<T> list = original.get();
    if (list == null || list.isEmpty()) throw e;
    return list;
  }

  public static <T> List<T> listIsEmptyThrows(Supplier<List<T>> original) throws Exception {
    return listIsEmptyThrows(original, "object is null");
  }

  public static <T> List<T> listIsEmptyThrows(Supplier<List<T>> original, String exceptionMessage) throws Exception {
    if (original == null) throw new Exception(exceptionMessage);
    List<T> list = original.get();
    if (list == null || list.isEmpty()) throw new Exception(exceptionMessage);
    return list;
  }

  public static <T> List<T> listIsEmptyThrows(Supplier<List<T>> original, Exception e) throws Exception {
    if (original == null) throw e;
    List<T> list = original.get();
    if (list == null || list.isEmpty()) throw e;
    return list;
  }

  private static <T> T assist(Supplier<T> or) {
    if (or == null) return null;
    else return or.get();
  }
}
