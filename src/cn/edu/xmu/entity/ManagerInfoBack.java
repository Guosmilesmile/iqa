package cn.edu.xmu.entity;

import java.sql.Date;

/*
 * 3-3 相关管理人员基本信息
 * chunfeng
 */
public class ManagerInfoBack {
	 private int mi_id;
     private String mi_name; //姓名
     private String mi_managerid;  //工号
     private String mi_sex; //性别
     private String mi_birthday;  //出生年月
     private Date mi_inschooldate;  //入校时间
     private String mi_class; //管理人员类别
     private String mi_unitnum;  //单位号
     private String mi_unitname;  //单位名称
     private String mi_edubackground;  //学历
     private String mi_highestoffering;  //最高学位
     private String mi_professiontitle;  //专业技术职称
     private String mi_duty; //职务
     private int mi_serialnumber; //序号
     
     public ManagerInfoBack (int mi_id, String mi_name, String mi_managerid, String mi_sex, String mi_birthday,
             Date mi_inschooldate, String mi_class, String mi_unitnum, String mi_unitname,
             String mi_edubackground, String mi_highestoffering, String mi_professiontitle,String mi_duty, int mi_serialnumber){
      super();
      this.mi_id = mi_id;
      this.mi_name = mi_name;
      this.mi_managerid  = mi_managerid;
      this.mi_sex = mi_sex;
      this.mi_birthday = mi_birthday;
      this.mi_inschooldate = mi_inschooldate;
      this.mi_class = mi_class;
      this.mi_unitnum = mi_unitnum;
      this.mi_unitname = mi_unitname;
      this.mi_edubackground = mi_edubackground;
      this.mi_highestoffering = mi_highestoffering;
      this.mi_professiontitle = mi_professiontitle;
      this.mi_duty = mi_duty;
      this.mi_serialnumber = mi_serialnumber;
}
	public int getMi_id() {
		return mi_id;
	}
	public void setMi_id(int mi_id) {
		this.mi_id = mi_id;
	}
	public String getMi_name() {
		return mi_name;
	}
	public void setMi_name(String mi_name) {
		this.mi_name = mi_name;
	}
	public String getMi_managerid() {
		return mi_managerid;
	}
	public void setMi_managerid(String mi_managerid) {
		this.mi_managerid = mi_managerid;
	}
	public String getMi_sex() {
		return mi_sex;
	}
	public void setMi_sex(String mi_sex) {
		this.mi_sex = mi_sex;
	}
	public String getMi_birthday() {
		return mi_birthday;
	}
	public void setMi_birthday(String mi_birthday) {
		this.mi_birthday = mi_birthday;
	}
	public Date getMi_inschooldate() {
		return mi_inschooldate;
	}
	public void setMi_inschooldate(Date mi_inschooldate) {
		this.mi_inschooldate = mi_inschooldate;
	}
	public String getMi_class() {
		return mi_class;
	}
	public void setMi_class(String mi_class) {
		this.mi_class = mi_class;
	}
	public String getMi_unitnum() {
		return mi_unitnum;
	}
	public void setMi_unitnum(String mi_unitnum) {
		this.mi_unitnum = mi_unitnum;
	}
	public String getMi_unitname() {
		return mi_unitname;
	}
	public void setMi_unitname(String mi_unitname) {
		this.mi_unitname = mi_unitname;
	}
	public String getMi_edubackground() {
		return mi_edubackground;
	}
	public void setMi_edubackground(String mi_edubackground) {
		this.mi_edubackground = mi_edubackground;
	}
	public String getMi_highestoffering() {
		return mi_highestoffering;
	}
	public void setMi_highestoffering(String mi_highestoffering) {
		this.mi_highestoffering = mi_highestoffering;
	}
	public String getMi_professiontitle() {
		return mi_professiontitle;
	}
	public void setMi_professiontitle(String mi_professiontitle) {
		this.mi_professiontitle = mi_professiontitle;
	}
	public String getMi_duty() {
		return mi_duty;
	}
	public void setMi_duty(String mi_duty) {
		this.mi_duty = mi_duty;
	}
	public int getMi_serialnumber() {
		return mi_serialnumber;
	}
	public void setMi_serialnumber(int mi_serialnumber) {
		this.mi_serialnumber = mi_serialnumber;
	}
     
     
}
