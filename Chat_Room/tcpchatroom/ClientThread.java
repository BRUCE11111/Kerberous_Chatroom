/**
 * 
 */
package tcpchatroom;

/**
 * @author xuxiaohui
 *
 */

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.UnsupportedEncodingException;
import java.math.BigInteger;
import java.net.Socket;

import javax.swing.JDialog;
import javax.swing.JOptionPane;
import javax.swing.JTextArea;

import DES_METHOD.DES;
import RSA.Rsa;
import factory.Test05;
import tcpchatroom.datagram.ServerDataGramAnalyzer;

/**
 * 基于TCP的并发多线程聊天室
 * 
 * 客户端子线程
 * 接收服务器端发来的消息并显示
 *
 */

public class ClientThread extends Thread{
	private Socket socket;
	private BufferedReader in;
	private JTextArea textArea;
	private JTextArea textArea2;
	private JTextArea textArea3;
	String key;
	String server_key;
	public BigInteger server_e;
	public BigInteger server_n;
    Test05 vc=new Test05();	

	
	public Rsa rsa=new Rsa();
	
	public String get_key() {
		return this.key;
	}
	
	public String get_server_key() {
		return this.server_key;
	}
	
	public ClientThread(JTextArea area,JTextArea area2, JTextArea area3,Socket socket,BufferedReader in) throws IOException{
		this.textArea = area;
		this.textArea2 = area2;
		this.socket = socket;
		this.in = in;
		this.textArea3=area3;
	}
	
	 public String C_DES_decry(String msg,String k) throws UnsupportedEncodingException{
	    	
		    msg=msg.substring(4,msg.length());
	    	
			DES des=new DES();
         
			String decrypttext=des.dec(msg,k);
			
			return decrypttext;
	    	
	    }
	 
	 
	 public String C_DES_decry1(String msg,String k) throws UnsupportedEncodingException{
	    	
		    msg=msg.substring(0,msg.length());
	    	
			DES des=new DES();
      
			String decrypttext=des.dec(msg,k);
			
			return decrypttext;
	    	
	    }

	public void run(){
		try {
			//循环读取
			int times=0;
			while(!this.socket.isClosed()){
				
				String stocdatagram = in.readLine();//************************抛出异常
				switch(ServerDataGramAnalyzer.getType(stocdatagram)){
				
				
				case(0)://收到普通消息
					times++;
//					System.out.println("接收到server 消息:" + stocdatagram);
					//textArea.setComponentOrientation(ComponentOrientation.LEFT_TO_RIGHT);
				    textArea3.append("		历史消息\n\n");
					textArea3.append("密文： "+stocdatagram+"\n");
					
					
					
					String mid=C_DES_decry(stocdatagram,key);
				    stocdatagram="0&&&"+mid;
				    
				    textArea3.append("明文： "+stocdatagram+"\n");
				    
				    //textArea3.append("\n");
				    
				    if(times==6) {
				    	textArea3.append(null);
				    	textArea3.append("       历史消息\n");
				    }
				   
				    String name1=ServerDataGramAnalyzer.getName(stocdatagram);
				    String[] msg=ServerDataGramAnalyzer.getMessage(stocdatagram).split("%%%");
				    stocdatagram=msg[0];
				    String hash=msg[1];
				    String sign=msg[2];
				    
				    boolean flag=rsa.confirm_sign_neww(sign, msg[0]);
				    if(flag==false) {
				    	 JOptionPane pane = new JOptionPane("消息抵赖");
				         JDialog dialog  = pane.createDialog("警告");
				         dialog.show();
				    }
				    
				    textArea3.append("明文： "+stocdatagram+"\n");
				    textArea3.append("签名内容： "+sign+"\n");
				    textArea3.append("\n");
				    
				    vc.voice(msg[0]);
					textArea.append(name1+ ":"  + msg[0]+"\n");
					break;
				
				case(4)://接收秘钥
					
					String[] key_des=stocdatagram.split("&&&");
				    key=key_des[1];
					
					
					
				case(1)://登陆成功 接受好友列表	
					
					rsa.begin();
					
				    System.out.println(stocdatagram);
				
					String [] usernames = ServerDataGramAnalyzer.getNames(stocdatagram);
					for(int i = 1 ;i < usernames.length;i++){
						OnlineUsers.onlineUsers.add(usernames[i]);
						textArea2.append(usernames[i]+"\n");
//						System.out.println("at client thread 接受好友列表:" + OnlineUsers.onlineUsers.get(i-1));
					}
					
//					String server_pubs=in.readLine();
//					String str[]=server_pubs.split("&");
//					server_e=new BigInteger(str[0]);
//					server_n=new BigInteger(str[1]);
//					
					server_key=in.readLine();
					
					key=in.readLine();
					
					 JOptionPane pane1 = new JOptionPane("秘钥交换成功");
				      JDialog dialog1  = pane1.createDialog("恭喜");
				      dialog1.show();
					
					
					
					break;
					//显示好哟列表
				case(2)://登陆失败
					JOptionPane.showMessageDialog(null,"登录失败", "信息错误",JOptionPane.ERROR_MESSAGE);break;
				case(3)://重复登陆
					System.out.println(stocdatagram);
					JOptionPane.showMessageDialog(null,"已经在线上", "信息错误",JOptionPane.ERROR_MESSAGE);break;
				case(5)://注册重复
					JOptionPane.showMessageDialog(null,"用户名已经注册过", "信息错误",JOptionPane.ERROR_MESSAGE);break;
				case(6):// 用户退出 重新显示列表
					OnlineUsers.onlineUsers.remove(ServerDataGramAnalyzer.getName(stocdatagram));//列表显示***************************************
					
				System.out.println(stocdatagram);
				
				vc.voice(ServerDataGramAnalyzer.getName(stocdatagram)+"下线了");
				
				textArea.append(ServerDataGramAnalyzer.getName(stocdatagram)+"  下线了"+ "\n");
					textArea2.setText(null);
					textArea2.append("		好友列表\n");
					
					for (String name : OnlineUsers.onlineUsers){
						System.out.println("******" + name);
						textArea2.append(name + "\n");
					}
					
				break;
				case(7)://设置在线状态
					break;//**********************************
				case(8)://用户上线提醒
					
					OnlineUsers.onlineUsers.add(ServerDataGramAnalyzer.getName(stocdatagram));
					//textArea.setComponentOrientation(ComponentOrientation.LEFT_TO_RIGHT);
					//每次接收到消息 都要打印一下好友列表:
					vc.voice(ServerDataGramAnalyzer.getName(stocdatagram)+"上线了");
				textArea.append(ServerDataGramAnalyzer.getName(stocdatagram)+"  上线了"+ "\n");
					textArea2.append(ServerDataGramAnalyzer.getName(stocdatagram)+ "\n");
					break;
				case(9)://传递文件
					
					String[] file_arr=stocdatagram.split("&&&");
				String filename=file_arr[1];//获取文件名
					
				System.out.println(filename+"   &&&&&&%%%%%%");
				//服务器先接受并存储文件
			//	BufferedReader bufr=new BufferedReader(new InputStreamReader(socket.getInputStream()));
		        
		        BufferedWriter bufw=new BufferedWriter(new FileWriter("F:\\Desktop\\测试\\"+filename));
		        
		        String line=null;
		        
		        while((line=in.readLine())!=null)//文件接收
		        {
		        	textArea3.append(line+"\n");
		        	line=C_DES_decry1(line,key);
		        	textArea3.append(line+"\n");
		        	if(line.equals("complect")) {
		        	
		        	break;
		        	}
		        	
		            bufw.write(line);
		            bufw.newLine();
		            bufw.flush();
		        }
		        
		        bufw.close();
		       // bufr.close();
				
		        JOptionPane pane = new JOptionPane("接收到文件： "+filename);
		   	      JDialog dialog  = pane.createDialog("恭喜");
		   	      dialog.show();
					
					
					
					break;	
					
				case(10)://接收被发送方的秘钥
					
					String[] file_arr1=stocdatagram.split("&&&");
					String text=file_arr1[1];
					 // String text=in.readLine();
			      //  System.out.println(text);
			        System.out.println(text);
			        String endmsg="a";
			        
			        if(text.equals(endmsg)) {//如果文件发送成功，则提醒对话框
			        	 JOptionPane pane11 = new JOptionPane("发送成功");
			   	      JDialog dialog11  = pane11.createDialog("恭喜");
			   	      dialog11.show();
		
			        }
					
					break;//**********************************	
					
					
					
				}
				
			
//				System.out.println("在线好友数:"+OnlineUsers.onlineUsers.size() + "   好友列表:\n");
//				for (String  username: OnlineUsers.onlineUsers){
//					System.out.println( "  "+ username + "、");
//				}
				
			}
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
}