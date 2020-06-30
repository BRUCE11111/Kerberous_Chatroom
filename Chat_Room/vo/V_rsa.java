package vo;

import java.math.BigInteger;

public class V_rsa {

	
	
	public BigInteger client_e;
	
	public BigInteger client_n;
	
	public void set_e(BigInteger msg) {
		this.client_e=msg;
	}
	
	public void set_n(BigInteger msg) {
		
		this.client_n=msg;
		
	}
	
}
