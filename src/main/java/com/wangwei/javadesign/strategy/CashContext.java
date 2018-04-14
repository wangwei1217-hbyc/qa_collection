package com.wangwei.javadesign.strategy;

import java.math.BigDecimal;

/**
 * 维护一个具体的现金算法引用
 *
 * @author Administrator
 *
 */
public class CashContext {
    private CashSuper cs;

    // 根据传入的具体下拉选的内容，初始化cs为不同的现金收费子类
    public CashContext(String type) {
        switch (type) {
            case "正常收费":
                cs = new CashNormal();
                break;
            case "打八折":
                cs = new CashRebate("0.8");
                break;
            case "满300减50":
                cs = new CashReturn("300", "50");
                break;
        }
    }
    public String getResult(String money){
        BigDecimal moneyBig = new BigDecimal(money);
        return cs.acceptCash(moneyBig).toString();
    }
}
