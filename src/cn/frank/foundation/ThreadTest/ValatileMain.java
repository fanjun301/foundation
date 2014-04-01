package cn.frank.foundation.ThreadTest;

/**
 * TODO test in jdk 6
 * @author jfan
 *
 */
public class ValatileMain {

	public static/**volatile*/ boolean done = false;

	public static void main(String[] args) throws InterruptedException {

		new Thread(new Runnable(){

			@Override
			public void run() {
				
				@SuppressWarnings("unused")
				int i = 0 ;
				while (!done) {
					i ++;
				}
				System.out.println("done");
			}
			
		}).start();
		System.out.println("Frank Fan Testing");
		Thread.sleep(2000);
		done = true;
		System.out.println("done flag was set to true");
	}


}
