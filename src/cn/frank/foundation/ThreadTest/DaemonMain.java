package cn.frank.foundation.ThreadTest;

/**
 * 当没有其他被守护线程的时候，守护线程自动结束
 * @author jfan
 *
 */
public class DaemonMain {

	public static void main(String[] args) {

		Thread tmp = new Thread(new Runnable(){

			@Override
			public void run() {

				System.out.println("This is Daemon Thread");
			}
			
		});
		tmp.setDaemon(true);
		System.out.println("Monitor Daemon Thread ====");
		tmp.start();
	}
	

}
