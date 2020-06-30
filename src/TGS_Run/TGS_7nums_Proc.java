package TGS_Run;

import java.io.UnsupportedEncodingException;
import java.text.SimpleDateFormat;
import java.util.Date;

import DES_METHOD.DES;
import DES_METHOD.test;

public class TGS_7nums_Proc {
	
	private String Kv="AAAAAAAA";
	public String TGS_get_time() {
    	Date now = new Date(); 
    	SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy/MM/dd HH:mm:ss");//可以方便地修改日期格式


    	String hehe = dateFormat.format( now ); 
    	System.out.println(hehe); 
    	int len=hehe.length();
    	System.out.println(len); 
    	return hehe;
    }
	
	public String pro_ticketv (String msg) throws UnsupportedEncodingException {
		
		String idv=msg.substring(6, 8);
		String kctgs=msg.substring(9,24);
		String idc=msg.substring(25,27);
		String adc=msg.substring(28,42);
		String idtgs=msg.substring(43,44);
		String ts2=msg.substring(45,64);
		String lifetime=msg.substring(65,70);
		String authc=DES_decry(msg,71,108,kctgs);
		String au_idc=authc.substring(0, 2);
		String au_adc=authc.substring(3,17);
		String au_ts3=authc.substring(18,37);
		
		test c=new test();
		String Kcv=c.genRandomNum();
		
		String result=Kcv+idv+TGS_get_time()+" ";
		String ticketv=Kcv+idc+adc+idv+TGS_get_time()+" "+lifetime;
		String Ticketv=TGS_DES_encry(ticketv,0,ticketv.length(),this.Kv);
		result=result+Ticketv;
		return TGS_DES_encry(result,0,result.length(),Kcv);
		
		
	}
	 public String DES_decry(String msg,int begin,int end,String kcv) throws UnsupportedEncodingException{
	    	msg=msg.substring(begin, end);
	    	
	    	
			
		
			DES des=new DES();
		
			System.out.print("解密后的结果是：");
			//解密
			String decrypttext=des.dec(msg,kcv);
			System.out.println(decrypttext);
			return decrypttext;
	    	
	    }
	 
	 
	 public String TGS_DES_encry(String msg,int begin,int end,String k) throws UnsupportedEncodingException{
			msg=msg.substring(begin, end);
			
			test c=new test();
			

			DES des=new DES();
			//加密
			String encrypttext=des.enc(msg,k);
			System.out.println("加密后的结果是：");
			System.out.println(encrypttext);

			return encrypttext;
		}


}
