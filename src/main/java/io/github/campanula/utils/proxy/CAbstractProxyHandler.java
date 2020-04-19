package io.github.campanula.utils.proxy;

/**
 * 代理抽象父类
 * @param <T> 要代理原对象类型
 */
public abstract class CAbstractProxyHandler<T> implements CProxy<T> {

    /**
     * 要代理的对象
     */
    protected T t;
    /**
     * 要在方法执行之前进行处理的逻辑
     */
    protected CAbstractBeforeProxyHandle<T> before;
    /**
     * 要在方法执行完进行处理的逻辑
     */
    protected CAbstractAfterProxyHandle<T> after;

    /**
     * @param t 要代理的对象
     * @param before 要在方法执行之前进行处理的逻辑
     * @param after 要在方法执行完进行处理的逻辑
     */
    public CAbstractProxyHandler(T t, CAbstractBeforeProxyHandle<T> before, CAbstractAfterProxyHandle<T> after) {
        this.t = t;
        this.before = before;
        this.after = after;
    }
}
