package com.zhuo;

class DatoCompartido{
	public static int c1 = 0;
	public static int c2 = 0;
	
	public static void incrementarC1() {
		c1++;
	}
	
	public static void incrementarC2() {
		c2++;
	}

}

class HilosMutex extends Thread{
	
	
	@Override
	public void run() {
		DatoCompartido.incrementarC1();
		DatoCompartido.incrementarC2();
	}
}


public class HiloExclusionMutua {

	public static void main(String[] args) throws InterruptedException {
		int n = Integer.parseInt(args[0]);
		HilosMutex hilos[];
		System.out.println("Creando N hilos");
		hilos = new HilosMutex[n];
		for (int i = 0; i < hilos.length; i++) {
			hilos[i] = new HilosMutex();
			hilos[i].start();
		}
		
		for (int i = 0; i < hilos.length; i++) {
			hilos[i].join();
		}
		System.out.println("C1 = " +DatoCompartido.c1);
		System.out.println("C2 = " +DatoCompartido.c2);
	}

}
