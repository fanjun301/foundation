package cn.frank.foundation.securityTest;

import java.io.IOException;
import java.io.InputStream;
import java.security.InvalidKeyException;
import java.security.Key;
import java.security.KeyStore;
import java.security.KeyStoreException;
import java.security.NoSuchAlgorithmException;
import java.security.PrivateKey;
import java.security.SecureRandom;
import java.security.UnrecoverableKeyException;
import java.security.cert.CertificateException;
import java.security.cert.CertificateFactory;
import java.security.cert.X509Certificate;

import javax.crypto.Cipher;
import javax.crypto.IllegalBlockSizeException;
import javax.crypto.KeyGenerator;
import javax.crypto.NoSuchPaddingException;
import javax.crypto.SecretKey;

/**
 * RSA对AES密钥
 * 公钥加密，私钥解密过程
 * @author jfan
 *
 */
public class CipherRSAMain {

	public static void main(String[] args) {

		String keyPass = "fanjun" ;
		InputStream inputStream = CertificateSignatureMain.class.getResourceAsStream("rsa.jks");
		InputStream inStream = CertificateSignatureMain.class.getResourceAsStream("rsa.cer");
		
		try {
			KeyGenerator kg = KeyGenerator.getInstance("AES");
			kg.init(128, new SecureRandom());
			SecretKey secretKey = kg.generateKey();
			
			//公钥对密钥加密
			CertificateFactory certFactory = CertificateFactory.getInstance("X.509");
			X509Certificate cert = (X509Certificate)certFactory.generateCertificate(inStream);
			Cipher rsaCipher = Cipher.getInstance("RSA");
			rsaCipher.init(Cipher.WRAP_MODE, cert);
//			rsaCipher.init(Cipher.WRAP_MODE, cert.getPublicKey());
			byte[] keyWrapBytes = rsaCipher.wrap(secretKey);
			
			//私钥对密钥解密
			KeyStore keyStore = KeyStore.getInstance(KeyStore.getDefaultType());
			keyStore.load(inputStream, keyPass.toCharArray());
			PrivateKey privateKey = (PrivateKey)keyStore.getKey("rsa", keyPass.toCharArray());
			Cipher rsaCipher1 = Cipher.getInstance("RSA");
			rsaCipher1.init(Cipher.UNWRAP_MODE, privateKey);
			Key unWrapKey = rsaCipher1.unwrap(keyWrapBytes, "AES", Cipher.SECRET_KEY);
			
			//密钥匹配判断
			System.out.println(unWrapKey.equals(secretKey));
			
		} catch (NoSuchAlgorithmException e) {
			e.printStackTrace();
		} catch (NoSuchPaddingException e) {
			e.printStackTrace();
		} catch (KeyStoreException e) {
			e.printStackTrace();
		} catch (CertificateException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		} catch (UnrecoverableKeyException e) {
			e.printStackTrace();
		} catch (InvalidKeyException e) {
			e.printStackTrace();
		} catch (IllegalBlockSizeException e) {
			e.printStackTrace();
		}finally{
			try {
				inputStream.close();
				inStream.close();
			} catch (IOException e) {
				e.printStackTrace();
			}
		}
		
	}

}
