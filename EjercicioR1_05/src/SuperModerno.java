
public class SuperModerno {

	public static void main(String[] args) {
		int nClientes = Integer.parseInt(args[0]);
		int nCajas = Integer.parseInt(args[1]);
		Caja[] cajas = new Caja[nCajas];
		Thread[] clientes = new Thread[nClientes];
		Resultados resultados = new Resultados(nClientes);
		
		
		for (int i = 0; i < cajas.length; i++) {
			cajas[i] = new Caja(i, resultados);
		}
		
		AdministrarSuper administrarSuper = new AdministrarSuper(cajas);
		
		for (int i = 0; i < clientes.length; i++) {
			clientes[i] = new Thread(new Cliente(i, administrarSuper));
		}
		
		
		for (Thread cliente : clientes) {
			cliente.start();
		}
		
		for (Thread cliente : clientes) {
			try {
				cliente.join();
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
		}
		resultados.imprimirResultados();
	}

}
