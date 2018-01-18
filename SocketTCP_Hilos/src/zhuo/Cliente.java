package zhuo;

import java.io.IOException;
import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.io.OutputStreamWriter;
import java.io.PrintWriter;
import java.net.Socket;
import java.net.UnknownHostException;
import java.util.Scanner;

public class Cliente {
	
	static final String HOST = "192.168.3.57";
	static final int PUERTO = 5000;
	Scanner entrada;
	
	private String LeerMensaje() {
		System.out.println("Introduce el mensaje para enviar");
		String mensaje = entrada.nextLine();
		return mensaje;
	}
	
	public static void main(String[] args) {
		// TODO Auto-generated method stub
			new Cliente();				
	}
	
	public Cliente() {
		try {
			Socket skCli = new Socket(HOST, PUERTO);
			//InputStreamReader is = new InputStreamReader(skCli.getInputStream(), "utf-8");
			BufferedReader br = new BufferedReader(new InputStreamReader(skCli.getInputStream(), "utf-8"));
			BufferedReader teclado = new BufferedReader(new InputStreamReader(System.in, "utf-8"));
			PrintWriter pw = new PrintWriter(new OutputStreamWriter(skCli.getOutputStream(), "utf-8"), true);

			System.out.println(br.readLine()); // Recibimos el saludo del servidor
			
			// enviar mensaje pedidio por consola al servidor
			System.out.print("Mensaje para enviar: ");
			String mensaje = teclado.readLine();
			System.out.println("Enviando al servidor el mensaje: " + mensaje);
			pw.println(mensaje);
			
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
