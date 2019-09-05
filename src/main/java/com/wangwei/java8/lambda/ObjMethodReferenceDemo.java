package com.wangwei.java8.lambda;

import java.io.Closeable;
import java.util.function.BiConsumer;
import java.util.function.BiFunction;
import java.util.function.Consumer;
import java.util.function.Supplier;

/**
 * 对象方法引用
 * -抽象方法的第一个参数类型刚好是实例方法的类型，抽象方法剩余的参数恰好可以当做实例方法的参数。
 *  如果函数式接口的实现能由上面说的实例方法调用来实现的话，那么就可以使用对象方法引用
 *
 *
 * 第一个参数类型 最好是自定义的类型
 *
 *  语法
 *  类名::instMethod
 * Created on 2019/9/5 0005.
 */
public class ObjMethodReferenceDemo {
    /**
     * 抽象方法没有输入参数，不能使用对象方法引用
     * 比如说，如下函数式接口
     */
    public void cannotUse(){
        Runnable r = () -> {};
        Closeable c = () -> {};
        Supplier<String> s = () -> "";
    }

    public static void main(String[] args) {
        Consumer<Too> c = (too) -> new Too().foo();
        Consumer<Too> c1 = (too) -> new Too2().foo();
        Consumer<Too> cc = Too::foo; //对象方法的引用

        cc.accept(new Too());

        BiConsumer<Too2,String> bc = (too2,str) -> {new Too2().fo(str);};
        BiConsumer<Too2,String> bc1 = Too2::fo;

        BiFunction<Prod,String,Integer> bf = (prod,str) -> new Prod().fun(str);
        BiFunction<Prod,String,Integer> bf2 = (prod,str) -> new Too().fun(str); //不适用对象方法的引用
        BiFunction<Prod,String,Integer> bbf = Prod::fun;

        Execute exc = (prod,name,size) -> new Prod().run(name,size);
        Execute exc2 = Prod::run;
    }
}

interface Execute {
    public void run(Prod p, String name, String size);
}
class Too {
    public void foo() {
        System.out.println("Too foo invoke...");
    }

    public Integer fun(String s) {
        return 1;
    }
}

class Too2 {
    public void foo() {
        System.out.println("Too2 foo invoke...");
    }

    public void fo(String str) {

    }
}
class Prod {
    public void run(String name, String size) {

    }
    public Integer fun(String s) {
        return 1;
    }

}
