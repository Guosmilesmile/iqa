package cn.edu.xmu.entity;

import java.sql.Date;

/**
 * @author zhantu
 * 本科生奖贷补（自然年） 实体类
 * date 2015-07-05
 */
public class UndergraduateAwardLoan {
	//ID
	private int ual_id;
	//总计资助金额（万元）
	private Float ual_sumcost;
	//政府奖、助学金资助金额（万元）
	private Float ual_govcost;
	//社会奖助学金资助金额（万元）
	private Float ual_societycost;
	//学校奖学金资助金额（万元）
	private Float ual_schoolcost;
	//国家助学贷款资助金额（万元）
	private Float ual_countrycost;
	//勤工助学金资助金额（万元）
	private Float ual_workstudycost;
	//减免学费资助金额（万元）
	private Float ual_deratecost;
	//临时困难补助资助金额（万元）
	private Float ual_troubleaidcost;
	
	//总计资助学生数（人次）
	private Integer ual_sumcount;
	//政府奖、助学金资助学生数（人次）
	private Integer ual_govcount;
	//社会奖助学金资助学生数（人次）
	private Integer ual_societycount;
	//学校奖学金资助学生数（人次）
	private Integer ual_schoolcount;
	//国家助学贷款资助学生数（人次）
	private Integer ual_countrycount;
	//勤工助学金资助学生数（人次）
	private Integer ual_workstudycount;
	//减免学费资助学生数（人次）
	private Integer ual_deratecount;
	//临时困难补助资助学生数（人次）
	private Integer ual_troubleaidcount;
		
	//序列号
	private int ual_serialnumber;
	//截止日期
	private Date ual_deadline;
	//所属学院
	private String ual_college;
	//审核意见
	private String ual_comments;
	//记录是否存在空值
	private int isnull;
	public int getUal_id() {
		return ual_id;
	}
	public void setUal_id(int ual_id) {
		this.ual_id = ual_id;
	}
	public Float getUal_sumcost() {
		return ual_sumcost;
	}
	public void setUal_sumcost(Float ual_sumcost) {
		this.ual_sumcost = ual_sumcost;
	}
	public Float getUal_govcost() {
		return ual_govcost;
	}
	public void setUal_govcost(Float ual_govcost) {
		this.ual_govcost = ual_govcost;
	}
	public Float getUal_societycost() {
		return ual_societycost;
	}
	public void setUal_societycost(Float ual_societycost) {
		this.ual_societycost = ual_societycost;
	}
	public Float getUal_schoolcost() {
		return ual_schoolcost;
	}
	public void setUal_schoolcost(Float ual_schoolcost) {
		this.ual_schoolcost = ual_schoolcost;
	}
	public Float getUal_countrycost() {
		return ual_countrycost;
	}
	public void setUal_countrycost(Float ual_countrycost) {
		this.ual_countrycost = ual_countrycost;
	}
	public Float getUal_workstudycost() {
		return ual_workstudycost;
	}
	public void setUal_workstudycost(Float ual_workstudycost) {
		this.ual_workstudycost = ual_workstudycost;
	}
	public Float getUal_deratecost() {
		return ual_deratecost;
	}
	public void setUal_deratecost(Float ual_deratecost) {
		this.ual_deratecost = ual_deratecost;
	}
	public Float getUal_troubleaidcost() {
		return ual_troubleaidcost;
	}
	public void setUal_troubleaidcost(Float ual_troubleaidcost) {
		this.ual_troubleaidcost = ual_troubleaidcost;
	}
	public Integer getUal_sumcount() {
		return ual_sumcount;
	}
	public void setUal_sumcount(Integer ual_sumcount) {
		this.ual_sumcount = ual_sumcount;
	}
	public Integer getUal_govcount() {
		return ual_govcount;
	}
	public void setUal_govcount(Integer ual_govcount) {
		this.ual_govcount = ual_govcount;
	}
	public Integer getUal_societycount() {
		return ual_societycount;
	}
	public void setUal_societycount(Integer ual_societycount) {
		this.ual_societycount = ual_societycount;
	}
	public Integer getUal_schoolcount() {
		return ual_schoolcount;
	}
	public void setUal_schoolcount(Integer ual_schoolcount) {
		this.ual_schoolcount = ual_schoolcount;
	}
	public Integer getUal_countrycount() {
		return ual_countrycount;
	}
	public void setUal_countrycount(Integer ual_countrycount) {
		this.ual_countrycount = ual_countrycount;
	}
	public Integer getUal_workstudycount() {
		return ual_workstudycount;
	}
	public void setUal_workstudycount(Integer ual_workstudycount) {
		this.ual_workstudycount = ual_workstudycount;
	}
	public Integer getUal_deratecount() {
		return ual_deratecount;
	}
	public void setUal_deratecount(Integer ual_deratecount) {
		this.ual_deratecount = ual_deratecount;
	}
	public Integer getUal_troubleaidcount() {
		return ual_troubleaidcount;
	}
	public void setUal_troubleaidcount(Integer ual_troubleaidcount) {
		this.ual_troubleaidcount = ual_troubleaidcount;
	}
	public int getUal_serialnumber() {
		return ual_serialnumber;
	}
	public void setUal_serialnumber(int ual_serialnumber) {
		this.ual_serialnumber = ual_serialnumber;
	}
	public Date getUal_deadline() {
		return ual_deadline;
	}
	public void setUal_deadline(Date ual_deadline) {
		this.ual_deadline = ual_deadline;
	}
	public String getUal_college() {
		return ual_college;
	}
	public void setUal_college(String ual_college) {
		this.ual_college = ual_college;
	}
	public String getUal_comments() {
		return ual_comments;
	}
	public void setUal_comments(String ual_comments) {
		this.ual_comments = ual_comments;
	}
	public int getIsnull() {
		return isnull;
	}
	public void setIsnull(int isnull) {
		this.isnull = isnull;
	}
	public UndergraduateAwardLoan() {
		super();
	}
	public UndergraduateAwardLoan(int ual_id, Float ual_sumcost,
			Float ual_govcost, Float ual_societycost, Float ual_schoolcost,
			Float ual_countrycost, Float ual_workstudycost,
			Float ual_deratecost, Float ual_troubleaidcost,
			Integer ual_sumcount, Integer ual_govcount,
			Integer ual_societycount, Integer ual_schoolcount,
			Integer ual_countrycount, Integer ual_workstudycount,
			Integer ual_deratecount, Integer ual_troubleaidcount,
			int ual_serialnumber, Date ual_deadline, String ual_college,
			String ual_comments, int isnull) {
		super();
		this.ual_id = ual_id;
		this.ual_sumcost = ual_sumcost;
		this.ual_govcost = ual_govcost;
		this.ual_societycost = ual_societycost;
		this.ual_schoolcost = ual_schoolcost;
		this.ual_countrycost = ual_countrycost;
		this.ual_workstudycost = ual_workstudycost;
		this.ual_deratecost = ual_deratecost;
		this.ual_troubleaidcost = ual_troubleaidcost;
		this.ual_sumcount = ual_sumcount;
		this.ual_govcount = ual_govcount;
		this.ual_societycount = ual_societycount;
		this.ual_schoolcount = ual_schoolcount;
		this.ual_countrycount = ual_countrycount;
		this.ual_workstudycount = ual_workstudycount;
		this.ual_deratecount = ual_deratecount;
		this.ual_troubleaidcount = ual_troubleaidcount;
		this.ual_serialnumber = ual_serialnumber;
		this.ual_deadline = ual_deadline;
		this.ual_college = ual_college;
		this.ual_comments = ual_comments;
		this.isnull = isnull;
	}
	public UndergraduateAwardLoan(Float ual_sumcost, Float ual_govcost,
			Float ual_societycost, Float ual_schoolcost, Float ual_countrycost,
			Float ual_workstudycost, Float ual_deratecost,
			Float ual_troubleaidcost, Integer ual_sumcount,
			Integer ual_govcount, Integer ual_societycount,
			Integer ual_schoolcount, Integer ual_countrycount,
			Integer ual_workstudycount, Integer ual_deratecount,
			Integer ual_troubleaidcount, int ual_serialnumber,
			String ual_college, String ual_comments, int isnull) {
		super();
		this.ual_sumcost = ual_sumcost;
		this.ual_govcost = ual_govcost;
		this.ual_societycost = ual_societycost;
		this.ual_schoolcost = ual_schoolcost;
		this.ual_countrycost = ual_countrycost;
		this.ual_workstudycost = ual_workstudycost;
		this.ual_deratecost = ual_deratecost;
		this.ual_troubleaidcost = ual_troubleaidcost;
		this.ual_sumcount = ual_sumcount;
		this.ual_govcount = ual_govcount;
		this.ual_societycount = ual_societycount;
		this.ual_schoolcount = ual_schoolcount;
		this.ual_countrycount = ual_countrycount;
		this.ual_workstudycount = ual_workstudycount;
		this.ual_deratecount = ual_deratecount;
		this.ual_troubleaidcount = ual_troubleaidcount;
		this.ual_serialnumber = ual_serialnumber;
		this.ual_college = ual_college;
		this.ual_comments = ual_comments;
		this.isnull = isnull;
	}
	public UndergraduateAwardLoan(int ual_id, Float ual_sumcost,
			Float ual_govcost, Float ual_societycost, Float ual_schoolcost,
			Float ual_countrycost, Float ual_workstudycost,
			Float ual_deratecost, Float ual_troubleaidcost,
			Integer ual_sumcount, Integer ual_govcount,
			Integer ual_societycount, Integer ual_schoolcount,
			Integer ual_countrycount, Integer ual_workstudycount,
			Integer ual_deratecount, Integer ual_troubleaidcount,
			int ual_serialnumber, String ual_comments, int isnull) {
		super();
		this.ual_id = ual_id;
		this.ual_sumcost = ual_sumcost;
		this.ual_govcost = ual_govcost;
		this.ual_societycost = ual_societycost;
		this.ual_schoolcost = ual_schoolcost;
		this.ual_countrycost = ual_countrycost;
		this.ual_workstudycost = ual_workstudycost;
		this.ual_deratecost = ual_deratecost;
		this.ual_troubleaidcost = ual_troubleaidcost;
		this.ual_sumcount = ual_sumcount;
		this.ual_govcount = ual_govcount;
		this.ual_societycount = ual_societycount;
		this.ual_schoolcount = ual_schoolcount;
		this.ual_countrycount = ual_countrycount;
		this.ual_workstudycount = ual_workstudycount;
		this.ual_deratecount = ual_deratecount;
		this.ual_troubleaidcount = ual_troubleaidcount;
		this.ual_serialnumber = ual_serialnumber;
		this.ual_comments = ual_comments;
		this.isnull = isnull;
	}
	public UndergraduateAwardLoan(int ual_id, Float ual_sumcost,
			Float ual_govcost, Float ual_societycost, Float ual_schoolcost,
			Float ual_countrycost, Float ual_workstudycost,
			Float ual_deratecost, Float ual_troubleaidcost,
			Integer ual_sumcount, Integer ual_govcount,
			Integer ual_societycount, Integer ual_schoolcount,
			Integer ual_countrycount, Integer ual_workstudycount,
			Integer ual_deratecount, Integer ual_troubleaidcount,
			String ual_comments, int isnull) {
		super();
		this.ual_id = ual_id;
		this.ual_sumcost = ual_sumcost;
		this.ual_govcost = ual_govcost;
		this.ual_societycost = ual_societycost;
		this.ual_schoolcost = ual_schoolcost;
		this.ual_countrycost = ual_countrycost;
		this.ual_workstudycost = ual_workstudycost;
		this.ual_deratecost = ual_deratecost;
		this.ual_troubleaidcost = ual_troubleaidcost;
		this.ual_sumcount = ual_sumcount;
		this.ual_govcount = ual_govcount;
		this.ual_societycount = ual_societycount;
		this.ual_schoolcount = ual_schoolcount;
		this.ual_countrycount = ual_countrycount;
		this.ual_workstudycount = ual_workstudycount;
		this.ual_deratecount = ual_deratecount;
		this.ual_troubleaidcount = ual_troubleaidcount;
		this.ual_comments = ual_comments;
		this.isnull = isnull;
	}
	public UndergraduateAwardLoan(Float ual_sumcost, Float ual_govcost,
			Float ual_societycost, Float ual_schoolcost, Float ual_countrycost,
			Float ual_workstudycost, Float ual_deratecost,
			Float ual_troubleaidcost, Integer ual_sumcount,
			Integer ual_govcount, Integer ual_societycount,
			Integer ual_schoolcount, Integer ual_countrycount,
			Integer ual_workstudycount, Integer ual_deratecount,
			Integer ual_troubleaidcount, String ual_college, int isnull) {
		super();
		this.ual_sumcost = ual_sumcost;
		this.ual_govcost = ual_govcost;
		this.ual_societycost = ual_societycost;
		this.ual_schoolcost = ual_schoolcost;
		this.ual_countrycost = ual_countrycost;
		this.ual_workstudycost = ual_workstudycost;
		this.ual_deratecost = ual_deratecost;
		this.ual_troubleaidcost = ual_troubleaidcost;
		this.ual_sumcount = ual_sumcount;
		this.ual_govcount = ual_govcount;
		this.ual_societycount = ual_societycount;
		this.ual_schoolcount = ual_schoolcount;
		this.ual_countrycount = ual_countrycount;
		this.ual_workstudycount = ual_workstudycount;
		this.ual_deratecount = ual_deratecount;
		this.ual_troubleaidcount = ual_troubleaidcount;
		this.ual_college = ual_college;
		this.isnull = isnull;
	}
	
}
