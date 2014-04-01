package cn.frank.foundation.securityTest;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.security.InvalidKeyException;
import java.security.NoSuchAlgorithmException;
import java.security.SecureRandom;

import javax.crypto.BadPaddingException;
import javax.crypto.Cipher;
import javax.crypto.CipherInputStream;
import javax.crypto.CipherOutputStream;
import javax.crypto.IllegalBlockSizeException;
import javax.crypto.KeyGenerator;
import javax.crypto.NoSuchPaddingException;
import javax.crypto.SecretKey;

public class CipherAESMain {

	public static void main(String[] args) {
		try {
			KeyGenerator kg = KeyGenerator.getInstance("AES");
			kg.init(128, new SecureRandom());
			SecretKey secretKey = kg.generateKey();
			
			cryptDirectly(secretKey);
			cryptStreamAPI(secretKey);
		} catch (NoSuchAlgorithmException e) {
			e.printStackTrace();
		} catch (NoSuchPaddingException e) {
			e.printStackTrace();
		} catch (InvalidKeyException e) {
			e.printStackTrace();
		} catch (IllegalBlockSizeException e) {
			e.printStackTrace();
		} catch (BadPaddingException e) {
			e.printStackTrace();
		} 
		
	}

	private static void cryptStreamAPI(SecretKey secretKey) throws NoSuchAlgorithmException, NoSuchPaddingException, InvalidKeyException {
		
		InputStream in = CipherAESMain.class.getResourceAsStream("content.txt");
		Cipher cipher = Cipher.getInstance("AES");
		cipher.init(Cipher.ENCRYPT_MODE, secretKey);
		byte[] b = new byte[cipher.getBlockSize()];
		CipherInputStream cin = new CipherInputStream(in, cipher);
			
		Cipher cipher1 = Cipher.getInstance("AES");
		cipher1.init(Cipher.DECRYPT_MODE, secretKey);
		File resFile = createDecrptFile();
		FileOutputStream fout = null;
		CipherOutputStream cout = null;
		try {
			fout = new FileOutputStream(resFile,true);
			cout = new CipherOutputStream(fout,cipher1);
			while (cin.read(b) > 0) {
				cout.write(b);
			}
			cout.flush();
//			fout.flush();
		} catch (IOException e) {
			e.printStackTrace();
		}finally{
			try {
				cin.close();
				in.close();
				cout.close();
				fout.close();
			} catch (IOException e) {
				e.printStackTrace();
			}
		}
		
	}

	private static File createDecrptFile() {
		File file = new File( CipherAESMain.class.getResource(".").getPath(),"result.txt");
		if (file.exists()) {
			file.delete();
		}
		try {
			file.getParentFile().mkdirs();
			file.createNewFile();
		} catch (IOException e) {
			e.printStackTrace();
		}
		return file ;
	}

	private static void cryptDirectly(SecretKey secretKey)
			throws NoSuchAlgorithmException, NoSuchPaddingException,
			InvalidKeyException, IllegalBlockSizeException, BadPaddingException {
		Cipher cipher = Cipher.getInstance("AES");
		cipher.init(Cipher.ENCRYPT_MODE, secretKey);			
		int bsize = cipher.getBlockSize();
		System.out.println(String.format("block size : %s", bsize));
		
//            byte[] b = new byte[bsize];
		byte[] b = "frank".getBytes();
		cipher.update(b);
		byte[] finalBytes = cipher.doFinal();
		for (byte c : finalBytes) {
			System.out.print(c);
		}
		System.out.println();
		
		cipher.init(Cipher.DECRYPT_MODE, secretKey);
		cipher.update(finalBytes);
		finalBytes = cipher.doFinal();
		System.out.println(new String(finalBytes));
	}

}
