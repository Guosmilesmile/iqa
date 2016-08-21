package cn.edu.xmu.entity;

import java.sql.Date;

/**
 * @author zhantu
 * 教学管理人员获教学成果奖情况（时点） 实体类
 * date 2015-07-09
 */
public class StaffTeachingAchieve {
	//ID
	private int sta_id;
	//年度
	private Integer sta_year;
	//届
	private String sta_session;
	//学院
	private String sta_collegename;
	//项目名称
	private String sta_projectname;
	//主要完成者
	private String sta_persons;
	//国家级
	private String sta_country;
	//省级
	private String sta_province;
	//校级
	private String sta_school;
	//教学管理人员是否参与
	private String sta_ifstaffattend;
	//序列号
	private int sta_serialnumber;
	//截止日期
	private Date sta_deadline;
	//填报学院
	private String sta_college;
	//审核意见
	private String sta_comments;
	//记录是否存在空值
	private int isnull;
	public int getSta_id() {
		return sta_id;
	}
	public void setSta_id(int sta_id) {
		this.sta_id = sta_id;
	}
	public Integer getSta_year() {
		return sta_year;
	}
	public void setSta_year(Integer sta_year) {
		this.sta_year = sta_year;
	}
	public String getSta_session() {
		return sta_session;
	}
	public void setSta_session(String sta_session) {
		this.sta_session = sta_session;
	}
	public String getSta_collegename() {
		return sta_collegename;
	}
	public void setSta_collegename(String sta_collegename) {
		this.sta_collegename = sta_collegename;
	}
	public String getSta_projectname() {
		return sta_projectname;
	}
	public void setSta_projectname(String sta_projectname) {
		this.sta_projectname = sta_projectname;
	}
	public String getSta_persons() {
		return sta_persons;
	}
	public void setSta_persons(String sta_persons) {
		this.sta_persons = sta_persons;
	}
	public String getSta_country() {
		return sta_country;
	}
	public void setSta_country(String sta_country) {
		this.sta_country = sta_country;
	}
	public String getSta_province() {
		return sta_province;
	}
	public void setSta_province(String sta_province) {
		this.sta_province = sta_province;
	}
	public String getSta_school() {
		return sta_school;
	}
	public void setSta_school(String sta_school) {
		this.sta_school = sta_school;
	}
	public String getSta_ifstaffattend() {
		return sta_ifstaffattend;
	}
	public void setSta_ifstaffattend(String sta_ifstaffattend) {
		this.sta_ifstaffattend = sta_ifstaffattend;
	}
	public int getSta_serialnumber() {
		return sta_serialnumber;
	}
	public void setSta_serialnumber(int sta_serialnumber) {
		this.sta_serialnumber = sta_serialnumber;
	}
	public Date getSta_deadline() {
		return sta_deadline;
	}
	public void setSta_deadline(Date sta_deadline) {
		this.sta_deadline = sta_deadline;
	}
	public String getSta_college() {
		return sta_college;
	}
	public void setSta_college(String sta_college) {
		this.sta_college = sta_college;
	}
	public String getSta_comments() {
		return sta_comments;
	}
	public void setSta_comments(String sta_comments) {
		this.sta_comments = sta_comments;
	}
	public int getIsnull() {
		return isnull;
	}
	public void setIsnull(int isnull) {
		this.isnull = isnull;
	}
	public StaffTeachingAchieve() {
		super();
	}
	public StaffTeachingAchieve(int sta_id, Integer sta_year,
			String sta_session, String sta_collegename, String sta_projectname,
			String sta_persons, String sta_country, String sta_province,
			String sta_school, String sta_ifstaffattend, int sta_serialnumber,
			Date sta_deadline, String sta_college, String sta_comments,
			int isnull) {
		super();
		this.sta_id = sta_id;
		this.sta_year = sta_year;
		this.sta_session = sta_session;
		this.sta_collegename = sta_collegename;
		this.sta_projectname = sta_projectname;
		this.sta_persons = sta_persons;
		this.sta_country = sta_country;
		this.sta_province = sta_province;
		this.sta_school = sta_school;
		this.sta_ifstaffattend = sta_ifstaffattend;
		this.sta_serialnumber = sta_serialnumber;
		this.sta_deadline = sta_deadline;
		this.sta_college = sta_college;
		this.sta_comments = sta_comments;
		this.isnull = isnull;
	}
	public StaffTeachingAchieve(Integer sta_year, String sta_session,
			String sta_collegename, String sta_projectname, String sta_persons,
			String sta_country, String sta_province, String sta_school,
			String sta_ifstaffattend, int sta_serialnumber, String sta_college,
			String sta_comments, int isnull) {
		super();
		this.sta_year = sta_year;
		this.sta_session = sta_session;
		this.sta_collegename = sta_collegename;
		this.sta_projectname = sta_projectname;
		this.sta_persons = sta_persons;
		this.sta_country = sta_country;
		this.sta_province = sta_province;
		this.sta_school = sta_school;
		this.sta_ifstaffattend = sta_ifstaffattend;
		this.sta_serialnumber = sta_serialnumber;
		this.sta_college = sta_college;
		this.sta_comments = sta_comments;
		this.isnull = isnull;
	}
	public StaffTeachingAchieve(int sta_id, Integer sta_year,
			String sta_session, String sta_collegename, String sta_projectname,
			String sta_persons, String sta_country, String sta_province,
			String sta_school, String sta_ifstaffattend, int sta_serialnumber,
			String sta_comments, int isnull) {
		super();
		this.sta_id = sta_id;
		this.sta_year = sta_year;
		this.sta_session = sta_session;
		this.sta_collegename = sta_collegename;
		this.sta_projectname = sta_projectname;
		this.sta_persons = sta_persons;
		this.sta_country = sta_country;
		this.sta_province = sta_province;
		this.sta_school = sta_school;
		this.sta_ifstaffattend = sta_ifstaffattend;
		this.sta_serialnumber = sta_serialnumber;
		this.sta_comments = sta_comments;
		this.isnull = isnull;
	}
	public StaffTeachingAchieve(int sta_id, Integer sta_year,
			String sta_session, String sta_collegename, String sta_projectname,
			String sta_persons, String sta_country, String sta_province,
			String sta_school, String sta_ifstaffattend, String sta_comments,
			int isnull) {
		super();
		this.sta_id = sta_id;
		this.sta_year = sta_year;
		this.sta_session = sta_session;
		this.sta_collegename = sta_collegename;
		this.sta_projectname = sta_projectname;
		this.sta_persons = sta_persons;
		this.sta_country = sta_country;
		this.sta_province = sta_province;
		this.sta_school = sta_school;
		this.sta_ifstaffattend = sta_ifstaffattend;
		this.sta_comments = sta_comments;
		this.isnull = isnull;
	}
	public StaffTeachingAchieve(Integer sta_year, String sta_session,
			String sta_collegename, String sta_projectname, String sta_persons,
			String sta_country, String sta_province, String sta_school,
			String sta_ifstaffattend, String sta_college, int isnull) {
		super();
		this.sta_year = sta_year;
		this.sta_session = sta_session;
		this.sta_collegename = sta_collegename;
		this.sta_projectname = sta_projectname;
		this.sta_persons = sta_persons;
		this.sta_country = sta_country;
		this.sta_province = sta_province;
		this.sta_school = sta_school;
		this.sta_ifstaffattend = sta_ifstaffattend;
		this.sta_college = sta_college;
		this.isnull = isnull;
	}

}
