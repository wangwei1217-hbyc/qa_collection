package com.wangwei.java8.lambda;

import java.util.function.BiFunction;
import java.util.function.Consumer;
import java.util.function.Function;
import java.util.function.Supplier;

/**
 * 静态方法引用
 * 如果函数式接口的实现恰好是通过调用一个静态方法来实现，那么就可以使用静态方法引用
 *
 *  语法
 *  类名::staticMethod
 * Created on 2019/9/5 0005.
 */
public class StaticMethodReferenceDemo {
    public static String put() {
        System.out.println("put method invoke");
        return "put-hello";
    }
    public static void con(Integer size) {
        System.out.println("size: " + size);
    }
    public static String toUpperCase(String str) {
        return str.toUpperCase();
    }
    public static String concat(String s1, String s2) {
        StringBuilder sb = new StringBuilder();
        return sb.append(s1).append(s2).toString();
    }
    public static void main(String[] args) {
        Supplier<String> s = () -> StaticMethodReferenceDemo.put();
        Supplier<String> s1 = () -> Fun.ret();
        //上面的可以改写为静态方法引用的形式
        Supplier<String> ss = StaticMethodReferenceDemo::put;
        Supplier<String> ss1 = Fun::ret;
        System.out.println(ss.get());
        System.out.println(ss1.get());

        Consumer<Integer> c = (size) -> StaticMethodReferenceDemo.con(size);
        Consumer<Integer> c1 = StaticMethodReferenceDemo::con;
        c1.accept(5);

        System.out.println("======================");
        Function<String,String> f1 = (str) -> str.toUpperCase();
        Function<String,String> f2 = (str) -> StaticMethodReferenceDemo.toUpperCase(str);
        Function<String,String> f3 = StaticMethodReferenceDemo::toUpperCase;
        Function<String,String> f4 = Fun::toUpperCase;
        System.out.println(f3.apply("spring"));
        System.out.println("======================");

        BiFunction<String,String,String> bf = (str1,str2) -> StaticMethodReferenceDemo.concat(str1,str2);
        BiFunction<String,String,String> bf2 = StaticMethodReferenceDemo::concat;
        System.out.println(bf2.apply("Hello","World!"));
    }
}
class Fun{
    public static String ret() {
        System.out.println("ret method invoke");
        return "ret-hello";
    }
    public static String toUpperCase(String str){
        return str.toUpperCase();
    }
}
