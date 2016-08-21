package cn.edu.xmu.entity;

import java.sql.Date;

/**
 * 5-1-1-1 教师资源表
 * @author chunfeng
 *
 */
public class TeacherInfo {
     private int ti_id;
     private String ti_collegename; //学院
     private String ti_name;//姓名
     private String ti_number; //工号
     private String ti_professiontitle; //职称
     
     private String ti_college;
     private Date ti_deadline;
     private int ti_serialnumber; 
     private String ti_comments;
     private int ti_isnull;
    
     public TeacherInfo(int ti_id, String ti_collegename, String ti_name, String ti_number, String ti_professiontitle,
            String ti_college, Date ti_deadline, int ti_serialnumber, String ti_comments, int ti_isnull){
    	 super();
    	 this.ti_id = ti_id;
    	 this.ti_collegename = ti_collegename;
    	 this.ti_name = ti_name;
    	 this.ti_number = ti_number;
    	 this.ti_professiontitle = ti_professiontitle;
    	 
         this.ti_college = ti_college;
         this.ti_deadline = ti_deadline;
         this.ti_serialnumber = ti_serialnumber;
         this.ti_comments = ti_comments;
         this.ti_isnull = ti_isnull;
     }
     public TeacherInfo(String ti_collegename, String ti_name, String ti_number, String ti_professiontitle,
             String ti_college, int ti_serialnumber,int ti_isnull){
    	 super();
    	 this.ti_collegename = ti_collegename;
    	 this.ti_name = ti_name;
    	 this.ti_number = ti_number;
    	 this.ti_professiontitle = ti_professiontitle;
    	 
         this.ti_college = ti_college;
         this.ti_serialnumber = ti_serialnumber;
         this.ti_isnull = ti_isnull;
     }
     public TeacherInfo(int ti_id, String ti_collegename, String ti_name, String ti_number, String ti_professiontitle,
             String ti_college, int ti_serialnumber, String ti_comments, int ti_isnull){
    	 super();
    	 this.ti_id = ti_id;
    	 this.ti_collegename = ti_collegename;
    	 this.ti_name = ti_name;
    	 this.ti_number = ti_number;
    	 this.ti_professiontitle = ti_professiontitle;
    	 
         this.ti_college = ti_college;
         this.ti_serialnumber = ti_serialnumber;
         this.ti_comments = ti_comments;
         this.ti_isnull = ti_isnull;
      }
	public int getTi_id() {
		return ti_id;
	}
	public void setTi_id(int ti_id) {
		this.ti_id = ti_id;
	}
	public String getTi_collegename() {
		return ti_collegename;
	}
	public void setTi_collegename(String ti_collegename) {
		this.ti_collegename = ti_collegename;
	}
	public String getTi_name() {
		return ti_name;
	}
	public void setTi_name(String ti_name) {
		this.ti_name = ti_name;
	}
	public String getTi_number() {
		return ti_number;
	}
	public void setTi_number(String ti_number) {
		this.ti_number = ti_number;
	}
	public String getTi_professiontitle() {
		return ti_professiontitle;
	}
	public void setTi_professiontitle(String ti_professiontitle) {
		this.ti_professiontitle = ti_professiontitle;
	}
	public String getTi_college() {
		return ti_college;
	}
	public void setTi_college(String ti_college) {
		this.ti_college = ti_college;
	}
	public Date getTi_deadline() {
		return ti_deadline;
	}
	public void setTi_deadline(Date ti_deadline) {
		this.ti_deadline = ti_deadline;
	}
	public int getTi_serialnumber() {
		return ti_serialnumber;
	}
	public void setTi_serialnumber(int ti_serialnumber) {
		this.ti_serialnumber = ti_serialnumber;
	}
	public String getTi_comments() {
		return ti_comments;
	}
	public void setTi_comments(String ti_comments) {
		this.ti_comments = ti_comments;
	}
	public int getTi_isnull() {
		return ti_isnull;
	}
	public void setTi_isnull(int ti_isnull) {
		this.ti_isnull = ti_isnull;
	}
     
}
