package com.wangwei.java8.stream;

import org.junit.Test;

import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.Arrays;
import java.util.List;
import java.util.Optional;
import java.util.Set;
import java.util.stream.Collectors;
import java.util.stream.IntStream;
import java.util.stream.Stream;

/**
 * Stream Api:一组用来处理数组、集合的API
 * Created on 2019/9/7 0007.
 */
public class App {
    @Test
    public void gen1(){
        String[] arr = {"a","b","1","2"};
        Stream<String> stream = Stream.of(arr);
//        stream.forEach((a) -> {System.out.println(a);});
        stream.forEach(System.out::println); //实例方法引用
    }
    @Test
    public void gen2(){
        List<String> list = Arrays.asList("A","B","1","2");
        Stream<String> stream = list.stream();
        stream.forEach(System.out::println);//实例方法引用
    }
    @Test
    public void gen3(){
        //Stream.generate()不会阻塞会一直生成输出
        Stream<Integer> stream = Stream.generate(() -> 1);
        stream.limit(10).forEach(System.out::println);
    }
    @Test
    public void gen4(){
        //Stream.iterate()不会阻塞会一直执行,将上一次的输出作为下一次的输入
        Stream<Integer> stream = Stream.iterate(1, x -> x + 1);
        stream.limit(10).forEach(System.out::println);
    }
    @Test
    public void gen5() throws Exception{
        String str = "abcd";
        IntStream intStream = str.chars();
        //97 98 99 100
        intStream.forEach(System.out::println);

        Files.lines(Paths.get("E:\\Java_Study\\IDE_Workspace\\qa_collection\\src\\main\\java\\com\\wangwei\\ip\\IpConvertUtil.java"))
        .forEach(System.out::println);
    }
    @Test
    public void gen6(){
        //过滤：只要偶数
        Arrays.asList(1,2,3,4,5).stream().filter(x -> x%2==0).forEach(System.out::println);
        //先过滤再求和
        int sum = Arrays.asList(1, 2, 3, 4, 5).stream().filter(x -> x % 2 == 0).mapToInt(x -> x).sum();
        System.out.println("sum=" + sum);//6
        Optional<Integer> maxOpt = Arrays.asList(1, 2, 3, 4, 5).stream().max((a, b) -> a - b);
        System.out.println("max=" + maxOpt.get());
        Optional<Integer> minOpt = Arrays.asList(1, 2, 3, 4, 5).stream().min((a, b) -> a - b);
        System.out.println("max=" + minOpt.get());
        Optional<Integer> anyOpt = Arrays.asList(1, 2, 3, 4, 5).stream().filter(x -> x % 2 == 0).findAny();
        System.out.println("anyOpt value = "+anyOpt.get());
        Optional<Integer> firstOpt = Arrays.asList(1, 2, 3, 4, 5,6).stream().filter(x -> x % 2 == 0).findFirst();
        System.out.println("firstOpt value = "+firstOpt.get());
        firstOpt = Arrays.asList(1, 2, 3, 4, 5,6).stream().filter(x -> x % 2 == 0).sorted((a,b) -> b-a).findFirst();
        System.out.println("firstOpt value2 = "+firstOpt.get());

        //升序
        Arrays.asList(11,3,8,5,10).stream().sorted().forEach(System.out::println);
        System.out.println("======================");
        //降序
        Arrays.asList(11,3,8,5,10).stream().sorted((a,b) -> b-a).forEach(System.out::println);
        System.out.println("======================");
        Arrays.asList("cn", "admin", "net", "io").stream().
                sorted((a,b) -> a.length()-b.length()).forEach(System.out::println);
    }
    @Test
    public void gen7(){
        //从1-50里面的所有偶数找出来你，放到一个list里面
        List<Integer> list = Stream.iterate(1, x -> x + 1).limit(50).filter(x -> x % 2 == 0).collect(Collectors.toList());
        System.out.println(list);

        //去重
        Arrays.asList(1, 3, 4, 2, 2, 5, 1).stream().distinct().forEach(System.out::println);
        //把stream结果放到Set集合里，也可以达到去重的效果
        //[1, 2, 3, 4, 5]
        Set<Integer> set = Arrays.asList(1, 3, 4, 2, 2, 5, 1).stream().collect(Collectors.toSet());
        System.out.println(set);
        System.out.println("=============================");

        //利用skip(0).limit(10)实现分页效果：
        /*
        skip(0).limit(10):第一页，每页10条
        skip(10).limit(10):第二页，每页10条
        skip(20).limit(10):第三页，每页10条
         */
        List<Integer> pageList = Stream.iterate(1, x -> x + 1).limit(50).sorted((a, b) -> b - a).skip(0).limit(10)
                .collect(Collectors.toList());
        System.out.println(pageList);
        pageList = Stream.iterate(1, x -> x + 1).limit(50).sorted((a, b) -> b - a).skip(10).limit(10)
                .collect(Collectors.toList());
        System.out.println(pageList);
        pageList = Stream.iterate(1, x -> x + 1).limit(50).sorted((a, b) -> b - a).skip(20).limit(10)
                .collect(Collectors.toList());
        System.out.println(pageList);
    }

    @Test
    public void gen8(){
        //把下列字符串分割，依次转换成int，然后求和
		String str = "11,22,33,44,55";
        int sum = Stream.of(str.split(",")).mapToInt(x -> Integer.valueOf(x)).sum();
        System.out.println("sum="+sum);

        sum = Stream.of(str.split(",")).map(x -> Integer.valueOf(x)).mapToInt(x -> x).sum();
        System.out.println("sum2="+sum);

        sum = Stream.of(str.split(",")).mapToInt(Integer::valueOf).sum(); //静态方法引用
        System.out.println("sum3="+sum);

        System.out.println("======================");
        //peek类似出栈
        sum = Stream.of(str.split(",")).peek(System.out::println).mapToInt(Integer::valueOf).sum();
        System.out.println("sum4="+sum);

        System.out.println("=======================");

        String server = "tomcat,nginx,apahce,jetty";
        Stream.of(server.split(",")).map(x -> new User(x)).forEach(System.out::println);
        System.out.println("==============");
        //上述例子可改写为构造方法引用
        Stream.of(server.split(",")).map(User::new).forEach(System.out::println);
        System.out.println("==============");

        Stream.of(server.split(",")).map(x -> Person.build(x)).forEach(System.out::println);
        System.out.println("=================");
        //上述例子可改写为静态方法引用
        Stream.of(server.split(",")).map(Person::build).forEach(System.out::println);
    }
    public static void main(String[] args) {

    }
}
class User {
    private String name;
    public User(String name) {
        this.name = name;
    }
    public String getName() {
        return name;
    }
    public void setName(String name) {
        this.name = name;
    }
    @Override
    public String toString() {
        return "User [name=" + name + "]";
    }
}
class Person {
    private String name;

    public static Person build(String name) {
        Person p = new Person();
        p.setName(name);
        return p;
    }

    public String getName() {
        return name;
    }
    public void setName(String name) {
        this.name = name;
    }
    @Override
    public String toString() {
        return "Person [name=" + name + "]";
    }
}
