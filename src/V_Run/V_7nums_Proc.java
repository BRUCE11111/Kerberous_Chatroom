package V_Run;

import java.io.UnsupportedEncodingException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

import Control_Package.Authenticator;
import Control_Package.Ticket_tgs;
import Control_Package.Ticketv;
import DES_METHOD.DES;
import DES_METHOD.test;

public class V_7nums_Proc {
	
	private String Kv="AAAAAAAA";
	private Ticketv t_v;
	private Authenticator auth;
	public String process(String msg) throws UnsupportedEncodingException, ParseException
	{   String result=null;
		String msgSet[]=msg.split(",");
		String Ticket_v=msgSet[1];
		String Authent=msgSet[2];
		//System.out.println("Tickettgs=============="+Ticket_v);
		t_v=getT_v(Ticket_v,Kv);
		String Kcv=t_v.get_Kcv();
		auth=getAuth(Authent,Kcv);
		if(docheck(t_v,auth)==false)
		{
		     result="error";
		}
		else
		{
		String TS6 = auth.get_TS();
		result = DES_encry(TS6,Kcv);
		result="001000"+result;
		//System.out.println("8号包=============="+result);
		}
		return result;
	}
	public boolean  docheck(Ticketv t_v, Authenticator t_auth) throws ParseException
	{   
	    SimpleDateFormat simpleFormat = new SimpleDateFormat("yyyy/MM/dd HH:mm:ss");//如2016-08-10 20:40 
	    long from = simpleFormat.parse(t_v.get_TS()).getTime();  
	    long to = simpleFormat.parse(t_auth.get_TS()).getTime();  
	    int minutes = (int) ((to - from)/(1000 * 60));  
		//System.out.println("ADC=============="+t_v.get_ADc()+","+t_auth.get_ADc());
		//System.out.println("time=============="+minutes);
		if(t_v.get_IDc().equals(t_auth.get_IDc())&&(minutes<1))
		{
			return true;
		}
		else{
			return false;
		}
		
	}
	public Ticketv getT_v(String msg,String Kt) throws UnsupportedEncodingException
	{   
		String Ticket_v=DES_decry(msg,Kt);
		Ticketv ticketv=new Ticketv();
		ticketv.write_Kcv(Ticket_v.substring(0, 8));
		ticketv.write_IDc(Ticket_v.substring(16, 19));
		ticketv.write_ADc(Ticket_v.substring(19, 34));
		ticketv.write_IDv(Ticket_v.substring(34, 36));
		ticketv.write_TS(Ticket_v.substring(36, 56));
		ticketv.write_Lifetime(Ticket_v.substring(56, 62));
		return ticketv;
	}
	
	public Authenticator getAuth(String msg,String k) throws UnsupportedEncodingException
	{     
		String Authen=DES_decry(msg,k);
		Authenticator auth1=new Authenticator();
		auth1.write_IDc(Authen.substring(0,3));
		auth1.write_ADc(Authen.substring(3,18));
		auth1.write_TS(Authen.substring(18,37));
		return auth1;
	}
	
	public String TGS_get_time() {
    	Date now = new Date(); 
    	SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy/MM/dd HH:mm:ss");//可以方便地修改日期格式
    	String hehe = dateFormat.format( now ); 
    	int len=hehe.length();
    	return hehe;
    }
	
	 public String DES_decry(String msg,String kcv) throws UnsupportedEncodingException{
	    	
			DES des=new DES();
			String decrypttext=des.dec(msg,kcv);
			return decrypttext;
	    	
	    }
	 
	 public String DES_encry(String msg,String k) throws UnsupportedEncodingException{
			
			DES des=new DES();
			//加密
			String encrypttext=des.enc(msg,k);
			return encrypttext;
		}
	 
}