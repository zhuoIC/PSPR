
public class Filosofo implements Runnable{
	int p_izq, p_der;
	int numero;
	int veces;
	Cena cena;
	boolean zurdo;
	
	public Filosofo(int numero, int veces, Cena cena, boolean zurdo) {
		this.numero = numero;
		this.veces = veces;
		this.cena = cena;
		this.p_izq = cena.getPalilloI(numero);
		this.p_der = cena.getPalilloD(numero);
		this.zurdo = zurdo; 
	}
	public int getNumero() {
		return numero +1;
	}
	
	@Override
	public void run() {
		for (int i = 0; i < veces; i++) {
			System.out.println((i+1)+"ª comida del filósofo "+getNumero()+" ("+isZurdo()+")");
			pensar();
			cogerPalillos();
			comer();
			soltarPalillos();
			System.out.println("Fin de la "+(i+1)+"ª comida del filósofo "+getNumero());
		}		
	}

	public void pensar() {
		System.out.println("El filósofo "+ getNumero() +" está pensando...");
		try {
			Thread.sleep(400);
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
	}
	public void cogerPalillos() {		
		System.out.println("El filósofo "+ getNumero() +" tiene hambre...");
		
		if(zurdo) {
			cogerPalillos(this.p_izq, this.p_der);
		}
		else {
			cogerPalillos(this.p_der, this.p_izq);
		}

	}
	
	public void cogerPalillos(int p_primero, int p_segundo) {
		boolean exitoS = false;
		boolean exitoP = false;
		exitoP = cogerPalillo(p_primero);
		exitoS = cogerPalillo(p_segundo);
		if(!exitoS) {
			if(exitoP) {
				soltarPalillo(p_primero);
			}
			System.out.println("El filósofo "+getNumero()+" no tiene palillos para comer...");
			cogerPalillos();
		}
		else if(!exitoP) {
			if(exitoS) {
				soltarPalillo(p_segundo);
			}
			System.out.println("El filósofo "+ getNumero() +" no tiene palillos para comer...");
			cogerPalillos();
		}
	}
	
	public void comer() {
		System.out.println("El filósofo " + getNumero() +" está comiendo.");
	}
	public void soltarPalillos() {
		if(zurdo) {
			soltarPalillo(this.p_izq);
			soltarPalillo(this.p_der);
		}
		else {
			soltarPalillo(this.p_der);
			soltarPalillo(this.p_izq);
		}
		System.out.println("El filósofo " + getNumero() +" ha dejado de comer.");
	}

	public boolean cogerPalillo(int palillo){
			boolean flag = true;
			if(!cena.getPalillo(palillo).enUso) {
				cena.getPalillo(palillo).coger(this);
			}
			else {
				flag = false;
			}
			return flag;
		
	}
	
	public void soltarPalillo(int palillo){
		cena.getPalillo(palillo).soltar(this);
	}
	
	public String isZurdo() {
		return this.zurdo ? "es zurdo" : "es diestro";
	}
}
