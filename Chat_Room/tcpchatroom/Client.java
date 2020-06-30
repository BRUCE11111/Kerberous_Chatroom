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
 * ����TCP�Ĳ������߳�������
 * 
 * �ͻ��˴��ڳ���
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
	public JLabel label_username = new JLabel("�û��� ");
	//public JLabel label_password = new JLabel("���� ");
	public JLabel label_privatename = new JLabel("˽�Ķ���");
	public String[] friends = null;
	
	public User user = new User();

	//private JTextField textField_username = new JTextField(15);
	//private JTextField textField_password = new JTextField(6);
	public JTextField textField_inputText = new JTextField(30);
	public JTextField textField_sendtoname=new JTextField(10);
	
	
	public JTextField textField_name = new JTextField(22);

	//private JButton button_join = new JButton("��½");
	//private JButton button_regist = new JButton("ע��");
	public JButton button_sent = new JButton("Ⱥ��");
	public JButton button_privatechat = new JButton("˽��");
	public JButton button_sendfile=new JButton("�����ļ�");
	
	/* ��Ϣ���� */
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
			if(!socket.isBound()){//��ʧ��
				textarea_messagerecord.setText("��ʧ��");
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

		//�ֱ�����ˮƽ�ʹ�ֱ���������ǳ���
		scrollPane.setHorizontalScrollBarPolicy(
		JScrollPane.HORIZONTAL_SCROLLBAR_ALWAYS);
		scrollPane.setVerticalScrollBarPolicy(
		JScrollPane.VERTICAL_SCROLLBAR_ALWAYS);
	
		//�ֱ�����ˮƽ�ʹ�ֱ���������ǳ���
				scrollPane2.setHorizontalScrollBarPolicy(
				JScrollPane.HORIZONTAL_SCROLLBAR_ALWAYS);
				scrollPane2.setVerticalScrollBarPolicy(
				JScrollPane.VERTICAL_SCROLLBAR_ALWAYS);

				//�ֱ�����ˮƽ�ʹ�ֱ���������ǳ���
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
//        //Ҫ���õı���ͼƬ  
//        JLabel imgLabel = new JLabel(img);  
//        //������ͼ���ڱ�ǩ�  
//        this.getLayeredPane().add(imgLabel, new Integer(Integer.MIN_VALUE));  
//        //��������ǩ��ӵ�jfram��LayeredPane����  
//        imgLabel.setBounds(0, 0, img.getIconWidth(), img.getIconHeight());  
//        // ���ñ�����ǩ��λ��  
//        Container contain = this.getContentPane();  
//        ((JPanel) contain).setOpaque(false);   
//        // �����������Ϊ͸������LayeredPane����еı�����ʾ������ 
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
		
		//Ĭ���û��������붼�ǿ�
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
		//���رհ�ť��ɶ��Ҳ����
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
	

	
	class ButtonSentfileListener implements ActionListener{//�����ļ�
		public void actionPerformed(ActionEvent arg0) {//**********************************��Ҫ�޸�
			
			
		
			
			
			
			//send file
			
			 File file = null;
			 short datanum=9;
			try {
				
				 // TODO Auto-generated method stub  
		        JFileChooser jfc=new JFileChooser();  
		        jfc.setFileSelectionMode(JFileChooser.FILES_AND_DIRECTORIES );  
		        jfc.showDialog(new JLabel(), "ѡ��");  
		        file=jfc.getSelectedFile();  
		        if(file.isDirectory()){  
		            System.out.println("�ļ���:"+file.getAbsolutePath());  
		        }else if(file.isFile()){  
		            System.out.println("�ļ�:"+file.getAbsolutePath());  
		        }  
		        System.out.println(jfc.getSelectedFile().getName());  
		          
		        
		        String file_name_send=file.getName();

		        out.println(Short.toString(datanum)+"&&&"+file_name_send);//�����ļ���!!!������Ҫ��Ӧ�Ƚ���
		        
		    	String message_name=textField_sendtoname.getText();//�����û���
				
				String message_text = textField_name.getText();//��������
				
				String message_send=message_name+"&&&"+message_text;
				
				friends = new String []{message_send};

				ClientDataGram clientdatagram = new ClientDataGram((short) 9,friends,textField_inputText.getText());//��ͨ��Ϣ
				
				
				try {
					out.println(clientdatagram.toString());
				}catch(Exception e){
					e.printStackTrace();
				}
		        
		        
		        
		        
		        
		        
		        
		        
		        //������ȡ����
		        BufferedReader bufr=new BufferedReader(new FileReader(file.getAbsolutePath()));
		        
		        //����д�����
		       // PrintWriter pw=new PrintWriter(socket.getOutputStream(), true);
		        
				
		        String line=null;
		        while((line=bufr.readLine())!=null)
		        {
		        	
		        	
		        	line=client_DES_encry(line,0,line.length(),thread.get_server_key());
		        	System.out.println(line);
		            out.println(line);//�����ļ�����
		        }
		        
		        String mmm="complet";
		        mmm=client_DES_encry(mmm,0,mmm.length(),thread.get_server_key());
		        out.println(mmm);
		        System.out.println(thread.get_server_key());
		        System.out.println("�ļ��������");
		        
		       // pw.close();
		        bufr.close();
		        
		        //���߷���ˣ��������
		       // socket.shutdownOutput();
		        
		        //���շ�������
		       // BufferedReader bfr=new BufferedReader(new InputStreamReader(socket.getInputStream()));
		      
		        
		        //bfr.close();
		        //bufr.close();
			        
			        
			        
			        
				
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		        
		       
			
			
			
			
			
			
			
			//textarea_messagerecord.setComponentOrientation(ComponentOrientation.RIGHT_TO_LEFT);
			label_username.setText(user.getUserName() + "   is   "+ (user.getState()?"onine":"offline"));
			textarea_messagerecord.append(user.getUserName() + ":  �����ļ�"+file.getName()+ textField_inputText.getText()+'\n');
			textField_inputText.setText("");
		}

	}
	
	 public String client_DES_encry(String msg,int begin,int end,String k) throws UnsupportedEncodingException{
	    	msg=msg.substring(begin, end);
	    	
	    
			
			DES des=new DES();
			//����
			String encrypttext=des.enc(msg,k);
		
	    	return encrypttext;
	    }
	
	 public String trans(String mid[]) {//���ַ�������������ַ���
			
			String result="";
			
			int len=mid.length;
			
			for(int i=0;i<len;i++) {
				result=result+mid[i];
			}
			
			return result;
			
		}
	
	class ButtonSentListener implements ActionListener{//������Ϣ
		public void actionPerformed(ActionEvent arg0) {//**********************************��Ҫ�޸�
			
			
			
			
			
			
			
			
			
			String send="";
			String message_text = textField_inputText.getText();
			String md5=new MD5().getResult(message_text);
			
			  String M=message_text;

				ArrayList<BigInteger> encry= rsa.encry_msg_sign(M);//����ǩ��
				
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
			ClientDataGram clientdatagram = new ClientDataGram((short) 0,friends,message_text);//��ͨ��Ϣ	
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
	

	class ButtonPrivateChatListener implements ActionListener{//˽�ķ�����Ϣ
		public void actionPerformed(ActionEvent arg0) {//**********************************��Ҫ�޸�
			
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

			ArrayList<BigInteger> encry= rsa.encry_msg_sign(M);//����ǩ��
			
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
//			System.out.print(message_text + "\n" + " ˽�ĺ���");
//			for (String name:  friends)
//				System.out.println("    " + name );
			//friends  = (String[]) OnlineUsers.onlineUsers.toArray(new String [OnlineUsers.onlineUsers.size()]);
			ClientDataGram clientdatagram = new ClientDataGram((short) 0,friends,textField_inputText.getText());//��ͨ��Ϣ
			
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
		   int option = JOptionPane.showConfirmDialog(this, "ȷ���˳�ϵͳ?", "��ʾ",JOptionPane.YES_NO_OPTION);
			       if (option == JOptionPane.YES_OPTION)
			       {
			               if (e.getWindow() == this) {
			                      this.dispose();
			                      ClientDataGram exitdatagram = new ClientDataGram((short)2);//�˳�
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