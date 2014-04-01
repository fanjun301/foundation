package cn.frank.foundation.ioTest;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.io.Writer;

public class WriterMain {

	public static void main(String[] args) {

		Writer writer = null;
		BufferedWriter bw = null;
		PrintWriter pw = null;
		try {
			File file = createNewFile();
			writer = new FileWriter(file, false); //append flag
			bw = new BufferedWriter(writer, 1024);
			bw.write("Frank Fan test write with append flag with false");
			bw.newLine();
			bw.flush();
			
			pw = new PrintWriter(file); //autoFlush false
			pw.println("Frank Fan test writer with PrintWriter");
			pw.flush();
			
		} catch (IOException e) {
			e.printStackTrace();
		} finally{
			try {
				if (writer != null) {
					writer.close();
				}
				if (bw != null) {
					bw.close();
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
		File file = new File(OutputStreamMain.class.getResource(".").getPath(),"WriterTest.txt");
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
