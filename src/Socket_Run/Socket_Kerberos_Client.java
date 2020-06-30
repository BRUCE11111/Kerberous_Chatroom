package Socket_Run;

import java.awt.Frame;
import java.io.DataInputStream;

import java.io.DataOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.net.Socket;
import java.net.UnknownHostException;

import C_Run.*;
import tcpchatroom.ClientThread;

public class Socket_Kerberos_Client {

	public C_Process run= new C_Process();
	
	 public String st3="包3";
	 public String st4="包4";
	 public String st5="包5";
	 public String st6="包6";
	 public String st7="包7";
	 public String st8="";
	 public String stas;
	 public String response;
	 public String send_sign;
	 
	 
	 public String name,password,ADc="001",logininfo="";
	 
	 
	 public Socket begin_login(Frame f) throws UnknownHostException, IOException {
		 Socket s = new Socket("127.0.0.1", 5437); 
		 
		 try {

			OutputStream out = s.getOutputStream(); 
			 
			DataOutputStream dout = new DataOutputStream(out);
			InputStream in = s.getInputStream();
			DataInputStream din = new DataInputStream(in);
			
			run.name=this.name;
			run.password=this.password;
			run.ADc=this.ADc;
			
			dout.writeUTF("000000");//告诉服务器，开始证书交换
			
			
			response = din.readUTF(); //接收服务器X.509,33 package
			run.process(response,"",null);//处理33号包
			
			send_sign=run.process("100000","",null);
			dout.writeUTF(send_sign);//send client x.509,32package
			String form=din.readUTF();
			if(!form.equals("ok")) {
				System.out.println("error");
				return s;
			}
			
			if(logininfo.equals("")) {
			String info=run.process("000001","",null);
			dout.writeUTF(info);//send user's infomation
			
			response = din.readUTF();//receive 2 package
			run.process(response,"",f);//process 2 package
			}else {
				String info=run.process("000001",logininfo,null);
				//info=info+"&"+logininfo;
				dout.writeUTF(info);//send user's infomation
				
				response = din.readUTF();//receive 2 package
				run.process(response,"",f);//process 2 package
			}
			
			dout.writeUTF("end");//告诉服务器,交互结束
			
			in.close();
			out.close();
			s.close(); 
			
			} 
			catch (IOException e) 
			{} 
		return s;
	 }
	 
	public void begin_as(String username) throws UnknownHostException, IOException
	{ 

		 Socket	s = new Socket("127.0.0.1", 5437);	

	try
	{ 
		
	 
	OutputStream out = s.getOutputStream(); 
	 
	DataOutputStream dout = new DataOutputStream(out);
	InputStream in = s.getInputStream();
	DataInputStream din = new DataInputStream(in);
	
	dout.writeUTF("000011");

	st3=run.process("000011",null,null);
	st3=st3+username;
	System.out.println(st3+" "+"3号包发送!!!!!!!!!!!!!!!!!!!");
	dout.writeUTF(st3);//3号包
	System.out.println("3号包发送!!!!!!!!!!!!!!!!!!!");
	System.out.println(st3);

	
	st4 = din.readUTF();//4号包
	System.out.println("4号包 收到!!!!!!!!!!!!!!!!!!!"+st4);
	
	 
	System.out.println(stas+" stas!!!!!!!!!!!!!!!!!!!");
	in.close();
	out.close();
	s.close();
	} 
	catch (IOException e) 
	{}

	} 
	
	
	public String begin_tgs(String passwd)
	{ 
	try
	{ 
		Socket s = new Socket("127.0.0.1", 5434); 
		 
		OutputStream out = s.getOutputStream(); 
		 
		DataOutputStream dout = new DataOutputStream(out);
		
		InputStream in = s.getInputStream();
		DataInputStream din = new DataInputStream(in);

	//	dout.writeUTF(");
		
		st5=run.process(st4,passwd,null);

		if(st5.equals("密码错误")) {
			return "密码错误";
		}
		System.out.println("5号包发送!!!!!!!!!!!!!!!!!!!");
		System.out.println(st5);
		dout.writeUTF(st5);//5号包
		
		
		st6 = din.readUTF();//6号包
		
		System.out.println("收到6号包!!!!!!!!!!!!!!!!!!!");
		System.out.println(st6);
		String address=s.getInetAddress().getHostAddress();
		st7=run.process(st6,address,null);
		
		in.close();
		out.close();
		s.close(); 
		return "完成";
	} 
	catch (IOException e) 
	{}
	return "完成"; 
	} 
	
	public Socket begin_v()
	{ 
	try
	{ 
		Socket s = new Socket("127.0.0.1", 5439); 
		
		OutputStream out = s.getOutputStream(); 
		DataOutputStream dout = new DataOutputStream(out);
		System.out.println("7号包发送!!!!!!!!!!!!!!!!!!!");
		System.out.println(st7);
		dout.writeUTF(st7);
		
		InputStream in = s.getInputStream();
		
		DataInputStream din = new DataInputStream(in);
		
		st8 = din.readUTF();//8号包
		System.out.println("8号包收到!!!!!!!!!!!!!!!!!!!");
		System.out.println(st8);
		if(s.isClosed()) {
			System.out.println("fuck !!!");
		
			
		}
		//in.close();
	//	out.close();
		//s.close(); 
		return s;
	} 
	catch (IOException e) 
	{}
	return null; 
	} 
	
	public static void main(String args[]) {
		Socket_Kerberos_Client c=new Socket_Kerberos_Client();
//		

		System.out.println("认证成功");
	}
	
}
