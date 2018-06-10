package dam.zhuo;

import java.io.IOException;
import java.net.DatagramPacket;
import java.net.DatagramSocket;
import java.net.InetAddress;

public class HoraClient {
	public static final int port = 8888;
	public static void main(String[] args) throws IOException {
		DatagramSocket datagramSocket = new DatagramSocket(port);
		InetAddress serverAddr = InetAddress.getByName("localhost");
		String mensaje = "hola";
		DatagramPacket datagramPacket = new DatagramPacket(mensaje.getBytes(), mensaje.getBytes().length, serverAddr, port);
		datagramSocket.send(datagramPacket); // Enviado
		byte[] respuesta = new byte[100];
		DatagramPacket datagramPacket2 = new DatagramPacket(respuesta, respuesta.length);
		datagramSocket.receive(datagramPacket2);
		System.out.println(new String(respuesta));
		datagramSocket.close();
	}
}
