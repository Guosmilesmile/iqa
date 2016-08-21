package cn.edu.xmu.entity;

import java.util.Date;

/**
 * 附表 未填表格说明
 * @author chunfeng
 *
 */
public class UnfinishedForm {
	private int uf_id;
	
	private String uf_formname;
	
	private String uf_accountfor;
	
	//所属学院
	private String uf_college;
	//序列号
	private int uf_serialnumber;
	//截止日期
	private Date uf_deadline;
	//审核意见
	private String uf_comments;
	
	private int uf_isnull;
	
	public UnfinishedForm(int uf_id, String uf_formname, String uf_accountfor, String uf_college,
	                      int uf_serialnumber, Date uf_deadline, String uf_comments, int uf_isnull){
		this.uf_id = uf_id;
		
		this.uf_formname = uf_formname;
		
		this.uf_accountfor = uf_accountfor;
		
		this.uf_college = uf_college;
		this.uf_serialnumber = uf_serialnumber;
		this.uf_deadline = uf_deadline;
		this.uf_comments = uf_comments;
		this.uf_isnull = uf_isnull;
	}
	
	public UnfinishedForm( String uf_formname, String uf_accountfor, String uf_college,
                           int uf_serialnumber, int uf_isnull){
           this.uf_formname = uf_formname;
           this.uf_accountfor = uf_accountfor;
           this.uf_college = uf_college;
           this.uf_serialnumber = uf_serialnumber;
           this.uf_isnull = uf_isnull;
     }

	public int getUf_id() {
		return uf_id;
	}

	public void setUf_id(int uf_id) {
		this.uf_id = uf_id;
	}

	public String getUf_formname() {
		return uf_formname;
	}

	public void setUf_formname(String uf_formname) {
		this.uf_formname = uf_formname;
	}

	public String getUf_accountfor() {
		return uf_accountfor;
	}

	public void setUf_accountfor(String uf_accountfor) {
		this.uf_accountfor = uf_accountfor;
	}

	public String getUf_college() {
		return uf_college;
	}

	public void setUf_college(String uf_college) {
		this.uf_college = uf_college;
	}

	public int getUf_serialnumber() {
		return uf_serialnumber;
	}

	public void setUf_serialnumber(int uf_serialnumber) {
		this.uf_serialnumber = uf_serialnumber;
	}

	public Date getUf_deadline() {
		return uf_deadline;
	}

	public void setUf_deadline(Date uf_deadline) {
		this.uf_deadline = uf_deadline;
	}

	public String getUf_comments() {
		return uf_comments;
	}

	public void setUf_comments(String uf_comments) {
		this.uf_comments = uf_comments;
	}

	public int getUf_isnull() {
		return uf_isnull;
	}

	public void setUf_isnull(int uf_isnull) {
		this.uf_isnull = uf_isnull;
	}
	
	
}
