package com.zhuo;

import java.util.Random;

public class Plaza {
	private int numPlaza;
	private boolean libre;
	private Random random;
	
	public Plaza(int numPlaza) {
		this.numPlaza = numPlaza;
		this.libre = true;
		random = new Random();
	}
	
	public boolean isLibre() {
		return libre;
	}
	
	public void pedir() {
		libre = false;
	}
	
	public void dejar() {
		libre = true;
	}
	
	public void ocupar() {
		try {
			Thread.sleep((long)random.nextInt(4000));
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
	}
}
