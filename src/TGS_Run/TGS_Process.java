package TGS_Run;
import DES_METHOD.*;

import java.io.UnsupportedEncodingException;
import java.text.ParseException;

import javax.swing.JTextArea;

import Control_Package.Eighth_ctol;

public class TGS_Process {
	
	private String Ktgs="AAAAAAAA";
	public JTextArea tx;
	
	public String process(String msg,JTextArea tt) throws UnsupportedEncodingException, Exception {
		
		int jud=judge_nums(msg);
		tx=tt;
		switch(jud) {
		case 5:
		{
			String result="";
			
			result=TGS_process_5(msg);
			result="000110"+result;
			return result;
		}
		default:{
			System.out.print("5 control package error");
			return "";
		}
		}
		
	}
	
	
	public String TGS_process_5(String msg) throws UnsupportedEncodingException, ParseException {
		
		TGS_5nums_Proc Proc5=new TGS_5nums_Proc();
		
		String result=Proc5.process(msg,tx);
		
		
		return result;
		
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


}
