
public class Bandera {
	private boolean libre;

	public Bandera() {
		libre = false;
	}
	
	public synchronized void coger(Hilo hilo) throws InterruptedException {
		while (!libre) {
			wait();
		}
		libre = false;
		hilo.cogerBandera();
		Thread.sleep(4000);
		libre = true;
		notifyAll();
	}
	
	public void iniciarCarrera() {
		libre = true;
	}

}
