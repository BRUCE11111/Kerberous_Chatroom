package V_Run;

import java.io.UnsupportedEncodingException;
import java.text.ParseException;

public class Context implements V_State{
	
	V_State state;
	
	
	
	public void setstate(V_State state) {
		
		this.state=state;
	}

	@Override
	public String doaction(String msg) throws UnsupportedEncodingException, ParseException {
		// TODO Auto-generated method stub
		return this.state.doaction(msg);
	}

	
	
}
