package cn.edu.xmu.entity;

public class RoleExamine {
	private int re_id;
	private int re_roleid;
	private String re_rolename;
	private int re_tableid;
	private String re_tablename;
	
	public RoleExamine(int re_id, int re_roleid, String re_rolename,
			int re_tableid, String re_tablename) {
		super();
		this.re_id = re_id;
		this.re_roleid = re_roleid;
		this.re_rolename = re_rolename;
		this.re_tableid = re_tableid;
		this.re_tablename = re_tablename;
	}
	public RoleExamine(int re_id, int re_roleid, String re_rolename,
			int re_tableid) {
		super();
		this.re_id = re_id;
		this.re_roleid = re_roleid;
		this.re_rolename = re_rolename;
		this.re_tableid = re_tableid;
	}
	public int getRe_id() {
		return re_id;
	}
	public void setRe_id(int re_id) {
		this.re_id = re_id;
	}
	public int getRe_roleid() {
		return re_roleid;
	}
	public void setRe_roleid(int re_roleid) {
		this.re_roleid = re_roleid;
	}
	public String getRe_rolename() {
		return re_rolename;
	}
	public void setRe_rolename(String re_rolename) {
		this.re_rolename = re_rolename;
	}
	public int getRe_tableid() {
		return re_tableid;
	}
	public void setRe_tableid(int re_tableid) {
		this.re_tableid = re_tableid;
	}
	public String getRe_tablename() {
		return re_tablename;
	}
	public void setRe_tablename(String re_tablename) {
		this.re_tablename = re_tablename;
	}
	
}
