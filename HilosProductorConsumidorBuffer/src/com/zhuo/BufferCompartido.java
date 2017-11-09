package com.zhuo;

public class BufferCompartido implements Buffer {

	private int buffer = 1;
	private int contadorOcupado = 1;
	
	@Override
	public synchronized int leer() {
		while(true)
		{
			try {
				wait();
				if(contadorOcupado == 1){
					contadorOcupado = 0;
					notifyAll();
					break;
				}
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
		}
		return 1;
	}

	@Override
	public synchronized void escribir(int valor) {
		
		contadorOcupado = 1;
	}

	@Override
	public void monstrarEstado(String cadena) {
		StringBuffer linea = new StringBuffer(cadena);
		linea.setLength(80);
		linea.append(buffer + " " + contadorOcupado);
		System.out.println(linea);
		System.out.println();
	}

}
