package cn.edu.xmu.entity;

import java.sql.Date;

public class PublishProduct {
	public PublishProduct(String pp_colleges, String pp_productname,
			String pp_grade, String pp_name, String pp_major,
			String pp_publication, Date pp_time, String pp_remark,
			String pp_college, int isnull) {
		super();
		this.pp_colleges = pp_colleges;
		this.pp_productname = pp_productname;
		this.pp_grade = pp_grade;
		this.pp_name = pp_name;
		this.pp_major = pp_major;
		this.pp_publication = pp_publication;
		this.pp_time = pp_time;
		this.pp_remark = pp_remark;
		this.pp_college = pp_college;
		this.isnull = isnull;
	}

	private int pp_id;
	private String pp_colleges;//学院
	private String pp_productname;//作品名称
	private String pp_grade;//
	private String pp_name;//姓名
	private String pp_major;//专业
	private String pp_publication;//发表刊物名称
	private Date pp_time;//发表时间
	private String pp_remark;//备注
	
	private int pp_serialnumber;// 序列号
	
	private Date pp_deadline;// 截止日期
	
	private String pp_college;// 所属学院
	
	private String pp_comments;// 审核意见
	private int isnull;
	

	public PublishProduct(String pp_colleges, String pp_productname,
			String pp_grade, String pp_name, String pp_major,
			String pp_publication, Date pp_time, String pp_remark,
			int pp_serialnumber, String pp_college, String pp_comments,
			int isnull) {
		super();
		this.pp_colleges = pp_colleges;
		this.pp_productname = pp_productname;
		this.pp_grade = pp_grade;
		this.pp_name = pp_name;
		this.pp_major = pp_major;
		this.pp_publication = pp_publication;
		this.pp_time = pp_time;
		this.pp_remark = pp_remark;
		this.pp_serialnumber = pp_serialnumber;
		this.pp_college = pp_college;
		this.pp_comments = pp_comments;
		this.isnull = isnull;
	}

	public int getIsnull() {
		return isnull;
	}

	public void setIsnull(int isnull) {
		this.isnull = isnull;
	}

	public PublishProduct() {
		super();
		// TODO Auto-generated constructor stub
	}

	public PublishProduct(int pp_id, String pp_colleges, String pp_productname,
			String pp_grade, String pp_name, String pp_major,
			String pp_publication, Date pp_time, String pp_remark,
			int pp_serialnumber, String pp_college, String pp_comments) {
		super();
		this.pp_id = pp_id;
		this.pp_colleges = pp_colleges;
		this.pp_productname = pp_productname;
		this.pp_grade = pp_grade;
		this.pp_name = pp_name;
		this.pp_major = pp_major;
		this.pp_publication = pp_publication;
		this.pp_time = pp_time;
		this.pp_remark = pp_remark;
		this.pp_serialnumber = pp_serialnumber;
		this.pp_college = pp_college;
		this.pp_comments = pp_comments;
	}

	public PublishProduct(String pp_colleges, String pp_productname,
			String pp_grade, String pp_name, String pp_major,
			String pp_publication, Date pp_time, String pp_remark,
			String pp_college) {
		super();
		this.pp_colleges = pp_colleges;
		this.pp_productname = pp_productname;
		this.pp_grade = pp_grade;
		this.pp_name = pp_name;
		this.pp_major = pp_major;
		this.pp_publication = pp_publication;
		this.pp_time = pp_time;
		this.pp_remark = pp_remark;
		this.pp_college = pp_college;
	}

	public PublishProduct(int pp_id, String pp_colleges, String pp_productname,
			String pp_grade, String pp_name, String pp_major,
			String pp_publication, Date pp_time, 
			String pp_remark, int pp_serialnumber, Date pp_deadline,
			String pp_college, String pp_comments) {
		super();
		this.pp_id = pp_id;
		this.pp_colleges = pp_colleges;
		this.pp_productname = pp_productname;
		this.pp_grade = pp_grade;
		this.pp_name = pp_name;
		this.pp_major = pp_major;
		this.pp_publication = pp_publication;
		this.pp_time = pp_time;
		this.pp_remark = pp_remark;
		this.pp_serialnumber = pp_serialnumber;
		this.pp_deadline = pp_deadline;
		this.pp_college = pp_college;
		this.pp_comments = pp_comments;
	}

	public PublishProduct(String pp_colleges, String pp_productname,
			String pp_grade, String pp_name, String pp_major,
			String pp_publication, Date pp_time,
			String pp_remark, int pp_serialnumber, String pp_college,
			String pp_comments) {
		super();
		this.pp_colleges = pp_colleges;
		this.pp_productname = pp_productname;
		this.pp_grade = pp_grade;
		this.pp_name = pp_name;
		this.pp_major = pp_major;
		this.pp_publication = pp_publication;
		this.pp_time = pp_time;
		this.pp_remark = pp_remark;
		this.pp_serialnumber = pp_serialnumber;
		this.pp_college = pp_college;
		this.pp_comments = pp_comments;
	}

	public PublishProduct(String pp_colleges, String pp_productname,
			String pp_grade, String pp_name, String pp_major,
			String pp_publication, Date pp_time, 
			String pp_remark, int pp_serialnumber, String pp_comments) {
		super();
		this.pp_colleges = pp_colleges;
		this.pp_productname = pp_productname;
		this.pp_grade = pp_grade;
		this.pp_name = pp_name;
		this.pp_major = pp_major;
		this.pp_publication = pp_publication;
		this.pp_time = pp_time;
		this.pp_remark = pp_remark;
		this.pp_serialnumber = pp_serialnumber;
		this.pp_comments = pp_comments;
	}

	public int getPp_id() {
		return pp_id;
	}

	public void setPp_id(int pp_id) {
		this.pp_id = pp_id;
	}

	public String getPp_colleges() {
		return pp_colleges;
	}

	public void setPp_colleges(String pp_colleges) {
		this.pp_colleges = pp_colleges;
	}

	public String getPp_productname() {
		return pp_productname;
	}

	public void setPp_productname(String pp_productname) {
		this.pp_productname = pp_productname;
	}

	public String getPp_grade() {
		return pp_grade;
	}

	public void setPp_grade(String pp_grade) {
		this.pp_grade = pp_grade;
	}

	public String getPp_name() {
		return pp_name;
	}

	public void setPp_name(String pp_name) {
		this.pp_name = pp_name;
	}

	public String getPp_major() {
		return pp_major;
	}

	public void setPp_major(String pp_major) {
		this.pp_major = pp_major;
	}

	public String getPp_publication() {
		return pp_publication;
	}

	public void setPp_publication(String pp_publication) {
		this.pp_publication = pp_publication;
	}

	public Date getPp_time() {
		return pp_time;
	}

	public void setPp_time(Date pp_time) {
		this.pp_time = pp_time;
	}

	

	public String getPp_remark() {
		return pp_remark;
	}

	public void setPp_remark(String pp_remark) {
		this.pp_remark = pp_remark;
	}

	public int getPp_serialnumber() {
		return pp_serialnumber;
	}

	public void setPp_serialnumber(int pp_serialnumber) {
		this.pp_serialnumber = pp_serialnumber;
	}

	public Date getPp_deadline() {
		return pp_deadline;
	}

	public void setPp_deadline(Date pp_deadline) {
		this.pp_deadline = pp_deadline;
	}

	public String getPp_college() {
		return pp_college;
	}

	public void setPp_college(String pp_college) {
		this.pp_college = pp_college;
	}

	public String getPp_comments() {
		return pp_comments;
	}

	public void setPp_comments(String pp_comments) {
		this.pp_comments = pp_comments;
	}

	
	
}
