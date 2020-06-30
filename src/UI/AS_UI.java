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
//		//�ֱ�����ˮƽ�ʹ�ֱ�������Զ�����
//		js.setHorizontalScrollBarPolicy(
//		JScrollPane.HORIZONTAL_SCROLLBAR_AS_NEEDED);
//		js.setVerticalScrollBarPolicy(
//		JScrollPane.VERTICAL_SCROLLBAR_AS_NEEDED);
		 
		//�ֱ�����ˮƽ�ʹ�ֱ���������ǳ���
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
		
		
		
		
		JButton btnNewButton = new JButton("��ʼ");
		btnNewButton.addActionListener(this);
		btnNewButton.setBounds(395, 321, 113, 27);
		contentPane.add(btnNewButton);
		
		JButton btnNewButton_1 = new JButton("�ر�");
		btnNewButton_1.setBounds(395, 361, 113, 27);
		contentPane.add(btnNewButton_1);
		btnNewButton_1.addActionListener(this);
		
		contentPane.setVisible(true);
		
		
	}
	
	 //�����¼��Ĵ���
	   public void actionPerformed(ActionEvent e)
	   {
	       //��ȡ�����¼����¼�Դǿ��ת��
	       JButton bt = (JButton)e.getSource();
	       //��ȡ��ť����ʾ���ı�
	       String str = bt.getText();
	       if(str.equals("��ʼ"))
	       {
	    	   
	    	   textArea.append("                                     ��������������\n");
//	    	   Socket_Kerberos_AS AS=new Socket_Kerberos_AS();
//	    	   AS.AS_begin(textArea);
	    	   
	    	  new AS_thread(textArea).start();
	    	   
	       }
	       
	       if(str.equals("�ر�")) {
	    	   
	    	   int option = JOptionPane.showConfirmDialog(this, "ȷ���˳�ϵͳ?", "��ʾ",JOptionPane.YES_NO_OPTION);
	    	   if (option == JOptionPane.YES_OPTION)
		       {
		              
		                     
		                      
		                      System.exit(0);
		       } 
		   if(option == JOptionPane.NO_OPTION){
		         
		        	  return;
		          
		     }
	    	   
	       }
//	       
//	       if(str.equals("ȡ��")) {
//	    	   
//	    	   this.frame.setVisible(false);
//	       }
	       
	       
       
	       
	       }
	
	
	
}
