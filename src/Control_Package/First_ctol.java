package Control_Package;


/*
 * 许晓晖定义
 */

public class First_ctol {

	/*注册使用此包，共45位，
	 * 分别为20位用户名String
	 * 3位IDc
	 * 16位用户密码
	 */
	private int Id_Package;
	private String username;
	private int IDc;
	private String password;
	
	public int get_Id_Package() {
		return this.Id_Package;
	}
	
	public String get_username() {
		return this.username;
	}
	
	public int get_IDc() {
		return this.IDc;
	}
	
	public String get_password() {
		return this.password;
	}
}
