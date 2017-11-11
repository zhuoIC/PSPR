import java.util.Random;

public class SimulacionCena {

	public static void main(String[] args) {
		int comensales = Integer.parseInt(args[0]);
		int almuerzos = Integer.parseInt(args[1]);
		Thread[] hilos = new Thread[comensales];
		Cena cena = new Cena(comensales);

		for (int i = 0; i < hilos.length; i++) {
			hilos[i] = new Thread(new Filosofo(i+1, almuerzos, cena, (new Random()).nextBoolean()));
		}
		System.out.println("Número de filósofos: "+comensales);
		System.out.println("Número de comidas: "+almuerzos);
		System.out.println("La cena empieza");
		for (Thread thread : hilos) {
			thread.start();
		}
		for (Thread thread : hilos) {
			try {
				thread.join();
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
		}
		System.out.println("La cena ha acabado");
	}

}
