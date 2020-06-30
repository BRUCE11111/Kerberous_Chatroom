package C_Run;

import java.math.BigInteger;
import java.util.ArrayList;

import RSA.Rsa;

public class C_1nums_Proc {//处理用户输入的用户名和秘钥
	
	public BigInteger AS_n;
	public BigInteger AS_e;
	private String M;
	public String AS_sign;
	
	
	public String  process(String name,String password,String ADc) {
		
//		name="xuxiaohui";
//		password="123456";
//		ADc="001";
		
		String result="";
		result=result+name;
		int len=name.length();
		
		for(int i=len;i<20;i++) {//user's name
			result=result+" ";
		}
		result=result+ADc;//user's id of client
		len=ADc.length();
		for(int i=len;i<3;i++) {
			result=result+" ";
		}
		
		result=result+password;
		len=password.length();//user's password
		for(int i=len;i<16;i++) {
			result=result+" ";
		}
		
		M=result;
		
		
		
		
		return result;
	}
	
	
	public String process_1(String msg,BigInteger mide,BigInteger midn,String info) {
		String result="";
	
		
		Rsa rsa=new Rsa();
		M=M+info;
		result=rsa.encry_msg(M, mide, midn);
		
		
		result="000001"+result;
		
	    System.out.println(result);

		
		return result;
		
	}
	
	
	

}
