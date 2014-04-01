package cn.frank.foundation.ThreadTest;

import java.util.Date;

/**
 * 如果线程被阻塞，就无法检测到中断状态
 * sleep wait上调用intterupt方法时，阻塞调用intteruptedException异常中断
 * 
 * 中断只不过是引起它的注意，被中断的线程可以决定如何响应中断
 * @author jfan
 *
 */
public class InterruptMain {

	public static void main(String[] args) throws InterruptedException {

		InterruptMain.MyThread myThread = new InterruptMain.MyThread(); 
		myThread.start();
//		Thread.sleep(60*1000);
//		myThread.interrupt();
	}

	public static class MyThread extends Thread {

		@Override
		public void run() {
			while (true) {
				if (Thread.currentThread().isInterrupted()) {
					System.out.println("Thread was interrupted, exit");
					return;
				}
				System.out.println(new Date());
				Thread.currentThread().interrupt();
//				try {
//					Thread.sleep(60*1000);
//				} catch (InterruptedException e) {
//					e.printStackTrace();
//				}
			}
		}

	}

}
