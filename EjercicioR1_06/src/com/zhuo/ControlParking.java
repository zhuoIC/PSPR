package com.zhuo;

public class ControlParking {
	boolean entradaEnUso;
	int turno;
	public ControlParking() {
		entradaEnUso = false;
	}
	
	public void quiereEntrarVehículo(Coche coche) {
		coche.turno = turno + 1;
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
		notifyAll();
	}
	public void quiereSalirVehículo(Coche coche) {
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
