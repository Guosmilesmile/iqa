package cn.edu.xmu.entity;

import java.sql.Date;

/**
 * @author zhantu
 * 教学团队（时点） 实体类
 * date 2015-07-08
 */
public class TeachingTeam {
	//ID
	private int tt_id;
	//序号
	private Integer tt_sequencenumber;
	//所属学院名
	private String tt_collegename;
	//名称
	private String tt_name;
	//带头人
	private String tt_leader;
	//国家级年份
	private Integer tt_country;
	//省级年份
	private Integer tt_province;
	//序列号
	private int tt_serialnumber;
	//截止日期
	private Date tt_deadline;
	//填报学院
	private String tt_college;
	//审核意见
	private String tt_comments;
	//记录是否存在空值
	private int isnull;
	public int getTt_id() {
		return tt_id;
	}
	public void setTt_id(int tt_id) {
		this.tt_id = tt_id;
	}
	public Integer getTt_sequencenumber() {
		return tt_sequencenumber;
	}
	public void setTt_sequencenumber(Integer tt_sequencenumber) {
		this.tt_sequencenumber = tt_sequencenumber;
	}
	public String getTt_collegename() {
		return tt_collegename;
	}
	public void setTt_collegename(String tt_collegename) {
		this.tt_collegename = tt_collegename;
	}
	public String getTt_name() {
		return tt_name;
	}
	public void setTt_name(String tt_name) {
		this.tt_name = tt_name;
	}
	public String getTt_leader() {
		return tt_leader;
	}
	public void setTt_leader(String tt_leader) {
		this.tt_leader = tt_leader;
	}
	public Integer getTt_country() {
		return tt_country;
	}
	public void setTt_country(Integer tt_country) {
		this.tt_country = tt_country;
	}
	public Integer getTt_province() {
		return tt_province;
	}
	public void setTt_province(Integer tt_province) {
		this.tt_province = tt_province;
	}
	public int getTt_serialnumber() {
		return tt_serialnumber;
	}
	public void setTt_serialnumber(int tt_serialnumber) {
		this.tt_serialnumber = tt_serialnumber;
	}
	public Date getTt_deadline() {
		return tt_deadline;
	}
	public void setTt_deadline(Date tt_deadline) {
		this.tt_deadline = tt_deadline;
	}
	public String getTt_college() {
		return tt_college;
	}
	public void setTt_college(String tt_college) {
		this.tt_college = tt_college;
	}
	public String getTt_comments() {
		return tt_comments;
	}
	public void setTt_comments(String tt_comments) {
		this.tt_comments = tt_comments;
	}
	public int getIsnull() {
		return isnull;
	}
	public void setIsnull(int isnull) {
		this.isnull = isnull;
	}
	
	
	public TeachingTeam(int tt_id, Integer tt_sequencenumber,
			String tt_collegename, String tt_name, String tt_leader,
			Integer tt_country, Integer tt_province, int tt_serialnumber,
			Date tt_deadline, String tt_college, String tt_comments, int isnull) {
		super();
		this.tt_id = tt_id;
		this.tt_sequencenumber = tt_sequencenumber;
		this.tt_collegename = tt_collegename;
		this.tt_name = tt_name;
		this.tt_leader = tt_leader;
		this.tt_country = tt_country;
		this.tt_province = tt_province;
		this.tt_serialnumber = tt_serialnumber;
		this.tt_deadline = tt_deadline;
		this.tt_college = tt_college;
		this.tt_comments = tt_comments;
		this.isnull = isnull;
	}
	public TeachingTeam(Integer tt_sequencenumber, String tt_collegename,
			String tt_name, String tt_leader, Integer tt_country,
			Integer tt_province, int tt_serialnumber, String tt_college,
			String tt_comments, int isnull) {
		super();
		this.tt_sequencenumber = tt_sequencenumber;
		this.tt_collegename = tt_collegename;
		this.tt_name = tt_name;
		this.tt_leader = tt_leader;
		this.tt_country = tt_country;
		this.tt_province = tt_province;
		this.tt_serialnumber = tt_serialnumber;
		this.tt_college = tt_college;
		this.tt_comments = tt_comments;
		this.isnull = isnull;
	}
	public TeachingTeam(int tt_id, Integer tt_sequencenumber,
			String tt_collegename, String tt_name, String tt_leader,
			Integer tt_country, Integer tt_province, int tt_serialnumber,
			String tt_comments, int isnull) {
		super();
		this.tt_id = tt_id;
		this.tt_sequencenumber = tt_sequencenumber;
		this.tt_collegename = tt_collegename;
		this.tt_name = tt_name;
		this.tt_leader = tt_leader;
		this.tt_country = tt_country;
		this.tt_province = tt_province;
		this.tt_serialnumber = tt_serialnumber;
		this.tt_comments = tt_comments;
		this.isnull = isnull;
	}
	public TeachingTeam(int tt_id, Integer tt_sequencenumber,
			String tt_collegename, String tt_name, String tt_leader,
			Integer tt_country, Integer tt_province, String tt_comments,
			int isnull) {
		super();
		this.tt_id = tt_id;
		this.tt_sequencenumber = tt_sequencenumber;
		this.tt_collegename = tt_collegename;
		this.tt_name = tt_name;
		this.tt_leader = tt_leader;
		this.tt_country = tt_country;
		this.tt_province = tt_province;
		this.tt_comments = tt_comments;
		this.isnull = isnull;
	}
	public TeachingTeam(Integer tt_sequencenumber, String tt_collegename,
			String tt_name, String tt_leader, Integer tt_country,
			Integer tt_province, String tt_college, int isnull) {
		super();
		this.tt_sequencenumber = tt_sequencenumber;
		this.tt_collegename = tt_collegename;
		this.tt_name = tt_name;
		this.tt_leader = tt_leader;
		this.tt_country = tt_country;
		this.tt_province = tt_province;
		this.tt_college = tt_college;
		this.isnull = isnull;
	}
	public TeachingTeam() {
		super();
	}
	
	
}
