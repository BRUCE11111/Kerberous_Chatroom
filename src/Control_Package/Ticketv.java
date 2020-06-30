package Control_Package;

public class Ticketv {

	private String Kcv;
	private String IDc;
	private String ADc;
	private String IDv;
	private String TS;
	private String Lifetime;
	
	public void write_Kcv(String msg) {
		this.Kcv=msg;
	}
	
	public String get_Kcv() {
		return this.Kcv;
	}
	
	public String get_TS() {
		return this.TS;
	}
	
	public void write_TS(String msg) {
		this.TS=msg;
	}
	
	public String get_ADc() {
		return this.ADc;
	}
	
	public void write_ADc(String msg) {
		this.ADc=msg;
	}
	
	public String get_Lifetime() {
		return this.Lifetime;
	}
	
	public void write_Lifetime(String msg) {
		this.Lifetime=msg;
	}
	
	public String get_IDc() {
		return this.IDc;
	}
	
	public void write_IDc(String msg) {
		this.IDc=msg;
	}
	
	public String get_IDv() {
		return this.IDv;
	}
	
	public void write_IDv(String msg) {
		this.IDv=msg;
	}
	
}
