package zhuo;

import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;

public class Servidor {
	static final int PUERTO = 5000;
	
	public static void main(String[] args) {

	}
	
	public Servidor() {
		ServerSocket skSRV;
		try {
			skSRV = new ServerSocket(PUERTO);
			System.out.println("Servidor escuchando en: "
			+ skSRV.getLocalSocketAddress().toString());
			int nClientes = 0;
			while (true) {
				Socket skAtencion = skSRV.accept();
				nClientes++;
				System.out.println("Atendiendo al nuevo cliente: " + nClientes);
				
				//creamos un hilo para atender al cliente y así liberar al socket principal
				new SevidorHilo(skAtencion, nClientes);
			}
		} catch (IOException e) {
			e.printStackTrace();
		}	
	}

}
