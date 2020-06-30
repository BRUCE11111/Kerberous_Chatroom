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
		text.setEditable(false);
		text.setVisible(true);
		
		js.setVisible(true);

		
		contentPane.add(js);
		
		JButton btnNewButton = new JButton("��ʼ");
		btnNewButton.setBounds(397, 411, 113, 27);
		btnNewButton.addActionListener(this);
		contentPane.add(btnNewButton);
		
		JButton btnNewButton_1 = new JButton("�ر�");
		btnNewButton_1.addActionListener(this);
		btnNewButton_1.setBounds(397, 455, 113, 27);
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
	    	   
	    	   text.append("                                     ��������������\n");
//	    	   Socket_Kerberos_AS AS=new Socket_Kerberos_AS();
//	    	   AS.AS_begin(textArea);
	    	   
	    	  new TGS_thread(text).start();
	    	   
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


	       
	       }
	
	
	
	
	
	
	
}
