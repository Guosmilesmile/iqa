package cn.edu.xmu.entity;

public class SubMenu {

	private int smb_id;
	private int smb_mbid;
	private String smb_ch_name;
	private String smb_en_name;

	public int getSmb_id() {
		return smb_id;
	}

	public void setSmb_id(int smb_id) {
		this.smb_id = smb_id;
	}

	public int getSmb_mbid() {
		return smb_mbid;
	}

	public void setSmb_mbid(int smb_mbid) {
		this.smb_mbid = smb_mbid;
	}

	public String getSmb_ch_name() {
		return smb_ch_name;
	}

	public void setSmb_ch_name(String smb_ch_name) {
		this.smb_ch_name = smb_ch_name;
	}

	public String getSmb_en_name() {
		return smb_en_name;
	}

	public void setSmb_en_name(String smb_en_name) {
		this.smb_en_name = smb_en_name;
	}

	public SubMenu(int smb_id, int smb_mbid, String smb_ch_name,
			String smb_en_name) {
		super();
		this.smb_id = smb_id;
		this.smb_mbid = smb_mbid;
		this.smb_ch_name = smb_ch_name;
		this.smb_en_name = smb_en_name;
	}

	public SubMenu(int smb_mbid, String smb_ch_name, String smb_en_name) {
		super();
		this.smb_mbid = smb_mbid;
		this.smb_ch_name = smb_ch_name;
		this.smb_en_name = smb_en_name;
	}

	public SubMenu() {
		super();
	}

}
