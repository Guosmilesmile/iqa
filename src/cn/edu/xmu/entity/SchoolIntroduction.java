package cn.edu.xmu.entity;

import java.util.Date;

/**
 * 
 * @author Luo
 * 学校概况 实体类
 * 全参构造函数
 * date 2015-06-30
 *
 */
public class SchoolIntroduction {
	private int si_id;
	//学校名称
	private String si_schoolname;
	//代码
	private String si_schoolcode;
	//英文名称
	private String si_Englishname;
	//办学类型
	private String si_campustype;
	//学校性质
	private String si_campusnature;
	//举办者
	private String si_host;
	//主管部门
	private String si_department;
	//学校网址
	private String si_website;
	//招生批次
	private String si_admissionsbatches;
	//开班本科教学年份
	private String si_educationstartyear;
	//序列号
	private int si_serialnumber;
	//截止日期
	private Date si_deadline;
	//所属学院
	private String si_college;
	//审核意见
	private String si_comments;
	
	private int isnull;
	
	public SchoolIntroduction(int si_id, String si_schoolname,
			String si_schoolcode, String si_Englishname, String si_campustype,
			String si_campusnature, String si_host, String si_department,
			String si_website, String si_admissionsbatches,
			String si_educationstartyear, int si_serialnumber,
			Date si_deadline, String si_college, String si_comments,int isnull) {
		super();
		this.si_id = si_id;
		this.si_schoolname = si_schoolname;
		this.si_schoolcode = si_schoolcode;
		this.si_Englishname = si_Englishname;
		this.si_campustype = si_campustype;
		this.si_campusnature = si_campusnature;
		this.si_host = si_host;
		this.si_department = si_department;
		this.si_website = si_website;
		this.si_admissionsbatches = si_admissionsbatches;
		this.si_educationstartyear = si_educationstartyear;
		this.si_serialnumber = si_serialnumber;
		this.si_deadline = si_deadline;
		this.si_college = si_college;
		this.si_comments = si_comments;
		this.setIsnull(isnull);
	}
	
	public SchoolIntroduction(int si_id, String si_schoolname,
			String si_schoolcode, String si_Englishname, String si_campustype,
			String si_campusnature, String si_host, String si_department,
			String si_website, String si_admissionsbatches,
			String si_educationstartyear, int si_serialnumber, String si_comments,int isnull,String si_college) {
		super();
		this.si_id = si_id;
		this.si_schoolname = si_schoolname;
		this.si_schoolcode = si_schoolcode;
		this.si_Englishname = si_Englishname;
		this.si_campustype = si_campustype;
		this.si_campusnature = si_campusnature;
		this.si_host = si_host;
		this.si_department = si_department;
		this.si_website = si_website;
		this.si_admissionsbatches = si_admissionsbatches;
		this.si_educationstartyear = si_educationstartyear;
		this.si_serialnumber = si_serialnumber;
		this.si_comments = si_comments;
		this.setIsnull(isnull);
		this.si_college = si_college;
	}
	
	public SchoolIntroduction( String si_schoolname,
			String si_schoolcode, String si_Englishname, String si_campustype,
			String si_campusnature, String si_host, String si_department,
			String si_website, String si_admissionsbatches,
			String si_educationstartyear, int si_serialnumber, String si_college,String si_comments,int isnull) {
		super();
		this.si_schoolname = si_schoolname;
		this.si_schoolcode = si_schoolcode;
		this.si_Englishname = si_Englishname;
		this.si_campustype = si_campustype;
		this.si_campusnature = si_campusnature;
		this.si_host = si_host;
		this.si_department = si_department;
		this.si_website = si_website;
		this.si_admissionsbatches = si_admissionsbatches;
		this.si_educationstartyear = si_educationstartyear;
		this.si_serialnumber = si_serialnumber;
		this.si_college = si_college;
		this.si_comments = si_comments;
		this.setIsnull(isnull);
	}
	
	public SchoolIntroduction(String si_schoolname, String si_schoolcode,
			String si_Englishname, String si_campustype,
			String si_campusnature, String si_host, String si_department,
			String si_website, String si_admissionsbatches,
			String si_educationstartyear, String si_college,int isnull) {
		super();
		this.si_schoolname = si_schoolname;
		this.si_schoolcode = si_schoolcode;
		this.si_Englishname = si_Englishname;
		this.si_campustype = si_campustype;
		this.si_campusnature = si_campusnature;
		this.si_host = si_host;
		this.si_department = si_department;
		this.si_website = si_website;
		this.si_admissionsbatches = si_admissionsbatches;
		this.si_educationstartyear = si_educationstartyear;
		this.si_college = si_college;
		this.isnull = isnull;
	}

	public int getSi_id() {
		return si_id;
	}
	public void setSi_id(int si_id) {
		this.si_id = si_id;
	}
	public String getSi_schoolname() {
		return si_schoolname;
	}
	public void setSi_schoolname(String si_schoolname) {
		this.si_schoolname = si_schoolname;
	}
	public String getSi_schoolcode() {
		return si_schoolcode;
	}
	public void setSi_schoolcode(String si_schoolcode) {
		this.si_schoolcode = si_schoolcode;
	}
	public String getSi_Englishname() {
		return si_Englishname;
	}
	public void setSi_Englishname(String si_Englishname) {
		this.si_Englishname = si_Englishname;
	}
	public String getSi_campustype() {
		return si_campustype;
	}
	public void setSi_campustype(String si_campustype) {
		this.si_campustype = si_campustype;
	}
	public String getSi_campusnature() {
		return si_campusnature;
	}
	public void setSi_campusnature(String si_campusnature) {
		this.si_campusnature = si_campusnature;
	}
	public String getSi_host() {
		return si_host;
	}
	public void setSi_host(String si_host) {
		this.si_host = si_host;
	}
	public String getSi_department() {
		return si_department;
	}
	public void setSi_department(String si_department) {
		this.si_department = si_department;
	}
	public String getSi_website() {
		return si_website;
	}
	public void setSi_website(String si_website) {
		this.si_website = si_website;
	}
	public String getSi_admissionsbatches() {
		return si_admissionsbatches;
	}
	public void setSi_admissionsbatches(String si_admissionsbatches) {
		this.si_admissionsbatches = si_admissionsbatches;
	}
	public String getSi_educationstartyear() {
		return si_educationstartyear;
	}
	public void setSi_educationstartyear(String si_educationstartyear) {
		this.si_educationstartyear = si_educationstartyear;
	}
	public int getSi_serialnumber() {
		return si_serialnumber;
	}
	public void setSi_serialnumber(int si_serialnumber) {
		this.si_serialnumber = si_serialnumber;
	}
	public Date getSi_deadline() {
		return si_deadline;
	}
	public void setSi_deadline(Date si_deadline) {
		this.si_deadline = si_deadline;
	}
	public String getSi_college() {
		return si_college;
	}
	public void setSi_college(String si_college) {
		this.si_college = si_college;
	}
	public String getSi_comments() {
		return si_comments;
	}
	public void setSi_comments(String si_comments) {
		this.si_comments = si_comments;
	}

	public int getIsnull() {
		return isnull;
	}

	public void setIsnull(int isnull) {
		this.isnull = isnull;
	}
	
	

}
