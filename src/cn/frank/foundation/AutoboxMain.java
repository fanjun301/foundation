package cn.frank.foundation;

import java.util.ArrayList;
import java.util.List;

public class AutoboxMain {

	public static void main(String[] args) {

		AutoboxMain.test4Integer();
		AutoboxMain.test4Collection();
	}
	
	public static void test4Collection() {
		
		List<Integer> temp = new ArrayList<Integer>();
		temp.add(1);
		temp.add(2);
		System.out.println(String.format("get Integer list size which add by using int : %s", temp.size()));
	}

	public static void test4Integer(){
		
		Integer i1 = 100;
		Integer i2 = 100;
		System.out.println(i1 == i2);//比较int， -128--127
		
		Integer i3 = 200;
		Integer i4 = 200;
		System.out.println(i3 == i4); //比较的是对象
	}

}
