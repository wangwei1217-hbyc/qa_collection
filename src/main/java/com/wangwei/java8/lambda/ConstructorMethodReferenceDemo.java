package com.wangwei.java8.lambda;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
import java.util.function.Consumer;
import java.util.function.Function;
import java.util.function.Supplier;

/**
 * 构造方法引用
 * -如果函数式接口的实现恰好可以通过调用一个类的构造方法来实现，那么就可以使用构造方法引用
 *
 *  语法
 *  类名::new
 * Created on 2019/9/6 0006.
 */
public class ConstructorMethodReferenceDemo {
    public static void main(String[] args) {
        Supplier<Person> s = () -> new Person();
        Supplier<Person> s1 = Person::new;
        System.out.println(s1.get());

        Supplier<List> s2 = ArrayList::new;
        Supplier<Thread> s3 = Thread::new;
        Supplier<Set> s4 = HashSet::new;

        Consumer<Integer> c = age -> new Account(age);
        //等价于age -> new Account(age);所以必须要有Account(int age)的构造函数
        Consumer<Integer> c1 = Account::new;
        c1.accept(34);

        Function<String,Integer> f = str -> Integer.valueOf(str);
        Function<String,Integer> f1 = Integer::valueOf;

        Function<String,Account> f2 = str -> new Account();
        //等价于str -> new Account(str);所以必须要有Account(String name)的构造函数
        Function<String,Account> f3 = Account::new;

        System.out.println(f2.apply("root"));
        System.out.println(f3.apply("admin"));

    }
}
class Account {
    public Account() {
        System.out.println("Account");
    }
    public Account(int age) {
        System.out.println("Account(age): "+age);
    }
    public Account(String name) {
        System.out.println("Account(name): "+name);
    }
}
class Person {
    public Person() {
        System.out.println("new Person()");
    }
}
