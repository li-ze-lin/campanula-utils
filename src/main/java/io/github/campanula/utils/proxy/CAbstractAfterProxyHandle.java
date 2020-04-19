package io.github.campanula.utils.proxy;

import io.github.campanula.utils.cor.AbstractChain;
import io.github.campanula.utils.proxy.param.CProxyAfterParam;
import io.github.campanula.utils.proxy.param.CProxyBeforeParam;

/**
 * 要在代理方法执行之后进行处理的逻辑
 * 以职责链的形式进行处理
 * @param <T> 带代理的对象
 */
public abstract class CAbstractAfterProxyHandle<T> extends AbstractChain<CProxyAfterParam<T>, CProxyAfterParam<T>> {

    /**
     * 重写父类方法 让子类实现operate方法即可
     * @param inParam 里面有 代理对象本身 需要代理的方法 传入的参数 方法执行后返回的结果
     * @return 返回了下一个链
     */
    @Override
    protected CProxyAfterParam<T> handler(CProxyAfterParam<T> inParam) {
        this.operate(inParam);
        return inParam;
    }

    /**
     * 需要子类实现的代理方法
     * @param afterParam 里面有 代理对象本身 需要代理的方法 传入的参数 方法执行后返回的结果
     */
    protected abstract void operate(CProxyAfterParam<T> afterParam);

    /**
     * 设置代理对象的数据 不建议使用
     * @param afterParam 里面有 代理对象本身 需要代理的方法 传入的参数 方法执行后返回的结果
     */
    public void setParam(CProxyAfterParam<T> afterParam) {
        super.setInParam(afterParam);
    }

    public CAbstractAfterProxyHandle() {
    }

    private CAbstractAfterProxyHandle(CProxyAfterParam<T> inParam) {
        super(inParam);
    }

    private CAbstractAfterProxyHandle(CProxyAfterParam<T> inParam, boolean useExternalInParam) {
        super(inParam, useExternalInParam);
    }

}
