package com.wangwei.javadesign.chainOfResponsibility;
/**
 * Abstracthandler类提供了get和set方法，方便MyHandle类设置和修改引用对象
 * @author Administrator
 *
 */
public abstract class AbstractHandler {
	private Handler handler;

	public Handler getHandler() {
		return handler;
	}

	public void setHandler(Handler handler) {
		this.handler = handler;
	}
	
}
