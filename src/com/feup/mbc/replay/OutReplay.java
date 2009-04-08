package com.feup.mbc.replay;

import com.feup.mbc.gui.ModBusClient;

public class OutReplay extends ReplayAction {

	public OutReplay(int register, int value) {
		super(register, value);
	}

	@Override
	public boolean play() {
		ModBusClient.getModBusInterface().setDigitalOut(register, value==1);
		return true;
	}
}
