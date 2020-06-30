package C_Run;

import java.text.SimpleDateFormat;
import java.util.Date;

import Control_Package.*;

public class C_3nums_Proc {

	
	private Third_ctol third_pack;
	private String send_third;
	
	public void process() {
		third_pack=new Third_ctol();
		third_pack.write_IDc(0);
		third_pack.write_IDtgs(0);
		third_pack.write_TS1(V_get_time());
	}
	
	 public String V_get_time() {
	    	Date now = new Date(); 
	    	SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy/MM/dd HH:mm:ss");//可以方便地修改日期格式
	    	String hehe = dateFormat.format( now ); 
	    	//System.out.println(hehe); 
	    	int len=hehe.length();
	    	//System.out.println(len); 
	    	return hehe;
	    }
	 
	 public String trans_thirdString() {
		 String result="000011";
		 result=result+trans_int_to_IDc(third_pack.get_IDc());
		 result=result+trans_int_to_IDtgs(third_pack.get_IDtgs());
		 result=result+third_pack.get_TS1()+" ";
		 return result;
	 }
	 
	 public String trans_int_to_IDc(int num) {
		 String result="";
		 
		 String binaryStr = java.lang.Integer.toBinaryString(num);
		 int size=binaryStr.length();
		 int len=3;
		 if(size<len) {
			 int nlen=len-size;
			 for(int i=0;i<nlen;i++) {
				 result=result+"0";
			 }
		 }
		 result=result+binaryStr;
		 
		 return result;
	 }
	 
	 public String trans_int_to_IDtgs(int num) {
		 String result="";
		 
		 String binaryStr = java.lang.Integer.toBinaryString(num);
		 int size=binaryStr.length();
		 int len=2;
		 if(size<len) {
			 int nlen=len-size;
			 for(int i=0;i<nlen;i++) {
				 result=result+"0";
			 }
		 }
		 result=result+binaryStr;
		 
		 return result;
	 }
	 
}
