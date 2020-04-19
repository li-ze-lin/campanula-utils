package io.github.campanula.utils.proxy;

import io.github.campanula.utils.exception.CampanulaRuntimeException;
import io.github.campanula.utils.proxy.param.CProxyAfterParam;
import io.github.campanula.utils.proxy.param.CProxyBeforeParam;

import java.lang.reflect.InvocationHandler;
import java.lang.reflect.Method;

public class CInvocationHandler<T> implements InvocationHandler {

    private T t;
    private CAbstractBeforeProxyHandle<T> before;
    private CAbstractAfterProxyHandle<T> after;

    public CInvocationHandler(T t, CAbstractBeforeProxyHandle<T> before, CAbstractAfterProxyHandle<T> after) {
        this.t = t;
        this.before = before;
        this.after = after;
    }

    @Override
    public Object invoke(Object proxy, Method method, Object[] args) {
        Object invoke = null;

        try {

            if (this.before != null) {
                this.before.setParam(new CProxyBeforeParam<T>((T) proxy, method, args));
                this.before.execute();
            }

            invoke = method.invoke(t, args);

            if (this.after != null) {
                this.after.setParam(new CProxyAfterParam<T>((T) proxy, method, args, invoke));
                this.after.execute();
            }

        }
        catch (Exception e) {
            throw new CampanulaRuntimeException(e);
        }

        return invoke;
    }

}
