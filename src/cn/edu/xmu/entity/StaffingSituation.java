package cn.edu.xmu.entity;

import java.util.Date;

/**
 * 
 * @author Luo
 * 6-1-8-2大学生创业教育与就业教育指导机构的专职人员配备情况（时点） 实体类
 * 全参构造函数
 * date 2015-07-09
 *
 */
public class StaffingSituation {
	private int ss_id;
	//条件1
	private String ss_condition1;
	//条件2
	private String ss_condition2;
	//大学生创业教育与就业教育指导机构专任教师人数
	private Integer ss_teachercount;
	//校级和院系级专任人员人数（含就业专职辅导员）
	private Integer ss_fulltimestaffcount;
	//学校教职工人数
	private Integer ss_facultycount;
	//序列号
	private int ss_serialnumber;
	//截止日期
	private Date ss_deadline;
	//所属学院
	private String ss_college;
	//审核意见
	private String ss_comments;
	
	private int isnull;

	public StaffingSituation(int ss_id, String ss_condition1,
			String ss_condition2, Integer ss_teachercount,
			Integer ss_fulltimestaffcount, Integer ss_facultycount,
			int ss_serialnumber, Date ss_deadline, String ss_college,
			String ss_comments, int isnull) {
		super();
		this.ss_id = ss_id;
		this.ss_condition1 = ss_condition1;
		this.ss_condition2 = ss_condition2;
		this.ss_teachercount = ss_teachercount;
		this.ss_fulltimestaffcount = ss_fulltimestaffcount;
		this.ss_facultycount = ss_facultycount;
		this.ss_serialnumber = ss_serialnumber;
		this.ss_deadline = ss_deadline;
		this.ss_college = ss_college;
		this.ss_comments = ss_comments;
		this.isnull = isnull;
	}

	public StaffingSituation(String ss_condition1, String ss_condition2,
			Integer ss_teachercount, Integer ss_fulltimestaffcount,
			Integer ss_facultycount, int ss_serialnumber, String ss_college,
			String ss_comments, int isnull) {
		super();
		this.ss_condition1 = ss_condition1;
		this.ss_condition2 = ss_condition2;
		this.ss_teachercount = ss_teachercount;
		this.ss_fulltimestaffcount = ss_fulltimestaffcount;
		this.ss_facultycount = ss_facultycount;
		this.ss_serialnumber = ss_serialnumber;
		this.ss_college = ss_college;
		this.ss_comments = ss_comments;
		this.isnull = isnull;
	}

	public StaffingSituation(int ss_id, String ss_condition1,
			String ss_condition2, Integer ss_teachercount,
			Integer ss_fulltimestaffcount, Integer ss_facultycount,
			int ss_serialnumber, String ss_comments, int isnull,String ss_college) {
		super();
		this.ss_id = ss_id;
		this.ss_condition1 = ss_condition1;
		this.ss_condition2 = ss_condition2;
		this.ss_teachercount = ss_teachercount;
		this.ss_fulltimestaffcount = ss_fulltimestaffcount;
		this.ss_facultycount = ss_facultycount;
		this.ss_serialnumber = ss_serialnumber;
		this.ss_comments = ss_comments;
		this.isnull = isnull;
		this.ss_college = ss_college;
	}

	public StaffingSituation(String ss_condition1, String ss_condition2,
			Integer ss_teachercount, Integer ss_fulltimestaffcount,
			Integer ss_facultycount, String ss_college, int isnull) {
		super();
		this.ss_condition1 = ss_condition1;
		this.ss_condition2 = ss_condition2;
		this.ss_teachercount = ss_teachercount;
		this.ss_fulltimestaffcount = ss_fulltimestaffcount;
		this.ss_facultycount = ss_facultycount;
		this.ss_college = ss_college;
		this.isnull = isnull;
	}

	public int getSs_id() {
		return ss_id;
	}

	public void setSs_id(int ss_id) {
		this.ss_id = ss_id;
	}

	public String getSs_condition1() {
		return ss_condition1;
	}

	public void setSs_condition1(String ss_condition1) {
		this.ss_condition1 = ss_condition1;
	}

	public String getSs_condition2() {
		return ss_condition2;
	}

	public void setSs_condition2(String ss_condition2) {
		this.ss_condition2 = ss_condition2;
	}

	public Integer getSs_teachercount() {
		return ss_teachercount;
	}

	public void setSs_teachercount(Integer ss_teachercount) {
		this.ss_teachercount = ss_teachercount;
	}

	public Integer getSs_fulltimestaffcount() {
		return ss_fulltimestaffcount;
	}

	public void setSs_fulltimestaffcount(Integer ss_fulltimestaffcount) {
		this.ss_fulltimestaffcount = ss_fulltimestaffcount;
	}

	public Integer getSs_facultycount() {
		return ss_facultycount;
	}

	public void setSs_facultycount(Integer ss_facultycount) {
		this.ss_facultycount = ss_facultycount;
	}

	public int getSs_serialnumber() {
		return ss_serialnumber;
	}

	public void setSs_serialnumber(int ss_serialnumber) {
		this.ss_serialnumber = ss_serialnumber;
	}

	public Date getSs_deadline() {
		return ss_deadline;
	}

	public void setSs_deadline(Date ss_deadline) {
		this.ss_deadline = ss_deadline;
	}

	public String getSs_college() {
		return ss_college;
	}

	public void setSs_college(String ss_college) {
		this.ss_college = ss_college;
	}

	public String getSs_comments() {
		return ss_comments;
	}

	public void setSs_comments(String ss_comments) {
		this.ss_comments = ss_comments;
	}

	public int getIsnull() {
		return isnull;
	}

	public void setIsnull(int isnull) {
		this.isnull = isnull;
	}


}
