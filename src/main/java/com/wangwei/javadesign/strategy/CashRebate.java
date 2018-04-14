package com.wangwei.javadesign.strategy;


import java.math.BigDecimal;
import java.math.MathContext;
import java.math.RoundingMode;

/**
 * 打折收费子类
 * @author Administrator
 *
 */
public class CashRebate extends CashSuper{
    private double moneyRebate = 0.00d;//打折率
    public CashRebate(String moneyRebate) {
        this.moneyRebate = Double.parseDouble(moneyRebate);//初始化必须传入费率
    }
    @Override
    public BigDecimal acceptCash(BigDecimal money) {
        return money.multiply(new BigDecimal(moneyRebate),new MathContext(5, RoundingMode.HALF_UP));
    }

}
