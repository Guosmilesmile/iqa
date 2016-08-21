package cn.edu.xmu.entity;

public class Role {
	private int r_roleid;
	private String r_rolename;
	private int r_islive;
	private String bak_2;

	public Role(int r_roleid, String r_rolename, int r_islive) {
		super();
		this.r_roleid = r_roleid;
		this.r_rolename = r_rolename;
		this.r_islive = r_islive;
	}

	public Role(int r_roleid, String r_rolename) {
		super();
		this.r_roleid = r_roleid;
		this.r_rolename = r_rolename;
	}

	public int getR_roleid() {
		return r_roleid;
	}

	public void setR_roleid(int r_roleid) {
		this.r_roleid = r_roleid;
	}

	public String getR_rolename() {
		return r_rolename;
	}

	public void setR_rolename(String r_rolename) {
		this.r_rolename = r_rolename;
	}

	
	public int getR_islive() {
		return r_islive;
	}

	public void setR_islive(int r_islive) {
		this.r_islive = r_islive;
	}

	public String getBak_2() {
		return bak_2;
	}

	public void setBak_2(String bak_2) {
		this.bak_2 = bak_2;
	}

}
