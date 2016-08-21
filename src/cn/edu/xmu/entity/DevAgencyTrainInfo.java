package cn.edu.xmu.entity;

import java.sql.Date;

/**
 * 附表3-5-1-1教师教学发展机构培训情况（学年）
 * @author yue
 *
 */
public class DevAgencyTrainInfo {
	private int dati_id;
	private String dati_name;//机构名称
	private String dati_departmentname;//单位名称
	private String dati_departmentnumber;//单位号
	private String dati_workplan;//工作计划
	private Integer dati_traintimes;//培训次数
	private Integer dati_traintrips;//培训人次
	private int dati_serialnumber;//序列号
	private Date dati_deadline;//截止日期
	private String dati_college;//所属学院
	private String dati_comments;//审核意见
	private int isnull;
	
	public DevAgencyTrainInfo(int dati_id, String dati_name, String dati_departmentname,String dati_departmentnumber, String dati_workplan,
			Integer dati_traintimes, Integer dati_traintrips, int dati_serialnumber, Date dati_deadline, String dati_college,
			String dati_comments,int isnull) {
		super();
		this.dati_id = dati_id;
		this.dati_name = dati_name;
		this.dati_departmentname = dati_departmentname;
		this.dati_departmentnumber = dati_departmentnumber;
		this.dati_workplan = dati_workplan;
		this.dati_traintimes = dati_traintimes;
		this.dati_traintrips = dati_traintrips;
		this.dati_serialnumber = dati_serialnumber;
		this.dati_deadline = dati_deadline;
		this.dati_college = dati_college;
		this.dati_comments = dati_comments;
		this.isnull = isnull;
	}
	public DevAgencyTrainInfo( String dati_name, String dati_departmentname, String dati_departmentnumber, String dati_workplan,
			Integer dati_traintimes, Integer dati_traintrips, int dati_serialnumber, String dati_college,
			String dati_comments,int isnull) {
		super();
		this.dati_name = dati_name;
		this.dati_departmentname = dati_departmentname;
		this.dati_departmentnumber = dati_departmentnumber;
		this.dati_workplan = dati_workplan;
		this.dati_traintimes = dati_traintimes;
		this.dati_traintrips = dati_traintrips;
		this.dati_serialnumber = dati_serialnumber;
		this.dati_college = dati_college;
		this.dati_comments = dati_comments;
		this.isnull = isnull;
	}
	public DevAgencyTrainInfo( String dati_name,String dati_departmentname, String dati_departmentnumber, String dati_workplan,
			Integer dati_traintimes, Integer dati_traintrips, String dati_college,int isnull) {
		super();
		this.dati_name = dati_name;
		this.dati_departmentname = dati_departmentname;
		this.dati_departmentnumber = dati_departmentnumber;
		this.dati_workplan = dati_workplan;
		this.dati_traintimes = dati_traintimes;
		this.dati_traintrips = dati_traintrips;
		this.dati_college = dati_college;
		this.dati_comments = "";
		this.isnull = isnull;
	}
	public int getDati_id() {
		return dati_id;
	}
	public void setDati_id(int dati_id) {
		this.dati_id = dati_id;
	}
	public String getDati_name() {
		return dati_name;
	}
	public void setDati_name(String dati_name) {
		this.dati_name = dati_name;
	}
	public String getDati_departmentname() {
		return dati_departmentname;
	}
	public void setDati_departmentname(String dati_departmentname) {
		this.dati_departmentname = dati_departmentname;
	}
	public String getDati_departmentnumber() {
		return dati_departmentnumber;
	}
	public void setDati_departmentnumber(String dati_departmentnumber) {
		this.dati_departmentnumber = dati_departmentnumber;
	}
	public String getDati_workplan() {
		return dati_workplan;
	}
	public void setDati_workplan(String dati_workplan) {
		this.dati_workplan = dati_workplan;
	}
	public Integer getDati_traintimes() {
		return dati_traintimes;
	}
	public void setDati_traintimes(Integer dati_traintimes) {
		this.dati_traintimes = dati_traintimes;
	}
	public Integer getDati_traintrips() {
		return dati_traintrips;
	}
	public void setDati_traintrips(Integer dati_traintrips) {
		this.dati_traintrips = dati_traintrips;
	}
	public int getDati_serialnumber() {
		return dati_serialnumber;
	}
	public void setDati_serialnumber(int dati_serialnumber) {
		this.dati_serialnumber = dati_serialnumber;
	}
	public Date getDati_deadline() {
		return dati_deadline;
	}
	public void setDati_deadline(Date dati_deadline) {
		this.dati_deadline = dati_deadline;
	}
	public String getDati_college() {
		return dati_college;
	}
	public void setDati_college(String dati_college) {
		this.dati_college = dati_college;
	}
	public String getDati_comments() {
		return dati_comments;
	}
	public void setDati_comments(String dati_comments) {
		this.dati_comments = dati_comments;
	}
	public int getIsnull() {
		return isnull;
	}
	public void setIsnull(int isnull) {
		this.isnull = isnull;
	}
	

	
	
}
