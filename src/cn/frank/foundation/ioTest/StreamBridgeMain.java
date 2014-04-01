package cn.frank.foundation.ioTest;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.io.Reader;
import java.io.Writer;
import java.nio.charset.Charset;

/**
 * InputStreamReader
 * OutputStreamWriter
 * 可以设置编码
 * @author jfan
 *
 */
public class StreamBridgeMain {

	@SuppressWarnings({ "unused", "resource" })
	public static void main(String[] args) throws FileNotFoundException {

		//Bridge between input stream and reader
		Reader reader = new InputStreamReader(StreamBridgeMain.class.getResourceAsStream("content.txt"), Charset.defaultCharset());
		
		//Bridge between output stream and writer
		File file = new File(StreamBridgeMain.class.getResource(".").getPath(),"content.txt");
		FileOutputStream fout = new FileOutputStream(file,true);
		Writer writer = new OutputStreamWriter(fout, Charset.defaultCharset());
		
	}

}
