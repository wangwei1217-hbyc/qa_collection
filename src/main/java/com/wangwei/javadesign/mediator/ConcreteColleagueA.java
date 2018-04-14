package com.wangwei.javadesign.mediator;
/**
 * 具体同事类A,不知道其他同事类，但都知道中介者对象
 * @author Administrator
 *
 */
public class ConcreteColleagueA extends Colleague {

    public ConcreteColleagueA(Mediator mediator) {
        super(mediator);
    }

    @Override
    void send(String message) {
        //发送消息时，由中介者发出此消息
        mediator.send(message, this);
    }

    @Override
    void notify(String message) {
        System.out.println("同事A收到消息："+message);
    }

}
