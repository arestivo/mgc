package com.feup.mbc.gui;

import java.awt.Button;
import java.awt.Checkbox;
import java.awt.Color;
import java.awt.GridLayout;
import java.awt.Panel;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.ItemEvent;
import java.awt.event.ItemListener;
import java.util.Vector;

import javax.swing.JComboBox;

public class WarehouseOutPanel extends FacilityPanel {
	private static final long serialVersionUID = -5363329705900033538L;

	private Button mplus;
	private Button mminus;
	
	private Checkbox msensor;
	
	private JComboBox blockType; 
	
	public WarehouseOutPanel(final int firstDigitalOut, int firstDigitalIn, final int reg, int nbt) {
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

		Vector<Integer> items = new Vector<Integer>();
		for (int i = 0; i < nbt; i++) items.add(i);
		blockType = new JComboBox(items);
		blockType.addItemListener(new ItemListener(){
			@Override
			public void itemStateChanged(ItemEvent evt) {
				if (evt.getStateChange() == ItemEvent.DESELECTED) return;
				setRegister(reg, (Integer)blockType.getSelectedItem());
			}
		}); 
		
		add(mplus); add(mminus); add(msensor); add(blockType); add(new Panel());add(new Panel());add(new Panel());
	}

	@Override
	public void updateColors() {
		if (getDigitalOut(firstDigitalOut)) mplus.setBackground(Color.green);
		else mplus.setBackground(Color.red);
		if (getDigitalOut(firstDigitalOut + 1)) mminus.setBackground(Color.green);
		else mminus.setBackground(Color.red);
		if (getDigitalIn(firstDigitalIn)) msensor.setState(true);
		else msensor.setState(false);
		//blockType.setText(new Integer(getRegister(0)).toString());
	}
}
