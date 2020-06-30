package UI;

import javax.swing.JTextArea;

import Socket_Run.Socket_Kerberos_AS;

public class AS_thread extends Thread{
	 
	public JTextArea textArea;
	
	AS_thread(JTextArea textArea){
		this.textArea=textArea;
	}
	
	public void run() {
		
		
		
		 Socket_Kerberos_AS AS=new Socket_Kerberos_AS();
  	   AS.AS_begin(textArea);
		
		
		
	}

}
