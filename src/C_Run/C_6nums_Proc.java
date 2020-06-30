package C_Run;

import Control_Package.Sixth_ctol;
public class C_6nums_Proc {

	private Sixth_ctol ctol_6;
	
	
	public Sixth_ctol get_6() {
		return this.ctol_6;
	}
	public void process(String msg) {
		
		String kcv=msg.substring(6, 21);
		String idv=msg.substring(22,24);
		int v=trans_IDv(idv);
		String TS4=msg.substring(25,44);
		String ticketv=msg.substring(45,107);
		
		ctol_6.write_Kcv(kcv);
		ctol_6.write_IDv(v);
		ctol_6.write_TS(TS4);
		ctol_6.write_ticketv(ticketv);
	}
	
	
public int trans_IDv(String msg) {
		
		int begin=22,end=24;

    	
    	String juge="";
    	int mid=0;
    	for(int i=begin;i<=end;i++) {
    		char c=msg.charAt(i);
    		if(i!=end) {
    		if(c=='0') {
    			continue;
    		}
    		if(c=='1') {
    			mid=mid+1;
    			//mid=mid+(int)Math.pow(2, (end-begin-1-i));
    		}
    		}else {
    			if(c=='0') {
        			continue;
        		}
        		if(c=='1') {	
        			mid=mid+(int)Math.pow(2, (end-i));
        		}
    		}
    	}
    	
    	return mid;
		
	}
	
	
}
