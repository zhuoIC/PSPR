package com.zhuo;

public class Consumidor extends Thread {
	private Buffer compartido;
	private int veces; // veces que tiene que iterar las producciones de enteros
	
	public Consumidor(Buffer buffer, int nveces) {
		this.compartido = buffer;
		veces = nveces;
	}
	@Override
	public void run() {
		int suma = 0;
		int contador = 0;
		while ( contador < veces) {
			try {
				currentThread();
				Thread.sleep((int) Math.random() * 3001);
				suma += compartido.leer();
				contador++;
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
		}
		System.out.println(getName() + "terminó de leer un total de " + suma);
	}

}
