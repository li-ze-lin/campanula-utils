package io.github.campanula.utils.proxy.param;

import java.lang.reflect.Method;

public class ProxyParam {

    private Object proxy;
    private Method method;
    private Object[] args;

    public ProxyParam(Object proxy, Method method, Object[] args) {
        this.proxy = proxy;
        this.method = method;
        this.args = args;
    }

    public ProxyParam() {
    }

    public Object getProxy() {
        return proxy;
    }

    public ProxyParam setProxy(Object proxy) {
        this.proxy = proxy;
        return this;
    }

    public Method getMethod() {
        return method;
    }

    public ProxyParam setMethod(Method method) {
        this.method = method;
        return this;
    }

    public Object[] getArgs() {
        return args;
    }

    public ProxyParam setArgs(Object[] args) {
        this.args = args;
        return this;
    }
}
