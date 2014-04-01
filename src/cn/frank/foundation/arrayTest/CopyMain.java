package cn.frank.foundation.arrayTest;

import java.util.Arrays;

public class CopyMain {

	public static final int[] sources = {1,2,3,4,5,6,7,8,9,0};
	public static void main(String[] args) {

		testByUsingSystem();
		testByUsingArrays();
	}

	private static void testByUsingArrays() {

		int[] alls = Arrays.copyOf(sources, sources.length);
		int[] ranges = Arrays.copyOfRange(sources, 0, sources.length/2);
		System.out.println(String.format("alls length %s", alls.length));
		System.out.println(String.format("ranges length %s", ranges.length));
	}

	private static void testByUsingSystem() {
		int[] dest = new int[10];
        System.arraycopy(sources, 0, dest, 0, sources.length);
        System.out.println(String.format("sys length %s", dest.length));
	}

}
