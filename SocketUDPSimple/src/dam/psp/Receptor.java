package dam.psp;

import java.io.IOException;
import java.net.DatagramPacket;
import java.net.DatagramSocket;
import java.net.InetAddress;
import java.net.SocketException;
import java.net.UnknownHostException;

public class Receptor {
	public static final int Puerto = 4444;
	public static final String IPEmisor = "localhost";
	private DatagramSocket socket = null;
	
	public Receptor() {
		try {
			socket = new DatagramSocket(Puerto, InetAddress.getByName(IPEmisor));
			System.out.println("Emisor conectado al socket: " + socket.getLocalAddress());
			while (true) {
				// Se recibe un dato y se escribe en pantalla
				DatagramPacket dato = new DatagramPacket(new byte[144], 144);
				socket.receive(dato); // bloqueante
				System.out.println("Recibo un paquete de " + dato.getAddress().getHostName() + ": ");
				byte[] contenido = dato.getData();
				System.out.println(" de longitud: " + dato.getLength());
				// Deserializamos a String
				String mensaje = DatoUDP.FromByteArray(contenido).cadenaTexto;
				int valor = DatoUDP.FromByteArray(contenido).valor;
				System.out.println("MENSAJE: " + mensaje + " VALOR: " + valor);
			}			
		} catch (SocketException e) {
			e.printStackTrace();
		} catch (UnknownHostException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		} finally {
			if(socket != null) {
				socket.close();
			}
		}
	}
	
	public static void main (String[] args) {
		new Receptor();
	}
}
