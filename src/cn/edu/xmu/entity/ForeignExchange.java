package cn.edu.xmu.entity;

import java.sql.Date;

/**
 * 6-2-2-1 本科生境外交流项目
 * @author chunfeng
 *
 */
public class ForeignExchange {
	private int fe_id;
	private String fe_collegename; //学院
	private String fe_projectname; //项目名称
	private String fe_iscsc;  //是否留学基金委项目
	private String fe_country; //所在国家或地区
	private String fe_school; //学校
	private String fe_level;  //级别
	private String fe_time;  //交流期限
	private Integer fe_selftoforeign;  //本校到外校学生数
	private Integer fe_foreigntoself;  //外校到本校学生数
	
	private String fe_college; //填报学院
	private Date fe_deadline;
	private int fe_serialnumber;
	private String fe_comments;
	
	private int fe_isnull; //该行数据是否全部填写  0：已填写完全   1： 存在字段未填写完全
	
	public ForeignExchange(int fe_id, String fe_collegename,String fe_projectname, String fe_iscsc,String fe_country,
			String fe_school, String fe_level, String fe_time,Integer fe_selftoforeign,Integer fe_foreigntoself,
			String fe_college, Date fe_deadline, int fe_serialnumber,String fe_comments, int fe_isnull) {
		super();
		this.fe_id = fe_id;
		this.fe_collegename = fe_collegename;
		this.fe_projectname = fe_projectname;
		this.fe_iscsc = fe_iscsc;
		this.fe_country = fe_country;
		this.fe_school = fe_school;
		this.fe_level = fe_level;
		this.fe_time = fe_time;
		this.fe_selftoforeign = fe_selftoforeign;
		this.fe_foreigntoself = fe_foreigntoself;
		
		this.fe_college = fe_college;
		this.fe_deadline = fe_deadline;
		this.fe_serialnumber = fe_serialnumber;
		this.fe_comments = fe_comments;
		
		this.fe_isnull = fe_isnull;
	}
	public String getFe_college() {
		return fe_college;
	}
	public void setFe_college(String fe_college) {
		this.fe_college = fe_college;
	}
	public ForeignExchange(int fe_id, String fe_collegename,String fe_projectname, String fe_iscsc,String fe_country,
			String fe_school, String fe_level, String fe_time, Integer fe_selftoforeign,Integer fe_foreigntoself,int fe_serialnumber, int fe_isnull) {
		super();
		this.fe_id = fe_id;
		this.fe_collegename = fe_collegename;
		this.fe_projectname = fe_projectname;
		this.fe_iscsc = fe_iscsc;
		this.fe_country = fe_country;
		this.fe_school = fe_school;
		this.fe_level = fe_level;
		this.fe_time = fe_time;
		this.fe_selftoforeign = fe_selftoforeign;
		this.fe_foreigntoself = fe_foreigntoself;
		this.fe_serialnumber = fe_serialnumber;
		this.fe_isnull = fe_isnull;
	}
	public ForeignExchange(int fe_id, String fe_collegename,String fe_projectname, String fe_iscsc,String fe_country,
			String fe_school, String fe_level, String fe_time,Integer fe_selftoforeign,Integer fe_foreigntoself,String fe_college, Date fe_deadline, int fe_serialnumber, int fe_isnull) {
		super();
		this.fe_id = fe_id;
		this.fe_collegename = fe_collegename;
		this.fe_projectname = fe_projectname;
		this.fe_iscsc = fe_iscsc;
		this.fe_country = fe_country;
		this.fe_school = fe_school;
		this.fe_level = fe_level;
		this.fe_time = fe_time;
		this.fe_selftoforeign = fe_selftoforeign;
		this.fe_foreigntoself = fe_foreigntoself;
		this.fe_college = fe_college;
		this.fe_deadline = fe_deadline;
		this.fe_serialnumber = fe_serialnumber;
		this.fe_isnull = fe_isnull;
	}
	/*public ForeignExchange(int fe_id,String fe_college,String fe_projectname, String fe_iscsc,String fe_country,
			String fe_school, String fe_level, String fe_time,int fe_selftoforeign,int fe_foreigntoself, int fe_isnull) {
		super();
		this.fe_id = fe_id;
		this.fe_college = fe_college;
		this.fe_projectname = fe_projectname;
		this.fe_iscsc = fe_iscsc;
		this.fe_country = fe_country;
		this.fe_school = fe_school;
		this.fe_level = fe_level;
		this.fe_time = fe_time;
		this.fe_selftoforeign = fe_selftoforeign;
		this.fe_foreigntoself = fe_foreigntoself;
		this.fe_isnull = fe_isnull;
	}*/
	
	/*public ForeignExchange( String fe_college,String fe_projectname, String fe_iscsc,String fe_country,
			String fe_school, String fe_level, String fe_time,int fe_selftoforeign,int fe_foreigntoself, int fe_isnull) {
		super();
		this.fe_college = fe_college;
		this.fe_projectname = fe_projectname;
		this.fe_iscsc = fe_iscsc;
		this.fe_country = fe_country;
		this.fe_school = fe_school;
		this.fe_level = fe_level;
		this.fe_time = fe_time;
		this.fe_selftoforeign = fe_selftoforeign;
		this.fe_foreigntoself = fe_foreigntoself;
		this.fe_isnull = fe_isnull;
	}*/
	public ForeignExchange() {
		super();
	}
	public ForeignExchange(String fe_collegename,String fe_projectname, String fe_iscsc,String fe_country,
			String fe_school, String fe_level, String fe_time,Integer fe_selftoforeign,Integer fe_foreigntoself,String fe_college, int fe_serialnumber, int fe_isnull) {
		super();
		this.fe_collegename = fe_collegename;
		this.fe_projectname = fe_projectname;
		this.fe_iscsc = fe_iscsc;
		this.fe_country = fe_country;
		this.fe_school = fe_school;
		this.fe_level = fe_level;
		this.fe_time = fe_time;
		this.fe_selftoforeign = fe_selftoforeign;
		this.fe_foreigntoself = fe_foreigntoself;
		this.fe_college = fe_college;
		this.fe_serialnumber = fe_serialnumber;
		this.fe_isnull = fe_isnull;
	}
	
	public Date getFe_deadline() {
		return fe_deadline;
	}
	public void setFe_deadline(Date fe_deadline) {
		this.fe_deadline = fe_deadline;
	}
	public int getFe_id() {
		return fe_id;
	}
	public void setFe_id(int fe_id) {
		this.fe_id = fe_id;
	}
	public String getFe_projectname() {
		return fe_projectname;
	}
	public void setFe_projectname(String fe_projectname) {
		this.fe_projectname = fe_projectname;
	}
	public String getFe_country() {
		return fe_country;
	}
	public void setFe_country(String fe_country) {
		this.fe_country = fe_country;
	}
	public String getFe_school() {
		return fe_school;
	}
	public void setFe_school(String fe_school) {
		this.fe_school = fe_school;
	}
	public String getFe_level() {
		return fe_level;
	}
	public void setFe_level(String fe_level) {
		this.fe_level = fe_level;
	}
	public String getFe_time() {
		return fe_time;
	}
	public void setFe_time(String fe_time) {
		this.fe_time = fe_time;
	}
	public int getFe_serialnumber() {
		return fe_serialnumber;
	}
	public void setFe_serialnumber(int fe_serialnumber) {
		this.fe_serialnumber = fe_serialnumber;
	}
	public String getFe_comments() {
		return fe_comments;
	}
	public void setFe_comments(String fe_comments) {
		this.fe_comments = fe_comments;
	}
	public String getFe_iscsc() {
		return fe_iscsc;
	}
	public void setFe_iscsc(String fe_iscsc) {
		this.fe_iscsc = fe_iscsc;
	}
	public Integer getFe_selftoforeign() {
		return fe_selftoforeign;
	}
	public void setFe_selftoforeign(Integer fe_selftoforeign) {
		this.fe_selftoforeign = fe_selftoforeign;
	}
	public Integer getFe_foreigntoself() {
		return fe_foreigntoself;
	}
	public void setFe_foreigntoself(Integer fe_foreigntoself) {
		this.fe_foreigntoself = fe_foreigntoself;
	}
	public int getFe_isnull() {
		return fe_isnull;
	}
	public void setFe_isnull(int fe_isnull) {
		this.fe_isnull = fe_isnull;
	}
	public String getFe_collegename() {
		return fe_collegename;
	}
	public void setFe_collegename(String fe_collegename) {
		this.fe_collegename = fe_collegename;
	}
	
}
