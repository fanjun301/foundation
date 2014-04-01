package cn.frank.foundation.ThreadTest;

import java.util.concurrent.CountDownLatch;

/**
 * 第一个countdownlatch 通知各线程工作
 * 第二个countdownlatch 等待各线程结束后汇总
 * @author jfan
 *
 */
public class CountDownLatch1Main {

	public static void main(String[] args) throws Exception
	  {
	    CountDownLatch startSignal = new CountDownLatch(1);
	    CountDownLatch doneSignal = new CountDownLatch(10);
	    for (int i = 0; i < 10; i++)
	    {
	      new Thread(new Worker(startSignal, doneSignal)).start();
	    }
	    System.out.println("-------BEGIN------------");
	    startSignal.countDown();
	    doneSignal.await();
	    System.out.println("-------END------------");
	  }

	  static class Worker implements Runnable
	  {
	    private final CountDownLatch startSingal;
	    private final CountDownLatch endSingal;
	    public Worker(CountDownLatch startSingal, CountDownLatch endSingal)
	    {
	      this.startSingal = startSingal;
	      this.endSingal = endSingal;
	    }
	    @Override
	    public void run()
	    {
	      try
	      {
	        startSingal.await();
	        work();
	        endSingal.countDown();
	      }
	      catch (Exception e)
	      {
	      }
	    }
	    public void work()
	    {
	      System.out.println("work-------------");
	    }
	  }

}
