package zhuo;

import java.io.IOException;
import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.net.Socket;
import java.net.UnknownHostException;
import java.util.Scanner;

public class Cliente {
	
	static final String HOST = "192.168.2.40";
	static final int PUERTO = 5000;
	Scanner entrada;
	
	private String LeerMensaje() {
		System.out.println("Introduce el mensaje para enviar");
		String mensaje = entrada.nextLine();
		return mensaje;
	}
	
	public static void main(String[] args) {
		// TODO Auto-generated method stub
		while (true) {
			new Cliente();			
		}
		
	}
	
	public Cliente() {
		try {
			Socket skCli = new Socket(HOST, PUERTO);
			InputStreamReader is = new InputStreamReader(skCli.getInputStream(), "utf-8");
			BufferedReader br = new BufferedReader(is);
			
			System.out.println(br.readLine()); // Recibimos el saludo del servidor
			
			skCli.close();
			
		} catch (UnknownHostException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

}
