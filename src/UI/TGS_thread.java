package UI;

import javax.swing.JTextArea;


import Socket_Run.Socket_Kerberos_TGS;

public class TGS_thread extends Thread{

public JTextArea textArea;
	
	TGS_thread(JTextArea textArea){
		this.textArea=textArea;
	}
	
	public void run() {
		
		
		
		Socket_Kerberos_TGS TGS=new Socket_Kerberos_TGS();
  	   TGS.begin(textArea);
		
		
		
	}
}




