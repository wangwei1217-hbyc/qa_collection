package com.wangwei.javadesign.memento;
/**
 * 备忘录类
 *    |--属性：value(代表存储的具体值)
 * @author Administrator
 *
 */
public class Memento {
	private Object value;
	
	public Memento(Object value) {
		this.value = value;
	}

	public Object getValue() {
		return value;
	}

	public void setValue(Object value) {
		this.value = value;
	}
	
}
