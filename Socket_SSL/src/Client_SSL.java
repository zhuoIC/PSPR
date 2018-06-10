import java.io.BufferedOutputStream;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
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
		mostrarCifrados(socketClient);//Mostramos los cifrados que actualmente tenemos disponibles/ habilitados
		
		PrintWriter pw = new PrintWriter(new BufferedOutputStream(socketClient.getOutputStream()));
		pw.println(mensaje);
		pw.flush();//Por seguridad
		System.out.println("Mensaje enviado: "+mensaje);
		BufferedReader br= new BufferedReader(new InputStreamReader(socketClient.getInputStream()));
		System.out.println("Mensaje cifrado recibido: "+br.readLine());
		System.out.println("Cerrando la conexión");
		pw.close();
		br.close();
		socketClient.close();
		System.out.println("Finalizando");
	}
	
	private void mostrarCifrados(SSLSocket socket)
	{
		String[] protocolos = socket.getEnabledProtocols();
		System.out.println("Protocolos habilitados:");
		for(int i =0; i<protocolos.length;i++)
			System.out.println("Protocolo numero "+i+": "+protocolos[i]);

		String[] protocolosSoportados = socket.getSupportedProtocols();
		System.out.println("Protocolos disponibles:");
		for(int i =0; i<protocolosSoportados.length;i++)
			System.out.println("Protocolo numero "+i+": "+protocolosSoportados[i]);

		String[] protocolosDeseados = new String[1];
		protocolosDeseados[0]="TLSv1.2";
		socket.setEnabledProtocols(protocolosDeseados);
		
		protocolos = socket.getEnabledProtocols();
		System.out.println("Protocolo activos: ");
		for(int i =0; i<protocolos.length;i++)
			System.out.println("Protocolo numero "+i+": "+protocolos[i]);
		
		
	}
	
	public static void main(String[] args) throws UnknownHostException, IOException {
		System.setProperty("javax.net.ssl.keyStore", "./cert/AlmacenCli");
		System.setProperty("javax.net.ssl.keyStorePassword", "molamazo");
		System.setProperty("javax.net.ssl.trustStore", "./cert/AlmacenCli");
		System.setProperty("javax.net.ssl.trustStorePassword", "molamazo");
		new Client_SSL("Mensaje");
	}
}
