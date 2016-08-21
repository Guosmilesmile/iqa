package cn.edu.xmu.entity;

public class RoleTablePowerOne {
	private int rp_id;
	private int r_roleid;
	private String r_rolename;
	private int t_tableid;
	private String t_tablename;
	private int rp_powerid;
	
	public RoleTablePowerOne(int rp_id, int r_roleid, String r_rolename, int t_tableid,
			String t_tablename, int rp_powerid) {
		super();
		this.rp_id = rp_id;
		this.r_roleid = r_roleid;
		this.r_rolename = r_rolename;
		this.t_tableid = t_tableid;
		this.t_tablename = t_tablename;
		this.rp_powerid = rp_powerid;
	}
	public int getRp_id() {
		return rp_id;
	}
	public void setRp_id(int rp_id) {
		this.rp_id = rp_id;
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
	public int getT_tableid() {
		return t_tableid;
	}
	public void setT_tableid(int t_tableid) {
		this.t_tableid = t_tableid;
	}
	public String getT_tablename() {
		return t_tablename;
	}
	public void setT_tablename(String t_tablename) {
		this.t_tablename = t_tablename;
	}
	public int getRp_powerid() {
		return rp_powerid;
	}
	public void setRp_powerid(int rp_powerid) {
		this.rp_powerid = rp_powerid;
	}
	
	
}
