package Control_Package;

public class Authenticator {

	private String IDc;
	private String ADc;
	private String TS;
	
	public String get_IDc() {
		return this.IDc;
	}
	
	public void write_IDc(String msg) {
		this.IDc=msg;
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
	
}
