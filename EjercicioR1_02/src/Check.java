
public class Check {

	public static void main(String[] args) {
		Buffer miBuffer = new Buffer();
		
		boolean isSynchronized = args[0].equals("0"); // Si es 0 se usan monitores
		
		HiloLector hiloLector = new HiloLector(miBuffer, isSynchronized);
		HiloEscritor hiloEscritor = new HiloEscritor(miBuffer, isSynchronized);
		
		System.out.println("Se inician los hilos: ");
		
		hiloLector.start();
		hiloEscritor.start();
		
		try {
			hiloLector.join();
			hiloEscritor.join();
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
		System.out.println("Se termina de escribir.");
	}

}
