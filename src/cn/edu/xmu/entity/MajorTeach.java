package cn.edu.xmu.entity;

import java.util.Date;

/**
 * 
 * @author Luo
 * 专业教学实施情况 实体类
 * 全参构造函数
 * date 2015-07-05
 *
 */
public class MajorTeach {
	private int mt_id;
	//校内专业（大类）名称
	private String mt_majornameinschool;
	//校内专业（大类）代码
	private String mt_majorcodeinschool;
	//开课号
	private String mt_coursecode;
	//课程性质
	private String mt_coursenature;
	//学分
	private Float mt_credits;
	//年级
	private String mt_grade;
	//序列号
	private int mt_serialnumber;
	//截止日期
	private Date mt_deadline;
	//所属学院
	private String mt_college;
	//审核意见
	private String mt_comments;
	
	private int isnull;

	public MajorTeach(int mt_id, String mt_majornameinschool,
			String mt_majorcodeinschool, String mt_coursecode,
			String mt_coursenature, Float mt_credits, String mt_grade,
			int mt_serialnumber, Date mt_deadline, String mt_college,
			String mt_comments, int isnull) {
		super();
		this.mt_id = mt_id;
		this.mt_majornameinschool = mt_majornameinschool;
		this.mt_majorcodeinschool = mt_majorcodeinschool;
		this.mt_coursecode = mt_coursecode;
		this.mt_coursenature = mt_coursenature;
		this.mt_credits = mt_credits;
		this.mt_grade = mt_grade;
		this.mt_serialnumber = mt_serialnumber;
		this.mt_deadline = mt_deadline;
		this.mt_college = mt_college;
		this.mt_comments = mt_comments;
		this.isnull = isnull;
	}

	public MajorTeach(String mt_majornameinschool, String mt_majorcodeinschool,
			String mt_coursecode, String mt_coursenature, Float mt_credits,
			String mt_grade, int mt_serialnumber, String mt_college,
			String mt_comments, int isnull) {
		super();
		this.mt_majornameinschool = mt_majornameinschool;
		this.mt_majorcodeinschool = mt_majorcodeinschool;
		this.mt_coursecode = mt_coursecode;
		this.mt_coursenature = mt_coursenature;
		this.mt_credits = mt_credits;
		this.mt_grade = mt_grade;
		this.mt_serialnumber = mt_serialnumber;
		this.mt_college = mt_college;
		this.mt_comments = mt_comments;
		this.isnull = isnull;
	}

	public MajorTeach(int mt_id, String mt_majornameinschool,
			String mt_majorcodeinschool, String mt_coursecode,
			String mt_coursenature, Float mt_credits, String mt_grade,
			int mt_serialnumber, String mt_comments, int isnull,String mt_college) {
		super();
		this.mt_id = mt_id;
		this.mt_majornameinschool = mt_majornameinschool;
		this.mt_majorcodeinschool = mt_majorcodeinschool;
		this.mt_coursecode = mt_coursecode;
		this.mt_coursenature = mt_coursenature;
		this.mt_credits = mt_credits;
		this.mt_grade = mt_grade;
		this.mt_serialnumber = mt_serialnumber;
		this.mt_comments = mt_comments;
		this.isnull = isnull;
		this.mt_college = mt_college;
	}

	public MajorTeach(String mt_majornameinschool, String mt_majorcodeinschool,
			String mt_coursecode, String mt_coursenature, Float mt_credits,
			String mt_grade, String mt_college, int isnull) {
		super();
		this.mt_majornameinschool = mt_majornameinschool;
		this.mt_majorcodeinschool = mt_majorcodeinschool;
		this.mt_coursecode = mt_coursecode;
		this.mt_coursenature = mt_coursenature;
		this.mt_credits = mt_credits;
		this.mt_grade = mt_grade;
		this.mt_college = mt_college;
		this.isnull = isnull;
	}

	public int getMt_id() {
		return mt_id;
	}

	public void setMt_id(int mt_id) {
		this.mt_id = mt_id;
	}

	public String getMt_majornameinschool() {
		return mt_majornameinschool;
	}

	public void setMt_majornameinschool(String mt_majornameinschool) {
		this.mt_majornameinschool = mt_majornameinschool;
	}

	public String getMt_majorcodeinschool() {
		return mt_majorcodeinschool;
	}

	public void setMt_majorcodeinschool(String mt_majorcodeinschool) {
		this.mt_majorcodeinschool = mt_majorcodeinschool;
	}

	public String getMt_coursecode() {
		return mt_coursecode;
	}

	public void setMt_coursecode(String mt_coursecode) {
		this.mt_coursecode = mt_coursecode;
	}

	public String getMt_coursenature() {
		return mt_coursenature;
	}

	public void setMt_coursenature(String mt_coursenature) {
		this.mt_coursenature = mt_coursenature;
	}

	public Float getMt_credits() {
		return mt_credits;
	}

	public void setMt_credits(Float mt_credits) {
		this.mt_credits = mt_credits;
	}

	public String getMt_grade() {
		return mt_grade;
	}

	public void setMt_grade(String mt_grade) {
		this.mt_grade = mt_grade;
	}

	public int getMt_serialnumber() {
		return mt_serialnumber;
	}

	public void setMt_serialnumber(int mt_serialnumber) {
		this.mt_serialnumber = mt_serialnumber;
	}

	public Date getMt_deadline() {
		return mt_deadline;
	}

	public void setMt_deadline(Date mt_deadline) {
		this.mt_deadline = mt_deadline;
	}

	public String getMt_college() {
		return mt_college;
	}

	public void setMt_college(String mt_college) {
		this.mt_college = mt_college;
	}

	public String getMt_comments() {
		return mt_comments;
	}

	public void setMt_comments(String mt_comments) {
		this.mt_comments = mt_comments;
	}

	public int getIsnull() {
		return isnull;
	}

	public void setIsnull(int isnull) {
		this.isnull = isnull;
	}
	
}
