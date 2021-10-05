package co.com.common.encrypt;

import java.security.MessageDigest;

public class Encryption {
	/**
	 * Función en la cual se cifra la contraseña antes de ser almacenada en la DB
	 * @param string_cifrar
	 * 		  Contrasela a ser cifrada
	 * @return
	 * 		String con la contraseña cifrada
	 * @throws Exception
	 * @author Esteban Montoya
	 * @throws EncryptionException
	 * 			Exception que es lanzada cuando ocurre un error cifrando la contraseña 
	 * @since 27/06/2018
	 */
	public static String encrypt(String string_cifrar) throws EncryptionException{
		try{
			MessageDigest md = MessageDigest.getInstance("MD5");
			byte[] b = md.digest(string_cifrar.getBytes());
			int size = b.length;
			StringBuffer h = new StringBuffer(size);
			for (int i = 0; i < size; i++) {
			int u = b[i] & 255;
			if (u < 16) {
			h.append("0" + Integer.toHexString(u));
			} else {
				h.append(Integer.toHexString(u));
			}
			}
			return h.toString();
		}catch(Exception e ){
			throw new EncryptionException(e.getMessage(),e);
		}
	}
}
