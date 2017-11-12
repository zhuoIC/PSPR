
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
			System.out.println((i+1)+"ª comida del filósofo "+getNumero()+" ("+zurdo+")");
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
		boolean exitoD = false;
		boolean exitoI = false;
		
		if(zurdo) {
			exitoI = cogerPalilloI();
			exitoD = cogerPalilloD();
			if(!exitoD) {
				if(exitoI) {
					soltarPalilloI();
				}
				System.out.println("El filósofo "+getNumero()+" no tiene palillos para comer...");
				cogerPalillos();
			}
			else if(!exitoI) {
				if(exitoD) {
					soltarPalilloD();
				}
				System.out.println("El filósofo "+ getNumero() +" no tiene palillos para comer...");
				cogerPalillos();
			}
		}
		else {
			exitoD = cogerPalilloD();
			exitoI = cogerPalilloI();
			if(!exitoI) {
				if(exitoD) {
					soltarPalilloD();
				}
				System.out.println("El filósofo "+getNumero()+" no tiene palillos para comer...");
				cogerPalillos();
			}
			else if(!exitoD) {
				if(exitoI) {
					soltarPalilloI();
				}
				System.out.println("El filósofo "+ getNumero() +" no tiene palillos para comer...");
				cogerPalillos();
			}
		}

	}
	public void comer() {
		System.out.println("El filósofo " + getNumero() +" está comiendo.");
	}
	public void soltarPalillos() {
		if(zurdo) {
			soltarPalilloI();
			soltarPalilloD();
		}
		else {
			soltarPalilloD();
			soltarPalilloI();
		}
		System.out.println("El filósofo " + getNumero() +" ha dejado de comer.");
	}

	public boolean cogerPalilloD(){
			boolean flag = true;
			if(!cena.getPalillo(p_der).enUso) {
				cena.getPalillo(p_der).coger(this);
			}
			else {
				flag = false;
			}
			return flag;
		
	}
	public boolean cogerPalilloI(){
			boolean flag = true;
			if(!cena.getPalillo(p_izq).enUso) {
				cena.getPalillo(p_izq).coger(this);
			}
			else {
				flag = false;
			}
			return flag;
	}
	
	public void soltarPalilloD(){
		cena.getPalillo(p_der).soltar(this);
	}
	public void soltarPalilloI(){
		cena.getPalillo(p_izq).soltar(this);
	}
}
