
public class Palillo {
	int numero;
	boolean enUso;
	
	public Palillo(int numero) {
		this.numero = numero;
		this.enUso = false;
	}
	
	public void coger() {
		this.enUso = true;
	}
	public void soltar() {
		this.enUso = false;
	}
}
