package cn.edu.xmu.entity;

import java.sql.Date;

/**
 * 表6-1-6-2 各专业（大类）报到情况（时点）
 * @author yue
 *
 */
public class MajorRegisterInfo {
	
	private int mri_id;
	private String mri_majorcode;//校内专业（大类）代码
	private String mri_majorname;//校内专业（大类）名称
	private Integer mri_registernumber;//实际报到数（人）
	private int mri_serialnumber;//序列号
	private Date mri_deadline;//截止日期
	private String mri_college;//所属学院
	private String mri_comments;//审核意见
	private int isnull;//记录是否存在空值
	
	public MajorRegisterInfo(int mri_id, String mri_majorcode, String mri_majorname, Integer mri_registernumber,
			int mri_serialnumber, Date mri_deadline, String mri_college, String mri_comments,int isnull) {
		super();
		this.mri_id = mri_id;
		this.mri_majorcode = mri_majorcode;
		this.mri_majorname = mri_majorname;
		this.mri_registernumber = mri_registernumber;
		this.mri_serialnumber = mri_serialnumber;
		this.mri_deadline = mri_deadline;
		this.mri_college = mri_college;
		this.mri_comments = mri_comments;
		this.isnull = isnull;
	}
	public MajorRegisterInfo(String mri_majorcode, String mri_majorname, Integer mri_registernumber, int mri_serialnumber,
			String mri_college, String mri_comments,int isnull) {
		super();
		this.mri_majorcode = mri_majorcode;
		this.mri_majorname = mri_majorname;
		this.mri_registernumber = mri_registernumber;
		this.mri_serialnumber = mri_serialnumber;
		this.mri_college = mri_college;
		this.mri_comments = mri_comments;
		this.isnull = isnull;
	}
	public MajorRegisterInfo(String mri_majorcode, String mri_majorname, Integer mri_registernumber,String mri_college,int isnull) {
		super();
		this.mri_majorcode = mri_majorcode;
		this.mri_majorname = mri_majorname;
		this.mri_registernumber = mri_registernumber;
		this.mri_college = mri_college;
		this.mri_comments = "";
		this.isnull = isnull;
	}
	public int getMri_id() {
		return mri_id;
	}
	public void setMri_id(int mri_id) {
		this.mri_id = mri_id;
	}
	public String getMri_majorcode() {
		return mri_majorcode;
	}
	public void setMri_majorcode(String mri_majorcode) {
		this.mri_majorcode = mri_majorcode;
	}
	public String getMri_majorname() {
		return mri_majorname;
	}
	public void setMri_majorname(String mri_majorname) {
		this.mri_majorname = mri_majorname;
	}
	public Integer getMri_registernumber() {
		return mri_registernumber;
	}
	public void setMri_registernumber(Integer mri_registernumber) {
		this.mri_registernumber = mri_registernumber;
	}
	public int getMri_serialnumber() {
		return mri_serialnumber;
	}
	public void setMri_serialnumber(int mri_serialnumber) {
		this.mri_serialnumber = mri_serialnumber;
	}
	public Date getMri_deadline() {
		return mri_deadline;
	}
	public void setMri_deadline(Date mri_deadline) {
		this.mri_deadline = mri_deadline;
	}
	public String getMri_college() {
		return mri_college;
	}
	public void setMri_college(String mri_college) {
		this.mri_college = mri_college;
	}
	public String getMri_comments() {
		return mri_comments;
	}
	public void setMri_comments(String mri_comments) {
		this.mri_comments = mri_comments;
	}
	public int getIsnull() {
		return isnull;
	}
	public void setIsnull(int isnull) {
		this.isnull = isnull;
	}
	
}
