
public class Filosofo implements Runnable{
	int p_izq, p_der;
	int numero;
	int veces;
	Cena cena;
	
	public Filosofo(int numero, int veces, Cena cena) {
		this.numero = numero;
		this.veces = veces;
		this.cena = cena;
		this.p_izq = cena.getPalilloI(numero);
		this.p_der = cena.getPalilloD(numero);
	}
	
	
	@Override
	public void run() {
		for (int i = 0; i < veces; i++) {
			pensar();
			cogerPalillos();
			comer();
			soltarPalillos();
		}		
	}

	public void pensar() {
		System.out.println("El filósofo "+ numero +" está pensando...");

	}
	public void cogerPalillos() {
		Palillo pI = cena.getPalillo(p_izq);
		Palillo pD = cena.getPalillo(p_der);
		while (pI.enUso || pD.enUso) {
			try {
				wait();
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
		}
	}
	public void comer() {
		System.out.println("El filósofo" + numero +" está comiendo.");
	}
	public void soltarPalillos() {
		System.out.println("El filósofo" + numero +" ha dejado de comer.");
		cena.getPalillo(p_izq).soltar();
		cena.getPalillo(p_der).soltar();
	}

}
