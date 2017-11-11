package com.zhuo;

class Bienvenida{
	private boolean comienzaLaClase;
	
	public Bienvenida() {
		this.comienzaLaClase = false;
	}
	public synchronized void saludarProfesor(String alumno) {
		System.out.println(alumno + " quiere saludar");
		while (!comienzaLaClase) {
			try {
				this.wait();
			} catch (InterruptedException e) {
				e.printStackTrace();
			}			
		}
		System.out.println(alumno + " dice buenos días");

	}
	public synchronized void profesorSaluda() {
		System.out.println("El profesor dice: ¡Buenos días!");
		this.comienzaLaClase = true;
		this.notifyAll();
		}
}

class Profesor extends Thread{
		private String nombre;
		Bienvenida saludo;
		public Profesor(String nombre, Bienvenida saludo) {
			this.nombre = nombre;
			this.saludo = saludo;
		}
		@Override
		public void run() {
			try {
				Thread.sleep(2000);
				saludo.profesorSaluda();
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
		}
}
class Alumno extends Thread{
		private String nombre;
		Bienvenida saludo;
		public Alumno(String nombre, Bienvenida saludo) {
			this.nombre = nombre;
			this.saludo = saludo;
		}
	@Override
	public void run() {
		try {
			Thread.sleep(1000);
			saludo.saludarProfesor(this.nombre);
		
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
	}
}

public class Hilo{
	public static void main(String args[]) {
		Bienvenida saludo = new Bienvenida();
		int nAlumnos = Integer.parseInt(args[0]);
		for (int i = 0; i < nAlumnos; i++) {
			new Alumno("Alumno "+ (i+1), saludo).start();
		}
		new Profesor("E.Moreno", saludo).start();
	}
}
