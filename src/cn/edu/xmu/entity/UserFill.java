package cn.edu.xmu.entity;


public class UserFill {
	private int id;
	private int tableid;
	private String tablename;
	private String rolename;
	private int rf_fillsituation;//提交状态
	private int rf_reviewsituation;//审核状态
	private String rf_reviewcontent;//审核意见
	private int roleid;
	private int sonid;
	
	public UserFill(int tableid, String tablename, String rolename,
			int rf_fillsituation, int rf_reviewsituation,
			String rf_reviewcontent) {
		super();
		this.tableid = tableid;
		this.tablename = tablename;
		this.rolename = rolename;
		this.rf_fillsituation = rf_fillsituation;
		this.rf_reviewsituation = rf_reviewsituation;
		this.rf_reviewcontent = rf_reviewcontent;
	}
	
	public UserFill(int tableid, String tablename, String rolename,
			int rf_fillsituation, int rf_reviewsituation,
			String rf_reviewcontent, int roleid) {
		super();
		this.tableid = tableid;
		this.tablename = tablename;
		this.rolename = rolename;
		this.rf_fillsituation = rf_fillsituation;
		this.rf_reviewsituation = rf_reviewsituation;
		this.rf_reviewcontent = rf_reviewcontent;
		this.roleid = roleid;
	}

	public int getRoleid() {
		return roleid;
	}

	public void setRoleid(int roleid) {
		this.roleid = roleid;
	}

	public String getRolename() {
		return rolename;
	}
	public void setRolename(String rolename) {
		this.rolename = rolename;
	}
	public UserFill(int tableid, String tablename, int rf_fillsituation,
			int rf_reviewsituation, String rf_reviewcontent) {
		super();
		this.tableid = tableid;
		this.tablename = tablename;
		this.rf_fillsituation = rf_fillsituation;
		this.rf_reviewsituation = rf_reviewsituation;
		this.rf_reviewcontent = rf_reviewcontent;
	}
	public UserFill() {
		super();
	}
	public int getSonid() {
		return sonid;
	}
	public void setSonid(int sonid) {
		this.sonid = sonid;
	}

	public UserFill(int id, int tableid, String tablename,
			int rf_fillsituation, int rf_reviewsituation,
			String rf_reviewcontent, int sonid) {
		super();
		this.id = id;
		this.tableid = tableid;
		this.tablename = tablename;
		this.rf_fillsituation = rf_fillsituation;
		this.rf_reviewsituation = rf_reviewsituation;
		this.rf_reviewcontent = rf_reviewcontent;
		this.sonid = sonid;
	}






	public String getRf_reviewcontent() {
		return rf_reviewcontent;
	}



	public void setRf_reviewcontent(String rf_reviewcontent) {
		this.rf_reviewcontent = rf_reviewcontent;
	}



	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public int getTableid() {
		return tableid;
	}
	public void setTableid(int tableid) {
		this.tableid = tableid;
	}
	public String getTablename() {
		return tablename;
	}
	public void setTablename(String tablename) {
		this.tablename = tablename;
	}
	public int getRf_fillsituation() {
		return rf_fillsituation;
	}
	public void setRf_fillsituation(int rf_fillsituation) {
		this.rf_fillsituation = rf_fillsituation;
	}
	public int getRf_reviewsituation() {
		return rf_reviewsituation;
	}
	public void setRf_reviewsituation(int rf_reviewsituation) {
		this.rf_reviewsituation = rf_reviewsituation;
	}

	public UserFill(int tableid, String tablename, int roleid, String rolename,
			int rf_fillsituation, int rf_reviewsituation) {
		super();
		this.tableid = tableid;
		this.tablename = tablename;
		this.roleid = roleid;
		this.rolename = rolename;
		this.rf_fillsituation = rf_fillsituation;
		this.rf_reviewsituation = rf_reviewsituation;
	}
	
	
	
}
