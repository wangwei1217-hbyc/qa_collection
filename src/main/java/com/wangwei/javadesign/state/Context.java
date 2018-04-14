package com.wangwei.javadesign.state;
/**
 * 状态切换类
 * @author Administrator
 *
 */
public class Context {
	private State state;

	public Context(State state) {
		this.state = state;
	}
	public State getState() {
		return state;
	}

	public void setState(State state) {
		this.state = state;
	}
	
	public void method(){
		if("A".equalsIgnoreCase(state.getValue())){
			state.method1();
		}else if("B".equalsIgnoreCase(state.getValue())){
			state.method2();
		}
	}
}
