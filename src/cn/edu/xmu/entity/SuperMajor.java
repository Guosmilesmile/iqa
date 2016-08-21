package cn.edu.xmu.entity;

import java.sql.Date;

import org.apache.poi.hssf.record.formula.functions.Int;

/*
 * 优势专业情况
 */
public class SuperMajor {
   private int sm_id; 
   private String sm_name; //校内专业名称
   private String sm_number; //校内专业代码
   private String sm_class; //类型
   private String c_startyear;  //国家级起始年份
   private String p_startyear;  //省级起始年份
   private String s_startyear;  //校级起始年份
   private String respon_person;  //负责人
   private String sm_college;  //COLLEGE学院
   private int sm_serialnumber;
   private Date sm_deadline; //终止时间
   
   
   
   public SuperMajor() {
	super();
}

   
   
public SuperMajor(String sm_name, String sm_number, String sm_class,
		String c_startyear, String p_startyear, String s_startyear,
		String respon_person, String sm_college,int sm_serialnumber) {
	super();
	this.sm_name = sm_name;
	this.sm_number = sm_number;
	this.sm_class = sm_class;
	this.c_startyear = c_startyear;
	this.p_startyear = p_startyear;
	this.s_startyear = s_startyear;
	this.respon_person = respon_person;
	this.sm_college = sm_college;
	this.sm_serialnumber = sm_serialnumber;
	//this.sm_deadline = sm_deadline;
}

public SuperMajor(String sm_name, String sm_number, String sm_class,
		String c_startyear, String p_startyear, String s_startyear,
		String respon_person, String college) {
	super();
	this.sm_name = sm_name;
	this.sm_number = sm_number;
	this.sm_class = sm_class;
	this.c_startyear = c_startyear;
	this.p_startyear = p_startyear;
	this.s_startyear = s_startyear;
	this.respon_person = respon_person;
	this.sm_college = college;
}

public SuperMajor(int sm_id,String sm_name, String sm_number, String sm_class,
		String c_startyear, String p_startyear, String s_startyear,
		String respon_person, String college,int sm_serialnumber,Date sm_deadline) {
	super();
	this.sm_id = sm_id;
	this.sm_name = sm_name;
	this.sm_number = sm_number;
	this.sm_class = sm_class;
	this.c_startyear = c_startyear;
	this.p_startyear = p_startyear;
	this.s_startyear = s_startyear;
	this.respon_person = respon_person;
	this.sm_college = college;
	this.sm_serialnumber=sm_serialnumber;
	this.sm_deadline = sm_deadline;
}



public SuperMajor(String sm_name,String sm_number,String sm_class,String c_startyear,
		              String p_startyear,String s_startyear,String respon_person,String sm_college,int sm_serialnumber,Date sm_deadline){
	   super();
	   this.sm_name=sm_name;
	   this.sm_number=sm_number;
	   this.sm_class=sm_class;
	   this.c_startyear=c_startyear;
	   this.p_startyear=p_startyear;
	   this.s_startyear=s_startyear;
	   this.respon_person=respon_person;
	   this.sm_college=sm_college;
	   this.sm_serialnumber=sm_serialnumber;
	   this.sm_deadline = sm_deadline;
   }
public int getSm_id() {
	return sm_id;
}
public void setSm_id(int sm_id) {
	this.sm_id = sm_id;
}
public String getSm_name() {
	return sm_name;
}
public void setSm_name(String sm_name) {
	this.sm_name = sm_name;
}
public String getSm_number() {
	return sm_number;
}
public void setSm_number(String sm_number) {
	this.sm_number = sm_number;
}
public String getSm_class() {
	return sm_class;
}
public void setSm_class(String sm_class) {
	this.sm_class = sm_class;
}
public String getC_startyear() {
	return c_startyear;
}
public void setC_startyear(String c_startyear) {
	this.c_startyear = c_startyear;
}
public String getP_startyear() {
	return p_startyear;
}
public void setP_startyear(String p_startyear) {
	this.p_startyear = p_startyear;
}
public String getS_startyear() {
	return s_startyear;
}
public void setS_startyear(String s_startyear) {
	this.s_startyear = s_startyear;
}
public String getRespon_person() {
	return respon_person;
}
public void setRespon_person(String respon_person) {
	this.respon_person = respon_person;
}
public String getSm_college() {
	return sm_college;
}
public void setSm_College(String sm_college) {
	this.sm_college = sm_college;
}
public int getSm_serialnumber() {
	return sm_serialnumber;
}
public void setSm_serialnumber(int sm_serialnumber) {
	this.sm_serialnumber = sm_serialnumber;
}
public Date getSm_deadline() {
	return sm_deadline;
}
public void setSm_deadline(Date sm_deadline) {
	this.sm_deadline = sm_deadline;
} 
   
}
