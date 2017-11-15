
public class Hilo extends Thread {
	int numero;
	Bandera bandera;
	public Hilo(int numero, Bandera bandera) {
		this.numero = numero;
		this.bandera = bandera;
	}
	
	@Override
	public void run() {
		try {
			bandera.coger(this);
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
	}
	
	public void cogerBandera() {
		System.out.println("El hilo ["+numero+"] ha cogido la bandera.");
	}
}
