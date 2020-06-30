/**
 * 
 */
package tcpchatroom.datagram;
/**
 * @author xuxiaohui
 *
 */
public class ClientDataGram {
	private short type;
	private String message = null;
	public  final static String delimiter = "&&&"; 
	public final static String nameDelimiter = "###";
	public ClientDataGram(short type,String[] friends,String _message){//chat message 0
		this.type = type;
//		System.out.println("client data gram's friends count:" + friends.length);

		for(int i = 0 ;i < friends.length;i++){
			this.message += nameDelimiter + friends[i]  ;
		} 
		if(_message != null)
			this.message += delimiter + _message;
		else
			this.message += delimiter + "\n";
	
	}
	public ClientDataGram(short type,String name,String password,boolean onlinestate){// login 1
		this.type = type;
		if (onlinestate)
			this.message = name + delimiter + password + delimiter + "online";
		else 
			this.message = name + delimiter + password + delimiter + "offline";
	}
	public ClientDataGram(short type,boolean state){//set state  2
		this.type = type;
		if(state)
			this.message = " "+ delimiter + " "+ delimiter+ "online";
		else 
			this.message = " "+ delimiter + " "+ delimiter+"offline";
	}
	public ClientDataGram(short type,String name,String password){//register and unregister 3  
		this.type = type;
		this.message = name + delimiter + password;
	}
	public ClientDataGram(short type){// logout 4
		this.type = type;
}
	public String toString(){//设计有点小问题  当消息内容 是空的时候  对于只发送 类型的datagram 来说 有信息冗余
//		System.out.println("client datagtam:"+ Short.toString(this.type) + delimiter + this.message);
			return Short.toString(this.type) + delimiter + this.message;
	
	}
}
