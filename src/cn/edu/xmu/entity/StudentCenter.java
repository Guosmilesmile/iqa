package cn.edu.xmu.entity;

import java.sql.Date;

/**
 * 2-4-2  运动场、学生活动中心
 * @author chunfeng
 *
 */
public class StudentCenter {
	 private int sc_id;
     private String sc_project; //项目
     private Integer sc_quantity; //数量
     private Float sc_area; //面积
     
     private String sc_college;
     private Date sc_deadline;
 	 private int sc_serialnumber;
 	 private String sc_comments;
 	 private int sc_isnull;
 	 
 	public StudentCenter() {
 		super();
 	}

     public StudentCenter(String sc_project,Integer sc_quantity, Float sc_area, int sc_isnull){
    	 super();
    	 this.sc_project = sc_project;
    	 this.sc_quantity = sc_quantity;
    	 this.sc_area = sc_area;
    	 this.sc_isnull = sc_isnull;
     }
     public StudentCenter(String sc_project,Integer sc_quantity, Float sc_area,String sc_college,int sc_serialnumber, int sc_isnull){
    	 super();
    	 this.sc_project = sc_project;
    	 this.sc_quantity = sc_quantity;
    	 this.sc_area = sc_area;
    	 this.sc_college = sc_college;
    	 this.sc_serialnumber = sc_serialnumber;
    	 this.sc_isnull = sc_isnull;
     }
     public StudentCenter(String sc_project,Integer sc_quantity, Float sc_area,String sc_college,Date sc_deadline, int sc_isnull){
    	 super();
    	 this.sc_project = sc_project;
    	 this.sc_quantity = sc_quantity;
    	 this.sc_area = sc_area;
    	 this.sc_college = sc_college;
    	 this.sc_deadline = sc_deadline;
    	 this.sc_isnull = sc_isnull;
     }
     public StudentCenter(String sc_project,Integer sc_quantity, Float sc_area,String sc_college,String sc_comments, int sc_isnull){
    	 super();
    	 this.sc_project = sc_project;
    	 this.sc_quantity = sc_quantity;
    	 this.sc_area = sc_area;
    	 this.sc_college = sc_college;
    	 this.sc_comments = sc_comments;
    	 this.sc_isnull = sc_isnull;
     }
     public StudentCenter(int sc_id,String sc_project,Integer sc_quantity, Float sc_area,String sc_college,Date sc_deadline,int sc_serialnumber,String sc_comments, int sc_isnull){
    	 super();
    	 this.sc_id = sc_id;
    	 this.sc_project = sc_project;
    	 this.sc_quantity = sc_quantity;
    	 this.sc_area = sc_area;
    	 this.sc_college = sc_college;
    	 this.sc_deadline = sc_deadline;
    	 this.sc_serialnumber = sc_serialnumber;
    	 this.sc_comments = sc_comments;
    	 this.sc_isnull = sc_isnull;
     }

	public int getSc_id() {
		return sc_id;
	}

	public void setSc_id(int sc_id) {
		this.sc_id = sc_id;
	}

	public String getSc_project() {
		return sc_project;
	}

	public void setSc_project(String sc_project) {
		this.sc_project = sc_project;
	}

	public Integer getSc_quantity() {
		return sc_quantity;
	}

	public void setSc_quantity(Integer sc_quantity) {
		this.sc_quantity = sc_quantity;
	}

	public Float getSc_area() {
		return sc_area;
	}

	public void setSc_area(Float sc_area) {
		this.sc_area = sc_area;
	}

	public String getSc_college() {
		return sc_college;
	}

	public void setSc_college(String sc_college) {
		this.sc_college = sc_college;
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
     
     
}
