package cn.frank.foundation.securityTest;

import java.io.IOException;
import java.io.InputStream;
import java.security.InvalidKeyException;
import java.security.KeyStore;
import java.security.KeyStoreException;
import java.security.NoSuchAlgorithmException;
import java.security.PrivateKey;
import java.security.SecureRandom;
import java.security.Signature;
import java.security.SignatureException;
import java.security.UnrecoverableKeyException;
import java.security.cert.Certificate;
import java.security.cert.CertificateException;
import java.security.cert.CertificateFactory;
import java.security.cert.X509Certificate;


public class CertificateSignatureMain {

	public static void main(String[] args) {

		String keyPass = "fanjun" ;
		String message = "Frank test for certificate on signature";
		
		InputStream inputStream = CertificateSignatureMain.class.getResourceAsStream("fanjun.keystore");
		InputStream inStream = CertificateSignatureMain.class.getResourceAsStream("fanjun.cer");
		
		try {
			byte[] signBytes = signOnServerSide(keyPass, message, inputStream);
			verifyOnClientSide(message, inStream, signBytes);
		} finally{
			try {
				inputStream.close();
				inStream.close();
			} catch (IOException e) {
				e.printStackTrace();
			}
		}
	}

	private static void verifyOnClientSide(String message,
			InputStream inStream, byte[] signBytes){
		// for client
		X509Certificate cert = null ;
		try {
			CertificateFactory certFactory = CertificateFactory.getInstance("X.509");
//		    Certificate cert = certFactory.generateCertificate(inStream);
			cert = (X509Certificate)certFactory.generateCertificate(inStream);
//		    PublicKey publicKey = cert.getPublicKey();
		} catch (CertificateException e) {
			e.printStackTrace();
		}
		
		try {
			Signature sig = Signature.getInstance("SHA1withDSA");
			sig.initVerify(cert);
			sig.update(message.getBytes());
			System.out.println(String.format("Client Signature no change : %s", sig.verify(signBytes)));
			sig.update("Changed".getBytes());
			System.out.println(String.format("Client Signature changed : %s", sig.verify(signBytes)));
		} catch (NoSuchAlgorithmException e) {
			e.printStackTrace();
		} catch (InvalidKeyException e) {
			e.printStackTrace();
		} catch (SignatureException e) {
			e.printStackTrace();
		}
	}

	private static byte[] signOnServerSide(String keyPass, String message,
			InputStream inputStream) {
		// for server
		KeyStore keyStore = null;
		PrivateKey privateKey = null;
		try {
			keyStore = KeyStore.getInstance(KeyStore.getDefaultType());
			keyStore.load(inputStream, keyPass.toCharArray());
			privateKey = (PrivateKey)keyStore.getKey("fanjun", keyPass.toCharArray());
		} catch (KeyStoreException e) {
			e.printStackTrace();
		} catch (NoSuchAlgorithmException e) {
			e.printStackTrace();
		} catch (CertificateException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		} catch (UnrecoverableKeyException e) {
			e.printStackTrace();
		}
		
		// signature in server side by using certificate private key
		Signature sig = null;
		byte[] signBytes = null;
		try {
			sig = Signature.getInstance("SHA1withDSA");
			sig.initSign(privateKey, new SecureRandom());
			sig.update(message.getBytes());
			signBytes = sig.sign();
		} catch (NoSuchAlgorithmException e) {
			e.printStackTrace();
		} catch (InvalidKeyException e) {
			e.printStackTrace();
		} catch (SignatureException e) {
			e.printStackTrace();
		}
		
		// verify within server side
		Certificate cert;
		try {
			cert = keyStore.getCertificate("fanjun");
			sig.initVerify(cert);
			sig.update(message.getBytes());
			System.out.println(String.format("verify within server side by certificate: %s", sig.verify(signBytes)));
		} catch (KeyStoreException e) {
			e.printStackTrace();
		} catch (InvalidKeyException e) {
			e.printStackTrace();
		} catch (SignatureException e) {
			e.printStackTrace();
		}
		return signBytes;
	}

}
