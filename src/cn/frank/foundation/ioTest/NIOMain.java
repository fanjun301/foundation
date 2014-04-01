package cn.frank.foundation.ioTest;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.nio.MappedByteBuffer;
import java.nio.channels.FileChannel;
import java.nio.channels.FileChannel.MapMode;

/**
 * 内存映射文件
 * 
 * @author jfan
 *
 */
public class NIOMain {

	public static void main(String[] args) {

		FileInputStream in = null;
		FileChannel channel = null;
		try {
			in = new FileInputStream(new File(NIOMain.class.getResource(".").getPath(),"content.txt"));
			channel = in.getChannel();
			/**
			 * FileChannel.MapMode.READ_WRITE
			 * FileChannel.MapMode.READ_ONLY 
			 * FileChannel.MapMode.PRIVATE 可写，但是不会传播到文件
			 */
			MappedByteBuffer mapBuffer = channel.map(MapMode.READ_ONLY, 0, channel.size());
			
			byte[] b = null;
			do {
				if (mapBuffer.remaining() < 64) {
					b  = new byte[mapBuffer.remaining()];
				}else{
					b = new byte[64];
				}
				mapBuffer.get(b);
				System.out.print(new String(b));
			} while (b != null && b.length > 0);
					
		} catch (IOException e) {
			e.printStackTrace();
		}finally{
			try {
				channel.close();
				in.close();
			} catch (IOException e) {
				e.printStackTrace();
			}
		}
		
	}

}
