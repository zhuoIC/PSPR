
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
	
	@Override
	public void run() {
		for (int i = 1; i <= veces; i++) {
			System.out.println(i+"ª comida del filósofo "+numero+" ("+zurdo+")");
			pensar();
			cogerPalillos();
			comer();
			soltarPalillos();
			System.out.println("Fin de la "+i+"ª comida del filósofo "+numero);
		}		
	}

	public void pensar() {
		System.out.println("El filósofo "+ numero +" está pensando...");
		try {
			Thread.sleep(400);
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
		

	}
	public void cogerPalillos() {		
		System.out.println("El filósofo "+ numero +" tiene hambre...");
		boolean exitoD = false;
		boolean exitoI = false;
		if(zurdo) {
			exitoI = cogerPalilloI();
			exitoD = cogerPalilloD();
		}
		else {
			exitoD = cogerPalilloD();
			exitoI = cogerPalilloI();
		}
		if(!exitoD) {
			if(exitoI) {
				soltarPalilloI();
			}
			System.out.println("El filósofo "+numero+" no tiene palillos para comer...");
			cogerPalillos();
		}
		else if(!exitoI) {
			if(exitoD) {
				soltarPalilloD();
				this.notifyAll();
			}
			System.out.println("El filósofo "+ numero +" no tiene palillos para comer...");
			cogerPalillos();
		}
		else{
		}
	}
	public void comer() {
		System.out.println("El filósofo " + numero +" está comiendo.");
	}
	public void soltarPalillos() {
		soltarPalilloD();
		soltarPalilloI();
		System.out.println("El filósofo " + numero +" ha dejado de comer.");
	}

	public boolean cogerPalilloD(){
			boolean flag = true;
			if(!cena.getPalillo(p_der).enUso) {
				cena.getPalillo(p_der).coger(this);
				System.out.println("El filósofo "+ numero+" ha cogido su palillo derecho ("+ cena.getPalillo(p_der).numero+")");
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
				System.out.println("El filósofo "+ numero+" ha cogido su palillo izquierdo ("+ cena.getPalillo(p_izq).numero+")");
			}
			else {
				flag = false;
			}
			return flag;
	}
	
	public void soltarPalilloD(){
		cena.getPalillo(p_der).soltar(this);
		System.out.println("El filósofo "+ numero+" ha soltado su palillo derecho ("+ cena.getPalillo(p_der).numero+")");

	}
	public void soltarPalilloI(){
		cena.getPalillo(p_izq).soltar(this);
		System.out.println("El filósofo "+ numero+" ha soltado su palillo izquierdo ("+ cena.getPalillo(p_izq).numero+")");
	}
}
