package cn.frank.foundation;

import java.util.Scanner;
import java.util.regex.MatchResult;

/**
 * Scanner(File source) 
 * Scanner(File source, String charsetName) 
 * Scanner(InputStream source) 
 * Scanner(Readable source) 
 * Scanner(ReadableByteChannel source) 
 * Scanner(ReadableByteChannel source, String charsetName)
 * Scanner(String source) 
 * @author jfan
 *
 */
public class ScannerMain {

	public static void main(String[] args) {
		test4Delimiter();
		System.out.println("======================");
		test4Regex();
	}

	private static void test4Regex() {
		String input = "1 fish 2 fish red fish blue fish";
		Scanner s = new Scanner(input);
		s.findInLine("(\\d+) fish (\\d+) fish (\\w+) fish (\\w+)");
		MatchResult result = s.match();
		for (int i = 1; i <= result.groupCount(); i++)
			System.out.println(result.group(i));
		s.close();
	}

	@SuppressWarnings("resource")
	private static void test4Delimiter() {
		String input = "1@2@red@blue@";
		Scanner s = new Scanner(input).useDelimiter("@");
		System.out.println(s.nextInt());
		System.out.println(s.nextInt());
		System.out.println(s.next());
		System.out.println(s.next());
		if (s.hasNext()) {
			System.out.println(s.next());
		}
		s.close();
	}

}
