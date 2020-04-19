package io.github.campanula.utils.proxy;

import io.github.campanula.utils.cor.AbstractChain;
import io.github.campanula.utils.proxy.param.CProxyBeforeParam;

public abstract class CAbstractBeforeProxyHandle<T> extends AbstractChain<CProxyBeforeParam<T>, CProxyBeforeParam<T>> {


    @Override
    protected CProxyBeforeParam<T> handler(CProxyBeforeParam<T> inParam) {
        this.operate(inParam);
        return inParam;
    }

    protected abstract void operate(CProxyBeforeParam<T> beforeParam);


    void setParam(CProxyBeforeParam<T> inParam) {
        super.setInParam(inParam);
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
