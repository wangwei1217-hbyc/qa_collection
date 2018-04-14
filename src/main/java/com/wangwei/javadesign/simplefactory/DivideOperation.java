package com.wangwei.javadesign.simplefactory;

import java.math.BigDecimal;
import java.math.RoundingMode;

public class DivideOperation extends Operation{
	public DivideOperation() {
		super();
	}

	public DivideOperation(BigDecimal numA, BigDecimal numB) {
		super(numA, numB);
	}

	@Override
	public String getResult() {
		if(numB.doubleValue() == 0d){
			throw new IllegalArgumentException("��������Ϊ0��");
		}
		return numA.divide(numB, 2, RoundingMode.HALF_UP).toString();
	}

}
