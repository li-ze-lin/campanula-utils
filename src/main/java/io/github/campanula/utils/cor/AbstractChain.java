package io.github.campanula.utils.cor;

public abstract class AbstractChain<IN, OUT> implements Chain<IN, OUT> {

    /**
     * 传入要要处理的数据源
     */
    protected IN inParam;
    /**
     * 是否使用构造方法传入的参数
     * 不使用上一个处理返回的数据
     * true 用外部数据
     * false 用上一个链处理后的返回值
     */
    protected boolean useExternalInParam;
    /**
     * 下一个链
     */
    private AbstractChain<OUT, ?> next;

    public AbstractChain() {
        this.useExternalInParam = false;
    }

    public AbstractChain(IN inParam) {
        this.inParam = inParam;
        this.useExternalInParam = false;
    }

    public AbstractChain(IN inParam, boolean useExternalInParam) {
        this.inParam = inParam;
        this.useExternalInParam = useExternalInParam;
    }

    @Override
    final public AbstractChain<OUT, ?> handler() {
        OUT out = handler(this.inParam);
        this.setNextInParam(out);
        return this.next;
    }

    protected abstract OUT handler(IN inParam);

    private void setNextInParam(OUT param) {
        if (this.next != null && !this.useExternalInParam) {
            this.next.inParam = param;
        }
    }

    @Override
    public Chain<?, ?> setNext(Chain<OUT, ?> next) {
        this.next = (AbstractChain<OUT, ?>) next;
        return next;
    }

}
