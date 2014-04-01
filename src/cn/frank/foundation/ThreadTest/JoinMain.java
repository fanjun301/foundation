package cn.frank.foundation.ThreadTest;

/**
 * join等待另外一个线程结束，才开始本线程的代码
 * @author jfan
 *
 */
public class JoinMain {

	public static void main(String[] args) {

		final Thread t1 = new Thread(new Runnable(){

			@Override
			public void run() {
                 for (int i = 0; i < 1000; i++) {
					System.out.println(String.format("Thread 1 : %s", i));
				}
			}
			
		});
		Thread t2 = new Thread(new Runnable(){

			@Override
			public void run() {
				
				try {
					t1.join();
				} catch (InterruptedException e) {
					e.printStackTrace();
				}
				
				for (int i = 0; i < 1000; i++) {
					System.out.println(String.format("Thread2 : %s ", i));
				}
			}
			
		});
		
		t1.start();
		t2.start();
		
	}

}
