package cn.edu.xmu.entity;

import java.sql.Date;

/*
 * 表4-1-2  博士后流动站   (时点)
 */
public class PostdoctoralMobileStation {
	private int pms_id;
	private String pms_departmentnumber;//单位号
	private String pms_departmentname;//单位名称
	private String pms_stationname;//博士后流动站名称
	private int pms_serialnumber;//序列号
	private Date pms_deadline;//截止日期
	private String pms_college;//学院
	private String pms_comments;//审核意见
	private int isnull;
	public PostdoctoralMobileStation(int pms_id, String pms_departmentnumber, String pms_departmentname,
			String pms_stationname, int pms_serialnumber, Date pms_deadline, String pms_college, String pms_comments,int isnull) {
		super();
		this.pms_id = pms_id;
		this.pms_departmentnumber = pms_departmentnumber;
		this.pms_departmentname = pms_departmentname;
		this.pms_stationname = pms_stationname;
		this.pms_serialnumber = pms_serialnumber;
		this.pms_deadline = pms_deadline;
		this.pms_college = pms_college;
		this.pms_comments = pms_comments;
		this.isnull = isnull;
	}
	public PostdoctoralMobileStation( String pms_departmentnumber, String pms_departmentname,
			String pms_stationname, int pms_serialnumber,  String pms_college, String pms_comments,int isnull) {
		super();
		this.pms_departmentnumber = pms_departmentnumber;
		this.pms_departmentname = pms_departmentname;
		this.pms_stationname = pms_stationname;
		this.pms_serialnumber = pms_serialnumber;
		this.pms_college = pms_college;
		this.pms_comments = pms_comments;
		this.isnull = isnull;
	}
	public PostdoctoralMobileStation( String pms_departmentnumber, String pms_departmentname,
			String pms_stationname,   String pms_college,int isnull) {
		super();
		this.pms_departmentnumber = pms_departmentnumber;
		this.pms_departmentname = pms_departmentname;
		this.pms_stationname = pms_stationname;
		this.pms_college = pms_college;
		this.pms_comments = "";
		this.isnull = isnull;
	}
	public int getIsnull() {
		return isnull;
	}
	public void setIsnull(int isnull) {
		this.isnull = isnull;
	}
	public int getPms_id() {
		return pms_id;
	}
	public void setPms_id(int pms_id) {
		this.pms_id = pms_id;
	}
	public String getPms_departmentnumber() {
		return pms_departmentnumber;
	}
	public void setPms_departmentnumber(String pms_departmentnumber) {
		this.pms_departmentnumber = pms_departmentnumber;
	}
	public String getPms_departmentname() {
		return pms_departmentname;
	}
	public void setPms_departmentname(String pms_departmentname) {
		this.pms_departmentname = pms_departmentname;
	}
	public String getPms_stationname() {
		return pms_stationname;
	}
	public void setPms_stationname(String pms_stationname) {
		this.pms_stationname = pms_stationname;
	}
	public int getPms_serialnumber() {
		return pms_serialnumber;
	}
	public void setPms_serialnumber(int pms_serialnumber) {
		this.pms_serialnumber = pms_serialnumber;
	}
	public Date getPms_deadline() {
		return pms_deadline;
	}
	public void setPms_deadline(Date pms_deadline) {
		this.pms_deadline = pms_deadline;
	}
	public String getPms_college() {
		return pms_college;
	}
	public void setPms_college(String pms_college) {
		this.pms_college = pms_college;
	}
	public String getPms_comments() {
		return pms_comments;
	}
	public void setPms_comments(String pms_comments) {
		this.pms_comments = pms_comments;
	}
	
	
}
