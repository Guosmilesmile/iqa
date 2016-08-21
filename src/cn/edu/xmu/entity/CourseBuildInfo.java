package cn.edu.xmu.entity;

import java.sql.Date;


/**
 * 附表5-2-3课程建设情况（时点）
 * @author yue
 *
 */
public class CourseBuildInfo {
	private int cbi_id;
	private String cbi_institute; //学院
	private String cbi_name;//名称
	private String cbi_chargeperson;//负责人
	private String cbi_type;//类型
	private String cbi_grade;//级别
	private Date cbi_approvaltime;//获准时间
	private int cbi_serialnumber;//序列号
	private Date cbi_deadline;//截止日期
	private String cbi_college;//所属学院
	private String cbi_comments;//审核意见
	private int isnull;
	

	public CourseBuildInfo(int cbi_id, String cbi_institute, String cbi_name,
			String cbi_chargeperson, String cbi_type, String cbi_grade, Date cbi_approvaltime, int cbi_serialnumber,
			Date cbi_deadline, String cbi_college, String cbi_comments,int isnull) {
		super();
		this.cbi_id = cbi_id;
		this.cbi_institute = cbi_institute;
		this.cbi_name = cbi_name;
		this.cbi_chargeperson = cbi_chargeperson;
		this.cbi_type = cbi_type;
		this.cbi_grade = cbi_grade;
		this.cbi_approvaltime = cbi_approvaltime;
		this.cbi_serialnumber = cbi_serialnumber;
		this.cbi_deadline = cbi_deadline;
		this.cbi_college = cbi_college;
		this.cbi_comments = cbi_comments;
		this.isnull = isnull;
	}
	public CourseBuildInfo( String cbi_institute, String cbi_name,
			String cbi_chargeperson, String cbi_type, String cbi_grade, Date cbi_approvaltime, int cbi_serialnumber,
			 String cbi_college, String cbi_comments,int isnull) {
		super();
		this.cbi_institute = cbi_institute;
		this.cbi_name = cbi_name;
		this.cbi_chargeperson = cbi_chargeperson;
		this.cbi_type = cbi_type;
		this.cbi_grade = cbi_grade;
		this.cbi_approvaltime = cbi_approvaltime;
		this.cbi_serialnumber = cbi_serialnumber;
		this.cbi_college = cbi_college;
		this.cbi_comments = cbi_comments;
		this.isnull = isnull;
	}
	public CourseBuildInfo( String cbi_institute, String cbi_name,
			String cbi_chargeperson, String cbi_type, String cbi_grade, Date cbi_approvaltime,String cbi_college,int isnull) {
		super();
		this.cbi_institute = cbi_institute;
		this.cbi_name = cbi_name;
		this.cbi_chargeperson = cbi_chargeperson;
		this.cbi_type = cbi_type;
		this.cbi_grade = cbi_grade;
		this.cbi_approvaltime = cbi_approvaltime;
		this.cbi_college = cbi_college;
		this.cbi_comments = "";
		this.isnull = isnull;
	}
	
	public int getIsnull() {
		return isnull;
	}
	public void setIsnull(int isnull) {
		this.isnull = isnull;
	}
	public int getCbi_id() {
		return cbi_id;
	}
	public void setCbi_id(int cbi_id) {
		this.cbi_id = cbi_id;
	}
	public String getCbi_institute() {
		return cbi_institute;
	}
	public void setCbi_institute(String cbi_institute) {
		this.cbi_institute = cbi_institute;
	}
	public String getCbi_name() {
		return cbi_name;
	}
	public void setCbi_name(String cbi_name) {
		this.cbi_name = cbi_name;
	}
	public String getCbi_chargeperson() {
		return cbi_chargeperson;
	}
	public void setCbi_chargeperson(String cbi_chargeperson) {
		this.cbi_chargeperson = cbi_chargeperson;
	}
	public String getCbi_type() {
		return cbi_type;
	}
	public void setCbi_type(String cbi_type) {
		this.cbi_type = cbi_type;
	}
	public String getCbi_grade() {
		return cbi_grade;
	}
	public void setCbi_grade(String cbi_grade) {
		this.cbi_grade = cbi_grade;
	}
	public Date getCbi_approvaltime() {
		return cbi_approvaltime;
	}
	public void setCbi_approvaltime(Date cbi_approvaltime) {
		this.cbi_approvaltime = cbi_approvaltime;
	}
	public int getCbi_serialnumber() {
		return cbi_serialnumber;
	}
	public void setCbi_serialnumber(int cbi_serialnumber) {
		this.cbi_serialnumber = cbi_serialnumber;
	}
	public Date getCbi_deadline() {
		return cbi_deadline;
	}
	public void setCbi_deadline(Date cbi_deadline) {
		this.cbi_deadline = cbi_deadline;
	}
	public String getCbi_college() {
		return cbi_college;
	}
	public void setCbi_college(String cbi_college) {
		this.cbi_college = cbi_college;
	}
	public String getCbi_comments() {
		return cbi_comments;
	}
	public void setCbi_comments(String cbi_comments) {
		this.cbi_comments = cbi_comments;
	}
	
}
