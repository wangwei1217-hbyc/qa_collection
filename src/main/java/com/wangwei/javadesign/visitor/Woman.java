package com.wangwei.javadesign.visitor;

public class Woman implements Person{

	@Override
	public void accept(Visitor visitor) {
		visitor.getWomanConclusion(this);
	}

}
