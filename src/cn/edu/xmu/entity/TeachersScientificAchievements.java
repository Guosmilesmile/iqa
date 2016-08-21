package cn.edu.xmu.entity;

import java.sql.Date;

/**
 * 3-6-3  教师最近一届科研成果奖数
 * @author chunfeng
 *
 */
public class TeachersScientificAchievements {
     private int tsa_id;
     private Integer tsa_total; //总数
     private Integer tsa_nationallevel; //国家级
     private Integer tsa_provinciallevel; //省部级
     
     private String tsa_college;
     private Date tsa_deadline;
 	 private int tsa_serialnumber;
 	 private String tsa_comments;
 	 private int tsa_isnull;
 	 
     public TeachersScientificAchievements() {
 		super();
 	}

     public TeachersScientificAchievements(Integer tsa_total,Integer tsa_nationallevel, Integer tsa_provinciallevel, int tsa_isnull){
    	 super();
    	 this.tsa_total = tsa_total;
    	 this.tsa_nationallevel = tsa_nationallevel;
    	 this.tsa_provinciallevel = tsa_provinciallevel;
    	 this.tsa_isnull = tsa_isnull;
     }
     public TeachersScientificAchievements(Integer tsa_total,Integer tsa_nationallevel, Integer tsa_provinciallevel,String tsa_college,int tsa_serialnumber,int tsa_isnull){
    	 super();
    	 this.tsa_total = tsa_total;
    	 this.tsa_nationallevel = tsa_nationallevel;
    	 this.tsa_provinciallevel = tsa_provinciallevel;
    	 this.tsa_college = tsa_college;
    	 this.tsa_serialnumber = tsa_serialnumber;
    	 this.tsa_isnull = tsa_isnull;
     }
     public TeachersScientificAchievements(Integer tsa_total,Integer tsa_nationallevel, Integer tsa_provinciallevel,String tsa_college,Date tsa_deadline, int tsa_isnull){
    	 super();
    	 this.tsa_total = tsa_total;
    	 this.tsa_nationallevel = tsa_nationallevel;
    	 this.tsa_provinciallevel = tsa_provinciallevel;
    	 this.tsa_college = tsa_college;
    	 this.tsa_deadline = tsa_deadline;
    	 this.tsa_isnull = tsa_isnull;
     }
     public TeachersScientificAchievements(Integer tsa_total,Integer tsa_nationallevel, Integer tsa_provinciallevel,String tsa_college,String tsa_comments, int tsa_isnull){
    	 super();
    	 this.tsa_total = tsa_total;
    	 this.tsa_nationallevel = tsa_nationallevel;
    	 this.tsa_provinciallevel = tsa_provinciallevel;
    	 this.tsa_college = tsa_college;
    	 this.tsa_comments = tsa_comments;
    	 this.tsa_isnull = tsa_isnull;
     }
     public TeachersScientificAchievements(int tsa_id,Integer tsa_total,Integer tsa_nationallevel, Integer tsa_provinciallevel,String tsa_college,Date tsa_deadline,int tsa_serialnumber,String tsa_comments, int tsa_isnull){
    	 super();
    	 this.tsa_id = tsa_id;
    	 this.tsa_total = tsa_total;
    	 this.tsa_nationallevel = tsa_nationallevel;
    	 this.tsa_provinciallevel = tsa_provinciallevel;
    	 this.tsa_college = tsa_college;
    	 this.tsa_deadline = tsa_deadline;
    	 this.tsa_serialnumber = tsa_serialnumber;
    	 this.tsa_comments = tsa_comments;
    	 this.tsa_isnull = tsa_isnull;
     }
	public int getTsa_id() {
		return tsa_id;
	}

	public void setTsa_id(int tsa_id) {
		this.tsa_id = tsa_id;
	}

	public Integer getTsa_total() {
		return tsa_total;
	}

	public void setTsa_total(Integer tsa_total) {
		this.tsa_total = tsa_total;
	}

	public Integer getTsa_nationallevel() {
		return tsa_nationallevel;
	}

	public void setTsa_nationallevel(Integer tsa_nationallevel) {
		this.tsa_nationallevel = tsa_nationallevel;
	}

	public Integer getTsa_provinciallevel() {
		return tsa_provinciallevel;
	}

	public void setTsa_provinciallevel(Integer tsa_provinciallevel) {
		this.tsa_provinciallevel = tsa_provinciallevel;
	}

	public Date getTsa_deadline() {
		return tsa_deadline;
	}

	public void setTsa_deadline(Date tsa_deadline) {
		this.tsa_deadline = tsa_deadline;
	}

	public int getTsa_serialnumber() {
		return tsa_serialnumber;
	}

	public void setTsa_serialnumber(int tsa_serialnumber) {
		this.tsa_serialnumber = tsa_serialnumber;
	}

	public String getTsa_comments() {
		return tsa_comments;
	}

	public void setTsa_comments(String tsa_comments) {
		this.tsa_comments = tsa_comments;
	}

	public String getTsa_college() {
		return tsa_college;
	}

	public void setTsa_college(String tsa_college) {
		this.tsa_college = tsa_college;
	}

	public int getTsa_isnull() {
		return tsa_isnull;
	}

	public void setTsa_isnull(int tsa_isnull) {
		this.tsa_isnull = tsa_isnull;
	}
     
     
}
