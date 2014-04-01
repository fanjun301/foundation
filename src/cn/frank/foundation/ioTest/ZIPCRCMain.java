package cn.frank.foundation.ioTest;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.PrintStream;
import java.nio.charset.Charset;
import java.util.zip.CRC32;
import java.util.zip.CheckedOutputStream;
import java.util.zip.ZipEntry;
import java.util.zip.ZipOutputStream;

public class ZIPCRCMain {

	public static void main(String[] args) throws IOException {

		File file = createNewFile();
		FileOutputStream fout = new FileOutputStream(file);
		ZipOutputStream zout = new ZipOutputStream(fout, Charset.defaultCharset());
		
		CRC32 crc32 = new CRC32();
		CheckedOutputStream checkou = new CheckedOutputStream(zout,crc32);
		PrintStream pw = new PrintStream(checkou);
		
		ZipEntry ze = new ZipEntry("crc.txt");
		zout.putNextEntry(ze);
		
		pw.println("汉字测试");
		pw.flush();
		
		zout.closeEntry();		
		
		//simulate not follow try catch finally structure
		pw.close();
		checkou.close();
		zout.close();
		fout.close();
		
		System.out.println(crc32.getValue());
	}
	
	private static File createNewFile() {
		File file = new File(OutputStreamMain.class.getResource(".").getPath(),"zipcrc.zip");
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
