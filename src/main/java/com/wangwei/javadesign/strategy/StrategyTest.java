package com.wangwei.javadesign.strategy;
/**
 * 策略模式：
 * 它定义了算法家族，分别封装起来，让它们之间可以相互替换，此模式让算法的变化，不会影响到使用算法的客户。
 * 该模式就是定义一系列算法的一种方法。所有这些算法完成的都是相同的工作，只是实现不同，我们可以用相同的方式调用所有的算法，
 * 减少了算法类和使用算法类之间的耦合。
 *    总结：策略模式封装了变化。
 * @author Administrator
 *
 */
public class StrategyTest {
    public static void main(String[] args) {

        String money = "500.00";
        String type1 = "打八折";
        String result1 = new CashContext(type1).getResult(money);

        String type2 = "满300减50";
        String result2 = new CashContext(type2).getResult(money);

        String type3 = "正常收费";
        String result3 = new CashContext(type3).getResult(money);
        System.out.println("正常收费："+result3);
        System.out.println(type1 + ": "+result1);
        System.out.println(type2 + ": "+result2);
    }
}

