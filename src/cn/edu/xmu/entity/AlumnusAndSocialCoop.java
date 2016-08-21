package cn.edu.xmu.entity;

import java.util.Date;

/**
 * 表1-7 校友会与社会合作
 * @author yue
 *
 */
public class AlumnusAndSocialCoop {
	
	private int as_id;//姓名
	private Integer as_alumnusamount;//校友会总数
	private Integer as_domesticalumnus;//境内校友会
	private Integer as_overseaalumnus;//境外校友会
	private Integer as_agencyamount; // 签订合作协议机构总数
	private Integer as_academic;//学术机构
	private Integer as_industry;//行业机构和企业
	private Integer as_government;//地方政府
	private int	as_serialnumber;//序列号
	private Date as_deadline;//截止日期
	private String as_college;//所属学院
	private String as_comments;//审核
	private int isnull;//记录是否存在空值

	
	public AlumnusAndSocialCoop(int as_id, Integer as_alumnusamount, Integer as_domesticalumnus,
			Integer as_overseaalumnus, Integer as_agencyamount, Integer as_academic, Integer as_industry,
			Integer as_government, int as_serialnumber, Date as_deadline, String as_college, String as_comments,
			int isnull) {
		super();
		this.as_id = as_id;
		this.as_alumnusamount = as_alumnusamount;
		this.as_domesticalumnus = as_domesticalumnus;
		this.as_overseaalumnus = as_overseaalumnus;
		this.as_agencyamount = as_agencyamount;
		this.as_academic = as_academic;
		this.as_industry = as_industry;
		this.as_government = as_government;
		this.as_serialnumber = as_serialnumber;
		this.as_deadline = as_deadline;
		this.as_college = as_college;
		this.as_comments = as_comments;
		this.isnull = isnull;
	}
	public AlumnusAndSocialCoop(Integer as_alumnusamount, Integer as_domesticalumnus, Integer as_overseaalumnus,
			Integer as_agencyamount, Integer as_academic, Integer as_industry, Integer as_government, String as_college,
			int isnull) {
		super();
		this.as_alumnusamount = as_alumnusamount;
		this.as_domesticalumnus = as_domesticalumnus;
		this.as_overseaalumnus = as_overseaalumnus;
		this.as_agencyamount = as_agencyamount;
		this.as_academic = as_academic;
		this.as_industry = as_industry;
		this.as_government = as_government;
		this.as_college = as_college;
		this.as_comments = "";
		this.isnull = isnull;
	}
	public AlumnusAndSocialCoop(Integer as_alumnusamount, Integer as_domesticalumnus, Integer as_overseaalumnus,
			Integer as_agencyamount, Integer as_academic, Integer as_industry, Integer as_government,
			int as_serialnumber, String as_college, String as_comments, int isnull) {
		super();
		this.as_alumnusamount = as_alumnusamount;
		this.as_domesticalumnus = as_domesticalumnus;
		this.as_overseaalumnus = as_overseaalumnus;
		this.as_agencyamount = as_agencyamount;
		this.as_academic = as_academic;
		this.as_industry = as_industry;
		this.as_government = as_government;
		this.as_serialnumber = as_serialnumber;
		this.as_college = as_college;
		this.as_comments = as_comments;
		this.isnull = isnull;
	}
	public int getAs_id() {
		return as_id;
	}
	public void setAs_id(int as_id) {
		this.as_id = as_id;
	}
	public Integer getAs_alumnusamount() {
		return as_alumnusamount;
	}
	public void setAs_alumnusamount(Integer as_alumnusamount) {
		this.as_alumnusamount = as_alumnusamount;
	}
	public Integer getAs_domesticalumnus() {
		return as_domesticalumnus;
	}
	public void setAs_domesticalumnus(Integer as_domesticalumnus) {
		this.as_domesticalumnus = as_domesticalumnus;
	}
	public Integer getAs_overseaalumnus() {
		return as_overseaalumnus;
	}
	public void setAs_overseaalumnus(Integer as_overseaalumnus) {
		this.as_overseaalumnus = as_overseaalumnus;
	}
	public Integer getAs_agencyamount() {
		return as_agencyamount;
	}
	public void setAs_agencyamount(Integer as_agencyamount) {
		this.as_agencyamount = as_agencyamount;
	}
	public Integer getAs_academic() {
		return as_academic;
	}
	public void setAs_academic(Integer as_academic) {
		this.as_academic = as_academic;
	}
	public Integer getAs_industry() {
		return as_industry;
	}
	public void setAs_industry(Integer as_industry) {
		this.as_industry = as_industry;
	}
	public Integer getAs_government() {
		return as_government;
	}
	public void setAs_government(Integer as_government) {
		this.as_government = as_government;
	}
	public int getAs_serialnumber() {
		return as_serialnumber;
	}
	public void setAs_serialnumber(int as_serialnumber) {
		this.as_serialnumber = as_serialnumber;
	}
	public Date getAs_deadline() {
		return as_deadline;
	}
	public void setAs_deadline(Date as_deadline) {
		this.as_deadline = as_deadline;
	}
	public String getAs_college() {
		return as_college;
	}
	public void setAs_college(String as_college) {
		this.as_college = as_college;
	}
	public String getAs_comments() {
		return as_comments;
	}
	public void setAs_comments(String as_comments) {
		this.as_comments = as_comments;
	}
	public int getIsnull() {
		return isnull;
	}
	public void setIsnull(int isnull) {
		this.isnull = isnull;
	}

	
	
	
}
