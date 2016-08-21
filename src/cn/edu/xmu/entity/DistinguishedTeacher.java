package cn.edu.xmu.entity;

import java.sql.Date;

/**
 * 附表3-6-1-1教学名师 实体类
 * @author zhantu
 * date 2015-07-08
 */
public class DistinguishedTeacher {
	//ID
	private int dt_id;
	//序号
	private Integer dt_sequencenumber;
	//姓名
	private String dt_name;
	//所属学院名
	private String dt_collegename;
	//国家级年份
	private Integer dt_country;
	//省级年份
	private Integer dt_province;
	//校级年份
	private Integer dt_school;
	//序列号
	private int dt_serialnumber;
	//截止日期
	private Date dt_deadline;
	//填报学院
	private String dt_college;
	//审核意见
	private String dt_comments;
	//记录是否存在空值
	private int isnull;
	public int getDt_id() {
		return dt_id;
	}
	public void setDt_id(int dt_id) {
		this.dt_id = dt_id;
	}
	public Integer getDt_sequencenumber() {
		return dt_sequencenumber;
	}
	public void setDt_sequencenumber(Integer dt_sequencenumber) {
		this.dt_sequencenumber = dt_sequencenumber;
	}
	public String getDt_name() {
		return dt_name;
	}
	public void setDt_name(String dt_name) {
		this.dt_name = dt_name;
	}
	public String getDt_collegename() {
		return dt_collegename;
	}
	public void setDt_collegename(String dt_collegename) {
		this.dt_collegename = dt_collegename;
	}
	public Integer getDt_country() {
		return dt_country;
	}
	public void setDt_country(Integer dt_country) {
		this.dt_country = dt_country;
	}
	public Integer getDt_province() {
		return dt_province;
	}
	public void setDt_province(Integer dt_province) {
		this.dt_province = dt_province;
	}
	public Integer getDt_school() {
		return dt_school;
	}
	public void setDt_school(Integer dt_school) {
		this.dt_school = dt_school;
	}
	public int getDt_serialnumber() {
		return dt_serialnumber;
	}
	public void setDt_serialnumber(int dt_serialnumber) {
		this.dt_serialnumber = dt_serialnumber;
	}
	public Date getDt_deadline() {
		return dt_deadline;
	}
	public void setDt_deadline(Date dt_deadline) {
		this.dt_deadline = dt_deadline;
	}
	public String getDt_college() {
		return dt_college;
	}
	public void setDt_college(String dt_college) {
		this.dt_college = dt_college;
	}
	public String getDt_comments() {
		return dt_comments;
	}
	public void setDt_comments(String dt_comments) {
		this.dt_comments = dt_comments;
	}
	public int getIsnull() {
		return isnull;
	}
	public void setIsnull(int isnull) {
		this.isnull = isnull;
	}
	public DistinguishedTeacher() {
		super();
	}
	public DistinguishedTeacher(int dt_id, Integer dt_sequencenumber,
			String dt_name, String dt_collegename, Integer dt_country,
			Integer dt_province, Integer dt_school, int dt_serialnumber,
			Date dt_deadline, String dt_college, String dt_comments, int isnull) {
		super();
		this.dt_id = dt_id;
		this.dt_sequencenumber = dt_sequencenumber;
		this.dt_name = dt_name;
		this.dt_collegename = dt_collegename;
		this.dt_country = dt_country;
		this.dt_province = dt_province;
		this.dt_school = dt_school;
		this.dt_serialnumber = dt_serialnumber;
		this.dt_deadline = dt_deadline;
		this.dt_college = dt_college;
		this.dt_comments = dt_comments;
		this.isnull = isnull;
	}
	public DistinguishedTeacher(Integer dt_sequencenumber, String dt_name,
			String dt_collegename, Integer dt_country, Integer dt_province,
			Integer dt_school, int dt_serialnumber, String dt_college,
			String dt_comments, int isnull) {
		super();
		this.dt_sequencenumber = dt_sequencenumber;
		this.dt_name = dt_name;
		this.dt_collegename = dt_collegename;
		this.dt_country = dt_country;
		this.dt_province = dt_province;
		this.dt_school = dt_school;
		this.dt_serialnumber = dt_serialnumber;
		this.dt_college = dt_college;
		this.dt_comments = dt_comments;
		this.isnull = isnull;
	}
	public DistinguishedTeacher(int dt_id, Integer dt_sequencenumber,
			String dt_name, String dt_collegename, Integer dt_country,
			Integer dt_province, Integer dt_school, int dt_serialnumber,
			String dt_comments, int isnull) {
		super();
		this.dt_id = dt_id;
		this.dt_sequencenumber = dt_sequencenumber;
		this.dt_name = dt_name;
		this.dt_collegename = dt_collegename;
		this.dt_country = dt_country;
		this.dt_province = dt_province;
		this.dt_school = dt_school;
		this.dt_serialnumber = dt_serialnumber;
		this.dt_comments = dt_comments;
		this.isnull = isnull;
	}
	public DistinguishedTeacher(int dt_id, Integer dt_sequencenumber,
			String dt_name, String dt_collegename, Integer dt_country,
			Integer dt_province, Integer dt_school, String dt_comments,
			int isnull) {
		super();
		this.dt_id = dt_id;
		this.dt_sequencenumber = dt_sequencenumber;
		this.dt_name = dt_name;
		this.dt_collegename = dt_collegename;
		this.dt_country = dt_country;
		this.dt_province = dt_province;
		this.dt_school = dt_school;
		this.dt_comments = dt_comments;
		this.isnull = isnull;
	}
	public DistinguishedTeacher(Integer dt_sequencenumber, String dt_name,
			String dt_collegename, Integer dt_country, Integer dt_province,
			Integer dt_school, String dt_college, int isnull) {
		super();
		this.dt_sequencenumber = dt_sequencenumber;
		this.dt_name = dt_name;
		this.dt_collegename = dt_collegename;
		this.dt_country = dt_country;
		this.dt_province = dt_province;
		this.dt_school = dt_school;
		this.dt_college = dt_college;
		this.isnull = isnull;
	}
	
	
}
