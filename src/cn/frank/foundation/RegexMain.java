package cn.frank.foundation;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class RegexMain {

	public static void main(String[] args) {

		Pattern pattern = Pattern.compile("^Frank.*regex$",Pattern.CASE_INSENSITIVE);
		Matcher ms = pattern.matcher("Frank Fan test for normal regex");
		System.out.println(ms.matches());
		
		String str = "Frank@Fan@Test";
		pattern = Pattern.compile("@");
		String[] strs = pattern.split(str);
		for (String s : strs) {
			System.out.println(s);
		}
		
		//反向模式
		str = str.replaceAll("^(.*)@(.*)@(.*)$", "$3@$2@$1");
		System.out.println(str);
	}

}
