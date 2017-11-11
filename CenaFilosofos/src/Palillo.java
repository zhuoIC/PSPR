
public class Palillo {
	int numero;
	boolean enUso;
	
	public Palillo(int numero) {
		this.numero = numero;
		this.enUso = false;
	}
	
	public void coger(Filosofo filosofo) {
		synchronized (this) {
			while (enUso) {
				try {
					System.out.println("[PRE] El filósofo "+ filosofo.numero +" quiere coger los palillos.");
					this.wait();
					System.out.println("[POST] El filósofo "+ filosofo.numero +" quiere coger los palillos.");
				} catch (InterruptedException e) {
					e.printStackTrace();
				}
			}
			this.enUso = true;
		}

	}
	public void soltar(Filosofo filosofo) {
		synchronized (this) {
			this.enUso = false;
			this.notifyAll();
		}
	}
}
