package com.wangwei.javadesign.command;
/**
 * 命令发出者(相当于司令员)
 * @author Administrator
 *
 */
public class Invoker {
	private Command command;
	public Invoker(Command command) {
		this.command = command;
	}
	
	public void action(){
		command.exe();
	}
	
}
