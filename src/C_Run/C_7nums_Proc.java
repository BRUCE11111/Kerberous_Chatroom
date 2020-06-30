package C_Run;

import java.io.UnsupportedEncodingException;
import java.text.SimpleDateFormat;
import java.util.Date;

import DES_METHOD.DES;
import DES_METHOD.test;

public class C_7nums_Proc {
	
	private String Id_pre="000111";
	
	public String process(String msg,String address) throws UnsupportedEncodingException {
		
		String result="";
		String Ticketv=msg.substring(44, msg.length());
		System.out.println("ticketv=================="+Ticketv);
		String IDc="000";
		String ADc=address;
        int len=ADc.length();
		if(len<15) {
			for(int i=0;i<15-len;i++) {
				ADc=ADc+" ";
			}
		}
		String TS5=C_get_time()+" ";
		String Kcv=msg.substring(6,14);
		String Authenticator=IDc+ADc+TS5;
		Authenticator=C_DES_encry(Authenticator,Kcv);
		result="000111"+","+Ticketv+","+Authenticator;
		return result;
	}
	public String C_get_time() {
    	Date now = new Date(); 
    	SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy/MM/dd HH:mm:ss");//可以方便地修改日期格式
    	String hehe = dateFormat.format( now ); 
    	int len=hehe.length();
    	return hehe;
    }
	 public String C_DES_encry(String msg,String k) throws UnsupportedEncodingException{
	    	test c=new test();
			
			DES des=new DES();
			//加密
			String encrypttext=des.enc(msg,k);
		
	    	return encrypttext;
	    }


}
