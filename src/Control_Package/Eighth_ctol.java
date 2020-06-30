package Control_Package;


public class Eighth_ctol {
	private int Id_Package=8;
	private String info;
	private String TS;

	
	public int get_Id_Package() {	
		
		return this.Id_Package;
	}
	
	public void write_info(String msg) {
		this.info=msg;
	}
	
	public String get_info() {
		return this.info;
	}
	
	public String get_TS() {
		return this.TS;
	}
	
	public void write_TS(String msg) {
		this.TS=msg;
	}
}
