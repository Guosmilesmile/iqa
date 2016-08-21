package cn.edu.xmu.entity;

import java.sql.Date;

/**
 * @author zhantu
 * 应届本科毕业生就业情况（时点） 实体类
 * date 2015-07-05
 */
public class EmploymentSituationofCollegeGraduates {
	//ID
	private int escg_id;
	//免试推荐研究生
	private Integer escg_recommendgraduate;
	//考研录取总数
	private Integer escg_postgraduateexamsum;
	//考研录取考取本校
	private Integer escg_postgraduateexamin;
	//考研录取考取外校
	private Integer escg_postgraduateexamout;
	//出国留学
	private Integer escg_studyabroad;
	//就业基本情况总数（人）
	private Integer escg_employmentsum;
	//政府机构
	private Integer escg_gov;
	//事业单位
	private Integer escg_institution;
	//企业
	private Integer escg_enterprise;
	//部队
	private Integer escg_troops;
	//灵活就业
	private Integer escg_flexibleemployment;
	//升学
	private Integer esce_entrance;
	//参加国家地方项目就业
	private Integer escg_nationallocalprojectemployment;
	//其他
	private Integer escg_others;
	//序列号
	private int escg_serialnumber;
	//截止日期
	private Date escg_deadline;
	//所属学院
	private String escg_college;
	//审核意见
	private String escg_comments;
	//记录是否存在空值
	private int isnull;
	public int getEscg_id() {
		return escg_id;
	}
	public void setEscg_id(int escg_id) {
		this.escg_id = escg_id;
	}
	public Integer getEscg_recommendgraduate() {
		return escg_recommendgraduate;
	}
	public void setEscg_recommendgraduate(Integer escg_recommendgraduate) {
		this.escg_recommendgraduate = escg_recommendgraduate;
	}
	public Integer getEscg_postgraduateexamsum() {
		return escg_postgraduateexamsum;
	}
	public void setEscg_postgraduateexamsum(Integer escg_postgraduateexamsum) {
		this.escg_postgraduateexamsum = escg_postgraduateexamsum;
	}
	public Integer getEscg_postgraduateexamin() {
		return escg_postgraduateexamin;
	}
	public void setEscg_postgraduateexamin(Integer escg_postgraduateexamin) {
		this.escg_postgraduateexamin = escg_postgraduateexamin;
	}
	public Integer getEscg_postgraduateexamout() {
		return escg_postgraduateexamout;
	}
	public void setEscg_postgraduateexamout(Integer escg_postgraduateexamout) {
		this.escg_postgraduateexamout = escg_postgraduateexamout;
	}
	public Integer getEscg_studyabroad() {
		return escg_studyabroad;
	}
	public void setEscg_studyabroad(Integer escg_studyabroad) {
		this.escg_studyabroad = escg_studyabroad;
	}
	public Integer getEscg_employmentsum() {
		return escg_employmentsum;
	}
	public void setEscg_employmentsum(Integer escg_employmentsum) {
		this.escg_employmentsum = escg_employmentsum;
	}
	public Integer getEscg_gov() {
		return escg_gov;
	}
	public void setEscg_gov(Integer escg_gov) {
		this.escg_gov = escg_gov;
	}
	public Integer getEscg_institution() {
		return escg_institution;
	}
	public void setEscg_institution(Integer escg_institution) {
		this.escg_institution = escg_institution;
	}
	public Integer getEscg_enterprise() {
		return escg_enterprise;
	}
	public void setEscg_enterprise(Integer escg_enterprise) {
		this.escg_enterprise = escg_enterprise;
	}
	public Integer getEscg_troops() {
		return escg_troops;
	}
	public void setEscg_troops(Integer escg_troops) {
		this.escg_troops = escg_troops;
	}
	public Integer getEscg_flexibleemployment() {
		return escg_flexibleemployment;
	}
	public void setEscg_flexibleemployment(Integer escg_flexibleemployment) {
		this.escg_flexibleemployment = escg_flexibleemployment;
	}
	public Integer getEsce_entrance() {
		return esce_entrance;
	}
	public void setEsce_entrance(Integer esce_entrance) {
		this.esce_entrance = esce_entrance;
	}
	public Integer getEscg_nationallocalprojectemployment() {
		return escg_nationallocalprojectemployment;
	}
	public void setEscg_nationallocalprojectemployment(
			Integer escg_nationallocalprojectemployment) {
		this.escg_nationallocalprojectemployment = escg_nationallocalprojectemployment;
	}
	public Integer getEscg_others() {
		return escg_others;
	}
	public void setEscg_others(Integer escg_others) {
		this.escg_others = escg_others;
	}
	public int getEscg_serialnumber() {
		return escg_serialnumber;
	}
	public void setEscg_serialnumber(int escg_serialnumber) {
		this.escg_serialnumber = escg_serialnumber;
	}
	public Date getEscg_deadline() {
		return escg_deadline;
	}
	public void setEscg_deadline(Date escg_deadline) {
		this.escg_deadline = escg_deadline;
	}
	public String getEscg_college() {
		return escg_college;
	}
	public void setEscg_college(String escg_college) {
		this.escg_college = escg_college;
	}
	public String getEscg_comments() {
		return escg_comments;
	}
	public void setEscg_comments(String escg_comments) {
		this.escg_comments = escg_comments;
	}
	public int getIsnull() {
		return isnull;
	}
	public void setIsnull(int isnull) {
		this.isnull = isnull;
	}
	public EmploymentSituationofCollegeGraduates() {
		super();
	}
	public EmploymentSituationofCollegeGraduates(int escg_id,
			Integer escg_recommendgraduate, Integer escg_postgraduateexamsum,
			Integer escg_postgraduateexamin, Integer escg_postgraduateexamout,
			Integer escg_studyabroad, Integer escg_employmentsum,
			Integer escg_gov, Integer escg_institution,
			Integer escg_enterprise, Integer escg_troops,
			Integer escg_flexibleemployment, Integer esce_entrance,
			Integer escg_nationallocalprojectemployment, Integer escg_others,
			int escg_serialnumber, Date escg_deadline, String escg_college,
			String escg_comments, int isnull) {
		super();
		this.escg_id = escg_id;
		this.escg_recommendgraduate = escg_recommendgraduate;
		this.escg_postgraduateexamsum = escg_postgraduateexamsum;
		this.escg_postgraduateexamin = escg_postgraduateexamin;
		this.escg_postgraduateexamout = escg_postgraduateexamout;
		this.escg_studyabroad = escg_studyabroad;
		this.escg_employmentsum = escg_employmentsum;
		this.escg_gov = escg_gov;
		this.escg_institution = escg_institution;
		this.escg_enterprise = escg_enterprise;
		this.escg_troops = escg_troops;
		this.escg_flexibleemployment = escg_flexibleemployment;
		this.esce_entrance = esce_entrance;
		this.escg_nationallocalprojectemployment = escg_nationallocalprojectemployment;
		this.escg_others = escg_others;
		this.escg_serialnumber = escg_serialnumber;
		this.escg_deadline = escg_deadline;
		this.escg_college = escg_college;
		this.escg_comments = escg_comments;
		this.isnull = isnull;
	}
	public EmploymentSituationofCollegeGraduates(
			Integer escg_recommendgraduate, Integer escg_postgraduateexamsum,
			Integer escg_postgraduateexamin, Integer escg_postgraduateexamout,
			Integer escg_studyabroad, Integer escg_employmentsum,
			Integer escg_gov, Integer escg_institution,
			Integer escg_enterprise, Integer escg_troops,
			Integer escg_flexibleemployment, Integer esce_entrance,
			Integer escg_nationallocalprojectemployment, Integer escg_others,
			int escg_serialnumber, String escg_college, String escg_comments,
			int isnull) {
		super();
		this.escg_recommendgraduate = escg_recommendgraduate;
		this.escg_postgraduateexamsum = escg_postgraduateexamsum;
		this.escg_postgraduateexamin = escg_postgraduateexamin;
		this.escg_postgraduateexamout = escg_postgraduateexamout;
		this.escg_studyabroad = escg_studyabroad;
		this.escg_employmentsum = escg_employmentsum;
		this.escg_gov = escg_gov;
		this.escg_institution = escg_institution;
		this.escg_enterprise = escg_enterprise;
		this.escg_troops = escg_troops;
		this.escg_flexibleemployment = escg_flexibleemployment;
		this.esce_entrance = esce_entrance;
		this.escg_nationallocalprojectemployment = escg_nationallocalprojectemployment;
		this.escg_others = escg_others;
		this.escg_serialnumber = escg_serialnumber;
		this.escg_college = escg_college;
		this.escg_comments = escg_comments;
		this.isnull = isnull;
	}
	public EmploymentSituationofCollegeGraduates(int escg_id,
			Integer escg_recommendgraduate, Integer escg_postgraduateexamsum,
			Integer escg_postgraduateexamin, Integer escg_postgraduateexamout,
			Integer escg_studyabroad, Integer escg_employmentsum,
			Integer escg_gov, Integer escg_institution,
			Integer escg_enterprise, Integer escg_troops,
			Integer escg_flexibleemployment, Integer esce_entrance,
			Integer escg_nationallocalprojectemployment, Integer escg_others,
			int escg_serialnumber, String escg_comments, int isnull) {
		super();
		this.escg_id = escg_id;
		this.escg_recommendgraduate = escg_recommendgraduate;
		this.escg_postgraduateexamsum = escg_postgraduateexamsum;
		this.escg_postgraduateexamin = escg_postgraduateexamin;
		this.escg_postgraduateexamout = escg_postgraduateexamout;
		this.escg_studyabroad = escg_studyabroad;
		this.escg_employmentsum = escg_employmentsum;
		this.escg_gov = escg_gov;
		this.escg_institution = escg_institution;
		this.escg_enterprise = escg_enterprise;
		this.escg_troops = escg_troops;
		this.escg_flexibleemployment = escg_flexibleemployment;
		this.esce_entrance = esce_entrance;
		this.escg_nationallocalprojectemployment = escg_nationallocalprojectemployment;
		this.escg_others = escg_others;
		this.escg_serialnumber = escg_serialnumber;
		this.escg_comments = escg_comments;
		this.isnull = isnull;
	}
	public EmploymentSituationofCollegeGraduates(int escg_id,
			Integer escg_recommendgraduate, Integer escg_postgraduateexamsum,
			Integer escg_postgraduateexamin, Integer escg_postgraduateexamout,
			Integer escg_studyabroad, Integer escg_employmentsum,
			Integer escg_gov, Integer escg_institution,
			Integer escg_enterprise, Integer escg_troops,
			Integer escg_flexibleemployment, Integer esce_entrance,
			Integer escg_nationallocalprojectemployment, Integer escg_others,
			String escg_comments, int isnull) {
		super();
		this.escg_id = escg_id;
		this.escg_recommendgraduate = escg_recommendgraduate;
		this.escg_postgraduateexamsum = escg_postgraduateexamsum;
		this.escg_postgraduateexamin = escg_postgraduateexamin;
		this.escg_postgraduateexamout = escg_postgraduateexamout;
		this.escg_studyabroad = escg_studyabroad;
		this.escg_employmentsum = escg_employmentsum;
		this.escg_gov = escg_gov;
		this.escg_institution = escg_institution;
		this.escg_enterprise = escg_enterprise;
		this.escg_troops = escg_troops;
		this.escg_flexibleemployment = escg_flexibleemployment;
		this.esce_entrance = esce_entrance;
		this.escg_nationallocalprojectemployment = escg_nationallocalprojectemployment;
		this.escg_others = escg_others;
		this.escg_comments = escg_comments;
		this.isnull = isnull;
	}
	public EmploymentSituationofCollegeGraduates(
			Integer escg_recommendgraduate, Integer escg_postgraduateexamsum,
			Integer escg_postgraduateexamin, Integer escg_postgraduateexamout,
			Integer escg_studyabroad, Integer escg_employmentsum,
			Integer escg_gov, Integer escg_institution,
			Integer escg_enterprise, Integer escg_troops,
			Integer escg_flexibleemployment, Integer esce_entrance,
			Integer escg_nationallocalprojectemployment, Integer escg_others,
			String escg_college, int isnull) {
		super();
		this.escg_recommendgraduate = escg_recommendgraduate;
		this.escg_postgraduateexamsum = escg_postgraduateexamsum;
		this.escg_postgraduateexamin = escg_postgraduateexamin;
		this.escg_postgraduateexamout = escg_postgraduateexamout;
		this.escg_studyabroad = escg_studyabroad;
		this.escg_employmentsum = escg_employmentsum;
		this.escg_gov = escg_gov;
		this.escg_institution = escg_institution;
		this.escg_enterprise = escg_enterprise;
		this.escg_troops = escg_troops;
		this.escg_flexibleemployment = escg_flexibleemployment;
		this.esce_entrance = esce_entrance;
		this.escg_nationallocalprojectemployment = escg_nationallocalprojectemployment;
		this.escg_others = escg_others;
		this.escg_college = escg_college;
		this.isnull = isnull;
	}
	
	
	
}
