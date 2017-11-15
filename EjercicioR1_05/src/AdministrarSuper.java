import java.util.Random;

public class AdministrarSuper {
	private Caja[] cajas;
	private int turno;
	private Caja caja;
	private Random random;
	
	public AdministrarSuper(Caja[] cajas) {
		this.cajas = cajas;
		this.turno = 0;
	}

	
	public synchronized void hacerCola(Cliente cliente){
		cliente.setTurno(++turno);
	}
	
	public synchronized void esperarCola(Cliente cliente) {
		while(turno != cliente.getTurno()) {
			try {
				wait();
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
		}
		
		for (Caja caja : cajas) {
			if(caja.isLibre()) {
				this.caja = caja;
				break;
			}
		}
		if (caja == null) {
			caja = cajas[random.nextInt(cajas.length)];
		}
		caja.setLibre(false);
	}
	
	public synchronized void pagarEnCaja(Cliente cliente) {
		caja.pagar(cliente);
		caja.setLibre(true);
		turno++;
		notifyAll();
	}
}
