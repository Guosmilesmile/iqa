package cn.edu.xmu.entity;

import java.sql.Date;

/**
 * 
 * @author zsj
 * 4-2-2-1 专业基本情况
 */
public class MajorInfo {
	private int    mi_id;  //id
	private String mi_majorcodeinschool;  //校内专业代码
	private String mi_majornameinschool; //校内专业名称
	private String mi_codeversion; //代码版本
	private String mi_majorcode; //专业代码
	private String mi_majorname; //专业名称
	private String mi_majorfieldnum; //专业方向号
	private String mi_majorfieldname;  //专业方向名称
	private String mi_departmentnumber; //所属单位号
	private String mi_departmentname;//所属单位名称
	private String mi_leaderid;   //专业带头人工号
	private String mi_leadername;   //专业带头人工号
	private String mi_admissionstate;  //招生状态
	private String mi_majorspecialty;  //专业特色
	private String mi_traininggoal;   //专业培养目标
	private Integer    mi_schoolingyear;   //学制
	private String mi_setupyear;   //专业设置年份
	private String mi_isnew;   //是否新专业
	private Integer    mi_allhours;  //学时总数
	private Integer    mi_musthours;  //必修课学时
	private Integer    mi_selectedhours;  //选修课学时
	private Integer    mi_inclasshours;  //课内教学学时
	private Integer    mi_experimenthours;  //实验教学学时
	private Float  mi_allcredits;  //学分总数
	private Float  mi_mustcredits;  //必修课学分
	private Float  mi_selectedcredits;  //选修课学分
	private Float  mi_concentratedpracticecredits;   //集中性实践教学环节学分
	private Float  mi_inclasscredits;  //课内教学学分
	private Float  mi_experimentcredits;  //实验教学学分
	private Float  mi_outclassactivitycredits;  //课外科技活动学分
	private int mi_serialnumber;//序列号
	private Date mi_deadline;//截止日期
	private String mi_college;//所属学院
	private String mi_comments;//审核意见
	
	//记录是否存在空值
	private int isnull;

	public int getMi_id() {
		return mi_id;
	}

	public void setMi_id(int mi_id) {
		this.mi_id = mi_id;
	}

	public String getMi_majorcodeinschool() {
		return mi_majorcodeinschool;
	}

	public void setMi_majorcodeinschool(String mi_majorcodeinschool) {
		this.mi_majorcodeinschool = mi_majorcodeinschool;
	}

	public String getMi_majornameinschool() {
		return mi_majornameinschool;
	}

	public void setMi_majornameinschool(String mi_majornameinschool) {
		this.mi_majornameinschool = mi_majornameinschool;
	}

	public String getMi_codeversion() {
		return mi_codeversion;
	}

	public void setMi_codeversion(String mi_codeversion) {
		this.mi_codeversion = mi_codeversion;
	}

	public String getMi_majorcode() {
		return mi_majorcode;
	}

	public void setMi_majorcode(String mi_majorcode) {
		this.mi_majorcode = mi_majorcode;
	}

	public String getMi_majorname() {
		return mi_majorname;
	}

	public void setMi_majorname(String mi_majorname) {
		this.mi_majorname = mi_majorname;
	}

	public String getMi_majorfieldnum() {
		return mi_majorfieldnum;
	}

	public void setMi_majorfieldnum(String mi_majorfieldnum) {
		this.mi_majorfieldnum = mi_majorfieldnum;
	}

	public String getMi_majorfieldname() {
		return mi_majorfieldname;
	}

	public void setMi_majorfieldname(String mi_majorfieldname) {
		this.mi_majorfieldname = mi_majorfieldname;
	}

	public String getMi_departmentnumber() {
		return mi_departmentnumber;
	}

	public void setMi_departmentnumber(String mi_departmentnumber) {
		this.mi_departmentnumber = mi_departmentnumber;
	}

	public String getMi_departmentname() {
		return mi_departmentname;
	}

	public void setMi_departmentname(String mi_departmentname) {
		this.mi_departmentname = mi_departmentname;
	}

	public String getMi_leaderid() {
		return mi_leaderid;
	}

	public void setMi_leaderid(String mi_leaderid) {
		this.mi_leaderid = mi_leaderid;
	}

	public String getMi_leadername() {
		return mi_leadername;
	}

	public void setMi_leadername(String mi_leadername) {
		this.mi_leadername = mi_leadername;
	}

	public String getMi_admissionstate() {
		return mi_admissionstate;
	}

	public void setMi_admissionstate(String mi_admissionstate) {
		this.mi_admissionstate = mi_admissionstate;
	}

	public String getMi_majorspecialty() {
		return mi_majorspecialty;
	}

	public void setMi_majorspecialty(String mi_majorspecialty) {
		this.mi_majorspecialty = mi_majorspecialty;
	}

	public String getMi_traininggoal() {
		return mi_traininggoal;
	}

	public void setMi_traininggoal(String mi_traininggoal) {
		this.mi_traininggoal = mi_traininggoal;
	}

	public Integer getMi_schoolingyear() {
		return mi_schoolingyear;
	}

	public void setMi_schoolingyear(Integer mi_schoolingyear) {
		this.mi_schoolingyear = mi_schoolingyear;
	}

	public String getMi_setupyear() {
		return mi_setupyear;
	}

	public void setMi_setupyear(String mi_setupyear) {
		this.mi_setupyear = mi_setupyear;
	}

	public String getMi_isnew() {
		return mi_isnew;
	}

	public void setMi_isnew(String mi_isnew) {
		this.mi_isnew = mi_isnew;
	}

	public Integer getMi_allhours() {
		return mi_allhours;
	}

	public void setMi_allhours(Integer mi_allhours) {
		this.mi_allhours = mi_allhours;
	}

	public Integer getMi_musthours() {
		return mi_musthours;
	}

	public void setMi_musthours(Integer mi_musthours) {
		this.mi_musthours = mi_musthours;
	}

	public Integer getMi_selectedhours() {
		return mi_selectedhours;
	}

	public void setMi_selectedhours(Integer mi_selectedhours) {
		this.mi_selectedhours = mi_selectedhours;
	}

	public Integer getMi_inclasshours() {
		return mi_inclasshours;
	}

	public void setMi_inclasshours(Integer mi_inclasshours) {
		this.mi_inclasshours = mi_inclasshours;
	}

	public Integer getMi_experimenthours() {
		return mi_experimenthours;
	}

	public void setMi_experimenthours(Integer mi_experimenthours) {
		this.mi_experimenthours = mi_experimenthours;
	}

	public Float getMi_allcredits() {
		return mi_allcredits;
	}

	public void setMi_allcredits(Float mi_allcredits) {
		this.mi_allcredits = mi_allcredits;
	}

	public Float getMi_mustcredits() {
		return mi_mustcredits;
	}

	public void setMi_mustcredits(Float mi_mustcredits) {
		this.mi_mustcredits = mi_mustcredits;
	}

	public Float getMi_selectedcredits() {
		return mi_selectedcredits;
	}

	public void setMi_selectedcredits(Float mi_selectedcredits) {
		this.mi_selectedcredits = mi_selectedcredits;
	}

	public Float getMi_concentratedpracticecredits() {
		return mi_concentratedpracticecredits;
	}

	public void setMi_concentratedpracticecredits(
			Float mi_concentratedpracticecredits) {
		this.mi_concentratedpracticecredits = mi_concentratedpracticecredits;
	}

	public Float getMi_inclasscredits() {
		return mi_inclasscredits;
	}

	public void setMi_inclasscredits(Float mi_inclasscredits) {
		this.mi_inclasscredits = mi_inclasscredits;
	}

	public Float getMi_experimentcredits() {
		return mi_experimentcredits;
	}

	public void setMi_experimentcredits(Float mi_experimentcredits) {
		this.mi_experimentcredits = mi_experimentcredits;
	}

	public Float getMi_outclassactivitycredits() {
		return mi_outclassactivitycredits;
	}

	public void setMi_outclassactivitycredits(Float mi_outclassactivitycredits) {
		this.mi_outclassactivitycredits = mi_outclassactivitycredits;
	}

	public int getMi_serialnumber() {
		return mi_serialnumber;
	}

	public void setMi_serialnumber(int mi_serialnumber) {
		this.mi_serialnumber = mi_serialnumber;
	}

	public Date getMi_deadline() {
		return mi_deadline;
	}

	public void setMi_deadline(Date mi_deadline) {
		this.mi_deadline = mi_deadline;
	}

	public String getMi_college() {
		return mi_college;
	}

	public void setMi_college(String mi_college) {
		this.mi_college = mi_college;
	}

	public String getMi_comments() {
		return mi_comments;
	}

	public void setMi_comments(String mi_comments) {
		this.mi_comments = mi_comments;
	}

	public int getIsnull() {
		return isnull;
	}

	public void setIsnull(int isnull) {
		this.isnull = isnull;
	}

	public MajorInfo(int mi_id, String mi_majorcodeinschool,
			String mi_majornameinschool, String mi_codeversion,
			String mi_majorcode, String mi_majorname, String mi_majorfieldnum,
			String mi_majorfieldname, String mi_departmentnumber,
			String mi_departmentname, String mi_leaderid, String mi_leadername,
			String mi_admissionstate, String mi_majorspecialty,
			String mi_traininggoal, Integer mi_schoolingyear,
			String mi_setupyear, String mi_isnew, Integer mi_allhours,
			Integer mi_musthours, Integer mi_selectedhours,
			Integer mi_inclasshours, Integer mi_experimenthours,
			Float mi_allcredits, Float mi_mustcredits,
			Float mi_selectedcredits, Float mi_concentratedpracticecredits,
			Float mi_inclasscredits, Float mi_experimentcredits,
			Float mi_outclassactivitycredits, int mi_serialnumber,
			Date mi_deadline, String mi_college, String mi_comments, int isnull) {
		super();
		this.mi_id = mi_id;
		this.mi_majorcodeinschool = mi_majorcodeinschool;
		this.mi_majornameinschool = mi_majornameinschool;
		this.mi_codeversion = mi_codeversion;
		this.mi_majorcode = mi_majorcode;
		this.mi_majorname = mi_majorname;
		this.mi_majorfieldnum = mi_majorfieldnum;
		this.mi_majorfieldname = mi_majorfieldname;
		this.mi_departmentnumber = mi_departmentnumber;
		this.mi_departmentname = mi_departmentname;
		this.mi_leaderid = mi_leaderid;
		this.mi_leadername = mi_leadername;
		this.mi_admissionstate = mi_admissionstate;
		this.mi_majorspecialty = mi_majorspecialty;
		this.mi_traininggoal = mi_traininggoal;
		this.mi_schoolingyear = mi_schoolingyear;
		this.mi_setupyear = mi_setupyear;
		this.mi_isnew = mi_isnew;
		this.mi_allhours = mi_allhours;
		this.mi_musthours = mi_musthours;
		this.mi_selectedhours = mi_selectedhours;
		this.mi_inclasshours = mi_inclasshours;
		this.mi_experimenthours = mi_experimenthours;
		this.mi_allcredits = mi_allcredits;
		this.mi_mustcredits = mi_mustcredits;
		this.mi_selectedcredits = mi_selectedcredits;
		this.mi_concentratedpracticecredits = mi_concentratedpracticecredits;
		this.mi_inclasscredits = mi_inclasscredits;
		this.mi_experimentcredits = mi_experimentcredits;
		this.mi_outclassactivitycredits = mi_outclassactivitycredits;
		this.mi_serialnumber = mi_serialnumber;
		this.mi_deadline = mi_deadline;
		this.mi_college = mi_college;
		this.mi_comments = mi_comments;
		this.isnull = isnull;
	}

	public MajorInfo() {
		super();
	}

	public MajorInfo(String mi_majorcodeinschool, String mi_majornameinschool,
			String mi_codeversion, String mi_majorcode, String mi_majorname,
			String mi_majorfieldnum, String mi_majorfieldname,
			String mi_departmentnumber, String mi_departmentname,
			String mi_leaderid, String mi_leadername, String mi_admissionstate,
			String mi_majorspecialty, String mi_traininggoal,
			Integer mi_schoolingyear, String mi_setupyear, String mi_isnew,
			Integer mi_allhours, Integer mi_musthours,
			Integer mi_selectedhours, Integer mi_inclasshours,
			Integer mi_experimenthours, Float mi_allcredits,
			Float mi_mustcredits, Float mi_selectedcredits,
			Float mi_concentratedpracticecredits, Float mi_inclasscredits,
			Float mi_experimentcredits, Float mi_outclassactivitycredits,
			int mi_serialnumber, String mi_college, String mi_comments,
			int isnull) {
		super();
		this.mi_majorcodeinschool = mi_majorcodeinschool;
		this.mi_majornameinschool = mi_majornameinschool;
		this.mi_codeversion = mi_codeversion;
		this.mi_majorcode = mi_majorcode;
		this.mi_majorname = mi_majorname;
		this.mi_majorfieldnum = mi_majorfieldnum;
		this.mi_majorfieldname = mi_majorfieldname;
		this.mi_departmentnumber = mi_departmentnumber;
		this.mi_departmentname = mi_departmentname;
		this.mi_leaderid = mi_leaderid;
		this.mi_leadername = mi_leadername;
		this.mi_admissionstate = mi_admissionstate;
		this.mi_majorspecialty = mi_majorspecialty;
		this.mi_traininggoal = mi_traininggoal;
		this.mi_schoolingyear = mi_schoolingyear;
		this.mi_setupyear = mi_setupyear;
		this.mi_isnew = mi_isnew;
		this.mi_allhours = mi_allhours;
		this.mi_musthours = mi_musthours;
		this.mi_selectedhours = mi_selectedhours;
		this.mi_inclasshours = mi_inclasshours;
		this.mi_experimenthours = mi_experimenthours;
		this.mi_allcredits = mi_allcredits;
		this.mi_mustcredits = mi_mustcredits;
		this.mi_selectedcredits = mi_selectedcredits;
		this.mi_concentratedpracticecredits = mi_concentratedpracticecredits;
		this.mi_inclasscredits = mi_inclasscredits;
		this.mi_experimentcredits = mi_experimentcredits;
		this.mi_outclassactivitycredits = mi_outclassactivitycredits;
		this.mi_serialnumber = mi_serialnumber;
		this.mi_college = mi_college;
		this.mi_comments = mi_comments;
		this.isnull = isnull;
	}

	public MajorInfo(int mi_id, String mi_majorcodeinschool,
			String mi_majornameinschool, String mi_codeversion,
			String mi_majorcode, String mi_majorname, String mi_majorfieldnum,
			String mi_majorfieldname, String mi_departmentnumber,
			String mi_departmentname, String mi_leaderid, String mi_leadername,
			String mi_admissionstate, String mi_majorspecialty,
			String mi_traininggoal, Integer mi_schoolingyear,
			String mi_setupyear, String mi_isnew, Integer mi_allhours,
			Integer mi_musthours, Integer mi_selectedhours,
			Integer mi_inclasshours, Integer mi_experimenthours,
			Float mi_allcredits, Float mi_mustcredits,
			Float mi_selectedcredits, Float mi_concentratedpracticecredits,
			Float mi_inclasscredits, Float mi_experimentcredits,
			Float mi_outclassactivitycredits, int mi_serialnumber,
			String mi_comments, int isnull) {
		super();
		this.mi_id = mi_id;
		this.mi_majorcodeinschool = mi_majorcodeinschool;
		this.mi_majornameinschool = mi_majornameinschool;
		this.mi_codeversion = mi_codeversion;
		this.mi_majorcode = mi_majorcode;
		this.mi_majorname = mi_majorname;
		this.mi_majorfieldnum = mi_majorfieldnum;
		this.mi_majorfieldname = mi_majorfieldname;
		this.mi_departmentnumber = mi_departmentnumber;
		this.mi_departmentname = mi_departmentname;
		this.mi_leaderid = mi_leaderid;
		this.mi_leadername = mi_leadername;
		this.mi_admissionstate = mi_admissionstate;
		this.mi_majorspecialty = mi_majorspecialty;
		this.mi_traininggoal = mi_traininggoal;
		this.mi_schoolingyear = mi_schoolingyear;
		this.mi_setupyear = mi_setupyear;
		this.mi_isnew = mi_isnew;
		this.mi_allhours = mi_allhours;
		this.mi_musthours = mi_musthours;
		this.mi_selectedhours = mi_selectedhours;
		this.mi_inclasshours = mi_inclasshours;
		this.mi_experimenthours = mi_experimenthours;
		this.mi_allcredits = mi_allcredits;
		this.mi_mustcredits = mi_mustcredits;
		this.mi_selectedcredits = mi_selectedcredits;
		this.mi_concentratedpracticecredits = mi_concentratedpracticecredits;
		this.mi_inclasscredits = mi_inclasscredits;
		this.mi_experimentcredits = mi_experimentcredits;
		this.mi_outclassactivitycredits = mi_outclassactivitycredits;
		this.mi_serialnumber = mi_serialnumber;
		this.mi_comments = mi_comments;
		this.isnull = isnull;
	}

	public MajorInfo(int mi_id, String mi_majorcodeinschool,
			String mi_majornameinschool, String mi_codeversion,
			String mi_majorcode, String mi_majorname, String mi_majorfieldnum,
			String mi_majorfieldname, String mi_departmentnumber,
			String mi_departmentname, String mi_leaderid, String mi_leadername,
			String mi_admissionstate, String mi_majorspecialty,
			String mi_traininggoal, Integer mi_schoolingyear,
			String mi_setupyear, String mi_isnew, Integer mi_allhours,
			Integer mi_musthours, Integer mi_selectedhours,
			Integer mi_inclasshours, Integer mi_experimenthours,
			Float mi_allcredits, Float mi_mustcredits,
			Float mi_selectedcredits, Float mi_concentratedpracticecredits,
			Float mi_inclasscredits, Float mi_experimentcredits,
			Float mi_outclassactivitycredits, String mi_comments, int isnull) {
		super();
		this.mi_id = mi_id;
		this.mi_majorcodeinschool = mi_majorcodeinschool;
		this.mi_majornameinschool = mi_majornameinschool;
		this.mi_codeversion = mi_codeversion;
		this.mi_majorcode = mi_majorcode;
		this.mi_majorname = mi_majorname;
		this.mi_majorfieldnum = mi_majorfieldnum;
		this.mi_majorfieldname = mi_majorfieldname;
		this.mi_departmentnumber = mi_departmentnumber;
		this.mi_departmentname = mi_departmentname;
		this.mi_leaderid = mi_leaderid;
		this.mi_leadername = mi_leadername;
		this.mi_admissionstate = mi_admissionstate;
		this.mi_majorspecialty = mi_majorspecialty;
		this.mi_traininggoal = mi_traininggoal;
		this.mi_schoolingyear = mi_schoolingyear;
		this.mi_setupyear = mi_setupyear;
		this.mi_isnew = mi_isnew;
		this.mi_allhours = mi_allhours;
		this.mi_musthours = mi_musthours;
		this.mi_selectedhours = mi_selectedhours;
		this.mi_inclasshours = mi_inclasshours;
		this.mi_experimenthours = mi_experimenthours;
		this.mi_allcredits = mi_allcredits;
		this.mi_mustcredits = mi_mustcredits;
		this.mi_selectedcredits = mi_selectedcredits;
		this.mi_concentratedpracticecredits = mi_concentratedpracticecredits;
		this.mi_inclasscredits = mi_inclasscredits;
		this.mi_experimentcredits = mi_experimentcredits;
		this.mi_outclassactivitycredits = mi_outclassactivitycredits;
		this.mi_comments = mi_comments;
		this.isnull = isnull;
	}

	public MajorInfo(String mi_majorcodeinschool, String mi_majornameinschool,
			String mi_codeversion, String mi_majorcode, String mi_majorname,
			String mi_majorfieldnum, String mi_majorfieldname,
			String mi_departmentnumber, String mi_departmentname,
			String mi_leaderid, String mi_leadername, String mi_admissionstate,
			String mi_majorspecialty, String mi_traininggoal,
			Integer mi_schoolingyear, String mi_setupyear, String mi_isnew,
			Integer mi_allhours, Integer mi_musthours,
			Integer mi_selectedhours, Integer mi_inclasshours,
			Integer mi_experimenthours, Float mi_allcredits,
			Float mi_mustcredits, Float mi_selectedcredits,
			Float mi_concentratedpracticecredits, Float mi_inclasscredits,
			Float mi_experimentcredits, Float mi_outclassactivitycredits,
			String mi_college, int isnull) {
		super();
		this.mi_majorcodeinschool = mi_majorcodeinschool;
		this.mi_majornameinschool = mi_majornameinschool;
		this.mi_codeversion = mi_codeversion;
		this.mi_majorcode = mi_majorcode;
		this.mi_majorname = mi_majorname;
		this.mi_majorfieldnum = mi_majorfieldnum;
		this.mi_majorfieldname = mi_majorfieldname;
		this.mi_departmentnumber = mi_departmentnumber;
		this.mi_departmentname = mi_departmentname;
		this.mi_leaderid = mi_leaderid;
		this.mi_leadername = mi_leadername;
		this.mi_admissionstate = mi_admissionstate;
		this.mi_majorspecialty = mi_majorspecialty;
		this.mi_traininggoal = mi_traininggoal;
		this.mi_schoolingyear = mi_schoolingyear;
		this.mi_setupyear = mi_setupyear;
		this.mi_isnew = mi_isnew;
		this.mi_allhours = mi_allhours;
		this.mi_musthours = mi_musthours;
		this.mi_selectedhours = mi_selectedhours;
		this.mi_inclasshours = mi_inclasshours;
		this.mi_experimenthours = mi_experimenthours;
		this.mi_allcredits = mi_allcredits;
		this.mi_mustcredits = mi_mustcredits;
		this.mi_selectedcredits = mi_selectedcredits;
		this.mi_concentratedpracticecredits = mi_concentratedpracticecredits;
		this.mi_inclasscredits = mi_inclasscredits;
		this.mi_experimentcredits = mi_experimentcredits;
		this.mi_outclassactivitycredits = mi_outclassactivitycredits;
		this.mi_college = mi_college;
		this.isnull = isnull;
	}

	
}
