package cn.edu.xmu.entity;

import java.util.Date;

/**
 * 
 * @author Luo
 * 附表5-2-1出版教材情况（自然年） 实体类
 * 全参构造函数
 * date 2015-07-08
 *
 */
public class PublishedMaterials {
	private int pm_id;
	//学院
	private String pm_importcollege;
	//出版教材名称
	private String pm_materialsname;
	//著者/编者姓名
	private String pm_author;
	//主编/参编
	private String pm_type;
	//出版社
	private String pm_publisher;
	//出版年份
	private String pm_publishyear;
	//马工程教材
	private String pm_engineeringmaterials;
	//规划教材
	private String pm_forteaching;
	//序列号
	private int pm_serialnumber;
	//截止日期
	private Date pm_deadline;
	//所属学院
	private String pm_college;
	//审核意见
	private String pm_comments;
	
	private int isnull ;
	public PublishedMaterials(int pm_id, String pm_importcollege,
			String pm_materialsname, String pm_author, String pm_type,
			String pm_publisher, String pm_publishyear,
			String pm_engineeringmaterials, String pm_forteaching,
			int pm_serialnumber, Date pm_deadline, String pm_college,
			String pm_comments,int isnull) {
		super();
		this.pm_id = pm_id;
		this.pm_importcollege = pm_importcollege;
		this.pm_materialsname = pm_materialsname;
		this.pm_author = pm_author;
		this.pm_type = pm_type;
		this.pm_publisher = pm_publisher;
		this.pm_publishyear = pm_publishyear;
		this.pm_engineeringmaterials = pm_engineeringmaterials;
		this.pm_forteaching = pm_forteaching;
		this.pm_serialnumber = pm_serialnumber;
		this.pm_deadline = pm_deadline;
		this.pm_college = pm_college;
		this.pm_comments = pm_comments;
		this.isnull = isnull;
	}
	public PublishedMaterials(String pm_importcollege, String pm_materialsname,
			String pm_author, String pm_type, String pm_publisher,
			String pm_publishyear, String pm_engineeringmaterials,
			String pm_forteaching, int pm_serialnumber, String pm_college,
			String pm_comments,int isnull) {
		super();
		this.pm_importcollege = pm_importcollege;
		this.pm_materialsname = pm_materialsname;
		this.pm_author = pm_author;
		this.pm_type = pm_type;
		this.pm_publisher = pm_publisher;
		this.pm_publishyear = pm_publishyear;
		this.pm_engineeringmaterials = pm_engineeringmaterials;
		this.pm_forteaching = pm_forteaching;
		this.pm_serialnumber = pm_serialnumber;
		this.pm_college = pm_college;
		this.pm_comments = pm_comments;
		this.isnull = isnull;
	}
	public PublishedMaterials(int pm_id, String pm_importcollege,
			String pm_materialsname, String pm_author, String pm_type,
			String pm_publisher, String pm_publishyear,
			String pm_engineeringmaterials, String pm_forteaching,
			int pm_serialnumber, String pm_comments,int isnull,String pm_college) {
		super();
		this.pm_id = pm_id;
		this.pm_importcollege = pm_importcollege;
		this.pm_materialsname = pm_materialsname;
		this.pm_author = pm_author;
		this.pm_type = pm_type;
		this.pm_publisher = pm_publisher;
		this.pm_publishyear = pm_publishyear;
		this.pm_engineeringmaterials = pm_engineeringmaterials;
		this.pm_forteaching = pm_forteaching;
		this.pm_serialnumber = pm_serialnumber;
		this.pm_comments = pm_comments;
		this.isnull = isnull;
		this.pm_college = pm_college;
	}
	
	public PublishedMaterials(String pm_importcollege, String pm_materialsname,
			String pm_author, String pm_type, String pm_publisher,
			String pm_publishyear, String pm_engineeringmaterials,
			String pm_forteaching, String pm_college,int isnull) {
		super();
		this.pm_importcollege = pm_importcollege;
		this.pm_materialsname = pm_materialsname;
		this.pm_author = pm_author;
		this.pm_type = pm_type;
		this.pm_publisher = pm_publisher;
		this.pm_publishyear = pm_publishyear;
		this.pm_engineeringmaterials = pm_engineeringmaterials;
		this.pm_forteaching = pm_forteaching;
		this.pm_college = pm_college;
		this.isnull = isnull;
	}
	public int getPm_id() {
		return pm_id;
	}
	public void setPm_id(int pm_id) {
		this.pm_id = pm_id;
	}
	public String getPm_importcollege() {
		return pm_importcollege;
	}
	public void setPm_importcollege(String pm_importcollege) {
		this.pm_importcollege = pm_importcollege;
	}
	public String getPm_materialsname() {
		return pm_materialsname;
	}
	public void setPm_materialsname(String pm_materialsname) {
		this.pm_materialsname = pm_materialsname;
	}
	public String getPm_author() {
		return pm_author;
	}
	public void setPm_author(String pm_author) {
		this.pm_author = pm_author;
	}
	public String getPm_type() {
		return pm_type;
	}
	public void setPm_type(String pm_type) {
		this.pm_type = pm_type;
	}
	public String getPm_publisher() {
		return pm_publisher;
	}
	public void setPm_publisher(String pm_publisher) {
		this.pm_publisher = pm_publisher;
	}
	public String getPm_publishyear() {
		return pm_publishyear;
	}
	public void setPm_publishyear(String pm_publishyear) {
		this.pm_publishyear = pm_publishyear;
	}
	public String getPm_engineeringmaterials() {
		return pm_engineeringmaterials;
	}
	public void setPm_engineeringmaterials(String pm_engineeringmaterials) {
		this.pm_engineeringmaterials = pm_engineeringmaterials;
	}
	public String getPm_forteaching() {
		return pm_forteaching;
	}
	public void setPm_forteaching(String pm_forteaching) {
		this.pm_forteaching = pm_forteaching;
	}
	public int getPm_serialnumber() {
		return pm_serialnumber;
	}
	public void setPm_serialnumber(int pm_serialnumber) {
		this.pm_serialnumber = pm_serialnumber;
	}
	public Date getPm_deadline() {
		return pm_deadline;
	}
	public void setPm_deadline(Date pm_deadline) {
		this.pm_deadline = pm_deadline;
	}
	public String getPm_college() {
		return pm_college;
	}
	public void setPm_college(String pm_college) {
		this.pm_college = pm_college;
	}
	public String getPm_comments() {
		return pm_comments;
	}
	public void setPm_comments(String pm_comments) {
		this.pm_comments = pm_comments;
	}
	public int getIsnull() {
		return isnull;
	}
	public void setIsnull(int isnull) {
		this.isnull = isnull;
	}
	
	
	

}
