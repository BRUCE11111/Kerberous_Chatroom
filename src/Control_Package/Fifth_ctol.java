package Control_Package;
/*editor xu;
 * 
 */

public class Fifth_ctol {

	/*
	 * 前6位char经转换为int类型5，代表控制包种类，否则结束本模块。
4、7-9位char经转换为int类型，且大于等于0小于8，标记服务器v代号，否则结束本模块。
5、10-71位char经转换，格式参照Ticket（tgs）格式，代表Ticket（tgs），否则结束本模块。
6、72-109位char经转换为int类型，格式参照Authenticator(c)，否则结束本模块。

	 */
	private int Id_Package=5;
	private int IDv;
	private Ticket_tgs Ticket_tgs_ctol;
	private Authenticator Authenticator_c;
	
	
	public int get_Id_Package() {	
		return this.Id_Package;
	}
	
	public int get_IDv() {
		return this.IDv;
	}
	
	public void write_IDv(int msg) {
		this.IDv=msg;
	}
	
	public Ticket_tgs get_Ticket_tgs() {
		return this.Ticket_tgs_ctol;
	}
	
	public Authenticator get_Authenticator_c() {
		return this.Authenticator_c;
	}
}
