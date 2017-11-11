package com.zhuo;

public class Productor extends Thread {
	private Buffer compartido;
	private int veces; // veces que tiene que iterar las producciones de enteros
	
	public Productor(Buffer buffer, int nveces) {
		this.compartido = buffer;
		veces = nveces;
	}
	@Override
	public void run() {
		int contador = 0;
		while ( contador < veces) {
			try {
				currentThread();
				Thread.sleep((int) Math.random() * 3001);
				compartido.escribir(contador);
				contador++;
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
		}
		System.out.println(getName() + "terminó de producir datos");
	}
}
