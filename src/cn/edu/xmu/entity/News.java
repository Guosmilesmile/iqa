package cn.edu.xmu.entity;

import java.util.Date;

public class News {

	private int n_id;
	private String n_titles;
	private String n_author;
	private String n_summary;
	private String n_content;
	private String n_imgurl;
	private Date n_publishtime;
	private String n_class;
	private String n_subclass;
	private String n_version;

	public int getN_id() {
		return n_id;
	}

	public void setN_id(int n_id) {
		this.n_id = n_id;
	}

	public String getN_titles() {
		return n_titles;
	}

	public void setN_titles(String n_titles) {
		this.n_titles = n_titles;
	}

	public String getN_author() {
		return n_author;
	}

	public void setN_author(String n_author) {
		this.n_author = n_author;
	}

	public String getN_summary() {
		return n_summary;
	}

	public void setN_summary(String n_summary) {
		this.n_summary = n_summary;
	}

	public String getN_content() {
		return n_content;
	}

	public void setN_content(String n_content) {
		this.n_content = n_content;
	}

	public Date getN_publishtime() {
		return n_publishtime;
	}

	public void setN_publishtime(Date n_publishtime) {
		this.n_publishtime = n_publishtime;
	}

	public String getN_imgurl() {
		return n_imgurl;
	}

	public void setN_imgurl(String n_imgurl) {
		this.n_imgurl = n_imgurl;
	}

	public String getN_class() {
		return n_class;
	}

	public void setN_class(String n_class) {
		this.n_class = n_class;
	}

	public String getN_subclass() {
		return n_subclass;
	}

	public void setN_subclass(String n_subclass) {
		this.n_subclass = n_subclass;
	}

	public String getN_version() {
		return n_version;
	}

	public void setN_version(String n_version) {
		this.n_version = n_version;
	}

	public News(String n_titles, String n_author, String n_content) {
		super();
		this.n_titles = n_titles;
		this.n_author = n_author;
		this.n_content = n_content;
	}

	public News(String n_titles, String n_author, String n_summary,
			String n_content, String n_imgurl, String n_class,
			String n_subclass, String n_version) {
		super();
		this.n_titles = n_titles;
		this.n_author = n_author;
		this.n_summary = n_summary;
		this.n_content = n_content;
		this.n_imgurl = n_imgurl;
		this.n_class = n_class;
		this.n_subclass = n_subclass;
		this.n_version = n_version;
	}

	public News(String n_titles, String n_author, String n_summary,
			String n_content, String n_imgurl, Date n_publishtime,
			String n_class, String n_subclass, String n_version) {
		super();
		this.n_titles = n_titles;
		this.n_author = n_author;
		this.n_summary = n_summary;
		this.n_content = n_content;
		this.n_imgurl = n_imgurl;
		this.n_publishtime = n_publishtime;
		this.n_class = n_class;
		this.n_subclass = n_subclass;
		this.n_version = n_version;
	}

	public News(int n_id, String n_titles, String n_author, String n_summary,
			String n_content, String n_imgurl, Date n_publishtime,
			String n_class, String n_subclass, String n_version) {
		super();
		this.n_id = n_id;
		this.n_titles = n_titles;
		this.n_author = n_author;
		this.n_summary = n_summary;
		this.n_content = n_content;
		this.n_imgurl = n_imgurl;
		this.n_publishtime = n_publishtime;
		this.n_class = n_class;
		this.n_subclass = n_subclass;
		this.n_version = n_version;
	}

	public News(int n_id, String n_titles, String n_author, String n_summary,
			String n_content, String n_imgurl, String n_class,
			String n_subclass, String n_version) {
		super();
		this.n_id = n_id;
		this.n_titles = n_titles;
		this.n_author = n_author;
		this.n_summary = n_summary;
		this.n_content = n_content;
		this.n_imgurl = n_imgurl;
		this.n_class = n_class;
		this.n_subclass = n_subclass;
		this.n_version = n_version;
	}

	public News() {
		super();
	}

}
