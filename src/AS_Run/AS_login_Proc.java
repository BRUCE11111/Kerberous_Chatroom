package AS_Run;

import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;

import factory.DaoFactory;
import tcpchatroom.OnlineUsers;
import tcpchatroom.Server;
import tcpchatroom.ServerThread;
import tcpchatroom.datagram.ServerDataGram;
import vo.User;

public class AS_login_Proc {

	private User user = new User();
	

	public String run(String username,String passwd) throws Exception{
		

						System.out.println(username+" "+passwd+"   对不对！！！");
						this.user.setUserName(username);
//						System.out.println("regist name : " + this.user.getUserName());
						this.user.setPasswd(passwd);
//						System.out.println("regist password : " + ClientDataGramAnalyzer.getPassword(clientdatagram));
					
							user.setRegisterTime(new Date());
							if(DaoFactory.getIUserInstance().findByUserName(this.user.getUserName()) != null){
								ServerDataGram logdulp = new ServerDataGram((short)5);//用户名已经注册
								String result="已经注册";;
								return result;
							}
							
							DaoFactory.getIUserInstance().doCreate(user);//放入数据库里
							return "注册成功";								
						}
	
	public String run1(String username,String passwd) throws Exception{
		

		System.out.println(username+" "+passwd+"   对不对！！！");

			String result=DaoFactory.getIUserInstance().updateuserpasswd(username, passwd);//放入数据库里
			if(result.equals("完成")) {
				return "修改成功";
			}else if (result.equals("不存在此用户")){
				return "BUCZ";
			}else {
				return "修改失败";
			}
				
			}
										
		}
	

	

