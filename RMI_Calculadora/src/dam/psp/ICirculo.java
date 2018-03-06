package dam.psp;

import java.rmi.Remote;
import java.rmi.RemoteException;

public interface ICirculo extends Remote {
	void set_radio(double radio) throws RemoteException;
	double area() throws RemoteException;
	double longitud() throws RemoteException;
}
