package cn.edu.xmu.entity;

import java.util.Date;

/**
 * 
 * @author luo
 * 学校教学科研单位 实体类
 * date 2015-06-29
 */

public class TeachScientific {
	private int ts_id;  //id
	private String ts_name; //行政单位名称
	private String ts_number;  //行政单位号
	private String ts_head; //单位负责人
	private int ts_serialnumber; //序号
	private Date ts_deadline; //截止日期
	private String ts_college;//所属学院
	private String ts_comments;//审核意见
	private int isnull;
	//全参构造函数
	public TeachScientific(int ts_id, String ts_name, String ts_number,
			String ts_head, int ts_serialnumber, Date ts_deadline,
			String ts_college, String ts_comments,int isnull) {
		super();
		this.ts_id = ts_id;
		this.ts_name = ts_name;
		this.ts_number = ts_number;
		this.ts_head = ts_head;
		this.ts_serialnumber = ts_serialnumber;
		this.ts_deadline = ts_deadline;
		this.ts_college = ts_college;
		this.ts_comments = ts_comments;
		this.isnull = isnull;
	}
	
	public TeachScientific(String ts_name, String ts_number, String ts_head,String ts_college,int isnull) {
		super();
		this.ts_name = ts_name;
		this.ts_number = ts_number;
		this.ts_head = ts_head;
		this.ts_college = ts_college;
		this.isnull = isnull;
	}
	
	

	public TeachScientific(String ts_name, String ts_number, String ts_head,
			int ts_serialnumber, String ts_college, String ts_comments,
			int isnull) {
		super();
		this.ts_name = ts_name;
		this.ts_number = ts_number;
		this.ts_head = ts_head;
		this.ts_serialnumber = ts_serialnumber;
		this.ts_college = ts_college;
		this.ts_comments = ts_comments;
		this.isnull = isnull;
	}

	public int getTs_id() {
		return ts_id;
	}
	public void setTs_id(int ts_id) {
		this.ts_id = ts_id;
	}
	public String getTs_name() {
		return ts_name;
	}
	public void setTs_name(String ts_name) {
		this.ts_name = ts_name;
	}
	public String getTs_number() {
		return ts_number;
	}
	public void setTs_number(String ts_number) {
		this.ts_number = ts_number;
	}
	public String getTs_head() {
		return ts_head;
	}
	public void setTs_head(String ts_head) {
		this.ts_head = ts_head;
	}
	public int getTs_serialnumber() {
		return ts_serialnumber;
	}
	public void setTs_serialnumber(int ts_serialnumber) {
		this.ts_serialnumber = ts_serialnumber;
	}
	public Date getTs_deadline() {
		return ts_deadline;
	}
	public void setTs_deadline(Date ts_deadline) {
		this.ts_deadline = ts_deadline;
	}
	public String getTs_college() {
		return ts_college;
	}
	public void setTs_college(String ts_college) {
		this.ts_college = ts_college;
	}
	public String getTs_comments() {
		return ts_comments;
	}
	public void setTs_comments(String ts_comments) {
		this.ts_comments = ts_comments;
	}

	public int getIsnull() {
		return isnull;
	}

	public void setIsnull(int isnull) {
		this.isnull = isnull;
	}
	
	
	
}