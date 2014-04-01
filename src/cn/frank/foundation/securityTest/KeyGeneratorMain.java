package cn.frank.foundation.securityTest;

import java.security.NoSuchAlgorithmException;
import java.security.SecureRandom;

import javax.crypto.KeyGenerator;
import javax.crypto.SecretKey;
/**
 * •AES (128)
 * •DES (56)
 * •DESede (168)
 * •HmacSHA1
 * •HmacSHA256
 * @author jfan
 *
 */
public class KeyGeneratorMain {

	public static void main(String[] args) {

		try {
			KeyGenerator kg = KeyGenerator.getInstance("AES");
			kg.init(128, new SecureRandom());
			
			SecretKey secretKey = kg.generateKey();
			System.out.println(secretKey.getAlgorithm());
			
		} catch (NoSuchAlgorithmException e) {
			e.printStackTrace();
		}
	}

}
