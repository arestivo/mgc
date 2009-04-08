package com.feup.mbc.replay;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.Scanner;

public class Replayer {
	ArrayList<ReplayAction> actions = new ArrayList<ReplayAction>();
	int currentAction = 0;
	
	public Replayer(String file) throws FileNotFoundException {
		System.out.println("Replaying from: " + file);
		Scanner scanner = new Scanner(new FileInputStream(file));
		while (scanner.hasNext()) {
			String type = scanner.next();
			int reg = scanner.nextInt();
			int value = scanner.nextInt();
			if (type.equals("IN")) actions.add(new InReplay(reg, value));
			if (type.equals("OUT")) actions.add(new OutReplay(reg, value));
			if (type.equals("REG")) actions.add(new RegReplay(reg, value));
		}
	}

	public void doStep() {
		if (currentAction >= actions.size()) return;
		if (actions.get(currentAction).play()) currentAction++;
	}

}
