package com.wangwei.javadesign.simplefactory;
/**
 * 运算符工厂类
 * @author Administrator
 *
 */
public class OperationFactory {
	
	public static Operation createOperation(String operate){
		Operation operation = null;
		switch(operate){
			case "+" : operation = new AddOperation();
						break;
			case "-" : operation = new SubOperation();
						break;
			case "*" : operation = new MultiplyOperation();
						break;
			case "/" : operation = new DivideOperation();
						break;
		}
		return operation;
	}
}
