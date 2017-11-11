
public class Palillo {
	int numero;
	boolean enUso;
	
	public Palillo(int numero) {
		this.numero = numero;
		this.enUso = false;
	}
	
	public synchronized void coger(Filosofo filosofo) {
		while (enUso) {
			try {
				System.out.println("[PRE] El filósofo "+ filosofo.numero +" quiere coger los palillos.");
				wait();
				System.out.println("[POST] El filósofo "+ filosofo.numero +" quiere coger los palillos.");
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
		}
		
		
		this.enUso = true;
	}
	public void soltar() {
		this.enUso = false;
		notifyAll();
	}
}
