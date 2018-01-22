package zhuo;

import java.io.IOException;
import java.net.DatagramPacket;
import java.net.DatagramSocket;
import java.net.InetAddress;
import java.net.SocketException;
import java.net.UnknownHostException;

public class Emisor {
	public static final int PuertoEmisor = 5555;
	public static final int PuertoReceptor = 4444;
	public static final String IPReceptor = "localhost";
	
	public Emisor() {
		DatagramSocket emisor = null;
		DatagramPacket dgp;
		InetAddress IPLocal;
		InetAddress IPRemota;
		byte[] contenido = new byte[] {1,2,3,4};
		String mensaje = "Van dos y se cae el del medio";
		
		try {
			IPLocal = InetAddress.getByName("0.0.0.0");
			IPRemota = InetAddress.getByName(IPReceptor);
			contenido = mensaje.getBytes();
			emisor = new DatagramSocket(PuertoEmisor, IPLocal);
			dgp = new DatagramPacket(contenido, contenido.length, IPRemota, PuertoReceptor);
			
			emisor.send(dgp);
			System.out.println("Datos correctamente enviados");
			
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
