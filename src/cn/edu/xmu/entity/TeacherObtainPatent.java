package cn.edu.xmu.entity;

import java.sql.Date;

/**
 * 3-6-6  教师获准专利数（自然年）
 * @author chunfeng
 *
 */
public class TeacherObtainPatent {
     private int top_id;
     private Integer top_total; //总数
     private Integer top_invention; //发明专利
     private Integer top_utilitymodel; //实用新型专利
     
     private String top_college;
     private Date top_deadline;
 	 private int top_serialnumber;
 	 private String top_comments;
 	 private int top_isnull;
 	 
     public TeacherObtainPatent() {
 		super();
 	}

     public TeacherObtainPatent(Integer top_total,Integer top_invention, Integer top_utilitymodel, int top_isnull){
    	 super();
    	 this.top_total = top_total;
    	 this.top_invention = top_invention;
    	 this.top_utilitymodel = top_utilitymodel;
    	 this.top_isnull = top_isnull;
     }
     public TeacherObtainPatent(Integer top_total,Integer top_invention, Integer top_utilitymodel,String top_college,int top_serialnumber, int top_isnull){
    	 super();
    	 this.top_total = top_total;
    	 this.top_invention = top_invention;
    	 this.top_utilitymodel = top_utilitymodel;
    	 this.top_college = top_college;
    	 this.top_serialnumber = top_serialnumber;
    	 this.top_isnull = top_isnull;
     }
     public TeacherObtainPatent(Integer top_total,Integer top_invention, Integer top_utilitymodel,String top_college,Date top_deadline, int top_isnull){
    	 super();
    	 this.top_total = top_total;
    	 this.top_invention = top_invention;
    	 this.top_utilitymodel = top_utilitymodel;
    	 this.top_college = top_college;
    	 this.top_deadline = top_deadline;
    	 this.top_isnull = top_isnull;
     }
     public TeacherObtainPatent(Integer top_total,Integer top_invention, Integer top_utilitymodel,String top_college,String top_comments, int top_isnull){
    	 super();
    	 this.top_total = top_total;
    	 this.top_invention = top_invention;
    	 this.top_utilitymodel = top_utilitymodel;
    	 this.top_college = top_college;
    	 this.top_comments = top_comments;
    	 this.top_isnull = top_isnull;
     }
     public TeacherObtainPatent(int top_id,Integer top_total,Integer top_invention, Integer top_utilitymodel,String top_college,Date top_deadline,int top_serialnumber,String top_comments, int top_isnull){
    	 super();
    	 this.top_id = top_id;
    	 this.top_total = top_total;
    	 this.top_invention = top_invention;
    	 this.top_utilitymodel = top_utilitymodel;
    	 this.top_college = top_college;
    	 this.top_deadline = top_deadline;
    	 this.top_serialnumber = top_serialnumber;
    	 this.top_comments = top_comments;
    	 this.top_isnull = top_isnull;
     }
	public int getTop_id() {
		return top_id;
	}

	public void setTop_id(int top_id) {
		this.top_id = top_id;
	}

	public Integer getTop_total() {
		return top_total;
	}

	public void setTop_total(Integer top_total) {
		this.top_total = top_total;
	}
    
	public Integer getTop_invention() {
		return top_invention;
	}

	public void setTop_invention(Integer top_invention) {
		this.top_invention = top_invention;
	}

	public Integer getTop_utilitymodel() {
		return top_utilitymodel;
	}

	public void setTop_utilitymodel(Integer top_utilitymodel) {
		this.top_utilitymodel = top_utilitymodel;
	}

	public Date getTop_deadline() {
		return top_deadline;
	}

	public void setTop_deadline(Date top_deadline) {
		this.top_deadline = top_deadline;
	}

	public int getTop_serialnumber() {
		return top_serialnumber;
	}

	public void setTop_serialnumber(int top_serialnumber) {
		this.top_serialnumber = top_serialnumber;
	}

	public String getTop_comments() {
		return top_comments;
	}

	public void setTop_comments(String top_comments) {
		this.top_comments = top_comments;
	}

	public String getTop_college() {
		return top_college;
	}

	public void setTop_college(String top_college) {
		this.top_college = top_college;
	}

	public int getTop_isnull() {
		return top_isnull;
	}

	public void setTop_isnull(int top_isnull) {
		this.top_isnull = top_isnull;
	}
     
     
}
