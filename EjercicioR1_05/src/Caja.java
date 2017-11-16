
public class Caja extends Thread {
	private int numero;
	private Resultados resultados;
	private AdministrarSuper administrarSuper;
	private int nClientes;
	
	public Caja(int numero, Resultados resultados, AdministrarSuper administrarSuper, int nClientes) {
		this.numero = numero;
		this.resultados = resultados;
		this.administrarSuper = administrarSuper;
		this.nClientes = nClientes;
	}
	
	public void pagar(Cliente cliente) {
		System.out.println("El cliente ["+cliente.getId()
		+"] está pagando en la caja ["+numero+"].");
		resultados.addResultado(new Resultado(cliente, this));
		notify();
	}
	
	public int getNumero() {
		return numero;
	}
	
	@Override
	public void run() {
		while(administrarSuper.getTurno() <= nClientes){
			try {
				wait();
			} catch (InterruptedException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			
		}
	}
}
