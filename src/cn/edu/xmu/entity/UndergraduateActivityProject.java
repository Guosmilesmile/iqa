package cn.edu.xmu.entity;

import java.sql.Date;

/**
 * 本科生课外科技、文化活动项目（学年）实体
 * @author zhantu
 * date 2015-07-09
 */
public class UndergraduateActivityProject {
	//ID
	private int uap_id;
	//项目名称
	private String uap_projectname;
	//组织单位
	private String uap_unit;
	//项目类型
	private String uap_projecttype;
	//参加人数
	private Integer uap_personnumber;
	//参加专业数
	private Integer uap_majornumber;
	//获奖项目数
	private Integer uap_projectnumber;
	//获奖等级（特等、一等、二等等）
	private String uap_awardgrade;
	//获奖类型（国际/国家/省/地区/校级）
	private String uap_awardtype;
	//获奖时间
	private Date uap_awarddate;
	//序列号
	private int uap_serialnumber;
	//截止日期
	private Date uap_deadline;
	//填报学院
	private String uap_college;
	//审核意见
	private String uap_comments;
	//记录是否存在空值
	private int isnull;
	public int getUap_id() {
		return uap_id;
	}
	public void setUap_id(int uap_id) {
		this.uap_id = uap_id;
	}
	public String getUap_projectname() {
		return uap_projectname;
	}
	public void setUap_projectname(String uap_projectname) {
		this.uap_projectname = uap_projectname;
	}
	public String getUap_unit() {
		return uap_unit;
	}
	public void setUap_unit(String uap_unit) {
		this.uap_unit = uap_unit;
	}
	public String getUap_projecttype() {
		return uap_projecttype;
	}
	public void setUap_projecttype(String uap_projecttype) {
		this.uap_projecttype = uap_projecttype;
	}
	public Integer getUap_personnumber() {
		return uap_personnumber;
	}
	public void setUap_personnumber(Integer uap_personnumber) {
		this.uap_personnumber = uap_personnumber;
	}
	public Integer getUap_majornumber() {
		return uap_majornumber;
	}
	public void setUap_majornumber(Integer uap_majornumber) {
		this.uap_majornumber = uap_majornumber;
	}
	public Integer getUap_projectnumber() {
		return uap_projectnumber;
	}
	public void setUap_projectnumber(Integer uap_projectnumber) {
		this.uap_projectnumber = uap_projectnumber;
	}
	public String getUap_awardgrade() {
		return uap_awardgrade;
	}
	public void setUap_awardgrade(String uap_awardgrade) {
		this.uap_awardgrade = uap_awardgrade;
	}
	public String getUap_awardtype() {
		return uap_awardtype;
	}
	public void setUap_awardtype(String uap_awardtype) {
		this.uap_awardtype = uap_awardtype;
	}
	public Date getUap_awarddate() {
		return uap_awarddate;
	}
	public void setUap_awarddate(Date uap_awarddate) {
		this.uap_awarddate = uap_awarddate;
	}
	public int getUap_serialnumber() {
		return uap_serialnumber;
	}
	public void setUap_serialnumber(int uap_serialnumber) {
		this.uap_serialnumber = uap_serialnumber;
	}
	public Date getUap_deadline() {
		return uap_deadline;
	}
	public void setUap_deadline(Date uap_deadline) {
		this.uap_deadline = uap_deadline;
	}
	public String getUap_college() {
		return uap_college;
	}
	public void setUap_college(String uap_college) {
		this.uap_college = uap_college;
	}
	public String getUap_comments() {
		return uap_comments;
	}
	public void setUap_comments(String uap_comments) {
		this.uap_comments = uap_comments;
	}
	public int getIsnull() {
		return isnull;
	}
	public void setIsnull(int isnull) {
		this.isnull = isnull;
	}
	public UndergraduateActivityProject() {
		super();
	}
	public UndergraduateActivityProject(int uap_id, String uap_projectname,
			String uap_unit, String uap_projecttype, Integer uap_personnumber,
			Integer uap_majornumber, Integer uap_projectnumber,
			String uap_awardgrade, String uap_awardtype, Date uap_awarddate,
			int uap_serialnumber, Date uap_deadline, String uap_college,
			String uap_comments, int isnull) {
		super();
		this.uap_id = uap_id;
		this.uap_projectname = uap_projectname;
		this.uap_unit = uap_unit;
		this.uap_projecttype = uap_projecttype;
		this.uap_personnumber = uap_personnumber;
		this.uap_majornumber = uap_majornumber;
		this.uap_projectnumber = uap_projectnumber;
		this.uap_awardgrade = uap_awardgrade;
		this.uap_awardtype = uap_awardtype;
		this.uap_awarddate = uap_awarddate;
		this.uap_serialnumber = uap_serialnumber;
		this.uap_deadline = uap_deadline;
		this.uap_college = uap_college;
		this.uap_comments = uap_comments;
		this.isnull = isnull;
	}
	public UndergraduateActivityProject(String uap_projectname,
			String uap_unit, String uap_projecttype, Integer uap_personnumber,
			Integer uap_majornumber, Integer uap_projectnumber,
			String uap_awardgrade, String uap_awardtype, Date uap_awarddate,
			int uap_serialnumber, String uap_college, String uap_comments,
			int isnull) {
		super();
		this.uap_projectname = uap_projectname;
		this.uap_unit = uap_unit;
		this.uap_projecttype = uap_projecttype;
		this.uap_personnumber = uap_personnumber;
		this.uap_majornumber = uap_majornumber;
		this.uap_projectnumber = uap_projectnumber;
		this.uap_awardgrade = uap_awardgrade;
		this.uap_awardtype = uap_awardtype;
		this.uap_awarddate = uap_awarddate;
		this.uap_serialnumber = uap_serialnumber;
		this.uap_college = uap_college;
		this.uap_comments = uap_comments;
		this.isnull = isnull;
	}
	public UndergraduateActivityProject(int uap_id, String uap_projectname,
			String uap_unit, String uap_projecttype, Integer uap_personnumber,
			Integer uap_majornumber, Integer uap_projectnumber,
			String uap_awardgrade, String uap_awardtype, Date uap_awarddate,
			int uap_serialnumber, String uap_comments, int isnull) {
		super();
		this.uap_id = uap_id;
		this.uap_projectname = uap_projectname;
		this.uap_unit = uap_unit;
		this.uap_projecttype = uap_projecttype;
		this.uap_personnumber = uap_personnumber;
		this.uap_majornumber = uap_majornumber;
		this.uap_projectnumber = uap_projectnumber;
		this.uap_awardgrade = uap_awardgrade;
		this.uap_awardtype = uap_awardtype;
		this.uap_awarddate = uap_awarddate;
		this.uap_serialnumber = uap_serialnumber;
		this.uap_comments = uap_comments;
		this.isnull = isnull;
	}
	public UndergraduateActivityProject(int uap_id, String uap_projectname,
			String uap_unit, String uap_projecttype, Integer uap_personnumber,
			Integer uap_majornumber, Integer uap_projectnumber,
			String uap_awardgrade, String uap_awardtype, Date uap_awarddate,
			String uap_comments, int isnull) {
		super();
		this.uap_id = uap_id;
		this.uap_projectname = uap_projectname;
		this.uap_unit = uap_unit;
		this.uap_projecttype = uap_projecttype;
		this.uap_personnumber = uap_personnumber;
		this.uap_majornumber = uap_majornumber;
		this.uap_projectnumber = uap_projectnumber;
		this.uap_awardgrade = uap_awardgrade;
		this.uap_awardtype = uap_awardtype;
		this.uap_awarddate = uap_awarddate;
		this.uap_comments = uap_comments;
		this.isnull = isnull;
	}
	public UndergraduateActivityProject(String uap_projectname,
			String uap_unit, String uap_projecttype, Integer uap_personnumber,
			Integer uap_majornumber, Integer uap_projectnumber,
			String uap_awardgrade, String uap_awardtype, Date uap_awarddate,
			String uap_college, int isnull) {
		super();
		this.uap_projectname = uap_projectname;
		this.uap_unit = uap_unit;
		this.uap_projecttype = uap_projecttype;
		this.uap_personnumber = uap_personnumber;
		this.uap_majornumber = uap_majornumber;
		this.uap_projectnumber = uap_projectnumber;
		this.uap_awardgrade = uap_awardgrade;
		this.uap_awardtype = uap_awardtype;
		this.uap_awarddate = uap_awarddate;
		this.uap_college = uap_college;
		this.isnull = isnull;
	}
	
	
}
