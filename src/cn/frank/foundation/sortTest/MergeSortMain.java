package cn.frank.foundation.sortTest;

import java.util.Arrays;

/**
 * 归并排序的递归算法
 * @author jfan
 *
 */
public class MergeSortMain {

	public static void main(String[] args) {
		String[] ss = { "abc", "acb", "aca" };
		ss = MergeSortMain.mergeSort(ss);
		for (String s : ss) {
			System.out.print(String.format("  %s ", s));
		}
		System.out.println();

		Integer[] is = { 12, 1321, 21, 43, 8564, 23, 21, 1, 0 };
		is = MergeSortMain.mergeSort(is);
		for (Integer iss : is) {
			System.out.print(String.format("  %s ", iss));
		}

	}

	public static <T extends Comparable<? super T>> T[] mergeSort(T[] src) {

		if (src == null || src.length == 1) {
			return src;
		} else {
			int middle = src.length / 2;
			T[] ls = mergeSort(Arrays.copyOfRange(src, 0, middle));
			T[] hs = mergeSort(Arrays.copyOfRange(src, middle, src.length));
			return merge(ls, hs, src.clone());
		}
	}

	public static <T extends Comparable<? super T>> T[] merge(T[] ls, T[] hs,
			T[] t) {
		int i = 0;
		int j = 0;
		int pos = 0;
		while (i < ls.length && j < hs.length) {
			if (ls[i].compareTo(hs[j]) <= 0) {
				t[pos] = ls[i];
				i++;
			} else {
				t[pos] = hs[j];
				j++;
			}
			pos++;
		}

		if (i == ls.length && j < hs.length) {

			for (; j < hs.length; j++) {
				t[pos++] = hs[j];
			}
		} else {
			for (; i < ls.length; i++) {
				t[pos++] = ls[i];
			}
		}

		return t;
	}

}
