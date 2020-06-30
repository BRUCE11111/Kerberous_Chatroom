package Socket_Run;

import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.net.Socket;

import javax.swing.JTextArea;

import TGS_Run.TGS_Process;

public class TGS_ServerThread extends Thread {

	 static String hello = "From TGS Server: ";
	    Socket sock;
	    public JTextArea text;
	    
	    public TGS_ServerThread(Socket s,JTextArea te)
	    {
	        sock =s ;
	        text=te;
	    }
	    public void run()
	    {
	        try{
	            InputStream in = sock.getInputStream();
	            DataInputStream din = new DataInputStream(in);
	            String st5 = din.readUTF();
	            
	            System.out.println("5号包收到!!!!!!!!!!!!!"+st5);
	            
	            text.append(sock.getInetAddress().toString()+" 原文： "+st5+"\n");
	            
	            
	            TGS_Process TGS=new TGS_Process();
	            
	            String st6=TGS.process(st5,text);
	            text.append(sock.getInetAddress().toString()+" 6号密文： "+st6+"\n");
	            
	            OutputStream out = sock.getOutputStream();
	            DataOutputStream dos = new DataOutputStream(out);
	            System.out.println("6号包发送!!!!!!!!!!!!!"+st6);
	            dos.writeUTF(st6);
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
