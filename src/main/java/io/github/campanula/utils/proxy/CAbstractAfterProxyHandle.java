package io.github.campanula.utils.proxy;

import io.github.campanula.utils.cor.AbstractChain;
import io.github.campanula.utils.proxy.param.CProxyAfterParam;
import io.github.campanula.utils.proxy.param.CProxyBeforeParam;

public abstract class CAbstractAfterProxyHandle<T> extends AbstractChain<CProxyAfterParam<T>, CProxyAfterParam<T>> {


    @Override
    protected CProxyAfterParam<T> handler(CProxyAfterParam<T> inParam) {
        this.operate(inParam);
        return inParam;
    }

    protected abstract void operate(CProxyAfterParam<T> afterParam);


    public void setParam(CProxyAfterParam<T> inParam) {
        super.setInParam(inParam);
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
