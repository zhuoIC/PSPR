import java.util.Arrays;

import javax.crypto.Cipher;
import javax.crypto.KeyGenerator;
import javax.crypto.SecretKey;
import javax.crypto.spec.SecretKeySpec;

import org.apache.commons.codec.binary.Base64;
import org.apache.commons.codec.digest.DigestUtils;

public class CifradoAES {
	private static final String cifrado = "AES";
	private static final String CHARSET = "UTF-8";
	
	public static SecretKey obtenerClaveOpaca(int longitud) throws Exception{
		KeyGenerator claveInstancia = KeyGenerator.getInstance(cifrado);
		claveInstancia.init(longitud); // por defecto 128 bits
		return claveInstancia.generateKey();
	}
	
	public static SecretKeySpec obtenerClaveTransparente(String miClave) throws Exception{
		byte[] miClaveEnBytes = miClave.getBytes(CHARSET);
		System.out.println("El hash SHA2 de la clave es: " + DigestUtils.sha256Hex(miClaveEnBytes));
		byte[] miClave256 = Arrays.copyOf(DigestUtils.sha256(miClaveEnBytes), 16);
		return new SecretKeySpec(miClave256, cifrado);
	}
	
	public static String encriptar(String mensaje, SecretKey clave) throws Exception{
		Cipher c = Cipher.getInstance(cifrado); // AES
		c.init(Cipher.ENCRYPT_MODE, clave);
		byte[] encVal = c.doFinal(mensaje.getBytes(CHARSET));
		
		byte[] criptogramaEnBytes = Base64.encodeBase64(encVal);
		return new String(criptogramaEnBytes);
	}
	
	public static String desencriptar(String criptograma, SecretKey clave) throws Exception{
		Cipher c = Cipher.getInstance(cifrado); // AES
		c.init(Cipher.DECRYPT_MODE, clave);
		
		byte[] decValue = Base64.decodeBase64(criptograma.getBytes(CHARSET)); // Desenpaqueta
		byte[] decryptedValue = c.doFinal(decValue);	// 
		return new String(decryptedValue);
	}
	
	public static void main(String[] args) {
		String mensaje = "¡Hola caracola!";
		String miClave = "123;abc";
		
		try {
			SecretKey miClaveOpaca = CifradoAES.obtenerClaveOpaca(256);// Por defecto sería 128	
			System.out.println("Mensaje en claro: " + mensaje);
			
			String criptograma = CifradoAES.encriptar(mensaje, miClaveOpaca);
			System.out.println("Criptograma: " + criptograma);
			System.out.println("Desencriptado: " + CifradoAES.desencriptar(criptograma, miClaveOpaca));
			
			// Ahora estamos usando una clave transparente
			
			SecretKeySpec claveT = CifradoAES.obtenerClaveTransparente(miClave);
			criptograma = CifradoAES.encriptar(mensaje, claveT);
			System.out.println("Criptograma: " + criptograma);
			System.out.println("Desencriptado: " + CifradoAES.desencriptar(criptograma, claveT));
			
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

}
