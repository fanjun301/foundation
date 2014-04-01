package cn.frank.foundation.securityTest;

import java.security.InvalidKeyException;
import java.security.KeyPair;
import java.security.KeyPairGenerator;
import java.security.NoSuchAlgorithmException;
import java.security.PrivateKey;
import java.security.PublicKey;
import java.security.SecureRandom;
import java.security.Signature;
import java.security.SignatureException;
import java.util.Arrays;
/**
 * 数字签名可以是 NIST 标准的 DSA，它使用 DSA 和 SHA-1。可以将使用 SHA-1 消息摘要算法的 DSA 算法指定为 SHA1withDSA。
 * 如果使用 RSA，对消息摘要算法则会有多种选择，因此，可以将签名算法指定为 MD2withRSA、MD5withRSA 或 SHA1withRSA。
 * 因为没有默认的算法名称，所以必须为其指定名称
 * •SHA1withDSA
 * •SHA1withRSA
 * •SHA256withRSA	 
 * @author jfan
 *
 */
public class SignatureMain {

	public static void main(String[] args) {

		/**
		 * Signature 3 phase
		 * 1 initialize public|private key
		 * 2 update(byte[])
		 * 3 sign or verify
		 */
		
		String message = "This is Frank Test for Signature";
		byte[] plainBytes = message.getBytes();
		
		/**
		 * Generate private public key base on RSA
		 * other choice: DSA EC
		 */
		KeyPair rsaKeyPair = createKeyPair("RSA");
		PrivateKey privateKey = rsaKeyPair.getPrivate();
		PublicKey publicKey = rsaKeyPair.getPublic();

		//signature with RSA private Key
		byte[] signBytes = sign(plainBytes, privateKey);
		
		//verify with public key -- content no change
		verify(plainBytes, publicKey, signBytes);
		//verify with public key -- content changed
		verify(Arrays.copyOfRange(plainBytes, 0, plainBytes.length/2), publicKey, signBytes);
	}

	private static void verify(byte[] plainTxt, PublicKey publicKey, byte[] signBytes) {
		try {
			Signature sig = Signature.getInstance("SHA1withRSA");
			sig.initVerify(publicKey);
		    sig.update(plainTxt);
		    System.out.println(String.format("verify signature result: %s", sig.verify(signBytes)));
		} catch (NoSuchAlgorithmException e) {
			e.printStackTrace();
		} catch (InvalidKeyException e) {
			e.printStackTrace();
		} catch (SignatureException e) {
			e.printStackTrace();
		}
	}

	private static byte[] sign(byte[] plainTxt, PrivateKey privateKey) {
		try {
			Signature sig = Signature.getInstance("SHA1withRSA");
		    sig.initSign(privateKey);
		    sig.update(plainTxt);
		    return sig.sign();
		} catch (NoSuchAlgorithmException e) {
			e.printStackTrace();
		} catch (InvalidKeyException e) {
			e.printStackTrace();
		} catch (SignatureException e) {
			e.printStackTrace();
		}
		return null;
	}

	/**
	 * @param alg value should be RSA or DSA
	 * @return
	 */
	private static KeyPair createKeyPair(String alg) {
		KeyPairGenerator keyGen = null; 
		try {
			keyGen = KeyPairGenerator.getInstance(alg);
			keyGen.initialize(1024, new SecureRandom());
			return keyGen.generateKeyPair();
		} catch (NoSuchAlgorithmException e) {
			e.printStackTrace();
		}
		return null;
	}

}
