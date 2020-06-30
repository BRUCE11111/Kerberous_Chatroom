package AS_Run;

import java.io.UnsupportedEncodingException;
import java.text.SimpleDateFormat;
import java.util.Date;

import Control_Package.Third_ctol;
import DES_METHOD.DES;
import DES_METHOD.test;
import factory.DaoFactory;
import tcpchatroom.OnlineUsers;
import tcpchatroom.datagram.ClientDataGramAnalyzer;
import tcpchatroom.datagram.ServerDataGram;
import vo.User;

public class AS_3nums_Proc {
		
	Third_ctol ctol_3;
	private String kc;
	private String ktgs="AAAAAAAA";
	private User user = new User();
	
	
	public String process(String msg,String address) throws Exception
	{
		
		String idc=msg.substring(6, 9);
		String idtgs=msg.substring(9,11);
		String ts1=msg.substring(11,31);
		
		String usname=msg.substring(31,msg.length());
		System.out.println("是否正确");
		//比对用户名数据库
		
		user.setUserName(usname);

			user = DaoFactory.getIUserInstance().findByUserName(this.user.getUserName());
			if(this.user==null){
			//	ServerDataGram loginfailedmessage = new ServerDataGram((short)2);//用户名或密码错误
			
			
			}else {
				
				kc=user.getPasswd();
				
			}
		
		
		//////////////////
		test c=new test();
		String Kctgs=c.genRandomNum();
		String ts2=AS_get_time()+" ";
		String lifetime="300000";
		String kctgs=Kctgs+"        ";
		String mid=kctgs+idtgs+AS_get_time()+" "+lifetime;
		String ADc=address;
	
		int len=ADc.length();
		
		//System.out.println(len);
		if(len<15) {
			
			for(int i=0;i<15-len;i++) {
				ADc=ADc+" ";
			}
			
		}
		
		String ticket=kctgs+idc+ADc+idtgs+AS_get_time()+" "+lifetime;
		
		//System.out.println(ticket+" address!!!!!!!!!!!!!");
		
		String tickettgs=AS_DES_encry(ticket,0,ticket.length(),ktgs);
		//System.out.println("tickettgs M========"+tickettgs);
		//System.out.println(mid+" address!!!!!!!!!!!!!");
		String t=DES_decry(tickettgs,ktgs);
		//System.out.println("tickettgs========"+t);
		String result=mid+tickettgs;
		
		//System.out.println("AS->c========"+result);
		
		result=AS_DES_encry(result,0,result.length(),kc);
		
		return "000100"+result;
		
	}
	 public String DES_decry(String msg,String kcv) throws UnsupportedEncodingException{
	    	
			DES des=new DES();
			String decrypttext=des.dec(msg,kcv);
			return decrypttext;
	    	
	    }
	public String AS_get_time() {
    	Date now = new Date(); 
    	SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy/MM/dd HH:mm:ss");//可以方便地修改日期格式
    	String hehe = dateFormat.format( now ); 
    	//System.out.println(hehe); 
    	int len=hehe.length();
    	//System.out.println(len); 
    	return hehe;
    }
	
	public String AS_DES_encry(String msg,int begin,int end,String k) throws UnsupportedEncodingException{
		msg=msg.substring(begin, end);
		
		test c=new test();
		
		DES des=new DES();
		//加密
		String encrypttext=des.enc(msg,k);


		return encrypttext;
	}


	
}
