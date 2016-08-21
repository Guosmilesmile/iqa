package cn.edu.xmu.entity;

import java.sql.Date;

public class TeacherPaper {
	public TeacherPaper(String tp_colleges, String tp_papertitle,
			String tp_paperclasses, String tp_authors, String tp_serial,
			String tp_authorclasses, String tp_publication, String tp_year,
			String tp_month, String tp_classes, String tp_college, int isnull) {
		super();
		this.tp_colleges = tp_colleges;
		this.tp_papertitle = tp_papertitle;
		this.tp_paperclasses = tp_paperclasses;
		this.tp_authors = tp_authors;
		this.tp_serial = tp_serial;
		this.tp_authorclasses = tp_authorclasses;
		this.tp_publication = tp_publication;
		this.tp_year = tp_year;
		this.tp_month = tp_month;
		this.tp_classes = tp_classes;
		this.tp_college = tp_college;
		this.isnull = isnull;
	}

	private int tp_id;
	private String tp_colleges;//学院
	private String tp_papertitle;//论文题目
	private String tp_paperclasses;//论文类别	
	private String tp_authors;//作者	
	private String tp_serial;//作者排序
	private String tp_authorclasses;//作者类别	
	private String tp_publication;//发表刊物名称
	private String tp_year;//发表年份
	private String tp_month;//发表时间	
	private String tp_classes;//刊物类别
	
	private int tp_serialnumber;// 序列号
	
	private Date tp_deadline;// 截止日期
	
	private String tp_college;// 所属学院
	
	private String tp_comments;// 审核意见
	private int isnull;
	
	public TeacherPaper(String tp_colleges, String tp_papertitle,
			String tp_paperclasses, String tp_authors, String tp_serial,
			String tp_authorclasses, String tp_publication, String tp_year,
			String tp_month, String tp_classes, int tp_serialnumber,
			String tp_college, String tp_comments, int isnull) {
		super();
		this.tp_colleges = tp_colleges;
		this.tp_papertitle = tp_papertitle;
		this.tp_paperclasses = tp_paperclasses;
		this.tp_authors = tp_authors;
		this.tp_serial = tp_serial;
		this.tp_authorclasses = tp_authorclasses;
		this.tp_publication = tp_publication;
		this.tp_year = tp_year;
		this.tp_month = tp_month;
		this.tp_classes = tp_classes;
		this.tp_serialnumber = tp_serialnumber;
		this.tp_college = tp_college;
		this.tp_comments = tp_comments;
		this.isnull = isnull;
	}

	public int getIsnull() {
		return isnull;
	}

	public void setIsnull(int isnull) {
		this.isnull = isnull;
	}

	public TeacherPaper() {
		super();
		// TODO Auto-generated constructor stub
	}

	public TeacherPaper(String tp_colleges, String tp_papertitle,
			String tp_paperclasses, String tp_authors, String tp_serial,
			String tp_authorclasses, String tp_publication, String tp_year,
			String tp_month, String tp_classes, String tp_college) {
		super();
		this.tp_colleges = tp_colleges;
		this.tp_papertitle = tp_papertitle;
		this.tp_paperclasses = tp_paperclasses;
		this.tp_authors = tp_authors;
		this.tp_serial = tp_serial;
		this.tp_authorclasses = tp_authorclasses;
		this.tp_publication = tp_publication;
		this.tp_year = tp_year;
		this.tp_month = tp_month;
		this.tp_classes = tp_classes;
		this.tp_college = tp_college;
	}

	public TeacherPaper(int tp_id, String tp_colleges, String tp_papertitle,
			String tp_paperclasses, String tp_authors, String tp_serial,
			String tp_authorclasses, String tp_publication, String tp_year,
			String tp_month, String tp_classes,
			int tp_serialnumber, Date tp_deadline, String tp_college,
			String tp_comments) {
		super();
		this.tp_id = tp_id;
		this.tp_colleges = tp_colleges;
		this.tp_papertitle = tp_papertitle;
		this.tp_paperclasses = tp_paperclasses;
		this.tp_authors = tp_authors;
		this.tp_serial = tp_serial;
		this.tp_authorclasses = tp_authorclasses;
		this.tp_publication = tp_publication;
		this.tp_year = tp_year;
		this.tp_month = tp_month;
		this.tp_classes = tp_classes;
		this.tp_serialnumber = tp_serialnumber;
		this.tp_deadline = tp_deadline;
		this.tp_college = tp_college;
		this.tp_comments = tp_comments;
	}

	public TeacherPaper(String tp_colleges, String tp_papertitle,
			String tp_paperclasses, String tp_authors, String tp_serial,
			String tp_authorclasses, String tp_publication, String tp_year,
			String tp_month, String tp_classes,
			int tp_serialnumber, String tp_college, String tp_comments) {
		super();
		this.tp_colleges = tp_colleges;
		this.tp_papertitle = tp_papertitle;
		this.tp_paperclasses = tp_paperclasses;
		this.tp_authors = tp_authors;
		this.tp_serial = tp_serial;
		this.tp_authorclasses = tp_authorclasses;
		this.tp_publication = tp_publication;
		this.tp_year = tp_year;
		this.tp_month = tp_month;
		this.tp_classes = tp_classes;
		this.tp_serialnumber = tp_serialnumber;
		this.tp_college = tp_college;
		this.tp_comments = tp_comments;
	}

	public TeacherPaper(String tp_colleges, String tp_papertitle,
			String tp_paperclasses, String tp_authors, String tp_serial,
			String tp_authorclasses, String tp_publication, String tp_year,
			String tp_month, String tp_classes, 
			int tp_serialnumber, String tp_comments) {
		super();
		this.tp_colleges = tp_colleges;
		this.tp_papertitle = tp_papertitle;
		this.tp_paperclasses = tp_paperclasses;
		this.tp_authors = tp_authors;
		this.tp_serial = tp_serial;
		this.tp_authorclasses = tp_authorclasses;
		this.tp_publication = tp_publication;
		this.tp_year = tp_year;
		this.tp_month = tp_month;
		this.tp_classes = tp_classes;
		this.tp_serialnumber = tp_serialnumber;
		this.tp_comments = tp_comments;
	}

	public int getTp_id() {
		return tp_id;
	}

	public void setTp_id(int tp_id) {
		this.tp_id = tp_id;
	}

	public String getTp_colleges() {
		return tp_colleges;
	}

	public void setTp_colleges(String tp_colleges) {
		this.tp_colleges = tp_colleges;
	}

	public String getTp_papertitle() {
		return tp_papertitle;
	}

	public void setTp_papertitle(String tp_papertitle) {
		this.tp_papertitle = tp_papertitle;
	}

	public String getTp_paperclasses() {
		return tp_paperclasses;
	}

	public void setTp_paperclasses(String tp_paperclasses) {
		this.tp_paperclasses = tp_paperclasses;
	}

	public String getTp_authors() {
		return tp_authors;
	}

	public void setTp_authors(String tp_authors) {
		this.tp_authors = tp_authors;
	}

	public String getTp_serial() {
		return tp_serial;
	}

	public void setTp_serial(String tp_serial) {
		this.tp_serial = tp_serial;
	}

	public String getTp_authorclasses() {
		return tp_authorclasses;
	}

	public void setTp_authorclasses(String tp_authorclasses) {
		this.tp_authorclasses = tp_authorclasses;
	}

	public String getTp_publication() {
		return tp_publication;
	}

	public void setTp_publication(String tp_publication) {
		this.tp_publication = tp_publication;
	}

	public String getTp_year() {
		return tp_year;
	}

	public void setTp_year(String tp_year) {
		this.tp_year = tp_year;
	}

	public String getTp_month() {
		return tp_month;
	}

	public void setTp_month(String tp_month) {
		this.tp_month = tp_month;
	}

	public String getTp_classes() {
		return tp_classes;
	}

	public void setTp_classes(String tp_classes) {
		this.tp_classes = tp_classes;
	}


	public int getTp_serialnumber() {
		return tp_serialnumber;
	}

	public void setTp_serialnumber(int tp_serialnumber) {
		this.tp_serialnumber = tp_serialnumber;
	}

	public Date getTp_deadline() {
		return tp_deadline;
	}

	public void setTp_deadline(Date tp_deadline) {
		this.tp_deadline = tp_deadline;
	}

	public String getTp_college() {
		return tp_college;
	}

	public void setTp_college(String tp_college) {
		this.tp_college = tp_college;
	}

	public String getTp_comments() {
		return tp_comments;
	}

	public void setTp_comments(String tp_comments) {
		this.tp_comments = tp_comments;
	}

	
	
}
