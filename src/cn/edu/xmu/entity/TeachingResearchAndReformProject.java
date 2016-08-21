package cn.edu.xmu.entity;

import java.sql.Date;


/**
 * 7-3-1  教育教学研究与改革项目
 * @author chunfeng
 *
 */

public class TeachingResearchAndReformProject {
	private int trarp_id;
	private String trarp_projectname;   //项目名称
	private String trarp_compere; //主持人
	private String trarp_comperenumber; //主持人工号
 	private String trarp_level;  //级别
	private String trarp_setupdate;  //立项时间
	private String trarp_checkdate; //验收时间
	private Float trarp_funds; //经费
	private Integer trarp_jointeachers; //参与教师数
	
	private String trarp_college;
	private Date trarp_deadline;
	private int trarp_serialnumber;	
	private String trarp_comments;
	private int trarp_isnull;
	
	public TeachingResearchAndReformProject() {
		super();
	}
	
	public TeachingResearchAndReformProject(String trarp_projectname,String trarp_compere,String trarp_comperenumber,String trarp_level,
			String trarp_setupdate,String trarp_checkdate,Float trarp_funds,Integer trarp_jointeachers, int trarp_isnull) {
		super();
		this.trarp_projectname = trarp_projectname;   //名称
		this.trarp_compere = trarp_compere; //院系名称
		this.trarp_comperenumber = trarp_comperenumber;  
	 	this.trarp_level = trarp_level;  
		this.trarp_setupdate = trarp_setupdate;  
		this.trarp_checkdate = trarp_checkdate; 
		this.trarp_funds = trarp_funds; 
		this.trarp_jointeachers = trarp_jointeachers; 
		this.trarp_isnull = trarp_isnull;
	}
	public TeachingResearchAndReformProject(int trarp_id, String trarp_projectname,String trarp_compere,String trarp_comperenumber,String trarp_level,
			String trarp_setupdate,String trarp_checkdate,Float trarp_funds,Integer trarp_jointeachers, int trarp_isnull) {
        super();
        this.trarp_id = trarp_id;
        this.trarp_projectname = trarp_projectname;   //名称
		this.trarp_compere = trarp_compere; //院系名称
		this.trarp_comperenumber = trarp_comperenumber;  
	 	this.trarp_level = trarp_level;  
		this.trarp_setupdate = trarp_setupdate;  
		this.trarp_checkdate = trarp_checkdate; 
		this.trarp_funds = trarp_funds; 
		this.trarp_jointeachers = trarp_jointeachers; 
		this.trarp_isnull = trarp_isnull;
    }
	public TeachingResearchAndReformProject(String trarp_projectname,String trarp_compere,String trarp_comperenumber,String trarp_level,
			String trarp_setupdate,String trarp_checkdate,Float trarp_funds,Integer trarp_jointeachers, String trarp_college, int trarp_serialnumber, int trarp_isnull) {
        super();
        this.trarp_projectname = trarp_projectname;   //名称
		this.trarp_compere = trarp_compere; //院系名称
		this.trarp_comperenumber = trarp_comperenumber;  
	 	this.trarp_level = trarp_level;  
		this.trarp_setupdate = trarp_setupdate;  
		this.trarp_checkdate = trarp_checkdate; 
		this.trarp_funds = trarp_funds; 
		this.trarp_jointeachers = trarp_jointeachers; 
		this.trarp_college = trarp_college;
        this.trarp_serialnumber = trarp_serialnumber;
        this.trarp_isnull = trarp_isnull;
    }
	public TeachingResearchAndReformProject(int trarp_id, String trarp_projectname,String trarp_compere,String trarp_comperenumber,String trarp_level,
			String trarp_setupdate,String trarp_checkdate,Float trarp_funds,Integer trarp_jointeachers, int trarp_serialnumber, int trarp_isnull) {
        super();
        this.trarp_id = trarp_id;
        this.trarp_projectname = trarp_projectname;   //名称
		this.trarp_compere = trarp_compere; //院系名称
		this.trarp_comperenumber = trarp_comperenumber;  
	 	this.trarp_level = trarp_level;  
		this.trarp_setupdate = trarp_setupdate;  
		this.trarp_checkdate = trarp_checkdate; 
		this.trarp_funds = trarp_funds; 
		this.trarp_jointeachers = trarp_jointeachers; 
        this.trarp_serialnumber = trarp_serialnumber;
        this.trarp_isnull = trarp_isnull;
    }
	public TeachingResearchAndReformProject(int trarp_id, String trarp_projectname,String trarp_compere,String trarp_comperenumber,String trarp_level,
			String trarp_setupdate,String trarp_checkdate,Float trarp_funds,Integer trarp_jointeachers, String trarp_college, int trarp_serialnumber,String trarp_comments, int trarp_isnull) {
        super();
        this.trarp_id = trarp_id;
        this.trarp_projectname = trarp_projectname;   //名称
		this.trarp_compere = trarp_compere; //院系名称
		this.trarp_comperenumber = trarp_comperenumber;  
	 	this.trarp_level = trarp_level;  
		this.trarp_setupdate = trarp_setupdate;  
		this.trarp_checkdate = trarp_checkdate; 
		this.trarp_funds = trarp_funds; 
		this.trarp_jointeachers = trarp_jointeachers; 
		this.trarp_college = trarp_college;
        this.trarp_serialnumber = trarp_serialnumber;
        this.trarp_comments = trarp_comments;
        this.trarp_isnull = trarp_isnull;
    }
	public TeachingResearchAndReformProject(int trarp_id, String trarp_projectname,String trarp_compere,String trarp_comperenumber,String trarp_level,
			String trarp_setupdate,String trarp_checkdate,Float trarp_funds,Integer trarp_jointeachers, String trarp_college, Date trarp_deadline, int trarp_serialnumber,String trarp_comments, int trarp_isnull) {
        super();
        this.trarp_id = trarp_id;
        this.trarp_projectname = trarp_projectname;   //名称
		this.trarp_compere = trarp_compere; //院系名称
		this.trarp_comperenumber = trarp_comperenumber;  
	 	this.trarp_level = trarp_level;  
		this.trarp_setupdate = trarp_setupdate;  
		this.trarp_checkdate = trarp_checkdate; 
		this.trarp_funds = trarp_funds; 
		this.trarp_jointeachers = trarp_jointeachers;
		this.trarp_college = trarp_college;
        this.trarp_deadline = trarp_deadline;
        this.trarp_serialnumber = trarp_serialnumber;
        this.trarp_comments = trarp_comments;
        this.trarp_isnull = trarp_isnull;
    }
	
	public int getTrarp_id() {
		return trarp_id;
	}
	public void setTrarp_id(int trarp_id) {
		this.trarp_id = trarp_id;
	}
	public String getTrarp_projectname() {
		return trarp_projectname;
	}
	public void setTrarp_projectname(String trarp_projectname) {
		this.trarp_projectname = trarp_projectname;
	}
	public String getTrarp_compere() {
		return trarp_compere;
	}
	public void setTrarp_compere(String trarp_compere) {
		this.trarp_compere = trarp_compere;
	}
	public String getTrarp_comperenumber() {
		return trarp_comperenumber;
	}
	public void setTrarp_comperenumber(String trarp_comperenumber) {
		this.trarp_comperenumber = trarp_comperenumber;
	}
	public String getTrarp_level() {
		return trarp_level;
	}
	public void setTrarp_level(String trarp_level) {
		this.trarp_level = trarp_level;
	}
	public String getTrarp_setupdate() {
		return trarp_setupdate;
	}
	public void setTrarp_setupdate(String trarp_setupdate) {
		this.trarp_setupdate = trarp_setupdate;
	}
	public String getTrarp_checkdate() {
		return trarp_checkdate;
	}
	public void setTrarp_checkdate(String trarp_checkdate) {
		this.trarp_checkdate = trarp_checkdate;
	}
	public Float getTrarp_funds() {
		return trarp_funds;
	}
	public void setTrarp_funds(Float trarp_funds) {
		this.trarp_funds = trarp_funds;
	}
	public Integer getTrarp_jointeachers() {
		return trarp_jointeachers;
	}
	public void setTrarp_jointeachers(Integer trarp_jointeachers) {
		this.trarp_jointeachers = trarp_jointeachers;
	}
	public String getTrarp_college() {
		return trarp_college;
	}
	public void setTrarp_college(String trarp_college) {
		this.trarp_college = trarp_college;
	}
	public Date getTrarp_deadline() {
		return trarp_deadline;
	}
	public void setTrarp_deadline(Date trarp_deadline) {
		this.trarp_deadline = trarp_deadline;
	}
	public int getTrarp_serialnumber() {
		return trarp_serialnumber;
	}
	public void setTrarp_serialnumber(int trarp_serialnumber) {
		this.trarp_serialnumber = trarp_serialnumber;
	}
	public String getTrarp_comments() {
		return trarp_comments;
	}
	public void setTrarp_comments(String trarp_comments) {
		this.trarp_comments = trarp_comments;
	}

	public int getTrarp_isnull() {
		return trarp_isnull;
	}

	public void setTrarp_isnull(int trarp_isnull) {
		this.trarp_isnull = trarp_isnull;
	}
	
	
	
}
