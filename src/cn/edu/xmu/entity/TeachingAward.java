package cn.edu.xmu.entity;

import java.sql.Date;

/**
 * 表7-3-2教学成果奖（时点）
 * @author chunfeng
 *
 */
public class TeachingAward {
	private int ta_id;
	private String ta_projectname;   //项目名称
	private String ta_compere; //主持人
	private String ta_comperenumber; //主持人工号
 	private String ta_level;  //级别
	private String ta_windate;  //获奖时间
	private String ta_grantunit; //授予单位
	
	private String ta_college;
	private Date ta_deadline;
	private int ta_serialnumber;	
	private String ta_comments;
	private int ta_isnull;
	
	public TeachingAward() {
		super();
	}
	
	public TeachingAward(String ta_projectname,String ta_compere,String ta_comperenumber,String ta_level,
			String ta_windate,String ta_grantunit,int ta_isnull) {
		super();
		this.ta_projectname = ta_projectname;   //名称
		this.ta_compere = ta_compere; //院系名称
		this.ta_comperenumber = ta_comperenumber;  
	 	this.ta_level = ta_level;  
		this.ta_windate = ta_windate;  
		this.ta_grantunit = ta_grantunit; 	
		this.ta_isnull = ta_isnull;
	}
	public TeachingAward(int ta_id, String ta_projectname,String ta_compere,String ta_comperenumber,String ta_level,
			String ta_windate,String ta_grantunit,int ta_isnull) {
        super();
        this.ta_id = ta_id;
        this.ta_projectname = ta_projectname;   //名称
		this.ta_compere = ta_compere; //院系名称
		this.ta_comperenumber = ta_comperenumber;  
	 	this.ta_level = ta_level;  
	 	this.ta_windate = ta_windate;  
		this.ta_grantunit = ta_grantunit; 	
		this.ta_isnull = ta_isnull;
    }
	public TeachingAward(String ta_projectname,String ta_compere,String ta_comperenumber,String ta_level,
			String ta_windate,String ta_grantunit, String ta_college, int ta_serialnumber,int ta_isnull) {
        super();
        this.ta_projectname = ta_projectname;   //名称
		this.ta_compere = ta_compere; //院系名称
		this.ta_comperenumber = ta_comperenumber;  
	 	this.ta_level = ta_level;  
	 	this.ta_windate = ta_windate;  
		this.ta_grantunit = ta_grantunit; 	
		this.ta_college = ta_college;
        this.ta_serialnumber = ta_serialnumber;
        this.ta_isnull = ta_isnull;
    }
	public TeachingAward(int ta_id, String ta_projectname,String ta_compere,String ta_comperenumber,String ta_level,
			String ta_windate,String ta_grantunit, int ta_serialnumber,int ta_isnull) {
        super();
        this.ta_id = ta_id;
        this.ta_projectname = ta_projectname;   //名称
		this.ta_compere = ta_compere; //院系名称
		this.ta_comperenumber = ta_comperenumber;  
	 	this.ta_level = ta_level;  
	 	this.ta_windate = ta_windate;  
		this.ta_grantunit = ta_grantunit; 	
        this.ta_serialnumber = ta_serialnumber;
        this.ta_isnull = ta_isnull;
    }
	public TeachingAward(int ta_id, String ta_projectname,String ta_compere,String ta_comperenumber,String ta_level,
			String ta_windate,String ta_grantunit, String ta_college, int ta_serialnumber,String ta_comments,int ta_isnull) {
        super();
        this.ta_id = ta_id;
        this.ta_projectname = ta_projectname;   //名称
		this.ta_compere = ta_compere; //院系名称
		this.ta_comperenumber = ta_comperenumber;  
	 	this.ta_level = ta_level;  
	 	this.ta_windate = ta_windate;  
		this.ta_grantunit = ta_grantunit; 	
		this.ta_college = ta_college;
        this.ta_serialnumber = ta_serialnumber;
        this.ta_comments = ta_comments;
        this.ta_isnull = ta_isnull;
    }
	public TeachingAward(int ta_id, String ta_projectname,String ta_compere,String ta_comperenumber,String ta_level,
			String ta_windate,String ta_grantunit, String ta_college, Date ta_deadline, int ta_serialnumber,String ta_comments,int ta_isnull) {
        super();
        this.ta_id = ta_id;
        this.ta_projectname = ta_projectname;   //名称
		this.ta_compere = ta_compere; //院系名称
		this.ta_comperenumber = ta_comperenumber;  
	 	this.ta_level = ta_level;  
	 	this.ta_windate = ta_windate;  
		this.ta_grantunit = ta_grantunit; 	
		this.ta_college = ta_college;
        this.ta_deadline = ta_deadline;
        this.ta_serialnumber = ta_serialnumber;
        this.ta_comments = ta_comments;
        this.ta_isnull = ta_isnull;
    }
	
	
	public int getTa_id() {
		return ta_id;
	}
	public void setTa_id(int ta_id) {
		this.ta_id = ta_id;
	}
	public String getTa_projectname() {
		return ta_projectname;
	}
	public void setTa_projectname(String ta_projectname) {
		this.ta_projectname = ta_projectname;
	}
	public String getTa_compere() {
		return ta_compere;
	}
	public void setTa_compere(String ta_compere) {
		this.ta_compere = ta_compere;
	}
	public String getTa_comperenumber() {
		return ta_comperenumber;
	}
	public void setTa_comperenumber(String ta_comperenumber) {
		this.ta_comperenumber = ta_comperenumber;
	}
	public String getTa_level() {
		return ta_level;
	}
	public void setTa_level(String ta_level) {
		this.ta_level = ta_level;
	}
	public String getTa_windate() {
		return ta_windate;
	}
	public void setTa_windate(String ta_windate) {
		this.ta_windate = ta_windate;
	}
	public String getTa_grantunit() {
		return ta_grantunit;
	}
	public void setTa_grantunit(String ta_grantunit) {
		this.ta_grantunit = ta_grantunit;
	}
	public String getTa_college() {
		return ta_college;
	}
	public void setTa_college(String ta_college) {
		this.ta_college = ta_college;
	}
	public Date getTa_deadline() {
		return ta_deadline;
	}
	public void setTa_deadline(Date ta_deadline) {
		this.ta_deadline = ta_deadline;
	}
	public int getTa_serialnumber() {
		return ta_serialnumber;
	}
	public void setTa_serialnumber(int ta_serialnumber) {
		this.ta_serialnumber = ta_serialnumber;
	}
	public String getTa_comments() {
		return ta_comments;
	}
	public void setTa_comments(String ta_comments) {
		this.ta_comments = ta_comments;
	}

	public int getTa_isnull() {
		return ta_isnull;
	}

	public void setTa_isnull(int ta_isnull) {
		this.ta_isnull = ta_isnull;
	}
	
	
	
}
