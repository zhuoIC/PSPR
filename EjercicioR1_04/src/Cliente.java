import java.util.Random;

public class Cliente implements Runnable {
	private int identificador;
	private Caja[] cajas;
	private int turno;
	private int nCaja;
	private Random random;
	
	public Cliente (int identificador, Caja[] cajas) {
		this.identificador = identificador;
		this.cajas = cajas;
		this.random = new Random();
	}
	
	public void run() {
		comprar();
		nCaja = seleccionarCaja();
		cajas[nCaja].hacerCola(this);
		cajas[nCaja].pagar(this);
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
	
	private void comprar() {
		try {
			Thread.sleep(random.nextInt(4000));
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
	}
	
	private int seleccionarCaja() {
		return random.nextInt(cajas.length);
	}
	
}
