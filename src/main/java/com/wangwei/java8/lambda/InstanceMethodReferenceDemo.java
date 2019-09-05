package com.wangwei.java8.lambda;

import java.util.function.Consumer;
import java.util.function.Function;
import java.util.function.Supplier;

/**
 * 实例方法引用
 * -如果函数式接口的实现恰好可以通过调用一个实例的实例方法来实现，那么就可以使用实例方法引用
 *
 *  语法
 *  inst::instMethod
 * Created on 2019/9/5 0005.
 */
public class InstanceMethodReferenceDemo extends Base{
    public String put() {
        return "hello";
    }
    public void con(Integer size) {
        System.out.println("size : " + size);
    }
    public String toUpper(String str) {
        System.out.println("current to upper");
        return str.toUpperCase();
    }
    public void test(){
        //这种方式前提:得先创建了对象，才能用this指向
        Function<String,String> f5 = this::toUpper;
        System.out.println(f5.apply("tomcat"));

        Function<String,String> f6 = super::toUpper;
        System.out.println(f6.apply("undertow"));
    }
    public static void main(String[] args) {
        Supplier<String> s = () -> new InstanceMethodReferenceDemo().put();
        Supplier<String> s1 = () -> {return new InstanceMethodReferenceDemo().put();};
        //可替换为实例方法引用
        Supplier<String> s2 = new InstanceMethodReferenceDemo()::put;
        System.out.println(s2.get());

        System.out.println("============");

        InstanceMethodReferenceDemo inst = new InstanceMethodReferenceDemo();

        Consumer<Integer> c = (size) -> new InstanceMethodReferenceDemo().con(size);
        Consumer<Integer> c1 = new InstanceMethodReferenceDemo()::con;
        Consumer<Integer> c2 = inst::con;
        c1.accept(10);
        c2.accept(100);

        Function<String,String> f = str -> str.toUpperCase();
        Function<String,String> f1 = str -> inst.toUpper(str);
        Function<String,String> f2 = str -> new InstanceMethodReferenceDemo().toUpper(str);
        Function<String,String> f3 = inst::toUpper;
        Function<String,String> f4 = new InstanceMethodReferenceDemo()::toUpper;
        System.out.println(f3.apply("mysql"));
        System.out.println(f4.apply("oracle"));

        inst.test();

    }
}
