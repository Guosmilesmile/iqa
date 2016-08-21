package cn.edu.xmu.entity;

import java.sql.Date;

/**
 * 外聘教师基本信息
 * @author zhantu
 *
 */
public class ExternalTeacher {
	private int et_id; 
	private String et_name;//姓名
	private String et_worknumber;//工号
	private String et_sex;//性别
	private Date et_birth;//出生年月
	private Date et_appointment;//聘任时间
	private String et_situation;//任职状态
	private Integer et_term;//聘期
	private String et_departmentnumber;//所属单位
	private String et_education;//学历
	private String et_topeducation;//最高学位
	private String et_professional;//专业技术职称
	private String et_subject;//学科类别
	private String et_job;//工作单位类别
	private String et_teacher;//导师类别
	private String et_area;//地区
	private int et_serialnumber;//序列号
	private Date et_deadline;//截止日期
	private String et_comments;//审核意见
	private String et_college;//所属学院
	private String et_documentnumber;//证件号
	private String et_departmentname;//部门名字

	//记录是否存在空值
	private int isnull;
	public int getEt_id() {
		return et_id;
	}
	public void setEt_id(int et_id) {
		this.et_id = et_id;
	}
	public String getEt_name() {
		return et_name;
	}
	public void setEt_name(String et_name) {
		this.et_name = et_name;
	}
	public String getEt_worknumber() {
		return et_worknumber;
	}
	public void setEt_worknumber(String et_worknumber) {
		this.et_worknumber = et_worknumber;
	}
	public String getEt_sex() {
		return et_sex;
	}
	public void setEt_sex(String et_sex) {
		this.et_sex = et_sex;
	}
	public Date getEt_birth() {
		return et_birth;
	}
	public void setEt_birth(Date et_birth) {
		this.et_birth = et_birth;
	}
	public Date getEt_appointment() {
		return et_appointment;
	}
	public void setEt_appointment(Date et_appointment) {
		this.et_appointment = et_appointment;
	}
	public String getEt_situation() {
		return et_situation;
	}
	public void setEt_situation(String et_situation) {
		this.et_situation = et_situation;
	}
	public Integer getEt_term() {
		return et_term;
	}
	public void setEt_term(Integer et_term) {
		this.et_term = et_term;
	}
	public String getEt_departmentnumber() {
		return et_departmentnumber;
	}
	public void setEt_departmentnumber(String et_departmentnumber) {
		this.et_departmentnumber = et_departmentnumber;
	}
	public String getEt_education() {
		return et_education;
	}
	public void setEt_education(String et_education) {
		this.et_education = et_education;
	}
	public String getEt_topeducation() {
		return et_topeducation;
	}
	public void setEt_topeducation(String et_topeducation) {
		this.et_topeducation = et_topeducation;
	}
	public String getEt_professional() {
		return et_professional;
	}
	public void setEt_professional(String et_professional) {
		this.et_professional = et_professional;
	}
	public String getEt_subject() {
		return et_subject;
	}
	public void setEt_subject(String et_subject) {
		this.et_subject = et_subject;
	}
	public String getEt_job() {
		return et_job;
	}
	public void setEt_job(String et_job) {
		this.et_job = et_job;
	}
	public String getEt_teacher() {
		return et_teacher;
	}
	public void setEt_teacher(String et_teacher) {
		this.et_teacher = et_teacher;
	}
	public String getEt_area() {
		return et_area;
	}
	public void setEt_area(String et_area) {
		this.et_area = et_area;
	}
	public int getEt_serialnumber() {
		return et_serialnumber;
	}
	public void setEt_serialnumber(int et_serialnumber) {
		this.et_serialnumber = et_serialnumber;
	}
	public Date getEt_deadline() {
		return et_deadline;
	}
	public void setEt_deadline(Date et_deadline) {
		this.et_deadline = et_deadline;
	}
	public String getEt_comments() {
		return et_comments;
	}
	public void setEt_comments(String et_comments) {
		this.et_comments = et_comments;
	}
	public String getEt_college() {
		return et_college;
	}
	public void setEt_college(String et_college) {
		this.et_college = et_college;
	}
	public String getEt_documentnumber() {
		return et_documentnumber;
	}
	public void setEt_documentnumber(String et_documentnumber) {
		this.et_documentnumber = et_documentnumber;
	}
	public String getEt_departmentname() {
		return et_departmentname;
	}
	public void setEt_departmentname(String et_departmentname) {
		this.et_departmentname = et_departmentname;
	}
	public int getIsnull() {
		return isnull;
	}

	public void setIsnull(int isnull) {
		this.isnull = isnull;
	}
	public ExternalTeacher() {
		super();
	}
	public ExternalTeacher(int et_id, String et_name, String et_worknumber,
			String et_sex, Date et_birth, Date et_appointment,
			String et_situation, Integer et_term, String et_departmentnumber,
			String et_education, String et_topeducation,
			String et_professional, String et_subject, String et_job,
			String et_teacher, String et_area, int et_serialnumber,
			Date et_deadline, String et_comments, String et_college,
			String et_documentnumber, String et_departmentname, int isnull) {
		super();
		this.et_id = et_id;
		this.et_name = et_name;
		this.et_worknumber = et_worknumber;
		this.et_sex = et_sex;
		this.et_birth = et_birth;
		this.et_appointment = et_appointment;
		this.et_situation = et_situation;
		this.et_term = et_term;
		this.et_departmentnumber = et_departmentnumber;
		this.et_education = et_education;
		this.et_topeducation = et_topeducation;
		this.et_professional = et_professional;
		this.et_subject = et_subject;
		this.et_job = et_job;
		this.et_teacher = et_teacher;
		this.et_area = et_area;
		this.et_serialnumber = et_serialnumber;
		this.et_deadline = et_deadline;
		this.et_comments = et_comments;
		this.et_college = et_college;
		this.et_documentnumber = et_documentnumber;
		this.et_departmentname = et_departmentname;
		this.isnull = isnull;
	}
	public ExternalTeacher(String et_name, String et_worknumber, String et_sex,
			Date et_birth, Date et_appointment, String et_situation,
			Integer et_term, String et_departmentnumber, String et_education,
			String et_topeducation, String et_professional, String et_subject,
			String et_job, String et_teacher, String et_area,
			int et_serialnumber, String et_comments, String et_college,
			String et_documentnumber, String et_departmentname, int isnull) {
		super();
		this.et_name = et_name;
		this.et_worknumber = et_worknumber;
		this.et_sex = et_sex;
		this.et_birth = et_birth;
		this.et_appointment = et_appointment;
		this.et_situation = et_situation;
		this.et_term = et_term;
		this.et_departmentnumber = et_departmentnumber;
		this.et_education = et_education;
		this.et_topeducation = et_topeducation;
		this.et_professional = et_professional;
		this.et_subject = et_subject;
		this.et_job = et_job;
		this.et_teacher = et_teacher;
		this.et_area = et_area;
		this.et_serialnumber = et_serialnumber;
		this.et_comments = et_comments;
		this.et_college = et_college;
		this.et_documentnumber = et_documentnumber;
		this.et_departmentname = et_departmentname;
		this.isnull = isnull;
	}
	public ExternalTeacher(int et_id, String et_name, String et_worknumber,
			String et_sex, Date et_birth, Date et_appointment,
			String et_situation, Integer et_term, String et_departmentnumber,
			String et_education, String et_topeducation,
			String et_professional, String et_subject, String et_job,
			String et_teacher, String et_area, int et_serialnumber,
			String et_comments, String et_documentnumber,
			String et_departmentname, int isnull) {
		super();
		this.et_id = et_id;
		this.et_name = et_name;
		this.et_worknumber = et_worknumber;
		this.et_sex = et_sex;
		this.et_birth = et_birth;
		this.et_appointment = et_appointment;
		this.et_situation = et_situation;
		this.et_term = et_term;
		this.et_departmentnumber = et_departmentnumber;
		this.et_education = et_education;
		this.et_topeducation = et_topeducation;
		this.et_professional = et_professional;
		this.et_subject = et_subject;
		this.et_job = et_job;
		this.et_teacher = et_teacher;
		this.et_area = et_area;
		this.et_serialnumber = et_serialnumber;
		this.et_comments = et_comments;
		this.et_documentnumber = et_documentnumber;
		this.et_departmentname = et_departmentname;
		this.isnull = isnull;
	}
	public ExternalTeacher(int et_id, String et_name, String et_worknumber,
			String et_sex, Date et_birth, Date et_appointment,
			String et_situation, Integer et_term, String et_departmentnumber,
			String et_education, String et_topeducation,
			String et_professional, String et_subject, String et_job,
			String et_teacher, String et_area, String et_comments,
			String et_documentnumber, String et_departmentname, int isnull) {
		super();
		this.et_id = et_id;
		this.et_name = et_name;
		this.et_worknumber = et_worknumber;
		this.et_sex = et_sex;
		this.et_birth = et_birth;
		this.et_appointment = et_appointment;
		this.et_situation = et_situation;
		this.et_term = et_term;
		this.et_departmentnumber = et_departmentnumber;
		this.et_education = et_education;
		this.et_topeducation = et_topeducation;
		this.et_professional = et_professional;
		this.et_subject = et_subject;
		this.et_job = et_job;
		this.et_teacher = et_teacher;
		this.et_area = et_area;
		this.et_comments = et_comments;
		this.et_documentnumber = et_documentnumber;
		this.et_departmentname = et_departmentname;
		this.isnull = isnull;
	}
	public ExternalTeacher(String et_name, String et_worknumber, String et_sex,
			Date et_birth, Date et_appointment, String et_situation,
			Integer et_term, String et_departmentnumber, String et_education,
			String et_topeducation, String et_professional, String et_subject,
			String et_job, String et_teacher, String et_area, String et_departmentname, String et_college, int isnull) {
		super();
		this.et_name = et_name;
		this.et_worknumber = et_worknumber;
		this.et_sex = et_sex;
		this.et_birth = et_birth;
		this.et_appointment = et_appointment;
		this.et_situation = et_situation;
		this.et_term = et_term;
		this.et_departmentnumber = et_departmentnumber;
		this.et_education = et_education;
		this.et_topeducation = et_topeducation;
		this.et_professional = et_professional;
		this.et_subject = et_subject;
		this.et_job = et_job;
		this.et_teacher = et_teacher;
		this.et_area = et_area;
		this.et_departmentname = et_departmentname;
		this.et_college = et_college;
		this.isnull = isnull;
	}
	
}
