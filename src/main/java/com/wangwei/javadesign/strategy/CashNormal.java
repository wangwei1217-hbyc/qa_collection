package com.wangwei.javadesign.strategy;

import java.math.BigDecimal;

/**
 * 正常收费类
 * @author Administrator
 *
 */
public class CashNormal extends CashSuper {

	@Override
	public BigDecimal acceptCash(BigDecimal money) {
		return money;
	}

}
