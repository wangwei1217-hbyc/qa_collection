package com.wangwei.javadesign.mediator;
/**
 * 具体中介者对象，实现抽象中介者方法，并且知道所有的具体同事类
 * @author Administrator
 *
 */
public class ConcreteMediator extends Mediator{
    private ConcreteColleagueA concreteColleagueA;
    private ConcreteColleagueB concreteColleagueB;


    public ConcreteColleagueA getConcreteColleagueA() {
        return concreteColleagueA;
    }


    public void setConcreteColleagueA(ConcreteColleagueA concreteColleagueA) {
        this.concreteColleagueA = concreteColleagueA;
    }


    public ConcreteColleagueB getConcreteColleagueB() {
        return concreteColleagueB;
    }


    public void setConcreteColleagueB(ConcreteColleagueB concreteColleagueB) {
        this.concreteColleagueB = concreteColleagueB;
    }


    @Override
    public void send(String message, Colleague colleague) {
        if(colleague instanceof ConcreteColleagueA){
            System.out.println("同事A: " + message);
            concreteColleagueB.notify(message);
        }else if(colleague instanceof ConcreteColleagueB){
            System.out.println("同事B: " + message);
            concreteColleagueA.notify(message);
        }
    }



}
