package dam.psp;

import java.rmi.Remote;
import java.rmi.RemoteException;

public interface ICalculadora extends Remote {
	float suma(float a, float b) throws RemoteException;
	float resta(float a, float b) throws RemoteException;
	float producto(float a, float b) throws RemoteException;
	float division(float a, float b) throws RemoteException;
}
