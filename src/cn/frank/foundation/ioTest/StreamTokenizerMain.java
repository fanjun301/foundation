package cn.frank.foundation.ioTest;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.StreamTokenizer;
import java.util.HashMap;
import java.util.Map;
import java.util.Map.Entry;
import java.util.Set;

public class StreamTokenizerMain {

	public static void main(String[] args) {

		
		InputStream in = StreamTokenizerMain.class.getResourceAsStream("content.txt");
		BufferedReader br = new BufferedReader(new InputStreamReader(in));
		StreamTokenizer token = new StreamTokenizer(br);
		token.ordinaryChar('\''); // 单引号 's
		token.ordinaryChar('\"'); // 双引号 "Fan Jun"
		token.ordinaryChar('.'); // . Frank.Fan
		token.ordinaryChar('-'); // - Frank-Fan
		
		Map<String, Integer> counts = new HashMap<String,Integer>();
		Integer countLine = 0 ;
		
		try {
			String s = null;
			while (token.nextToken() != StreamTokenizer.TT_EOF) {
				s = null;
				switch (token.ttype) {
				case StreamTokenizer.TT_EOL:
					countLine++ ;
					break;
				case StreamTokenizer.TT_NUMBER:
					s = String.valueOf(token.nval);
					break;
				case StreamTokenizer.TT_WORD:
					s = token.sval;
                    break;
				default:
					break;
				}
				
				if (counts.get(s) != null) {
					counts.put(s, counts.get(s)+1);
				}else {
					counts.put(s, 1);
				}
			}
			
			System.out.println(String.format("Have %s line", countLine));
			Set<Entry<String, Integer>> entrySet = counts.entrySet();
			for (Entry<String, Integer> entry : entrySet) {
				System.out.println(String.format("Word : %s | count: %s", entry.getKey(), entry.getValue()));
			}
			
			
		} catch (IOException e) {
			e.printStackTrace();
		}finally{
			try {
				br.close();
				in.close();
			} catch (IOException e) {
				e.printStackTrace();
			}
		}
		System.out.println("========StreamTokenizer can be used for calculate word counts=========");
		
	}

}
