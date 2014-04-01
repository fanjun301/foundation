package cn.frank.foundation.networkTest;

import java.io.IOException;
import java.io.InputStream;
import java.net.InetAddress;
import java.net.InetSocketAddress;
import java.net.Socket;
import java.net.UnknownHostException;
import java.util.Scanner;

public class SocketMain {

	public static void main(String[] args) {
		Socket socket = null;
		InputStream inputStream = null;
		Scanner scanner = null;
		try {
//			socket = new Socket("time-A.timefreq.bldrdoc.gov",13);
//			socket.setSoTimeout(10000);
			socket = new Socket();
			InetAddress inetAddress = InetAddress.getByName("time-A.timefreq.bldrdoc.gov");
			socket.connect(new InetSocketAddress(inetAddress, 13));
			socket.setSoTimeout(10000);
			inputStream = socket.getInputStream();
			scanner = new Scanner(inputStream);
			while (scanner.hasNextLine()) {
				System.out.println(scanner.nextLine());
			}
			
		} catch (UnknownHostException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}finally{
			try {
				if (!socket.isClosed()) {
					socket.close();
				}
				if (inputStream != null) {
					inputStream.close();
				}
				if (scanner != null) {
					scanner.close();
				}
			} catch (IOException e) {
				e.printStackTrace();
			}
		}
		
	}
}
