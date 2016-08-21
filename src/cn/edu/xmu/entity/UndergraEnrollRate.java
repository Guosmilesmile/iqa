package cn.edu.xmu.entity;

import java.sql.Date;

/**
 * 附表6-1-5-3本科生招生志愿满足率（时点）
 * @author yue
 *
 */

public class UndergraEnrollRate {
	private int uer_id;//id
	private String uer_institute;//学院
	private String uer_admission;//录取专业
	private Double uer_firstmajorrate;//第一专业志愿满足率
	private Double uer_unfirstmajorrate;//非第一专业志愿满足率
	private Double uer_adjustrate;//调剂率
	private int uer_serialnumber;//序列号
	private Date uer_deadline;//截止日期
	private String uer_college;//所属学院
	private String uer_comments;//审核意见
	private int isnull;//记录是否存在空值
	
	
	public UndergraEnrollRate(int uer_id, String uer_institute, String uer_admission, Double uer_firstmajorrate,
			Double uer_unfirstmajorrate, Double uer_adjustrate, int uer_serialnumber, Date uer_deadline,
			String uer_college, String uer_comments,int isnull) {
		super();
		this.uer_id = uer_id;
		this.uer_institute = uer_institute;
		this.uer_admission = uer_admission;
		this.uer_firstmajorrate = uer_firstmajorrate;
		this.uer_unfirstmajorrate = uer_unfirstmajorrate;
		this.uer_adjustrate = uer_adjustrate;
		this.uer_serialnumber = uer_serialnumber;
		this.uer_deadline = uer_deadline;
		this.uer_college = uer_college;
		this.uer_comments = uer_comments;
		this.isnull = isnull;
	}
	public UndergraEnrollRate( String uer_institute, String uer_admission, Double uer_firstmajorrate,
			Double uer_unfirstmajorrate, Double uer_adjustrate, int uer_serialnumber, 
			String uer_college, String uer_comments,int isnull) {
		super();
		this.uer_institute = uer_institute;
		this.uer_admission = uer_admission;
		this.uer_firstmajorrate = uer_firstmajorrate;
		this.uer_unfirstmajorrate = uer_unfirstmajorrate;
		this.uer_adjustrate = uer_adjustrate;
		this.uer_serialnumber = uer_serialnumber;
		this.uer_college = uer_college;
		this.uer_comments = uer_comments;
		this.isnull = isnull;
	}
	public UndergraEnrollRate( String uer_institute, String uer_admission, Double uer_firstmajorrate,
			Double uer_unfirstmajorrate, Double uer_adjustrate, String uer_college,int isnull) {
		super();
		this.uer_institute = uer_institute;
		this.uer_admission = uer_admission;
		this.uer_firstmajorrate = uer_firstmajorrate;
		this.uer_unfirstmajorrate = uer_unfirstmajorrate;
		this.uer_adjustrate = uer_adjustrate;
		this.uer_college = uer_college;
		this.uer_comments = "";
		this.isnull = isnull;
	}
	public int getUer_id() {
		return uer_id;
	}
	public void setUer_id(int uer_id) {
		this.uer_id = uer_id;
	}
	public String getUer_institute() {
		return uer_institute;
	}
	public void setUer_institute(String uer_institute) {
		this.uer_institute = uer_institute;
	}
	public String getUer_admission() {
		return uer_admission;
	}
	public void setUer_admission(String uer_admission) {
		this.uer_admission = uer_admission;
	}
	public Double getUer_firstmajorrate() {
		return uer_firstmajorrate;
	}
	public void setUer_firstmajorrate(Double uer_firstmajorrate) {
		this.uer_firstmajorrate = uer_firstmajorrate;
	}
	public Double getUer_unfirstmajorrate() {
		return uer_unfirstmajorrate;
	}
	public void setUer_unfirstmajorrate(Double uer_unfirstmajorrate) {
		this.uer_unfirstmajorrate = uer_unfirstmajorrate;
	}
	public Double getUer_adjustrate() {
		return uer_adjustrate;
	}
	public void setUer_adjustrate(Double uer_adjustrate) {
		this.uer_adjustrate = uer_adjustrate;
	}
	public int getUer_serialnumber() {
		return uer_serialnumber;
	}
	public void setUer_serialnumber(int uer_serialnumber) {
		this.uer_serialnumber = uer_serialnumber;
	}
	public Date getUer_deadline() {
		return uer_deadline;
	}
	public void setUer_deadline(Date uer_deadline) {
		this.uer_deadline = uer_deadline;
	}
	public String getUer_college() {
		return uer_college;
	}
	public void setUer_college(String uer_college) {
		this.uer_college = uer_college;
	}
	public String getUer_comments() {
		return uer_comments;
	}
	public void setUer_comments(String uer_comments) {
		this.uer_comments = uer_comments;
	}
	public int getIsnull() {
		return isnull;
	}
	public void setIsnull(int isnull) {
		this.isnull = isnull;
	}

	
}
