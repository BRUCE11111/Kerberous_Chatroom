package dao.proxy;
//import java.sql.ResultSet;

import java.sql.SQLException;

import dao.*;
import dao.impl.UserDaoImpl;
import dbc.*;
import vo.*;
public class UserDaoProxy implements IUserDao{
	
	private DatabaseConnection dbc = null;
	private IUserDao dao = null;
	
	public UserDaoProxy() throws Exception{
		this.dbc = new DatabaseConnection();
		this.dao = new UserDaoImpl(this.dbc.getConnection());
	}
	
	public boolean doCreate(User user) throws Exception {
		boolean flag = false;
		try{
			if(this.dao.findByUserName(user.getUserName()) == null){
				flag = this.dao.doCreate(user);
			}
		}catch(Exception e){
			throw e;
		}finally{
			this.dbc.close();
		}
		return flag;
	}
	
	
	public User findByUserName(String name) throws Exception {
		User user = null;
		try{
			user = this.dao.findByUserName(name);
		}catch(Exception e){
			throw e;
		}finally{
			this.dbc.close();
		}
		return user;
	}
	
	
	public User findByUserNameandPasswd(String name,String password) throws Exception {
		User user = null;
		try{
			user = this.dao.findByUserNameandPasswd(name,password);
		}catch(Exception e){
			throw e;
		}finally{
			this.dbc.close();
		}
		return user;
	}
	


	@Override
	public String updateuserpasswd(String name, String password) throws SQLException {
		// TODO Auto-generated method stub
		
		User user = null;
		String result="";
		try{
			result = this.dao.updateuserpasswd(name,password);
		}catch(Exception e){
			//throw e;
		}
		return result;
	
	}
	
	
	
}
