package cn.frank.foundation.ioTest;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.PrintStream;
import java.nio.charset.Charset;
import java.util.Scanner;
import java.util.zip.ZipEntry;
import java.util.zip.ZipFile;
import java.util.zip.ZipInputStream;
import java.util.zip.ZipOutputStream;

/**
 * zip 中文乱码问题
 * 在JDK7 中已经解决了
 * JDK7前需要借助 org.apache.tools.zip.ZipEntry 来解决
 * 或者使用UTF-8作为字节编码
 * @author jfan
 *
 */
public class ZipStreamMain {

	public static void main(String[] args) {

		File file = createNewFile();
		FileOutputStream fout = null;
		ZipOutputStream zout = null;
		PrintStream ps = null;
		
		ZipInputStream zin = null;
		FileInputStream fin = null;
		Scanner scanner = null;
		Scanner scanner1 = null;
		ZipFile zf = null;
		InputStream inputStream = null;
		try {
			fout = new FileOutputStream(file);
			zout = new ZipOutputStream(fout, Charset.defaultCharset());
			ps = new PrintStream(zout);
			
			ZipEntry ze = new ZipEntry("ziptest/test.txt");
			zout.putNextEntry(ze);
			ps.println("汉字测试");
			ps.flush();
			zout.closeEntry();
			zout.close(); //since we want to test read in the same method, we need close first
			
			fin = new FileInputStream(file);
			zin = new ZipInputStream(fin, Charset.defaultCharset());
			scanner = new Scanner(zin);
			ZipEntry entry = null;
			while ((entry = zin.getNextEntry()) != null) {
				System.out.println(String.format("entry name : %s", entry.getName()));
				while (scanner.hasNextLine()) {
					System.out.println(scanner.nextLine());
				}
				zin.closeEntry();
			}
			
			System.out.println("========ZIP FILE=========");
			zf = new ZipFile(file, Charset.defaultCharset());
			ZipEntry entry2 = zf.getEntry("ziptest/test.txt");
            inputStream = zf.getInputStream(entry2);			
            scanner1 = new Scanner(inputStream);
            while (scanner1.hasNextLine()) {
				System.out.println(scanner1.nextLine());
			}
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}finally{
			try {
				if (ps != null) {
					ps.close();
				}
				if (zout != null) {
					zout.close();
				}
				if (fout != null) {
					fout.close();
				}
				
				if (scanner != null) {
					scanner.close();
				}
				if (scanner1 != null) {
					scanner1.close();
				}
				
				if (zin != null) {
					zin.close();
				}
				if (fin != null) {
					fin.close();
				}
				
				if (zf != null) {
					zf.close();
				}
				if (inputStream != null) {
					inputStream.close();
				}
			} catch (IOException e) {
				e.printStackTrace();
			}
		}
		
	}
	
	private static File createNewFile() {
		File file = new File(OutputStreamMain.class.getResource(".").getPath(),"ziptest.zip");
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
