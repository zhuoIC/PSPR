package dam.zhuo;

import java.rmi.Remote;
import java.rmi.RemoteException;

public interface IRMIMates extends Remote {
	public boolean esPrimo(int n) throws RemoteException;
}
