package com.feup.mbc.replay;

import com.feup.mbc.gui.ModBusClient;

public class RegReplay extends ReplayAction{

	public RegReplay(int register, int value) {
		super(register, value);
	}

	@Override
	public boolean play() {
		ModBusClient.getModBusInterface().setRegister(register, value);
		return true;
	}

}
