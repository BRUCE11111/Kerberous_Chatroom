package UI;

import java.net.Socket;
import java.util.ArrayList;
import java.util.HashMap;

import javax.swing.JTextArea;

import tcpchatroom.OnlineUsers;
import tcpchatroom.Server;

public class V_thread extends Thread{
public JTextArea textArea;
public JTextArea textArea1;
	
V_thread(JTextArea textArea,JTextArea textArea1){
		this.textArea=textArea;
		this.textArea1=textArea1;
	}
	
	public void run() {
		
		
		
		OnlineUsers.ssm = new HashMap<String,Socket>();
		OnlineUsers.sskey=new HashMap<String,Socket>();
		OnlineUsers.onlineUsers  = new ArrayList<String>();
		OnlineUsers.clientList = new ArrayList<Socket>();
		OnlineUsers.name_passwd=new HashMap<String,String>();
		
		
		
		try {
			new Server(5439,textArea,textArea1);
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
  	 
		
		
		
	}

}
