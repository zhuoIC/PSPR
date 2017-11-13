
public class Hilo extends Thread{
	private int numero;
	private GestionHilos gestionHilos;
	public Hilo(int numero, GestionHilos gestionHilos) {
		this.numero = numero;
		this.gestionHilos = gestionHilos;
	}
	
	@Override
	public void run() {
		gestionHilos.imprimirHilo(this);
	}
	
	public int getNumero() {
		return numero + 1;
	}
	
	@Override
	public String toString() {
		return "Hola, soy el hilo " + getNumero();
	}
}
