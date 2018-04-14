package com.wangwei.javadesign.visitor;

public class Man implements Person{

	@Override
	public void accept(Visitor visitor) {
		visitor.getManConclusion(this);
	}
	
}
