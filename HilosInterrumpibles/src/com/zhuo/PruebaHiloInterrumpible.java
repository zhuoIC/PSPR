package com.zhuo;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

class HiloInterrumpible implements Runnable{

	private Thread miHilo;
	private volatile boolean vivo;
	
	public HiloInterrumpible() {
		this.miHilo = new Thread(this, "Paco paquito");
		this.vivo = true;
		miHilo.start();
	}

	public void interrumpir() {
		if (this.miHilo != null) {
			this.miHilo.interrupt();
		}
	}
	
	public void detener() {
		this.vivo = false;
	}
	
	public void esperar() throws InterruptedException{
		this.miHilo.join();

	}
	
	@Override
	public void run() {
		System.out.println("Corriendo el cuerpo del hilo...");
		while(this.vivo) {
			System.out.println("El hilo a dormir un rato");
			try {
			Thread.sleep(2000);
			}
			catch(InterruptedException e){
				System.out.println("El hilo a sido interrumpido de la siesta");
			}
		}
		System.out.println("El hilo ha sido interrumpido");
	}
	
	public boolean vive() {
		return this.vivo;
	}
}

public class PruebaHiloInterrumpible {

	public static void main(String[] args) {
		HiloInterrumpible hilo = new HiloInterrumpible();
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		String tecla;
		
		System.out.println("Indicando el hilo principal...");
		while (hilo.vive()) {
			System.out.println("Hilo vivo [i] interrumpir, [k] matar ");
			try {
				tecla = br.readLine();
				if (tecla.equals("i")) {
					hilo.interrumpir();
				}
				if (tecla.equals("k")) {
					hilo.detener();;
				}
			}
			catch(IOException e){
				e.printStackTrace();;
			}
		}
		try {
			hilo.esperar();
		}
		catch(InterruptedException e){
			e.printStackTrace();;
		}
		System.out.println("Hilo principal finalizado...");
	}

}
