/**
 * 
 */
package tcpchatroom;

/**
 * @author xuxiaohui
 *
 */

import java.awt.Container;
import java.awt.FlowLayout;
import java.awt.Graphics;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.WindowEvent;
import java.awt.event.WindowListener;
import java.awt.image.BufferedImage;
import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.io.UnsupportedEncodingException;
import java.math.BigInteger;
import java.net.Socket;
import java.util.ArrayList;

import javax.imageio.ImageIO;
import javax.swing.DefaultListModel;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JFileChooser;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JList;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;
import javax.swing.JTextField;

import DES_METHOD.DES;
import DES_METHOD.test;
import RSA.Rsa;
import Socket_Run.Socket_Kerberos_Client;
import tcpchatroom.datagram.ClientDataGram;
import vo.User;

/**
 * 基于TCP的并发多线程聊天室
 * 
 * 客户端窗口程序
 *
 *
 */

public class Client extends JFrame implements WindowListener{
	/**
	 * 
	 */
	
	Rsa rsa=new Rsa();
	
	String key;
	String server_key;
	
	public ClientThread thread;
	
	public String get_key() {
		return this.key;
	}
	
	
	public String get_server_key() {
		return this.server_key;
	}
	
	public void set_key(String msg) {
		this.key=msg;
	}
	
	
	public void set_server_key(String msg) {
		this.server_key=msg;
	}
	
	public void set_thread(ClientThread msg) {
		this.thread=msg;
	}
	
	public Socket_Kerberos_Client c=new Socket_Kerberos_Client();
	public static final long serialVersionUID = 1L;
	public Container container=this.getContentPane();
	public JLabel label_username = new JLabel("用户名 ");
	//public JLabel label_password = new JLabel("密码 ");
	public JLabel label_privatename = new JLabel("私聊对象");
	public String[] friends = null;
	
	public User user = new User();

	//private JTextField textField_username = new JTextField(15);
	//private JTextField textField_password = new JTextField(6);
	public JTextField textField_inputText = new JTextField(30);
	public JTextField textField_sendtoname=new JTextField(10);
	
	
	public JTextField textField_name = new JTextField(22);

	//private JButton button_join = new JButton("登陆");
	//private JButton button_regist = new JButton("注册");
	public JButton button_sent = new JButton("群聊");
	public JButton button_privatechat = new JButton("私聊");
	public JButton button_sendfile=new JButton("传递文件");
	
	/* 消息窗口 */
	public JTextArea textarea_messagerecord = new ContentArea();
	public  JScrollPane scrollPane = new JScrollPane(textarea_messagerecord);

	public JTextArea textarea_friends = new ContentArea();
	public  JScrollPane scrollPane2 = new JScrollPane(textarea_friends);
	
	public JTextArea textarea_cipher=new ContentArea();
	public  JScrollPane scrollPane3 = new JScrollPane(textarea_cipher);


	
	
	public Socket socket;
	public PrintWriter out;
	public  BufferedReader in;

	public Client(Socket s){
		
		try{
			socket = s;
			if(!socket.isBound()){//绑定失败
				textarea_messagerecord.setText("绑定失败");
			}
			out = new PrintWriter(socket.getOutputStream(),true);
			in = new BufferedReader(new InputStreamReader(socket.getInputStream()));
			System.out.println("client socket open!!!!!!!!!!!!!!!!!!!!!");
		}catch(Exception e){
			System.out.println(e.getMessage()+" xuxixixixiixixixixix");
		}
		//setLayout(new FlowLayout(FlowLayout.CENTER, 30, 10));
		
		rsa.set_e(BigInteger.valueOf(17));
		rsa.set_n(BigInteger.valueOf(3233));
		rsa.set_d(BigInteger.valueOf(2753));
		
		
		
		setLayout(null);
		setSize(1200,1200);
		setResizable(true);

		//分别设置水平和垂直滚动条总是出现
		scrollPane.setHorizontalScrollBarPolicy(
		JScrollPane.HORIZONTAL_SCROLLBAR_ALWAYS);
		scrollPane.setVerticalScrollBarPolicy(
		JScrollPane.VERTICAL_SCROLLBAR_ALWAYS);
	
		//分别设置水平和垂直滚动条总是出现
				scrollPane2.setHorizontalScrollBarPolicy(
				JScrollPane.HORIZONTAL_SCROLLBAR_ALWAYS);
				scrollPane2.setVerticalScrollBarPolicy(
				JScrollPane.VERTICAL_SCROLLBAR_ALWAYS);

				//分别设置水平和垂直滚动条总是出现
				scrollPane3.setHorizontalScrollBarPolicy(
				JScrollPane.HORIZONTAL_SCROLLBAR_ALWAYS);
				scrollPane3.setVerticalScrollBarPolicy(
				JScrollPane.VERTICAL_SCROLLBAR_ALWAYS);
				
				scrollPane2.setBounds(100,520,400,340);//private chat
				scrollPane.setBounds(100, 50, 400, 340);//all chat
				scrollPane3.setBounds(650,50,500,900);			
			

				textField_inputText.setBounds(100,400,345,30);//all chat text
				button_sent.setBounds(450, 400,60,30);//all chat button
				
				
				button_privatechat.setBounds(450, 440, 60, 30);
				textField_name.setBounds(230, 440, 215, 30);
				label_privatename.setBounds(100, 440, 60, 30);
				textField_sendtoname.setBounds(165, 440, 60, 30);
				
				button_sendfile.setBounds(260, 480, 100, 30);
				
				
		textarea_messagerecord.setLineWrap(true);
		textarea_messagerecord.setEditable(false);
		textarea_cipher.setEditable(false);
		textarea_friends.setEditable(false);

	
		textField_inputText.setVisible(true);
		button_sent.setVisible(true);
		textField_sendtoname.setVisible(true);	
		button_privatechat.setVisible(true);
		textField_name.setVisible(true);		
		label_privatename.setVisible(true);
		button_sendfile.setVisible(true);
		
	
		
		//p4.add(textarea_friends);
	
		

		scrollPane.setVisible(true);
		scrollPane2.setVisible(true);
		scrollPane3.setVisible(true);
		
		container.add(button_privatechat);
		container.add(button_sendfile);//wenjain
		container.add(button_sent);//qunliao
		container.add(label_privatename);
		container.add(label_username);
		
		container.add(textField_inputText);
		container.add(textField_sendtoname);
		container.add(textField_name);
		
		container.add(scrollPane);
		container.add(scrollPane2);
		container.add(scrollPane3);
	    container.setVisible(true);
		
	

		
		
//		ImageIcon img = new ImageIcon("C:\\Users\\lenovo\\eclipse-workspace\\Kerberos_Chatroom\\flower.jpg");  
//        //要设置的背景图片  
//        JLabel imgLabel = new JLabel(img);  
//        //将背景图放在标签里。  
//        this.getLayeredPane().add(imgLabel, new Integer(Integer.MIN_VALUE));  
//        //将背景标签添加到jfram的LayeredPane面板里。  
//        imgLabel.setBounds(0, 0, img.getIconWidth(), img.getIconHeight());  
//        // 设置背景标签的位置  
//        Container contain = this.getContentPane();  
//        ((JPanel) contain).setOpaque(false);   
//        // 将内容面板设为透明。将LayeredPane面板中的背景显示出来。 
//		
		
		
		
		ButtonSentfileListener sentListener1 = new ButtonSentfileListener();
		button_sendfile.addActionListener(sentListener1);
		//ButtonJoinListener joinListener = new ButtonJoinListener();
		//button_join.addActionListener(joinListener);
		ButtonSentListener sentListener = new ButtonSentListener();
		button_sent.addActionListener(sentListener);
		
		//ButtonRegistListener registListener = new ButtonRegistListener();
		//button_regist.addActionListener(registListener);
		ButtonPrivateChatListener privateChatListener = new ButtonPrivateChatListener();
		button_privatechat.addActionListener(privateChatListener);
		
		//默认用户名和密码都是空
//		textField_username.setText("admin");
//		textField_password.setText("admin");
//		
//		button_join.setVisible(false);
//		button_regist.setVisible(false);
//		//label_username.setVisible(false);
//		label_password.setVisible(false);
//		textField_password.setVisible(false);
//		textField_username.setVisible(false);
		
		this.addWindowListener(this);
		//按关闭按钮，啥事也不做
		this.setDefaultCloseOperation(DO_NOTHING_ON_CLOSE);
		
	//	signin_client(username,passwd);
		

	}


	
	class ContentArea extends JTextArea{
		/**
		 * 
		 */
		private static final long serialVersionUID = 1L;

		protected ContentArea(){
			super(10, 40);
		}
	}
	

	
	class ButtonSentfileListener implements ActionListener{//发送文件
		public void actionPerformed(ActionEvent arg0) {//**********************************需要修改
			
			
		
			
			
			
			//send file
			
			 File file = null;
			 short datanum=9;
			try {
				
				 // TODO Auto-generated method stub  
		        JFileChooser jfc=new JFileChooser();  
		        jfc.setFileSelectionMode(JFileChooser.FILES_AND_DIRECTORIES );  
		        jfc.showDialog(new JLabel(), "选择");  
		        file=jfc.getSelectedFile();  
		        if(file.isDirectory()){  
		            System.out.println("文件夹:"+file.getAbsolutePath());  
		        }else if(file.isFile()){  
		            System.out.println("文件:"+file.getAbsolutePath());  
		        }  
		        System.out.println(jfc.getSelectedFile().getName());  
		          
		        
		        String file_name_send=file.getName();

		        out.println(Short.toString(datanum)+"&&&"+file_name_send);//发送文件名!!!服务器要对应先接收
		        
		    	String message_name=textField_sendtoname.getText();//发送用户名
				
				String message_text = textField_name.getText();//发送内容
				
				String message_send=message_name+"&&&"+message_text;
				
				friends = new String []{message_send};

				ClientDataGram clientdatagram = new ClientDataGram((short) 9,friends,textField_inputText.getText());//普通消息
				
				
				try {
					out.println(clientdatagram.toString());
				}catch(Exception e){
					e.printStackTrace();
				}
		        
		        
		        
		        
		        
		        
		        
		        
		        //创建读取对象
		        BufferedReader bufr=new BufferedReader(new FileReader(file.getAbsolutePath()));
		        
		        //创建写入对象
		       // PrintWriter pw=new PrintWriter(socket.getOutputStream(), true);
		        
				
		        String line=null;
		        while((line=bufr.readLine())!=null)
		        {
		        	
		        	
		        	line=client_DES_encry(line,0,line.length(),thread.get_server_key());
		        	System.out.println(line);
		            out.println(line);//发送文件内容
		        }
		        
		        String mmm="complet";
		        mmm=client_DES_encry(mmm,0,mmm.length(),thread.get_server_key());
		        out.println(mmm);
		        System.out.println(thread.get_server_key());
		        System.out.println("文件发送完毕");
		        
		       // pw.close();
		        bufr.close();
		        
		        //告诉服务端，发送完成
		       // socket.shutdownOutput();
		        
		        //接收返回数据
		       // BufferedReader bfr=new BufferedReader(new InputStreamReader(socket.getInputStream()));
		      
		        
		        //bfr.close();
		        //bufr.close();
			        
			        
			        
			        
				
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		        
		       
			
			
			
			
			
			
			
			//textarea_messagerecord.setComponentOrientation(ComponentOrientation.RIGHT_TO_LEFT);
			label_username.setText(user.getUserName() + "   is   "+ (user.getState()?"onine":"offline"));
			textarea_messagerecord.append(user.getUserName() + ":  发送文件"+file.getName()+ textField_inputText.getText()+'\n');
			textField_inputText.setText("");
		}

	}
	
	 public String client_DES_encry(String msg,int begin,int end,String k) throws UnsupportedEncodingException{
	    	msg=msg.substring(begin, end);
	    	
	    
			
			DES des=new DES();
			//加密
			String encrypttext=des.enc(msg,k);
		
	    	return encrypttext;
	    }
	
	 public String trans(String mid[]) {//将字符串数组整理成字符串
			
			String result="";
			
			int len=mid.length;
			
			for(int i=0;i<len;i++) {
				result=result+mid[i];
			}
			
			return result;
			
		}
	
	class ButtonSentListener implements ActionListener{//发送信息
		public void actionPerformed(ActionEvent arg0) {//**********************************需要修改
			
			
			
			
			
			
			
			
			
			String send="";
			String message_text = textField_inputText.getText();
			String md5=new MD5().getResult(message_text);
			
			  String M=message_text;

				ArrayList<BigInteger> encry= rsa.encry_msg_sign(M);//数字签名
				
				String mid[]=new String[encry.size()];
				
				for(int i=0;i<mid.length;i++) {
					if(i!=(mid.length-1)) {
					mid[i]=encry.get(i).toString()+"&";}
					else {
						mid[i]=encry.get(i).toString();	
					}	
				}
			String sin=trans(mid);
			
			message_text=message_text+"%%%"+md5+"%%%"+sin;
			friends  = (String[]) OnlineUsers.onlineUsers.toArray(new String [OnlineUsers.onlineUsers.size()]);
			ClientDataGram clientdatagram = new ClientDataGram((short) 0,friends,message_text);//普通消息	
			//System.out.println(clientdatagram.toString()+" wocao");
			try {
				send=client_DES_encry(clientdatagram.toString(),4,clientdatagram.toString().length(),thread.get_server_key());
			} catch (UnsupportedEncodingException e1) {
				// TODO Auto-generated catch block
				e1.printStackTrace();
			}
			
			
			try {
				send="0&&&"+send;
				out.println(send);
			}catch(Exception e){
				e.printStackTrace();
			}
			//textarea_messagerecord.setComponentOrientation(ComponentOrientation.RIGHT_TO_LEFT);
			label_username.setText(user.getUserName() + "   is   "+ (user.getState()?"onine":"offline"));
			textarea_messagerecord.append(user.getUserName() + ":  "+ textField_inputText.getText()+'\n');
			textField_inputText.setText("");
		}

	}
	

	class ButtonPrivateChatListener implements ActionListener{//私聊发送信息
		public void actionPerformed(ActionEvent arg0) {//**********************************需要修改
			
			//Rsa rsa=new Rsa();
			//
			
			String send="";
			String message_name=textField_sendtoname.getText();
			String message_text = textField_name.getText();
			String message_send=message_name+"&&&"+message_text;
			
			String md5=new MD5().getResult(message_text);
			
		    System.out.println(message_name);
		    System.out.println(message_text);
		    System.out.println(message_send);
			
			String M=md5;

			ArrayList<BigInteger> encry= rsa.encry_msg_sign(M);//数字签名
			
			String mid[]=new String[encry.size()];
			
			for(int i=0;i<mid.length;i++) {
				if(i!=(mid.length-1)) {
				mid[i]=encry.get(i).toString()+"&";}
				else {
					mid[i]=encry.get(i).toString();	
				}	
			}
		String sin=trans(mid);
		
		//message_text=message_text+"%%%"+md5+"%%%"+sin;
			
			
			
			md5=new MD5().getResult(message_text);
			//message_text=message_text+"%%%"+md5;
			
			
			
			friends = new String []{message_send};
//			System.out.print(message_text + "\n" + " 私聊好友");
//			for (String name:  friends)
//				System.out.println("    " + name );
			//friends  = (String[]) OnlineUsers.onlineUsers.toArray(new String [OnlineUsers.onlineUsers.size()]);
			ClientDataGram clientdatagram = new ClientDataGram((short) 0,friends,textField_inputText.getText());//普通消息
			
			String nnn=clientdatagram.toString().substring(0, clientdatagram.toString().length()-3);
			String rrr=nnn+"%%%"+md5+"%%%"+sin;
			System.out.println(rrr);
			
			try {
				send=client_DES_encry(rrr,4,rrr.length(),thread.get_server_key());
				System.out.println(send);
			} catch (UnsupportedEncodingException e1) {
				// TODO Auto-generated catch block
				e1.printStackTrace();
			}
			
			try {
				send="0&&&"+send;
				out.println(send);
			}catch(Exception e){
				e.printStackTrace();
			}
			//textarea_messagerecord.setComponentOrientation(ComponentOrientation.RIGHT_TO_LEFT);
			label_username.setText(user.getUserName() + "   is   "+ (user.getState()?"onine":"offline"));
			textarea_messagerecord.append(user.getUserName() + ": "+ textField_inputText.getText()+'\n');
			textField_inputText.setText("");
		}

	}

	@Override
	public void windowClosing(WindowEvent e) {
		// TODO Auto-generated method stub
		   int option = JOptionPane.showConfirmDialog(this, "确定退出系统?", "提示",JOptionPane.YES_NO_OPTION);
			       if (option == JOptionPane.YES_OPTION)
			       {
			               if (e.getWindow() == this) {
			                      this.dispose();
			                      ClientDataGram exitdatagram = new ClientDataGram((short)2);//退出
			                      out.print(exitdatagram.toString());
			                      try {
			                    	  out.close();
			                    	  in.close();
									socket.close();
								} catch (IOException e1) {
									// TODO Auto-generated catch block
									e1.printStackTrace();
								}
			                      
			                      System.exit(0);
			       } else {
			              return;
			       }
			    }
			   else if(option == JOptionPane.NO_OPTION){
			          if (e.getWindow() == this) {
			                   return;
			          }else{
			        	  return;
			          }
			     }
	}
	@Override
	public void windowClosed(WindowEvent e) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void windowDeactivated(WindowEvent e) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void windowDeiconified(WindowEvent e) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void windowIconified(WindowEvent e) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void windowOpened(WindowEvent e) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void windowActivated(WindowEvent e) {
		// TODO Auto-generated method stub
		
	}

//	public static void main(String[] args) throws IOException {
//		OnlineUsers.onlineUsers  = new ArrayList<String>();
//		
//		//Client chatRoom = new Client();
//		
//		
//		ClientThread thread = new ClientThread(chatRoom.textarea_messagerecord,chatRoom.textarea_friends, chatRoom.socket,chatRoom.in);
//		thread.start();
//		
//		chatRoom.setLocationRelativeTo(null);
//		//chatRoom.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
//		chatRoom.setVisible(true);
//	}
}