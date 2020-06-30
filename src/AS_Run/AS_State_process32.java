package AS_Run;

import java.math.BigInteger;

import javax.swing.JDialog;
import javax.swing.JOptionPane;

import RSA.Rsa;

public class AS_State_process32 implements AS_State{
	//rec info form client's public key  rsa use;
		public BigInteger Client_e;
		public BigInteger Client_n;
		public String rec_sign;
		
		
		
		
		
		
		public 	String AS_process_32(String msg) {
			
			System.out.println(msg+" wokao确认收到的客户端的请求");
			
			String rec[]=msg.split("&");
			String sign_info[];
			int len=rec.length;
			String Mc="";
			String signn="";
			String ttt="t";
			
			sign_info=new String[len-4];
			for(int i=1;i<len;i++) {
				if(i==1) {
					String pube=rec[1];
					Client_e=new BigInteger(pube);
					
					continue;
				}
				if(i==2) {
					String pubn=rec[2];
					Client_n=new BigInteger(pubn);
					continue;
				}
				if(i==3) {
					String name=rec[3];
					rec_sign=name;
					continue;
				}
				
				signn=rec[4];
				
					Mc=rec[len-1];
				
				
				
			}
			
			Rsa rsa=new Rsa();
			boolean flag=confirm_sign_new(Mc, ttt);
			if(flag==false) {
				 JOptionPane pane = new JOptionPane("身份警告");
			      JDialog dialog  = pane.createDialog("警告");
			      dialog.show();
				return "身份";
				
			}
			
			return "ok";
			
		}

		public boolean confirm_sign_new(String M_as,String ttt) {
			if(M_as.equals(ttt)) {
				return true;
			}else {
				return false;
			}
			
		}




		@Override
		public String doaction(String msg, String address) throws Exception {
			// TODO Auto-generated method stub
			
            String result="";
			
			result=AS_process_32(msg);
			
			return result;
			
	
		}
		
		
		
}
