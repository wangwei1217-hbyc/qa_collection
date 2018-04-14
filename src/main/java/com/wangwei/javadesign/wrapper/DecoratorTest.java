package com.wangwei.javadesign.wrapper;

/**
 * 装饰模式又名包装(Wrapper)模式。
 * 装饰模式是为已有功能动态添加功能的一种方式。装饰模式以对客户端透明的方式扩展对象的功能，是继承关系的一个替代方案。
 * 装饰模式以对客户透明的方式动态地给一个对象附加上更多的责任。换言之，客户端并不会觉得对象在装饰前和装饰后有什么不同。
 * 装饰模式可以在不使用创造更多子类的情况下，将对象的功能加以扩展。
 *
 * 装饰模式把每个要装饰的功能放在单独的类中。并让这个类包装它所要装饰的对象。因此，当需要执行特殊行为时，
 * 客户代码就可以在运行时根据需要有选择的、按顺序的使用装饰功能包装对象了。
 *
 *  好处：有效的把类的核心职责和装饰功能区分开了。而且可以去除相关类中重复的装饰逻辑。
 */
public class DecoratorTest {
    public static void main(String[] args) {
        Monkey sage = new Monkey();
        FishDecorator fishDecorator = new FishDecorator();
        BirdDecorator birdDecorator = new BirdDecorator();

        //开始装饰
        fishDecorator.setSage(sage);
        birdDecorator.setSage(fishDecorator);
        birdDecorator.change();
    }
}
