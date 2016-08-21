package cn.edu.xmu.entity;

public class RoleFill {
	private int rr_id;
	private int rr_roleid;
	private String rr_rolename;
	private int rr_tableid;
	private String rr_tablename;
	
	public RoleFill(int rr_id, int rr_roleid, String rr_rolename, int rr_tableid) {
		super();
		this.rr_id = rr_id;
		this.rr_roleid = rr_roleid;
		this.rr_rolename = rr_rolename;
		this.rr_tableid = rr_tableid;
	}
	public RoleFill(int rr_id, int rr_roleid, String rr_rolename, int rr_tableid,
			String rr_tablename) {
		super();
		this.rr_id = rr_id;
		this.rr_roleid = rr_roleid;
		this.rr_rolename = rr_rolename;
		this.rr_tableid = rr_tableid;
		this.rr_tablename = rr_tablename;
	}
	public int getRr_id() {
		return rr_id;
	}
	public void setRr_id(int rr_id) {
		this.rr_id = rr_id;
	}
	public int getRr_roleid() {
		return rr_roleid;
	}
	public void setRr_roleid(int rr_roleid) {
		this.rr_roleid = rr_roleid;
	}
	public int getRr_tableid() {
		return rr_tableid;
	}
	public void setRr_table(int rr_tableid) {
		this.rr_tableid = rr_tableid;
	}
	public String getRr_tablename() {
		return rr_tablename;
	}
	public void setRr_tablename(String rr_tablename) {
		this.rr_tablename = rr_tablename;
	}
	public String getRr_rolename() {
		return rr_rolename;
	}
	public void setRr_rolename(String rr_rolename) {
		this.rr_rolename = rr_rolename;
	}
	public void setRr_tableid(int rr_tableid) {
		this.rr_tableid = rr_tableid;
	}
	
}
