import java.io.IOException;
import java.net.UnknownHostException;

import javax.net.ssl.SSLSocket;
import javax.net.ssl.SSLSocketFactory;

public class Client_SSL {
	public static final String DESTINO = "localhost";
	public static int PUERTO = 5555; // puerto destino del servidor a conectar
	
	public Client_SSL(String mensaje) throws UnknownHostException, IOException {
		System.out.println("Obteniendo factoría de socket del cliente...");
		SSLSocketFactory socketClientFactory =  (SSLSocketFactory) SSLSocketFactory.getDefault();
		
		System.out.println("Creando el socket del cliente...");
		SSLSocket socketClient = (SSLSocket) socketClientFactory.createSocket(DESTINO, PUERTO);
	}
	
	public static void main(String[] args) {
		
	}
}
