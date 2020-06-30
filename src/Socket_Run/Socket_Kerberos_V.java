package Socket_Run;

import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;

public class Socket_Kerberos_V {
	
	boolean flag=false;

	 public static void main(String[] args) {
	        // TODO Auto-generated method stub
	        ServerSocket s = null;
	        try{
	        	s= new ServerSocket(5439);
	            System.out.println("start V service:");
	        }
	        catch(IOException e)
	        {
	            System.out.println(e);
	            System.exit(1);
	        }
	        int i = 1;
	        while(true)
	        {
	            
	            try{
	                Socket cs = s.accept();
	               V_ServerThread thread= new V_ServerThread(cs,s);
	               thread.start();
	               if(thread.jud!="") {
	                System.out.println("接收了 第"+i+"个请求");
	               }
	                i++;
	            }
	            catch(IOException e)
	            {
	                System.out.println(e);
	            }
	        }
	    }
	
}
