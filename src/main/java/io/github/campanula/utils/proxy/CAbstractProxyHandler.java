package io.github.campanula.utils.proxy;

public abstract class CAbstractProxyHandler<T> implements CProxy<T> {

    protected T t;
    protected CAbstractBeforeProxyHandle<T> before;
    protected CAbstractAfterProxyHandle<T> after;

    public CAbstractProxyHandler(T t, CAbstractBeforeProxyHandle<T> before, CAbstractAfterProxyHandle<T> after) {
        this.t = t;
        this.before = before;
        this.after = after;
    }
}
