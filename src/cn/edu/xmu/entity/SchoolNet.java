package cn.edu.xmu.entity;

import java.util.Date;

/**
 * 
 * @author Luo
 * 校园网 实体类
 * 全参构造函数
 * date 2015-07-03
 *
 */
public class SchoolNet {
	private int sn_id;
	//校园网主干带宽（Mbps）
	private Integer sn_backbonebandwidth;
	//校园网出口带宽（Mbps）
	private Integer sn_exportbandwidth;
	//网络接入信息点数量（个）
	private Integer sn_informationcount;
	//序列号
	private int sn_serialnumber;
	//截止日期
	private Date sn_deadline;
	//所属学院
	private String sn_college;
	//审核意见
	private String sn_comments;
	
	private int isnull;

	public SchoolNet(Integer sn_backbonebandwidth, Integer sn_exportbandwidth,
			Integer sn_informationcount, int sn_serialnumber,
			String sn_college, String sn_comments, int isnull) {
		super();
		this.sn_backbonebandwidth = sn_backbonebandwidth;
		this.sn_exportbandwidth = sn_exportbandwidth;
		this.sn_informationcount = sn_informationcount;
		this.sn_serialnumber = sn_serialnumber;
		this.sn_college = sn_college;
		this.sn_comments = sn_comments;
		this.isnull = isnull;
	}

	public SchoolNet(int sn_id, Integer sn_backbonebandwidth,
			Integer sn_exportbandwidth, Integer sn_informationcount,
			int sn_serialnumber, String sn_comments, int isnull,String sn_college) {
		super();
		this.sn_id = sn_id;
		this.sn_backbonebandwidth = sn_backbonebandwidth;
		this.sn_exportbandwidth = sn_exportbandwidth;
		this.sn_informationcount = sn_informationcount;
		this.sn_serialnumber = sn_serialnumber;
		this.sn_comments = sn_comments;
		this.isnull = isnull;
		this.sn_college = sn_college;
	}

	public SchoolNet(Integer sn_backbonebandwidth, Integer sn_exportbandwidth,
			Integer sn_informationcount, String sn_college, int isnull) {
		super();
		this.sn_backbonebandwidth = sn_backbonebandwidth;
		this.sn_exportbandwidth = sn_exportbandwidth;
		this.sn_informationcount = sn_informationcount;
		this.sn_college = sn_college;
		this.isnull = isnull;
	}

	public int getSn_id() {
		return sn_id;
	}

	public void setSn_id(int sn_id) {
		this.sn_id = sn_id;
	}

	public Integer getSn_backbonebandwidth() {
		return sn_backbonebandwidth;
	}

	public void setSn_backbonebandwidth(Integer sn_backbonebandwidth) {
		this.sn_backbonebandwidth = sn_backbonebandwidth;
	}

	public Integer getSn_exportbandwidth() {
		return sn_exportbandwidth;
	}

	public void setSn_exportbandwidth(Integer sn_exportbandwidth) {
		this.sn_exportbandwidth = sn_exportbandwidth;
	}

	public Integer getSn_informationcount() {
		return sn_informationcount;
	}

	public void setSn_informationcount(Integer sn_informationcount) {
		this.sn_informationcount = sn_informationcount;
	}

	public int getSn_serialnumber() {
		return sn_serialnumber;
	}

	public void setSn_serialnumber(int sn_serialnumber) {
		this.sn_serialnumber = sn_serialnumber;
	}

	public Date getSn_deadline() {
		return sn_deadline;
	}

	public void setSn_deadline(Date sn_deadline) {
		this.sn_deadline = sn_deadline;
	}

	public String getSn_college() {
		return sn_college;
	}

	public void setSn_college(String sn_college) {
		this.sn_college = sn_college;
	}

	public String getSn_comments() {
		return sn_comments;
	}

	public void setSn_comments(String sn_comments) {
		this.sn_comments = sn_comments;
	}

	public int getIsnull() {
		return isnull;
	}

	public void setIsnull(int isnull) {
		this.isnull = isnull;
	}
	
	

}
