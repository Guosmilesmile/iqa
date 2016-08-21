package cn.edu.xmu.entity;

import java.sql.Date;

/**
 * 校内外专家开设文化、学术讲座情况（学年）实体类
 * @author zhantu
 * date 2015-07-09
 */
public class ExpertLecture {
	//ID
	private int el_id;
	//承办学院
	private String el_collegename;
	//题目
	private String el_title;
	//级别
	private String el_grade;
	//学期
	private String el_term;
	//讲座类型
	private String el_type;
	//报告人姓名
	private String el_name;
	//报告人职称
	private String el_prorank;
	//报告人所在单位
	private String el_unit;
	//地区
	private String el_area;
	//备注（诺奖、院士、文科资深教授、千人计划、长江学者、杰青等可在备注说明。）
	private String el_remark;
	//序列号
	private int el_serialnumber;
	//截止日期
	private Date el_deadline;
	//填报学院
	private String el_college;
	//审核意见
	private String el_comments;
	//记录是否存在空值
	private int isnull;
	public int getIsnull() {
		return isnull;
	}
	public void setIsnull(int isnull) {
		this.isnull = isnull;
	}
	public int getEl_id() {
		return el_id;
	}
	public void setEl_id(int el_id) {
		this.el_id = el_id;
	}
	public String getEl_collegename() {
		return el_collegename;
	}
	public void setEl_collegename(String el_collegename) {
		this.el_collegename = el_collegename;
	}
	public String getEl_title() {
		return el_title;
	}
	public void setEl_title(String el_title) {
		this.el_title = el_title;
	}
	public String getEl_grade() {
		return el_grade;
	}
	public void setEl_grade(String el_grade) {
		this.el_grade = el_grade;
	}
	public String getEl_term() {
		return el_term;
	}
	public void setEl_term(String el_term) {
		this.el_term = el_term;
	}
	public String getEl_type() {
		return el_type;
	}
	public void setEl_type(String el_type) {
		this.el_type = el_type;
	}
	public String getEl_name() {
		return el_name;
	}
	public void setEl_name(String el_name) {
		this.el_name = el_name;
	}
	public String getEl_prorank() {
		return el_prorank;
	}
	public void setEl_prorank(String el_prorank) {
		this.el_prorank = el_prorank;
	}
	public String getEl_unit() {
		return el_unit;
	}
	public void setEl_unit(String el_unit) {
		this.el_unit = el_unit;
	}
	public String getEl_area() {
		return el_area;
	}
	public void setEl_area(String el_area) {
		this.el_area = el_area;
	}
	public String getEl_remark() {
		return el_remark;
	}
	public void setEl_remark(String el_remark) {
		this.el_remark = el_remark;
	}
	public int getEl_serialnumber() {
		return el_serialnumber;
	}
	public void setEl_serialnumber(int el_serialnumber) {
		this.el_serialnumber = el_serialnumber;
	}
	public Date getEl_deadline() {
		return el_deadline;
	}
	public void setEl_deadline(Date el_deadline) {
		this.el_deadline = el_deadline;
	}
	public String getEl_college() {
		return el_college;
	}
	public void setEl_college(String el_college) {
		this.el_college = el_college;
	}
	public String getEl_comments() {
		return el_comments;
	}
	public void setEl_comments(String el_comments) {
		this.el_comments = el_comments;
	}
	public ExpertLecture() {
		super();
	}
	public ExpertLecture(int el_id, String el_collegename, String el_title,
			String el_grade, String el_term, String el_type, String el_name,
			String el_prorank, String el_unit, String el_area,
			String el_remark, int el_serialnumber, Date el_deadline,
			String el_college, String el_comments, int isnull) {
		super();
		this.el_id = el_id;
		this.el_collegename = el_collegename;
		this.el_title = el_title;
		this.el_grade = el_grade;
		this.el_term = el_term;
		this.el_type = el_type;
		this.el_name = el_name;
		this.el_prorank = el_prorank;
		this.el_unit = el_unit;
		this.el_area = el_area;
		this.el_remark = el_remark;
		this.el_serialnumber = el_serialnumber;
		this.el_deadline = el_deadline;
		this.el_college = el_college;
		this.el_comments = el_comments;
		this.isnull = isnull;
	}
	public ExpertLecture(String el_collegename, String el_title,
			String el_grade, String el_term, String el_type, String el_name,
			String el_prorank, String el_unit, String el_area,
			String el_remark, int el_serialnumber, String el_college,
			String el_comments, int isnull) {
		super();
		this.el_collegename = el_collegename;
		this.el_title = el_title;
		this.el_grade = el_grade;
		this.el_term = el_term;
		this.el_type = el_type;
		this.el_name = el_name;
		this.el_prorank = el_prorank;
		this.el_unit = el_unit;
		this.el_area = el_area;
		this.el_remark = el_remark;
		this.el_serialnumber = el_serialnumber;
		this.el_college = el_college;
		this.el_comments = el_comments;
		this.isnull = isnull;
	}
	public ExpertLecture(int el_id, String el_collegename, String el_title,
			String el_grade, String el_term, String el_type, String el_name,
			String el_prorank, String el_unit, String el_area,
			String el_remark, int el_serialnumber, String el_comments, int isnull) {
		super();
		this.el_id = el_id;
		this.el_collegename = el_collegename;
		this.el_title = el_title;
		this.el_grade = el_grade;
		this.el_term = el_term;
		this.el_type = el_type;
		this.el_name = el_name;
		this.el_prorank = el_prorank;
		this.el_unit = el_unit;
		this.el_area = el_area;
		this.el_remark = el_remark;
		this.el_serialnumber = el_serialnumber;
		this.el_comments = el_comments;
		this.isnull = isnull;
	}
	public ExpertLecture(int el_id, String el_collegename, String el_title,
			String el_grade, String el_term, String el_type, String el_name,
			String el_prorank, String el_unit, String el_area,
			String el_remark, String el_comments, int isnull) {
		super();
		this.el_id = el_id;
		this.el_collegename = el_collegename;
		this.el_title = el_title;
		this.el_grade = el_grade;
		this.el_term = el_term;
		this.el_type = el_type;
		this.el_name = el_name;
		this.el_prorank = el_prorank;
		this.el_unit = el_unit;
		this.el_area = el_area;
		this.el_remark = el_remark;
		this.el_comments = el_comments;
		this.isnull = isnull;
	}
	public ExpertLecture(String el_collegename, String el_title,
			String el_grade, String el_term, String el_type, String el_name,
			String el_prorank, String el_unit, String el_area,
			String el_remark, String el_college, int isnull) {
		super();
		this.el_collegename = el_collegename;
		this.el_title = el_title;
		this.el_grade = el_grade;
		this.el_term = el_term;
		this.el_type = el_type;
		this.el_name = el_name;
		this.el_prorank = el_prorank;
		this.el_unit = el_unit;
		this.el_area = el_area;
		this.el_remark = el_remark;
		this.el_college = el_college;
		this.isnull = isnull;
	}

	
}
