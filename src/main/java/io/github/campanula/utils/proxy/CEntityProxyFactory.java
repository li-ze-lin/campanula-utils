package io.github.campanula.utils.proxy;

import io.github.campanula.utils.proxy.param.ProxyParam;

import java.lang.reflect.Proxy;
import java.util.List;
import java.util.function.Consumer;
import java.util.stream.Collectors;

public class CEntityProxyFactory {

    private CEntityProxyFactory() {}

    public static CEntityProxyFactory newCEntityProxyFactory() {
        return new CEntityProxyFactory();
    }

    public <T> T proxy(T entity) {
        return proxyPlus(entity, null, null);
    }

    public <T> T proxyBeforePlus(T entity, Consumer<ProxyParam> before) {
        return proxyPlus(entity, before, null);
    }

    public <T> T proxyAfterPlus(T entity, Consumer<ProxyParam> after) {
        return proxyPlus(entity, null, after);
    }

    public <T> T proxyPlus(T entity, Consumer<ProxyParam> before, Consumer<ProxyParam> after) {
        return proxyPlus(entity, before, after);
    }

    public <T> List<T> proxy(List<T> entity) {
        return proxyPlus(entity, null, null);
    }

    public <T> List<T> proxyBeforePlus(List<T> entity, Consumer<ProxyParam> before) {
        return proxyPlus(entity, before, null);
    }

    public <T> List<T> proxyAfterPlus(List<T> entity, Consumer<ProxyParam> after) {
        return proxyPlus(entity, null, after);
    }

    public <T> List<T> proxyPlus(List<T> entity, Consumer<ProxyParam> before, Consumer<ProxyParam> after) {
        if (entity == null || !entity.isEmpty()) return entity;
        return entity.stream().map(data -> this.getProxy(data, before, after)).collect(Collectors.toList());
    }

    private  <T> T getProxy(T t, Consumer<ProxyParam> before, Consumer<ProxyParam> after) {
        Class<?> aClass = t.getClass();
        CInvocationHandler<T> handler = new CInvocationHandler<T>(t);
        handler.setBefore(before).setAfter(after);
        return (T) Proxy.newProxyInstance(aClass.getClassLoader(), aClass.getInterfaces(), handler);
    }
}
