package cn.frank.foundation;

import java.awt.AWTException;
import java.awt.Robot;
import java.awt.event.KeyEvent;
import java.util.Date;

public class RobotMain {

	public static void main(String[] args) {

		try {
			Robot root = new Robot();
			while (true) {
				root.keyPress(KeyEvent.VK_SHIFT);
				root.keyRelease(KeyEvent.VK_SHIFT);
				System.out.println(String.format("Shift key was pressed at %s ", new Date()));
				Thread.sleep(5*60*1000);
			}
		} catch (AWTException e) {
			e.printStackTrace();
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
	}

}
