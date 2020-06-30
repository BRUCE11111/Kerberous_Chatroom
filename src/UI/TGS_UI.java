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

public class TGS_UI extends JFrame implements ActionListener {

	private JPanel contentPane;
	public JTextArea text;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					TGS_UI frame = new TGS_UI();
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
	public TGS_UI() {
		this.setTitle("TGS server");
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 978, 565);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		text = new JTextArea();
		
		
		
		
		JScrollPane js=new JScrollPane(text);
		js.setBounds(75, 31, 803, 345);
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
		text.setEditable(false);
		text.setVisible(true);
		
		js.setVisible(true);

		
		contentPane.add(js);
		
		JButton btnNewButton = new JButton("开始");
		btnNewButton.setBounds(397, 411, 113, 27);
		btnNewButton.addActionListener(this);
		contentPane.add(btnNewButton);
		
		JButton btnNewButton_1 = new JButton("关闭");
		btnNewButton_1.addActionListener(this);
		btnNewButton_1.setBounds(397, 455, 113, 27);
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
	    	   
	    	   text.append("                                     服务器接收内容\n");
//	    	   Socket_Kerberos_AS AS=new Socket_Kerberos_AS();
//	    	   AS.AS_begin(textArea);
	    	   
	    	  new TGS_thread(text).start();
	    	   
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


	       
	       }
	
	
	
	
	
	
	
}
