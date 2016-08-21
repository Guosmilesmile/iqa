package cn.edu.xmu.entity;

import java.util.Date;

/**
 * 
 * @author Luo
 * 学校经费概况 实体类
 * 全参构造函数
 * date 2015-07-03
 *
 */
public class FundingProfile {
	private int fp_id;
	//学校教育经费总额
	private Float fp_educationfund;
	//教学经费总额
	private Float fp_teachfund;
	//教学改革与建设专项经费总额
	private Float fp_reformfund;
	//序列号
	private int fp_serialnumber;
	//截止日期
	private Date fp_deadline;
	//所属学院
	private String fp_college;
	//审核意见
	private String fp_comments;
	
	private int isnull;

	public FundingProfile(int fp_id, Float fp_educationfund,
			Float fp_teachfund, Float fp_reformfund, int fp_serialnumber,
			Date fp_deadline, String fp_college, String fp_comments, int isnull) {
		super();
		this.fp_id = fp_id;
		this.fp_educationfund = fp_educationfund;
		this.fp_teachfund = fp_teachfund;
		this.fp_reformfund = fp_reformfund;
		this.fp_serialnumber = fp_serialnumber;
		this.fp_deadline = fp_deadline;
		this.fp_college = fp_college;
		this.fp_comments = fp_comments;
		this.isnull = isnull;
	}

	public FundingProfile(Float fp_educationfund, Float fp_teachfund,
			Float fp_reformfund, int fp_serialnumber, String fp_college,
			String fp_comments, int isnull) {
		super();
		this.fp_educationfund = fp_educationfund;
		this.fp_teachfund = fp_teachfund;
		this.fp_reformfund = fp_reformfund;
		this.fp_serialnumber = fp_serialnumber;
		this.fp_college = fp_college;
		this.fp_comments = fp_comments;
		this.isnull = isnull;
	}

	public FundingProfile(int fp_id, Float fp_educationfund,
			Float fp_teachfund, Float fp_reformfund, int fp_serialnumber,
			String fp_comments, int isnull,String fp_college) {
		super();
		this.fp_id = fp_id;
		this.fp_educationfund = fp_educationfund;
		this.fp_teachfund = fp_teachfund;
		this.fp_reformfund = fp_reformfund;
		this.fp_serialnumber = fp_serialnumber;
		this.fp_comments = fp_comments;
		this.isnull = isnull;
		this.fp_college = fp_college;
	}

	public FundingProfile(Float fp_educationfund, Float fp_teachfund,
			Float fp_reformfund, String fp_college, int isnull) {
		super();
		this.fp_educationfund = fp_educationfund;
		this.fp_teachfund = fp_teachfund;
		this.fp_reformfund = fp_reformfund;
		this.fp_college = fp_college;
		this.isnull = isnull;
	}

	public int getFp_id() {
		return fp_id;
	}

	public void setFp_id(int fp_id) {
		this.fp_id = fp_id;
	}

	public Float getFp_educationfund() {
		return fp_educationfund;
	}

	public void setFp_educationfund(Float fp_educationfund) {
		this.fp_educationfund = fp_educationfund;
	}

	public Float getFp_teachfund() {
		return fp_teachfund;
	}

	public void setFp_teachfund(Float fp_teachfund) {
		this.fp_teachfund = fp_teachfund;
	}

	public Float getFp_reformfund() {
		return fp_reformfund;
	}

	public void setFp_reformfund(Float fp_reformfund) {
		this.fp_reformfund = fp_reformfund;
	}

	public int getFp_serialnumber() {
		return fp_serialnumber;
	}

	public void setFp_serialnumber(int fp_serialnumber) {
		this.fp_serialnumber = fp_serialnumber;
	}

	public Date getFp_deadline() {
		return fp_deadline;
	}

	public void setFp_deadline(Date fp_deadline) {
		this.fp_deadline = fp_deadline;
	}

	public String getFp_college() {
		return fp_college;
	}

	public void setFp_college(String fp_college) {
		this.fp_college = fp_college;
	}

	public String getFp_comments() {
		return fp_comments;
	}

	public void setFp_comments(String fp_comments) {
		this.fp_comments = fp_comments;
	}

	public int getIsnull() {
		return isnull;
	}

	public void setIsnull(int isnull) {
		this.isnull = isnull;
	}
	
	

}
