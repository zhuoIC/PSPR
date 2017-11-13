
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
		int p_primero, p_segundo;
		if (zurdo) {
			p_primero = this.p_izq;
			p_segundo = this.p_der;
		}
		else {
			p_primero = this.p_der;
			p_segundo = this.p_izq;
		}
		for (int i = 0; i < veces; i++) {
			System.out.println((i+1)+"ª comida del filósofo "+getNumero()+" ("+isZurdo()+")");
			pensar();
			cogerPalillos(p_primero, p_segundo);
			comer();
			soltarPalillos(p_primero, p_segundo);
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
	
	public void cogerPalillos(int p_primero, int p_segundo) {
		System.out.println("El filósofo "+ getNumero() +" tiene hambre...");
		boolean exitoS = false;
		boolean exitoP = false;
		exitoP = cogerPalillo(p_primero);
		exitoS = cogerPalillo(p_segundo);
		if(!exitoS) {
			if(exitoP) {
				soltarPalillo(p_primero);
			}
			System.out.println("El filósofo "+getNumero()+" no tiene palillos para comer...");
			cogerPalillos(p_primero, p_segundo);
		}
		else if(!exitoP) {
			if(exitoS) {
				soltarPalillo(p_segundo);
			}
			System.out.println("El filósofo "+ getNumero() +" no tiene palillos para comer...");
			cogerPalillos(p_primero, p_segundo);
		}
	}
	
	public void comer() {
		System.out.println("El filósofo " + getNumero() +" está comiendo.");
	}
	
	public void soltarPalillos(int p_primero, int p_segundo) {
		soltarPalillo(p_primero);
		soltarPalillo(p_segundo);
		System.out.println("El filósofo " + getNumero() +" ha dejado de comer.");
	}

	public boolean cogerPalillo(int palillo){
			boolean flag = true;
			if(!cena.getPalillo(palillo).isEnUso()) {
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
