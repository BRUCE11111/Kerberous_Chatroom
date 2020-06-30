/**
 * 
 */
package tcpchatroom.datagram;
/**
 * @author xuxiaohui
 *
 */
public class ServerDataGram {
	private short type;
	private String dataGram = null;
	public final static String delimiter = "&&&";
	public final static String nameDelimiter = "###";
	public ServerDataGram(short type,String username,String message){//chat message 0 4
		this.type = type;
		this.dataGram = username + delimiter + message;
	}
	public ServerDataGram(short type,String [] friends){// login 1
		this.type = type;
			for(int i = 0 ;i < friends.length ;i++){
				this.dataGram += nameDelimiter+ friends[i] ;
			} 
	}
	public ServerDataGram(short type){// 2 3 5
 		this.type = type;
	}
	public ServerDataGram(short type,String username){//6  
		this.type = type;
		this.dataGram = username;
	}
	public ServerDataGram(short type, String username,boolean state){
		this.type = type;
		if(state)
			this.dataGram += username + delimiter +  "online";
		else 
			this.dataGram += username + delimiter + "offline";
		
	}

	public String toString(){	
		return Short.toString(type) + delimiter + this.dataGram;
	}
}
