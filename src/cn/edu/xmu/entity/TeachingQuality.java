package cn.edu.xmu.entity;

import java.util.Date;

/**
 * 
 * @author Luo
 * 表5-1-3课堂教学质量评估统计表（学年）
 * 全参构造函数
 * date 2015-07-07
 *
 */
public class TeachingQuality {
	private int tq_id;
	//项目
	private String tq_project;
	//覆盖比例
	private Float tq_coverpercent;
	//优秀
	private Float tq_excellent;
	//良好
	private Float tq_good;
	//中等
	private Float tq_medium;
	//差
	private Float tq_poor;
	//序列号
	private int tq_serialnumber;
	//截止日期
	private Date tq_deadline;
	//所属学院
	private String tq_college;
	//审核意见
	private String tq_comments;
	
	private int isnull;

	public TeachingQuality(int tq_id, String tq_project, Float tq_coverpercent,
			Float tq_excellent, Float tq_good, Float tq_medium, Float tq_poor,
			int tq_serialnumber, Date tq_deadline, String tq_college,
			String tq_comments, int isnull) {
		super();
		this.tq_id = tq_id;
		this.tq_project = tq_project;
		this.tq_coverpercent = tq_coverpercent;
		this.tq_excellent = tq_excellent;
		this.tq_good = tq_good;
		this.tq_medium = tq_medium;
		this.tq_poor = tq_poor;
		this.tq_serialnumber = tq_serialnumber;
		this.tq_deadline = tq_deadline;
		this.tq_college = tq_college;
		this.tq_comments = tq_comments;
		this.isnull = isnull;
	}

	public TeachingQuality(String tq_project, Float tq_coverpercent,
			Float tq_excellent, Float tq_good, Float tq_medium, Float tq_poor,
			int tq_serialnumber, String tq_college, String tq_comments,
			int isnull) {
		super();
		this.tq_project = tq_project;
		this.tq_coverpercent = tq_coverpercent;
		this.tq_excellent = tq_excellent;
		this.tq_good = tq_good;
		this.tq_medium = tq_medium;
		this.tq_poor = tq_poor;
		this.tq_serialnumber = tq_serialnumber;
		this.tq_college = tq_college;
		this.tq_comments = tq_comments;
		this.isnull = isnull;
	}

	public TeachingQuality(int tq_id, String tq_project, Float tq_coverpercent,
			Float tq_excellent, Float tq_good, Float tq_medium, Float tq_poor,
			int tq_serialnumber, String tq_comments, int isnull,String tq_college) {
		super();
		this.tq_id = tq_id;
		this.tq_project = tq_project;
		this.tq_coverpercent = tq_coverpercent;
		this.tq_excellent = tq_excellent;
		this.tq_good = tq_good;
		this.tq_medium = tq_medium;
		this.tq_poor = tq_poor;
		this.tq_serialnumber = tq_serialnumber;
		this.tq_comments = tq_comments;
		this.isnull = isnull;
		this.tq_college = tq_college;
	}

	public TeachingQuality(String tq_project, Float tq_coverpercent,
			Float tq_excellent, Float tq_good, Float tq_medium, Float tq_poor,
			String tq_college, int isnull) {
		super();
		this.tq_project = tq_project;
		this.tq_coverpercent = tq_coverpercent;
		this.tq_excellent = tq_excellent;
		this.tq_good = tq_good;
		this.tq_medium = tq_medium;
		this.tq_poor = tq_poor;
		this.tq_college = tq_college;
		this.isnull = isnull;
	}

	public int getTq_id() {
		return tq_id;
	}

	public void setTq_id(int tq_id) {
		this.tq_id = tq_id;
	}

	public String getTq_project() {
		return tq_project;
	}

	public void setTq_project(String tq_project) {
		this.tq_project = tq_project;
	}

	public Float getTq_coverpercent() {
		return tq_coverpercent;
	}

	public void setTq_coverpercent(Float tq_coverpercent) {
		this.tq_coverpercent = tq_coverpercent;
	}

	public Float getTq_excellent() {
		return tq_excellent;
	}

	public void setTq_excellent(Float tq_excellent) {
		this.tq_excellent = tq_excellent;
	}

	public Float getTq_good() {
		return tq_good;
	}

	public void setTq_good(Float tq_good) {
		this.tq_good = tq_good;
	}

	public Float getTq_medium() {
		return tq_medium;
	}

	public void setTq_medium(Float tq_medium) {
		this.tq_medium = tq_medium;
	}

	public Float getTq_poor() {
		return tq_poor;
	}

	public void setTq_poor(Float tq_poor) {
		this.tq_poor = tq_poor;
	}

	public int getTq_serialnumber() {
		return tq_serialnumber;
	}

	public void setTq_serialnumber(int tq_serialnumber) {
		this.tq_serialnumber = tq_serialnumber;
	}

	public Date getTq_deadline() {
		return tq_deadline;
	}

	public void setTq_deadline(Date tq_deadline) {
		this.tq_deadline = tq_deadline;
	}

	public String getTq_college() {
		return tq_college;
	}

	public void setTq_college(String tq_college) {
		this.tq_college = tq_college;
	}

	public String getTq_comments() {
		return tq_comments;
	}

	public void setTq_comments(String tq_comments) {
		this.tq_comments = tq_comments;
	}

	public int getIsnull() {
		return isnull;
	}

	public void setIsnull(int isnull) {
		this.isnull = isnull;
	}
	


	
	
}
