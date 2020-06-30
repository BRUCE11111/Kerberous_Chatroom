package UI;

import java.awt.BorderLayout;
import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.border.EmptyBorder;

import Socket_Run.Socket_Kerberos_AS;
import tcpchatroom.datagram.ClientDataGram;

import javax.swing.JTextArea;
import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.io.IOException;
import java.awt.event.ActionEvent;

public class AS_UI extends JFrame implements ActionListener {

	private JPanel contentPane;
	
    public JTextArea textArea;
	

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					AS_UI frame = new AS_UI();
					frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * Create the frame.
	 */
	public AS_UI() {
	    this.setTitle("AS SERVER");
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 954, 459);
		setResizable(true);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		textArea = new JTextArea();
		JScrollPane js=new JScrollPane(textArea);
//		//分别设置水平和垂直滚动条自动出现
//		js.setHorizontalScrollBarPolicy(
//		JScrollPane.HORIZONTAL_SCROLLBAR_AS_NEEDED);
//		js.setVerticalScrollBarPolicy(
//		JScrollPane.VERTICAL_SCROLLBAR_AS_NEEDED);
		 
		//分别设置水平和垂直滚动条总是出现
		js.setHorizontalScrollBarPolicy(
		JScrollPane.HORIZONTAL_SCROLLBAR_ALWAYS);
		js.setVerticalScrollBarPolicy(
		JScrollPane.VERTICAL_SCROLLBAR_ALWAYS);
		//js.setViewportView(textArea);
		textArea.setEditable(false);
		textArea.setVisible(true);
		
		js.setVisible(true);
		
		
		//textArea.setBounds(52, 39, 827, 249);
		js.setBounds(52, 39, 827, 249);
		contentPane.add(js);
		
		
		
		
		JButton btnNewButton = new JButton("开始");
		btnNewButton.addActionListener(this);
		btnNewButton.setBounds(395, 321, 113, 27);
		contentPane.add(btnNewButton);
		
		JButton btnNewButton_1 = new JButton("关闭");
		btnNewButton_1.setBounds(395, 361, 113, 27);
		contentPane.add(btnNewButton_1);
		btnNewButton_1.addActionListener(this);
		
		contentPane.setVisible(true);
		
		
	}
	
	 //具体事件的处理
	   public void actionPerformed(ActionEvent e)
	   {
	       //获取产生事件的事件源强制转换
	       JButton bt = (JButton)e.getSource();
	       //获取按钮上显示的文本
	       String str = bt.getText();
	       if(str.equals("开始"))
	       {
	    	   
	    	   textArea.append("                                     服务器接收内容\n");
//	    	   Socket_Kerberos_AS AS=new Socket_Kerberos_AS();
//	    	   AS.AS_begin(textArea);
	    	   
	    	  new AS_thread(textArea).start();
	    	   
	       }
	       
	       if(str.equals("关闭")) {
	    	   
	    	   int option = JOptionPane.showConfirmDialog(this, "确定退出系统?", "提示",JOptionPane.YES_NO_OPTION);
	    	   if (option == JOptionPane.YES_OPTION)
		       {
		              
		                     
		                      
		                      System.exit(0);
		       } 
		   if(option == JOptionPane.NO_OPTION){
		         
		        	  return;
		          
		     }
	    	   
	       }
//	       
//	       if(str.equals("取消")) {
//	    	   
//	    	   this.frame.setVisible(false);
//	       }
	       
	       
       
	       
	       }
	
	
	
}
