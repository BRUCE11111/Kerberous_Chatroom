package X_509;

public class X_509 {
	
	String version;//∞Ê±æ∫≈
	String serial_nums;//–Ú¡–∫≈
	String sign[];
	String name="true";
	String Pub_key_n;
	String Pub_key_e;

	public void write_version(String msg) {
		this.version=msg;
	}
	
	public void write_serial_nums(String msg) {
		this.serial_nums=msg;
	}
	
	public void write_sign(String msg[]) {
		this.sign=msg;
	}
	
	public void write_name(String msg) {
		this.name=msg;
	}
	
	public void write_Pub_key_n(String msg) {
		this.Pub_key_n=msg;
	}
	
	public void write_Pub_key_e(String msg) {
		this.Pub_key_e=msg;
	}
	
	public String get_version() {
		return this.version;
	}
	
	public String get_serial_nums() {
		return this.serial_nums;
	}
	
	public String[] get_sign() {
		return this.sign;
	}
	
	public String get_name() {
		return this.name;
	
	}
	
	public String get_Pub_key_n() {
		return this.Pub_key_n;
	}
	
	public String get_Pub_key_e() {
		return this.Pub_key_e;
	}

}
