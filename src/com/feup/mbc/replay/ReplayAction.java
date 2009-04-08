package com.feup.mbc.replay;

public abstract class ReplayAction{
	protected int register;
	protected int value;
	
	public ReplayAction(int register, int value) {
		this.register = register;
		this.value = value;
	}

	public void setRegister(int register) {
		this.register = register;
	}
	
	public int getRegister() {
		return register;
	}
	
	public void setValue(int value) {
		this.value = value;
	}
	
	public int getValue() {
		return value;
	}

	public abstract boolean play();
}
