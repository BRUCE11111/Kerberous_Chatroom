package Control_Package;

/*editor xu;
 * 
 */

public class Sixth_ctol {

	/*
	 * 前6位char经转换为int类型6，代表控制包种类，查看是否是其他控制包，否则结束本模块。
4、7-22位char经转换为String类型，长度必须小于等于16，代表k（cv）客户端与服务器v的秘钥，否则结束本模块。
5、23-25位char经转换为int类型，且大于等于0小于8，标记服务器v代号，否则结束本模块。
6、26-45位char经转换为String类型，代表系统时间，格式必须为年月日时分秒，且字符串内无空格，样例：2018年4月28日8:35:42，否则结束本模块。
7、46-108位经转换，参考Ticket（v）。

	 */
	
	private int Id_Package=6;
	private String Kcv;
	private int IDv;
	private String TS;
	private Ticketv Ticketv_ctol;
	private String ticketv;
	
	public int get_Id_Package() {	
		return this.Id_Package;
	}
	
	public String get_Kcv() {
		return this.Kcv;
	}
	
	public void write_Kcv(String msg) {
		this.Kcv=msg;
	}
	
	public int get_IDv() {
		return this.IDv;
	}
	
	public void write_IDv(int msg) {
		this.IDv=msg;
	}
	
	public String get_TS() {
		return this.TS;
	}
	
	public void write_TS(String msg) {
		this.TS=msg;
	}
	
	public Ticketv get_Ticketv_ctol() {
		return this.Ticketv_ctol;
	}
	
	public String get_ticketv() {
		return this.ticketv;
	}
	
	public void write_ticketv(String msg) {
		this.ticketv=msg;
	}
	
}
