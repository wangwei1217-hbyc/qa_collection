package com.wangwei.javadesign.strategy;

import java.math.BigDecimal;

/**
 * 返利收费子类
 * @author Administrator
 */
public class CashReturn extends CashSuper{
    //返利条件
    private double returnCondition = 0.00d;
    //返利金额
    private double returnMoney = 0.00d;
    public CashReturn(String returnCondition,String returnMoney) {
        this.returnCondition = Double.parseDouble(returnCondition);
        this.returnMoney = Double.parseDouble(returnMoney);
    }
    @Override
    public BigDecimal acceptCash(BigDecimal money) {
        if(money.compareTo(new BigDecimal(returnCondition)) != -1){
            return money.subtract(new BigDecimal(returnMoney));
        }
        return null;
    }

}
