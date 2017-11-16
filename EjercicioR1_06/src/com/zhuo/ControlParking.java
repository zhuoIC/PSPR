package com.zhuo;

public class ControlParking {
	boolean entradaEnUso;
	int turno;
	int ticket;
	public ControlParking() {
		entradaEnUso = false;
		turno = 1;
		ticket = 0;
	}
	
	public synchronized void quiereEntrarVehículo(Coche coche) {
		coche.turno = ++ticket;
		while (entradaEnUso || turno != coche.turno) {
			try {
				wait();
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
		}
		entradaEnUso = true;
		try {
			Thread.sleep(4000);
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
		entradaEnUso = false;
		turno++;
		notifyAll();
	}
	public synchronized void quiereSalirVehículo(Coche coche) {
		while(entradaEnUso) {
			try {
				wait();
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
		}
		entradaEnUso = true;
		try {
			Thread.sleep(4000);
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
		entradaEnUso = false;
		notifyAll();

	}
}
