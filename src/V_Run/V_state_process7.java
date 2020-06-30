package V_Run;

import java.io.UnsupportedEncodingException;
import java.text.ParseException;

public class V_state_process7 implements V_State {

	
	@Override
	public String doaction(String msg) throws UnsupportedEncodingException, ParseException {
		
		String result="";
		
		
		result=V_process_7(msg);
		
		return result;
		
		
	}
	
public String V_process_7(String msg) throws UnsupportedEncodingException, ParseException {
		
		V_7nums_Proc Proc7=new V_7nums_Proc();
		
		String result=Proc7.process(msg);
		
		
		return result;
		
	}
	
	
}
