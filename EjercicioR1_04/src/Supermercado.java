
public class Supermercado {

	public static void main(String[] args) {
		int nCajas = Integer.parseInt(args[0]);
		int nClientes = Integer.parseInt(args[1]);
		Caja[] cajas = new Caja[nCajas];
		Thread[] clientes = new Thread[nClientes];
		Resultados resultados = new Resultados(nClientes);
		
		for (int i = 0; i < cajas.length; i++) {
			cajas[i] = new Caja(i, resultados);
		}
		
		for (int i = 0; i < clientes.length; i++) {
			clientes[i] = new Thread(new Cliente(i, cajas));
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
