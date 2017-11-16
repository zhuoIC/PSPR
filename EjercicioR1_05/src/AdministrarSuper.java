
public class AdministrarSuper {
	private Caja[] cajas;
	private int turno;
	private int ticket;
	boolean[] cajasLibres;
	
	public AdministrarSuper(Caja[] cajas) {
		this.cajas = cajas;
		this.turno = 1;
		this.ticket = 0;
		iniciarCajasLibres();
	}
	
	public void iniciarCajasLibres() {
		cajasLibres = new boolean[cajas.length];
		for (int i = 0; i < cajasLibres.length; i++) {
			cajasLibres[i] = true;
		}
	}
	
	public synchronized void ocuparCaja(int nCaja) {
		cajasLibres[nCaja] = false;
	}
	
	public synchronized void liberarCaja(int nCaja) {
		cajasLibres[nCaja] = true;
	}
	
	public synchronized int hayCajasLibres() {
		System.out.println(cajasLibres.length);
		for (int i = cajasLibres.length - 1; i >= 0 ; i--) {
			if(cajasLibres[i]) {
				return i;
			}
		}
		return -1;
	}
	
	public synchronized void pagarEnCaja(Cliente cliente) {
		cliente.setTurno(++ticket);
		int nCaja;
		while((nCaja = hayCajasLibres()) == -1 || turno == cliente.getTurno()) {
			try {
				wait();
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
		}
		ocuparCaja(nCaja);
		turno++;
		notifyAll();
		cajas[nCaja].pagar(cliente);
		liberarCaja(nCaja);

	}
}
