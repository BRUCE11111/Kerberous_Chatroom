package Socket_Run;

import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.net.Socket;

import javax.swing.JTextArea;

import AS_Run.AS_Process;

public class AS_ServerThread extends Thread {
	
	 static String hello = "From AS Server: ";
	    Socket sock;
	    String address;
	    String pubs;
	    public JTextArea textArea;
	    
	    public AS_ServerThread(Socket s,JTextArea textArea)
	    {
	    	this.textArea=textArea;
	        sock =s ;
	        address=sock.getInetAddress().getHostAddress();
	        
	    }
	    
	    
	    public void run()
	    {
	    	
	    	
	        try{
	            InputStream in = sock.getInputStream();
	            DataInputStream din = new DataInputStream(in);
	            OutputStream out = sock.getOutputStream();
	            DataOutputStream dos = new DataOutputStream(out);
	            AS_Process AS=new AS_Process();
	            
	            String rec_mission;
	            while((rec_mission=din.readUTF())!="end") {
	            	
	            	System.out.println("process begin");
	            	if(rec_mission=="end") {
	            		System.out.println(" as end ");
	            		textArea.append(sock.getInetAddress().toString()+": "+rec_mission+"\n");
	            		
	            		break;
	            	}
	            	textArea.append(sock.getInetAddress().toString()+" 原文: "+rec_mission+"\n");
	            	System.out.println(rec_mission+" ####$#$#$#$#");
	            int jud=judge_nums(rec_mission);
	            System.out.println(jud);
	            switch(jud) {
	            
	            case 0:{
	            	
	            	System.out.println("this is as 0 process");
 
	 	            pubs=AS.process("100001", address,this.textArea);//send the public key to the client
	 	            dos.writeUTF(pubs);//send confirm information
	 	            
	 	            
	 	            String name1 = din.readUTF();//receive the public key form the client 
	 	            System.out.println(name1+" 到底怎么回事");
	 	           String form=AS.process(name1, address,this.textArea);//process 32 package
	 	          dos.writeUTF(form);
	 	           
	 	          name1 = din.readUTF();//receive the user's info from the client 
		            System.out.println(name1+" 到底怎么回事");
		            String response=AS.process(name1, address,this.textArea);
		            dos.writeUTF(response);//send 2 package to confirm registion
		            //Decipher deph=new Decipher();
	 	           
	 	           
	            }
	 	           case 3:{
	 	        	   
	 	        	  
	 	        	 
	 		            
	 		            //kerberos 
	 		           String name1 = din.readUTF();
	 		          textArea.append(sock.getInetAddress().toString()+" 原文: "+name1+"\n");
	 		            System.out.println("3号包收到!!!!!!!!!!!!!");
	 		            System.out.println(name1);	
	 		            
	 		            
	 		            String Packet4=AS.process(name1, address,this.textArea);
	 		                       	           
	 		           textArea.append(sock.getInetAddress().toString()+" 4号原文: "+Packet4+"\n");
	 		            System.out.println("4号包发送!!!!!!!!!!!!!");
	 		            System.out.println(Packet4);	
	 		            dos.writeUTF(Packet4);
	 	        	   
	 	           }
	 	           default :{
	 	        	   System.out.println("as receive error");
	 	           }
	 	           
	            }
	            
	            
	            
	            }//while
	            
	           
	           
	           
              
	            
	            in.close();
	            out.close();
	            sock.close();
	        }
	        catch(Exception e)
	        {
	            System.out.println(e);
	        }
	    }
	
	    public int judge_nums(String msg) {
	    	
	        int num=6;
	    	
	    	String juge="";
	    	int mid=0;
	    	for(int i=0;i<num;i++) {
	    		char c=msg.charAt(i);
	    		
	    		if(i==(num-1)) {
	    		if(c=='0') {
	    			continue;
	    		}
	    		if(c=='1') {
	    			mid=mid+1;
	    			
	    		}
	    		}else {
	    			
	    			if(c=='0') {
	    				continue;
	    			}
	    			if(c=='1') {
	    				mid=mid+(int)Math.pow(2, (num-1-i));
	    			}
	    			
	    			
	    		}
	    	}
	    	
	    	return mid;
	    }
	    
	    
}
