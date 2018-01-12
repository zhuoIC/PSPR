package zhuo;

import java.io.BufferedOutputStream;
import java.io.IOException;
import java.io.PrintWriter;
import java.net.Socket;

public class SevidorHilo extends Thread{
	Socket socket;
	int id;
	String mensaje;
	
	public SevidorHilo(Socket socket, int id) {
		this.socket = socket;
		this.id = id;
		this.mensaje = "Bienvenido/a a mi canal";
	}
	
	@Override
	public void run() {
		BufferedOutputStream bo;
		PrintWriter pw = null;
		
		try {
			bo = new BufferedOutputStream(socket.getOutputStream());
			pw = new PrintWriter(bo, true); // En true limpia el buffer
			pw.println(id + ":" + this.mensaje);
		} catch (IOException e) {
			e.printStackTrace();
		}
		if (pw != null) {
			pw.flush(); // nunca debería hacer falta esto
			pw.close();
		}
	}

}
