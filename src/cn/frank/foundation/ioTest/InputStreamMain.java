package cn.frank.foundation.ioTest;

import java.io.BufferedInputStream;
import java.io.ByteArrayInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.SequenceInputStream;
import java.util.Scanner;

/**
 * AudioInputStream,
 * ByteArrayInputStream, 
 * FileInputStream, 
 * FilterInputStream,  -> BufferedInputStream DataInputStream
 * InputStream, 
 * ObjectInputStream, 
 * PipedInputStream, 
 * SequenceInputStream, 
 * @author jfan
 *
 */
public class InputStreamMain {

	public static void main(String[] args) {

		InputStream is = null;
		BufferedInputStream bs = null;
		Scanner scanner = null;
		Scanner scanner1 = null;
		ByteArrayInputStream bin = null;
		SequenceInputStream sin = null;
		try {
//			new FileInputStream(new File(""));
		    is = InputStreamMain.class.getResourceAsStream("content.txt");	
		    
		    System.out.println("====BUFFER INPUSTREAM====");
		    is.mark(0);
		    bs = new BufferedInputStream(is,1024); //缓冲
		    byte[] b = new byte[1024];
		    while (bs.read(b) != -1) {
				System.out.println(new String(b));
			}
		    
		    System.out.println("=======SCANNER=======");
		    is.reset();
		    scanner = new Scanner(is);
		    while (scanner.hasNextLine()) {
		    	System.out.println(scanner.nextLine());
			}
		    
		    //test for SequenceInputStream
		    System.out.println("=======SEQUENCE INPUTSTREAM===========");
		    is.reset();
		    bin = new ByteArrayInputStream("ByteArray".getBytes());
		    sin = new SequenceInputStream(bin, is);
//		    b = new byte[1024];
//		    while (sin.read(b) > 0) {
//		    	System.out.println(new String(b));
//			}
		    scanner1 = new Scanner(sin);
		    while (scanner1.hasNextLine()) {
				System.out.println(scanner1.nextLine());
			}
		} catch (IOException e) {
			e.printStackTrace();
		}finally{
			try {
				if (is != null) {
					is.close();
				}
				if (bs != null) {
					bs.close();
				}
				if (scanner != null) {
					scanner.close();
				}
				if (scanner1 != null) {
					scanner1.close();
				}
				if (bin != null) {
					bin.close();
				}
				if (sin != null) {
					sin.close();
				}
			} catch (IOException e) {
				e.printStackTrace();
			}
		}
	}

}
