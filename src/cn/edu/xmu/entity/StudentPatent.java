package cn.edu.xmu.entity;

import java.sql.Date;

public class StudentPatent {
	public StudentPatent(String sp_colleges, String sp_patentname,
			String sp_number, String sp_grade, String sp_authors,
			String sp_serial, String sp_major, Date sp_time, String sp_remark,
			String sp_college, int isnull) {
		super();
		this.sp_colleges = sp_colleges;
		this.sp_patentname = sp_patentname;
		this.sp_number = sp_number;
		this.sp_grade = sp_grade;
		this.sp_authors = sp_authors;
		this.sp_serial = sp_serial;
		this.sp_major = sp_major;
		this.sp_time = sp_time;
		this.sp_remark = sp_remark;
		this.sp_college = sp_college;
		this.isnull = isnull;
	}

	private int sp_id;
	private String sp_colleges;//学院
	private String sp_patentname;//专利名称
	private String sp_number;//专利号
	private Date sp_time;//公开授权时间
	private String sp_authors;//作者
	private String sp_serial;//作者排序
	private String sp_major;//专业
	private String sp_grade;//年级
	private String sp_remark;//备注
	
	private int sp_serialnumber;// 序列号
	
	private Date sp_deadline;// 截止日期
	
	private String sp_college;// 所属学院
	
	private String sp_comments;// 审核意见
	private int isnull;
	
	public StudentPatent(String sp_colleges, String sp_patentname,
			String sp_number, String sp_grade, String sp_authors,
			String sp_serial, String sp_major, Date sp_time, String sp_remark,
			int sp_serialnumber, String sp_college, int isnull) {
		super();
		this.sp_colleges = sp_colleges;
		this.sp_patentname = sp_patentname;
		this.sp_number = sp_number;
		this.sp_grade = sp_grade;
		this.sp_authors = sp_authors;
		this.sp_serial = sp_serial;
		this.sp_major = sp_major;
		this.sp_time = sp_time;
		this.sp_remark = sp_remark;
		this.sp_serialnumber = sp_serialnumber;
		this.sp_college = sp_college;
		this.isnull = isnull;
	}

	public StudentPatent(String sp_colleges, String sp_patentname,
			String sp_number, String sp_grade, String sp_authors,
			String sp_serial, String sp_major, Date sp_time, String sp_remark,
			int sp_serialnumber, String sp_college, String sp_comments,
			int isnull) {
		super();
		this.sp_colleges = sp_colleges;
		this.sp_patentname = sp_patentname;
		this.sp_number = sp_number;
		this.sp_grade = sp_grade;
		this.sp_authors = sp_authors;
		this.sp_serial = sp_serial;
		this.sp_major = sp_major;
		this.sp_time = sp_time;
		this.sp_remark = sp_remark;
		this.sp_serialnumber = sp_serialnumber;
		this.sp_college = sp_college;
		this.sp_comments = sp_comments;
		this.isnull = isnull;
	}

	public int getIsnull() {
		return isnull;
	}

	public void setIsnull(int isnull) {
		this.isnull = isnull;
	}

	public StudentPatent() {
		super();
		// TODO Auto-generated constructor stub
	}

	public StudentPatent(String sp_colleges, String sp_patentname,
			String sp_number, String sp_grade, String sp_authors,
			String sp_serial, String sp_major, Date sp_time, String sp_remark,
			String sp_college) {
		super();
		this.sp_colleges = sp_colleges;
		this.sp_patentname = sp_patentname;
		this.sp_number = sp_number;
		this.sp_grade = sp_grade;
		this.sp_authors = sp_authors;
		this.sp_serial = sp_serial;
		this.sp_major = sp_major;
		this.sp_time = sp_time;
		this.sp_remark = sp_remark;
		this.sp_college = sp_college;
	}

	public StudentPatent(String sp_colleges, String sp_patentname,
			String sp_number, String sp_grade, String sp_authors,
			String sp_serial, String sp_major, Date sp_time, String sp_remark,
			int sp_serialnumber, String sp_college, String sp_comments) {
		super();
		this.sp_colleges = sp_colleges;
		this.sp_patentname = sp_patentname;
		this.sp_number = sp_number;
		this.sp_grade = sp_grade;
		this.sp_authors = sp_authors;
		this.sp_serial = sp_serial;
		this.sp_major = sp_major;
		this.sp_time = sp_time;
		this.sp_remark = sp_remark;
		this.sp_serialnumber = sp_serialnumber;
		this.sp_college = sp_college;
		this.sp_comments = sp_comments;
	}

	public StudentPatent(int sp_id, String sp_colleges, String sp_patentname,
			String sp_number, String sp_grade, String sp_authors,
			String sp_serial, String sp_major, Date sp_time, String sp_remark,
			int sp_serialnumber, Date sp_deadline, String sp_college,
			String sp_comments) {
		super();
		this.sp_id = sp_id;
		this.sp_colleges = sp_colleges;
		this.sp_patentname = sp_patentname;
		this.sp_number = sp_number;
		this.sp_grade = sp_grade;
		this.sp_authors = sp_authors;
		this.sp_serial = sp_serial;
		this.sp_major = sp_major;
		this.sp_time = sp_time;
		this.sp_remark = sp_remark;
		this.sp_serialnumber = sp_serialnumber;
		this.sp_deadline = sp_deadline;
		this.sp_college = sp_college;
		this.sp_comments = sp_comments;
	}

	public StudentPatent(String sp_colleges, String sp_patentname,
			String sp_number, String sp_grade, String sp_authors,
			String sp_serial, String sp_major, Date sp_time, String sp_remark,
			int sp_serialnumber, String sp_college) {
		super();
		this.sp_colleges = sp_colleges;
		this.sp_patentname = sp_patentname;
		this.sp_number = sp_number;
		this.sp_grade = sp_grade;
		this.sp_authors = sp_authors;
		this.sp_serial = sp_serial;
		this.sp_major = sp_major;
		this.sp_time = sp_time;
		this.sp_remark = sp_remark;
		this.sp_serialnumber = sp_serialnumber;
		this.sp_college = sp_college;
	}

	public StudentPatent(int sp_id, String sp_colleges, String sp_patentname,
			String sp_number, String sp_grade, String sp_authors,
			String sp_serial, String sp_major, Date sp_time, String sp_remark,
			int sp_serialnumber, String sp_comments) {
		super();
		this.sp_id = sp_id;
		this.sp_colleges = sp_colleges;
		this.sp_patentname = sp_patentname;
		this.sp_number = sp_number;
		this.sp_grade = sp_grade;
		this.sp_authors = sp_authors;
		this.sp_serial = sp_serial;
		this.sp_major = sp_major;
		this.sp_time = sp_time;
		this.sp_remark = sp_remark;
		this.sp_serialnumber = sp_serialnumber;
		this.sp_comments = sp_comments;
	}

	
	public StudentPatent(int sp_id, String sp_colleges, String sp_patentname,
			String sp_number, String sp_grade, String sp_authors,
			String sp_serial, String sp_major, Date sp_time, String sp_remark,
			int sp_serialnumber, String sp_college, String sp_comments) {
		super();
		this.sp_id = sp_id;
		this.sp_colleges = sp_colleges;
		this.sp_patentname = sp_patentname;
		this.sp_number = sp_number;
		this.sp_grade = sp_grade;
		this.sp_authors = sp_authors;
		this.sp_serial = sp_serial;
		this.sp_major = sp_major;
		this.sp_time = sp_time;
		this.sp_remark = sp_remark;
		this.sp_serialnumber = sp_serialnumber;
		this.sp_college = sp_college;
		this.sp_comments = sp_comments;
	}

	public int getSp_id() {
		return sp_id;
	}

	public void setSp_id(int sp_id) {
		this.sp_id = sp_id;
	}

	public String getSp_colleges() {
		return sp_colleges;
	}

	public void setSp_colleges(String sp_colleges) {
		this.sp_colleges = sp_colleges;
	}

	public String getSp_patentname() {
		return sp_patentname;
	}

	public void setSp_patentname(String sp_patentname) {
		this.sp_patentname = sp_patentname;
	}

	public String getSp_number() {
		return sp_number;
	}

	public void setSp_number(String sp_number) {
		this.sp_number = sp_number;
	}

	public String getSp_grade() {
		return sp_grade;
	}

	public void setSp_grade(String sp_grade) {
		this.sp_grade = sp_grade;
	}

	public String getSp_authors() {
		return sp_authors;
	}

	public void setSp_authors(String sp_authors) {
		this.sp_authors = sp_authors;
	}

	public String getSp_serial() {
		return sp_serial;
	}

	public void setSp_serial(String sp_serial) {
		this.sp_serial = sp_serial;
	}

	public String getSp_major() {
		return sp_major;
	}

	public void setSp_major(String sp_major) {
		this.sp_major = sp_major;
	}

	public Date getSp_time() {
		return sp_time;
	}

	public void setSp_time(Date sp_time) {
		this.sp_time = sp_time;
	}

	public String getSp_remark() {
		return sp_remark;
	}

	public void setSp_remark(String sp_remark) {
		this.sp_remark = sp_remark;
	}

	public int getSp_serialnumber() {
		return sp_serialnumber;
	}

	public void setSp_serialnumber(int sp_serialnumber) {
		this.sp_serialnumber = sp_serialnumber;
	}

	public Date getSp_deadline() {
		return sp_deadline;
	}

	public void setSp_deadline(Date sp_deadline) {
		this.sp_deadline = sp_deadline;
	}

	public String getSp_college() {
		return sp_college;
	}

	public void setSp_college(String sp_college) {
		this.sp_college = sp_college;
	}

	public String getSp_comments() {
		return sp_comments;
	}

	public void setSp_comments(String sp_comments) {
		this.sp_comments = sp_comments;
	}

	

	
}
