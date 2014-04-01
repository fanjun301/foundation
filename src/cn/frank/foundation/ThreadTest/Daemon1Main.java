package cn.frank.foundation.ThreadTest;

/**
 * 线程结束后，守护线程也会自动消亡
 * @author jfan
 *
 */
public class Daemon1Main {
	
	public static int number = 0;

	public static void main(String[] args) {

		Thread tmp = new Thread(new Runnable(){

			@Override
			public void run() {
				while (true) {
					number++;
					try {
						Thread.sleep(30*1000);
					} catch (InterruptedException e) {
						e.printStackTrace();
					}
				    if (number == 3) {
						System.out.println("Time is over, get out");
						return;
					}
				}
			}
		});
		
		
		Thread daemon = new Thread(new Runnable(){

			@Override
			public void run() {
				while (true) {
					System.out.println(String.format("daemon monitor number is %s ", number));
					try {
						Thread.sleep(31*1000);
					} catch (InterruptedException e) {
						e.printStackTrace();
					}
				}
			}
			
		});
		
		tmp.start();
		daemon.setDaemon(true);
		daemon.start();
		
	}

}
