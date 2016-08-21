package cn.edu.xmu.entity;

import java.sql.Date;

/**
 * @author zhantu
 * 教师科研项目数  (自然年) 实体类
 * date 2015-07-03
 */
public class TeacherResearchNumber {
	//ID
	private int trn_id;
	//项目数总计(项)
	private Integer trn_projectnum;
	//横向项目总数
	private Integer trn_hrznprojectnum;
	//横向人文社会科学项目总数
	private Integer trn_hrznhumanitiesnum;
	//纵向项目总数
	private Integer trn_vtclprojectnum;
	//纵向人文社会科学项目总数
	private Integer trn_vtclhumanitiesnum;
	//项目经费总计(万元)
	private Float trn_projectcost;
	//横向项目经费
	private Float trn_hrznprojectcost;
	//横向人文社会科学项目经费
	private Float trn_hrznhumanitiescost;
	//纵向项目经费
	private Float trn_vtclprojectcost;
	//纵向人文社会科学项目经费
	private Float trn_vtclhumanitiescost;
	//序列号
	private int trn_serialnumber;
	//截止日期
	private Date trn_deadline;
	//所属学院
	private String trn_college;
	//审核意见
	private String trn_comments;
	//记录是否存在空值
	private int isnull;
	public int getTrn_id() {
		return trn_id;
	}
	public void setTrn_id(int trn_id) {
		this.trn_id = trn_id;
	}
	public Integer getTrn_projectnum() {
		return trn_projectnum;
	}
	public void setTrn_projectnum(Integer trn_projectnum) {
		this.trn_projectnum = trn_projectnum;
	}
	public Integer getTrn_hrznprojectnum() {
		return trn_hrznprojectnum;
	}
	public void setTrn_hrznprojectnum(Integer trn_hrznprojectnum) {
		this.trn_hrznprojectnum = trn_hrznprojectnum;
	}
	public Integer getTrn_hrznhumanitiesnum() {
		return trn_hrznhumanitiesnum;
	}
	public void setTrn_hrznhumanitiesnum(Integer trn_hrznhumanitiesnum) {
		this.trn_hrznhumanitiesnum = trn_hrznhumanitiesnum;
	}
	public Integer getTrn_vtclprojectnum() {
		return trn_vtclprojectnum;
	}
	public void setTrn_vtclprojectnum(Integer trn_vtclprojectnum) {
		this.trn_vtclprojectnum = trn_vtclprojectnum;
	}
	public Integer getTrn_vtclhumanitiesnum() {
		return trn_vtclhumanitiesnum;
	}
	public void setTrn_vtclhumanitiesnum(Integer trn_vtclhumanitiesnum) {
		this.trn_vtclhumanitiesnum = trn_vtclhumanitiesnum;
	}
	public Float getTrn_projectcost() {
		return trn_projectcost;
	}
	public void setTrn_projectcost(Float trn_projectcost) {
		this.trn_projectcost = trn_projectcost;
	}
	public Float getTrn_hrznprojectcost() {
		return trn_hrznprojectcost;
	}
	public void setTrn_hrznprojectcost(Float trn_hrznprojectcost) {
		this.trn_hrznprojectcost = trn_hrznprojectcost;
	}
	public Float getTrn_hrznhumanitiescost() {
		return trn_hrznhumanitiescost;
	}
	public void setTrn_hrznhumanitiescost(Float trn_hrznhumanitiescost) {
		this.trn_hrznhumanitiescost = trn_hrznhumanitiescost;
	}
	public Float getTrn_vtclprojectcost() {
		return trn_vtclprojectcost;
	}
	public void setTrn_vtclprojectcost(Float trn_vtclprojectcost) {
		this.trn_vtclprojectcost = trn_vtclprojectcost;
	}
	public Float getTrn_vtclhumanitiescost() {
		return trn_vtclhumanitiescost;
	}
	public void setTrn_vtclhumanitiescost(Float trn_vtclhumanitiescost) {
		this.trn_vtclhumanitiescost = trn_vtclhumanitiescost;
	}
	public int getTrn_serialnumber() {
		return trn_serialnumber;
	}
	public void setTrn_serialnumber(int trn_serialnumber) {
		this.trn_serialnumber = trn_serialnumber;
	}
	public Date getTrn_deadline() {
		return trn_deadline;
	}
	public void setTrn_deadline(Date trn_deadline) {
		this.trn_deadline = trn_deadline;
	}
	public String getTrn_college() {
		return trn_college;
	}
	public void setTrn_college(String trn_college) {
		this.trn_college = trn_college;
	}
	public String getTrn_comments() {
		return trn_comments;
	}
	public void setTrn_comments(String trn_comments) {
		this.trn_comments = trn_comments;
	}
	public int getIsnull() {
		return isnull;
	}
	public void setIsnull(int isnull) {
		this.isnull = isnull;
	}
	public TeacherResearchNumber() {
		super();
	}
	public TeacherResearchNumber(int trn_id, Integer trn_projectnum,
			Integer trn_hrznprojectnum, Integer trn_hrznhumanitiesnum,
			Integer trn_vtclprojectnum, Integer trn_vtclhumanitiesnum,
			Float trn_projectcost, Float trn_hrznprojectcost,
			Float trn_hrznhumanitiescost, Float trn_vtclprojectcost,
			Float trn_vtclhumanitiescost, int trn_serialnumber,
			Date trn_deadline, String trn_college, String trn_comments,
			int isnull) {
		super();
		this.trn_id = trn_id;
		this.trn_projectnum = trn_projectnum;
		this.trn_hrznprojectnum = trn_hrznprojectnum;
		this.trn_hrznhumanitiesnum = trn_hrznhumanitiesnum;
		this.trn_vtclprojectnum = trn_vtclprojectnum;
		this.trn_vtclhumanitiesnum = trn_vtclhumanitiesnum;
		this.trn_projectcost = trn_projectcost;
		this.trn_hrznprojectcost = trn_hrznprojectcost;
		this.trn_hrznhumanitiescost = trn_hrznhumanitiescost;
		this.trn_vtclprojectcost = trn_vtclprojectcost;
		this.trn_vtclhumanitiescost = trn_vtclhumanitiescost;
		this.trn_serialnumber = trn_serialnumber;
		this.trn_deadline = trn_deadline;
		this.trn_college = trn_college;
		this.trn_comments = trn_comments;
		this.isnull = isnull;
	}
	public TeacherResearchNumber(Integer trn_projectnum,
			Integer trn_hrznprojectnum, Integer trn_hrznhumanitiesnum,
			Integer trn_vtclprojectnum, Integer trn_vtclhumanitiesnum,
			Float trn_projectcost, Float trn_hrznprojectcost,
			Float trn_hrznhumanitiescost, Float trn_vtclprojectcost,
			Float trn_vtclhumanitiescost, int trn_serialnumber,
			String trn_college, String trn_comments, int isnull) {
		super();
		this.trn_projectnum = trn_projectnum;
		this.trn_hrznprojectnum = trn_hrznprojectnum;
		this.trn_hrznhumanitiesnum = trn_hrznhumanitiesnum;
		this.trn_vtclprojectnum = trn_vtclprojectnum;
		this.trn_vtclhumanitiesnum = trn_vtclhumanitiesnum;
		this.trn_projectcost = trn_projectcost;
		this.trn_hrznprojectcost = trn_hrznprojectcost;
		this.trn_hrznhumanitiescost = trn_hrznhumanitiescost;
		this.trn_vtclprojectcost = trn_vtclprojectcost;
		this.trn_vtclhumanitiescost = trn_vtclhumanitiescost;
		this.trn_serialnumber = trn_serialnumber;
		this.trn_college = trn_college;
		this.trn_comments = trn_comments;
		this.isnull = isnull;
	}
	public TeacherResearchNumber(int trn_id, Integer trn_projectnum,
			Integer trn_hrznprojectnum, Integer trn_hrznhumanitiesnum,
			Integer trn_vtclprojectnum, Integer trn_vtclhumanitiesnum,
			Float trn_projectcost, Float trn_hrznprojectcost,
			Float trn_hrznhumanitiescost, Float trn_vtclprojectcost,
			Float trn_vtclhumanitiescost, int trn_serialnumber,
			String trn_comments, int isnull) {
		super();
		this.trn_id = trn_id;
		this.trn_projectnum = trn_projectnum;
		this.trn_hrznprojectnum = trn_hrznprojectnum;
		this.trn_hrznhumanitiesnum = trn_hrznhumanitiesnum;
		this.trn_vtclprojectnum = trn_vtclprojectnum;
		this.trn_vtclhumanitiesnum = trn_vtclhumanitiesnum;
		this.trn_projectcost = trn_projectcost;
		this.trn_hrznprojectcost = trn_hrznprojectcost;
		this.trn_hrznhumanitiescost = trn_hrznhumanitiescost;
		this.trn_vtclprojectcost = trn_vtclprojectcost;
		this.trn_vtclhumanitiescost = trn_vtclhumanitiescost;
		this.trn_serialnumber = trn_serialnumber;
		this.trn_comments = trn_comments;
		this.isnull = isnull;
	}
	public TeacherResearchNumber(int trn_id, Integer trn_projectnum,
			Integer trn_hrznprojectnum, Integer trn_hrznhumanitiesnum,
			Integer trn_vtclprojectnum, Integer trn_vtclhumanitiesnum,
			Float trn_projectcost, Float trn_hrznprojectcost,
			Float trn_hrznhumanitiescost, Float trn_vtclprojectcost,
			Float trn_vtclhumanitiescost, String trn_comments, int isnull) {
		super();
		this.trn_id = trn_id;
		this.trn_projectnum = trn_projectnum;
		this.trn_hrznprojectnum = trn_hrznprojectnum;
		this.trn_hrznhumanitiesnum = trn_hrznhumanitiesnum;
		this.trn_vtclprojectnum = trn_vtclprojectnum;
		this.trn_vtclhumanitiesnum = trn_vtclhumanitiesnum;
		this.trn_projectcost = trn_projectcost;
		this.trn_hrznprojectcost = trn_hrznprojectcost;
		this.trn_hrznhumanitiescost = trn_hrznhumanitiescost;
		this.trn_vtclprojectcost = trn_vtclprojectcost;
		this.trn_vtclhumanitiescost = trn_vtclhumanitiescost;
		this.trn_comments = trn_comments;
		this.isnull = isnull;
	}
	public TeacherResearchNumber(Integer trn_projectnum,
			Integer trn_hrznprojectnum, Integer trn_hrznhumanitiesnum,
			Integer trn_vtclprojectnum, Integer trn_vtclhumanitiesnum,
			Float trn_projectcost, Float trn_hrznprojectcost,
			Float trn_hrznhumanitiescost, Float trn_vtclprojectcost,
			Float trn_vtclhumanitiescost, String trn_college, int isnull) {
		super();
		this.trn_projectnum = trn_projectnum;
		this.trn_hrznprojectnum = trn_hrznprojectnum;
		this.trn_hrznhumanitiesnum = trn_hrznhumanitiesnum;
		this.trn_vtclprojectnum = trn_vtclprojectnum;
		this.trn_vtclhumanitiesnum = trn_vtclhumanitiesnum;
		this.trn_projectcost = trn_projectcost;
		this.trn_hrznprojectcost = trn_hrznprojectcost;
		this.trn_hrznhumanitiescost = trn_hrznhumanitiescost;
		this.trn_vtclprojectcost = trn_vtclprojectcost;
		this.trn_vtclhumanitiescost = trn_vtclhumanitiescost;
		this.trn_college = trn_college;
		this.isnull = isnull;
	}
	
}
