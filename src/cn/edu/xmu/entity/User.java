package cn.edu.xmu.entity;

public class User {
	private String u_userid;
	private String u_username;
	private String u_password;
	private int u_islive;
	private String u_deptxi;
	private String bak_2;

	



	public User() {
		super();
		// TODO Auto-generated constructor stub
	}

	public User(String u_userid, String u_username, String u_password,
			int u_islive) {
		super();
		this.u_userid = u_userid;
		this.u_username = u_username;
		this.u_password = u_password;
		this.u_islive = u_islive;
	}

	public User(String u_userid, String u_username, String u_password,
			int u_islive, String u_deptxi) {
		super();
		this.u_userid = u_userid;
		this.u_username = u_username;
		this.u_password = u_password;
		this.u_islive = u_islive;
		this.u_deptxi = u_deptxi;
	}

	public String getU_userid() {
		return u_userid;
	}

	public void setU_userid(String u_userid) {
		this.u_userid = u_userid;
	}

	public String getU_username() {
		return u_username;
	}

	public void setU_username(String u_username) {
		this.u_username = u_username;
	}

	public String getU_password() {
		return u_password;
	}

	public void setU_password(String u_password) {
		this.u_password = u_password;
	}

	public int getU_islive() {
		return u_islive;
	}

	public void setU_islive(int u_islive) {
		this.u_islive = u_islive;
	}


	public String getU_deptxi() {
		return u_deptxi;
	}

	public void setU_deptxi(String u_deptxi) {
		this.u_deptxi = u_deptxi;
	}

	public String getBak_2() {
		return bak_2;
	}

	public void setBak_2(String bak_2) {
		this.bak_2 = bak_2;
	}

}
