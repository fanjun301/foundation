package cn.frank.foundation.securityTest;

import java.security.KeyPair;
import java.security.KeyPairGenerator;
import java.security.NoSuchAlgorithmException;
import java.security.PrivateKey;
import java.security.PublicKey;
import java.security.SecureRandom;

public class KeyPairGeneratorMain {

	@SuppressWarnings("unused")
	public static void main(String[] args) {
		/**
		 * •DiffieHellman (1024)
		 * •DSA (1024)
		 * •RSA (1024, 2048)
		 * •EC
		 */
		try {
			KeyPairGenerator kpg = KeyPairGenerator.getInstance("DSA");
//			kpg.initialize(1024);
			kpg.initialize(1024, new SecureRandom());
			KeyPair keyPair = kpg.generateKeyPair();
			
			PrivateKey privateKey = keyPair.getPrivate();
			PublicKey publicKey = keyPair.getPublic();
			
		} catch (NoSuchAlgorithmException e) {
			e.printStackTrace();
		}
	}

}
