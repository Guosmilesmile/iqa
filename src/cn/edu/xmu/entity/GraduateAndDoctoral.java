package cn.edu.xmu.entity;

import java.sql.Date;

/**
 * 表4-1-3  博士点、硕士点 (时点)
 * @author yue
 *
 */
public class GraduateAndDoctoral {
	private int gd_id;
	private String gd_name;//名称
	private String gd_code;//代码
	private String gd_departmentname;//单位名称
	private String gd_departmentnumber;//单位号
	private String gd_type;//类型
	private int gd_serialnumber;//序列号
	private Date gd_deadline;//截止日期
	private String gd_college;//所属学院
	private String gd_comments;//审核意见
	private int isnull;
	public GraduateAndDoctoral(int gd_id, String gd_name, String gd_code, String gd_departmentname,
			String gd_departmentnumber, String gd_type, int gd_serialnumber, Date gd_deadline, String gd_college,
			String gd_comments,int isnull) {
		super();
		this.gd_id = gd_id;
		this.gd_name = gd_name;
		this.gd_code = gd_code;
		this.gd_departmentname = gd_departmentname;
		this.gd_departmentnumber = gd_departmentnumber;
		this.gd_type = gd_type;
		this.gd_serialnumber = gd_serialnumber;
		this.gd_deadline = gd_deadline;
		this.gd_college = gd_college;
		this.gd_comments = gd_comments;
		this.isnull = isnull;
	}
	public GraduateAndDoctoral( String gd_name, String gd_code, String gd_departmentname,
			String gd_departmentnumber, String gd_type, int gd_serialnumber,  String gd_college,
			String gd_comments,int isnull) {
		super();

		this.gd_name = gd_name;
		this.gd_code = gd_code;
		this.gd_departmentname = gd_departmentname;
		this.gd_departmentnumber = gd_departmentnumber;
		this.gd_type = gd_type;
		this.gd_serialnumber = gd_serialnumber;
		this.gd_college = gd_college;
		this.gd_comments = gd_comments;
		this.isnull = isnull;
	}
	public GraduateAndDoctoral( String gd_name, String gd_code, String gd_departmentname,
			String gd_departmentnumber, String gd_type,  String gd_college,int isnull) {
		super();

		this.gd_name = gd_name;
		this.gd_code = gd_code;
		this.gd_departmentname = gd_departmentname;
		this.gd_departmentnumber = gd_departmentnumber;
		this.gd_type = gd_type;
		this.gd_college = gd_college;
		this.gd_comments = "";
		this.isnull  = isnull;
	}
	public int getIsnull() {
		return isnull;
	}
	public void setIsnull(int isnull) {
		this.isnull = isnull;
	}
	public int getGd_id() {
		return gd_id;
	}
	public void setGd_id(int gd_id) {
		this.gd_id = gd_id;
	}
	public String getGd_name() {
		return gd_name;
	}
	public void setGd_name(String gd_name) {
		this.gd_name = gd_name;
	}
	public String getGd_code() {
		return gd_code;
	}
	public void setGd_code(String gd_code) {
		this.gd_code = gd_code;
	}
	public String getGd_departmentname() {
		return gd_departmentname;
	}
	public void setGd_departmentname(String gd_departmentname) {
		this.gd_departmentname = gd_departmentname;
	}
	public String getGd_departmentnumber() {
		return gd_departmentnumber;
	}
	public void setGd_departmentnumber(String gd_departmentnumber) {
		this.gd_departmentnumber = gd_departmentnumber;
	}
	public String getGd_type() {
		return gd_type;
	}
	public void setGd_type(String gd_type) {
		this.gd_type = gd_type;
	}
	public int getGd_serialnumber() {
		return gd_serialnumber;
	}
	public void setGd_serialnumber(int gd_serialnumber) {
		this.gd_serialnumber = gd_serialnumber;
	}
	public Date getGd_deadline() {
		return gd_deadline;
	}
	public void setGd_deadline(Date gd_deadline) {
		this.gd_deadline = gd_deadline;
	}
	public String getGd_college() {
		return gd_college;
	}
	public void setGd_college(String gd_college) {
		this.gd_college = gd_college;
	}
	public String getGd_comments() {
		return gd_comments;
	}
	public void setGd_comments(String gd_comments) {
		this.gd_comments = gd_comments;
	}
	
	
}
