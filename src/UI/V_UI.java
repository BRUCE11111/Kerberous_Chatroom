package UI;

import java.awt.BorderLayout;
import java.awt.EventQueue;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JFrame;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.border.EmptyBorder;
import javax.swing.JTextArea;
import javax.swing.JButton;

public class V_UI extends JFrame implements ActionListener{

	private JPanel contentPane;
	
	JTextArea textArea;
	JTextArea textArea_1;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					V_UI frame = new V_UI();
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
	public V_UI() {
		
		this.setTitle("v server");
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 519, 489);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		textArea= new JTextArea();

		JScrollPane js=new JScrollPane(textArea);
		js.setBounds(14, 13, 208, 227);
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

		
		contentPane.add(js);
		
		textArea_1 = new JTextArea();
		//textArea_1.setBounds(273, 13, 208, 227);
		
		JScrollPane js1=new JScrollPane(textArea_1);
		js1.setBounds(273, 13, 208, 227);
//		//分别设置水平和垂直滚动条自动出现
//		js.setHorizontalScrollBarPolicy(
//		JScrollPane.HORIZONTAL_SCROLLBAR_AS_NEEDED);
//		js.setVerticalScrollBarPolicy(
//		JScrollPane.VERTICAL_SCROLLBAR_AS_NEEDED);
		 
		//分别设置水平和垂直滚动条总是出现
		js1.setHorizontalScrollBarPolicy(
		JScrollPane.HORIZONTAL_SCROLLBAR_ALWAYS);
		js1.setVerticalScrollBarPolicy(
		JScrollPane.VERTICAL_SCROLLBAR_ALWAYS);
		//js.setViewportView(textArea);
		textArea_1.setEditable(false);
		textArea_1.setVisible(true);
		
		js1.setVisible(true);

		
		contentPane.add(js1);
		

		
		JButton btnNewButton = new JButton("开始");
		btnNewButton.addActionListener(this);
		btnNewButton.setBounds(55, 313, 113, 27);
		contentPane.add(btnNewButton);
		
		JButton btnNewButton_1 = new JButton("关闭");
		btnNewButton_1.addActionListener(this);
		btnNewButton_1.setBounds(299, 313, 113, 27);
		contentPane.add(btnNewButton_1);
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
	    	   textArea_1.append("                                  用户上下线列表、n");
//	    	   Socket_Kerberos_AS AS=new Socket_Kerberos_AS();
//	    	   AS.AS_begin(textArea);
	    	   
	    	  new V_thread(textArea,textArea_1).start();
	    	   
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
