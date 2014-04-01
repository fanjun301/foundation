package cn.frank.foundation.ioTest;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.RandomAccessFile;
import java.nio.MappedByteBuffer;
import java.nio.channels.FileChannel;
import java.nio.channels.FileChannel.MapMode;

public class NIO2Main {

	public static void main(String[] args) {
		RandomAccessFile file = null;
		try {
			file = new RandomAccessFile(new File(NIO2Main.class.getResource(".").getPath(),"content2.txt"), "rw");
			FileChannel channel = file.getChannel();
			MappedByteBuffer mapBuffer = channel.map(MapMode.READ_WRITE, 0, channel.size());
			
			mapBuffer.rewind(); //重写
			mapBuffer.put("Fan Jun test for memory".getBytes());
			mapBuffer.flip();
			mapBuffer.force();
			
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}finally{
			try {
				file.close();
			} catch (IOException e) {
				e.printStackTrace();
			}
		}
	}
}
