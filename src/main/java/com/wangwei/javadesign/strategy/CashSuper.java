package com.wangwei.javadesign.strategy;

import java.math.BigDecimal;
/**
 * 现金收费抽象类
 * @author Administrator
 *
 */
public abstract class CashSuper {
	public abstract BigDecimal acceptCash(BigDecimal money);
}
