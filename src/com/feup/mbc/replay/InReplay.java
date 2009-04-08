package com.feup.mbc.replay;

import com.feup.mbc.gui.ModBusClient;

public class InReplay extends ReplayAction {

	public InReplay(int register, int value) {
		super(register,value);
	}

	@Override
	public boolean play() {
		int newValue = ModBusClient.getModBusInterface().getDigitalIn(register)?1:0;
		return (newValue == value);
	}
}
