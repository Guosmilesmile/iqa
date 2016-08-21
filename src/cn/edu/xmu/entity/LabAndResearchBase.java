package cn.edu.xmu.entity;

import java.sql.Date;

/**
 * 
 * @author zsj
 * 1-5实验室和科研基地表
 */
public class LabAndResearchBase {
	private int    lr_id;  //id
	private String lr_name;  //实验室和科研基地名称
	private String lr_type; //类别
	private String lr_ifbuildtogether; //共建情况
	private String lr_ifopentonongraduate; //是否对本科生开放
	private int lr_serialnumber;//序列号
	private Date lr_deadline;//截止日期
	private String lr_college;//所属学院
	private String lr_comments;//审核意见
	
	//记录是否存在空值
	private int isnull;
	
	public int getLr_id() {
		return lr_id;
	}
	public void setLr_id(int lr_id) {
		this.lr_id = lr_id;
	}
	public String getLr_name() {
		return lr_name;
	}
	public void setLr_name(String lr_name) {
		this.lr_name = lr_name;
	}
	public String getLr_type() {
		return lr_type;
	}
	public void setLr_type(String lr_type) {
		this.lr_type = lr_type;
	}
	public String getLr_ifbuildtogether() {
		return lr_ifbuildtogether;
	}
	public void setLr_ifbuildtogether(String lr_ifbuildtogether) {
		this.lr_ifbuildtogether = lr_ifbuildtogether;
	}
	public String getLr_ifopentonongraduate() {
		return lr_ifopentonongraduate;
	}
	public void setLr_ifopentonongraduate(String lr_ifopentonongraduate) {
		this.lr_ifopentonongraduate = lr_ifopentonongraduate;
	}
	public int getLr_serialnumber() {
		return lr_serialnumber;
	}
	public void setLr_serialnumber(int lr_serialnumber) {
		this.lr_serialnumber = lr_serialnumber;
	}
	public Date getLr_deadline() {
		return lr_deadline;
	}
	public void setLr_deadline(Date lr_deadline) {
		this.lr_deadline = lr_deadline;
	}
	public String getLr_college() {
		return lr_college;
	}
	public void setLr_college(String lr_college) {
		this.lr_college = lr_college;
	}
	public String getLr_comments() {
		return lr_comments;
	}
	public void setLr_comments(String lr_comments) {
		this.lr_comments = lr_comments;
	}
	public int getIsnull() {
		return isnull;
	}
	public void setIsnull(int isnull) {
		this.isnull = isnull;
	}
	public LabAndResearchBase(String lr_name, String lr_type,
			String lr_ifbuildtogether, String lr_ifopentonongraduate,
			int lr_serialnumber, Date lr_deadline, String lr_college,
			String lr_comments, int isnull) {
		super();
		this.lr_name = lr_name;
		this.lr_type = lr_type;
		this.lr_ifbuildtogether = lr_ifbuildtogether;
		this.lr_ifopentonongraduate = lr_ifopentonongraduate;
		this.lr_serialnumber = lr_serialnumber;
		this.lr_deadline = lr_deadline;
		this.lr_college = lr_college;
		this.lr_comments = lr_comments;
		this.isnull = isnull;
	}
	public LabAndResearchBase(int lr_id, String lr_name, String lr_type,
			String lr_ifbuildtogether, String lr_ifopentonongraduate,
			int lr_serialnumber, Date lr_deadline, String lr_college,
			String lr_comments, int isnull) {
		super();
		this.lr_id = lr_id;
		this.lr_name = lr_name;
		this.lr_type = lr_type;
		this.lr_ifbuildtogether = lr_ifbuildtogether;
		this.lr_ifopentonongraduate = lr_ifopentonongraduate;
		this.lr_serialnumber = lr_serialnumber;
		this.lr_deadline = lr_deadline;
		this.lr_college = lr_college;
		this.lr_comments = lr_comments;
		this.isnull = isnull;
	}
	public LabAndResearchBase(String lr_name, String lr_type,
			String lr_ifbuildtogether, String lr_ifopentonongraduate,
			String lr_college, int isnull) {
		super();
		this.lr_name = lr_name;
		this.lr_type = lr_type;
		this.lr_ifbuildtogether = lr_ifbuildtogether;
		this.lr_ifopentonongraduate = lr_ifopentonongraduate;
		this.lr_college = lr_college;
		this.isnull = isnull;
	}

	
	
}
