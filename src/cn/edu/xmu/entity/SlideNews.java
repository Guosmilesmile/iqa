package cn.edu.xmu.entity;

import java.util.Date;

//滑动新闻实体
public class SlideNews {

	private int sn_id;
	private String sn_titles;
	private String sn_content;
	private String sn_imgurl;
	private Date sn_publishtime;
	private String sn_version;

	public int getSn_id() {
		return sn_id;
	}

	public void setSn_id(int sn_id) {
		this.sn_id = sn_id;
	}

	public String getSn_titles() {
		return sn_titles;
	}

	public void setSn_titles(String sn_titles) {
		this.sn_titles = sn_titles;
	}

	public String getSn_content() {
		return sn_content;
	}

	public void setSn_content(String sn_content) {
		this.sn_content = sn_content;
	}

	public String getSn_imgurl() {
		return sn_imgurl;
	}

	public void setSn_imgurl(String sn_imgurl) {
		this.sn_imgurl = sn_imgurl;
	}

	public Date getSn_publishtime() {
		return sn_publishtime;
	}

	public void setSn_publishtime(Date sn_publishtime) {
		this.sn_publishtime = sn_publishtime;
	}

	public String getSn_version() {
		return sn_version;
	}

	public void setSn_version(String sn_version) {
		this.sn_version = sn_version;
	}

	public SlideNews() {
		super();
	}

	public SlideNews(String sn_titles, String sn_version, String sn_content,
			String sn_imgurl) {
		super();
		this.sn_titles = sn_titles;
		this.sn_version = sn_version;
		this.sn_content = sn_content;
		this.sn_imgurl = sn_imgurl;
	}

	public SlideNews(int sn_id, String sn_titles, String sn_content,
			String sn_imgurl) {
		super();
		this.sn_id = sn_id;
		this.sn_titles = sn_titles;
		this.sn_content = sn_content;
		this.sn_imgurl = sn_imgurl;
	}

	public SlideNews(int sn_id, String sn_titles, String sn_content,
			String sn_imgurl, Date sn_publishtime) {
		super();
		this.sn_id = sn_id;
		this.sn_titles = sn_titles;
		this.sn_content = sn_content;
		this.sn_imgurl = sn_imgurl;
		this.sn_publishtime = sn_publishtime;
	}

}
