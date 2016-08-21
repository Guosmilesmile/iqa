package cn.edu.xmu.entity;

import java.util.Date;

/**
 * 表5-3-3 2014-2015学年分专业（大类）实验情况
 * 
 * @author Gy
 * 
 */
public class CategoryExperiment {

	private int ce_id;
	private String ce_colleges;
	private String ce_majorname;
	private Integer ce_containexperiment;// 有实验的课程（门）
	private Integer ce_singleexperiment;// 独立设置的实验课程（门）
	private Integer ce_nosingleexperiment;// 非独立设置的实验课（门）
	private Integer ce_designexperiment;// 其中综合性、设计性实验教学（门）
	private Float ce_percentage;// 实验开出率（%）
	private String ce_majornumber;// 专业代码
	private int ce_serialnumber;// 序列号
	private Date ce_deadline;// 截止日期
	private String ce_college;// 所属学院
	private String ce_comments;// 审核
	private int isnull;

	public CategoryExperiment(String ce_colleges, String ce_majorname,
			Integer ce_containexperiment, Integer ce_singleexperiment,
			Integer ce_nosingleexperiment, Integer ce_designexperiment, Float ce_percentage,
			String ce_majornumber, String ce_college, int isnull) {
		super();
		this.ce_colleges = ce_colleges;
		this.ce_majorname = ce_majorname;
		this.ce_containexperiment = ce_containexperiment;
		this.ce_singleexperiment = ce_singleexperiment;
		this.ce_nosingleexperiment = ce_nosingleexperiment;
		this.ce_designexperiment = ce_designexperiment;
		this.ce_percentage = ce_percentage;
		this.ce_majornumber = ce_majornumber;
		this.ce_college = ce_college;
		this.isnull = isnull;
	}

	public void setCe_majornumber(String ce_majornumber) {
		this.ce_majornumber = ce_majornumber;
	}

	public CategoryExperiment(String ce_colleges, String ce_majorname,
			Integer ce_containexperiment, Integer ce_singleexperiment,
			Integer ce_nosingleexperiment, Integer ce_designexperiment, Float ce_percentage,
			String ce_majornumber, String ce_college) {
		super();
		this.ce_colleges = ce_colleges;
		this.ce_majorname = ce_majorname;
		this.ce_containexperiment = ce_containexperiment;
		this.ce_singleexperiment = ce_singleexperiment;
		this.ce_nosingleexperiment = ce_nosingleexperiment;
		this.ce_designexperiment = ce_designexperiment;
		this.ce_percentage = ce_percentage;
		this.ce_majornumber = ce_majornumber;
		this.ce_college = ce_college;
	}

	public int getIsnull() {
		return isnull;
	}

	public void setIsnull(int isnull) {
		this.isnull = isnull;
	}

	public CategoryExperiment(String ce_colleges, String ce_majorname,
			Integer ce_containexperiment, Integer ce_singleexperiment,
			Integer ce_nosingleexperiment, Integer ce_designexperiment, Float ce_percentage,
			String ce_majornumber, int ce_serialnumber, String ce_college, int isnull) {
		super();
		this.ce_colleges = ce_colleges;
		this.ce_majorname = ce_majorname;
		this.ce_containexperiment = ce_containexperiment;
		this.ce_singleexperiment = ce_singleexperiment;
		this.ce_nosingleexperiment = ce_nosingleexperiment;
		this.ce_designexperiment = ce_designexperiment;
		this.ce_percentage = ce_percentage;
		this.ce_serialnumber = ce_serialnumber;
		this.ce_college = ce_college;
		this.isnull = isnull;
		this.ce_majornumber = ce_majornumber;
	}

	public CategoryExperiment() {
		super();
	}

	public CategoryExperiment(int ce_id, String ce_colleges, String ce_majorname,
			Integer ce_containexperiment, Integer ce_singleexperiment,
			Integer ce_nosingleexperiment, Integer ce_designexperiment, Float ce_percentage,
			String ce_majornumber, int ce_serialnumber, String ce_comments) {
		super();
		this.ce_id = ce_id;
		this.ce_colleges = ce_colleges;
		this.ce_majorname = ce_majorname;
		this.ce_containexperiment = ce_containexperiment;
		this.ce_singleexperiment = ce_singleexperiment;
		this.ce_nosingleexperiment = ce_nosingleexperiment;
		this.ce_designexperiment = ce_designexperiment;
		this.ce_percentage = ce_percentage;
		this.ce_serialnumber = ce_serialnumber;
		this.ce_comments = ce_comments;
		this.ce_majornumber = ce_majornumber;
	}

	public CategoryExperiment(String ce_colleges, String ce_majorname,
			Integer ce_containexperiment, Integer ce_singleexperiment,
			Integer ce_nosingleexperiment, Integer ce_designexperiment, Float ce_percentage,
			String ce_majornumber, int ce_serialnumber, String ce_college) {
		super();
		this.ce_colleges = ce_colleges;
		this.ce_majorname = ce_majorname;
		this.ce_containexperiment = ce_containexperiment;
		this.ce_singleexperiment = ce_singleexperiment;
		this.ce_nosingleexperiment = ce_nosingleexperiment;
		this.ce_designexperiment = ce_designexperiment;
		this.ce_percentage = ce_percentage;
		this.ce_serialnumber = ce_serialnumber;
		this.ce_college = ce_college;
		this.ce_majornumber = ce_majornumber;
	}

	public CategoryExperiment(int ce_id, String ce_colleges, String ce_majorname,
			Integer ce_containexperiment, Integer ce_singleexperiment,
			Integer ce_nosingleexperiment, Integer ce_designexperiment, Float ce_percentage,
			String ce_majornumber, int ce_serialnumber, Date ce_deadline, String ce_college,
			String ce_comments) {
		super();
		this.ce_id = ce_id;
		this.ce_colleges = ce_colleges;
		this.ce_majorname = ce_majorname;
		this.ce_containexperiment = ce_containexperiment;
		this.ce_singleexperiment = ce_singleexperiment;
		this.ce_nosingleexperiment = ce_nosingleexperiment;
		this.ce_designexperiment = ce_designexperiment;
		this.ce_percentage = ce_percentage;
		this.ce_serialnumber = ce_serialnumber;
		this.ce_deadline = ce_deadline;
		this.ce_college = ce_college;
		this.ce_comments = ce_comments;
		this.ce_majornumber = ce_majornumber;
	}

	public CategoryExperiment(int ce_id, String ce_colleges, String ce_majorname,
			Integer ce_containexperiment, Integer ce_singleexperiment, Integer ce_nosingleexperiment,
			Integer ce_designexperiment, Float ce_percentage, String ce_majornumber, int ce_serialnumber,
			String ce_college, String ce_comments) {
		super();
		this.ce_id = ce_id;
		this.ce_colleges = ce_colleges;
		this.ce_majorname = ce_majorname;
		this.ce_containexperiment = ce_containexperiment;
		this.ce_singleexperiment = ce_singleexperiment;
		this.ce_nosingleexperiment = ce_nosingleexperiment;
		this.ce_designexperiment = ce_designexperiment;
		this.ce_percentage = ce_percentage;
		this.ce_serialnumber = ce_serialnumber;
		this.ce_college = ce_college;
		this.ce_comments = ce_comments;
		this.ce_majornumber = ce_majornumber;
	}

	public String getCe_majornumber() {
		return ce_majornumber;
	}

	public void setCe_marjornumber(String ce_majornumber) {
		this.ce_majornumber = ce_majornumber;
	}

	public int getCe_id() {
		return ce_id;
	}

	public void setCe_id(int ce_id) {
		this.ce_id = ce_id;
	}

	public String getCe_colleges() {
		return ce_colleges;
	}

	public void setCe_colleges(String ce_colleges) {
		this.ce_colleges = ce_colleges;
	}

	public String getCe_majorname() {
		return ce_majorname;
	}

	public void setCe_majorname(String ce_majorname) {
		this.ce_majorname = ce_majorname;
	}

	public Integer getCe_containexperiment() {
		return ce_containexperiment;
	}

	public void setCe_containexperiment(Integer ce_containexperiment) {
		this.ce_containexperiment = ce_containexperiment;
	}

	public Integer getCe_singleexperiment() {
		return ce_singleexperiment;
	}

	public void setCe_singleexperiment(Integer ce_singleexperiment) {
		this.ce_singleexperiment = ce_singleexperiment;
	}

	public Integer getCe_nosingleexperiment() {
		return ce_nosingleexperiment;
	}

	public void setCe_nosingleexperiment(Integer ce_nosingleexperiment) {
		this.ce_nosingleexperiment = ce_nosingleexperiment;
	}

	public Integer getCe_designexperiment() {
		return ce_designexperiment;
	}

	public void setCe_designexperiment(Integer ce_designexperiment) {
		this.ce_designexperiment = ce_designexperiment;
	}

	public Float getCe_percentage() {
		return ce_percentage;
	}

	public void setCe_percentage(Float ce_percentage) {
		this.ce_percentage = ce_percentage;
	}

	public int getCe_serialnumber() {
		return ce_serialnumber;
	}

	public void setCe_serialnumber(int ce_serialnumber) {
		this.ce_serialnumber = ce_serialnumber;
	}

	public Date getCe_deadline() {
		return ce_deadline;
	}

	public void setCe_deadline(Date ce_deadline) {
		this.ce_deadline = ce_deadline;
	}

	public String getCe_college() {
		return ce_college;
	}

	public void setCe_college(String ce_college) {
		this.ce_college = ce_college;
	}

	public String getCe_comments() {
		return ce_comments;
	}

	public void setCe_comments(String ce_comments) {
		this.ce_comments = ce_comments;
	}

}
