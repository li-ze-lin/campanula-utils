package io.github.campanula.utils.proxy;

import io.github.campanula.utils.exception.CampanulaRuntimeException;
import io.github.campanula.utils.proxy.param.ProxyParam;

import java.lang.reflect.InvocationHandler;
import java.lang.reflect.Method;
import java.util.function.Consumer;

public class CInvocationHandler<T> implements InvocationHandler {

    private T t;
    private Consumer<ProxyParam> before;
    private Consumer<ProxyParam> after;

    public CInvocationHandler(T t) {
        this.t = t;
    }

    public CInvocationHandler(T t, Consumer<ProxyParam> before, Consumer<ProxyParam> after) {
        this.t = t;
        this.before = before;
        this.after = after;
    }

    @Override
    public Object invoke(Object proxy, Method method, Object[] args) {
        Object invoke = null;

        try {
            final ProxyParam proxyParam = new ProxyParam();
            if (this.before != null) this.before.accept(proxyParam);

            invoke = method.invoke(t, args);

            if (this.after != null) this.after.accept(proxyParam);
        }
        catch (Exception e) {
            throw new CampanulaRuntimeException(e);
        }

        return invoke;
    }

    public CInvocationHandler<T> setBefore(Consumer<ProxyParam> before) {
        this.before = before;
        return this;
    }

    public CInvocationHandler<T> setAfter(Consumer<ProxyParam> after) {
        this.after = after;
        return this;
    }
}
