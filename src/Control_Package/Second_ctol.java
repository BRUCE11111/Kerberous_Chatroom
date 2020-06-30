package Control_Package;

public class Second_ctol {
	/*用于注册后，AS返回Client端注册成功与否信息
	 * 前6位 char 控制包信息
	 * 7-9位返回yes or no 占3位
	 */
	
	private int Id_Package;

	private String Register_Info;
	
	public String get_Register_Info() {
		return this.Register_Info;
	}
	
	public int get_Id_Package() {	
		return this.Id_Package;
	}
}
