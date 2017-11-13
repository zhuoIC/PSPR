
public class GestionHilos {
	int nHilos;
	
	public GestionHilos(int nHilos) {
		this.nHilos = nHilos;
	}
	
	public synchronized void imprimirHilo(Hilo hilo){
		while (hilo.getNumero() < nHilos) {
			try {
				wait();
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
		}
		System.out.println(hilo.toString());
		nHilos--;
		notifyAll();
	}
}
