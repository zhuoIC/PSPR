package zhuo;

class HiloHolaMundoExtends extends Thread{

		Thread hilo; 
		HiloHolaMundoExtends(){
		hilo = new Thread(this, "Nuevo hilo creado");
		System.out.println("Creando hilo nuevo..." + hilo);
		hilo.start();
	}
	
	public void Espera() {
		try {
			hilo.join();
		} catch(InterruptedException e) {
			e.printStackTrace();
		}
		
	}
	
	@Override
	public void run() {
		System.out.println("Hilo nuevo empezando a ejecutarse...");
		try {
			Thread.sleep(2000);
		}
		catch(InterruptedException e){
			e.printStackTrace();
		}
		System.out.println("Hilo nuevo finalizando...");
	}
	
}


public class HolaMundoExtends {

	public static void main(String[] args) {
		HiloHolaMundo hijo;
		System.out.println("Hola desde el hilo principal (main)");
		hijo = new HiloHolaMundo();
		hijo.Espera();
		System.out.println("Hilo principal (main) finalizando...");
	}

}
