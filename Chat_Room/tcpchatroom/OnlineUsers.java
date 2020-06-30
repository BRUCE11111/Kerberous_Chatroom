/**
 * 
 */
package tcpchatroom;

import java.net.Socket;
import java.util.ArrayList;
import java.util.Map;

/**
 * @author Xu XiaoHui
 *
 */
public class OnlineUsers {
	static public ArrayList<Socket> clientList;
	static public  ArrayList<String> onlineUsers;
	static public  Map<String,Socket> ssm;
	static public  Map<String,Socket> sskey;
	static public  Map<String,String> name_passwd;
}
