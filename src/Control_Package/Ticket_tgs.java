package Control_Package;

/*
 * editor xu;
 */
public class Ticket_tgs {

	private String K_ctgs;
	private String IDc;
	private String ADc;
	private String IDtgs;
	private String TS;
	private String Lifetime2;
	
	public String get_K_ctgs() {
		return this.K_ctgs;
	}
	
	public void write_Kctgs(String msg) {
		this.K_ctgs=msg;
	}
	
	public String get_ADc() {
		return this.ADc;
	}
	
	public void write_ADc(String msg) {
		this.ADc=msg;
	}
	
	public String get_TS() {
		return this.TS;
	}
	
	public void write_TS(String msg) {
		this.TS=msg;
	}
	
	public String get_Lifetime2() {
		return this.Lifetime2;
	}
	
	public void write_Lifetie2(String msg) {
		this.Lifetime2=msg;
	}
	
	public String get_IDc() {
		return this.IDc;
	}
	
	public void write_IDc(String msg) {
		this.IDc=msg;
	}
	
	public String get_IDtgs() {
		return this.IDtgs;
	}
	
	public void write_IDtgs(String msg) {
		this.IDtgs=msg;
	}
	
}
