package cn.edu.xmu.entity;

import java.sql.Date;
				
public class FourSix {
	public FourSix(String fx_colleges, String fx_department, String fx_major,
			String fx_grade, String fx_level, int fx_total, int fx_attend,
			int fx_attendcount, int fx_pass, double fx_passpercent, int fx_good,
			double fx_goodpercent, String fx_college, int isnull) {
		super();
		this.fx_colleges = fx_colleges;
		this.fx_department = fx_department;
		this.fx_major = fx_major;
		this.fx_grade = fx_grade;
		this.fx_level = fx_level;
		this.fx_total = fx_total;
		this.fx_attend = fx_attend;
		this.fx_attendcount = fx_attendcount;
		this.fx_pass = fx_pass;
		this.fx_passpercent = fx_passpercent;
		this.fx_good = fx_good;
		this.fx_goodpercent = fx_goodpercent;
		this.fx_college = fx_college;
		this.isnull = isnull;
	}

	private int fx_id;
	private String fx_colleges;//学院
	private String fx_department;//系
	private String fx_major;//专业
	private String fx_grade;//年级
	private String fx_level;//考试级别
	private int fx_total;//学生总人数
	private int fx_attend;//参加人数
	private int fx_attendcount;//累计参加人次数
	private int fx_pass;//通过人数	
	private double fx_passpercent;//通过率
	private int fx_good;//优秀人数
	private double fx_goodpercent;//优秀率
	private int fx_serialnumber;// 序列号
	private Date fx_deadline;// 截止日期
	private String fx_college;// 所属学院
	private String fx_comments;// 审核意见
	private int isnull;
	public int getIsnull() {
		return isnull;
	}

	public FourSix(String fx_colleges, String fx_department, String fx_major,
			String fx_grade, String fx_level, int fx_total, int fx_attend,
			int fx_attendcount, int fx_pass,double fx_passpercent, int fx_good,
			double fx_goodpercent, int fx_serialnumber, String fx_college,
			String fx_comments, int isnull) {
		super();
		this.fx_colleges = fx_colleges;
		this.fx_department = fx_department;
		this.fx_major = fx_major;
		this.fx_grade = fx_grade;
		this.fx_level = fx_level;
		this.fx_total = fx_total;
		this.fx_attend = fx_attend;
		this.fx_attendcount = fx_attendcount;
		this.fx_pass = fx_pass;
		this.fx_passpercent = fx_passpercent;
		this.fx_good = fx_good;
		this.fx_goodpercent = fx_goodpercent;
		this.fx_serialnumber = fx_serialnumber;
		this.fx_college = fx_college;
		this.fx_comments = fx_comments;
		this.isnull = isnull;
	}

	public void setIsnull(int isnull) {
		this.isnull = isnull;
	}

	public FourSix() {
		super();
	}

	public FourSix(String fx_colleges, String fx_department, String fx_major,
			String fx_grade, String fx_level, int fx_total, int fx_attend,
			int fx_attendcount, int fx_pass, double fx_passpercent, int fx_good,
			double fx_goodpercent, String fx_college) {
		super();
		this.fx_colleges = fx_colleges;
		this.fx_department = fx_department;
		this.fx_major = fx_major;
		this.fx_grade = fx_grade;
		this.fx_level = fx_level;
		this.fx_total = fx_total;
		this.fx_attend = fx_attend;
		this.fx_attendcount = fx_attendcount;
		this.fx_pass = fx_pass;
		this.fx_passpercent = fx_passpercent;
		this.fx_good = fx_good;
		this.fx_goodpercent = fx_goodpercent;
		this.fx_college = fx_college;
	}

	public FourSix(int fx_id, String fx_colleges, String fx_department,
			String fx_major, String fx_grade, String fx_level, int fx_total,
			int fx_attend, int fx_attendcount, int fx_pass, double fx_passpercent,
			int fx_good, double fx_goodpercent, int fx_serialnumber,
			Date fx_deadline, String fx_college, String fx_comments) {
		super();
		this.fx_id = fx_id;
		this.fx_colleges = fx_colleges;
		this.fx_department = fx_department;
		this.fx_major = fx_major;
		this.fx_grade = fx_grade;
		this.fx_level = fx_level;
		this.fx_total = fx_total;
		this.fx_attend = fx_attend;
		this.fx_attendcount = fx_attendcount;
		this.fx_pass = fx_pass;
		this.fx_passpercent = fx_passpercent;
		this.fx_good = fx_good;
		this.fx_goodpercent = fx_goodpercent;
		this.fx_serialnumber = fx_serialnumber;
		this.fx_deadline = fx_deadline;
		this.fx_college = fx_college;
		this.fx_comments = fx_comments;
	}

	public FourSix(int fx_id, String fx_colleges, String fx_department,
			String fx_major, String fx_grade, String fx_level, int fx_total,
			int fx_attend, int fx_attendcount, int fx_pass, double fx_passpercent,
			int fx_good, double fx_goodpercent, int fx_serialnumber,
			String fx_comments) {
		super();
		this.fx_id = fx_id;
		this.fx_colleges = fx_colleges;
		this.fx_department = fx_department;
		this.fx_major = fx_major;
		this.fx_grade = fx_grade;
		this.fx_level = fx_level;
		this.fx_total = fx_total;
		this.fx_attend = fx_attend;
		this.fx_attendcount = fx_attendcount;
		this.fx_pass = fx_pass;
		this.fx_passpercent = fx_passpercent;
		this.fx_good = fx_good;
		this.fx_goodpercent = fx_goodpercent;
		this.fx_serialnumber = fx_serialnumber;
		this.fx_comments = fx_comments;
	}

	public FourSix(String fx_colleges, String fx_department, String fx_major,
			String fx_grade, String fx_level, int fx_total, int fx_attend,
			int fx_attendcount, int fx_pass, double fx_passpercent, int fx_good,
			double fx_goodpercent, int fx_serialnumber, String fx_college,
			String fx_comments) {
		super();
		this.fx_colleges = fx_colleges;
		this.fx_department = fx_department;
		this.fx_major = fx_major;
		this.fx_grade = fx_grade;
		this.fx_level = fx_level;
		this.fx_total = fx_total;
		this.fx_attend = fx_attend;
		this.fx_attendcount = fx_attendcount;
		this.fx_pass = fx_pass;
		this.fx_passpercent = fx_passpercent;
		this.fx_good = fx_good;
		this.fx_goodpercent = fx_goodpercent;
		this.fx_serialnumber = fx_serialnumber;
		this.fx_college = fx_college;
		this.fx_comments = fx_comments;
	}

	
	public int getFx_id() {
		return fx_id;
	}

	public void setFx_id(int fx_id) {
		this.fx_id = fx_id;
	}

	public String getFx_colleges() {
		return fx_colleges;
	}

	public void setFx_colleges(String fx_colleges) {
		this.fx_colleges = fx_colleges;
	}

	public String getFx_department() {
		return fx_department;
	}

	public void setFx_department(String fx_department) {
		this.fx_department = fx_department;
	}

	public String getFx_major() {
		return fx_major;
	}

	public void setFx_major(String fx_major) {
		this.fx_major = fx_major;
	}

	public String getFx_grade() {
		return fx_grade;
	}

	public void setFx_grade(String fx_grade) {
		this.fx_grade = fx_grade;
	}

	public String getFx_level() {
		return fx_level;
	}

	public void setFx_level(String fx_level) {
		this.fx_level = fx_level;
	}

	public int getFx_total() {
		return fx_total;
	}

	public void setFx_total(int fx_total) {
		this.fx_total = fx_total;
	}

	public int getFx_attend() {
		return fx_attend;
	}

	public void setFx_attend(int fx_attend) {
		this.fx_attend = fx_attend;
	}

	public int getFx_attendcount() {
		return fx_attendcount;
	}

	public void setFx_attendcount(int fx_attendcount) {
		this.fx_attendcount = fx_attendcount;
	}

	public int getFx_pass() {
		return fx_pass;
	}

	public void setFx_pass(int fx_pass) {
		this.fx_pass = fx_pass;
	}

	public double getFx_passpercent() {
		return fx_passpercent;
	}

	public void setFx_passpercent(double fx_passpercent) {
		this.fx_passpercent = fx_passpercent;
	}

	public int getFx_good() {
		return fx_good;
	}

	public void setFx_good(int fx_good) {
		this.fx_good = fx_good;
	}

	public double getFx_goodpercent() {
		return fx_goodpercent;
	}

	public void setFx_goodpercent(double fx_goodpercent) {
		this.fx_goodpercent = fx_goodpercent;
	}

	public int getFx_serialnumber() {
		return fx_serialnumber;
	}

	public void setFx_serialnumber(int fx_serialnumber) {
		this.fx_serialnumber = fx_serialnumber;
	}

	public Date getFx_deadline() {
		return fx_deadline;
	}

	public void setFx_deadline(Date fx_deadline) {
		this.fx_deadline = fx_deadline;
	}

	public String getFx_college() {
		return fx_college;
	}

	public void setFx_college(String fx_college) {
		this.fx_college = fx_college;
	}

	public String getFx_comments() {
		return fx_comments;
	}

	public void setFx_comments(String fx_comments) {
		this.fx_comments = fx_comments;
	}

	
	
}
