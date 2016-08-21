package cn.edu.xmu.entity;

import java.sql.Date;

/**
 * 附表6-1-6-1  各专业（大类）招生情况（时点）
 * @author yue
 *
 */
public class MajorEnrollInfo {

	private int mei_id;
	private String mei_majorcode;//校内专业（大类）代码
	private String mei_majorname;//校内专业（大类）名称
	private Integer mei_plannumber;//招生计划数（人）
	private Integer mei_enrollnumber;//实际录取数（人）
	private int mei_serialnumber;//序列号
	private Date mei_deadline;//截止日期
	private String mei_college;//所属学院
	private String mei_comments;//审核意见
	private int isnull;//记录是否存在空值
	
	public MajorEnrollInfo(int mei_id, String mei_majorcode, String mei_majorname, Integer mei_plannumber,
			Integer mei_enrollnumber, int mei_serialnumber, Date mei_deadline, String mei_college, 
			String mei_comments,int isnull) {
		super();
		this.mei_id = mei_id;
		this.mei_majorcode = mei_majorcode;
		this.mei_majorname = mei_majorname;
		this.mei_plannumber = mei_plannumber;
		this.mei_enrollnumber = mei_enrollnumber;
		this.mei_serialnumber = mei_serialnumber;
		this.mei_deadline = mei_deadline;
		this.mei_college = mei_college;
		this.mei_comments = mei_comments;
		this.isnull = isnull;
	}
	public MajorEnrollInfo( String mei_majorcode, String mei_majorname, Integer mei_plannumber,
			Integer mei_enrollnumber, int mei_serialnumber, String mei_college, 
			String mei_comments,int isnull) {
		super();
		this.mei_majorcode = mei_majorcode;
		this.mei_majorname = mei_majorname;
		this.mei_plannumber = mei_plannumber;
		this.mei_enrollnumber = mei_enrollnumber;
		this.mei_serialnumber = mei_serialnumber;
		this.mei_college = mei_college;
		this.mei_comments = mei_comments;
		this.isnull = isnull;
	}
	public MajorEnrollInfo( String mei_majorcode, String mei_majorname, Integer mei_plannumber,
			Integer mei_enrollnumber, String mei_college,int isnull) {
		super();
		this.mei_majorcode = mei_majorcode;
		this.mei_majorname = mei_majorname;
		this.mei_plannumber = mei_plannumber;
		this.mei_enrollnumber = mei_enrollnumber;
		this.mei_college = mei_college;
		this.mei_comments = "";
		this.isnull = isnull;
	}
	public int getMei_id() {
		return mei_id;
	}
	public void setMei_id(int mei_id) {
		this.mei_id = mei_id;
	}
	public String getMei_majorcode() {
		return mei_majorcode;
	}
	public void setMei_majorcode(String mei_majorcode) {
		this.mei_majorcode = mei_majorcode;
	}
	public String getMei_majorname() {
		return mei_majorname;
	}
	public void setMei_majorname(String mei_majorname) {
		this.mei_majorname = mei_majorname;
	}
	public Integer getMei_plannumber() {
		return mei_plannumber;
	}
	public void setMei_plannumber(Integer mei_plannumber) {
		this.mei_plannumber = mei_plannumber;
	}
	public Integer getMei_enrollnumber() {
		return mei_enrollnumber;
	}
	public void setMei_enrollnumber(Integer mei_enrollnumber) {
		this.mei_enrollnumber = mei_enrollnumber;
	}
	public int getMei_serialnumber() {
		return mei_serialnumber;
	}
	public void setMei_serialnumber(int mei_serialnumber) {
		this.mei_serialnumber = mei_serialnumber;
	}
	public Date getMei_deadline() {
		return mei_deadline;
	}
	public void setMei_deadline(Date mei_deadline) {
		this.mei_deadline = mei_deadline;
	}
	public String getMei_college() {
		return mei_college;
	}
	public void setMei_college(String mei_college) {
		this.mei_college = mei_college;
	}
	public String getMei_comments() {
		return mei_comments;
	}
	public void setMei_comments(String mei_comments) {
		this.mei_comments = mei_comments;
	}
	public int getIsnull() {
		return isnull;
	}
	public void setIsnull(int isnull) {
		this.isnull = isnull;
	}
	
}
