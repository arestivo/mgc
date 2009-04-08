package com.feup.mbc.gui;

import java.awt.Button;
import java.awt.Checkbox;
import java.awt.Color;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class RotatorPanel extends FacilityPanel{
	private static final long serialVersionUID = -6864318167604013561L;

	private Button mplus;
	private Button mminus;
	private Button rplus;
	private Button rminus;
	
	private Checkbox rmsensor;
	private Checkbox msensor;
	private Checkbox rpsensor;
	
	public RotatorPanel(final int firstDigitalOut, int firstDigitalIn){
		super(firstDigitalOut, firstDigitalIn);
		setLayout(new GridLayout(1, 4));
		
		mplus = new Button("M+");
		mplus.addActionListener(new ActionListener(){
			@Override
			public void actionPerformed(ActionEvent e) {
					setDigitalOut(firstDigitalOut, !getDigitalOut(firstDigitalOut));
			}
		});

		mminus = new Button("M-");
		mminus.addActionListener(new ActionListener(){
			@Override
			public void actionPerformed(ActionEvent e) {
					setDigitalOut(firstDigitalOut + 1, !getDigitalOut(firstDigitalOut + 1));
			}
		});

		rminus = new Button("R-");
		rminus.addActionListener(new ActionListener(){
			@Override
			public void actionPerformed(ActionEvent e) {
					setDigitalOut(firstDigitalOut + 2, !getDigitalOut(firstDigitalOut + 2));
			}
		});
		
		rplus = new Button("R+");
		rplus.addActionListener(new ActionListener(){
			@Override
			public void actionPerformed(ActionEvent e) {
					setDigitalOut(firstDigitalOut + 3, !getDigitalOut(firstDigitalOut + 3));
			}
		});
	
		msensor = new Checkbox();
		rmsensor = new Checkbox();
		rpsensor = new Checkbox();
		
		add(mplus); add(mminus); add(msensor); add(rplus); add(rminus); add(rpsensor); add(rmsensor);
	}

	@Override
	public void updateColors() {
		if (getDigitalOut(firstDigitalOut)) mplus.setBackground(Color.green);
		else mplus.setBackground(Color.red);
		if (getDigitalOut(firstDigitalOut + 1)) mminus.setBackground(Color.green);
		else mminus.setBackground(Color.red);
		if (getDigitalOut(firstDigitalOut + 2)) rminus.setBackground(Color.green);
		else rminus.setBackground(Color.red);
		if (getDigitalOut(firstDigitalOut + 3)) rplus.setBackground(Color.green);
		else rplus.setBackground(Color.red);
		
		if (getDigitalIn(firstDigitalIn)) msensor.setState(true);
		else msensor.setState(false);
		if (getDigitalIn(firstDigitalIn + 1)) rmsensor.setState(true);
		else rmsensor.setState(false);
		if (getDigitalIn(firstDigitalIn + 2)) rpsensor.setState(true);
		else rpsensor.setState(false);

	}

}
