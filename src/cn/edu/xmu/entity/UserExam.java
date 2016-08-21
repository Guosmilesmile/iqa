package cn.edu.xmu.entity;

import java.util.Date;

public class UserExam {
	private int id;
	private String tablename;
	private String filluserid;
	private Date filldate;
	private int situation;
	private int tableid;
	
	public UserExam(int id, String tablename, String filluserid, Date filldate,
			int situation, int tableid) {
		super();
		this.id = id;
		this.tablename = tablename;
		this.filluserid = filluserid;
		this.filldate = filldate;
		this.situation = situation;
		this.tableid = tableid;
	}
	public int getTableid() {
		return tableid;
	}
	public void setTableid(int tableid) {
		this.tableid = tableid;
	}
	public UserExam(int id, String tablename, String filluserid, Date filldate,
			int situation) {
		super();
		this.id = id;
		this.tablename = tablename;
		this.filluserid = filluserid;
		this.filldate = filldate;
		this.situation = situation;
	}
	public UserExam() {
		super();
	}
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public String getTablename() {
		return tablename;
	}
	public void setTablename(String tablename) {
		this.tablename = tablename;
	}
	public String getFilluserid() {
		return filluserid;
	}
	public void setFilluserid(String filluserid) {
		this.filluserid = filluserid;
	}
	public Date getFilldate() {
		return filldate;
	}
	public void setFilldate(Date filldate) {
		this.filldate = filldate;
	}
	public int getSituation() {
		return situation;
	}
	public void setSituation(int situation) {
		this.situation = situation;
	}
	
}
