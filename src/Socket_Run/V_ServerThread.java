package Socket_Run;

import java.io.DataInputStream;

import V_Run.*;
import tcpchatroom.OnlineUsers;
import tcpchatroom.Server;
import tcpchatroom.ServerThread;

import java.io.DataOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.ArrayList;
import java.util.HashMap;



public class V_ServerThread extends Thread {
	
	public String jud="";

	 static String hello = "From V Server: ";
	    Socket sock;
	    ServerSocket server;
	    
	    public V_ServerThread(Socket s,ServerSocket se)
	    {
	        sock =s ;
	        server=se;
	    
	    }
	    
	    
	    public void run()
	    {
	        try{
	            InputStream in = sock.getInputStream();
	            DataInputStream din = new DataInputStream(in);
	            String st7  = din.readUTF();
	            System.out.println("7号包收到!!!!!!!!!!!!!");
	            System.out.println(st7);
	            V_Process V=new V_Process();
	            
	            String st8=V.process(st7);
	            System.out.println("8号包发送!!!!!!!!!!!!!");
	            System.out.println(st8);
	            OutputStream out = sock.getOutputStream();
	            DataOutputStream dos = new DataOutputStream(out);
	            dos.writeUTF(st8);
	            jud=st8;
	            
	            
	           
	            in.close();
	            out.close();
	            sock.close();
	        }
	        catch(Exception e)
	        {
	            System.out.println(e);
	        }
	        
	        
	    }
}
