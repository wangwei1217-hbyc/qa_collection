package com.wangwei.javadesign.simplefactory;

import java.math.BigDecimal;

/**
 * 运算抽象类
 * @author Administrator
 *
 */
public abstract class Operation {
	protected BigDecimal numA;
	protected BigDecimal numB;
	
	public Operation() {
		
	}
	
	public Operation(BigDecimal numA,BigDecimal numB) {
		this.numA = numA;
		this.numB = numB;
	}
	
	public BigDecimal getNumA() {
		return numA;
	}
	public void setNumA(BigDecimal numA) {
		this.numA = numA;
	}
	public BigDecimal getNumB() {
		return numB;
	}
	public void setNumB(BigDecimal numB) {
		this.numB = numB;
	}
	
	public abstract String getResult();
}
