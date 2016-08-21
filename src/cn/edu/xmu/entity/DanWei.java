package cn.edu.xmu.entity;

/**
 * 
 * @author Lee
 * 自动补充单位号
 */
public class DanWei {
	
	private String danweiname;
	private String danweinumber;
	
	public String getDanweiname() {
		return danweiname;
	}
	public void setDanweiname(String danweiname) {
		this.danweiname = danweiname;
	}
	public String getDanweinumber() {
		return danweinumber;
	}
	public void setDanweinumber(String danweinumber) {
		this.danweinumber = danweinumber;
	}
	
	public DanWei() {
		super();
	}
	public DanWei(String danweiname, String danweinumber) {
		super();
		this.danweiname = danweiname;
		this.danweinumber = danweinumber;
	}
	public DanWei(String union){
		super();
		this.danweiname = union;
	}

	
}
