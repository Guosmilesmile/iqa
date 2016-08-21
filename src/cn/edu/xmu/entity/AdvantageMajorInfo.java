package cn.edu.xmu.entity;

import java.sql.Date;

/**
 * 附表4-2-3-1优势专业情况（时点）
 * @author yue
 *
 */
public class AdvantageMajorInfo {

	private int ami_id;
	private String ami_institute;//学院
	private String ami_name;//名称
	private String ami_type;//类别
	private Integer ami_nationallevel;//国家级
	private Integer ami_provinciallevel;//省级
	private String ami_remark;//备注
	private int ami_serialnumber;//序列号
	private	Date ami_deadline;//截止日期
	private String ami_college;//所属学院
	private String ami_comments;//审核意见
	private int isnull;
	public AdvantageMajorInfo(int ami_id, String ami_institute, String ami_name,
			String ami_type, Integer ami_nationallevel, Integer ami_provinciallevel, String ami_remark, int ami_serialnumber,
			Date ami_deadline, String ami_college, String ami_comments,int isnull) {
		super();
		this.ami_id = ami_id;
		this.ami_institute = ami_institute;
		this.ami_name = ami_name;
		this.ami_type = ami_type;
		this.ami_nationallevel = ami_nationallevel;
		this.ami_provinciallevel = ami_provinciallevel;
		this.ami_remark = ami_remark;
		this.ami_serialnumber = ami_serialnumber;
		this.ami_deadline = ami_deadline;
		this.ami_college = ami_college;
		this.ami_comments = ami_comments;
		this.isnull = isnull;
	}
	public AdvantageMajorInfo( String ami_institute, String ami_name, String ami_type,
			Integer ami_nationallevel, Integer ami_provinciallevel, String ami_remark, int ami_serialnumber, String ami_college,
			String ami_comments,int isnull) {
		super();
		this.ami_institute = ami_institute;
		this.ami_name = ami_name;
		this.ami_type = ami_type;
		this.ami_nationallevel = ami_nationallevel;
		this.ami_provinciallevel = ami_provinciallevel;
		this.ami_remark = ami_remark;
		this.ami_serialnumber = ami_serialnumber;
		this.ami_college = ami_college;
		this.ami_comments = ami_comments;
		this.isnull = isnull;
	}
	public AdvantageMajorInfo( String ami_institute, String ami_name, String ami_type,
			Integer ami_nationallevel, Integer ami_provinciallevel, String ami_remark,
			String ami_college,int isnull) {
		super();
		this.ami_institute = ami_institute;
		this.ami_name = ami_name;
		this.ami_type = ami_type;
		this.ami_nationallevel = ami_nationallevel;
		this.ami_provinciallevel = ami_provinciallevel;
		this.ami_remark = ami_remark;
		this.ami_college = ami_college;
		this.ami_comments = "";
		this.isnull = isnull;
	}
	public int getAmi_id() {
		return ami_id;
	}
	public void setAmi_id(int ami_id) {
		this.ami_id = ami_id;
	}
	public String getAmi_institute() {
		return ami_institute;
	}
	public void setAmi_institute(String ami_institute) {
		this.ami_institute = ami_institute;
	}
	public String getAmi_name() {
		return ami_name;
	}
	public void setAmi_name(String ami_name) {
		this.ami_name = ami_name;
	}
	public String getAmi_type() {
		return ami_type;
	}
	public void setAmi_type(String ami_type) {
		this.ami_type = ami_type;
	}
	public Integer getAmi_nationallevel() {
		return ami_nationallevel;
	}
	public void setAmi_nationallevel(Integer ami_nationallevel) {
		this.ami_nationallevel = ami_nationallevel;
	}
	public Integer getAmi_provinciallevel() {
		return ami_provinciallevel;
	}
	public void setAmi_provinciallevel(Integer ami_provinciallevel) {
		this.ami_provinciallevel = ami_provinciallevel;
	}
	public String getAmi_remark() {
		return ami_remark;
	}
	public void setAmi_remark(String ami_remark) {
		this.ami_remark = ami_remark;
	}
	public int getAmi_serialnumber() {
		return ami_serialnumber;
	}
	public void setAmi_serialnumber(int ami_serialnumber) {
		this.ami_serialnumber = ami_serialnumber;
	}
	public Date getAmi_deadline() {
		return ami_deadline;
	}
	public void setAmi_deadline(Date ami_deadline) {
		this.ami_deadline = ami_deadline;
	}
	public String getAmi_college() {
		return ami_college;
	}
	public void setAmi_college(String ami_college) {
		this.ami_college = ami_college;
	}
	public String getAmi_comments() {
		return ami_comments;
	}
	public void setAmi_comments(String ami_comments) {
		this.ami_comments = ami_comments;
	}
	public int getIsnull() {
		return isnull;
	}
	public void setIsnull(int isnull) {
		this.isnull = isnull;
	}
	
}
