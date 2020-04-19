package io.github.campanula.utils.proxy.param;

import java.lang.reflect.Method;

public class CProxyBeforeParam<T> {

    private T proxy;
    private Method method;
    private Object[] args;

    public CProxyBeforeParam(T proxy, Method method, Object[] args) {
        this.proxy = proxy;
        this.method = method;
        this.args = args;
    }

    public CProxyBeforeParam() {
    }

    public T getProxy() {
        return proxy;
    }

    public Method getMethod() {
        return method;
    }

    public Object[] getArgs() {
        return args;
    }
}
