import java.util.Random;

public class Resultados {
	Resultado[] resultado;
	int nResultados;
	public Resultados(int clientes) {
		resultado = new Resultado[clientes];
	}
	
	public void addResultado(Resultado resultado) {
		this.resultado[nResultados++] = resultado;
	}
	
	public void imprimirResultados() {
		long tiempo = 0;
		System.out.println();
		System.out.println("Resultado del ejercicio");
		System.out.println("------------------------------");
		for (Resultado resultado : this.resultado) {
			System.out.println(resultado.toString());
			tiempo += resultado.getCliente().getTiempo();
		}
		System.out.println();
		System.out.println("Fin de los resultados. Tiempo medio: "
		+ tiempo / resultado.length+ " milisegundo(s).");
	}
}
class Resultado {
	private Cliente cliente;
	private Caja caja;
	private float precio;
	private static final Random random = new Random();
	
	public Resultado (Cliente cliente, Caja caja) {
		this.cliente = cliente;
		this.caja = caja;
		this.precio = random.nextFloat() * 50;
	}
	
	@Override
	public String toString() {
		return "El cliente ["+cliente.getId()+
				"] con turno ["+cliente.getTurno()+
				"] ha pagado en la caja ["+caja.getNumero()+
				"] "+ String.format("%.2f", precio) +"€ "+
				"Tiempo empleado: "+cliente.getTiempo()+" milisegundo(s).";
	}
	
	public Cliente getCliente() {
		return cliente;
	}
}
