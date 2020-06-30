package TGS_Run;

import java.io.UnsupportedEncodingException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

import javax.swing.JTextArea;

import Control_Package.Authenticator;
import Control_Package.Ticket_tgs;
import DES_METHOD.DES;
import DES_METHOD.test;

public class TGS_5nums_Proc {
	
	
	private String Ktgs="AAAAAAAA";
	
	private String Kv="AAAAAAAA";
	private Ticket_tgs t_tgs;
	private Authenticator auth;
	public JTextArea tx;
	public String process(String msg,JTextArea tx1) throws UnsupportedEncodingException, ParseException
	{   
		tx=tx1;
		String result=null;
		String msgSet[]=msg.split(",");
		String IDv=msgSet[1];
		String Tickettgs=msgSet[2];
		String Authent=msgSet[3];
		//System.out.println("Tickettgs=============="+Tickettgs);
		t_tgs=getTtgs(Tickettgs,Ktgs);
		auth=getAuth(Authent,t_tgs.get_K_ctgs());
		if(docheck(t_tgs,auth)==false)
		{
		     result="error";
		}
		else
		{
		String Kcv=test.genRandomNum()+"        ";
		String IDc=t_tgs.get_IDc();
		String ADc=t_tgs.get_ADc();
		String TS4=TGS_get_time()+" ";
		String LifeTime4="300000";
		String Ticketv=Kcv+IDc+ADc+IDv+TS4+LifeTime4;
		tx.append("6 明文: "+Kcv+IDv+TS4+Ticketv+"\n");
		//System.out.println("Ticketv明文=============="+Ticketv);
		Ticketv = DES_encry(Ticketv,Kv);
		//System.out.println("Ticketv密文=============="+Ticketv);
		result=Kcv+IDv+TS4+Ticketv;
		//System.out.println("6号包明文=============="+result);
		}
		
		
		result  = DES_encry(result,t_tgs.get_K_ctgs());
		return result;
	}
	public boolean  docheck(Ticket_tgs t_tgs, Authenticator t_auth) throws ParseException
	{   
	    SimpleDateFormat simpleFormat = new SimpleDateFormat("yyyy/MM/dd HH:mm:ss");//如2016-08-10 20:40 
	    long from = simpleFormat.parse(t_tgs.get_TS()).getTime();  
	    long to = simpleFormat.parse(t_auth.get_TS()).getTime();  
	    int minutes = (int) ((to - from)/(1000 * 60));  
		//System.out.println("ADC=============="+t_tgs.get_ADc()+","+t_auth.get_ADc());
		//System.out.println("time=============="+minutes);
		if(t_tgs.get_IDc().equals(t_auth.get_IDc())&&(minutes<1))
		{
			return true;
		}
		else{
			return false;
		}
		
	}
	public Ticket_tgs getTtgs(String msg,String Kt) throws UnsupportedEncodingException
	{   
		String Tickettgs=DES_decry(msg,Kt);
		
		tx.append("Ticket tgs: "+Tickettgs+"\n");
		
		Ticket_tgs ticket_tgs=new Ticket_tgs();
		ticket_tgs.write_Kctgs(Tickettgs.substring(0, 8));
		ticket_tgs.write_IDc(Tickettgs.substring(16, 19));
		ticket_tgs.write_ADc(Tickettgs.substring(19, 34));
		ticket_tgs.write_IDtgs(Tickettgs.substring(34, 36));
		ticket_tgs.write_TS(Tickettgs.substring(36, 55));
		ticket_tgs.write_Lifetie2(Tickettgs.substring(56, 62));
		return ticket_tgs;
	}
	
	public Authenticator getAuth(String msg,String k) throws UnsupportedEncodingException
	{     
		String Authen=DES_decry(msg,k);
		
		tx.append("Authen: "+Authen+"\n");
		
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