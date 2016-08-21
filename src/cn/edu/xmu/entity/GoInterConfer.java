package cn.edu.xmu.entity;

import java.sql.Date;

/**
 * 
 * @author Lee
 * 数据附表 6-2-1-9 学生参加国际会议情况（学年）
 * date 2015/7/13
 */

public class GoInterConfer {
	//序号
	private int gic_id;

	//学院
	private String gic_college1;
	
	//专业
	private String gic_major;
	
	//年级
	private String gic_grade;
	
	//学号
	private String gic_stunum;
	
	//姓名
	private String gic_name;
	
	//国际会议名称
	private String gic_intername;
	
	//会议论文或大会发言题目
	private String gic_paperortitle;

	//序列号
	private int gic_serialnumber;
	
	//截止日期
	private Date gic_deadline;
	
	//审核意见
	private String gic_comments;
	
	//操作学院
	private String gic_college;
	
	//记录是否存在空值
	private int isnull;

	public int getGic_id() {
		return gic_id;
	}

	public void setGic_id(int gic_id) {
		this.gic_id = gic_id;
	}

	public String getGic_college1() {
		return gic_college1;
	}

	public void setGic_college1(String gic_college1) {
		this.gic_college1 = gic_college1;
	}

	public String getGic_major() {
		return gic_major;
	}

	public void setGic_major(String gic_major) {
		this.gic_major = gic_major;
	}

	public String getGic_grade() {
		return gic_grade;
	}

	public void setGic_grade(String gic_grade) {
		this.gic_grade = gic_grade;
	}

	public String getGic_stunum() {
		return gic_stunum;
	}

	public void setGic_stunum(String gic_stunum) {
		this.gic_stunum = gic_stunum;
	}

	public String getGic_name() {
		return gic_name;
	}

	public void setGic_name(String gic_name) {
		this.gic_name = gic_name;
	}

	public String getGic_intername() {
		return gic_intername;
	}

	public void setGic_intername(String gic_intername) {
		this.gic_intername = gic_intername;
	}

	public String getGic_paperortitle() {
		return gic_paperortitle;
	}

	public void setGic_paperortitle(String gic_paperortitle) {
		this.gic_paperortitle = gic_paperortitle;
	}

	public int getGic_serialnumber() {
		return gic_serialnumber;
	}

	public void setGic_serialnumber(int gic_serialnumber) {
		this.gic_serialnumber = gic_serialnumber;
	}

	public Date getGic_deadline() {
		return gic_deadline;
	}

	public void setGic_deadline(Date gic_deadline) {
		this.gic_deadline = gic_deadline;
	}

	public String getGic_comments() {
		return gic_comments;
	}

	public void setGic_comments(String gic_comments) {
		this.gic_comments = gic_comments;
	}

	public String getGic_college() {
		return gic_college;
	}

	public void setGic_college(String gic_college) {
		this.gic_college = gic_college;
	}

	public int getIsnull() {
		return isnull;
	}

	public void setIsnull(int isnull) {
		this.isnull = isnull;
	}

	public GoInterConfer() {
		super();
	}

	public GoInterConfer(int gic_id, String gic_college1, String gic_major,
			String gic_grade, String gic_stunum, String gic_name,
			String gic_intername, String gic_paperortitle,
			int gic_serialnumber, String gic_comments, String gic_college,
			int isnull) {
		super();
		this.gic_id = gic_id;
		this.gic_college1 = gic_college1;
		this.gic_major = gic_major;
		this.gic_grade = gic_grade;
		this.gic_stunum = gic_stunum;
		this.gic_name = gic_name;
		this.gic_intername = gic_intername;
		this.gic_paperortitle = gic_paperortitle;
		this.gic_serialnumber = gic_serialnumber;
		this.gic_comments = gic_comments;
		this.gic_college = gic_college;
		this.isnull = isnull;
	}

	public GoInterConfer(int gic_id, String gic_college1, String gic_major,
			String gic_grade, String gic_stunum, String gic_name,
			String gic_intername, String gic_paperortitle,
			int gic_serialnumber, Date gic_deadline, String gic_comments,
			String gic_college, int isnull) {
		super();
		this.gic_id = gic_id;
		this.gic_college1 = gic_college1;
		this.gic_major = gic_major;
		this.gic_grade = gic_grade;
		this.gic_stunum = gic_stunum;
		this.gic_name = gic_name;
		this.gic_intername = gic_intername;
		this.gic_paperortitle = gic_paperortitle;
		this.gic_serialnumber = gic_serialnumber;
		this.gic_deadline = gic_deadline;
		this.gic_comments = gic_comments;
		this.gic_college = gic_college;
		this.isnull = isnull;
	}

	public GoInterConfer(String gic_college1, String gic_major,
			String gic_grade, String gic_stunum, String gic_name,
			String gic_intername, String gic_paperortitle,
			int gic_serialnumber, String gic_college, int isnull) {
		super();
		this.gic_college1 = gic_college1;
		this.gic_major = gic_major;
		this.gic_grade = gic_grade;
		this.gic_stunum = gic_stunum;
		this.gic_name = gic_name;
		this.gic_intername = gic_intername;
		this.gic_paperortitle = gic_paperortitle;
		this.gic_serialnumber = gic_serialnumber;
		this.gic_college = gic_college;
		this.isnull = isnull;
	}

	/**
	 * 导出使用
	 * @param gic_college1
	 * @param gic_major
	 * @param gic_grade
	 * @param gic_stunum
	 * @param gic_name
	 * @param gic_intername
	 * @param gic_paperortitle
	 * @param gic_college
	 * @param isnull
	 */
	public GoInterConfer(String gic_college1, String gic_major,
			String gic_grade, String gic_stunum, String gic_name,
			String gic_intername, String gic_paperortitle, String gic_college,
			int isnull) {
		super();
		this.gic_college1 = gic_college1;
		this.gic_major = gic_major;
		this.gic_grade = gic_grade;
		this.gic_stunum = gic_stunum;
		this.gic_name = gic_name;
		this.gic_intername = gic_intername;
		this.gic_paperortitle = gic_paperortitle;
		this.gic_college = gic_college;
		this.isnull = isnull;
	}
	
	
	
}
