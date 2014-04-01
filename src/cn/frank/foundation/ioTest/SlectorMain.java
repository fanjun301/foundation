package cn.frank.foundation.ioTest;

import java.io.IOException;
import java.net.InetSocketAddress;
import java.net.ServerSocket;
import java.nio.ByteBuffer;
import java.nio.channels.SelectableChannel;
import java.nio.channels.SelectionKey;
import java.nio.channels.Selector;
import java.nio.channels.ServerSocketChannel;
import java.nio.channels.SocketChannel;
import java.util.Iterator;

/**
 * 1，Selector 选择器，完成主要的选择功能。select()， 并保存有注册到他上面的通道集合。
 * 2，SelectableChannel 可被注册到Selector上的通道。
 * 3，SelectionKey 描述一个Selector和SelectableChannel的关系。并保存有通道所关心的操作
 * @author jfan
 *
 */
public class SlectorMain {

	public static void main(String[] args) {

		Thread server = new Thread(new Runnable(){

			@Override
			public void run() {
				
				try {
					ServerSocketChannel serverChannel = ServerSocketChannel.open();
					ServerSocket serverSocket = serverChannel.socket();
					Selector selector = Selector.open();

					serverSocket.bind(new InetSocketAddress(8189));
					serverChannel.configureBlocking(false);
					serverChannel.register(selector, SelectionKey.OP_ACCEPT);
					
					while (true) {
						int select = selector.select();
						if (select == 0) {
							continue ;
						}
						
						Iterator<SelectionKey> it = selector.selectedKeys().iterator();
						while (it.hasNext()) {
							SelectionKey key = it.next();
							// Is a new connection coming in? 
							if (key.isAcceptable()) { 
								System.out.println("======set up=====");
								ServerSocketChannel server = (ServerSocketChannel) key.channel(); 
								SocketChannel channel = server.accept(); 
								registerChannel(selector, channel, SelectionKey.OP_READ);
								//sayHello(channel);
							}
							
							if (key.isReadable()) { 
								readDataFromSocket(key);
							}
							
							if (key.isWritable()) {
//								sayHello((SocketChannel) key.channel());
							}
							it.remove();
						}
					}
					
				} catch (IOException e) {
					e.printStackTrace();
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
			
		});
		
		server.start();
	}
	
	public static void readDataFromSocket(SelectionKey key) throws Exception {
		SocketChannel socketChannel = (SocketChannel) key.channel(); 
		int count; 
        ByteBuffer buffer = ByteBuffer.allocate(1024);		
        buffer.clear();
        while ((count = socketChannel.read(buffer)) > 0) {
        	buffer.flip();
        	while (buffer.hasRemaining()) {
        		socketChannel.write(buffer);
			}
        	buffer.clear();
        }
        if (count < 0) { 
        	socketChannel.close(); 
        }
	}
	
	public static void registerChannel(Selector selector, SelectableChannel channel, int ops) throws Exception {
		if (channel == null) { 
			return; 
		}
		
		// Set the new channel nonblocking 
		channel.configureBlocking(false); 
		// Register it with the selector 
		channel.register(selector, ops); 
	}
	
	public static void sayHello(SocketChannel channel) throws Exception { 
		ByteBuffer buffer = ByteBuffer.allocate(128);
		buffer.clear(); 
		buffer.put("Hi there!\r\n".getBytes()); 
		buffer.flip(); 
		channel.write(buffer); 
	}

}
