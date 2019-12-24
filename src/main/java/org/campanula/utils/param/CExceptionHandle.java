package org.campanula.utils.param;

import org.campanula.utils.function.VoidMethod;
import java.util.LinkedList;
import java.util.List;
import java.util.function.Supplier;

/**
 * 用于异常处理
 * 可以处理有返回值和无返回的
 * 如果 最终的异常是没有被指定的类型 会抛出传入的异常
 */
public final class CExceptionHandle<EX extends Exception, EF> {

    /**
     * 要处理的全部异常类型
     */
    private List<Class<EX>> exceptions;

    /**
     * 指定类型处理指定的逻辑(有返回值)
     */
    private List<Supplier<EF>> suppliers;

    /**
     * 指定类型处理指定的逻辑(无返回值)
     */
    private List<VoidMethod> voidMethods;

    /**
     * 异常为Exception的处理(有返回值)
     */
    private Supplier<EF> exceptionSupplier;

    /**
     * 异常为Exception的处理(无返回值)
     */
    private VoidMethod exceptionVoidMethods;

    /**
     * 标识 是否有返回值
     */
    private FlagEnum flagEnum;

    private CExceptionHandle() {
        this.exceptions = new LinkedList<>();
    }

    /**
     * 传入异常 进行处理
     * 如果 最终的异常是没有被指定的类型 会抛出传入的异常
     * @param e 一个要处理的异常
     * @return 有返回值类型的会返回数据 无返回值的会返回一个null
     */
    public EF handle(EX e) {
        int size = exceptions.size();
        Class<EX> exClass;
        if (flagEnum.equals(FlagEnum.RESULT)) {
            for (int i = 0; i < size; i++) {
                exClass = exceptions.get(i);
                if (e.getClass().equals(exClass)) {
                    return suppliers.get(i).get();
                }
            }

            if (exceptionSupplier != null) {
                return exceptionSupplier.get();
            }
            else {
                throw new RuntimeException(e);
            }
        }
        else {
            for (int i = 0; i < size; i++) {
                exClass = exceptions.get(i);
                if (e.getClass().equals(exClass)) {
                    voidMethods.get(i).method();
                    return null;
                }
            }

            if (exceptionVoidMethods != null) {
                exceptionVoidMethods.method();
                return null;
            }
            else {
                throw new RuntimeException(e);
            }
        }
    }

    /**
     * 有返回值异常初始化以及拼装
     */
    public final static class Result<EX extends Exception, EF> {

        private CExceptionHandle handle;
        private Class<Exception> clzz;

        private Result() {
            this.handle = new CExceptionHandle();
            this.handle.suppliers = new LinkedList<>();
            this.handle.flagEnum = FlagEnum.RESULT;
            this.clzz = Exception.class;
        }

        public static Result aResult() {
            return new Result();
        }

        /**
         * 拼装异常以及执行的方法
         * @param eClass 异常类型
         * @param supplier 这种类型要执行的方法
         * @return 拼装类本身
         */
        public Result add(Class<EX> eClass, Supplier<EF> supplier) {
            if (this.clzz.equals(eClass)) {
                this.handle.exceptionSupplier = supplier;
                return this;
            }
            this.handle.suppliers.add(supplier);
            this.handle.exceptions.add(eClass);
            return this;
        }

        public CExceptionHandle getHandle() {
            return this.handle;
        }
    }

    /**
     * 无返回值异常初始化以及拼装
     */
    public final static class Blank<EX extends Exception> {

        private CExceptionHandle handle;
        private Class<Exception> clzz;

        private Blank() {
            this.handle = new CExceptionHandle();
            this.handle.voidMethods = new LinkedList<>();
            this.handle.flagEnum = FlagEnum.BLANK;
            this.clzz = Exception.class;
        }

        public static Blank aBlank() {
            return new Blank();
        }

        /**
         * 拼装异常以及执行的方法
         * @param eClass 异常类型
         * @param voidMethod 这种类型要执行的方法
         * @return 拼装类本身
         */
        public Blank add(Class<EX> eClass, VoidMethod voidMethod) {
            if (this.clzz.equals(eClass)) {
                this.handle.exceptionVoidMethods = voidMethod;
                return this;
            }
            this.handle.voidMethods.add(voidMethod);
            this.handle.exceptions.add(eClass);
            return this;
        }

        public CExceptionHandle getHandle() {
            return this.handle;
        }
    }

    /**
     * 标识
     * RESULT 有返回值
     * BLANK 无返回值
     */
    private enum FlagEnum {
        RESULT,
        BLANK
    }
}
