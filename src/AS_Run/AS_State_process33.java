package AS_Run;

import java.math.BigInteger;
import java.util.ArrayList;

import RSA.Rsa;
import X_509.X_509;

public class AS_State_process33 implements AS_State{
	
	private static Rsa rsa;
	
	//info of AS's public key
		public BigInteger d;
		public BigInteger e;
		public BigInteger n;
		
		public static X_509 certificate;
		
		public static String pubs;

	@Override
	public String doaction(String msg, String address) throws Exception {
		
		String result=AS_process_33(msg);
		
		
		
		// TODO Auto-generated method stub
		return result;
	}

	public String AS_process_33(String msg) {
		
		String result="";
		
		String dd="t";
		
		 rsa =new Rsa();
		    certificate=new X_509();
			rsa.begin();
			String pubn=rsa.n.toString();
			String pube=rsa.e.toString();
			String pubd=rsa.get_d().toString();
			
			e=new BigInteger(pube);
			n=new BigInteger(pubn);
			d=new BigInteger(pubd);
			
			certificate.write_name("AS");
			certificate.write_Pub_key_e(pube);
			certificate.write_Pub_key_n(pubn);
			certificate.write_serial_nums("123321");
			
			
		    String M="t";

			ArrayList<BigInteger> encry= rsa.encry_msg_sign(M);//数字签名
			
			String mid[]=new String[encry.size()];
			
			for(int i=0;i<mid.length;i++) {
				if(i!=(mid.length-1)) {
				mid[i]=encry.get(i).toString()+"&";}
				else {
					mid[i]=encry.get(i).toString();	
				}	
			}
			
			
			certificate.write_sign(mid);
			
			pubs="100001&"+certificate.get_Pub_key_e()+"&"+certificate.get_Pub_key_n()+"&"+
			certificate.get_name()+"&"+trans(certificate.get_sign())+"&"+M;
		
		return pubs;
		
	}
	
	public String trans(String mid[]) {//将字符串数组整理成字符串
		
		String result="";
		
		int len=mid.length;
		
		for(int i=0;i<len;i++) {
			result=result+mid[i];
		}
		
		return result;
		
	}
	
}
