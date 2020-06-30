package vo;
import java.util.Date;
public class User {
	private String username = null;
	private String passwd = null;
	private String grade = "";
	private String birth;
	private String sex = null;
	private String email = null;
	private String phone = null;
	private Date registertime = (Date) null;
	private boolean state = true;
	public User(){
		
	}
	public void setUserName(String name){
		this.username = name;
	}
	public void setPasswd(String passwd){
		this.passwd = passwd;
	}
	public void setSex(String sex){
		this.sex = sex;
	}
	public void setGrade(String grade){
		this.grade = grade;
	}
	public void setBirth(String birth){
		this.birth = birth;
	}
	public void setEmail(String email){
		this.email = email;
	}
	public void setPhone(String phone){
		this.phone = phone;
	}
	public void setRegisterTime(Date time){
		this.registertime = time;
	}
	public void setState(boolean state){
		this.state = state;
	}
	

	public String getUserName(){
		return this.username;
	}
	public String getPasswd(){
		return this.passwd;
	}
	public String getSex(){
		return this.sex;
	}
	public String getGrade(){
		return this.grade;
	}
	public String getBirth(){
		return this.birth;
	}
	public String getEmail(){
		return this.email;
	}
	public String getPhone(){
		return this.phone;
	}
	public Date getRegisterTime(){
		return this.registertime;
	}
	public boolean getState(){
		return this.state;
	}
	
	
}
