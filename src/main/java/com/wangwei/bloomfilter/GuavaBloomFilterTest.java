package com.wangwei.bloomfilter;

import com.google.common.hash.BloomFilter;
import com.google.common.hash.Funnels;

import java.math.BigDecimal;
import java.math.RoundingMode;

/**
 * Google的Guava实现的布隆过滤器
 * 可参照：
 * https://www.cnblogs.com/CodeBear/p/10911177.html 大白话布隆过滤器
 * https://blog.csdn.net/yuanlaijike/article/details/94356729（详解布隆过滤器：guava实现 & redis插件实现）
 * Created by wangwei on 2020/1/14 0014.
 */
public class GuavaBloomFilterTest {
    private static int size = 1000000;//预计要插入多少数据
    private static double fpp = 0.01;//期望的误判率
    private static BloomFilter<Integer> bloomFilter = BloomFilter.create(Funnels.integerFunnel(),size,fpp);

    public static void main(String[] args) {
        long start = System.currentTimeMillis();
        //插入数据
        for(int i = 0;i < 1000000;i++){
            bloomFilter.put(i);
        }
        int count = 0;
        for(int j = 1000000;j < 2000000;j++){
            //判断是否存在
            if(bloomFilter.mightContain(j)){
                count++;
                System.out.println(j + "-布隆过滤器中实际不存在，误判了！");
            }
        }
        System.out.println(size + "个数据中总共的误判数: "+count + ",实际误判率为: "+new BigDecimal(count).divide(new BigDecimal(size),6, RoundingMode.HALF_UP));
        long end = System.currentTimeMillis();
        System.out.println("总执行时间：" + (end-start) + "毫秒(ms).");
    }
}
