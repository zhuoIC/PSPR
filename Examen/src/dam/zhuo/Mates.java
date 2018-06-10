package dam.zhuo;

import java.rmi.NotBoundException;
import java.rmi.RemoteException;
import java.rmi.registry.LocateRegistry;
import java.rmi.registry.Registry;

public class Mates {
	
	public static void main(String[] args) throws RemoteException {
		IRMIMates calculadora= null;
		System.out.println("Localizando en red el objeto remoto");
		Registry registry = LocateRegistry.getRegistry("localhost", 8888);
		
		System.out.println("Obteniendo el falso objeto <stub> del remote");
		try {
			if(registry != null) {
			String j = "";
			calculadora=(IRMIMates) registry.lookup("rmi://localhost/"+OperaMates.BOUND);
			System.out.println("¿Es primo? 64546823="+calculadora.esPrimo(64546823));
			}
		} catch (NotBoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		
}
}
