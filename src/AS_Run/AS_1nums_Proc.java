package AS_Run;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.Socket;
import java.util.Date;

import factory.DaoFactory;
import tcpchatroom.OnlineUsers;
import tcpchatroom.datagram.ClientDataGramAnalyzer;
import tcpchatroom.datagram.ServerDataGram;
import vo.User;

public class AS_1nums_Proc {
	
	private Socket socket;
	private BufferedReader in;
	private PrintWriter out;
	private User user = new User();
	public AS_1nums_Proc(Socket socket) throws IOException{
		this.socket = socket;
		in = new BufferedReader(new InputStreamReader(socket.getInputStream()));
		out = new PrintWriter(socket.getOutputStream(),true);
	}

	public void process(String username,String password) throws IOException {
		String clientdatagram = in.readLine();
		
		this.user.setUserName(ClientDataGramAnalyzer.getName(clientdatagram));
//		System.out.println("regist name : " + this.user.getUserName());
		this.user.setPasswd(ClientDataGramAnalyzer.getPassword(clientdatagram));
//		System.out.println("regist password : " + ClientDataGramAnalyzer.getPassword(clientdatagram));
		try {
			user.setRegisterTime(new Date());
			if(DaoFactory.getIUserInstance().findByUserName(this.user.getUserName()) != null){
				ServerDataGram logdulp = new ServerDataGram((short)5);//用户名已经注册
				out.println(logdulp.toString());
			
			}
			
			DaoFactory.getIUserInstance().doCreate(user);//放入数据库里
			//发送在线好友列表
			String[] friends = (String[]) OnlineUsers.onlineUsers.toArray(new String[OnlineUsers.onlineUsers.size()]);
				
			ServerDataGram registermessage = new ServerDataGram((short)4,friends);//注册成功
				
			out.println(registermessage.toString());
			//上线提醒：发送所有在线人
			//if(ClientDataGramAnalyzer.getState(clientdatagram).equals("online")){

			ServerDataGram onlineinform = new ServerDataGram((short)8,this.user.getUserName());//**************
			for(Socket client : OnlineUsers.clientList){
					PrintWriter messagetofriend = new PrintWriter(client.getOutputStream(),true);
					messagetofriend.println(onlineinform.toString());
			}
			//}
			OnlineUsers.clientList.add(this.socket);
			OnlineUsers.onlineUsers.add(this.user.getUserName());//*****************	
			OnlineUsers.ssm.put(this.user.getUserName(),this.socket);
			
//			for(String user: OnlineUsers.onlineUsers)
//				System.out.print("在线好友数" + OnlineUsers.onlineUsers.size()+ "online users: " + user);
			
		}
		 catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		
		
		
	}
	
	
	
}
