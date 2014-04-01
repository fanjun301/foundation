package cn.frank.foundation.ioTest;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.io.Reader;

/**
 * BufferedReader 缓冲区 Scanner可以替代, 
 * CharArrayReader, 
 * FilterReader, -> PushbackReader
 * InputStreamReader -> FileReader, 
 * PipedReader, 
 * StringReader
 * @author jfan
 *
 */
public class ReaderMain {

	public static void main(String[] args) {

		Reader reader = null;
		BufferedReader br = null;
		try {
//		    reader = new InputStreamReader(ReaderMain.class.getResourceAsStream("content.txt"));
			reader = new FileReader(new File(ReaderMain.class.getResource(".").getPath(),"content.txt"));
			br = new BufferedReader(reader,1024); //buffer size = 1024
			
			String newLine = null;
			do {
				newLine = br.readLine();
				if (newLine != null) {
					System.out.println(newLine);
				}
			} while (newLine != null);
			
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}finally{
			try {
				if (reader != null) {
					reader.close();
				}
			} catch (IOException e) {
				e.printStackTrace();
			}
		}
		
	}

}
