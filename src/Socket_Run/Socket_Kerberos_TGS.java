package Socket_Run;

import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;

import javax.swing.JTextArea;

public class Socket_Kerberos_TGS {

	 public  void begin(JTextArea textarea) {
	        // TODO Auto-generated method stub
	        ServerSocket s = null;
	        try{
	        	 s= new ServerSocket(5434);
	            System.out.println("start TGS service:");
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
	                new TGS_ServerThread(cs,textarea).start();
	                System.out.println("接收了 第"+i+"个请求");
	                i++;
	            }
	            catch(IOException e)
	            {
	                System.out.println(e);
	            }
	        }
	    }
	
}
