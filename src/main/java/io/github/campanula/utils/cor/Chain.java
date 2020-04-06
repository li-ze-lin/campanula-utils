package io.github.campanula.utils.cor;

public interface Chain<IN, OUT> {

    /**
     * 处理
     * @return 返回下一个要处理的链
     */
    Chain<OUT, ?> handler();

    /**
     * 设置下一个链
     * PS: 使用的时 要用这个返回值在进行设置下一个链 否则会更新一个链
     * @code yes Chain.setNext(next).setNext(next).setNext(next).setNext(next);
     * @code no Chain.setNext(next); Chain.setNext(next);
     * @param next 下一个链
     * @return 返回传入链的实例
     */
    <E> Chain<OUT, E> setNext(Chain<OUT, E> next);

    /**
     * 从当前链一直处理到最后一个链
     * PS: 一定要是头链使用 否则 前面的链将被错过执行
     * @code yes Chain.setNext(next).setNext(next); Chain.execute();
     * @code no Chain.setNext(next).setNext(next).execute();
     */
    default void execute() {
        Chain<?, ?> handler = handler();
        do {
            handler = handler.handler();
        } while (handler != null);
    }
}
