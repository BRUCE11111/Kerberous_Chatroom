/**
 * 
 */
package tcpchatroom;

/**
 * @author xuxiaohui
 *
 */
import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

import javax.swing.JTextArea;

import RSA.Rsa;
/**
 * 基于TCP的并发多线程聊天室
 * 
 * 
 */
public class Server{
	private ServerSocket ss;
	public JTextArea tx;
	public JTextArea tx1;
	private Rsa rsa=new Rsa();
	Lock lock = new ReentrantLock(); 
//	public static  Map<Socket,String> ssm = new HashMap<Socket,String>();
//	public static ArrayList<String> onlineUsers = new ArrayList<String>(); 
//	public static ArrayList<Socket> clientSocket = new ArrayList<Socket>();
	public Server(int port,JTextArea tx,JTextArea tx1) throws InterruptedException{
		try {
			
			this.tx=tx;
			this.tx1=tx1;
			ss = new ServerSocket(port);
			System.out.println("服务器启动成功！	port=" + ss.getLocalPort());
			int i=0;
			
			
			
			while(true){
				
				
				Socket socket = ss.accept();
				lock.lock();
				
				ServerThread thread = new ServerThread(socket,rsa,tx,tx1,lock);//**********************
				//thread.sleep(i*1600);
				i++;
				thread.start();
				lock.unlock();
				
			}
		} catch (IOException e) {
			// TODO Auto-generated catch block
			System.out.println("服务器启动失败");
			System.out.println(e.getMessage());
		}
		
	}

	public static void main(String[] args) throws InterruptedException {	
		OnlineUsers.ssm = new HashMap<String,Socket>();
		OnlineUsers.sskey=new HashMap<String,Socket>();
		OnlineUsers.onlineUsers  = new ArrayList<String>();
		OnlineUsers.clientList = new ArrayList<Socket>();
		OnlineUsers.name_passwd=new HashMap<String,String>();
		
		
		
		new Server(5439,null,null);
	}
}