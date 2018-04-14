package com.wangwei.javadesign.mediator;
/**
 * 抽象同事类
 * @author Administrator
 *
 */
public abstract class Colleague {
    //同事都知道中介者
    protected Mediator mediator;

    public Colleague(Mediator mediator) {
        this.mediator = mediator;
    }
    //发送消息
    abstract void send(String message);

    //响应消息
    abstract void notify(String message);

}
