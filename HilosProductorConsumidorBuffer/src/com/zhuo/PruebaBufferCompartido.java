package com.zhuo;

public class PruebaBufferCompartido {

	public static void main(String[] args) {
		Buffer bCompartido = new BufferCompartido();
		
		Productor prod = new Productor(bCompartido, 10);
		Consumidor cons1 = new Consumidor(bCompartido, 6);
		Consumidor cons2 = new Consumidor(bCompartido, 4);
		
		StringBuffer encabezado = new StringBuffer("Operación");
		encabezado.setLength(40);
		encabezado.append("Buffer   Contador ocupado");
		System.out.println(encabezado);
		System.out.println();
		
		bCompartido.monstrarEstado("Estado inicial");
		prod.start();
		cons1.start();
		cons2.start();
		try {
			prod.join();
			cons1.join();
			cons2.join();
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
		System.out.println("Hilo principal ha finalizado");

	}

}
