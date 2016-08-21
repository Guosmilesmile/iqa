package cn.edu.xmu.entity;

import java.util.Date;

/**
 * 表7-4  教学质量管理与监控（时点）
 * @author chunfeng
 *
 */
public class TeachingQualityMonitor {
	private int tqm_id;
	//教学质量管理制度目录
	private String tqm_managesyscontent;
	//教学质量评估实施情况
	private String tqm_assessexe;
	//本科教学质量年度报告
	private String tqm_annualreport;
	
	//所属学院
	private String tqm_college;
	//序列号
	private int tqm_serialnumber;
	//截止日期
	private Date tqm_deadline;
	//审核意见
	private String tqm_comments;
	
	private int tqm_isnull;
	
	public TeachingQualityMonitor(int tqm_id, String tqm_managesyscontent, String tqm_assessexe, String tqm_annualreport,
	       String tqm_college, int tqm_serialnumber, Date tqm_deadline, String tqm_comments, int tqm_isnull){
		this.tqm_id = tqm_id;
		//教学质量管理制度目录
		this.tqm_managesyscontent = tqm_managesyscontent;
		//教学质量评估实施情况
		this.tqm_assessexe = tqm_assessexe;
		//本科教学质量年度报告
		this.tqm_annualreport = tqm_annualreport;
		
		//所属学院
		this.tqm_college = tqm_college;
		//序列号
		this.tqm_serialnumber = tqm_serialnumber;
		//截止日期
		this.tqm_deadline = tqm_deadline;
		//审核意见
		this.tqm_comments = tqm_comments;
		
		this.tqm_isnull = tqm_isnull;
	}
	
	public TeachingQualityMonitor(String tqm_managesyscontent, String tqm_assessexe, String tqm_annualreport,
		       String tqm_college, int tqm_serialnumber, int tqm_isnull){
			
			//教学质量管理制度目录
			this.tqm_managesyscontent = tqm_managesyscontent;
			//教学质量评估实施情况
			this.tqm_assessexe = tqm_assessexe;
			//本科教学质量年度报告
			this.tqm_annualreport = tqm_annualreport;
			
			//所属学院
			this.tqm_college = tqm_college;
			//序列号
			this.tqm_serialnumber = tqm_serialnumber;
			
			this.tqm_isnull = tqm_isnull;
		}

	public int getTqm_id() {
		return tqm_id;
	}

	public void setTqm_id(int tqm_id) {
		this.tqm_id = tqm_id;
	}

	public String getTqm_managesyscontent() {
		return tqm_managesyscontent;
	}

	public void setTqm_managesyscontent(String tqm_managesyscontent) {
		this.tqm_managesyscontent = tqm_managesyscontent;
	}

	public String getTqm_assessexe() {
		return tqm_assessexe;
	}

	public void setTqm_assessexe(String tqm_assessexe) {
		this.tqm_assessexe = tqm_assessexe;
	}

	public String getTqm_annualreport() {
		return tqm_annualreport;
	}

	public void setTqm_annualreport(String tqm_annualreport) {
		this.tqm_annualreport = tqm_annualreport;
	}

	public String getTqm_college() {
		return tqm_college;
	}

	public void setTqm_college(String tqm_college) {
		this.tqm_college = tqm_college;
	}

	public int getTqm_serialnumber() {
		return tqm_serialnumber;
	}

	public void setTqm_serialnumber(int tqm_serialnumber) {
		this.tqm_serialnumber = tqm_serialnumber;
	}

	public Date getTqm_deadline() {
		return tqm_deadline;
	}

	public void setTqm_deadline(Date tqm_deadline) {
		this.tqm_deadline = tqm_deadline;
	}

	public String getTqm_comments() {
		return tqm_comments;
	}

	public void setTqm_comments(String tqm_comments) {
		this.tqm_comments = tqm_comments;
	}

	public int getTqm_isnull() {
		return tqm_isnull;
	}

	public void setTqm_isnull(int tqm_isnull) {
		this.tqm_isnull = tqm_isnull;
	}
	
	
}
