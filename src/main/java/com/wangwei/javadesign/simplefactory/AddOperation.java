package com.wangwei.javadesign.simplefactory;

import java.math.BigDecimal;
import java.math.BigInteger;
import java.math.RoundingMode;

/**
 * 加法运算类
 * @author Administrator
 *
 */
public class AddOperation extends Operation {
	
	public AddOperation() {
	}

	public AddOperation(BigDecimal numA, BigDecimal numB) {
		super(numA, numB);
	}

	@Override
	public String getResult() {
		BigDecimal result = numA.add(numB);
		return result.toString();
	}
	public static void main(String[] args) {
		BigDecimal a = new BigDecimal(new BigInteger("1"));
		BigDecimal b = new BigDecimal(new BigInteger("4"));
		String str = a.divide(b, 5, RoundingMode.HALF_UP).toString();
		System.out.println(str);
		System.out.println(0d == 0.000d);
	}

}
