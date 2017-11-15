
public class Caja {
	private int numero;
	private boolean libre;
	private Resultados resultados;
	
	public Caja(int numero, Resultados resultados) {
		this.numero = numero;
		this.resultados = resultados;
		this.libre = true;
	}
	
	public synchronized void pagar(Cliente cliente) {
		while(!isLibre()) {
			try {
				wait();
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
		}
		setLibre(false);
		System.out.println("El cliente ["+cliente.getId()
		+"] está pagando en la caja ["+numero+"].");
		resultados.addResultado(new Resultado(cliente, this));
		setLibre(true);
		notifyAll();
	}
	
	public int getNumero() {
		return numero;
	}
	
	public synchronized boolean isLibre() {
		return libre;
	}

	public synchronized void setLibre(boolean libre) {
		this.libre = libre;
	}
}
