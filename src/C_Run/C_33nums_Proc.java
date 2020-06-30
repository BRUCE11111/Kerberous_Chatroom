package C_Run;

import java.math.BigInteger;

import javax.swing.JDialog;
import javax.swing.JOptionPane;

import RSA.Rsa;

public class C_33nums_Proc {
	
	public BigInteger AS_e;
	public BigInteger AS_n;
	public String rec_sign;
	
	public String process(String msg) {
		String result="";
		
		System.out.println("身份信息 "+msg);
		
		String rec[]=msg.split("&");
		String sign_info;
		int len=rec.length;
		String M_as="";
		String ttt="t";
		String signn="";
		for(int i=1;i<len;i++) {
			
			if(i==1) {
				String pube=rec[1];
				AS_e=new BigInteger(pube);
				continue;
			}
			
			if(i==2) {
				String pubn=rec[2];
				AS_n=new BigInteger(pubn);
				continue;
			}
			if(i==3) {
				String name=rec[3];
				continue;
			}

				
			
			   signn=rec[4];
			   if(i==5) {
				M_as=rec[i];
			   }
			
			
		}
		
		
		System.out.println();
		System.out.println(M_as+" fdjkdjfkdjfkadjfkdjkfjd");
		Rsa rsa=new Rsa();
		boolean flag=confirm_sign_new(M_as,ttt);
		
		if(flag==false) {
			 JOptionPane pane = new JOptionPane("身份警告");
		      JDialog dialog  = pane.createDialog("警告");
		      dialog.show();
			return "身份";
		}
		
		
		
		return result;
		
		
	}
	
	public boolean confirm_sign_new(String M_as,String ttt) {
		if(M_as.equals(ttt)) {
			return true;
		}else {
			return false;
		}
		
	}
	
	

}
