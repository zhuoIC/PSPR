
public class Relevos {

	public static void main(String[] args) {
		final int NUMHILOS = 4;
		Hilo[] corredores = new Hilo[NUMHILOS];
		Bandera bandera = new Bandera();
		for (int i = 0; i < corredores.length; i++) {
			corredores[i] = new Hilo(i, bandera);
		}
		System.out.println("Corredores preparados");
		for (Hilo hilo : corredores) {
			hilo.start();
		}
		System.out.println("Se inicia la carrera");
		bandera.iniciarCarrera();
		for (Hilo hilo : corredores) {
			try {
				hilo.join();
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
		}
		System.out.println("Se acaba la carrera");
	}

}
