package dam.psp;

import java.rmi.AlreadyBoundException;
import java.rmi.RemoteException;
import java.rmi.registry.Registry;
import java.rmi.server.UnicastRemoteObject;

public class CirculoServidor implements ICirculo {
	
	public final double PI = Math.PI;
	private double radio;

	public CirculoServidor(Registry registro) {
		System.out.println("Creando objeto círculo y su inscripción en el registro.");
		try {
			registro.bind("Circulo", (ICirculo) UnicastRemoteObject.exportObject(this, 0));
		} catch (RemoteException | AlreadyBoundException e) {
			e.printStackTrace();
		}
	}
	
	@Override
	public void set_radio(double radio) throws RemoteException {
		this.radio = radio;
	}

	@Override
	public double area() throws RemoteException {
		return PI * Math.pow(radio, 2);
	}

	@Override
	public double longitud() throws RemoteException {
		return 2 * PI * radio;
	}

}
