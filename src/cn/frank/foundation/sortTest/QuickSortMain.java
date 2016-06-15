package cn.frank.foundation.sortTest;

public class QuickSortMain {

	public static void main(String[] args) {

		String[] ss = {"abc","acb","aca"};
		QuickSortMain.quickSort(ss, 0, ss.length-1);
		for (String s : ss) {
			System.out.print(String.format("  %s ", s));
		}
		System.out.println();
		
		Integer[] is = {12,1321,21,43,8564,23,1,0};
		QuickSortMain.quickSort(is, 0, is.length-1);
		for (Integer iss : is) {
			System.out.print(String.format("  %s ", iss));
		}
	}

	public static <T extends Comparable<? super T>>void quickSort(T[] ts, int start, int end) {
		
		if (ts == null || ts.length <=1) {
			return ;
		}
		
		int position = findFirstSortPosition(ts,start, end);
		if (position > start) {
			quickSort(ts, start, position -1);
		}
		if (position < end) {
			quickSort(ts, position+1, end);
		}
		
	}
	
	public static <T extends Comparable<? super T>> int findFirstSortPosition(T[] ts, int start, int end){
		
		T t = ts[start];
		while (start < end) {
            while (start < end && ts[end].compareTo(t) >= 0) {
				end--;
			} 			
			ts[start] = ts[end];
			while (start < end && ts[start].compareTo(t) <= 0) {
				start++;
			}
			ts[end] = ts[start];
		}
        ts[start] = t;		
		return start;
	}
	

}
