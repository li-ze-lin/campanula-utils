package io.github.campanula.utils.proxy.param;

import java.lang.reflect.Method;

public class CProxyAfterParam<T> {

    private T proxy;
    private Method method;
    private Object[] args;
    private Object result;

    public CProxyAfterParam(T proxy, Method method, Object[] args, Object result) {
        this.proxy = proxy;
        this.method = method;
        this.args = args;
        this.result = result;
    }

    public CProxyAfterParam() {
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

    public Object getResult() {
        return result;
    }

    public void setProxy(T proxy) {
        this.proxy = proxy;
    }

    public void setMethod(Method method) {
        this.method = method;
    }

    public void setArgs(Object[] args) {
        this.args = args;
    }

    public void setResult(Object result) {
        this.result = result;
    }
}
