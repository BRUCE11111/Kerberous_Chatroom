package C_Run;

import java.io.UnsupportedEncodingException;
import java.net.InetAddress;
import java.net.UnknownHostException;
import java.text.SimpleDateFormat;
import java.util.Date;

import javax.swing.JDialog;
import javax.swing.JOptionPane;

import Control_Package.Fourth_ctol;
import DES_METHOD.*;

public class C_5nums_Proc {
	
	private String kc;
	private Fourth_ctol ctol_4;
	public String process(String msg,String passwd) throws UnsupportedEncodingException, UnknownHostException {
		kc=passwd;
		String decry=C_DES_decry(msg,kc);
		System.out.println(decry.length()+" IIOIOIOIOIROIOIOIO");
		
		
		if(decry.length()!=220) {
			  JOptionPane pane = new JOptionPane("密码错误");
		      JDialog dialog  = pane.createDialog("警告");
		      dialog.show();
		      return "密码错误";
		}
		
		//System.out.println("4号包解密==========="+decry);
		decry="000100"+decry;
		ctol_4=new Fourth_ctol();
		ctol_4.write_K_ctgs(decry.substring(6, 14));
		ctol_4.write_IDtgs(decry.substring(22, 24));
		ctol_4.write_TS2(decry.substring(24,44));
		ctol_4.write_Lifetime1(decry.substring(44,50));
		//System.out.println("LifeTime=================="+decry.substring(44,50));
		ctol_4.write_tickettgs(decry.substring(50,decry.length()));
		
		String result="000101";
		String IDv="00";
		String tgs=ctol_4.get_tickettgs();
		//System.out.println("Tickettgs=================="+decry.substring(50,decry.length()));
		System.out.println("Tickettgs=================="+tgs);
		String IDc="000";
		String ADc=InetAddress.getLocalHost().getHostAddress();
		int len=ADc.length();
		if(len<15) {
			for(int i=0;i<15-len;i++) {
				ADc=ADc+" ";
			}
		}
		String TS3=C_get_time()+" ";
		
		String authenc=IDc+ADc+TS3;
		
		String auth=C_DES_encry(authenc,0,authenc.length(),ctol_4.get_K_ctgs());
		
		result=result+","+IDv+","+tgs+","+auth;
		
		return result;
		
	}
	public String C_get_Kctgs()
	{
		return ctol_4.get_K_ctgs();
	}
	 public String C_get_time() {
	    	Date now = new Date(); 
	    	SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy/MM/dd HH:mm:ss");//可以方便地修改日期格式
	    	String hehe = dateFormat.format( now ); 
	    	int len=hehe.length();

	    	return hehe;
	    }

	 public String C_DES_encry(String msg,int begin,int end,String k) throws UnsupportedEncodingException{
	    	msg=msg.substring(begin, end);
	    	
	    	test c=new test();
			
			DES des=new DES();
			//加密
			String encrypttext=des.enc(msg,k);
		
	    	return encrypttext;
	    }

	 
	 public String C_DES_decry(String msg,String k) throws UnsupportedEncodingException{
	    	
		    msg=msg.substring(6,msg.length());
	    	
			DES des=new DES();
            
			String decrypttext=des.dec(msg,k);
			
			return decrypttext;
	    	
	    }
	
	

}
