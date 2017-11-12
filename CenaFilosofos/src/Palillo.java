
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
			if(this.getNumero() == filosofo.getNumero())
				System.out.println("El filósofo "+ filosofo.getNumero()+" ha cogido su palillo derecho ("+ this.getNumero()+")");
			else
				System.out.println("El filósofo "+ filosofo.getNumero()+" ha cogido su palillo izquierdo ("+ this.getNumero()+")");
	}
	public synchronized void soltar(Filosofo filosofo) {
			this.enUso = false;
			if(this.getNumero() == filosofo.getNumero())
				System.out.println("El filósofo "+ filosofo.getNumero()+" ha soltado su palillo derecho ("+ this.getNumero()+")");
			else
				System.out.println("El filósofo "+ filosofo.getNumero()+" ha soltado su palillo izquierdo ("+ this.getNumero()+")");

			this.notifyAll();
	}
}
