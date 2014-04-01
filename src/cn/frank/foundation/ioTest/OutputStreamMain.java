package cn.frank.foundation.ioTest;

import java.io.BufferedOutputStream;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.OutputStream;
import java.io.PrintStream;
import java.io.PrintWriter;

/**
 * ByteArrayOutputStream, 
 * FileOutputStream, 
 * FilterOutputStream, -> BufferedOutputStream DataOutputStream
 * ObjectOutputStream, 
 * OutputStream, 
 * PipedOutputStream
 * @author jfan
 *
 */
public class OutputStreamMain {

	/**
	 * Scanner
	 * PrintStream
	 * PrintWriter
	 * 都是有buffer的
	 * @param args
	 */
	public static void main(String[] args) {

		OutputStream os = null;
		BufferedOutputStream bout = null;
		PrintStream ps = null;
		PrintWriter pw = null;
		try {
			File file = createNewFile();
//			os = new FileOutputStream(file,true);
			os = new FileOutputStream(file);
			bout = new BufferedOutputStream(os,1024);
			bout.write(("This is Frank test for buffered write "+System.getProperty("line.separator")).getBytes());
			bout.flush();
			
			//PrintStream(OutputStream out, boolean autoFlush, String encoding) //编码
			ps = new PrintStream(os);
			ps.println("This is Frank test by using PrintStream println");
			ps.flush();
			
			pw = new PrintWriter(os);
			pw.write("This is Frank test by using PrintWriter write");
			pw.println();
			pw.println("This is Frank test by using PrintWriter println ");
			pw.flush();
			
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}finally{
			try {
				if (os != null) {
					os.close();
				}
				if (bout != null) {
					bout.close();
				}
				if (ps != null) {
					ps.close();
				}
				if (pw != null) {
					pw.close();
				}
			} catch (IOException e) {
				e.printStackTrace();
			}
		}
		
		
	}

	private static File createNewFile() {
		File file = new File(OutputStreamMain.class.getResource(".").getPath(),"OutputStreamTest.txt");
		if (file.exists()) {
			file.delete();
		}
		file.getParentFile().mkdirs();
		try {
			file.createNewFile();
		} catch (IOException e) {
			e.printStackTrace();
		}
		return file;
		
	}

}
