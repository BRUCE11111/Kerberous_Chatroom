package AS_Run;

import java.math.BigInteger;

import RSA.Rsa;
import X_509.X_509;

public class AS_Context implements AS_State{
	
	
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
		
		
		public AS_State state;
	
	public void setstate(AS_State state) {
		
		this.state= state;
		
		//this.d=st
		
	}
	

	@Override
	public String doaction(String msg, String address) throws Exception {
		// TODO Auto-generated method stub
		System.out.println("现在在1号包这里处理");
		return this.state.doaction(msg,address);
	}
	
	
	
	

}
