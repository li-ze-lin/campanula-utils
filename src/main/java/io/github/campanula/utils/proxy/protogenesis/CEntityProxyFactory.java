package io.github.campanula.utils.proxy.protogenesis;

import io.github.campanula.utils.exception.CampanulaRuntimeException;
import io.github.campanula.utils.proxy.CAbstractAfterProxyHandle;
import io.github.campanula.utils.proxy.CAbstractBeforeProxyHandle;

import java.lang.reflect.Proxy;
import java.util.List;
import java.util.stream.Collectors;

/**
 * 获取原生jdk代理对象工厂
 */
public class CEntityProxyFactory {

    private CEntityProxyFactory() {}

    public static CEntityProxyFactory newCEntityProxyFactory() {
        return new CEntityProxyFactory();
    }

    /**
     * 获取代理后的对象
     * @param entity 要代理的对象
     * @param entityInterfaces 代理对象实现的接口类型
     * @param <T> 要代理的对象类型
     * @param <I> 代理对象实现的接口类型
     * @return 返回传入实现接口类型代理后的对象
     * @throws CampanulaRuntimeException CampanulaRuntimeException
     */
    public <T extends I, I> I proxy(T entity, Class<I> entityInterfaces) {
        return proxyPlus(entity, entityInterfaces, null, null);
    }

    /**
     * 获取代理后的对象
     * @param entity 要代理的对象
     * @param entityInterfaces 代理对象实现的接口类型
     * @param before 要在方法执行之前进行处理的逻辑
     * @param <T> 要代理的对象类型
     * @param <I> 代理对象实现的接口类型
     * @return 返回传入实现接口类型代理后的对象
     * @throws CampanulaRuntimeException CampanulaRuntimeException
     */
    public <T extends I, I> I proxyBeforePlus(T entity, Class<I> entityInterfaces, CAbstractBeforeProxyHandle<T> before) {
        return proxyPlus(entity, entityInterfaces, before, null);
    }

    /**
     * 获取代理后的对象
     * @param entity 要代理的对象
     * @param entityInterfaces 代理对象实现的接口类型
     * @param after 要在方法执行完进行处理的逻辑
     * @param <T> 要代理的对象类型
     * @param <I> 代理对象实现的接口类型
     * @return 返回传入实现接口类型代理后的对象
     * @throws CampanulaRuntimeException CampanulaRuntimeException
     */
    public <T extends I, I> I proxyAfterPlus(T entity, Class<I> entityInterfaces, CAbstractAfterProxyHandle<T> after) {
        return proxyPlus(entity, entityInterfaces, null, after);
    }

    /**
     * 获取代理后的对象
     * @param entity 要代理的对象
     * @param entityInterfaces 代理对象实现的接口类型
     * @param before 要在方法执行之前进行处理的逻辑
     * @param after 要在方法执行完进行处理的逻辑
     * @param <T> 要代理的对象类型
     * @param <I> 代理对象实现的接口类型
     * @return 返回传入实现接口类型代理后的对象
     * @throws CampanulaRuntimeException CampanulaRuntimeException
     */
    public <T extends I, I> I proxyPlus(T entity, Class<I> entityInterfaces, CAbstractBeforeProxyHandle<T> before, CAbstractAfterProxyHandle<T> after) {
        return getProxy(entity, entityInterfaces, before, after);
    }

    /**
     * 获取代理后的对象集合
     * @param entity 要代理的对象集合
     * @param entityInterfaces 代理对象实现的接口类型
     * @param <T> 要代理的对象类型
     * @param <I> 代理对象实现的接口类型
     * @return 返回代理后的对象集合
     * @throws CampanulaRuntimeException CampanulaRuntimeException
     */
    public <T extends I, I> List<I> proxy(List<T> entity, Class<I> entityInterfaces) {
        return proxyPlusList(entity, entityInterfaces, null, null);
    }

    /**
     * 获取代理后的对象集合
     * @param entity 要代理的对象集合
     * @param entityInterfaces 代理对象实现的接口类型
     * @param before 要在方法执行之前进行处理的逻辑
     * @param <T> 要代理的对象类型
     * @param <I> 代理对象实现的接口类型
     * @return 返回代理后的对象集合
     * @throws CampanulaRuntimeException CampanulaRuntimeException
     */
    public <T extends I, I> List<I> proxyBeforePlus(List<T> entity, Class<I> entityInterfaces, CAbstractBeforeProxyHandle<T> before) {
        return proxyPlusList(entity, entityInterfaces, before, null);
    }

    /**
     * 获取代理后的对象集合
     * @param entity 要代理的对象集合
     * @param entityInterfaces 代理对象实现的接口类型
     * @param after 要在方法执行完进行处理的逻辑
     * @param <T> 要代理的对象类型
     * @param <I> 代理对象实现的接口类型
     * @return 返回代理后的对象集合
     * @throws CampanulaRuntimeException CampanulaRuntimeException
     */
    public <T extends I, I> List<I> proxyAfterPlus(List<T> entity, Class<I> entityInterfaces, CAbstractAfterProxyHandle<T> after) {
        return proxyPlusList(entity, entityInterfaces, null, after);
    }

    /**
     * 获取代理后的对象集合
     * @param entity 要代理的对象集合
     * @param entityInterfaces 代理对象实现的接口类型
     * @param before 要在方法执行之前进行处理的逻辑
     * @param after 要在方法执行完进行处理的逻辑
     * @param <T> 要代理的对象类型
     * @param <I> 代理对象实现的接口类型
     * @return 返回代理后的对象集合
     * @throws CampanulaRuntimeException CampanulaRuntimeException
     */
    public <T extends I, I> List<I> proxyPlusList(List<T> entity, final Class<I> entityInterfaces, final CAbstractBeforeProxyHandle<T> before, final CAbstractAfterProxyHandle<T> after) {
        if (entity == null || !entity.isEmpty()) throw new CampanulaRuntimeException("The proxy collection is empty or has no surrogate elements");
        return entity.stream().map(data -> this.getProxy(data, entityInterfaces, before, after)).collect(Collectors.toList());
    }

    /**
     * 获取代理后的对象
     * @param t 要代理的对象
     * @param entityInterfaces 代理对象实现的接口类型
     * @param before 要在方法执行之前进行处理的逻辑
     * @param after 要在方法执行完进行处理的逻辑
     * @param <T> 要代理的对象类型
     * @param <I> 代理对象实现的接口类型
     * @return 返回传入实现接口类型代理后的对象
     * @throws CampanulaRuntimeException CampanulaRuntimeException
     */
    private <T extends I, I> I getProxy(T t, Class<I> entityInterfaces, CAbstractBeforeProxyHandle<T> before, CAbstractAfterProxyHandle<T> after) {
        if (t == null)
            throw new CampanulaRuntimeException("The proxy object cannot be empty");
        if (entityInterfaces == null)
            throw new CampanulaRuntimeException("The proxy generic interface cannot be empty");
        Class<?> aClass = t.getClass();
        CInvocationHandler<T> handler = new CInvocationHandler(t, before, after);
        return (I) Proxy.newProxyInstance(aClass.getClassLoader(), aClass.getInterfaces(), handler);
    }
}
