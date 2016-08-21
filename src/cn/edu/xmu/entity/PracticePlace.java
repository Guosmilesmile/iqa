package cn.edu.xmu.entity;

import java.sql.Date;

/**
 * 2-6-1 本科实验实习实训场所
 * @author chunfeng
 *
 */
public class PracticePlace {
	private int pp_id;
	private String pp_placename;   //名称
	private String pp_collegename; //院系名称
	private Float pp_area;  //面积
	private String pp_property; //实验室性质
 	private String pp_majors;  //面向专业
	private Integer pp_hours;  //学年度承担的实验教学人时数
	private Integer pp_times; //学年度承担的实验教学人次数
	private Integer pp_projectnum; //本科生实验实习实训项目数
	private Integer pp_largeststudents;  //最大可容纳学生数
	
	private String pp_college; //填报学院
	private Date pp_deadline;
	private int pp_serialnumber;	
	private String pp_comments;
	private int pp_isnull;
	
	public PracticePlace() {
		super();
	}
	
	public PracticePlace(String pp_placename, String pp_collegename, Float pp_area, String pp_property, String pp_majors, 
			Integer pp_hours, Integer pp_times, Integer pp_projectnum, Integer pp_largeststudents, String pp_college, int pp_isnull) {
		super();
		this.pp_placename = pp_placename;   //名称
		this.pp_collegename = pp_collegename; //院系名称
		this.pp_area = pp_area;  //面积
		this.pp_property = pp_property; //实验室性质
	 	this.pp_majors = pp_majors;  //面向专业
		this.pp_hours = pp_hours;  //学年度承担的实验教学人时数
		this.pp_times = pp_times; //学年度承担的实验教学人次数
		this.pp_projectnum = pp_projectnum; //本科生实验实习实训项目数
		this.pp_largeststudents = pp_largeststudents; 
		this.pp_college = pp_college;
		this.pp_isnull = pp_isnull;
	}
	public PracticePlace(int pp_id, String pp_placename, String pp_collegename, Float pp_area, String pp_property, String pp_majors, 
			Integer pp_hours, Integer pp_times, Integer pp_projectnum, Integer pp_largeststudents, String pp_college, int pp_isnull) {
        super();
        this.pp_id = pp_id;
        this.pp_placename = pp_placename;   //名称
        this.pp_collegename = pp_collegename; //院系名称
        this.pp_area = pp_area;  //面积
        this.pp_property = pp_property; //实验室性质
        this.pp_majors = pp_majors;  //面向专业
        this.pp_hours = pp_hours;  //学年度承担的实验教学人时数
        this.pp_times = pp_times; //学年度承担的实验教学人次数
        this.pp_projectnum = pp_projectnum; //本科生实验实习实训项目数
        this.pp_largeststudents = pp_largeststudents; 
        this.pp_college = pp_college;
        this.pp_isnull = pp_isnull;
    }
	public PracticePlace(String pp_placename, String pp_collegename, Float pp_area, String pp_property, String pp_majors, 
			Integer pp_hours, Integer pp_times, Integer pp_projectnum, Integer pp_largeststudents, String pp_college, int pp_serialnumber, int pp_isnull) {
        super();
        this.pp_placename = pp_placename;   //名称
        this.pp_collegename = pp_collegename; //院系名称
        this.pp_area = pp_area;  //面积
        this.pp_property = pp_property; //实验室性质
        this.pp_majors = pp_majors;  //面向专业
        this.pp_hours = pp_hours;  //学年度承担的实验教学人时数
        this.pp_times = pp_times; //学年度承担的实验教学人次数
        this.pp_projectnum = pp_projectnum; //本科生实验实习实训项目数
        this.pp_largeststudents = pp_largeststudents;
        this.pp_college = pp_college;
        this.pp_serialnumber = pp_serialnumber;
        this.pp_isnull = pp_isnull;
    }
	public PracticePlace(int pp_id, String pp_placename, String pp_collegename, Float pp_area, String pp_property, String pp_majors, 
			Integer pp_hours, Integer pp_times, Integer pp_projectnum, Integer pp_largeststudents,String pp_college, int pp_serialnumber, int pp_isnull) {
        super();
        this.pp_id = pp_id;
        this.pp_placename = pp_placename;   //名称
        this.pp_collegename = pp_collegename; //院系名称
        this.pp_area = pp_area;  //面积
        this.pp_property = pp_property; //实验室性质
        this.pp_majors = pp_majors;  //面向专业
        this.pp_hours = pp_hours;  //学年度承担的实验教学人时数
        this.pp_times = pp_times; //学年度承担的实验教学人次数
        this.pp_projectnum = pp_projectnum; //本科生实验实习实训项目数
        this.pp_largeststudents = pp_largeststudents; 
        this.pp_college = pp_college;
        this.pp_serialnumber = pp_serialnumber;
        this.pp_isnull = pp_isnull;
    }
	public PracticePlace(int pp_id, String pp_placename, String pp_collegename, Float pp_area, String pp_property, String pp_majors, 
			Integer pp_hours, Integer pp_times, Integer pp_projectnum, Integer pp_largeststudents,String pp_college, int pp_serialnumber,String pp_comments, int pp_isnull) {
        super();
        this.pp_id = pp_id;
        this.pp_placename = pp_placename;   //名称
        this.pp_collegename = pp_collegename; //院系名称
        this.pp_area = pp_area;  //面积
        this.pp_property = pp_property; //实验室性质
        this.pp_majors = pp_majors;  //面向专业
        this.pp_hours = pp_hours;  //学年度承担的实验教学人时数
        this.pp_times = pp_times; //学年度承担的实验教学人次数
        this.pp_projectnum = pp_projectnum; //本科生实验实习实训项目数
        this.pp_largeststudents = pp_largeststudents; 
        this.pp_college = pp_college;
        this.pp_serialnumber = pp_serialnumber;
        this.pp_comments = pp_comments;
        this.pp_isnull = pp_isnull;
    }
	public PracticePlace(int pp_id, String pp_placename, String pp_collegename, Float pp_area, String pp_property, String pp_majors, 
			Integer pp_hours, Integer pp_times, Integer pp_projectnum, Integer pp_largeststudents,String pp_college, Date pp_deadline, int pp_serialnumber,String pp_comments, int pp_isnull) {
        super();
        this.pp_id = pp_id;
        this.pp_placename = pp_placename;   //名称
        this.pp_collegename = pp_collegename; //院系名称
        this.pp_area = pp_area;  //面积
        this.pp_property = pp_property; //实验室性质
        this.pp_majors = pp_majors;  //面向专业
        this.pp_hours = pp_hours;  //学年度承担的实验教学人时数
        this.pp_times = pp_times; //学年度承担的实验教学人次数
        this.pp_projectnum = pp_projectnum; //本科生实验实习实训项目数
        this.pp_largeststudents = pp_largeststudents; 
        this.pp_college = pp_college;
        this.pp_deadline = pp_deadline;
        this.pp_serialnumber = pp_serialnumber;
        this.pp_comments = pp_comments;
        this.pp_isnull = pp_isnull;
    }
	public int getPp_id() {
		return pp_id;
	}
	public void setPp_id(int pp_id) {
		this.pp_id = pp_id;
	}
	public String getPp_placename() {
		return pp_placename;
	}
	public void setPp_placename(String pp_placename) {
		this.pp_placename = pp_placename;
	}
	public String getPp_college() {
		return pp_college;
	}
	public void setPp_college(String pp_college) {
		this.pp_college = pp_college;
	}
	public Float getPp_area() {
		return pp_area;
	}
	public void setPp_area(Float pp_area) {
		this.pp_area = pp_area;
	}
	public String getPp_property() {
		return pp_property;
	}
	public void setPp_property(String pp_property) {
		this.pp_property = pp_property;
	}
	public String getPp_majors() {
		return pp_majors;
	}
	public void setPp_majors(String pp_majors) {
		this.pp_majors = pp_majors;
	}
	public Integer getPp_hours() {
		return pp_hours;
	}
	public void setPp_hours(Integer pp_hours) {
		this.pp_hours = pp_hours;
	}
	public Integer getPp_times() {
		return pp_times;
	}
	public void setPp_times(Integer pp_times) {
		this.pp_times = pp_times;
	}
	public Integer getPp_projectnum() {
		return pp_projectnum;
	}
	public void setPp_projectnum(Integer pp_projectnum) {
		this.pp_projectnum = pp_projectnum;
	}
	public Integer getPp_largeststudents() {
		return pp_largeststudents;
	}
	public void setPp_largeststudents(Integer pp_largeststudents) {
		this.pp_largeststudents = pp_largeststudents;
	}
	public Date getPp_deadline() {
		return pp_deadline;
	}
	public void setPp_deadline(Date pp_deadline) {
		this.pp_deadline = pp_deadline;
	}
	public int getPp_serialnumber() {
		return pp_serialnumber;
	}
	public void setPp_serialnumber(int pp_serialnumber) {
		this.pp_serialnumber = pp_serialnumber;
	}
	public String getPp_comments() {
		return pp_comments;
	}
	public void setPp_comments(String pp_comments) {
		this.pp_comments = pp_comments;
	}

	public int getPp_isnull() {
		return pp_isnull;
	}

	public void setPp_isnull(int pp_isnull) {
		this.pp_isnull = pp_isnull;
	}

	public String getPp_collegename() {
		return pp_collegename;
	}

	public void setPp_collegename(String pp_collegename) {
		this.pp_collegename = pp_collegename;
	}
	
	
} 
