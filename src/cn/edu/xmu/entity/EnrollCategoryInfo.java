package cn.edu.xmu.entity;

import java.sql.Date;

/**
 * 表6-1-3  近一届本科生招生类别情况
 * @author yue
 *
 */
public class EnrollCategoryInfo {
	private int eci_id;//id
	private Integer eci_plannumber;//计划招生数
	private Integer eci_enrollnumber;//实际录取数
	private Integer eci_registernumber;//实际报到数
	private Integer eci_indrecruitmentnumber;//自主招生数
	private Integer eci_specialnumber;//招收特长生数
	private Integer eci_provincenumber;//招收本省学生数
	private Integer eci_newspecialitynumber;//新办专业招生
	private int eci_serialnumber;//序列号
	private Date eci_deadline;//截止日期
	private String eci_college;//所属学院
	private String eci_comments;//审核意见
	private int isnull;
	
	
	public EnrollCategoryInfo(int eci_id, Integer eci_plannumber, Integer eci_enrollnumber, Integer eci_registernumber,
			Integer eci_indrecruitmentnumber, Integer eci_specialnumber, Integer eci_provincenumber,
			Integer eci_newspecialitynumber, int eci_serialnumber, Date eci_deadline, String eci_college,
			String eci_comments, int isnull) {
		super();
		this.eci_id = eci_id;
		this.eci_plannumber = eci_plannumber;
		this.eci_enrollnumber = eci_enrollnumber;
		this.eci_registernumber = eci_registernumber;
		this.eci_indrecruitmentnumber = eci_indrecruitmentnumber;
		this.eci_specialnumber = eci_specialnumber;
		this.eci_provincenumber = eci_provincenumber;
		this.eci_newspecialitynumber = eci_newspecialitynumber;
		this.eci_serialnumber = eci_serialnumber;
		this.eci_deadline = eci_deadline;
		this.eci_college = eci_college;
		this.eci_comments = eci_comments;
		this.isnull = isnull;
	}
	
	
	public EnrollCategoryInfo(Integer eci_plannumber, Integer eci_enrollnumber, Integer eci_registernumber,
			Integer eci_indrecruitmentnumber, Integer eci_specialnumber, Integer eci_provincenumber,
			Integer eci_newspecialitynumber, String eci_college,int eci_serialnumber,  String eci_comments,
			int isnull) {
		super();
		this.eci_plannumber = eci_plannumber;
		this.eci_enrollnumber = eci_enrollnumber;
		this.eci_registernumber = eci_registernumber;
		this.eci_indrecruitmentnumber = eci_indrecruitmentnumber;
		this.eci_specialnumber = eci_specialnumber;
		this.eci_provincenumber = eci_provincenumber;
		this.eci_newspecialitynumber = eci_newspecialitynumber;
		this.eci_serialnumber = eci_serialnumber;
		this.eci_college = eci_college;
		this.eci_comments = eci_comments;
		this.isnull = isnull;
	}


	public EnrollCategoryInfo(Integer eci_plannumber, Integer eci_enrollnumber, Integer eci_registernumber,
			Integer eci_indrecruitmentnumber, Integer eci_specialnumber, Integer eci_provincenumber,
			Integer eci_newspecialitynumber, String eci_college, int isnull) {
		super();
		this.eci_plannumber = eci_plannumber;
		this.eci_enrollnumber = eci_enrollnumber;
		this.eci_registernumber = eci_registernumber;
		this.eci_indrecruitmentnumber = eci_indrecruitmentnumber;
		this.eci_specialnumber = eci_specialnumber;
		this.eci_provincenumber = eci_provincenumber;
		this.eci_newspecialitynumber = eci_newspecialitynumber;
		this.eci_college = eci_college;
		this.eci_comments = "";
		this.isnull = isnull;
	}


	public int getEci_id() {
		return eci_id;
	}
	public void setEci_id(int eci_id) {
		this.eci_id = eci_id;
	}
	public Integer getEci_plannumber() {
		return eci_plannumber;
	}
	public void setEci_plannumber(Integer eci_plannumber) {
		this.eci_plannumber = eci_plannumber;
	}
	public Integer getEci_enrollnumber() {
		return eci_enrollnumber;
	}
	public void setEci_enrollnumber(Integer eci_enrollnumber) {
		this.eci_enrollnumber = eci_enrollnumber;
	}
	public Integer getEci_registernumber() {
		return eci_registernumber;
	}
	public void setEci_registernumber(Integer eci_registernumber) {
		this.eci_registernumber = eci_registernumber;
	}
	public Integer getEci_indrecruitmentnumber() {
		return eci_indrecruitmentnumber;
	}
	public void setEci_indrecruitmentnumber(Integer eci_indrecruitmentnumber) {
		this.eci_indrecruitmentnumber = eci_indrecruitmentnumber;
	}
	public Integer getEci_specialnumber() {
		return eci_specialnumber;
	}
	public void setEci_specialnumber(Integer eci_specialnumber) {
		this.eci_specialnumber = eci_specialnumber;
	}
	public Integer getEci_provincenumber() {
		return eci_provincenumber;
	}
	public void setEci_provincenumber(Integer eci_provincenumber) {
		this.eci_provincenumber = eci_provincenumber;
	}
	public Integer getEci_newspecialitynumber() {
		return eci_newspecialitynumber;
	}
	public void setEci_newspecialitynumber(Integer eci_newspecialitynumber) {
		this.eci_newspecialitynumber = eci_newspecialitynumber;
	}
	public int getEci_serialnumber() {
		return eci_serialnumber;
	}
	public void setEci_serialnumber(int eci_serialnumber) {
		this.eci_serialnumber = eci_serialnumber;
	}
	public Date getEci_deadline() {
		return eci_deadline;
	}
	public void setEci_deadline(Date eci_deadline) {
		this.eci_deadline = eci_deadline;
	}
	public String getEci_college() {
		return eci_college;
	}
	public void setEci_college(String eci_college) {
		this.eci_college = eci_college;
	}
	public String getEci_comments() {
		return eci_comments;
	}
	public void setEci_comments(String eci_comments) {
		this.eci_comments = eci_comments;
	}
	public int getIsnull() {
		return isnull;
	}
	public void setIsnull(int isnull) {
		this.isnull = isnull;
	}
	
	
	
}
