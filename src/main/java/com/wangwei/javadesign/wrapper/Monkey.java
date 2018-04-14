package com.wangwei.javadesign.wrapper;

/**
 * 具体构件(ConcreteComponent)角色：定义一个将要接收附加责任的类。
 *  石猴
 */
public class Monkey implements TheGreatestSage {
    @Override
    public void change() {
        System.out.println("俺是一只石猴...");
    }
}
