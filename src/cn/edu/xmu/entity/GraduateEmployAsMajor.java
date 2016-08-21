package cn.edu.xmu.entity;

import java.sql.Date;

/**
 * 附表6-1-9-3应届本科毕业生分专业就业情况（时点）
 * @author chunfeng
 *
 */
public class GraduateEmployAsMajor {
      private int geam_id;
      private String geam_majorcodeinschool;  //校内专业代码
  	  private String geam_majornameinschool; //校内专业名称
  	  private Integer geam_graduatenum;
  	  
  	  private String geam_college;
  	  private Date geam_deadline;
      private int geam_serialnumber;
      private String geam_comments;
      private int geam_isnull;
      
      public GraduateEmployAsMajor(int geam_id, String geam_majorcodeinschool, String geam_majornameinschool, Integer geam_graduatenum,	  
    		  String geam_college, Date geam_deadline, int geam_serialnumber, String geam_comments, int geam_isnull){
    	  this.geam_id = geam_id;
    	  this.geam_majorcodeinschool = geam_majorcodeinschool;  //校内专业代码
    	  this.geam_majornameinschool = geam_majornameinschool; //校内专业名称
    	  this.geam_graduatenum = geam_graduatenum;
      	  
    	  this.geam_college = geam_college;
    	  this.geam_deadline = geam_deadline;
    	  this.geam_serialnumber = geam_serialnumber;
    	  this.geam_comments = geam_comments; 
    	  this.geam_isnull = geam_isnull;
      }
      
      public GraduateEmployAsMajor(String geam_majorcodeinschool, String geam_majornameinschool, Integer geam_graduatenum,	  
    		  String geam_college, int geam_serialnumber, int geam_isnull){  	  
   	  this.geam_majorcodeinschool = geam_majorcodeinschool;  //校内专业代码
   	  this.geam_majornameinschool = geam_majornameinschool; //校内专业名称
   	  this.geam_graduatenum = geam_graduatenum;
     	  
   	  this.geam_college = geam_college;
   	  this.geam_serialnumber = geam_serialnumber;
   	  this.geam_isnull = geam_isnull;
     }

	public int getGeam_id() {
		return geam_id;
	}

	public void setGeam_id(int geam_id) {
		this.geam_id = geam_id;
	}

	public String getGeam_majorcodeinschool() {
		return geam_majorcodeinschool;
	}

	public void setGeam_majorcodeinschool(String geam_majorcodeinschool) {
		this.geam_majorcodeinschool = geam_majorcodeinschool;
	}

	public String getGeam_majornameinschool() {
		return geam_majornameinschool;
	}

	public void setGeam_majornameinschool(String geam_majornameinschool) {
		this.geam_majornameinschool = geam_majornameinschool;
	}

	public Integer getGeam_graduatenum() {
		return geam_graduatenum;
	}

	public void setGeam_graduatenum(Integer geam_graduatenum) {
		this.geam_graduatenum = geam_graduatenum;
	}

	public Date getGeam_deadline() {
		return geam_deadline;
	}

	public void setGeam_deadline(Date geam_deadline) {
		this.geam_deadline = geam_deadline;
	}

	public int getGeam_serialnumber() {
		return geam_serialnumber;
	}

	public void setGeam_serialnumber(int geam_serialnumber) {
		this.geam_serialnumber = geam_serialnumber;
	}

	public String getGeam_comments() {
		return geam_comments;
	}

	public void setGeam_comments(String geam_comments) {
		this.geam_comments = geam_comments;
	}

	public String getGeam_college() {
		return geam_college;
	}

	public void setGeam_college(String geam_college) {
		this.geam_college = geam_college;
	}

	public int getGeam_isnull() {
		return geam_isnull;
	}

	public void setGeam_isnull(int geam_isnull) {
		this.geam_isnull = geam_isnull;
	}
      
      
}
