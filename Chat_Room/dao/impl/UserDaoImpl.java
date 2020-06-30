package dao.impl;
import java.sql.*;

import vo.User;
import dao.IUserDao;

public class UserDaoImpl implements IUserDao {
	
	private Connection conn = null;
	private PreparedStatement pstmt = null;
	public UserDaoImpl(Connection conn){
		this.conn = conn;
	}
	public boolean doCreate(User user) throws Exception {
		boolean flag = false;
		String sql = "insert into user (username,passwd,grade,birth,sex,email,phone,registertime) values (?,?,?,?,?,?,?,?)";
		this.pstmt = this.conn.prepareStatement(sql);
		this.pstmt.setString(1,user.getUserName());
		this.pstmt.setString(2,user.getPasswd());
		this.pstmt.setString(3,user.getGrade());
		this.pstmt.setString(4,user.getBirth());
		this.pstmt.setString(5,user.getSex());
		this.pstmt.setString(6, user.getEmail());
		this.pstmt.setString(7,user.getPhone());
		this.pstmt.setDate(8, new java.sql.Date(user.getRegisterTime().getTime()));
		if(this.pstmt.executeUpdate() > 0){
			flag = true;
		}
		this.pstmt.close();
		return flag;
	}
	
	public User findByUserName(String name) throws Exception {
		User user = null;
		String sql = "select username,passwd,grade,birth,sex,email,phone,registertime from user where username=?";
		this.pstmt = this.conn.prepareStatement(sql);
		this.pstmt.setString(1,name);
		ResultSet rs = this.pstmt.executeQuery();
		if(rs.next()){
			user = new User();
			user.setUserName(rs.getString(1));
			user.setPasswd(rs.getString(2));
			user.setGrade(rs.getString(3));
			user.setBirth(rs.getString(4));
			user.setSex(rs.getString(5));
			user.setEmail(rs.getString(6));
			user.setPhone(rs.getString(7));
			user.setRegisterTime(rs.getDate(8));
		}
		this.pstmt.close();	
		return user;
	}

	public User findByUserNameandPasswd(String name, String password)throws Exception {
		User user = null;
		String sql = "select username,passwd,grade,birth,sex,email,phone,registertime from user where username=? and passwd=?";
		this.pstmt = this.conn.prepareStatement(sql);
		this.pstmt.setString(1,name);
		this.pstmt.setString(2,password);
		ResultSet rs = this.pstmt.executeQuery();
		if(rs.next()){
			user = new User();
			user.setUserName(rs.getString(1));
			user.setPasswd(rs.getString(2));
			user.setGrade(rs.getString(3));
			user.setBirth(rs.getString(4));
			user.setSex(rs.getString(5));
			user.setEmail(rs.getString(6));
			user.setPhone(rs.getString(7));
			user.setRegisterTime(rs.getDate(8));
		}
		this.pstmt.close();	
		return user;
	}
	
	public String updateuserpasswd(String name,String password) throws Exception {
		try {
		User user = null;
		 user=findByUserName(name);
		if(user==null) {
			return "不存在此用户";
		}
		
		String sql = "update user set passwd=? where username=?";
		this.pstmt = this.conn.prepareStatement(sql);
		this.pstmt.setString(1,password);
		this.pstmt.setString(2,name);
		this.pstmt.executeUpdate();
		}catch(SQLException e){
			System.out.println(e.getMessage());
		}
		return "完成";
	}
	
}
