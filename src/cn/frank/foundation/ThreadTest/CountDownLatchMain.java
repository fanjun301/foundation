package cn.frank.foundation.ThreadTest;

import java.util.concurrent.CountDownLatch;

/**
 * 通知各线程一起开始工作
 * @author jfan
 *
 */
public class CountDownLatchMain {

	public static void main(String[] args) {

		CountDownLatch cdl = new CountDownLatch(2);
		class MyThread extends Thread{
			private CountDownLatch cdl;
			private String name;
			public MyThread(CountDownLatch cdl,String name){
				this.cdl = cdl;
				this.name = name;
			}

			@Override
			public void run() {
				System.out.println(String.format("Thread %s run before await", name));
				try {
					this.cdl.await();
				} catch (InterruptedException e) {
					e.printStackTrace();
				}
				System.out.println(String.format("Thread %s run end await", name));
			}
		}
		
		MyThread t1 = new MyThread(cdl,"1");
		MyThread t2 = new MyThread(cdl,"2");
		MyThread t3 = new MyThread(cdl,"3");
		t1.start();
		t2.start();
		t3.start();
		try {
			Thread.sleep(5*1000);
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
		System.out.println("count down once");
		cdl.countDown();
		try {
			Thread.sleep(5*1000);
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
		System.out.println("count down twice");
		cdl.countDown();
		
	}

}
