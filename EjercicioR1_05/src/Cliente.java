import java.util.Random;

public class Cliente implements Runnable {
	private int identificador;
	private int turno;
	private Random random;
	private long tiempo;
	private AdministrarSuper administrarSuper;
	
	public Cliente (int identificador, AdministrarSuper administrarSuper) {
		this.identificador = identificador;
		this.administrarSuper = administrarSuper;
		this.random = new Random();
	}
	
	public void run() {
		comprar();
		administrarSuper.hacerCola(this);
		tiempo = System.currentTimeMillis();
		administrarSuper.esperarCola(this);
		administrarSuper.pagarEnCaja(this);
		tiempo = System.currentTimeMillis() - tiempo;
	}
	
	public int getId() {
		return identificador;
	}
	
	public void setTurno(int turno) {
		this.turno = turno;
	}
	
	public int getTurno() {
		return turno;
	}
	public long getTiempo() {
		return tiempo;
	}
	
	private void comprar() {
		try {
			Thread.sleep(random.nextInt(4000));
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
	}
	
}
