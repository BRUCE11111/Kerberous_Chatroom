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

		
		contentPane.add(js);
		
		textArea_1 = new JTextArea();
		//textArea_1.setBounds(273, 13, 208, 227);
		
		JScrollPane js1=new JScrollPane(textArea_1);
		js1.setBounds(273, 13, 208, 227);
//		//�ֱ�����ˮƽ�ʹ�ֱ�������Զ�����
//		js.setHorizontalScrollBarPolicy(
//		JScrollPane.HORIZONTAL_SCROLLBAR_AS_NEEDED);
//		js.setVerticalScrollBarPolicy(
//		JScrollPane.VERTICAL_SCROLLBAR_AS_NEEDED);
		 
		//�ֱ�����ˮƽ�ʹ�ֱ���������ǳ���
		js1.setHorizontalScrollBarPolicy(
		JScrollPane.HORIZONTAL_SCROLLBAR_ALWAYS);
		js1.setVerticalScrollBarPolicy(
		JScrollPane.VERTICAL_SCROLLBAR_ALWAYS);
		//js.setViewportView(textArea);
		textArea_1.setEditable(false);
		textArea_1.setVisible(true);
		
		js1.setVisible(true);

		
		contentPane.add(js1);
		

		
		JButton btnNewButton = new JButton("��ʼ");
		btnNewButton.addActionListener(this);
		btnNewButton.setBounds(55, 313, 113, 27);
		contentPane.add(btnNewButton);
		
		JButton btnNewButton_1 = new JButton("�ر�");
		btnNewButton_1.addActionListener(this);
		btnNewButton_1.setBounds(299, 313, 113, 27);
		contentPane.add(btnNewButton_1);
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
	    	   textArea_1.append("                                  �û��������б�n");
//	    	   Socket_Kerberos_AS AS=new Socket_Kerberos_AS();
//	    	   AS.AS_begin(textArea);
	    	   
	    	  new V_thread(textArea,textArea_1).start();
	    	   
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
