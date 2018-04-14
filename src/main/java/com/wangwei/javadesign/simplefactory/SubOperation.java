package com.wangwei.javadesign.simplefactory;

import java.math.BigDecimal;
/**
 * 减法运算
 * @author Administrator
 *
 */
public class SubOperation extends Operation{
	
	public SubOperation() {
		super();
	}

	public SubOperation(BigDecimal numA, BigDecimal numB) {
		super(numA, numB);
	}

	@Override
	public String getResult() {
		return numA.subtract(numB).toString();
	}

}
