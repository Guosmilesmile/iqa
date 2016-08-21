package cn.edu.xmu.entity;

import java.sql.Date;

/**
 * @author zhantu
 * 教材获奖情况（自然年） 实体类
 * date 2015-07-08
 */
public class TextbookAward {
	//ID
	private int ta_id;
	//所属学院名
	private String ta_collegename;
	//教材名称
	private String ta_name;
	//著者/编者姓名
	private String ta_author;
	//出版社
	private String ta_publisher;
	//获奖名称
	private String ta_prizename;
	//获奖级别
	private String ta_grade;
	//获奖类别
	private String ta_type;
	//序列号
	private int ta_serialnumber;
	//截止日期
	private Date ta_deadline;
	//填报学院
	private String ta_college;
	//审核意见
	private String ta_comments;
	//记录是否存在空值
	private int isnull;
	public int getIsnull() {
		return isnull;
	}
	public void setIsnull(int isnull) {
		this.isnull = isnull;
	}
	public int getTa_id() {
		return ta_id;
	}
	public void setTa_id(int ta_id) {
		this.ta_id = ta_id;
	}
	public String getTa_collegename() {
		return ta_collegename;
	}
	public void setTa_collegename(String ta_collegename) {
		this.ta_collegename = ta_collegename;
	}
	public String getTa_name() {
		return ta_name;
	}
	public void setTa_name(String ta_name) {
		this.ta_name = ta_name;
	}
	public String getTa_author() {
		return ta_author;
	}
	public void setTa_author(String ta_author) {
		this.ta_author = ta_author;
	}
	public String getTa_publisher() {
		return ta_publisher;
	}
	public void setTa_publisher(String ta_publisher) {
		this.ta_publisher = ta_publisher;
	}
	public String getTa_prizename() {
		return ta_prizename;
	}
	public void setTa_prizename(String ta_prizename) {
		this.ta_prizename = ta_prizename;
	}
	public String getTa_grade() {
		return ta_grade;
	}
	public void setTa_grade(String ta_grade) {
		this.ta_grade = ta_grade;
	}
	public String getTa_type() {
		return ta_type;
	}
	public void setTa_type(String ta_type) {
		this.ta_type = ta_type;
	}
	public int getTa_serialnumber() {
		return ta_serialnumber;
	}
	public void setTa_serialnumber(int ta_serialnumber) {
		this.ta_serialnumber = ta_serialnumber;
	}
	public Date getTa_deadline() {
		return ta_deadline;
	}
	public void setTa_deadline(Date ta_deadline) {
		this.ta_deadline = ta_deadline;
	}
	public String getTa_college() {
		return ta_college;
	}
	public void setTa_college(String ta_college) {
		this.ta_college = ta_college;
	}
	public String getTa_comments() {
		return ta_comments;
	}
	public void setTa_comments(String ta_comments) {
		this.ta_comments = ta_comments;
	}
	public TextbookAward() {
		super();
	}
	public TextbookAward(int ta_id, String ta_collegename, String ta_name,
			String ta_author, String ta_publisher, String ta_prizename,
			String ta_grade, String ta_type, int ta_serialnumber,
			Date ta_deadline, String ta_college, String ta_comments, int isnull) {
		super();
		this.ta_id = ta_id;
		this.ta_collegename = ta_collegename;
		this.ta_name = ta_name;
		this.ta_author = ta_author;
		this.ta_publisher = ta_publisher;
		this.ta_prizename = ta_prizename;
		this.ta_grade = ta_grade;
		this.ta_type = ta_type;
		this.ta_serialnumber = ta_serialnumber;
		this.ta_deadline = ta_deadline;
		this.ta_college = ta_college;
		this.ta_comments = ta_comments;
		this.isnull = isnull;
	}
	public TextbookAward(String ta_collegename, String ta_name,
			String ta_author, String ta_publisher, String ta_prizename,
			String ta_grade, String ta_type, int ta_serialnumber,
			String ta_college, String ta_comments, int isnull) {
		super();
		this.ta_collegename = ta_collegename;
		this.ta_name = ta_name;
		this.ta_author = ta_author;
		this.ta_publisher = ta_publisher;
		this.ta_prizename = ta_prizename;
		this.ta_grade = ta_grade;
		this.ta_type = ta_type;
		this.ta_serialnumber = ta_serialnumber;
		this.ta_college = ta_college;
		this.ta_comments = ta_comments;
		this.isnull = isnull;
	}
	public TextbookAward(int ta_id, String ta_collegename, String ta_name,
			String ta_author, String ta_publisher, String ta_prizename,
			String ta_grade, String ta_type, int ta_serialnumber,
			String ta_comments, int isnull) {
		super();
		this.ta_id = ta_id;
		this.ta_collegename = ta_collegename;
		this.ta_name = ta_name;
		this.ta_author = ta_author;
		this.ta_publisher = ta_publisher;
		this.ta_prizename = ta_prizename;
		this.ta_grade = ta_grade;
		this.ta_type = ta_type;
		this.ta_serialnumber = ta_serialnumber;
		this.ta_comments = ta_comments;
		this.isnull = isnull;
	}
	public TextbookAward(int ta_id, String ta_collegename, String ta_name,
			String ta_author, String ta_publisher, String ta_prizename,
			String ta_grade, String ta_type, String ta_comments, int isnull) {
		super();
		this.ta_id = ta_id;
		this.ta_collegename = ta_collegename;
		this.ta_name = ta_name;
		this.ta_author = ta_author;
		this.ta_publisher = ta_publisher;
		this.ta_prizename = ta_prizename;
		this.ta_grade = ta_grade;
		this.ta_type = ta_type;
		this.ta_comments = ta_comments;
		this.isnull = isnull;
	}
	public TextbookAward(String ta_collegename, String ta_name,
			String ta_author, String ta_publisher, String ta_prizename,
			String ta_grade, String ta_type, String ta_college, int isnull) {
		super();
		this.ta_collegename = ta_collegename;
		this.ta_name = ta_name;
		this.ta_author = ta_author;
		this.ta_publisher = ta_publisher;
		this.ta_prizename = ta_prizename;
		this.ta_grade = ta_grade;
		this.ta_type = ta_type;
		this.ta_college = ta_college;
		this.isnull = isnull;
	}
	
	
}
