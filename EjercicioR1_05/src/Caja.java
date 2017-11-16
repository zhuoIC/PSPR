
public class Caja {
	private int numero;
	private Resultados resultados;
	
	public Caja(int numero, Resultados resultados) {
		this.numero = numero;
		this.resultados = resultados;
	}
	
	public void pagar(Cliente cliente) {
		System.out.println("El cliente ["+cliente.getId()
		+"] está pagando en la caja ["+numero+"].");
		resultados.addResultado(new Resultado(cliente, this));
	}
	
	public int getNumero() {
		return numero;
	}
	
}
