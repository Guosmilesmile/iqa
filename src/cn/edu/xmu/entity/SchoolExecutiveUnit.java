package cn.edu.xmu.entity;

import java.util.Date;



/**
 * 
 * @author Lee
 * 学校相关行政单位 实体类
 * 全参构造函数
 * date 2015-06-29
 *
 */
public class SchoolExecutiveUnit {

	private int seu_id;
	//行政单位名称
	private String seu_departmentname;
	
	//单位号
	private String seu_departmentnumber;
	
	//单位职能
	private String seu_function;
	
	//单位负责人
	private String seu_bosshead;
	
	//序列号
	private int seu_serialnumber;
	//截止日期
	private Date seu_deadline;
	//所属学院
	private String seu_college;
	//审核意见
	private String seu_comments;
	//记录是否存在空值
	private int isnull;
	
	
	public int getSeu_id() {
		return seu_id;
	}
	public void setSeu_id(int seu_id) {
		this.seu_id = seu_id;
	}
	public String getSeu_departmentname() {
		return seu_departmentname;
	}
	public void setSeu_departmentname(String seu_departmentname) {
		this.seu_departmentname = seu_departmentname;
	}
	public String getSeu_departmentnumber() {
		return seu_departmentnumber;
	}
	public void setSeu_departmentnumber(String seu_departmentnumber) {
		this.seu_departmentnumber = seu_departmentnumber;
	}
	public String getSeu_function() {
		return seu_function;
	}
	public void setSeu_function(String seu_function) {
		this.seu_function = seu_function;
	}
	public String getSeu_bosshead() {
		return seu_bosshead;
	}
	public void setSeu_bosshead(String seu_bosshead) {
		this.seu_bosshead = seu_bosshead;
	}
	public int getSeu_serialnumber() {
		return seu_serialnumber;
	}
	public void setSeu_serialnumber(int seu_serialnumber) {
		this.seu_serialnumber = seu_serialnumber;
	}
	public Date getSeu_deadline() {
		return seu_deadline;
	}
	public void setSeu_deadline(Date seu_deadline) {
		this.seu_deadline = seu_deadline;
	}
	public String getSeu_college() {
		return seu_college;
	}
	public void setSeu_college(String seu_college) {
		this.seu_college = seu_college;
	}
	public String getSeu_comments() {
		return seu_comments;
	}
	public void setSeu_comments(String seu_comments) {
		this.seu_comments = seu_comments;
	}
	
	public int getIsnull() {
		return isnull;
	}

	public void setIsnull(int isnull) {
		this.isnull = isnull;
	}
	//构造函数
	public SchoolExecutiveUnit() {
		super();
	}
	/**
	 * 
	 * @param seu_id
	 * @param seu_departmentname
	 * @param seu_departmentnumber
	 * @param seu_function
	 * @param seu_bosshead
	 * @param seu_serialnumber
	 * @param seu_deadline
	 * @param seu_college
	 * @param seu_comments
	 * 全参数
	 */
	public SchoolExecutiveUnit(int seu_id, String seu_departmentname,
			String seu_departmentnumber, String seu_function,
			String seu_bosshead, int seu_serialnumber, Date seu_deadline,
			String seu_college, String seu_comments, int isnull) {
		super();
		this.seu_id = seu_id;
		this.seu_departmentname = seu_departmentname;
		this.seu_departmentnumber = seu_departmentnumber;
		this.seu_function = seu_function;
		this.seu_bosshead = seu_bosshead;
		this.seu_serialnumber = seu_serialnumber;
		this.seu_deadline = seu_deadline;
		this.seu_college = seu_college;
		this.seu_comments = seu_comments;
		this.isnull = isnull;
	}
	
	/**
	 * 
	 * @param seu_id
	 * @param seu_departmentname
	 * @param seu_departmentnumber
	 * @param seu_function
	 * @param seu_bosshead
	 * @param seu_comments
	 */
	public SchoolExecutiveUnit(int seu_id, String seu_departmentname,
			String seu_departmentnumber, String seu_function,
			String seu_bosshead, int seu_serialnumber,String seu_comments, int isnull) {
		super();
		this.seu_id = seu_id;
		this.seu_departmentname = seu_departmentname;
		this.seu_departmentnumber = seu_departmentnumber;
		this.seu_function = seu_function;
		this.seu_bosshead = seu_bosshead;
		this.seu_serialnumber = seu_serialnumber;
		this.seu_comments = seu_comments;
		this.isnull = isnull;
	}
	public SchoolExecutiveUnit(String seu_departmentname,
			String seu_departmentnumber, String seu_function,
			String seu_bosshead, int seu_serialnumber, String seu_college,
			 int isnull) {
		super();
		this.seu_departmentname = seu_departmentname;
		this.seu_departmentnumber = seu_departmentnumber;
		this.seu_function = seu_function;
		this.seu_bosshead = seu_bosshead;
		this.seu_serialnumber = seu_serialnumber;
		this.seu_college = seu_college;
		this.isnull = isnull;
	}
	
	public SchoolExecutiveUnit(String seu_departmentname,
			String seu_departmentnumber, String seu_function,
			String seu_bosshead,  String seu_college,
			 int isnull) {
		super();
		this.seu_departmentname = seu_departmentname;
		this.seu_departmentnumber = seu_departmentnumber;
		this.seu_function = seu_function;
		this.seu_bosshead = seu_bosshead;
		this.seu_college = seu_college;
		this.isnull = isnull;
	}
	
	
	
	
}
