package cn.edu.xmu.entity;

import java.sql.Date;

/**
 * 2-6-2校外实习、实践基地
 * @author chunfeng
 *
 */
public class OutsidePracticePlace {
	private int opp_id;
	private String opp_placename;   //校外实习、实训基地名称
	private String opp_collegename; //院系
	private String opp_address; //地址
 	private String opp_majors;  //面向专业
	private Integer opp_studentspertime;  //每次可接纳学生数（个）
	private Integer opp_studentsthisyear; //当年接纳学生数（个）
	private String opp_month; //实习、实训月份
	private String opp_cooperator;  //合作或依托单位
	private String opp_setupdate;  //建立时间
	private String opp_level;  //级别
	private Integer opp_accumulatedstudents; //建设至今累计接收人次数
	
	private String opp_college; 
	private Date opp_deadline;
	private int opp_serialnumber;	
	private String opp_comments;
	private int opp_isnull;
	
	public OutsidePracticePlace() {
		super();
	}
	
	public OutsidePracticePlace(String opp_placename, String opp_collegename, String opp_address, String opp_majors, 
			Integer opp_studentspertime, Integer opp_studentsthisyear, String opp_month, String opp_cooperator,
			             String opp_setupdate, String opp_level, Integer opp_accumulatedstudents, String opp_college, int opp_isnull) {
		super();
		this.opp_placename = opp_placename;   //名称
		this.opp_collegename = opp_collegename; //院系名称
		this.opp_address = opp_address;  
	 	this.opp_majors = opp_majors;  
		this.opp_studentspertime = opp_studentspertime;  
		this.opp_studentsthisyear = opp_studentsthisyear; 
		this.opp_month = opp_month; 
		this.opp_cooperator = opp_cooperator; 
		this.opp_setupdate = opp_setupdate;
		this.opp_level = opp_level;
		this.opp_accumulatedstudents = opp_accumulatedstudents;
		this.opp_college = opp_college;
		this.opp_isnull = opp_isnull;
	}
	public OutsidePracticePlace(int opp_id, String opp_placename, String opp_collegename, String opp_address, String opp_majors, 
			Integer opp_studentspertime, Integer opp_studentsthisyear, String opp_month, String opp_cooperator,
            String opp_setupdate, String opp_level, Integer opp_accumulatedstudents,String opp_college, int opp_isnull) {
        super();
        this.opp_id = opp_id;
        this.opp_placename = opp_placename;   //名称
		this.opp_collegename = opp_collegename; //院系名称
		this.opp_address = opp_address;  
	 	this.opp_majors = opp_majors;  
		this.opp_studentspertime = opp_studentspertime;  
		this.opp_studentsthisyear = opp_studentsthisyear; 
		this.opp_month = opp_month; 
		this.opp_cooperator = opp_cooperator; 
		this.opp_setupdate = opp_setupdate;
		this.opp_level = opp_level;
		this.opp_accumulatedstudents = opp_accumulatedstudents;
		this.opp_college = opp_college;
		this.opp_isnull = opp_isnull;
    }
	public OutsidePracticePlace(String opp_placename, String opp_collegename, String opp_address, String opp_majors, 
			Integer opp_studentspertime, Integer opp_studentsthisyear, String opp_month, String opp_cooperator,
            String opp_setupdate, String opp_level, Integer opp_accumulatedstudents, String opp_college, int opp_serialnumber,int opp_isnull) {
        super();
        this.opp_placename = opp_placename;   //名称
		this.opp_collegename = opp_collegename; //院系名称
		this.opp_address = opp_address;  
	 	this.opp_majors = opp_majors;  
		this.opp_studentspertime = opp_studentspertime;  
		this.opp_studentsthisyear = opp_studentsthisyear; 
		this.opp_month = opp_month; 
		this.opp_cooperator = opp_cooperator; 
		this.opp_setupdate = opp_setupdate;
		this.opp_level = opp_level;
		this.opp_accumulatedstudents = opp_accumulatedstudents;
		this.opp_college = opp_college;
        this.opp_serialnumber = opp_serialnumber;
        this.opp_isnull = opp_isnull;
    }
	public OutsidePracticePlace(int opp_id, String opp_placename, String opp_collegename, String opp_address, String opp_majors, 
			Integer opp_studentspertime, Integer opp_studentsthisyear, String opp_month, String opp_cooperator,
            String opp_setupdate, String opp_level, Integer opp_accumulatedstudents, String opp_college,int opp_serialnumber,int opp_isnull) {
        super();
        this.opp_id = opp_id;
        this.opp_placename = opp_placename;   //名称
		this.opp_collegename = opp_collegename; //院系名称
		this.opp_address = opp_address;  
	 	this.opp_majors = opp_majors;  
		this.opp_studentspertime = opp_studentspertime;  
		this.opp_studentsthisyear = opp_studentsthisyear; 
		this.opp_month = opp_month; 
		this.opp_cooperator = opp_cooperator; 
		this.opp_setupdate = opp_setupdate;
		this.opp_level = opp_level;
		this.opp_accumulatedstudents = opp_accumulatedstudents;
		this.opp_college = opp_college;
        this.opp_serialnumber = opp_serialnumber;
        this.opp_isnull = opp_isnull;
    }
	public OutsidePracticePlace(int opp_id, String opp_placename, String opp_collegename, String opp_address, String opp_majors, 
			Integer opp_studentspertime, Integer opp_studentsthisyear, String opp_month, String opp_cooperator,
            String opp_setupdate, String opp_level, Integer opp_accumulatedstudents, String opp_college, int opp_serialnumber,String opp_comments,int opp_isnull) {
        super();
        this.opp_id = opp_id;
        this.opp_placename = opp_placename;   //名称
		this.opp_collegename = opp_collegename; //院系名称
		this.opp_address = opp_address;  
	 	this.opp_majors = opp_majors;  
		this.opp_studentspertime = opp_studentspertime;  
		this.opp_studentsthisyear = opp_studentsthisyear; 
		this.opp_month = opp_month; 
		this.opp_cooperator = opp_cooperator; 
		this.opp_setupdate = opp_setupdate;
		this.opp_level = opp_level;
		this.opp_accumulatedstudents = opp_accumulatedstudents;
		this.opp_college = opp_college; 
        this.opp_serialnumber = opp_serialnumber;
        this.opp_comments = opp_comments;
        this.opp_isnull = opp_isnull;
    }
	public OutsidePracticePlace(int opp_id, String opp_placename, String opp_collegename, String opp_address, String opp_majors, 
			Integer opp_studentspertime, Integer opp_studentsthisyear, String opp_month, String opp_cooperator,
            String opp_setupdate, String opp_level, Integer opp_accumulatedstudents, String opp_college,Date opp_deadline, int opp_serialnumber,String opp_comments,int opp_isnull) {
        super();
        this.opp_id = opp_id;
        this.opp_placename = opp_placename;   //名称
		this.opp_collegename = opp_collegename; //院系名称
		this.opp_address = opp_address;  
	 	this.opp_majors = opp_majors;  
		this.opp_studentspertime = opp_studentspertime;  
		this.opp_studentsthisyear = opp_studentsthisyear; 
		this.opp_month = opp_month; 
		this.opp_cooperator = opp_cooperator; 
		this.opp_setupdate = opp_setupdate;
		this.opp_level = opp_level;
		this.opp_accumulatedstudents = opp_accumulatedstudents;
		this.opp_college = opp_college;
        this.opp_deadline = opp_deadline;
        this.opp_serialnumber = opp_serialnumber;
        this.opp_comments = opp_comments;
        this.opp_isnull = opp_isnull;
    }

	public int getOpp_id() {
		return opp_id;
	}

	public void setOpp_id(int opp_id) {
		this.opp_id = opp_id;
	}

	public String getOpp_placename() {
		return opp_placename;
	}

	public void setOpp_placename(String opp_placename) {
		this.opp_placename = opp_placename;
	}

	public String getOpp_college() {
		return opp_college;
	}

	public void setOpp_college(String opp_college) {
		this.opp_college = opp_college;
	}

	public String getOpp_address() {
		return opp_address;
	}

	public void setOpp_address(String opp_address) {
		this.opp_address = opp_address;
	}

	public String getOpp_majors() {
		return opp_majors;
	}

	public void setOpp_majors(String opp_majors) {
		this.opp_majors = opp_majors;
	}

	public Integer getOpp_studentspertime() {
		return opp_studentspertime;
	}

	public void setOpp_studentspertime(Integer opp_studentspertime) {
		this.opp_studentspertime = opp_studentspertime;
	}

	public Integer getOpp_studentsthisyear() {
		return opp_studentsthisyear;
	}

	public void setOpp_studentsthisyear(Integer opp_studentsthisyear) {
		this.opp_studentsthisyear = opp_studentsthisyear;
	}

	public String getOpp_month() {
		return opp_month;
	}

	public void setOpp_month(String opp_month) {
		this.opp_month = opp_month;
	}

	public String getOpp_cooperator() {
		return opp_cooperator;
	}

	public void setOpp_cooperator(String opp_cooperator) {
		this.opp_cooperator = opp_cooperator;
	}

	public String getOpp_setupdate() {
		return opp_setupdate;
	}

	public void setOpp_setupdate(String opp_setupdate) {
		this.opp_setupdate = opp_setupdate;
	}

	public String getOpp_level() {
		return opp_level;
	}

	public void setOpp_level(String opp_level) {
		this.opp_level = opp_level;
	}

	public Integer getOpp_accumulatedstudents() {
		return opp_accumulatedstudents;
	}

	public void setOpp_accumulatedstudents(Integer opp_accumulatedstudents) {
		this.opp_accumulatedstudents = opp_accumulatedstudents;
	}

	public Date getOpp_deadline() {
		return opp_deadline;
	}

	public void setOpp_deadline(Date opp_deadline) {
		this.opp_deadline = opp_deadline;
	}

	public int getOpp_serialnumber() {
		return opp_serialnumber;
	}

	public void setOpp_serialnumber(int opp_serialnumber) {
		this.opp_serialnumber = opp_serialnumber;
	}

	public String getOpp_comments() {
		return opp_comments;
	}

	public void setOpp_comments(String opp_comments) {
		this.opp_comments = opp_comments;
	}

	public int getOpp_isnull() {
		return opp_isnull;
	}

	public void setOpp_isnull(int opp_isnull) {
		this.opp_isnull = opp_isnull;
	}

	public String getOpp_collegename() {
		return opp_collegename;
	}

	public void setOpp_collegename(String opp_collegename) {
		this.opp_collegename = opp_collegename;
	}
	
} 
