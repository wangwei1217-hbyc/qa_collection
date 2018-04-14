package com.wangwei.javadesign.simplefactory;

import java.math.BigDecimal;

/**
 * 乘法运算
 * @author Administrator
 *
 */
public class MultiplyOperation extends Operation{
	public MultiplyOperation() {
		super();
	}

	public MultiplyOperation(BigDecimal numA, BigDecimal numB) {
		super(numA, numB);
	}

	@Override
	public String getResult() {
		return numA.multiply(numB).toString();
	}

}
