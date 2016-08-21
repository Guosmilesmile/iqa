package cn.edu.xmu.entity;

import java.util.Date;

/**
 * 校区地址 实体类
 * 
 * @author Gy
 * @date 2015-6-30 全参构造函数
 */
public class SchoolAddress {

	// id
	private int sa_id;

	// 校区名称
	private String sa_name;

	// 校区地址
	private String sa_address;

	// 序列号
	private int sa_serialnumber;
	// 截止日期
	private Date sa_deadline;
	// 所属学院
	private String sa_college;
	// 审核意见
	private String sa_comments;
	private int isnull;
	public int getIsnull() {
		return isnull;
	}
	public void setIsnull(int isnull) {
		this.isnull = isnull;
	}
	
	public SchoolAddress(String sa_name, String sa_address,
			int sa_serialnumber, Date sa_deadline, String sa_college,
			String sa_comments, int isnull) {
		super();
		this.sa_name = sa_name;
		this.sa_address = sa_address;
		this.sa_serialnumber = sa_serialnumber;
		this.sa_deadline = sa_deadline;
		this.sa_college = sa_college;
		this.sa_comments = sa_comments;
		this.isnull = isnull;
	}
	public SchoolAddress(String sa_name, String sa_address,
			int sa_serialnumber, Date sa_deadline, String sa_college,
			String sa_comments) {
		super();
		this.sa_name = sa_name;
		this.sa_address = sa_address;
		this.sa_serialnumber = sa_serialnumber;
		this.sa_deadline = sa_deadline;
		this.sa_college = sa_college;
		this.sa_comments = sa_comments;
	}
	public SchoolAddress(int sa_id, String sa_name, String sa_address,
			int sa_serialnumber, Date sa_deadline, String sa_college,
			String sa_comments) {
		super();
		this.sa_id = sa_id;
		this.sa_name = sa_name;
		this.sa_address = sa_address;
		this.sa_serialnumber = sa_serialnumber;
		this.sa_deadline = sa_deadline;
		this.sa_college = sa_college;
		this.sa_comments = sa_comments;
	}
	
	public SchoolAddress(int sa_id, String sa_name, String sa_address,
			String sa_college) {
		super();
		this.sa_id = sa_id;
		this.sa_name = sa_name;
		this.sa_address = sa_address;
		this.sa_college = sa_college;
	}
	public int getSa_id() {
		return sa_id;
	}
	public void setSa_id(int sa_id) {
		this.sa_id = sa_id;
	}
	public String getSa_name() {
		return sa_name;
	}
	public void setSa_name(String sa_name) {
		this.sa_name = sa_name;
	}
	public String getSa_address() {
		return sa_address;
	}
	public void setSa_address(String sa_address) {
		this.sa_address = sa_address;
	}
	public int getSa_serialnumber() {
		return sa_serialnumber;
	}
	public void setSa_serialnumber(int sa_serialnumber) {
		this.sa_serialnumber = sa_serialnumber;
	}
	public Date getSa_deadline() {
		return sa_deadline;
	}
	public void setSa_deadline(Date sa_deadline) {
		this.sa_deadline = sa_deadline;
	}
	public String getSa_college() {
		return sa_college;
	}
	public void setSa_college(String sa_college) {
		this.sa_college = sa_college;
	}
	public String getSa_comments() {
		return sa_comments;
	}
	public void setSa_comments(String sa_comments) {
		this.sa_comments = sa_comments;
	}
	
	

}
