
public class HiloLector extends Thread {
	Buffer buffer;
	int numero;
	boolean isSynchronized;

	public HiloLector(Buffer buffer, boolean isSynchronized) {
		this.buffer=buffer;
		this.numero = 1;
		this.isSynchronized = isSynchronized;
	}	
	@Override
	public void run() {
		for (int i = 0; i < buffer.CAPACIDAD; i++) {
			if(isSynchronized)
				buffer.leerBufferSincronizado(this);
			else
				buffer.leerBuffer(this);
		}
	}
}
