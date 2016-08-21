package cn.edu.xmu.entity;

public class RolePower {
	private int r_roleid;
	private String r_rolename;
	private int powerid;
	private String powername;

	public RolePower(int r_roleid, String r_rolename, int powerid,
			String powername) {
		super();
		this.r_roleid = r_roleid;
		this.r_rolename = r_rolename;
		this.powerid = powerid;
		this.powername = powername;
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

	public int getPowerid() {
		return powerid;
	}

	public void setPowerid(int powerid) {
		this.powerid = powerid;
	}

	public String getPowername() {
		return powername;
	}

	public void setPowername(String powername) {
		this.powername = powername;
	}

}
