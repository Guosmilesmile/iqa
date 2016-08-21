package cn.edu.xmu.entity;

import java.sql.Date;

/**
 * 附表5-1-1-1开课情况表
 * @author chunfeng
 *
 */
public class StartClass {
    private int sc_id;
    private String sc_number; //开课号
    private String sc_coursenum; //课程号
    private String sc_coursecategory; //课程类别
    private String sc_campus; //校区
    private Integer sc_totalhour; //总学时
    private Float sc_totalcredit; //学分
    private String sc_evaluationmode; //考核方式
    private String sc_teachobject; //授课对象
    private String sc_arrange; //安排情况
    private String sc_yearandsemester; //学年学期
    private String sc_collegename; //授课院
    private String sc_coursename; //课程名称
    private String sc_teacher; //授课教师
    private String sc_isoutsideteacher; //是否校外专家
    private String sc_teachernumber; //授课教师工号
    private String sc_teachertitle; //职称
    private Integer sc_studentnum; //本科学生数
    private String sc_isenglish; //英语授课情况
    private String sc_website; //网络教学平台网站
    private String sc_teachmaterial; //教材使用情况
    private Integer sc_materialspecies; //使用教材种数
    private String sc_ismagong; //是否使用马工教材
    private String sc_isstandard; //是否使用规划教材
    private String sc_foreignmaterial; //境外教材使用情况
    private String sc_m_name; //教材名称
    private String sc_m_auther;//作者
    private String sc_m_publisher; //出版社
    private String sc_m_country; //所属国家
    private Integer sc_m_publishyear; //出版年
    
    private String sc_college;
    private Date sc_deadline;
    private int sc_serialnumber;
    private String sc_comments;
    private int sc_isnull;
    
    public StartClass(int sc_id, String sc_number, String sc_coursenum, String sc_coursecategory, String sc_campus, Integer sc_totalhour,
           Float sc_totalcredit, String sc_evaluationmode, String sc_teachobject, String sc_arrange, String sc_yearandsemester,
           String sc_collegename, String sc_coursename, String sc_teacher, String sc_isoutsideteacher, String sc_teachernumber,
           String sc_teachertitle, Integer sc_studentnum, String sc_isenglish, String sc_website, String sc_teachmaterial,
           Integer sc_materialspecies, String sc_ismagong, String sc_isstandard, String sc_foreignmaterial, String sc_m_name,
           String sc_m_auther, String sc_m_publisher, String sc_m_country, Integer sc_m_publishyear, 
           String sc_college, Date sc_deadline, int sc_serialnumber, String sc_comments, int sc_isnull){
             this.sc_id = sc_id;
             this.sc_number = sc_number; //开课号
             this.sc_coursenum = sc_coursenum; //课程号
             this.sc_coursecategory = sc_coursecategory; //课程类别
             this.sc_campus = sc_campus; //小区
             this.sc_totalhour = sc_totalhour; //总学时
             this.sc_totalcredit = sc_totalcredit; //学分
             this.sc_evaluationmode = sc_evaluationmode; //考核方式
             this.sc_teachobject = sc_teachobject; //授课对象
             this.sc_arrange = sc_arrange; //安排情况
             this.sc_yearandsemester = sc_yearandsemester; //学年学期
             this.sc_collegename = sc_collegename; //授课院
             this.sc_coursename = sc_coursename; //课程名称
             this.sc_teacher = sc_teacher; //授课教师
             this.sc_isoutsideteacher = sc_isoutsideteacher; //是否校外专家
             this.sc_teachernumber = sc_teachernumber; //授课教师工号
             this.sc_teachertitle = sc_teachertitle; //职称
             this.sc_studentnum = sc_studentnum; //本科学生数
             this.sc_isenglish = sc_isenglish; //英语授课情况
             this.sc_website = sc_website; //网络教学平台网站
             this.sc_teachmaterial = sc_teachmaterial; //教材使用情况
             this.sc_materialspecies = sc_materialspecies; //使用教材种数
             this.sc_ismagong = sc_ismagong; //是否使用马工教材
             this.sc_isstandard = sc_isstandard; //是否使用规划教材
             this.sc_foreignmaterial = sc_foreignmaterial; //境外教材使用情况
             this.sc_m_name = sc_m_name; //教材名称
             this.sc_m_auther = sc_m_auther;//作者
             this.sc_m_publisher = sc_m_publisher; //出版社
             this.sc_m_country = sc_m_country; //所属国家
             this.sc_m_publishyear = sc_m_publishyear; //出版年
        
             this.sc_college = sc_college; 
             this.sc_deadline = sc_deadline;
             this.sc_serialnumber = sc_serialnumber;
             this.sc_comments = sc_comments;	
             this.sc_isnull = sc_isnull;
    }
    
    public StartClass(String sc_number, String sc_coursenum, String sc_coursecategory, String sc_campus, Integer sc_totalhour,
            Float sc_totalcredit, String sc_evaluationmode, String sc_teachobject, String sc_arrange, String sc_yearandsemester,
            String sc_collegename, String sc_coursename, String sc_teacher, String sc_isoutsideteacher, String sc_teachernumber,
            String sc_teachertitle, Integer sc_studentnum, String sc_isenglish, String sc_website, String sc_teachmaterial,
            Integer sc_materialspecies, String sc_ismagong, String sc_isstandard, String sc_foreignmaterial, String sc_m_name,
            String sc_m_auther, String sc_m_publisher, String sc_m_country, Integer sc_m_publishyear, String sc_college, int sc_serialnumber, int sc_isnull){
    	this.sc_number = sc_number; //开课号
        this.sc_coursenum = sc_coursenum; //课程号
        this.sc_coursecategory = sc_coursecategory; //课程类别
        this.sc_campus = sc_campus; //小区
        this.sc_totalhour = sc_totalhour; //总学时
        this.sc_totalcredit = sc_totalcredit; //学分
        this.sc_evaluationmode = sc_evaluationmode; //考核方式
        this.sc_teachobject = sc_teachobject; //授课对象
        this.sc_arrange = sc_arrange; //安排情况
        this.sc_yearandsemester = sc_yearandsemester; //学年学期
        this.sc_collegename = sc_collegename; //授课院
        this.sc_coursename = sc_coursename; //课程名称
        this.sc_teacher = sc_teacher; //授课教师
        this.sc_isoutsideteacher = sc_isoutsideteacher; //是否校外专家
        this.sc_teachernumber = sc_teachernumber; //授课教师工号
        this.sc_teachertitle = sc_teachertitle; //职称
        this.sc_studentnum = sc_studentnum; //本科学生数
        this.sc_isenglish = sc_isenglish; //英语授课情况
        this.sc_website = sc_website; //网络教学平台网站
        this.sc_teachmaterial = sc_teachmaterial; //教材使用情况
        this.sc_materialspecies = sc_materialspecies; //使用教材种数
        this.sc_ismagong = sc_ismagong; //是否使用马工教材
        this.sc_isstandard = sc_isstandard; //是否使用规划教材
        this.sc_foreignmaterial = sc_foreignmaterial; //境外教材使用情况
        this.sc_m_name = sc_m_name; //教材名称
        this.sc_m_auther = sc_m_auther;//作者
        this.sc_m_publisher = sc_m_publisher; //出版社
        this.sc_m_country = sc_m_country; //所属国家
        this.sc_m_publishyear = sc_m_publishyear; //出版年
          
        this.sc_college = sc_college;
        this.sc_serialnumber = sc_serialnumber;
        this.sc_isnull = sc_isnull;
    }
    
	public int getSc_id() {
		return sc_id;
	}
	public void setSc_id(int sc_id) {
		this.sc_id = sc_id;
	}
	public String getSc_number() {
		return sc_number;
	}
	public void setSc_number(String sc_number) {
		this.sc_number = sc_number;
	}
	public String getSc_coursenum() {
		return sc_coursenum;
	}
	public void setSc_coursenum(String sc_coursenum) {
		this.sc_coursenum = sc_coursenum;
	}
	public String getSc_coursecategory() {
		return sc_coursecategory;
	}
	public void setSc_coursecategory(String sc_coursecategory) {
		this.sc_coursecategory = sc_coursecategory;
	}
	public String getSc_campus() {
		return sc_campus;
	}
	public void setSc_campus(String sc_campus) {
		this.sc_campus = sc_campus;
	}
	public Integer getSc_totalhour() {
		return sc_totalhour;
	}
	public void setSc_totalhour(Integer sc_totalhour) {
		this.sc_totalhour = sc_totalhour;
	}
	public Float getSc_totalcredit() {
		return sc_totalcredit;
	}
	public void setSc_totalcredit(Float sc_totalcredit) {
		this.sc_totalcredit = sc_totalcredit;
	}
	public String getSc_evaluationmode() {
		return sc_evaluationmode;
	}
	public void setSc_evaluationmode(String sc_evaluationmode) {
		this.sc_evaluationmode = sc_evaluationmode;
	}
	public String getSc_teachobject() {
		return sc_teachobject;
	}
	public void setSc_teachobject(String sc_teachobject) {
		this.sc_teachobject = sc_teachobject;
	}
	public String getSc_arrange() {
		return sc_arrange;
	}
	public void setSc_arrange(String sc_arrange) {
		this.sc_arrange = sc_arrange;
	}
	public String getSc_yearandsemester() {
		return sc_yearandsemester;
	}
	public void setSc_yearandsemester(String sc_yearandsemester) {
		this.sc_yearandsemester = sc_yearandsemester;
	}
	public String getSc_college() {
		return sc_college;
	}
	public void setSc_college(String sc_college) {
		this.sc_college = sc_college;
	}
	public String getSc_coursename() {
		return sc_coursename;
	}
	public void setSc_coursename(String sc_coursename) {
		this.sc_coursename = sc_coursename;
	}
	public String getSc_teacher() {
		return sc_teacher;
	}
	public void setSc_teacher(String sc_teacher) {
		this.sc_teacher = sc_teacher;
	}
	public String getSc_isoutsideteacher() {
		return sc_isoutsideteacher;
	}
	public void setSc_isoutsideteacher(String sc_isoutsideteacher) {
		this.sc_isoutsideteacher = sc_isoutsideteacher;
	}
	public String getSc_teachernumber() {
		return sc_teachernumber;
	}
	public void setSc_teachernumber(String sc_teachernumber) {
		this.sc_teachernumber = sc_teachernumber;
	}
	public String getSc_teachertitle() {
		return sc_teachertitle;
	}
	public void setSc_teachertitle(String sc_teachertitle) {
		this.sc_teachertitle = sc_teachertitle;
	}
	public Integer getSc_studentnum() {
		return sc_studentnum;
	}
	public void setSc_studentnum(Integer sc_studentnum) {
		this.sc_studentnum = sc_studentnum;
	}
	public String getSc_isenglish() {
		return sc_isenglish;
	}
	public void setSc_isenglish(String sc_isenglish) {
		this.sc_isenglish = sc_isenglish;
	}
	public String getSc_website() {
		return sc_website;
	}
	public void setSc_website(String sc_website) {
		this.sc_website = sc_website;
	}
	public String getSc_teachmaterial() {
		return sc_teachmaterial;
	}
	public void setSc_teachmaterial(String sc_teachmaterial) {
		this.sc_teachmaterial = sc_teachmaterial;
	}
	public Integer getSc_materialspecies() {
		return sc_materialspecies;
	}
	public void setSc_materialspecies(Integer sc_materialspecies) {
		this.sc_materialspecies = sc_materialspecies;
	}
	public String getSc_ismagong() {
		return sc_ismagong;
	}
	public void setSc_ismagong(String sc_ismagong) {
		this.sc_ismagong = sc_ismagong;
	}
	public String getSc_isstandard() {
		return sc_isstandard;
	}
	public void setSc_isstandard(String sc_isstandard) {
		this.sc_isstandard = sc_isstandard;
	}
	public String getSc_foreignmaterial() {
		return sc_foreignmaterial;
	}
	public void setSc_foreignmaterial(String sc_foreignmaterial) {
		this.sc_foreignmaterial = sc_foreignmaterial;
	}
	public String getSc_m_name() {
		return sc_m_name;
	}
	public void setSc_m_name(String sc_m_name) {
		this.sc_m_name = sc_m_name;
	}
	public String getSc_m_auther() {
		return sc_m_auther;
	}
	public void setSc_m_auther(String sc_m_auther) {
		this.sc_m_auther = sc_m_auther;
	}
	public String getSc_m_publisher() {
		return sc_m_publisher;
	}
	public void setSc_m_publisher(String sc_m_publisher) {
		this.sc_m_publisher = sc_m_publisher;
	}
	public String getSc_m_country() {
		return sc_m_country;
	}
	public void setSc_m_country(String sc_m_country) {
		this.sc_m_country = sc_m_country;
	}
	public Integer getSc_m_publishyear() {
		return sc_m_publishyear;
	}
	public void setSc_m_publishyear(Integer sc_m_publishyear) {
		this.sc_m_publishyear = sc_m_publishyear;
	}
	public Date getSc_deadline() {
		return sc_deadline;
	}
	public void setSc_deadline(Date sc_deadline) {
		this.sc_deadline = sc_deadline;
	}
	public int getSc_serialnumber() {
		return sc_serialnumber;
	}
	public void setSc_serialnumber(int sc_serialnumber) {
		this.sc_serialnumber = sc_serialnumber;
	}
	public String getSc_comments() {
		return sc_comments;
	}
	public void setSc_comments(String sc_comments) {
		this.sc_comments = sc_comments;
	}

	public int getSc_isnull() {
		return sc_isnull;
	}

	public void setSc_isnull(int sc_isnull) {
		this.sc_isnull = sc_isnull;
	}

	public String getSc_collegename() {
		return sc_collegename;
	}

	public void setSc_collegename(String sc_collegename) {
		this.sc_collegename = sc_collegename;
	}
    
    
    
}
