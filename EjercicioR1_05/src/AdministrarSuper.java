
public class AdministrarSuper {
	private Caja[] cajas;
	private int turno;
	private int ticket;
	boolean[] cajasLibres;
	
	public AdministrarSuper(Caja[] cajas) {
		this.cajas = cajas;
		this.setTurno(1);
		this.ticket = 0;
		iniciarCajasLibres();
	}
	
	public void iniciarCajasLibres() {
		cajasLibres = new boolean[cajas.length];
		for (int i = 0; i < cajasLibres.length; i++) {
			cajasLibres[i] = true;
		}
	}
	
	public void cajaDisponible() {
		
	}
	
	public synchronized void ocuparCaja(int nCaja) {
		cajasLibres[nCaja] = false;
	}
	
	public synchronized void liberarCaja(int nCaja) {
		cajasLibres[nCaja] = true;
		notify();
	}
	
	public synchronized int hayCajasLibres() {

		return -1;
	}
	
	public synchronized void pagarEnCaja(Cliente cliente) {
		cliente.setTurno(++ticket);
		int nCaja = esperarCola(cliente);
		cajas[nCaja].pagar(cliente);
		liberarCaja(nCaja);
	}
	
	public synchronized int esperarCola(Cliente cliente) {
		int numero = -1;
		while (true) {
			for (int i = cajasLibres.length - 1; i >= 0; i--) {
				if (getTurno() == cliente.getTurno() && cajasLibres[i]) {
					numero = i;
					ocuparCaja(numero);
					setTurno(getTurno() + 1);

					break;
				}
			}
			try {
				wait();
			} catch (InterruptedException e) {
				e.printStackTrace();
			} 
		}
	}

	public int getTurno() {
		return turno;
	}

	public void setTurno(int turno) {
		this.turno = turno;
	}
}
