package cn.edu.xmu.entity;

import java.sql.Date;

/*
 * 3-1-3 其他教师信息
 * Original author: chunfeng
 */
public class OtherTeacherInfoBak {
     private int ti_id;
     private String ti_name; //姓名
     private String ti_teacherid;  //工号
     private int ti_sex; //性别
     private Date ti_birthday;  //出生年月
     private Date ti_inschooldate;  //入校时间
     private int ti_state; //任职状态
     private int ti_class; //师资类别
     private String ti_unitnum;  //单位号
     private String ti_unitname;  //单位名称
     private int ti_edubackground;  //学历
     private int ti_highestoffering;  //最高学位
     private int ti_professiontitle;  //专业技术职称
     private int ti_subjectcategory;  //学科类别
     private int ti_tutorcategory; //导师类别
     private String ti_college; //填报学院
     private int ti_serialnumber; //序号
     
     public OtherTeacherInfoBak (int ti_id, String ti_name, String ti_teacherid, int ti_sex, Date ti_birthday,
    		              Date ti_inschooldate, int ti_state, int ti_class, String ti_unitnum, String ti_unitname,
    		              int ti_edubackground, int ti_highestoffering, int ti_professiontitle, int ti_subjectcategory,
    		              int ti_tutorcategory, String ti_college, int ti_serialnumber){
    	 super();
    	 this.ti_id = ti_id;
    	 this.ti_name = ti_name;
    	 this.ti_teacherid  = ti_teacherid;
    	 this.ti_sex = ti_sex;
    	 this.ti_birthday = ti_birthday;
    	 this.ti_inschooldate = ti_inschooldate;
    	 this.ti_state = ti_state;
    	 this.ti_class = ti_class;
    	 this.ti_unitnum = ti_unitnum;
    	 this.ti_name = ti_name;
    	 this.ti_edubackground = ti_edubackground;
    	 this.ti_highestoffering = ti_highestoffering;
    	 this.ti_professiontitle = ti_professiontitle;
    	 this.ti_subjectcategory = ti_subjectcategory;
    	 this.ti_tutorcategory = ti_tutorcategory;
    	 this.ti_college = ti_college;
    	 this.ti_serialnumber = ti_serialnumber;
     }
	public int getTi_id() {
		return ti_id;
	}
	public void setTi_id(int ti_id) {
		this.ti_id = ti_id;
	}
	public String getTi_name() {
		return ti_name;
	}
	public void setTi_name(String ti_name) {
		this.ti_name = ti_name;
	}
	public String getTi_teacherid() {
		return ti_teacherid;
	}
	public void setTi_teacherid(String ti_teacherid) {
		this.ti_teacherid = ti_teacherid;
	}
	public int getTi_sex() {
		return ti_sex;
	}
	public void setTi_sex(int ti_sex) {
		this.ti_sex = ti_sex;
	}
	public Date getTi_birthday() {
		return ti_birthday;
	}
	public void setTi_birthday(Date ti_birthday) {
		this.ti_birthday = ti_birthday;
	}
	public Date getTi_inschooldate() {
		return ti_inschooldate;
	}
	public void setTi_inschooldate(Date ti_inschooldate) {
		this.ti_inschooldate = ti_inschooldate;
	}
	public int getTi_state() {
		return ti_state;
	}
	public void setTi_state(int ti_state) {
		this.ti_state = ti_state;
	}
	public int getTi_class() {
		return ti_class;
	}
	public void setTi_class(int ti_class) {
		this.ti_class = ti_class;
	}
	public String getTi_unitnum() {
		return ti_unitnum;
	}
	public void setTi_unitnum(String ti_unitnum) {
		this.ti_unitnum = ti_unitnum;
	}
	public String getTi_unitname() {
		return ti_unitname;
	}
	public void setTi_unitname(String ti_unitname) {
		this.ti_unitname = ti_unitname;
	}
	public int getTi_edubackground() {
		return ti_edubackground;
	}
	public void setTi_edubackground(int ti_edubackground) {
		this.ti_edubackground = ti_edubackground;
	}
	public int getTi_highestoffering() {
		return ti_highestoffering;
	}
	public void setTi_highestoffering(int ti_highestoffering) {
		this.ti_highestoffering = ti_highestoffering;
	}
	public int getTi_professiontitle() {
		return ti_professiontitle;
	}
	public void setTi_professiontitle(int ti_professiontitle) {
		this.ti_professiontitle = ti_professiontitle;
	}
	public int getTi_subjectcategory() {
		return ti_subjectcategory;
	}
	public void setTi_subjectcategory(int ti_subjectcategory) {
		this.ti_subjectcategory = ti_subjectcategory;
	}
	public int getTi_tutorcategory() {
		return ti_tutorcategory;
	}
	public void setTi_tutorcategory(int ti_tutorcategory) {
		this.ti_tutorcategory = ti_tutorcategory;
	}
	public String getTi_college() {
		return ti_college;
	}
	public void setTi_college(String ti_college) {
		this.ti_college = ti_college;
	}
	public int getTi_serialnumber() {
		return ti_serialnumber;
	}
	public void setTi_serialnumber(int ti_serialnumber) {
		this.ti_serialnumber = ti_serialnumber;
	}
     
     
}
