package cn.frank.foundation.networkTest;

import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.io.PrintWriter;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.Scanner;

public class ServerSocketMain {

	public static void main(String[] args) {

		ServerSocket serverSocket = null;
		InputStream in = null;
		Scanner scanner = null;
		OutputStream outputStream = null;
		PrintWriter writer = null;
		try {
			serverSocket = new ServerSocket(8189);
			Socket socket = null;
			try {
				socket = serverSocket.accept();
				in = socket.getInputStream();
				outputStream = socket.getOutputStream();
				writer = new PrintWriter(outputStream, true);
				scanner = new Scanner(in);
				boolean endFlag = false;
				while (!endFlag && scanner.hasNextLine()) {
					String txt = scanner.nextLine();
					writer.println(String.format("Received: %s", txt));
					if (txt.equalsIgnoreCase("bye")) {
						writer.println("Receive end flag");
						endFlag = true;
					}
				}
			} finally{
				socket.close();
			}
		} catch (IOException e) {
			e.printStackTrace();
		}finally{
			try {
				if (!serverSocket.isClosed()) {
					serverSocket.close();
				}
				if (scanner != null) {
					scanner.close();
				}
				if (in != null) {
					in.close();
				}
				if (writer != null) {
					writer.close();
				}
				if (outputStream != null) {
					outputStream.close();
				}
			} catch (IOException e) {
				e.printStackTrace();
			}
			
		}
		
	}

}
