package cn.frank.foundation.ThreadTest;

import java.util.concurrent.BrokenBarrierException;
import java.util.concurrent.CyclicBarrier;

/**
 * 在某一个点汇总
 * @author jfan
 *
 */
public class CyclicBarrierMain {

	public static void main(String[] args) {

		final CyclicBarrier cb = new CyclicBarrier(2);
		class MyThread extends Thread{
			private CyclicBarrier cb;
			private String name;
			public MyThread(CyclicBarrier cdl,String name){
				this.cb = cdl;
				this.name = name;
			}

			@Override
			public void run() {
				try {
					Thread.sleep(30*1000);
					System.out.println(String.format("Thread %s job done", name));
					this.cb.await();
				} catch (InterruptedException e) {
					e.printStackTrace();
				} catch (BrokenBarrierException e) {
					e.printStackTrace();
				}
			}
		}
		
		MyThread t1 = new MyThread(cb,"1");
		MyThread t2 = new MyThread(cb,"2");
		
		new Thread(new Runnable(){

			@Override
			public void run() {
				try {
					while (cb.await() != 0) {
						System.out.println("all jobs not done, waiting...");
						Thread.sleep(3*1000);
					}
					System.out.println("all jobs done");
				} catch (InterruptedException e) {
					e.printStackTrace();
				} catch (BrokenBarrierException e) {
					e.printStackTrace();
				}				
			}
			
		}).start();
		t1.start();
		t2.start();
	}

}
