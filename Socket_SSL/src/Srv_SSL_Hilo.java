import java.io.BufferedOutputStream;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;

import javax.net.ssl.SSLSocket;

public class Srv_SSL_Hilo extends Thread {

	private SSLSocket miSocket;
	private static final String CHARSET = "utf8";
	private static final String algorithm = "SHA-256";
	
	public Srv_SSL_Hilo(SSLSocket socket) {
		this.miSocket = socket;
	}
	
	@Override
	public void run() {
		InputStreamReader is;
		PrintWriter pw;
		try {
			is = new InputStreamReader(miSocket.getInputStream(), CHARSET);
			BufferedReader br = new BufferedReader(is);
			String mensajeRecibido = br.readLine();

			System.out.println("Mensaje recibido desde el cliente: " + mensajeRecibido);
			
			// Enviamos como respuesta el mensaje en hash SHA-256
			pw = new PrintWriter(new BufferedOutputStream(miSocket.getOutputStream()), true);
			
			byte[] mensajeEnBytes = mensajeRecibido.getBytes(CHARSET);
			MessageDigest sha = MessageDigest.getInstance(algorithm);
			
			pw.println(sha.digest(mensajeEnBytes));
			
			System.out.println("Cerrando socket y el hilo dedicado");
			pw.flush();
			pw.close();
			miSocket.close();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (NoSuchAlgorithmException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

	}

}
