package com.zhuo;

import java.security.KeyPair;
import java.security.KeyPairGenerator;
import java.security.Signature;
import java.util.Base64;

public class FirmaDigital {
	private static final String ALG1 = "RSA";
	private static final String ALG2 = "DSA";

	private static final String PROTO1 = "SHA1";//"SHA256WithRSA";
	private static final String PROTO2 = "DSA";
	
	private static final String CHARSET = "utf8";

	// DSA is faster for signing. RSA is faster at verifying
	// DSA can only be used for signing/verification
	// whereas RSA can be used for encryption/decryption as well
	
	private static String firmar(String mensaje, KeyPair clave) throws Exception{
		System.out.println("Creando un objeto de tipo Signature.");
		Signature firmaContenedor = Signature.getInstance(PROTO1);
		System.out.println("Firmando el mensaje con la parte PRIVADA de la clave asimétrica...");
		
		firmaContenedor.initSign((clave.getPrivate()));
		firmaContenedor.update(mensaje.getBytes(CHARSET));
		byte[] firma = firmaContenedor.sign();
		return Base64.getEncoder().encodeToString(firma);
		//return new String(firma);
	}
	
	private static boolean esFirmaValida(String mensajeEnClaro, String firma, KeyPair clave) throws Exception{
		System.out.println("Creando un objeto de tipo Signature.");
		Signature firmaContenedor = Signature.getInstance(ALG1);
		
		byte[] mensajeBytes = Base64.getDecoder().decode(firma.getBytes(CHARSET));
		//byte[] mensajeBytes = firma.getBytes(CHARSET);

		
		System.out.println("Verificando el mensaje con la parte PUBLICA de la clave asimétrica...");
		firmaContenedor.initVerify(clave.getPublic());
		firmaContenedor.update(mensajeEnClaro.getBytes(CHARSET));
		
		return firmaContenedor.verify(mensajeBytes);
	}
	
	public static void main(String[] args) throws Exception {
		String mensaje = "Whatś going on?";
		System.out.println("Obteniendo generador de claves: " + ALG1);
		KeyPairGenerator keyPairGenerator = KeyPairGenerator.getInstance(PROTO1);
		KeyPair clave = keyPairGenerator.generateKeyPair();

		
		System.out.println("Firmando el mensaje: " + mensaje + " con la parte privada de la clave.");
		String firma = firmar(mensaje, clave);
		System.out.println("Resultado de la firma: " + firma);
		System.out.println("Comprobando validez de la firma");
		
		if(esFirmaValida(mensaje, firma, clave)) {
			System.out.println("VERIFICADO OK");
		}
		else {
			System.out.println("Firma NO VALIDA");
		}
		
		System.out.println("Ahora manipulamos el texto y debe invalidar la firma");
		String mensajeAlterado = mensaje + "+";
		if(esFirmaValida(mensajeAlterado, firma, clave)) {
			System.out.println("VERIFICADO OK");
		}
		else {
			System.out.println("Firma NO VALIDA");
		}
	}

}
