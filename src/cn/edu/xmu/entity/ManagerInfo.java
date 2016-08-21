
package cn.edu.xmu.entity;

import java.sql.Date;


/**
 * 3-3 相关管理人员基本信息
 * @author yue
 *
 */
public class ManagerInfo {

	private int mi_id; //id
	private String mi_name;//姓名
	private String mi_worknumber;//工号
	private String mi_sex;//性别
	private Date mi_birthday;//出生年月:格式：yyyy-mm
	private Date mi_inschooldate; //入校时间:格式：yyyy-mm
	private String mi_managertype;//管理人员类别
	private String mi_departmentnumber;//单位号:参考表1-3和1-4，不在此范围的填000
	private String mi_departmentname;//单位名称
	private String mi_education;//学历
	private String mi_degrees;//最高学位
	private String mi_professionaltitle;//专业技术职称
	private String mi_duty;//职务
	private String mi_college;//填报学院
	private Date mi_deadline;//截止时间
	private int mi_serialnumber;//顺序号
	private String mi_comments;//备注,用于填写审核意见
	private int isnull;
	
	public ManagerInfo( ) {
	
		super();
	}
	public ManagerInfo(int mi_id, String mi_name, String mi_worknumber, String mi_sex, Date mi_birthday,
			Date mi_inschooldate, String mi_managertype, String mi_departmentnumber,
			String mi_departmentname, String mi_education, String mi_degrees,
			String mi_professionaltitle, String mi_duty, String mi_college, Date mi_deadline,
			int mi_serialnumber, String mi_comments,int isnull) {
		super();
		this.mi_id = mi_id;
		this.mi_name = mi_name;
		this.mi_worknumber = mi_worknumber;
		this.mi_sex = mi_sex;
		this.mi_birthday = mi_birthday;
		this.mi_inschooldate = mi_inschooldate;
		this.mi_managertype = mi_managertype;
		this.mi_departmentnumber = mi_departmentnumber;
		this.mi_departmentname = mi_departmentname;
		this.mi_education = mi_education;
		this.mi_degrees = mi_degrees;
		this.mi_professionaltitle = mi_professionaltitle;
		this.mi_duty = mi_duty;
		this.mi_college = mi_college;
		this.mi_deadline = mi_deadline;
		this.mi_serialnumber = mi_serialnumber;
		this.mi_comments = mi_comments;
		this.isnull = isnull;
	}
	public ManagerInfo(String mi_name, String mi_worknumber, String mi_sex, Date mi_birthday,
			Date mi_inschooldate, String mi_managertype, String mi_departmentnumber,
			String mi_departmentname, String mi_education, String mi_degrees,
			String mi_professionaltitle, String mi_duty, String mi_college, 
			int mi_serialnumber, String mi_comments,int isnull) {
		super();
		this.mi_name = mi_name;
		this.mi_worknumber = mi_worknumber;
		this.mi_sex = mi_sex;
		this.mi_birthday = mi_birthday;
		this.mi_inschooldate = mi_inschooldate;
		this.mi_managertype = mi_managertype;
		this.mi_departmentnumber = mi_departmentnumber;
		this.mi_departmentname = mi_departmentname;
		this.mi_education = mi_education;
		this.mi_degrees = mi_degrees;
		this.mi_professionaltitle = mi_professionaltitle;
		this.mi_duty = mi_duty;
		this.mi_college = mi_college;
		this.mi_serialnumber = mi_serialnumber;
		this.mi_comments = mi_comments;
		this.isnull = isnull;
	}
	public ManagerInfo(String mi_name, String mi_worknumber, String mi_sex, Date mi_birthday,
			Date mi_inschooldate, String mi_managertype, String mi_departmentnumber,
			String mi_departmentname, String mi_education, String mi_degrees,
			String mi_professionaltitle, String mi_duty, String mi_college,int isnull) {
		super();
		this.mi_name = mi_name;
		this.mi_worknumber = mi_worknumber;
		this.mi_sex = mi_sex;
		this.mi_birthday = mi_birthday;
		this.mi_inschooldate = mi_inschooldate;
		this.mi_managertype = mi_managertype;
		this.mi_departmentnumber = mi_departmentnumber;
		this.mi_departmentname = mi_departmentname;
		this.mi_education = mi_education;
		this.mi_degrees = mi_degrees;
		this.mi_professionaltitle = mi_professionaltitle;
		this.mi_duty = mi_duty;
		this.mi_college = mi_college;
		this.mi_comments = "";
		this.isnull = isnull;
	}
	
	public Date getMi_deadline() {
		return mi_deadline;
	}
	public void setMi_deadline(Date mi_deadline) {
		this.mi_deadline = mi_deadline;
	}
	public int getIsnull() {
		return isnull;
	}
	public void setIsnull(int isnull) {
		this.isnull = isnull;
	}
	public int getMi_id() {
		return mi_id;
	}

	public void setMi_id(int mi_id) {
		this.mi_id = mi_id;
	}

	public String getMi_name() {
		return mi_name;
	}

	public void setMi_name(String mi_name) {
		this.mi_name = mi_name;
	}

	public String getMi_worknumber() {
		return mi_worknumber;
	}

	public void setMi_worknumber(String mi_worknumber) {
		this.mi_worknumber = mi_worknumber;
	}

	public String getMi_sex() {
		return mi_sex;
	}

	public void setMi_sex(String mi_sex) {
		this.mi_sex = mi_sex;
	}

	public Date getMi_birthday() {
		return mi_birthday;
	}

	public void setMi_birthday(Date mi_birthday) {
		this.mi_birthday = mi_birthday;
	}

	public Date getMi_inschooldate() {
		return mi_inschooldate;
	}

	public void setMi_inschooldate(Date mi_inschooldate) {
		this.mi_inschooldate = mi_inschooldate;
	}

	public String getMi_managertype() {
		return mi_managertype;
	}

	public void setMi_managertype(String mi_managertype) {
		this.mi_managertype = mi_managertype;
	}

	public String getMi_departmentnumber() {
		return mi_departmentnumber;
	}

	public void setMi_departmentnumber(String mi_departmentnumber) {
		this.mi_departmentnumber = mi_departmentnumber;
	}

	public String getMi_departmentname() {
		return mi_departmentname;
	}

	public void setMi_departmentname(String mi_departmentname) {
		this.mi_departmentname = mi_departmentname;
	}

	public String getMi_education() {
		return mi_education;
	}

	public void setMi_education(String mi_education) {
		this.mi_education = mi_education;
	}

	public String getMi_degrees() {
		return mi_degrees;
	}

	public void setMi_degrees(String mi_degrees) {
		this.mi_degrees = mi_degrees;
	}

	public String getMi_professionaltitle() {
		return mi_professionaltitle;
	}

	public void setMi_professionaltitle(String mi_professionaltitle) {
		this.mi_professionaltitle = mi_professionaltitle;
	}

	public String getMi_duty() {
		return mi_duty;
	}

	public void setMi_duty(String mi_duty) {
		this.mi_duty = mi_duty;
	}

	public String getMi_college() {
		return mi_college;
	}

	public void setMi_college(String mi_college) {
		this.mi_college = mi_college;
	}

	public int getMi_serialnumber() {
		return mi_serialnumber;
	}

	public void setMi_serialnumber(int mi_serialnumber) {
		this.mi_serialnumber = mi_serialnumber;
	}

	public String getMi_comments() {
		return mi_comments;
	}

	public void setMi_comments(String mi_comments) {
		this.mi_comments = mi_comments;
	}
	
	
	
}