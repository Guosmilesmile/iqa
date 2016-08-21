package cn.edu.xmu.entity;

import java.util.Date;

public class GraduationSituation {
	private int gs_id;
	//学院
	private String gs_importcollege;
	//专业名称
	private String gs_major;
	//专业代码
	private String gs_majorcode;
	//学制
	private Integer gs_eductionalsystme;
	//学位类别
	private String gs_degreecategory;
	//毕业班人数
	private Integer gs_graduationnumber;
	//小计
	private Integer gs_total;
	//授予学位数
	private Integer gs_degreeyes;
	//不授予学位数
	private Integer gs_degreeno;
	//结业数
	private Integer gs_numberofcompletion;
	//肄业数
	private Integer gs_numberofincomplete;
	//获得辅修专业证书数
	private Integer gs_minorcertificatecount;
	//获得辅修学位人数
	private Integer gs_minordegreecount;
	//序列号
	private int gs_serialnumber;
	//截止日期
	private Date gs_deadline;
	//所属学院
	private String gs_college;
	//审核意见
	private String gs_comments;
	
	private int isnull;

	public GraduationSituation(int gs_id, String gs_importcollege,
			String gs_major, String gs_majorcode, Integer gs_eductionalsystme,
			String gs_degreecategory, Integer gs_graduationnumber,
			Integer gs_total, Integer gs_degreeyes, Integer gs_degreeno,
			Integer gs_numberofcompletion, Integer gs_numberofincomplete,
			Integer gs_minorcertificatecount, Integer gs_minordegreecount,
			int gs_serialnumber, Date gs_deadline, String gs_college,
			String gs_comments, int isnull) {
		super();
		this.gs_id = gs_id;
		this.gs_importcollege = gs_importcollege;
		this.gs_major = gs_major;
		this.gs_majorcode = gs_majorcode;
		this.gs_eductionalsystme = gs_eductionalsystme;
		this.gs_degreecategory = gs_degreecategory;
		this.gs_graduationnumber = gs_graduationnumber;
		this.gs_total = gs_total;
		this.gs_degreeyes = gs_degreeyes;
		this.gs_degreeno = gs_degreeno;
		this.gs_numberofcompletion = gs_numberofcompletion;
		this.gs_numberofincomplete = gs_numberofincomplete;
		this.gs_minorcertificatecount = gs_minorcertificatecount;
		this.gs_minordegreecount = gs_minordegreecount;
		this.gs_serialnumber = gs_serialnumber;
		this.gs_deadline = gs_deadline;
		this.gs_college = gs_college;
		this.gs_comments = gs_comments;
		this.isnull = isnull;
	}

	public GraduationSituation(String gs_importcollege, String gs_major,
			String gs_majorcode, Integer gs_eductionalsystme,
			String gs_degreecategory, Integer gs_graduationnumber,
			Integer gs_total, Integer gs_degreeyes, Integer gs_degreeno,
			Integer gs_numberofcompletion, Integer gs_numberofincomplete,
			Integer gs_minorcertificatecount, Integer gs_minordegreecount,
			int gs_serialnumber, String gs_college, String gs_comments,
			int isnull) {
		super();
		this.gs_importcollege = gs_importcollege;
		this.gs_major = gs_major;
		this.gs_majorcode = gs_majorcode;
		this.gs_eductionalsystme = gs_eductionalsystme;
		this.gs_degreecategory = gs_degreecategory;
		this.gs_graduationnumber = gs_graduationnumber;
		this.gs_total = gs_total;
		this.gs_degreeyes = gs_degreeyes;
		this.gs_degreeno = gs_degreeno;
		this.gs_numberofcompletion = gs_numberofcompletion;
		this.gs_numberofincomplete = gs_numberofincomplete;
		this.gs_minorcertificatecount = gs_minorcertificatecount;
		this.gs_minordegreecount = gs_minordegreecount;
		this.gs_serialnumber = gs_serialnumber;
		this.gs_college = gs_college;
		this.gs_comments = gs_comments;
		this.isnull = isnull;
	}

	public GraduationSituation(int gs_id, String gs_importcollege,
			String gs_major, String gs_majorcode, Integer gs_eductionalsystme,
			String gs_degreecategory, Integer gs_graduationnumber,
			Integer gs_total, Integer gs_degreeyes, Integer gs_degreeno,
			Integer gs_numberofcompletion, Integer gs_numberofincomplete,
			Integer gs_minorcertificatecount, Integer gs_minordegreecount,
			int gs_serialnumber, String gs_comments, int isnull) {
		super();
		this.gs_id = gs_id;
		this.gs_importcollege = gs_importcollege;
		this.gs_major = gs_major;
		this.gs_majorcode = gs_majorcode;
		this.gs_eductionalsystme = gs_eductionalsystme;
		this.gs_degreecategory = gs_degreecategory;
		this.gs_graduationnumber = gs_graduationnumber;
		this.gs_total = gs_total;
		this.gs_degreeyes = gs_degreeyes;
		this.gs_degreeno = gs_degreeno;
		this.gs_numberofcompletion = gs_numberofcompletion;
		this.gs_numberofincomplete = gs_numberofincomplete;
		this.gs_minorcertificatecount = gs_minorcertificatecount;
		this.gs_minordegreecount = gs_minordegreecount;
		this.gs_serialnumber = gs_serialnumber;
		this.gs_comments = gs_comments;
		this.isnull = isnull;
	}

	public GraduationSituation(String gs_importcollege, String gs_major,
			String gs_majorcode, Integer gs_eductionalsystme,
			String gs_degreecategory, Integer gs_graduationnumber,
			Integer gs_total, Integer gs_degreeyes, Integer gs_degreeno,
			Integer gs_numberofcompletion, Integer gs_numberofincomplete,
			Integer gs_minorcertificatecount, Integer gs_minordegreecount,
			String gs_college, int isnull) {
		super();
		this.gs_importcollege = gs_importcollege;
		this.gs_major = gs_major;
		this.gs_majorcode = gs_majorcode;
		this.gs_eductionalsystme = gs_eductionalsystme;
		this.gs_degreecategory = gs_degreecategory;
		this.gs_graduationnumber = gs_graduationnumber;
		this.gs_total = gs_total;
		this.gs_degreeyes = gs_degreeyes;
		this.gs_degreeno = gs_degreeno;
		this.gs_numberofcompletion = gs_numberofcompletion;
		this.gs_numberofincomplete = gs_numberofincomplete;
		this.gs_minorcertificatecount = gs_minorcertificatecount;
		this.gs_minordegreecount = gs_minordegreecount;
		this.gs_college = gs_college;
		this.isnull = isnull;
	}

	public int getGs_id() {
		return gs_id;
	}

	public void setGs_id(int gs_id) {
		this.gs_id = gs_id;
	}

	public String getGs_importcollege() {
		return gs_importcollege;
	}

	public void setGs_importcollege(String gs_importcollege) {
		this.gs_importcollege = gs_importcollege;
	}

	public String getGs_major() {
		return gs_major;
	}

	public void setGs_major(String gs_major) {
		this.gs_major = gs_major;
	}

	public String getGs_majorcode() {
		return gs_majorcode;
	}

	public void setGs_majorcode(String gs_majorcode) {
		this.gs_majorcode = gs_majorcode;
	}

	public Integer getGs_eductionalsystme() {
		return gs_eductionalsystme;
	}

	public void setGs_eductionalsystme(Integer gs_eductionalsystme) {
		this.gs_eductionalsystme = gs_eductionalsystme;
	}

	public String getGs_degreecategory() {
		return gs_degreecategory;
	}

	public void setGs_degreecategory(String gs_degreecategory) {
		this.gs_degreecategory = gs_degreecategory;
	}

	public Integer getGs_graduationnumber() {
		return gs_graduationnumber;
	}

	public void setGs_graduationnumber(Integer gs_graduationnumber) {
		this.gs_graduationnumber = gs_graduationnumber;
	}

	public Integer getGs_total() {
		return gs_total;
	}

	public void setGs_total(Integer gs_total) {
		this.gs_total = gs_total;
	}

	public Integer getGs_degreeyes() {
		return gs_degreeyes;
	}

	public void setGs_degreeyes(Integer gs_degreeyes) {
		this.gs_degreeyes = gs_degreeyes;
	}

	public Integer getGs_degreeno() {
		return gs_degreeno;
	}

	public void setGs_degreeno(Integer gs_degreeno) {
		this.gs_degreeno = gs_degreeno;
	}

	public Integer getGs_numberofcompletion() {
		return gs_numberofcompletion;
	}

	public void setGs_numberofcompletion(Integer gs_numberofcompletion) {
		this.gs_numberofcompletion = gs_numberofcompletion;
	}

	public Integer getGs_numberofincomplete() {
		return gs_numberofincomplete;
	}

	public void setGs_numberofincomplete(Integer gs_numberofincomplete) {
		this.gs_numberofincomplete = gs_numberofincomplete;
	}

	public Integer getGs_minorcertificatecount() {
		return gs_minorcertificatecount;
	}

	public void setGs_minorcertificatecount(Integer gs_minorcertificatecount) {
		this.gs_minorcertificatecount = gs_minorcertificatecount;
	}

	public Integer getGs_minordegreecount() {
		return gs_minordegreecount;
	}

	public void setGs_minordegreecount(Integer gs_minordegreecount) {
		this.gs_minordegreecount = gs_minordegreecount;
	}

	public int getGs_serialnumber() {
		return gs_serialnumber;
	}

	public void setGs_serialnumber(int gs_serialnumber) {
		this.gs_serialnumber = gs_serialnumber;
	}

	public Date getGs_deadline() {
		return gs_deadline;
	}

	public void setGs_deadline(Date gs_deadline) {
		this.gs_deadline = gs_deadline;
	}

	public String getGs_college() {
		return gs_college;
	}

	public void setGs_college(String gs_college) {
		this.gs_college = gs_college;
	}

	public String getGs_comments() {
		return gs_comments;
	}

	public void setGs_comments(String gs_comments) {
		this.gs_comments = gs_comments;
	}

	public int getIsnull() {
		return isnull;
	}

	public void setIsnull(int isnull) {
		this.isnull = isnull;
	}

	
	
}
