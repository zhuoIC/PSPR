
public class Buffer {
	int numero;
	StringBuffer stringBuffer;
	final static int CAPACIDAD = 10000;
	boolean lleno;

	public Buffer() {
		this.numero = 0;
		stringBuffer = new StringBuffer();
		lleno = false;
	}

	public synchronized void escribirBufferSincronizado() {
		System.out.println("Hilo escritor quiere escribir...");
		while (lleno) {
			try {
				wait();
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
		}
		stringBuffer.delete(0, stringBuffer.length());
		stringBuffer.append(String.valueOf(++numero));
		System.out.println("Hilo escritor ha terminado de escribir.");
		lleno = true;
		notify();
	}
	
	public synchronized void leerBufferSincronizado(HiloLector hiloLector) {
		System.out.println("Hilo lector está esperando para leer.");
		while (!lleno) {
			try {
				wait();
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
		}
		boolean exito = String.valueOf(stringBuffer).equals(String.valueOf(hiloLector.numero));
		System.out.print("Hilo lector está leyendo. ");
		System.out.println("Número: "+stringBuffer);
		System.out.print("Hilo lector ha terminado de leer. ");
		System.out.println(exito ? "Correcto("+hiloLector.numero+")":
			"Error("+hiloLector.numero+")");
		hiloLector.numero++;
		lleno = false;
		notify();
	}

	public void escribirBuffer() {
		while (lleno) {
			System.out.println("Hilo escritor quiere escribir...");
		}
		stringBuffer.delete(0, stringBuffer.length());
		stringBuffer.append(String.valueOf(numero++));
		System.out.println("Hilo escritor ha terminado de escribir.");
		lleno = true;

	}

	public void leerBuffer(HiloLector hiloLector) {
		while (!lleno) {
			System.out.println("Hilo lector está esperando para leer.");
		}
		boolean exito = String.valueOf(stringBuffer).equals(String.valueOf(hiloLector.numero));
		System.out.println("Número: "+stringBuffer);
		System.out.print("Hilo lector ha terminado de leer.");
		System.out.println(exito ? "Correcto.":"Error.");
		lleno = false;
	}
}
