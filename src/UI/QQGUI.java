package UI;
//模拟qq登录窗口
import java.awt.*;
import java.io.*;
import java.net.Socket;
import java.net.UnknownHostException;
import java.awt.event.*;
import javax.swing.*;

import Socket_Run.Socket_Kerberos_Client;
import tcpchatroom.Client;
import tcpchatroom.ClientThread;
import tcpchatroom.OnlineUsers;
import tcpchatroom.datagram.ClientDataGram;

//验证码的生成
import java.awt.*;
import java.util.*;


//下拉框的实现
import java.awt.*;
import java.awt.event.*;
import javax.swing.*;

public class QQGUI extends JFrame implements ActionListener{
  private JLabel userLa;
  private JLabel pwdLa;
  private JLabel verCodeLa;//验证码
  private JTextField userTxt;
  private JPasswordField pwdTxt;
  private JTextField verCodeTxt;//验证码
  private JButton sureBt;
  private JButton quitBt;
  private Mypanel mp;
  public Socket_Kerberos_Client c;
 // public Socket s_as;
  Frame frame;
  //构造方法
  public QQGUI()
  {
      Init();
  }
  public void Init()
  {
      frame = new Frame("聊天室登录");

      //创建出控件对象（因为上面只是声明出来，并没有给出实际的空间)

      //用户文本
      userLa = new JLabel();
      userLa.setText("用户名：");
      userLa.setSize(60, 50);
      userLa.setLocation(100, 80);

      //密码文本
      pwdLa = new JLabel();
      pwdLa.setText("密码：");
      pwdLa.setSize(50, 50);
      pwdLa.setLocation(100, 120);

      //用户输入框
      userTxt = new JTextField();
      userTxt.setSize(100, 20);
      //this.setSize(width, height)
      userTxt.setLocation(170, 95);

      //密码输入框
      pwdTxt = new JPasswordField();
      pwdTxt.setSize(100, 20);
      pwdTxt.setLocation(170, 135);

      //确认按钮
      sureBt = new JButton("登录");
      sureBt.setSize(60, 25);
      sureBt.setLocation(135, 260);

      //退出按钮
      quitBt = new JButton("注册");
      quitBt.setSize(60, 25);
      quitBt.setLocation(240, 260);

      //验证码文本
      verCodeLa = new JLabel();
      verCodeLa.setText("验证码：");
      verCodeLa.setSize(60, 50);
      verCodeLa.setLocation(100,165);

      //验证码文本框
      verCodeTxt = new JTextField();
      verCodeTxt.setSize(100, 20);
      verCodeTxt.setLocation(170, 180);

      //验证码
      mp = new Mypanel();
      mp.setSize(100, 30);
      mp.setLocation(280, 175);

//      //登录方式选择框
//      JComboBox xlk=new JComboBox();
//      xlk.setSize(60, 20);
//      xlk.setLocation(250, 220);
//      xlk.addItem("在线");
//      xlk.addItem("隐身");
//      xlk.addItem("离开");


      this.setLayout(null);
      this.setSize(500, 400);
      this.add(userLa);
      this.add(pwdLa);
      this.add(userTxt);
      this.add(pwdTxt);
      this.add(sureBt);
      this.add(quitBt);
      this.add(verCodeLa);
      this.add(verCodeTxt);
      this.add(mp);
    //  this.add(xlk);
      sureBt.addActionListener(this);
      quitBt.addActionListener(this);
      this.setVisible(true);
  }
  //具体事件的处理
   public void actionPerformed(ActionEvent e)
   {
       //获取产生事件的事件源强制转换
       JButton bt = (JButton)e.getSource();
       //获取按钮上显示的文本
       String str = bt.getText();
       String username=userTxt.getText();
		@SuppressWarnings("deprecation")
		String passwd=pwdTxt.getText();
       if(str.equals("登录"))
       {
    	   
    	   System.out.println(mp.sb.toString()+" 验证码");
    	   String midf=mp.sb.toString();
    	   String rrr="";
    	   int lent=midf.length();
    	   for(int i=0;i<lent;i++) {
    		   char c=midf.charAt(i);
    		   if(c==' ')
    			   continue;
    		   else
    			   rrr=rrr+c;
    	   }
    	   
    	  if(!rrr.equals(verCodeTxt.getText())) {
    		  JOptionPane pane = new JOptionPane("验证码错误");
    	      JDialog dialog  = pane.createDialog("警告");
    	      dialog.show();
    	      return ;
    	  }
    	   
    	   
   		try {
   			
   			if(c!=null) {
			c.begin_as(username);
   			}else {
   				c=new Socket_Kerberos_Client ();
   				c.begin_as(username);
   			}
		} catch (UnknownHostException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		} catch (IOException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}
   		
   		String conf=c.begin_tgs(passwd);
   		if(!conf.equals("完成")) {
   			return ;
   		}
   		Socket s_v=c.begin_v();
   		if(s_v.isClosed()) {
   			System.out.println("认证成功,但是s关闭了");
   		}
   		
    	   
   	    //进入聊天室

		try {
			OnlineUsers.onlineUsers  = new ArrayList<String>();
			
			
			
			
			
			
			Client chatRoom = new Client(s_v);
			//客户端多线程聊天
			ClientThread thread;
			
			thread = new ClientThread(chatRoom.textarea_messagerecord,chatRoom.textarea_friends, chatRoom.textarea_cipher,chatRoom.socket,chatRoom.in);
		
			thread.start();

				/*login use*?**************************/
				chatRoom.user.setUserName(username);
				chatRoom.user.setPasswd(passwd);
				
				
				ClientDataGram logindatagram = new ClientDataGram((short) 1,chatRoom.user.getUserName(),chatRoom.user.getPasswd(),true);//登录
				System.out.println(logindatagram.toString()+" ^^^^^^^^^^^^^^^^^^^^^^");
				
				
				chatRoom.out.println(logindatagram.toString());
				String key=thread.get_key();
				String server_key=thread.get_server_key();
				
				chatRoom.set_key(key);
				chatRoom.set_server_key(server_key);
				chatRoom.set_thread(thread);
				
				chatRoom.setLocationRelativeTo(null);
				//chatRoom.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
				chatRoom.setVisible(true);
				this.setVisible(false);
				//加入聊天室后禁止修改参数		
				//setSize(600,600);

				
				chatRoom.setTitle(chatRoom.user.getUserName());
			
				chatRoom.scrollPane.setVisible(true);
				chatRoom.scrollPane2.setVisible(true);
				chatRoom.scrollPane3.setVisible(true);
				chatRoom.textField_name.setVisible(true);
				chatRoom.textField_inputText.setVisible(true);
				chatRoom.textField_sendtoname.setVisible(true);
				chatRoom.button_sent.setVisible(true);
				chatRoom.button_privatechat.setVisible(true);
				chatRoom.label_privatename.setVisible(true);
				
				chatRoom.label_username.setText(chatRoom.user.getUserName() + "   is   "+ (chatRoom.user.getState()?"onine":"offline"));
				chatRoom.textarea_friends.setText(null);
				chatRoom.textarea_friends.setText("		好友列表\n");
				chatRoom.textarea_cipher.setText("		历史消息\n");
			
			
			
			
			
			
			
		} catch (IOException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}
		
    	   
    	   

           
           
           
           
           
       }
       if(str.equals("注册")) {
    	   
    	   UI_Register login=new UI_Register();
           login.frame.setVisible(true);
    	  
    	   
    	   
       }
//       else
//       {
//           //调用系统类中的一个正常退出
//           System.exit(0);
//       }
   }
   private boolean CheckIsNull()
   {
       boolean flag = false;
       if(userTxt.getText().trim().equals(" "))
       {
           flag = true;
       }
       else
       {
           if(pwdTxt.getText().trim().equals(" "))
           {
               flag = true;
           }
       }
       return flag;
   }
  
}



//测试
