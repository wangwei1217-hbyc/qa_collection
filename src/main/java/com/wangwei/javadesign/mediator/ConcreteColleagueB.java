package com.wangwei.javadesign.mediator;
/**
 * 具体同事类B
 * @author Administrator
 *
 */
public class ConcreteColleagueB extends Colleague{

    public ConcreteColleagueB(Mediator mediator) {
        super(mediator);
    }

    @Override
    void send(String message) {
        mediator.send(message, this);
    }

    @Override
    void notify(String message) {
        System.out.println("同事B收到消息："+message);
    }

}
