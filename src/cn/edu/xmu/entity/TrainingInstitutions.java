package cn.edu.xmu.entity;

import java.util.Date;

/**
 * 附表3-5-1-2教师教学发展机构培训情况（学年）
 * @author Gy
 *
 */
public class TrainingInstitutions {
	
	public TrainingInstitutions(int ti_id, String ti_name,
			String ti_departmentnumber, String ti_departmentname,
			String ti_projectname, String ti_object, Date ti_time,
			int ti_peoplecount, String ti_college, int isnull) {
		super();
		this.ti_id = ti_id;
		this.ti_name = ti_name;
		this.ti_departmentnumber = ti_departmentnumber;
		this.ti_departmentname = ti_departmentname;
		this.ti_projectname = ti_projectname;
		this.ti_object = ti_object;
		this.ti_time = ti_time;
		this.ti_peoplecount = ti_peoplecount;
		this.ti_college = ti_college;
		this.isnull = isnull;
	}

	public TrainingInstitutions(String ti_name, String ti_departmentnumber,
			String ti_departmentname, String ti_projectname, String ti_object,
			Date ti_time, int ti_peoplecount, String ti_college, int isnull) {
		super();
		this.ti_name = ti_name;
		this.ti_departmentnumber = ti_departmentnumber;
		this.ti_departmentname = ti_departmentname;
		this.ti_projectname = ti_projectname;
		this.ti_object = ti_object;
		this.ti_time = ti_time;
		this.ti_peoplecount = ti_peoplecount;
		this.ti_college = ti_college;
		this.isnull = isnull;
	}

	public TrainingInstitutions(String ti_name, String ti_departmentnumber,
			String ti_departmentname, String ti_projectname, String ti_object,
			Date ti_time, int ti_peoplecount, int ti_serialnumber,
			String ti_college, String ti_comments, int isnull) {
		super();
		this.ti_name = ti_name;
		this.ti_departmentnumber = ti_departmentnumber;
		this.ti_departmentname = ti_departmentname;
		this.ti_projectname = ti_projectname;
		this.ti_object = ti_object;
		this.ti_time = ti_time;
		this.ti_peoplecount = ti_peoplecount;
		this.ti_serialnumber = ti_serialnumber;
		this.ti_college = ti_college;
		this.ti_comments = ti_comments;
		this.isnull = isnull;
	}

	private int ti_id;
	private String ti_name;//机构名称
	private String ti_departmentnumber;//单位号
	private String ti_departmentname;//单位名
	private String ti_projectname;//培养项目名称
	private String ti_object;//培训对象
	private Date ti_time;
	private int ti_peoplecount;//培训人次
	private int	ti_serialnumber;//序列号
	private Date ti_deadline;//截止日期
	private String ti_college;//所属学院
	private String ti_comments;//审核
	private int isnull;
	public int getIsnull() {
		return isnull;
	}

	public void setIsnull(int isnull) {
		this.isnull = isnull;
	}

	public TrainingInstitutions() {
		super();
	}

	public TrainingInstitutions(String ti_name, String ti_departmentnumber,
			String ti_departmentname, String ti_projectname, String ti_object,
			Date ti_time, int ti_peoplecount, int ti_serialnumber,
			String ti_college, int isnull) {
		super();
		this.ti_name = ti_name;
		this.ti_departmentnumber = ti_departmentnumber;
		this.ti_departmentname = ti_departmentname;
		this.ti_projectname = ti_projectname;
		this.ti_object = ti_object;
		this.ti_time = ti_time;
		this.ti_peoplecount = ti_peoplecount;
		this.ti_serialnumber = ti_serialnumber;
		this.ti_college = ti_college;
		this.isnull = isnull;
	}

	public TrainingInstitutions(int ti_id, String ti_name,
			String ti_departmentnumber, String ti_departmentname,
			String ti_projectname, String ti_object, Date ti_time,
			int ti_peoplecount, String ti_college) {
		super();
		this.ti_id = ti_id;
		this.ti_name = ti_name;
		this.ti_departmentnumber = ti_departmentnumber;
		this.ti_departmentname = ti_departmentname;
		this.ti_projectname = ti_projectname;
		this.ti_object = ti_object;
		this.ti_time = ti_time;
		this.ti_peoplecount = ti_peoplecount;
		this.ti_college = ti_college;
	}

	public TrainingInstitutions(int ti_id, String ti_name,
			String ti_departmentnumber, String ti_departmentname,
			String ti_projectname, String ti_object, Date ti_time,
			int ti_peoplecount, int ti_serialnumber, Date ti_deadline,
			String ti_college, String ti_comments) {
		super();
		this.ti_id = ti_id;
		this.ti_name = ti_name;
		this.ti_departmentnumber = ti_departmentnumber;
		this.ti_departmentname = ti_departmentname;
		this.ti_projectname = ti_projectname;
		this.ti_object = ti_object;
		this.ti_time = ti_time;
		this.ti_peoplecount = ti_peoplecount;
		this.ti_serialnumber = ti_serialnumber;
		this.ti_deadline = ti_deadline;
		this.ti_college = ti_college;
		this.ti_comments = ti_comments;
	}

	public TrainingInstitutions(String ti_name, String ti_departmentnumber,
			String ti_departmentname, String ti_projectname, String ti_object,
			Date ti_time, int ti_peoplecount, int ti_serialnumber,
			String ti_college, String ti_comments) {
		super();
		this.ti_name = ti_name;
		this.ti_departmentnumber = ti_departmentnumber;
		this.ti_departmentname = ti_departmentname;
		this.ti_projectname = ti_projectname;
		this.ti_object = ti_object;
		this.ti_time = ti_time;
		this.ti_peoplecount = ti_peoplecount;
		this.ti_serialnumber = ti_serialnumber;
		this.ti_college = ti_college;
		this.ti_comments = ti_comments;
	}

	public int getTi_id() {
		return ti_id;
	}

	public void setTi_id(int ti_id) {
		this.ti_id = ti_id;
	}

	public String getTi_name() {
		return ti_name;
	}

	public void setTi_name(String ti_name) {
		this.ti_name = ti_name;
	}

	public String getTi_departmentnumber() {
		return ti_departmentnumber;
	}

	public void setTi_departmentnumber(String ti_departmentnumber) {
		this.ti_departmentnumber = ti_departmentnumber;
	}

	public String getTi_departmentname() {
		return ti_departmentname;
	}

	public void setTi_departmentname(String ti_departmentname) {
		this.ti_departmentname = ti_departmentname;
	}

	public String getTi_projectname() {
		return ti_projectname;
	}

	public void setTi_projectname(String ti_projectname) {
		this.ti_projectname = ti_projectname;
	}

	public String getTi_object() {
		return ti_object;
	}

	public void setTi_object(String ti_object) {
		this.ti_object = ti_object;
	}

	public Date getTi_time() {
		return ti_time;
	}

	public void setTi_time(Date ti_time) {
		this.ti_time = ti_time;
	}

	public int getTi_peoplecount() {
		return ti_peoplecount;
	}

	public void setTi_peoplecount(int ti_peoplecount) {
		this.ti_peoplecount = ti_peoplecount;
	}

	public int getTi_serialnumber() {
		return ti_serialnumber;
	}

	public void setTi_serialnumber(int ti_serialnumber) {
		this.ti_serialnumber = ti_serialnumber;
	}

	public Date getTi_deadline() {
		return ti_deadline;
	}

	public void setTi_deadline(Date ti_deadline) {
		this.ti_deadline = ti_deadline;
	}

	public String getTi_college() {
		return ti_college;
	}

	public void setTi_college(String ti_college) {
		this.ti_college = ti_college;
	}

	public String getTi_comments() {
		return ti_comments;
	}

	public void setTi_comments(String ti_comments) {
		this.ti_comments = ti_comments;
	}
	
	
	
	
	
	
}
