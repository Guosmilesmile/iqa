package cn.edu.xmu.entity;

import java.sql.Date;



public class AcademicPaper {
	public AcademicPaper(String ap_colleges, String ap_papertitle,
			String ap_grade, String ap_name, String ap_major,
			String ap_publication, Date ap_time, String ap_classes,
			String ap_remark, String ap_college, int isnull) {
		super();
		this.ap_colleges = ap_colleges;
		this.ap_papertitle = ap_papertitle;
		this.ap_grade = ap_grade;
		this.ap_name = ap_name;
		this.ap_major = ap_major;
		this.ap_publication = ap_publication;
		this.ap_time = ap_time;
		this.ap_classes = ap_classes;
		this.ap_remark = ap_remark;
		this.ap_college = ap_college;
		this.isnull = isnull;
	}

	public AcademicPaper(int ap_id, String ap_colleges, String ap_papertitle,
			String ap_grade, String ap_name, String ap_major,
			String ap_publication, Date ap_time, String ap_classes,
			String ap_remark, int ap_serialnumber, String ap_college,
			String ap_comments) {
		super();
		this.ap_id = ap_id;
		this.ap_colleges = ap_colleges;
		this.ap_papertitle = ap_papertitle;
		this.ap_grade = ap_grade;
		this.ap_name = ap_name;
		this.ap_major = ap_major;
		this.ap_publication = ap_publication;
		this.ap_time = ap_time;
		this.ap_classes = ap_classes;
		this.ap_remark = ap_remark;
		this.ap_serialnumber = ap_serialnumber;
		this.ap_college = ap_college;
		this.ap_comments = ap_comments;
	}

	public AcademicPaper(String ap_colleges, String ap_papertitle,
			String ap_grade, String ap_name, String ap_major,
			String ap_publication, Date ap_time, String ap_classes,
			String ap_remark, int ap_serialnumber, String ap_college,
			String ap_comments, int isnull) {
		super();
		this.ap_colleges = ap_colleges;
		this.ap_papertitle = ap_papertitle;
		this.ap_grade = ap_grade;
		this.ap_name = ap_name;
		this.ap_major = ap_major;
		this.ap_publication = ap_publication;
		this.ap_time = ap_time;
		this.ap_classes = ap_classes;
		this.ap_remark = ap_remark;
		this.ap_serialnumber = ap_serialnumber;
		this.ap_college = ap_college;
		this.ap_comments = ap_comments;
		this.isnull = isnull;
	}

	public int getIsnull() {
		return isnull;
	}

	public void setIsnull(int isnull) {
		this.isnull = isnull;
	}

	private int ap_id;
	private String ap_colleges;//学院
	private String ap_papertitle;//论文题目
	private String ap_grade;//
	private String ap_name;//姓名
	private String ap_major;//专业
	private String ap_publication;//发表刊物名称
	private Date ap_time;//发表时间
	private String ap_classes;//刊物类别
	private String ap_remark;//备注
	
	private int ap_serialnumber;// 序列号
	
	private Date ap_deadline;// 截止日期
	
	private String ap_college;// 所属学院
	
	private String ap_comments;// 审核意见

	private int isnull;
	public AcademicPaper() {
		super();
		// TODO Auto-generated constructor stub
	}

	public AcademicPaper(String ap_colleges, String ap_papertitle,
			String ap_grade, String ap_name, String ap_major,
			String ap_publication, Date ap_time, String ap_classes,
			String ap_remark, String ap_college) {
		super();
		this.ap_colleges = ap_colleges;
		this.ap_papertitle = ap_papertitle;
		this.ap_grade = ap_grade;
		this.ap_name = ap_name;
		this.ap_major = ap_major;
		this.ap_publication = ap_publication;
		this.ap_time = ap_time;
		this.ap_classes = ap_classes;
		this.ap_remark = ap_remark;
		this.ap_college = ap_college;
	}

	public AcademicPaper(int ap_id, String ap_colleges, String ap_papertitle,
			String ap_grade, String ap_name, String ap_major,
			String ap_publication, Date ap_time, String ap_classes,
			String ap_remark, int ap_serialnumber, Date ap_deadline,
			String ap_college, String ap_comments) {
		super();
		this.ap_id = ap_id;
		this.ap_colleges = ap_colleges;
		this.ap_papertitle = ap_papertitle;
		this.ap_grade = ap_grade;
		this.ap_name = ap_name;
		this.ap_major = ap_major;
		this.ap_publication = ap_publication;
		this.ap_time = ap_time;
		this.ap_classes = ap_classes;
		this.ap_remark = ap_remark;
		this.ap_serialnumber = ap_serialnumber;
		this.ap_deadline = ap_deadline;
		this.ap_college = ap_college;
		this.ap_comments = ap_comments;
	}

	public AcademicPaper(String ap_colleges, String ap_papertitle,
			String ap_grade, String ap_name, String ap_major,
			String ap_publication, Date ap_time, String ap_classes,
			String ap_remark, int ap_serialnumber, String ap_college,
			String ap_comments) {
		super();
		this.ap_colleges = ap_colleges;
		this.ap_papertitle = ap_papertitle;
		this.ap_grade = ap_grade;
		this.ap_name = ap_name;
		this.ap_major = ap_major;
		this.ap_publication = ap_publication;
		this.ap_time = ap_time;
		this.ap_classes = ap_classes;
		this.ap_remark = ap_remark;
		this.ap_serialnumber = ap_serialnumber;
		this.ap_college = ap_college;
		this.ap_comments = ap_comments;
	}

	public AcademicPaper(String ap_colleges, String ap_papertitle,
			String ap_grade, String ap_name, String ap_major,
			String ap_publication, Date ap_time, String ap_classes,
			String ap_remark, int ap_serialnumber, String ap_comments) {
		super();
		this.ap_colleges = ap_colleges;
		this.ap_papertitle = ap_papertitle;
		this.ap_grade = ap_grade;
		this.ap_name = ap_name;
		this.ap_major = ap_major;
		this.ap_publication = ap_publication;
		this.ap_time = ap_time;
		this.ap_classes = ap_classes;
		this.ap_remark = ap_remark;
		this.ap_serialnumber = ap_serialnumber;
		this.ap_comments = ap_comments;
	}

	public int getAp_id() {
		return ap_id;
	}

	public void setAp_id(int ap_id) {
		this.ap_id = ap_id;
	}

	public String getAp_colleges() {
		return ap_colleges;
	}

	public void setAp_colleges(String ap_colleges) {
		this.ap_colleges = ap_colleges;
	}

	public String getAp_papertitle() {
		return ap_papertitle;
	}

	public void setAp_papertitle(String ap_papertitle) {
		this.ap_papertitle = ap_papertitle;
	}

	public String getAp_grade() {
		return ap_grade;
	}

	public void setAp_grade(String ap_grade) {
		this.ap_grade = ap_grade;
	}

	public String getAp_name() {
		return ap_name;
	}

	public void setAp_name(String ap_name) {
		this.ap_name = ap_name;
	}

	public String getAp_major() {
		return ap_major;
	}

	public void setAp_major(String ap_major) {
		this.ap_major = ap_major;
	}

	public String getAp_publication() {
		return ap_publication;
	}

	public void setAp_publication(String ap_publication) {
		this.ap_publication = ap_publication;
	}

	public Date getAp_time() {
		return ap_time;
	}

	public void setAp_time(Date ap_time) {
		this.ap_time = ap_time;
	}

	public String getAp_classes() {
		return ap_classes;
	}

	public void setAp_classes(String ap_classes) {
		this.ap_classes = ap_classes;
	}

	public String getAp_remark() {
		return ap_remark;
	}

	public void setAp_remark(String ap_remark) {
		this.ap_remark = ap_remark;
	}

	public int getAp_serialnumber() {
		return ap_serialnumber;
	}

	public void setAp_serialnumber(int ap_serialnumber) {
		this.ap_serialnumber = ap_serialnumber;
	}

	public Date getAp_deadline() {
		return ap_deadline;
	}

	public void setAp_deadline(Date ap_deadline) {
		this.ap_deadline = ap_deadline;
	}

	public String getAp_college() {
		return ap_college;
	}

	public void setAp_college(String ap_college) {
		this.ap_college = ap_college;
	}

	public String getAp_comments() {
		return ap_comments;
	}

	public void setAp_comments(String ap_comments) {
		this.ap_comments = ap_comments;
	}
	
}
