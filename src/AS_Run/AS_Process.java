package AS_Run;

import java.io.UnsupportedEncodingException;
import java.math.BigInteger;
import java.util.ArrayList;

import javax.swing.JTextArea;

import DES_METHOD.DES;
import DES_METHOD.test;
import RSA.Rsa;
import TGS_Run.TGS_7nums_Proc;
import V_Run.Context;
import X_509.X_509;

public class AS_Process {
	
	private String Ktgs="AAAAAAAA";
	

	//rec info form client's public key  rsa use;
	public BigInteger Client_e;
	public BigInteger Client_n;
	public String rec_sign;
	
	//info of AS's public key
	private BigInteger d;
	public BigInteger e;
	public BigInteger n;
	
	private static Rsa rsa;
	private static X_509 certificate;
	public static String pubs;
	AS_Context ascontext = new AS_Context();
	
public String process(String msg,String address,JTextArea textArea) throws Exception {
	
	
		
		int jud=judge_nums(msg);
		switch(jud) {
		case 1:{
			
			String result="";
			AS_State_process1 state=new AS_State_process1();
		   state.Client_e=this.Client_e;
		   state.Client_n=this.Client_n;
		   state.d=this.d;
		   state.n=this.n;
		 
			ascontext.setstate(state);
			
			result=ascontext.doaction(msg, address);
			
			return result;
			
		}
		
		
		case 3:
		{
            String result="";
			
			result=AS_process_3(msg,address);
			
			return result;
		}
		case 32:
		{
			String result="";
			AS_State_process32 state=new AS_State_process32();
			ascontext.setstate(state);
			result=ascontext.doaction(msg, address);
			if(result.equals("身份")) {
				System.exit(0);
			}
			
			this.Client_e=state.Client_e;
			this.Client_n=state.Client_n;
			this.rec_sign=state.rec_sign;
			
			return result;
		}
		
		case 33:{
			
			AS_State_process33 state=new AS_State_process33();
			ascontext.setstate(state);
			String result=ascontext.doaction(msg, address);
			this.e=state.e;
			this.n=state.n;
			this.d=state.d;
			this.certificate=state.certificate;
			this.pubs=state.pubs;
			
			
			return result;
		}
		default:{
			System.out.print("7 control package error");
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
			
		}
		}else {
			
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






public String AS_process_3(String msg,String address) throws Exception {
	
	AS_3nums_Proc Proc3=new AS_3nums_Proc();
	
	String result=Proc3.process(msg, address);
	
	return result;
	
}


	
public String AS_DES_encry(String msg,int begin,int end,String k) throws UnsupportedEncodingException{
	msg=msg.substring(begin, end);
	
	test c=new test();
	

	DES des=new DES();
	//加密
	String encrypttext=des.enc(msg,k);
	System.out.println("加密后的结果是：");
	System.out.println(encrypttext);

	return encrypttext;
}


public String AS_DES_decry(String msg,int begin,int end) throws UnsupportedEncodingException{
	msg=msg.substring(begin, end+1);
	
	
	

	DES des=new DES();

	System.out.print("解密后的结果是：");
	//解密
	String decrypttext=des.dec(msg,Ktgs);
	System.out.println(decrypttext);
	return decrypttext;
	
}

	
	

}
