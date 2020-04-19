package io.github.campanula.utils.proxy;

import io.github.campanula.utils.cor.AbstractChain;
import io.github.campanula.utils.proxy.param.CProxyBeforeParam;

/**
 * 要在代理方法执行之前进行处理的逻辑
 * 以职责链的形式进行处理
 * @param <T> 带代理的对象
 */
public abstract class CAbstractBeforeProxyHandle<T> extends AbstractChain<CProxyBeforeParam<T>, CProxyBeforeParam<T>> {

    /**
     * 重写父类方法 让子类实现operate方法即可
     * @param inParam 里面有 代理对象本身 需要代理的方法 传入的参数
     * @return 返回了下一个链
     */
    @Override
    protected CProxyBeforeParam<T> handler(CProxyBeforeParam<T> inParam) {
        this.operate(inParam);
        return inParam;
    }

    /**
     * 需要子类实现的代理方法
     * @param beforeParam 里面有 代理对象本身 需要代理的方法 传入的参数
     */
    protected abstract void operate(CProxyBeforeParam<T> beforeParam);

    /**
     * 设置代理对象的数据 不建议使用
     * @param beforeParam 里面有 代理对象本身 需要代理的方法 传入的参数
     */
    public void setParam(CProxyBeforeParam<T> beforeParam) {
        super.setInParam(beforeParam);
    }

    public CAbstractBeforeProxyHandle() {
    }

    private CAbstractBeforeProxyHandle(CProxyBeforeParam<T> inParam) {
        super(inParam);
    }

    private CAbstractBeforeProxyHandle(CProxyBeforeParam<T> inParam, boolean useExternalInParam) {
        super(inParam, useExternalInParam);
    }

}
