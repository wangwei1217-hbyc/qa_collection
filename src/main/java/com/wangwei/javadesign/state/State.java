package com.wangwei.javadesign.state;
/**
 * 状态类
 * @author Administrator
 *
 */
public class State {
	private String value;

	public String getValue() {
		return value;
	}

	public void setValue(String value) {
		this.value = value;
	}
	public void method1(){
		System.out.println("execute method1 function!");
	}
	public void method2(){
		System.out.println("execute method2 function!");
	}
}
