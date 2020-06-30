package Control_Package;

/*Editor xu;
 * 
 */

public class Third_ctol {
	/*
	 * 用于KERBEROS 中C向AS请求
	 * 1-6位 控制包代号
	 * 7-9位 IDc
	 * 10-11 IDtgs
	 * 12-31 TS1 系统时间
	 */
	private int Id_Package=3;
    private int IDc;
    private int IDtgs;
	private String TS1;
	
	public String get_TS1() {
		return this.TS1;
	}
	
	public void write_TS1(String msg) {
		this.TS1=msg;
	}
	
	
	public int get_Id_Package() {	
		return this.Id_Package;
	}
	
	public int get_IDc() {
		return this.IDc;
	}
	
	public void write_IDc(int msg) {
		this.IDc=msg;
	}
	
	public int get_IDtgs() {
		return this.IDtgs;
	}
	
	public void write_IDtgs(int msg) {
		this.IDtgs=msg;
	}
	
}
