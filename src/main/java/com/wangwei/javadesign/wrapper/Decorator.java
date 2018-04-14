package com.wangwei.javadesign.wrapper;

/**
 * 装饰(Decorator)角色：持有一个构件(Component)对象的实例，并定义一个与抽象构件接口一致的接口。
 *  七十二变
 */
public class Decorator implements TheGreatestSage{
    //装饰的关键：持有待装饰对象的一个引用
    private TheGreatestSage sage;
    public void setSage(TheGreatestSage sage){
        this.sage = sage;
    }

    @Override
    public void change() {
        sage.change();
    }
}
