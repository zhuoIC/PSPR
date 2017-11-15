
public class Caja {
	private int numero;
	private int turno;
	private int nClientes;
	private Resultados resultados;
	
	public Caja(int numero, Resultados resultados) {
		this.numero = numero;
		this.turno = 1;
		this.nClientes = 0;
		this.resultados = resultados;
	}
	
	public synchronized void pagar(Cliente cliente) {
		while(turno != cliente.getTurno()) {
			try {
				wait();
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
		}
		
		System.out.println("El cliente ["+cliente.getId()
		+"] está pagando en la caja ["+numero+"].");
		resultados.addResultado(new Resultado(cliente, this));
		
		turno++;
		notifyAll();
	}
	
	public synchronized void hacerCola(Cliente cliente) {
		cliente.setTurno(++nClientes);
	}
	
	public int getNumero() {
		return numero;
	}
}
