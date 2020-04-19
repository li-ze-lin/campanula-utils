package io.github.campanula.utils.proxy;

import io.github.campanula.utils.exception.CampanulaRuntimeException;
import io.github.campanula.utils.proxy.param.CProxyAfterParam;
import io.github.campanula.utils.proxy.param.CProxyBeforeParam;
import io.github.campanula.utils.proxy.protogenesis.CEntityProxyFactory;
import org.junit.Assert;
import org.junit.Test;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;
import java.lang.reflect.Method;

public class TestProxy {

    @Test
    public void testProxy() {
        Demo demo = new Demo();
        ArgsNullCheckBefore before = new ArgsNullCheckBefore();
        before.setNext(new PlusOneBefore());
        SubtractOneAfter after = new SubtractOneAfter();
        after.setNext(new SubtractOneAfter());

        DemoInterface demoInterface = CEntityProxyFactory.newCEntityProxyFactory().proxyPlus(demo, DemoInterface.class, before, after);
        Assert.assertThrows(CampanulaRuntimeException.class, () -> demoInterface.plusOneProxy(null));
        Assert.assertEquals(demoInterface.plusOne(null), Integer.valueOf(1));

        Assert.assertEquals(demoInterface.plusOneProxy(1), Integer.valueOf(1));
        Assert.assertEquals(demoInterface.plusOne(1), Integer.valueOf(2));


    }

    static class SubtractOneAfter extends CAbstractAfterProxyHandle<Demo> {
        @Override
        protected void operate(CProxyAfterParam<Demo> afterParam) {
            Method method = afterParam.getMethod();
            DemoProxy annotation = method.getAnnotation(DemoProxy.class);
            if (annotation == null) {
                return;
            }

            Integer result = (Integer)afterParam.getResult();
            result = result - 1;
            afterParam.setResult(result);
        }
    }

    static class ArgsNullCheckBefore extends CAbstractBeforeProxyHandle<Demo> {
        @Override
        protected void operate(CProxyBeforeParam<Demo> beforeParam) {
            Method method = beforeParam.getMethod();
            DemoProxy annotation = method.getAnnotation(DemoProxy.class);
            if (annotation == null) {
                return;
            }

            Object[] args = beforeParam.getArgs();
            int l = args.length;
            for (int i = 0; i < l; i++) {
                if (args[i] == null) {
                    throw new NullPointerException();
                }
            }

        }
    }

    static class PlusOneBefore extends CAbstractBeforeProxyHandle<Demo> {
        @Override
        protected void operate(CProxyBeforeParam<Demo> beforeParam) {
            Method method = beforeParam.getMethod();
            DemoProxy annotation = method.getAnnotation(DemoProxy.class);
            if (annotation == null) {
                return;
            }

            Object[] args = beforeParam.getArgs();
            int l = args.length;
            for (int i = 0; i < l; i++) {
                Object o = args[i];
                if (o instanceof Integer) {
                    Integer integer = (Integer)o;
                    integer += 1;
                    args[i] = integer;
                }
            }
            beforeParam.setArgs(args);
        }
    }

    public static class Demo implements DemoInterface {

        public Integer plusOneProxy(Integer integer) {
            if (integer == null) return 1;
            else return integer +=1;
        }

        public Integer plusOne(Integer integer) {
            if (integer == null) return 1;
            else return integer +=1;
        }
    }

    public interface DemoInterface {
        @DemoProxy
        Integer plusOneProxy(Integer integer);
        Integer plusOne(Integer integer);
    }

    @Target({ElementType.METHOD})
    @Retention(RetentionPolicy.RUNTIME)
    @interface DemoProxy {}
}
