package cn.frank.foundation.ThreadTest;

import java.util.concurrent.Semaphore;

public class SemaphoreMain {

	public static void main(String[] args) {

		Semaphore sph = new Semaphore(10);
		try {
			sph.acquire(2);
			System.out.println(String.format("Semaphore alive number : %s", sph.availablePermits()));
			sph.release();
			System.out.println(String.format("Semaphore alive number : %s", sph.availablePermits()));
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
		
	}

}
