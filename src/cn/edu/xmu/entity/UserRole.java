package cn.edu.xmu.entity;

public class UserRole {
	private String id;
	private String userid;
	private String rolename;
	public UserRole(String id , String userid,String rolename) {
		super();
		this.id = id;
		this.userid = userid;
		this.rolename = rolename;
	}
	
	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public String getUserid() {
		return userid;
	}
	public void setUserid(String userid) {
		this.userid = userid;
	}
	public String getRolename() {
		return rolename;
	}
	public void setRolename(String rolename) {
		this.rolename = rolename;
	}
	
	
}
