import java.io.IOException;

import javax.net.ssl.SSLServerSocket;
import javax.net.ssl.SSLServerSocketFactory;
import javax.net.ssl.SSLSocket;

public class Srv_SSL {
	
	private static int PUERTO = 5555;
	public Srv_SSL() throws IOException {
		System.out.println("Obteniendo factoría del socket para el servidor");
		SSLServerSocketFactory socketSRVFactory =  (SSLServerSocketFactory) SSLServerSocketFactory.getDefault();
		
		System.out.println("Creando el socket...");
		SSLServerSocket socketSRV = (SSLServerSocket) socketSRVFactory.createServerSocket(PUERTO);
		while (true) {
			System.out.println("Aceptando conexiones...");
			SSLSocket socketAtencion = (SSLSocket) socketSRV.accept();
			System.out.println("Atendiendo la conexión nueva con un hilo dedicado...");
			new Srv_SSL_Hilo(socketAtencion).start();
		}
	}
	public static void main(String[] args) throws IOException {
		try {
			System.setProperty("javax.net.ssl.keyStore", "./cert/AlmacenSRV");
			System.setProperty("javax.net.ssl.keyStorePassword", "mallorcamola");
			System.setProperty("javax.net.ssl.trustStore", "./cert/AlmacenSRV");
			System.setProperty("javax.net.ssl.trustStorePassword", "mallorcamola");

			new Srv_SSL();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
}
	}

}
