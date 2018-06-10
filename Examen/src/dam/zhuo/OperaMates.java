package dam.zhuo;

import java.rmi.RemoteException;
import java.rmi.registry.LocateRegistry;
import java.rmi.registry.Registry;
import java.rmi.server.UnicastRemoteObject;

public class OperaMates implements IRMIMates{
	public static final String BOUND = "SERVIDOR";
	public static final String IP = "localhost";
	public final static int PORT =8888;
	
	public static void main(String[] args) throws RemoteException {
		System.setProperty("java.rmi.server.hostname",IP);
		System.setProperty("java.net.preferIPv4Stack", "true");
		
		Registry registry = LocateRegistry.createRegistry(PORT);
		new OperaMates(registry);
	}
	
	public OperaMates(Registry registry) {
		System.out.println("Creando Objeto Calculadora y su inscripción en el registro");
		try {
			registry.rebind(BOUND, (IRMIMates)UnicastRemoteObject.exportObject(this, 0));
		} catch (RemoteException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	@Override
	public boolean esPrimo(int n) throws RemoteException {
		boolean primo = true;
		if(n < 2) {
			primo = false;
		}
		else {
			for (int i = 2; i <= n/2; i++) {
				if(i % 2 == 0) {
					primo = false;
					break;
				}
			}
		}
		return primo;
	}
}
