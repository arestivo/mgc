package com.feup.mbc.gui;

import java.awt.Panel;

public abstract class FacilityPanel extends Panel{
	private static final long serialVersionUID = -7117420947028854564L;
	
	protected int firstDigitalOut;
	protected int firstDigitalIn;
	
	public FacilityPanel(int firstDigitalOut, int firstDigitalIn) {
		this.firstDigitalOut = firstDigitalOut;
		this.firstDigitalIn = firstDigitalIn;
	}
	
	protected boolean getDigitalOut(int out) {
		return ModBusClient.getModBusInterface().getDigitalOut(out);
	}
	
	protected void setDigitalOut(int out, boolean b) {
		ModBusClient.getModBusInterface().setDigitalOut(out, b);
	}

	protected boolean getDigitalIn(int in) {
		return ModBusClient.getModBusInterface().getDigitalIn(in);
	}

	protected int getRegister(int reg) {
		return ModBusClient.getModBusInterface().getRegister(reg);
	}
	
	protected void setRegister(int reg, int value) {
		ModBusClient.getModBusInterface().setRegister(reg, value);
		
	}
	
	abstract public void updateColors();
}
