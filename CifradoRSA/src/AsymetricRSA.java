import java.security.KeyFactory;
import java.security.KeyPair;
import java.security.KeyPairGenerator;
import java.security.NoSuchAlgorithmException;
import java.security.spec.RSAPrivateKeySpec;
import java.security.spec.RSAPublicKeySpec;
import java.util.Base64;

import javax.crypto.Cipher;

public class AsymetricRSA {
	private static final String ALG = "RSA";
	private static final String CHARSET = "utf-8";

	public static String encriptar(String mensaje, KeyPair clave) throws Exception{
		Cipher rsaCifrador = Cipher.getInstance(ALG);
		rsaCifrador.init(Cipher.ENCRYPT_MODE, clave.getPublic());
															   // Primero cifro 
		byte[] criptogramaEnBytes = Base64.getEncoder().encode(rsaCifrador.doFinal(mensaje.getBytes(CHARSET)));
		
		return new String(criptogramaEnBytes);
	}
	
	public static String desencriptar(String criptograma, KeyPair clave) throws Exception{
		Cipher rsaCifrador = Cipher.getInstance(ALG);
		rsaCifrador.init(Cipher.DECRYPT_MODE, clave.getPrivate());
		// Deserealizo
		byte[] mensajeEnBytes = Base64.getDecoder().decode(criptograma.getBytes(CHARSET));
		
		return new String(rsaCifrador.doFinal(mensajeEnBytes)); // Descifro
	}
	
	public static void mostrarInfoclave(KeyPair clave) throws Exception{
		KeyFactory factoria = KeyFactory.getInstance(ALG);
		RSAPublicKeySpec partePublica = factoria.getKeySpec(clave.getPublic(), RSAPublicKeySpec.class);
		RSAPrivateKeySpec partePrivada = factoria.getKeySpec(clave.getPrivate(), RSAPrivateKeySpec.class);
		
		System.out.println("Clave PÚBLICA: ");
		System.out.println("Módulus: " + partePublica.getModulus());
		System.out.println("Exponentus: " + partePublica.getPublicExponent());
		
		System.out.println("Clave PRIVADA: ");
		System.out.println("Módulus: " + partePrivada.getModulus());
		System.out.println("Exponentus: " + partePrivada.getPrivateExponent());
	}
	
	public static void main(String[] args) {
		String mensaje = "Anda que la que va a liar Paco, va a ser parda.";
		System.out.println("Obteniendo el generador de claves RSA");
		try {
			KeyPairGenerator keygen = KeyPairGenerator.getInstance(ALG);
			
			System.out.println("Generando la clave RSA...");
			KeyPair clave = keygen.generateKeyPair();
			
			System.out.println("Mostrar información de la clave generada: ");
			mostrarInfoclave(clave);
			
			String criptograma = encriptar(mensaje, clave);
			System.out.println("Mensaje cifrado: " + criptograma);
			
			System.out.println("Mensaje descifrado: " + desencriptar(criptograma, clave));
			
		} catch (NoSuchAlgorithmException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

}
