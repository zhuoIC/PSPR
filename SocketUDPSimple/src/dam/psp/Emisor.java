package dam.psp;

import java.io.IOException;
import java.net.DatagramPacket;
import java.net.DatagramSocket;
import java.net.InetAddress;
import java.net.SocketException;
import java.net.UnknownHostException;

public class Emisor {
	public static final int PuertoEmisor = 5555;
	public static final int PuertoReceptor = 4444;
	public static final String IPReceptor = "localhost";//"192.168.3.57";
		public Emisor() {
		DatagramSocket emisor = null;
		DatagramPacket dgp;
		InetAddress IPLocal;
		InetAddress IPRemota;
		byte[] contenido = new byte[140];
		int valor = 5;
		String cadena = "Así funciona";
		DatoUDP datoUDP = new DatoUDP(cadena, valor);
		
		try {
			IPLocal = InetAddress.getByName("0.0.0.0");
			IPRemota = InetAddress.getByName(IPReceptor);
			contenido = datoUDP.ToByteArray();
			emisor = new DatagramSocket(PuertoEmisor, IPLocal);
			dgp = new DatagramPacket(contenido, contenido.length, IPRemota, PuertoReceptor);
			
			emisor.send(dgp);
			System.out.println("Datos correctamente enviados: " + cadena.length());
			
		} catch (UnknownHostException e) {
			e.printStackTrace();
		} catch (SocketException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		} finally {
			if(emisor != null) {
				emisor.close();
			}
		}
	}
	
	public static void main (String[] args) {
		new Emisor();
	}
}
