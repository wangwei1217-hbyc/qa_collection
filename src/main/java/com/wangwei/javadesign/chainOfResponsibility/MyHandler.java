package com.wangwei.javadesign.chainOfResponsibility;
/**
 * MyHandle类是核心，实例化后生成一系列相互持有的对象，构成一条链
 * @author Administrator
 *
 */
public class MyHandler extends AbstractHandler implements Handler{
	private String name;
	public MyHandler(String name) {
		this.name = name;
	}
	@Override
	public void operate() {
		System.out.println("MyHandler("+name+") deal!");
		if(getHandler() != null){
			getHandler().operate();
		}
	}

}
