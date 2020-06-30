package V_Run;
import DES_METHOD.*;

import java.io.UnsupportedEncodingException;
import java.text.ParseException;

import Control_Package.Eighth_ctol;

public class V_Process {
	
	private String Kv="AAAAAAAA";
	
	public String process(String msg) throws UnsupportedEncodingException, Exception {
		Context context=new Context();
		int jud=judge_nums(msg);
		switch(jud) {
		case 7:
		{
			String result="";
			
			
			context.setstate(new V_state_process7());
			result=context.doaction(msg);
			
			return result;
		}
		case 9:{
			
			
			
		}
		default:{
			System.out.print("5 control package error");
			return "";
		}
		}
		
	}
	
	
	
	
public int judge_nums(String msg) {
		
        int num=6;
    	String juge="";
    	int mid=0;
    	for(int i=0;i<num;i++) {
    		char c=msg.charAt(i);
    		
    		if(i==(num-1)) {
    		if(c=='0') {
    			continue;
    		}
    		if(c=='1') {
    			mid=mid+1;
    		}}else {
    			if(c=='0') {
        			continue;
        		}
        		if(c=='1') {
        			mid=mid+(int)Math.pow(2, (num-1-i));
        		}
    		}
	
    	}
    	

    	return mid;
	}

//    	switch(mid){
//    	case 7:{
//    	//	if(V_numbers_pack(msg)) {
//        		ticketv_rec=V_String_char_ticketv(msg);
//        		authenticator_c_rec=V_String_char_authc(msg);
//        		ctol_8.write_info("YES");
//        		String time=V_get_time();
//        		ctol_8.write_TS(time);
//        		String flag="001000";
//        		String cipher=V_DES_encry(ctol_8.get_info(),0,2);
//        		String cipher1=V_DES_encry(ctol_8.get_TS(),0,ctol_8.get_TS().length()-1);
//        		String result=flag+cipher+cipher1;
//        		return result;
//        	//}else {
////        		ctol_8.write_info("NO");
////        		String flag="001000";
////        		String cipher=V_DES_encry(ctol_8.get_info(),0,2);
////        		String result=flag+cipher;
////        		return result;
//        		
//        	//}
//    		
//    	}
//    	case 9:{
//    		
//    		String result="";
//    		return result;
//    		
//    		
//    	}
//    	
//    	case 11:{
//    		String result="";
//    		
//    		
//    		
//    		
//    		return result;
//    	}
//    	default:
//    	{
//    		System.out.println("switch error");
//    		return "";
//    	}
//    	}
//    	
//    	
//    	
//    
//    }
//    
//    
    public String V_DES_decry(String msg,int begin,int end) throws UnsupportedEncodingException{
    	//msg=msg.substring(begin, end);
    	
    	
		
	
		DES des=new DES();
	
		System.out.print("解密后的结果是：");
		//解密
		String decrypttext=des.dec(msg,Kv);
		decrypttext=decrypttext.substring(begin, end);
		System.out.println(decrypttext);
		return decrypttext;
    	
    }
    
    public String V_DES_encry(String msg,int begin,int end) throws UnsupportedEncodingException{
    	//msg=msg.substring(begin, end);
    	
    	test c=new test();
		String Kcv=c.genRandomNum();
	
		DES des=new DES();
		//加密
		String encrypttext=des.enc(msg,Kcv);
		encrypttext=encrypttext.substring(begin, end);
		System.out.println("加密后的结果是：");
		System.out.println(encrypttext);
	
    	return encrypttext;
    }
    
//>>>>>>> refs/remotes/origin/master


}