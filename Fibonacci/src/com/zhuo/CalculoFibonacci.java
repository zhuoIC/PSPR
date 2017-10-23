package com.zhuo;



public class CalculoFibonacci extends Thread{
	int veces;
	double suma;
	public CalculoFibonacci(int veces) {
		this.veces = veces;
	}
	
	@Override
	public void run() {
		suma = fibonacciIterativa(veces);
	}
	public static void main(String[] args) {
		int n = 10;
		long timeRecursiva;
		long timeIterativa;
		long timeThread;
		
		timeRecursiva = -System.nanoTime();
		double nRecursiva = fibonacciRecursiva(n);
		timeRecursiva += System.nanoTime();
		
		timeIterativa = -System.nanoTime();
		double nIterativa = fibonacciIterativa(n);
		timeIterativa += System.nanoTime();
		
		timeThread = -System.nanoTime();
		double nHilado = fibonacciHilado(n);
		timeThread += System.nanoTime();
		
		System.out.println("Fibonnacci recursiva = " + nRecursiva+". Tiempo: "+ timeRecursiva);
		System.out.println("Fibonnacci iterativa = " + nIterativa+". Tiempo: "+ timeIterativa);
		System.out.println("Fibonnacci hilos = " + nHilado + ". Tiempo: "+ timeThread);
		
	}
	
	public static double fibonacciRecursiva(int n) {
		double valor = 0;
		if (n < 0)
			valor = -1;
		else if (n == 0 || n == 1)
			valor = 1;
		else
			valor = fibonacciRecursiva(n-1) + fibonacciRecursiva(n-2);
		return valor;
	}
	
	public static double fibonacciIterativa(int n) {
		double valor = 0;
		double resto = 1;
		double resto2 = 1;
		if (n < 0)
			valor = -1;
		else if (n == 0 || n == 1)
			valor = 1;
		else {
			for (int i = n; i > 1; i--) {
				valor =  resto + resto2;
				resto2 = resto;
				resto = valor;
			}
		}
		return valor;
	}

	public static double fibonacciHilado(int n) {
		double suma = 0;
		if(n < 2) {
			suma = 1;
		}
		else {
			try {
				CalculoFibonacci hilo1 = new CalculoFibonacci(n -1);
				CalculoFibonacci hilo2 = new CalculoFibonacci (n -2);
				hilo1.start();
				hilo2.start();
			
				hilo1.join();
				hilo2.join();
				suma = hilo1.suma + hilo2.suma;
			} catch (InterruptedException e) {
				e.printStackTrace();
			}			
		}
		return suma;
	}
}
