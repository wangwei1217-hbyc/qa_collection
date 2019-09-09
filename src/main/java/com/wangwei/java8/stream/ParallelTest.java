package com.wangwei.java8.stream;

import java.util.Optional;
import java.util.concurrent.ForkJoinPool;
import java.util.stream.Stream;

/**
 * Stream的parallel()并行
 * Created on 2019/9/8 0008.
 */
public class ParallelTest {
    public static void main(String[] args) {
        /**
         * (a,b) -> Integer.compare(a,b)  <-> Integer::compare
         *
         * 默认串行，就是只有一个main线程
         * parallel():并行执行-多线程
         */
        /*
        设置并行线程数。例如:此处设置5，加上main线程总共就是6个线程
        也可以在启动参数上进行设置：-Djava.util.concurrent.ForkJoinPool.common.parallelism=5
         */
        System.setProperty("java.util.concurrent.ForkJoinPool.common.parallelism","5");
        Optional<Integer> maxOpt = Stream.iterate(1, x -> x + 1).limit(200).peek(x -> {
            System.out.println(Thread.currentThread().getName());
        }).parallel().max(Integer::compare);
        System.out.println("max="+maxOpt.get());
    }
}
