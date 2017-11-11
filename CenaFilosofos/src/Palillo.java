
public class Palillo {
	int numero;
	boolean enUso;
	
	public Palillo(int numero) {
		this.numero = numero;
		this.enUso = false;
	}
	
	public int getNumero() {
		return numero +1;
	}
	
	public synchronized void coger(Filosofo filosofo) {
			while (enUso) {
				try {
					System.out.println("[PRE] El filósofo "+ filosofo.getNumero() +" quiere coger los palillos.");
					this.wait();
					System.out.println("[POST] El filósofo "+ filosofo.getNumero() +" quiere coger los palillos.");
				} catch (InterruptedException e) {
					e.printStackTrace();
				}
			}
			this.enUso = true;
	}
	public synchronized void soltar() {
			this.enUso = false;
			this.notifyAll();
	}
}
