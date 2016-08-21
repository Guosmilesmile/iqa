package cn.edu.xmu.entity;

import java.util.Date;

/**
 * 
 * @author Gy
 * 大类培养基本情况表 实体类
 * 全参构造函数
 * date 2015-06-29
 */
public class ClassCulture {
	
	public ClassCulture(String c_classname, String c_classnumber,
			int c_shunttime,  String c_departmentname,String c_departmentnumber, String c_majornumber,
			String c_majorname, String c_college, int isnull) {
		super();
		this.c_classname = c_classname;
		this.c_classnumber = c_classnumber;
		this.c_shunttime = c_shunttime;
		this.c_departmentname = c_departmentname;
		this.c_departmentnumber = c_departmentnumber;
		this.c_majornumber = c_majornumber;
		this.c_majorname = c_majorname;
		this.c_college = c_college;
		this.isnull = isnull;
	}
	//id
	private int c_id;
	//大类名称
	private String c_classname;
	//大类代码
	private String c_classnumber;
	//分流时间
	private int c_shunttime;
	//所属单位名称
	private String c_departmentname;
	//所属单位号
	private String c_departmentnumber;
	//包含校内专业代码
	private String c_majornumber;
	//包含校内专业名称
	private String c_majorname;
	//序列号
	private int c_serialnumber;
	//截止日期
	private Date c_deadline;
	//所属学院
	private String c_college;
	//审核意见
	private String c_comments;
	
	//记录是否存在空值
	private int isnull;
	
	public ClassCulture(String c_classname, String c_classnumber,
			int c_shunttime, String c_departmentname, String c_departmentnumber, String c_majornumber,
			String c_majorname, int c_serialnumber, Date c_deadline,
			String c_college, String c_comments, int isnull) {
		super();
		this.c_classname = c_classname;
		this.c_classnumber = c_classnumber;
		this.c_shunttime = c_shunttime;
		this.c_departmentname = c_departmentname;
		this.c_departmentnumber = c_departmentnumber;
		this.c_majornumber = c_majornumber;
		this.c_majorname = c_majorname;
		this.c_serialnumber = c_serialnumber;
		this.c_deadline = c_deadline;
		this.c_college = c_college;
		this.c_comments = c_comments;
		this.isnull = isnull;
	}
	public int getIsnull() {
		return isnull;
	}
	public void setIsnull(int isnull) {
		this.isnull = isnull;
	}
	public int getC_id() {
		return c_id;
	}
	public void setC_id(int c_id) {
		this.c_id = c_id;
	}
	public String getC_classname() {
		return c_classname;
	}
	public void setC_classname(String c_classname) {
		this.c_classname = c_classname;
	}
	public String getC_classnumber() {
		return c_classnumber;
	}
	public void setC_classnumber(String c_classnumber) {
		this.c_classnumber = c_classnumber;
	}
	public int getC_shunttime() {
		return c_shunttime;
	}
	public void setC_shunttime(int c_shunttime) {
		this.c_shunttime = c_shunttime;
	}
	public String getC_departmentname() {
		return c_departmentname;
	}
	public void setC_departmentname(String c_departmentname) {
		this.c_departmentname = c_departmentname;
	}
	public String getC_departmentnumber() {
		return c_departmentnumber;
	}
	public void setC_departmentnumber(String c_departmentnumber) {
		this.c_departmentnumber = c_departmentnumber;
	}
	public String getC_majornumber() {
		return c_majornumber;
	}
	public void setC_majornumber(String c_majornumber) {
		this.c_majornumber = c_majornumber;
	}
	public String getC_majorname() {
		return c_majorname;
	}
	public void setC_majorname(String c_majorname) {
		this.c_majorname = c_majorname;
	}
	public int getC_serialnumber() {
		return c_serialnumber;
	}
	public void setC_serialnumber(int c_serialnumber) {
		this.c_serialnumber = c_serialnumber;
	}
	public Date getC_deadline() {
		return c_deadline;
	}
	public void setC_deadline(Date c_deadline) {
		this.c_deadline = c_deadline;
	}
	public String getC_college() {
		return c_college;
	}
	public void setC_college(String c_college) {
		this.c_college = c_college;
	}
	public String getC_comments() {
		return c_comments;
	}
	public void setC_comments(String c_comments) {
		this.c_comments = c_comments;
	}
	/**
	 * 
	 * @param c_id
	 * @param c_classname
	 * @param c_classnumber
	 * @param c_shunttime
	 * @param c_departmentnumber
	 * @param c_majornumber
	 * @param c_majorname
	 * @param c_serialnumber
	 * @param c_deadline
	 * @param c_college
	 * @param c_comments
	 */
	public ClassCulture(int c_id, String c_classname, String c_classnumber,
			int c_shunttime, String c_departmentname, String c_departmentnumber, String c_majornumber,
			String c_majorname, int c_serialnumber, Date c_deadline,
			String c_college, String c_comments) {
		super();
		this.c_id = c_id;
		this.c_classname = c_classname;
		this.c_classnumber = c_classnumber;
		this.c_shunttime = c_shunttime;
		this.c_departmentname = c_departmentname;
		this.c_departmentnumber = c_departmentnumber;
		this.c_majornumber = c_majornumber;
		this.c_majorname = c_majorname;
		this.c_serialnumber = c_serialnumber;
		this.c_deadline = c_deadline;
		this.c_college = c_college;
		this.c_comments = c_comments;
	}
	public ClassCulture(String c_classname, String c_classnumber,
			int c_shunttime,  String c_departmentname, String c_departmentnumber, String c_majornumber,
			String c_majorname, int c_serialnumber, Date c_deadline,
			String c_college, String c_comments) {
		super();
		this.c_classname = c_classname;
		this.c_classnumber = c_classnumber;
		this.c_shunttime = c_shunttime;
		this.c_departmentname = c_departmentname;
		this.c_departmentnumber = c_departmentnumber;
		this.c_majornumber = c_majornumber;
		this.c_majorname = c_majorname;
		this.c_serialnumber = c_serialnumber;
		this.c_deadline = c_deadline;
		this.c_college = c_college;
		this.c_comments = c_comments;
	}
	public ClassCulture(String c_classname, String c_classnumber,
			int c_shunttime,  String c_departmentname, String c_departmentnumber, String c_majornumber,
			String c_majorname, String c_college) {
		super();
		this.c_classname = c_classname;
		this.c_classnumber = c_classnumber;
		this.c_shunttime = c_shunttime;
		this.c_departmentname = c_departmentname;
		this.c_departmentnumber = c_departmentnumber;
		this.c_majornumber = c_majornumber;
		this.c_majorname = c_majorname;
		this.c_college = c_college;
	}
	
	
}
