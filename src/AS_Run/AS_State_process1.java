package AS_Run;

import java.math.BigInteger;

import RSA.Rsa;

public class AS_State_process1 implements AS_State{

	private static Rsa rsa=new Rsa();
	
	public BigInteger Client_e;
	public BigInteger Client_n;
	public BigInteger n;
	public BigInteger d;
	
	
	@Override
	public String doaction(String msg,String address) throws Exception {
		
		String result="";
		
		result=AS_process_1(msg,address);
		
		return result;

	}
	
	
	public String AS_process_1(String msg,String address) throws Exception {//process 1 package and return 2 pakage
		
		String result="";
		System.out.println("�㻹����"+msg);
		String cipher=msg.substring(6, msg.length());
		String M=rsa.decry_msg(cipher, d, n);
		M="000001"+M;
		
		String username=M.substring(6, 26);
		String userADc=M.substring(26,29);
		String userpassword=M.substring(29,45);
		
		AS_login_Proc login=new AS_login_Proc();
		
		if(M.length()!=45) {//�޸�����
			username=format_str(username);
			userADc=format_str(userADc);
			userpassword=format_str(userpassword);
			result=login.run1(username, userpassword);
			
			System.out.println(M+" ��ļٵİ�");
			
			//AS_1nums_Proc pro=new AS_1nums_Proc();
			
			
			if(result.equals("�޸ĳɹ�")) {
			result="XIU";}
			else if(result.equals("�޸�ʧ��")) {
				result="XIUB";
			}else if(result.equals("BUCZ")){
				result="BUCZ";
			}
			
			String response=rsa.encry_msg(result, Client_e, Client_n);
			result="000010"+response;
			return result;
		}
		else {
		username=format_str(username);
		userADc=format_str(userADc);
		userpassword=format_str(userpassword);
		
		
		
		result=login.run(username, userpassword);
		
		System.out.println(M+" ��ļٵİ�");
		
		//AS_1nums_Proc pro=new AS_1nums_Proc();
		
		
		if(result.equals("ע��ɹ�")) {
		result="YES";}
		else if(result.equals("�Ѿ�ע��")) {
			result="HIND";
		}
		
		String response=rsa.encry_msg(result, Client_e, Client_n);
		result="000010"+response;
		
		return result;
		}
	}
	
	
	public String  format_str(String str) {//ɾ���ո�
		
		String mid="";
		for(int i=0;i<str.length();i++) {
			
			char c=str.charAt(i);
			if(c==' ') {
				continue;
			}else {
				mid=mid+c;
			}

		}
		 str="";
		str=mid;
		return str;
		
	}
	
}
