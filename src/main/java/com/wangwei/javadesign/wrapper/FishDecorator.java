package com.wangwei.javadesign.wrapper;

/**
 * 具体装饰(ConcreteDecorator)角色：负责给构件对象“贴上”附加的责任。
 *      具体装饰角色“鱼儿”
 */
public class FishDecorator extends Decorator {
    @Override
    public void change() {
        super.change();
        System.out.println("拥有了幻化成鱼儿的能力：可以水中游");
    }
}
