
public class HiloEscritor extends Thread{
	Buffer buffer;
	int numero;
	boolean isSynchronized;
	public HiloEscritor(Buffer buffer, boolean isSynchronized) {
		this.buffer= buffer;
		this.numero = 1;
		this.isSynchronized = isSynchronized;
	}	
	
	@Override
	public void run() {
		for (int i = 0; i < Buffer.CAPACIDAD; i++) {
			if(isSynchronized)
				buffer.escribirBufferSincronizado();
			else
				buffer.escribirBuffer();
		}
	}
}
