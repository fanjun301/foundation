package cn.frank.foundation.securityTest;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.security.DigestInputStream;
import java.security.DigestOutputStream;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;

public class MessageDigestMain {

	public static void main(String[] args) {

		String message = "This is Frank Test for Message Digest";

		try {
			/**
			 * Current support by default
			 * •MD5
			 * •SHA-1
			 * •SHA-256
			 * 速度排名：MD5 > SHA-1 快近一倍
			 */
			MessageDigest sha1 =MessageDigest.getInstance("SHA-1");
			
			//direct calculate by using MessageDigest
			sha1.update(message.getBytes());
			byte[] digest = sha1.digest();
			
			for (byte b : digest) {
				System.out.print(b);
			}
			System.out.println();
			digestByStream(message, sha1);
		} catch (NoSuchAlgorithmException e) {
			e.printStackTrace();
		} 
	}

	
	/**
	 * //Reader Writer
	 * InputStreamReader OutputStreamWriter
	 * Scanner PrintStream PrintWriter
	 * use above java.io class to wrap DigestInputStream|DigestOutputStream
	 */
	private static void digestByStream(String message, MessageDigest sha1) {
		byte[] digest;
		DigestInputStream mdin = null;
		DigestOutputStream dout = null;
		ByteArrayInputStream inStream = new ByteArrayInputStream(message.getBytes());
		ByteArrayOutputStream outStream = new ByteArrayOutputStream();
		try {
			//Calculate when read from input stream
			sha1.reset();
			mdin = new DigestInputStream(inStream,sha1);
			mdin.on(true); //default is true
			byte[]  bs  = new byte[message.getBytes().length];
			mdin.read(bs);
			digest = sha1.digest();
			for (byte b : digest) {
				System.out.print(b);
			}
			System.out.println();
			
			//Calculate when write into output stream
			sha1.reset();
			dout = new DigestOutputStream(outStream,sha1);
			dout.on(true);//false will not calculate message digest
			dout.write(message.getBytes());
			digest = sha1.digest();
			for (byte b : digest) {
				System.out.print(b);
			}
			System.out.println();
			
		} catch (IOException e) {
			e.printStackTrace();
		}finally{
			try {
				mdin.close();
				dout.close();
				inStream.close();
				outStream.close();
			} catch (IOException e) {
				e.printStackTrace();
			}
		}
	}

}
