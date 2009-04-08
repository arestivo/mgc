package com.feup.mbc.gui;

import java.awt.Button;
import java.awt.Checkbox;
import java.awt.Color;
import java.awt.GridLayout;
import java.awt.Panel;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class WarehouseInPanel extends FacilityPanel {
	private static final long serialVersionUID = -5363329705900033538L;

	private Button mplus;
	private Button mminus;
	
	private Checkbox msensor;
	
	private Button in; 
	
	public WarehouseInPanel(final int firstDigitalOut, int firstDigitalIn) {
		super(firstDigitalOut, firstDigitalIn);
		
		setLayout(new GridLayout(1, 7));
		
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
		
		msensor = new Checkbox();

		in = new Button("In");
		in.addActionListener(new ActionListener(){
			@Override
			public void actionPerformed(ActionEvent e) {
				setDigitalOut(firstDigitalOut + 2, !getDigitalOut(firstDigitalOut + 2));
			}
		});
		
		add(mplus); add(mminus); add(msensor); add(in); add(new Panel());add(new Panel());add(new Panel());
	}

	@Override
	public void updateColors() {
		if (getDigitalOut(firstDigitalOut)) mplus.setBackground(Color.green);
		else mplus.setBackground(Color.red);
		if (getDigitalOut(firstDigitalOut + 1)) mminus.setBackground(Color.green);
		else mminus.setBackground(Color.red);
		if (getDigitalOut(firstDigitalOut + 2)) in.setBackground(Color.green);
		else in.setBackground(Color.red);
		if (getDigitalIn(firstDigitalIn)) msensor.setState(true);
		else msensor.setState(false);
		//blockType.setText(new Integer(getRegister(0)).toString());
	}
}
