package C_Run;

import java.io.UnsupportedEncodingException;

import Control_Package.Ticketv;

import Control_Package.Fourth_ctol;
public class C_4nums_Proc {
	
	private Fourth_ctol ctol_4;
	
	public Fourth_ctol get_ctol_4() {
	
		return this.ctol_4;
	}

	public void process(String msg) {
		ctol_4.write_K_ctgs(msg.substring(6, 14));
		ctol_4.write_IDtgs(msg.substring(22, 24));
		ctol_4.write_TS2(msg.substring(24,44));
		ctol_4.write_Lifetime1(msg.substring(44,50));
		ctol_4.write_tickettgs(msg.substring(50,msg.length()));
	}
	
	

	



}
