package com.wangwei.java8.stream;

import com.wangwei.java8.stream.entity.Book;
import org.junit.Test;

import java.time.LocalDate;
import java.util.*;
import java.util.stream.Collectors;
import java.util.stream.Stream;

/**
 * Stream API实战演练
 * Created on 2019/9/8 0008.
 */
public class StreamUseTest {
    private List<Book> books(){
        List<Book> books = new ArrayList<>();
        books.add(new Book(1, "tomcat", 70d, "服务器", LocalDate.parse("2014-05-17")));
        books.add(new Book(2, "jetty", 60d, "服务器", LocalDate.parse("2015-12-01")));
        books.add(new Book(3, "nginx", 65d, "服务器", LocalDate.parse("2016-10-17")));
        books.add(new Book(4, "java", 66d, "编程语言", LocalDate.parse("2011-04-09")));
        books.add(new Book(5, "ruby", 80d, "编程语言", LocalDate.parse("2013-05-09")));
        books.add(new Book(6, "php", 40d, "编程语言", LocalDate.parse("2014-08-06")));
        books.add(new Book(7, "html", 44d, "编程语言", LocalDate.parse("2011-01-06")));
        books.add(new Book(8, "oracle", 150d, "数据库", LocalDate.parse("2013-08-09")));
        books.add(new Book(9, "mysql", 66d, "数据库", LocalDate.parse("2015-04-06")));
        books.add(new Book(10, "ssh", 70d, "编程语言", LocalDate.parse("2016-12-04")));
        books.add(new Book(11, "设计模式", 81d, "其他", LocalDate.parse("2017-04-06")));
        books.add(new Book(12, "重构", 62d, "其他", LocalDate.parse("2012-04-09")));
        books.add(new Book(13, "敏捷开发", 72d, "其他", LocalDate.parse("2016-09-07")));
        books.add(new Book(14, "从技术到管理", 42d, "其他", LocalDate.parse("2016-02-19")));
        books.add(new Book(15, "算法导论", 66d, "其他", LocalDate.parse("2010-05-08")));
        books.add(new Book(16, "oracle 12c", 150d, "数据库", LocalDate.parse("2017-05-08")));

        return books;
    }
    /**
     * index.do?itemId=1&userId=10000&type=20&token=111111111111&key=index
     * 转换成key=value的map结构存储
     */
    @Test
    public void test1(){
        String queryString = "itemId=1&userId=10000&type=20&token=123456789&key=index";
        Map<String, String> map = Stream.of(queryString.split("&")).map(x -> x.split("=")).collect(Collectors.toMap(s -> s[0], s -> s[1]));
        System.out.println(map);
    }

    @Test
    public void test2(){
        List<Integer> ids = books().stream().map(book -> book.getId()).collect(Collectors.toList());
        System.out.println(ids);

        //改写为对象方法引用
        ids = books().stream().map(Book::getId).collect(Collectors.toList());
        System.out.println(ids);

        //结果：1,2,3,4,5,6,7,8,9,10,11,12,13,14,15,16
        String str = books().stream().map(book -> book.getId() + "").collect(Collectors.joining(","));
        System.out.println(str);

        //结果：(1,2,3,4,5,6,7,8,9,10,11,12,13,14,15,16)
        str = books().stream().map(book -> book.getId() + "").collect(Collectors.joining(",","(",")"));
        System.out.println(str);

        //结果：('1','2','3','4','5','6','7','8','9','10','11','12','13','14','15','16')
        str = books().stream().map(book -> "'"+book.getId() + "'").collect(Collectors.joining(",","(",")"));
        System.out.println(str);
    }

    @Test
    public void test3(){
        //结果：[服务器, 服务器, 服务器, 编程语言, 编程语言, 编程语言, 编程语言, 数据库, 数据库, 编程语言, 其他, 其他, 其他, 其他, 其他, 数据库]
        List<String> typeList = books().stream().map(Book::getType).collect(Collectors.toList());
        System.out.println(typeList);

        //去重:方案一
        //[服务器, 编程语言, 数据库, 其他]
        typeList = books().stream().map(Book::getType).distinct().collect(Collectors.toList());
        System.out.println(typeList);

        //去重:方案二--Set集合中不会有重复的元素
        //[编程语言, 服务器, 其他, 数据库]
        Set<String> typeSet = books().stream().map(Book::getType).collect(Collectors.toSet());
        System.out.println(typeSet);
    }
    @Test
    public void test4(){
//        books().stream().sorted((boo1,boo2) -> Double.compare(boo1.getPrice(),boo2.getPrice())).forEach(System.out::println);
//        System.out.println("===================================================");
//        books().stream().sorted(Comparator.comparingDouble(book -> book.getPrice())).forEach(System.out::println);

        //按价格降序
        Comparator<Book> compa = (boo1,boo2) -> Double.compare(boo1.getPrice(),boo2.getPrice());
//        books().stream().sorted(compa.reversed()).forEach(System.out::println);
        //先按价格升序排列，再按出版日期降序排列
//        books().stream().sorted(compa.thenComparing((book1,book2) -> book1.getPublishDate().isAfter(book2.getPublishDate()) ? -1:1)).forEach(System.out::println);

        //按价格升序
//        books().stream().sorted(Comparator.comparing(book -> book.getPrice())).forEach(System.out::println);
        //上述可改写为对象方法引用
//        books().stream().sorted(Comparator.comparing(Book::getPrice)).forEach(System.out::println);
//        books().stream().sorted(Comparator.comparing(Book::getPrice).reversed()).forEach(System.out::println);

        //现按价格降序，再按出版日期降序
        books().stream().sorted(Comparator.comparing(Book::getPrice).reversed().thenComparing(Comparator.comparing(Book::getPublishDate).reversed())).forEach(System.out::println);
    }

    @Test
    public void test5(){
        /**
         * key-Id,value-Book对象
         */
//        Map<Integer, Book> bookMap = books().stream().collect(Collectors.toMap(book -> book.getId(), book -> book));
//        System.out.println(bookMap);

        //可改写为对象放引用
        Map<Integer, Book> bookMap = books().stream().collect(Collectors.toMap(Book::getId, book -> book));
        System.out.println(bookMap);
    }

    @Test
    public void test6(){
        //求所有书的价格平均数
        Double avg = books().stream().collect(Collectors.averagingDouble(Book::getPrice));
        System.out.println(avg);
    }

    @Test
    public void test7(){
        //找出所有书中价格最贵的那本书
        Optional<Book> maxBook = books().stream().collect(Collectors.maxBy(Comparator.comparing(Book::getPrice)));
        System.out.println(maxBook.get());

        //找出所有书中价格最便宜的那本书
        Optional<Book> minBook = books().stream().collect(Collectors.minBy(Comparator.comparing(Book::getPrice)));
        System.out.println(minBook.get());

        //找出所有书中出版日期最早的那本书
        Optional<Book> minDateBook = books().stream().collect(Collectors.minBy(Comparator.comparing(Book::getPublishDate)));
        System.out.println(minDateBook.get());

        //找出所有书中出版日期最近的那本书
        Optional<Book> maxDateBook = books().stream().collect(Collectors.maxBy(Comparator.comparing(Book::getPublishDate)));
        System.out.println(maxDateBook.get());

        //找出所有书中价格最贵出版日期最早的那本书
        Comparator<Book> comp = Comparator.comparing(Book::getPrice);
        Optional<Book> book = books().stream().collect(Collectors.maxBy(comp.thenComparing(Comparator.comparing(Book::getPublishDate).reversed())));
        System.out.println(book.get());
    }

    @Test
    public void test8(){
        //以类型进行分组
        Map<String, List<Book>> listMap = books().stream().collect(Collectors.groupingBy(Book::getType));
        listMap.keySet().forEach(key -> {
            System.out.println(key+"="+listMap.get(key));
            System.out.println("------------------------------");
        });

        System.out.println("===============================");
        //以类型进行分组,并统计各个分组书的数量
        Map<String, Long> typeCountMap = books().stream().collect(Collectors.groupingBy(Book::getType, Collectors.counting()));
        System.out.println(typeCountMap);

        //以类型进行分组,并统计各个分组书的总价格
        Map<String, Double> typePriceSumMap = books().stream().collect(Collectors.groupingBy(Book::getType, Collectors.summingDouble(Book::getPrice)));
        System.out.println(typePriceSumMap);

        //以类型进行分组,并统计各个分组书的平均价格
        Map<String, Double> typePriceAvgMap = books().stream().collect(Collectors.groupingBy(Book::getType, Collectors.averagingDouble(Book::getPrice)));
        System.out.println(typePriceAvgMap);

        //以类型进行分组,并统计各个分组中价格最贵的那本书
        Map<String, Optional<Book>> typeHignPriceBookMap = books().stream().collect(Collectors.groupingBy(Book::getType, Collectors.maxBy(Comparator.comparing(Book::getPrice))));
        System.out.println(typeHignPriceBookMap);

        //以类型进行分组,并统计各个分组中价格最便宜的那本书
        Map<String, Optional<Book>> typeDownPriceBookMap = books().stream().collect(Collectors.groupingBy(Book::getType, Collectors.minBy(Comparator.comparing(Book::getPrice))));
        System.out.println(typeDownPriceBookMap);

        //以类型进行分组,并统计各个分组中出版日期最新的那本书
        Map<String, Optional<Book>> typeNewPublishDateBookMap = books().stream().collect(Collectors.groupingBy(Book::getType, Collectors.maxBy(Comparator.comparing(Book::getPublishDate))));
        System.out.println(typeNewPublishDateBookMap);
    }

    @Test
    public void test9(){
        //首先过滤得到价格大于等于80的书，然后将这些书按照出版日期由近到远进行排序，然后输出
        books().stream().filter(book -> book.getPrice() >=80).sorted(Comparator.comparing(Book::getPublishDate).reversed()).forEach(System.out::println);
    }
}
