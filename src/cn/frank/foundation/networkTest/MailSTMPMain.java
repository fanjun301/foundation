package cn.frank.foundation.networkTest;

import java.io.IOException;
import java.io.PrintWriter;
import java.net.Socket;
import java.net.UnknownHostException;
import java.util.Scanner;

import sun.misc.BASE64Encoder;

public class MailSTMPMain {

	public static void main(String[] args) throws InterruptedException {

		try {
			Socket s = new Socket("smtp.126.com",25);
			
			PrintWriter pw = new PrintWriter(s.getOutputStream(), true);
			Scanner scanner = new Scanner(s.getInputStream());
			
			pw.println("ehlo 126.com");
			
			Thread.sleep(1000);
			System.out.println(scanner.nextLine());
			pw.println("auth login");

			Thread.sleep(1000);
			System.out.println(scanner.nextLine());
			pw.println(new BASE64Encoder().encode("fanjun301".getBytes()));
			
			Thread.sleep(1000);
			System.out.println(scanner.nextLine());
			pw.println(new BASE64Encoder().encode("fj3401221983".getBytes()));
			
			Thread.sleep(1000);
			System.out.println(scanner.nextLine());
			pw.println("mail from: <fanjun301@126.com>");
			
			Thread.sleep(1000);
			System.out.println(scanner.nextLine());
			pw.println("rcpt to: <jfan_sh@sina.com>");
			
			Thread.sleep(1000);
			System.out.println(scanner.nextLine());
			pw.println("data");
			
			Thread.sleep(1000);
			System.out.println(scanner.nextLine());
			String con = "From: fanjun301@126.com\r\n";  
            con += "To: <jfan_sh@sina.com>\r\n";  
            con = con + "Subject: fanjun\r\n";  
            con = con + "Content-Type: text/plain;charset=\"UTF-8\"\r\n";  
            con = con + "\r\n";  
            con = con + "测试\r\n";  
            con = con + ".\r\n";  
            pw.println(con); 
            
            Thread.sleep(1000);
            System.out.println(scanner.nextLine());
            System.out.println(scanner.nextLine());
            scanner.close();
			pw.close();
			s.close();
		} catch (UnknownHostException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

}
