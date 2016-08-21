package cn.edu.xmu.entity;

import java.sql.Date;

/**
 * 
 * @author Lee
 * 表2-4-1 运动场
 * date 2015/7/13
 */
public class PlayGround {

	
	//序号
	private int pg_id;

	//校区
	private String pg_campus;
	
	//体育场地名称
	private String pg_groundname;
	
	//数量
	private int pg_amount;
	
	//室内面积
	private String pg_indoorarea;
	
	//室外面积
	private String pg_outdoorarea;
	
	//合计
	private String pg_totalarea;
	
	//序列号
	private int pg_serialnumber;
	
	//截止日期
	private Date pg_deadline;
	
	//审核意见
	private String pg_comments;
	
	//操作学院
	private String pg_college;
	
	//记录是否存在空值
	private int isnull;

	public int getPg_id() {
		return pg_id;
	}
	public void setPg_id(int pg_id) {
		this.pg_id = pg_id;
	}


	public String getPg_campus() {
		return pg_campus;
	}
	public void setPg_campus(String pg_campus) {
		this.pg_campus = pg_campus;
	}


	public String getPg_groundname() {
		return pg_groundname;
	}
	public void setPg_groundname(String pg_groundname) {
		this.pg_groundname = pg_groundname;
	}


	public int getPg_amount() {
		return pg_amount;
	}
	public void setPg_amount(int pg_amount) {
		this.pg_amount = pg_amount;
	}
	
	public String getPg_indoorarea() {
		return pg_indoorarea;
	}
	public void setPg_indoorarea(String pg_indoorarea) {
		this.pg_indoorarea = pg_indoorarea;
	}


	public String getPg_outdoorarea() {
		return pg_outdoorarea;
	}
	public void setPg_outdoorarea(String pg_outdoorarea) {
		this.pg_outdoorarea = pg_outdoorarea;
	}

	public String getPg_totalarea() {
		return pg_totalarea;
	}
	public void setPg_totalarea(String pg_totalarea) {
		this.pg_totalarea = pg_totalarea;
	}

	public int getPg_serialnumber() {
		return pg_serialnumber;
	}

	public void setPg_serialnumber(int pg_serialnumber) {
		this.pg_serialnumber = pg_serialnumber;
	}


	public Date getPg_deadline() {
		return pg_deadline;
	}

	public void setPg_deadline(Date pg_deadline) {
		this.pg_deadline = pg_deadline;
	}

	public String getPg_comments() {
		return pg_comments;
	}

	public void setPg_comments(String pg_comments) {
		this.pg_comments = pg_comments;
	}

	public String getPg_college() {
		return pg_college;
	}


	public void setPg_college(String pg_college) {
		this.pg_college = pg_college;
	}


	public int getIsnull() {
		return isnull;
	}

	public void setIsnull(int isnull) {
		this.isnull = isnull;
	}

	public PlayGround() {
		super();
	}


	public PlayGround(int pg_id, String pg_campus, String pg_groundname,
			int pg_amount, String pg_indoorarea, String pg_outdoorarea,
			String pg_totalarea, int pg_serialnumber, String pg_comments, String pg_college,
			int isnull) {
		super();
		this.pg_id = pg_id;
		this.pg_campus = pg_campus;
		this.pg_groundname = pg_groundname;
		this.pg_amount = pg_amount;
		this.pg_indoorarea = pg_indoorarea;
		this.pg_outdoorarea = pg_outdoorarea;
		this.pg_totalarea = pg_totalarea;
		this.pg_serialnumber = pg_serialnumber;
		this.pg_comments = pg_comments;
		this.pg_college = pg_college;
		this.isnull = isnull;
	}




	public PlayGround(int pg_id, String pg_campus, String pg_groundname,
			int pg_amount, String pg_indoorarea, String pg_outdoorarea,
			String pg_totalarea, int pg_serialnumber, Date pg_deadline,
			String pg_comments, String pg_college, int isnull) {
		super();
		this.pg_id = pg_id;
		this.pg_campus = pg_campus;
		this.pg_groundname = pg_groundname;
		this.pg_amount = pg_amount;
		this.pg_indoorarea = pg_indoorarea;
		this.pg_outdoorarea = pg_outdoorarea;
		this.pg_totalarea = pg_totalarea;
		this.pg_serialnumber = pg_serialnumber;
		this.pg_deadline = pg_deadline;
		this.pg_comments = pg_comments;
		this.pg_college = pg_college;
		this.isnull = isnull;
	}
	public PlayGround(String pg_campus, String pg_groundname, int pg_amount,
			String pg_indoorarea, String pg_outdoorarea, String pg_totalarea,
			int pg_serialnumber, String pg_college, int isnull) {
		super();
		this.pg_campus = pg_campus;
		this.pg_groundname = pg_groundname;
		this.pg_amount = pg_amount;
		this.pg_indoorarea = pg_indoorarea;
		this.pg_outdoorarea = pg_outdoorarea;
		this.pg_totalarea = pg_totalarea;
		this.pg_serialnumber = pg_serialnumber;
		this.pg_college = pg_college;
		this.isnull = isnull;
	}
	public PlayGround(String pg_campus, String pg_groundname, int pg_amount,
			String pg_indoorarea, String pg_outdoorarea, String pg_totalarea,
			String pg_college, int isnull) {
		super();
		this.pg_campus = pg_campus;
		this.pg_groundname = pg_groundname;
		this.pg_amount = pg_amount;
		this.pg_indoorarea = pg_indoorarea;
		this.pg_outdoorarea = pg_outdoorarea;
		this.pg_totalarea = pg_totalarea;
		this.pg_college = pg_college;
		this.isnull = isnull;
	}
	
	
	
}
