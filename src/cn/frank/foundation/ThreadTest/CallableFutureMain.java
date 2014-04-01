package cn.frank.foundation.ThreadTest;

import java.util.concurrent.Callable;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.FutureTask;

public class CallableFutureMain {

	public static void main(String[] args) {

		FutureTask<Integer> ft = new FutureTask<Integer>(new Callable<Integer>(){

			@Override
			public Integer call() throws Exception {
				System.out.println("Callable was called");
				Thread.sleep(30*1000);
				System.out.println("Callable completed");
				return 1;
			}
			
		});
		Thread t = new Thread(ft);
		t.start();
		while (!ft.isDone()) {
			System.out.println("Not completed yet");
			try {
				Thread.sleep(10*1000);
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
		}
		try {
			System.out.println(ft.get());
		} catch (InterruptedException e) {
			e.printStackTrace();
		} catch (ExecutionException e) {
			e.printStackTrace();
		}
		
	}

}
