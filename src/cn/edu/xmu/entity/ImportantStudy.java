package cn.edu.xmu.entity;

import java.sql.Date;

import cn.edu.xmu.enums.BoolEnum;

/**
 * 
 * @author Gy
 * 表4-1-4  重点学科  (时点)
 */
public class ImportantStudy {
	
	
	
	public ImportantStudy(int ip_studynumber, String ip_studyname,
			String ip_departmentnumber, String ip_departmentname,
			String ip_category, String ip_level, String ip_college, int isnull) {
		super();
		this.ip_studynumber = ip_studynumber;
		this.ip_studyname = ip_studyname;
		this.ip_departmentnumber = ip_departmentnumber;
		this.ip_departmentname = ip_departmentname;
		this.ip_category = ip_category;
		this.ip_level = ip_level;
		this.ip_college = ip_college;
		this.isnull = isnull;
	}
	public ImportantStudy(int ip_studynumber, String ip_studyname,
			String ip_departmentnumber, String ip_departmentname,
			String ip_category, String ip_level, int ip_serialnumber,
			String ip_college, int isnull) {
		super();
		this.ip_studynumber = ip_studynumber;
		this.ip_studyname = ip_studyname;
		this.ip_departmentnumber = ip_departmentnumber;
		this.ip_departmentname = ip_departmentname;
		this.ip_category = ip_category;
		this.ip_level = ip_level;
		this.ip_serialnumber = ip_serialnumber;
		this.ip_college = ip_college;
		this.isnull = isnull;
	}
	private String ip_id;
	private int ip_studynumber;//学科代码
	private String ip_studyname;//重点学科名称
	private String ip_departmentnumber;//单位号
	private String ip_departmentname;//单位名称
	private String ip_category;//学科门类
	private String ip_level;//级别
	private int ip_serialnumber;//序列号
	private Date ip_deadline;//截止日期
	private String ip_college;//所属学院
	private String ip_comments;//审核意见
	private int isnull;
	
	public int getIsnull() {
		return isnull;
	}
	public void setIsnull(int isnull) {
		this.isnull = isnull;
	}
	public ImportantStudy(String ip_id, int ip_studynumber,
			String ip_studyname, String ip_departmentnumber,
			String ip_departmentname, String ip_category, String ip_level,
			int ip_serialnumber, Date ip_deadline, String ip_college,
			String ip_comments) {
		super();
		this.ip_id = ip_id;
		this.ip_studynumber = ip_studynumber;
		this.ip_studyname = ip_studyname;
		this.ip_departmentnumber = ip_departmentnumber;
		this.ip_departmentname = ip_departmentname;
		this.ip_category = ip_category;
		this.ip_level = ip_level;
		this.ip_serialnumber = ip_serialnumber;
		this.ip_deadline = ip_deadline;
		this.ip_college = ip_college;
		this.ip_comments = ip_comments;
	}
	public ImportantStudy() {
		super();
		// TODO Auto-generated constructor stub
	}
	
	
	
	public ImportantStudy(int ip_studynumber, String ip_studyname,
			String ip_departmentnumber, String ip_departmentname,
			String ip_category, String ip_level, String ip_college) {
		super();
		this.ip_studynumber = ip_studynumber;
		this.ip_studyname = ip_studyname;
		this.ip_departmentnumber = ip_departmentnumber;
		this.ip_departmentname = ip_departmentname;
		this.ip_category = ip_category;
		this.ip_level = ip_level;
		this.ip_college = ip_college;
	}
	public ImportantStudy(int ip_studynumber, String ip_studyname,
			String ip_departmentnumber, String ip_departmentname,
			String ip_category, String ip_level, int ip_serialnumber,
			String ip_college) {
		super();
		this.ip_studynumber = ip_studynumber;
		this.ip_studyname = ip_studyname;
		this.ip_departmentnumber = ip_departmentnumber;
		this.ip_departmentname = ip_departmentname;
		this.ip_category = ip_category;
		this.ip_level = ip_level;
		this.ip_serialnumber = ip_serialnumber;
		this.ip_college = ip_college;
	}
	public String getIp_id() {
		return ip_id;
	}
	public void setIp_id(String ip_id) {
		this.ip_id = ip_id;
	}
	public int getIp_studynumber() {
		return ip_studynumber;
	}
	public void setIp_studynumber(int ip_studynumber) {
		this.ip_studynumber = ip_studynumber;
	}
	public String getIp_studyname() {
		return ip_studyname;
	}
	public void setIp_studyname(String ip_studyname) {
		this.ip_studyname = ip_studyname;
	}
	public String getIp_departmentnumber() {
		return ip_departmentnumber;
	}
	public void setIp_departmentnumber(String ip_departmentnumber) {
		this.ip_departmentnumber = ip_departmentnumber;
	}
	public String getIp_departmentname() {
		return ip_departmentname;
	}
	public void setIp_departmentname(String ip_departmentname) {
		this.ip_departmentname = ip_departmentname;
	}
	public String getIp_category() {
		return ip_category;
	}
	public void setIp_category(String ip_category) {
		this.ip_category = ip_category;
	}
	public String getIp_level() {
		return ip_level;
	}
	public void setIp_level(String ip_level) {
		this.ip_level = ip_level;
	}
	public int getIp_serialnumber() {
		return ip_serialnumber;
	}
	public void setIp_serialnumber(int ip_serialnumber) {
		this.ip_serialnumber = ip_serialnumber;
	}
	public Date getIp_deadline() {
		return ip_deadline;
	}
	public void setIp_deadline(Date ip_deadline) {
		this.ip_deadline = ip_deadline;
	}
	public String getIp_college() {
		return ip_college;
	}
	public void setIp_college(String ip_college) {
		this.ip_college = ip_college;
	}
	public String getIp_comments() {
		return ip_comments;
	}
	public void setIp_comments(String ip_comments) {
		this.ip_comments = ip_comments;
	}
	
	
}
