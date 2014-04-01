package cn.frank.foundation.ThreadTest;

import java.util.concurrent.Callable;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.Future;

public class ExecutorsMain {

	public static void main(String[] args) throws InterruptedException, ExecutionException {
		
		ExecutorService threadPools = Executors.newFixedThreadPool(3);
		Callable<Integer> c = new Callable<Integer>(){

			@Override
			public Integer call() throws Exception {
				System.out.println("Callable was called");
				return 1;
			}
			
		};
		Future<Integer> cs = threadPools.submit(c);
		threadPools.submit(c);
		threadPools.submit(c);
		threadPools.submit(c);
		System.out.println(cs.get());
		threadPools.execute(new Runnable(){

			@Override
			public void run() {

				System.out.println("Runnable was called");
			}
			
		});
		threadPools.shutdown();
	}

}
