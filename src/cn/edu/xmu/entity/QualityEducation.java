package cn.edu.xmu.entity;

import java.util.Date;

/**
 * 
 * @author Luo
 * 素质教育基地、职业资质培训等情况 实体类
 * 全参构造函数
 * date 2015-07-03
 *
 */
public class QualityEducation {
	private int qe_id;
	//大学生素质拓展活动数
	private Integer qe_diathesisdeveloping;
	//大学生职业资质培训数
	private Integer qe_qualificationtraining;
	//开设的职业生涯规划及创业教育指导课程数
	private Integer qe_course;
	//素质教育基地数
	private Integer qe_basecount;
	//序列号
	private int qe_serialnumber;
	//截止日期
	private Date qe_deadline;
	//所属学院
	private String qe_college;
	//审核意见
	private String qe_comments;
	private int isnull;
	public QualityEducation(int qe_id, Integer qe_diathesisdeveloping,
			Integer qe_qualificationtraining, Integer qe_course,
			Integer qe_basecount, int qe_serialnumber, Date qe_deadline,
			String qe_college, String qe_comments, int isnull) {
		super();
		this.qe_id = qe_id;
		this.qe_diathesisdeveloping = qe_diathesisdeveloping;
		this.qe_qualificationtraining = qe_qualificationtraining;
		this.qe_course = qe_course;
		this.qe_basecount = qe_basecount;
		this.qe_serialnumber = qe_serialnumber;
		this.qe_deadline = qe_deadline;
		this.qe_college = qe_college;
		this.qe_comments = qe_comments;
		this.isnull = isnull;
	}
	public QualityEducation(Integer qe_diathesisdeveloping,
			Integer qe_qualificationtraining, Integer qe_course,
			Integer qe_basecount, int qe_serialnumber, String qe_college,
			String qe_comments, int isnull) {
		super();
		this.qe_diathesisdeveloping = qe_diathesisdeveloping;
		this.qe_qualificationtraining = qe_qualificationtraining;
		this.qe_course = qe_course;
		this.qe_basecount = qe_basecount;
		this.qe_serialnumber = qe_serialnumber;
		this.qe_college = qe_college;
		this.qe_comments = qe_comments;
		this.isnull = isnull;
	}
	public QualityEducation(int qe_id, Integer qe_diathesisdeveloping,
			Integer qe_qualificationtraining, Integer qe_course,
			Integer qe_basecount, int qe_serialnumber, String qe_comments,
			int isnull,String qe_college) {
		super();
		this.qe_id = qe_id;
		this.qe_diathesisdeveloping = qe_diathesisdeveloping;
		this.qe_qualificationtraining = qe_qualificationtraining;
		this.qe_course = qe_course;
		this.qe_basecount = qe_basecount;
		this.qe_serialnumber = qe_serialnumber;
		this.qe_comments = qe_comments;
		this.isnull = isnull;
		this.qe_college = qe_college;
	}
	public QualityEducation(Integer qe_diathesisdeveloping,
			Integer qe_qualificationtraining, Integer qe_course,
			Integer qe_basecount, String qe_college, int isnull) {
		super();
		this.qe_diathesisdeveloping = qe_diathesisdeveloping;
		this.qe_qualificationtraining = qe_qualificationtraining;
		this.qe_course = qe_course;
		this.qe_basecount = qe_basecount;
		this.qe_college = qe_college;
		this.isnull = isnull;
	}
	public int getQe_id() {
		return qe_id;
	}
	public void setQe_id(int qe_id) {
		this.qe_id = qe_id;
	}
	public Integer getQe_diathesisdeveloping() {
		return qe_diathesisdeveloping;
	}
	public void setQe_diathesisdeveloping(Integer qe_diathesisdeveloping) {
		this.qe_diathesisdeveloping = qe_diathesisdeveloping;
	}
	public Integer getQe_qualificationtraining() {
		return qe_qualificationtraining;
	}
	public void setQe_qualificationtraining(Integer qe_qualificationtraining) {
		this.qe_qualificationtraining = qe_qualificationtraining;
	}
	public Integer getQe_course() {
		return qe_course;
	}
	public void setQe_course(Integer qe_course) {
		this.qe_course = qe_course;
	}
	public Integer getQe_basecount() {
		return qe_basecount;
	}
	public void setQe_basecount(Integer qe_basecount) {
		this.qe_basecount = qe_basecount;
	}
	public int getQe_serialnumber() {
		return qe_serialnumber;
	}
	public void setQe_serialnumber(int qe_serialnumber) {
		this.qe_serialnumber = qe_serialnumber;
	}
	public Date getQe_deadline() {
		return qe_deadline;
	}
	public void setQe_deadline(Date qe_deadline) {
		this.qe_deadline = qe_deadline;
	}
	public String getQe_college() {
		return qe_college;
	}
	public void setQe_college(String qe_college) {
		this.qe_college = qe_college;
	}
	public String getQe_comments() {
		return qe_comments;
	}
	public void setQe_comments(String qe_comments) {
		this.qe_comments = qe_comments;
	}
	public int getIsnull() {
		return isnull;
	}
	public void setIsnull(int isnull) {
		this.isnull = isnull;
	}
	
}
