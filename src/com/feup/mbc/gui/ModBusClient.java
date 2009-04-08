package com.feup.mbc.gui;

import java.awt.FlowLayout;
import java.awt.GridLayout;
import java.awt.Panel;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.Properties;
import java.util.Vector;

import javax.swing.JFrame;

import com.feup.mbc.modbus.ModBusInterface;
import com.feup.mbc.replay.Replayer;

public class ModBusClient extends Panel {
	private static final long serialVersionUID = 1252734479254579169L;
	private static ModBusInterface modbus;

	private Vector<FacilityPanel> panels = new Vector<FacilityPanel>();;

	private Replayer replayer = null;
	
	public static void main(String args[]) throws FileNotFoundException, IOException {
		JFrame frame = new JFrame("Modbus Test Client");
		
		Properties properties = new Properties();
		properties.load(new FileInputStream("plant.properties"));		

		String replayFile = null;
		if (args.length == 2 && args[0].equals("--replay")) replayFile = args[1]; 
		
		ModBusClient client = new ModBusClient();
		if (replayFile !=null) client.setReplayer(new Replayer(replayFile));
		
		int nbt = 1;
		while(true){
			if (properties.getProperty("blocktype."+nbt+".name")==null) break;
			nbt++;
		}
		
		int id = 1;
		int out = 0; int in = 0; int reg = 0;
		while(true){
			String type = properties.getProperty("facility."+id+".type");
			if (type==null) break;
			if (type.equals("conveyor")) {client.addConveyor(out, in); out+=2; in+=1;}
			if (type.equals("rotator")) {client.addRotator(out, in); out+=4; in+=3;}
			if (type.equals("machine")) {client.addMachine(out, in); out+=5; in+=2;}
			if (type.equals("warehouseout")) {client.addWarehouseOut(out, in, reg, nbt); out+=2; in+=1; reg+=1;}
			if (type.equals("warehousein")) {client.addWarehouseIn(out, in); out+=3; in+=1;}
			id++;
		}
		
		client.setLayout(new GridLayout(id - 1, 1));
		
		frame.setLayout(new FlowLayout());
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

		frame.add(client);
		frame.pack();
		frame.setVisible(true);
		
		int port = new Integer(properties.getProperty("configuration.port")).intValue();
		
		modbus = new ModBusInterface(port);

		client.start();
	}

	private void setReplayer(Replayer replayer) {
		this.replayer = replayer;
	}

	private void addWarehouseIn(int out, int in) {
		FacilityPanel panel = new WarehouseInPanel(out, in);
		add(panel);
		panels.add(panel);
	}

	private void addWarehouseOut(int out, int in, int reg, int nbt) {
		FacilityPanel panel = new WarehouseOutPanel(out, in, reg, nbt);
		add(panel);
		panels.add(panel);
	}

	private void addMachine(int out, int in) {
		FacilityPanel panel = new MachinePanel(out, in); 
		add(panel);
		panels.add(panel);
	}

	private void start() {
		new Thread(new Runnable(){
			public void run() {
				while(true) {
					try {
						Thread.sleep(100);
					} catch (InterruptedException e) {
						e.printStackTrace();
					}
					for (FacilityPanel facility : panels)
						facility.updateColors();
					if (replayer!=null) replayer.doStep();
				}
			}
		}).start();
	}

	private void addRotator(int out, int in) {;
		FacilityPanel panel = new RotatorPanel(out, in); 
		add(panel);
		panels.add(panel);
	}

	private void addConveyor(int out, int in) {
		FacilityPanel panel = new ConveyorPanel(out, in); 
		add(panel);
		panels.add(panel);
	}
	
	public static ModBusInterface getModBusInterface(){
		return modbus;
	}
}
