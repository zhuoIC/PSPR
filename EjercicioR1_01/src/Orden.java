
public class Orden {

	public static void main(String[] args)  {
		int nHilos = 2;
		GestionHilos gestionHilos = new GestionHilos(nHilos);
		Hilo hilo = new Hilo(0,gestionHilos);
		Hilo hilo2 = new Hilo(1, gestionHilos);
		
		System.out.println("Se inician los hilos");
		
		hilo.start();
		hilo2.start();
		try {
			hilo.join();
			hilo2.join();			
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
	}

}
