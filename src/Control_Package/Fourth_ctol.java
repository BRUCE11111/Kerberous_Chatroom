package Control_Package;

/*edito xu;
 * 
 */

public class Fourth_ctol {
	/*用于注册后，AS返回Client端注册成功与否信息
	 * 前6位 char 控制包信息
	 * 前6位char经转换为int类型4，代表控制包种类，查看是否是其他控制包，否则结束本模块。
4、7-9位char经转换为int类型，且大于0小于等于8，标记是哪个客户端，如果不是本机，否则结束本模块。
5、10-25位char经转换为String类型，且必须16位，代表K（c，tgs）秘钥。
6、26-27位char经转换为int类型，且大于0小于等于4，否则结束本模块。
7、28-47位char经转换为Stirng类型，代表系统时间。
8、48-53位char经转换为String类型，代表存活时间。
9、54-112位char经转换，为Ticket（tgs），参考其格式转换。

	 */
	
	private int Id_Package=4;

	private String K_ctgs;
	private String IDtgs;
	private String TS2;
	private String Lifetime1;
	private Ticket_tgs Ticket_tgs_ctol;
	private String ticket_tgs;
	public String get_K_ctgs() {
		return this.K_ctgs;
	}
	
	public void write_tickettgs(String msg) {
		this.ticket_tgs=msg;
	}
	
	public String get_tickettgs() {
		return this.ticket_tgs;
	}
	
	public void write_K_ctgs(String msg) {
		this.K_ctgs=msg;
	}
	
	
	public String get_TS2() {
		return this.TS2;
	}
	
	public void write_TS2(String msg) {
		this.TS2=msg;
	}
	
	public String get_Lifetime1() {
		return this.Lifetime1;
	}
	
	public void write_Lifetime1(String msg) {
		this.Lifetime1=msg;
	}
	
	public int get_Id_Package() {	
		return this.Id_Package;
	}
	
	public String get_IDtgs() {	
		return this.IDtgs;
	}
	
	public void write_IDtgs(String msg) {
		
		this.IDtgs=msg;
	}
	
	public Ticket_tgs Ticket_tgs_ctol() {	
		return this.Ticket_tgs_ctol;
	}
}
