package com.wangwei.javadesign.mediator;
/**
 * 抽象中介者类
 * @author Administrator
 *
 */
public abstract class Mediator {
    /**
     *
     * @param message 发送消息的内容
     * @param colleague 发送者
     */
    public abstract void send(String message,Colleague colleague);
}
