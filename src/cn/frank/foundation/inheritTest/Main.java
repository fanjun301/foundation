package cn.frank.foundation.inheritTest;

import java.io.IOException;

public class Main {

	public static void main(String[] args) throws IOException {

		test4Inherit();
		test4DynamicLoad();
		
	}

	private static void test4DynamicLoad() throws IOException {

		Parent s = new Son();
		Parent d = new Daughter();
		System.out.println("should call son's method implementation");
		s.pne();
		System.out.println("s d class name should not equal");
		System.out.println(String.format("s class %s - d class %s - ==test %s", s.getClass(), d.getClass() ,s.getClass() == d.getClass()));
		
	}

	private static void test4Inherit() {
		System.out.println(Son.P_VAR);
		Son.ps();
	}

}
