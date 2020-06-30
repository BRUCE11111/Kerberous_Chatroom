package Socket_Run;

import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.net.Socket;
import Control_Package.First_ctol;;

public class Socket_Register {

	 public First_ctol first_ctol;
	 public String st="°ü1";
	 public String st1;
	public void begin()
	{ 
	try
	{ 
	Socket s = new Socket("127.0.0.1", 5432); 
	 
	OutputStream out = s.getOutputStream(); 
	 
	DataOutputStream dout = new DataOutputStream(out);
	 
	dout.writeUTF(st);
	
	 
	InputStream in = s.getInputStream();
	DataInputStream din = new DataInputStream(in);
	 
	st1 = din.readUTF();
	 
	System.out.println(st1);
	in.close();
	out.close();
	s.close(); 
	} 
	catch (IOException e) 
	{} 
	} 
	public static void main(String args[]) {
		Socket_Register s =new Socket_Register();
		s.begin();
	
	}
	
}
