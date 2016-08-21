package cn.edu.xmu.entity;

import java.sql.Date;


public class TableList implements Comparable<TableList>{
	private Integer t_tableid;
	private String t_tablename;
	private int t_publish;
	private String t_tablename_db;
	private Date datetime;
	private String jindu;
	
	
	public TableList(int t_tableid, String t_tablename, int t_publish,
			Date datetime) {
		super();
		this.t_tableid = t_tableid;
		this.t_tablename = t_tablename;
		this.t_publish = t_publish;
		this.datetime = datetime;
	}

	public TableList(int t_tableid, String t_tablename, int t_publish,
			String t_tablename_db, Date datetime) {
		super();
		this.t_tableid = t_tableid;
		this.t_tablename = t_tablename;
		this.t_publish = t_publish;
		this.t_tablename_db = t_tablename_db;
		this.datetime = datetime;
	}

	public Date getDatetime() {
		return datetime;
	}

	public void setDatetime(Date datetime) {
		this.datetime = datetime;
	}

	public TableList(String t_tablename, int t_publish, String t_tablename_db) {
		super();
		this.t_tablename = t_tablename;
		this.t_publish = t_publish;
		this.t_tablename_db = t_tablename_db;
	}
	/*public TableList(int t_tableid, String t_tablename) {
		super();
		this.t_tableid = t_tableid;
		this.t_tablename = t_tablename;
	}*/
	
	public String getT_tablename_db() {
		return t_tablename_db;
	}
	public void setT_tablename_db(String t_tablename_db) {
		this.t_tablename_db = t_tablename_db;
	}
	
	public TableList(int t_tableid, String t_tablename, int t_publish) {
		super();
		this.t_tableid = t_tableid;
		this.t_tablename = t_tablename;
		this.t_publish = t_publish;
	}
	public TableList(int t_tableid, String t_tablename) {
		super();
		this.t_tableid = t_tableid;
		this.t_tablename = t_tablename;
	}
	public Integer getT_tableid() {
		return t_tableid;
	}
	public int getT_publish() {
		return t_publish;
	}
	public void setT_publish(int t_publish) {
		this.t_publish = t_publish;
	}
	public void setT_tableid(Integer t_tableid) {
		this.t_tableid = t_tableid;
	}
	public String getT_tablename() {
		return t_tablename;
	}
	public void setT_tablename(String t_tablename) {
		this.t_tablename = t_tablename;
	}

	public String getJindu() {
		return jindu;
	}

	public void setJindu(String jindu) {
		this.jindu = jindu;
	}

	public TableList(int t_tableid, String t_tablename, String jindu) {
		super();
		this.t_tableid = t_tableid;
		this.t_tablename = t_tablename;
		this.jindu = jindu;
	}

	@Override
	public int compareTo(TableList arg0) {
		return this.getT_tableid().compareTo(arg0.getT_tableid());
	}
	
	
	
}
