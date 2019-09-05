package com.wangwei.java8.lambda;

import java.util.concurrent.Callable;
import java.util.function.BiFunction;
import java.util.function.Function;

/**
 * Created on 2019/9/5 0005.
 */
public class LambdaDemo {
    private static void test() throws Exception{
        Runnable r1 = new Runnable() {
            @Override
            public void run() {
                System.out.println("......run1......");
            }
        };
        r1.run();
        Runnable r2 = () -> {System.out.println("......run2......");};
        r2.run();

        Runnable r3 = () -> System.out.println("......run3......");
        r3.run();

        Callable<String> c1 = new Callable<String>() {
            @Override
            public String call() throws Exception {
                return "hello1";
            }
        };

        Callable<String> c2 = () -> {return "hello2";};
        Callable<String> c3 = () -> "hello3";

        System.out.println(c1.call());
        System.out.println(c2.call());
        System.out.println(c3.call());

        UserMapper userMapper = new UserMapper() {
            @Override
            public void insert(User user) {
                System.out.println("insert User: "+user);
            }
        };
        UserMapper userMapper2 = (User user) -> {System.out.println("insert User2: "+user);};
        UserMapper userMapper3 = (user) -> System.out.println("insert User3: "+user);

        userMapper.insert(new User());
        userMapper2.insert(new User());
        userMapper3.insert(new User());

        OrderMapper orderMapper = new OrderMapper() {
            @Override
            public int insert(Order order) {
                System.out.println("insert Order: " + order);
                return 1;
            }
        };

        OrderMapper orderMapper2 = (order) -> {return 1;};
        OrderMapper orderMapper3 = (Order order) -> {return 1;};
        OrderMapper orderMapper4 = (order) -> 1;
        System.out.println(orderMapper.insert(new Order()));;
        System.out.println(orderMapper2.insert(new Order()));;
        System.out.println(orderMapper3.insert(new Order()));;
        System.out.println(orderMapper4.insert(new Order()));;

        Function<Integer,Integer> f1 = (a) -> {
            int sum = 0;
            for(int i = 1; i <= a; i++){
                sum += i;
            }
            return sum;
        };

        System.out.println(f1.apply(10));

    }
    static void exec(){
    }
    static void fun(){
        get();
    }
    static int get(){
        return 1;
    }
    static String find(){
        return "";
    }
    public static void main(String[] args) throws Exception{
//        test();
        Runnable run1 = () -> exec();
        Runnable run2 = () -> get();
        Runnable run3 = () -> fun();
//        Runnable run4 = () -> 1;  //错误用法
//        Runnable run5 = () -> ""; //错误用法
        Foo foo = () -> get();
//        Foo foo2 = () -> fun(); //错误用法
//        Foo foo3 = () -> find(); //错误用法
        Foo foo4 = () -> 1;
        Foo foo5 = () -> true ? 1 : -1;

        BiFunction<String,String,Integer> bf = (a,b) -> a.length()+ b.length();

        System.out.println(bf.apply("java","ee"));

        BiFunction<String,String,Integer> bf2 = (a,b) -> {
            return 1;
        };
        System.out.println(bf2.apply("java","se"));

        Function<String,Integer> fun = a -> a.length();
        System.out.println(fun.apply("world"));
    }
}
interface UserMapper{
    void insert(User user);
}
interface OrderMapper {
    int insert(Order order);
}
interface Foo {
    int get();
}
class User{

}
class Order {

}
