package cn.edu.xmu.entity;

import java.util.Date;

/**
 * 
 * @author Luo
 * 附表6-1-9-2XXXX年全校获得辅修学位、获得辅修本科证书人数统计表 实体类
 * 全参构造函数
 * date 2015-07-10
 *
 */
public class MinorDegree {
	private int md_id;
	//序号
	private Integer md_order;
	//学院
	private String md_importcollege;
	//专业
	private String md_major;
	//授予辅修学位人数
	private Integer md_minordegreecount;
	//获得辅修证书人数
	private Integer md_minorcertificatecount;
	//序列号
	private int md_serialnumber;
	//截止日期
	private Date md_deadline;
	//所属学院
	private String md_college;
	//审核意见
	private String md_comments;
	
	private int isnull;
	

	public MinorDegree(int md_id, Integer md_order, String md_importcollege,
			String md_major, Integer md_minordegreecount,
			Integer md_minorcertificatecount, int md_serialnumber,
			Date md_deadline, String md_college, String md_comments, int isnull) {
		super();
		this.md_id = md_id;
		this.md_order = md_order;
		this.md_importcollege = md_importcollege;
		this.md_major = md_major;
		this.md_minordegreecount = md_minordegreecount;
		this.md_minorcertificatecount = md_minorcertificatecount;
		this.md_serialnumber = md_serialnumber;
		this.md_deadline = md_deadline;
		this.md_college = md_college;
		this.md_comments = md_comments;
		this.isnull = isnull;
	}



	public MinorDegree(Integer md_order, String md_importcollege,
			String md_major, Integer md_minordegreecount,
			Integer md_minorcertificatecount, int md_serialnumber,
			String md_college, String md_comments, int isnull) {
		super();
		this.md_order = md_order;
		this.md_importcollege = md_importcollege;
		this.md_major = md_major;
		this.md_minordegreecount = md_minordegreecount;
		this.md_minorcertificatecount = md_minorcertificatecount;
		this.md_serialnumber = md_serialnumber;
		this.md_college = md_college;
		this.md_comments = md_comments;
		this.isnull = isnull;
	}

	
	
	public MinorDegree(int md_id, Integer md_order, String md_importcollege,
			String md_major, Integer md_minordegreecount,
			Integer md_minorcertificatecount, int md_serialnumber,
			String md_comments, int isnull,String md_college) {
		super();
		this.md_id = md_id;
		this.md_order = md_order;
		this.md_importcollege = md_importcollege;
		this.md_major = md_major;
		this.md_minordegreecount = md_minordegreecount;
		this.md_minorcertificatecount = md_minorcertificatecount;
		this.md_serialnumber = md_serialnumber;
		this.md_comments = md_comments;
		this.isnull = isnull;
		this.md_college = md_college;
	}



	public MinorDegree(int md_id, Integer md_order, String md_importcollege,
			String md_major, Integer md_minordegreecount,
			Integer md_minorcertificatecount, String md_comments, int isnull) {
		super();
		this.md_id = md_id;
		this.md_order = md_order;
		this.md_importcollege = md_importcollege;
		this.md_major = md_major;
		this.md_minordegreecount = md_minordegreecount;
		this.md_minorcertificatecount = md_minorcertificatecount;
		this.md_comments = md_comments;
		this.isnull = isnull;
	}


	public MinorDegree(Integer md_order, String md_importcollege,
			String md_major, Integer md_minordegreecount,
			Integer md_minorcertificatecount, String md_college, int isnull) {
		super();
		this.md_order = md_order;
		this.md_importcollege = md_importcollege;
		this.md_major = md_major;
		this.md_minordegreecount = md_minordegreecount;
		this.md_minorcertificatecount = md_minorcertificatecount;
		this.md_college = md_college;
		this.isnull = isnull;
	}



	public int getMd_id() {
		return md_id;
	}



	public void setMd_id(int md_id) {
		this.md_id = md_id;
	}


	public Integer getMd_order() {
		return md_order;
	}



	public void setMd_order(Integer md_order) {
		this.md_order = md_order;
	}



	public String getMd_importcollege() {
		return md_importcollege;
	}



	public void setMd_importcollege(String md_importcollege) {
		this.md_importcollege = md_importcollege;
	}




	public String getMd_major() {
		return md_major;
	}



	public void setMd_major(String md_major) {
		this.md_major = md_major;
	}




	public Integer getMd_minordegreecount() {
		return md_minordegreecount;
	}



	public void setMd_minordegreecount(Integer md_minordegreecount) {
		this.md_minordegreecount = md_minordegreecount;
	}




	public Integer getMd_minorcertificatecount() {
		return md_minorcertificatecount;
	}



	public void setMd_minorcertificatecount(Integer md_minorcertificatecount) {
		this.md_minorcertificatecount = md_minorcertificatecount;
	}




	public int getMd_serialnumber() {
		return md_serialnumber;
	}



	public void setMd_serialnumber(int md_serialnumber) {
		this.md_serialnumber = md_serialnumber;
	}



	public Date getMd_deadline() {
		return md_deadline;
	}



	public void setMd_deadline(Date md_deadline) {
		this.md_deadline = md_deadline;
	}



	public String getMd_college() {
		return md_college;
	}



	public void setMd_college(String md_college) {
		this.md_college = md_college;
	}


	public String getMd_comments() {
		return md_comments;
	}



	public void setMd_comments(String md_comments) {
		this.md_comments = md_comments;
	}



	public int getIsnull() {
		return isnull;
	}



	public void setIsnull(int isnull) {
		this.isnull = isnull;
	}

	


}
