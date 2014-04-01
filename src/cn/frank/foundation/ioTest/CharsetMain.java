package cn.frank.foundation.ioTest;

import java.nio.ByteBuffer;
import java.nio.CharBuffer;
import java.nio.charset.Charset;
import java.util.SortedMap;

public class CharsetMain {

	public static void main(String[] args) {

		System.out.println("==============Available Charsets=======================");
		SortedMap<String, Charset> availableCharsets = Charset.availableCharsets();
		for (String key : availableCharsets.keySet()) {
			System.out.println(String.format("Charset name value: %s", key));
		}
		
		System.out.println("===================Default Charset==================");
		Charset defaultCharset = Charset.defaultCharset();
		System.out.println(String.format("Default Charset : %s", defaultCharset.name()));
		
		System.out.println("==================Encoding===================");
		Charset utf8 = Charset.forName("UTF-8");
		ByteBuffer encodeStr = utf8.encode("Frank Fan test for encoding");
		byte[] encodeArr = encodeStr.array();
		for (byte b : encodeArr) {
			System.out.print(b);
		}
		System.out.println();

		System.out.println("==================Decoding===================");
		ByteBuffer wrap = ByteBuffer.wrap(encodeArr);
		CharBuffer decodeStr = utf8.decode(wrap);
		System.out.println(decodeStr);
	}

}
