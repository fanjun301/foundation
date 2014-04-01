package cn.frank.foundation.ioTest;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.nio.ByteBuffer;
import java.nio.channels.FileChannel;

/**
 * 直接拷贝
 * 直接写入
 * @author jfan
 *
 */
public class NIO1Main {

	public static void main(String[] args) {

		FileInputStream fin = null;
		FileOutputStream fout = null;
		try {
			fin = new FileInputStream(new File(NIOMain.class.getResource(".").getPath(),"content.txt"));
			fout = new FileOutputStream(createNewFile(), true);
			
			FileChannel srcChannel = fin.getChannel();
			FileChannel destChannel = fout.getChannel();
			destChannel.transferFrom(srcChannel, 0, srcChannel.size());
			
			ByteBuffer bf = ByteBuffer.allocate(1024);
			bf.put("\n樊俊给你爆点料".getBytes());
			bf.flip(); 
			
			destChannel.write(bf);
			
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}finally{
			try {
				if (fin != null) {
					fin.close();
				}
				if (fout != null) {
					fout.close();
				}
			} catch (IOException e) {
				e.printStackTrace();
			}
		}
	}
	
	private static File createNewFile() {
		File file = new File(OutputStreamMain.class.getResource(".").getPath(),"content1.txt");
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
