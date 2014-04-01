package cn.frank.foundation.ioTest;

import java.io.File;
import java.io.FilenameFilter;

public class FileMain {

	public static void main(String[] args) {

		File file = new File("c:\\");
		String[] intellijFolders = file.list(new FilenameFilter(){

			@Override
			public boolean accept(File dir, String name) {
				System.out.println(dir.getPath()+name);
				return name.indexOf("Intel") > -1;
			}
			
		});
		System.out.println("======================");
		for (String intellijFolder : intellijFolders) {
			System.out.println(String.format("this is filter result : %s", intellijFolder));
		}
		
	}

}
