package cn.edu.xmu.entity;

import java.sql.Date;

/**
 * 表3-6-4  教师发表论文数 (自然年)
 * @author chunfeng
 *
 */
public class TeacherPublishThesis {
    private int tpt_id;
    private Integer tpt_total;  //总数
    private Integer tpt_sci;  //SCI
    private Integer tpt_ssci;  //SSCI
    private Integer tpt_ei; //EI
    private Integer tpt_istp;  //ISTP
    private Integer tpt_domesic; //国内核心期刊
    
    private String tpt_college;
    private Date tpt_deadline;
    private int tpt_serialnumber;
    private String tpt_comments;
    private int tpt_isnull;
    
    public TeacherPublishThesis() {
 		super();
 	}

     public TeacherPublishThesis(Integer tpt_total,Integer tpt_sci, Integer tpt_ssci, Integer tpt_ei, Integer tpt_istp, Integer tpt_domesic, int tpt_isnull){
    	 super();
    	 this.tpt_total = tpt_total;
    	 this.tpt_sci = tpt_sci;
    	 this.tpt_ssci = tpt_ssci;
    	 this.tpt_ei = tpt_ei;
    	 this.tpt_istp = tpt_istp;
    	 this.tpt_domesic = tpt_domesic;
    	 this.tpt_isnull = tpt_isnull;
     }
     public TeacherPublishThesis(Integer tpt_total,Integer tpt_sci, Integer tpt_ssci, Integer tpt_ei, Integer tpt_istp, Integer tpt_domesic,String tpt_college,int tpt_serialnumber, int tpt_isnull){
    	 super();
    	 this.tpt_total = tpt_total;
    	 this.tpt_sci = tpt_sci;
    	 this.tpt_ssci = tpt_ssci;
    	 this.tpt_ei = tpt_ei;
    	 this.tpt_istp = tpt_istp;
    	 this.tpt_domesic = tpt_domesic;
    	 this.tpt_college = tpt_college;
    	 this.tpt_serialnumber = tpt_serialnumber;
    	 this.tpt_isnull = tpt_isnull;
     }
     public TeacherPublishThesis(Integer tpt_total,Integer tpt_sci, Integer tpt_ssci, Integer tpt_ei, Integer tpt_istp, Integer tpt_domesic,String tpt_college,Date tpt_deadline, int tpt_isnull){
    	 super();
    	 this.tpt_total = tpt_total;
    	 this.tpt_sci = tpt_sci;
    	 this.tpt_ssci = tpt_ssci;
    	 this.tpt_ei = tpt_ei;
    	 this.tpt_istp = tpt_istp;
    	 this.tpt_domesic = tpt_domesic;
    	 this.tpt_college = tpt_college;
    	 this.tpt_deadline = tpt_deadline;
    	 this.tpt_isnull = tpt_isnull;
     }
     public TeacherPublishThesis(Integer tpt_total,Integer tpt_sci, Integer tpt_ssci, Integer tpt_ei, Integer tpt_istp, Integer tpt_domesic,String tpt_college,String tpt_comments, int tpt_isnull){
    	 super();
    	 this.tpt_total = tpt_total;
    	 this.tpt_sci = tpt_sci;
    	 this.tpt_ssci = tpt_ssci;
    	 this.tpt_ei = tpt_ei;
    	 this.tpt_istp = tpt_istp;
    	 this.tpt_domesic = tpt_domesic;
    	 this.tpt_college = tpt_college;
    	 this.tpt_comments = tpt_comments;
    	 this.tpt_isnull = tpt_isnull;
     }
     public TeacherPublishThesis(int tpt_id,Integer tpt_total,Integer tpt_sci, Integer tpt_ssci, Integer tpt_ei, Integer tpt_istp, Integer tpt_domesic,String tpt_college,Date tpt_deadline,int tpt_serialnumber,String tpt_comments, int tpt_isnull){
    	 super();
    	 this.tpt_id = tpt_id;
    	 this.tpt_total = tpt_total;
    	 this.tpt_sci = tpt_sci;
    	 this.tpt_ssci = tpt_ssci;
    	 this.tpt_ei = tpt_ei;
    	 this.tpt_istp = tpt_istp;
    	 this.tpt_domesic = tpt_domesic;
    	 this.tpt_college = tpt_college;
    	 this.tpt_deadline = tpt_deadline;
    	 this.tpt_serialnumber = tpt_serialnumber;
    	 this.tpt_comments = tpt_comments;
    	 this.tpt_isnull = tpt_isnull;
     }
	public int getTpt_id() {
		return tpt_id;
	}
	public void setTpt_id(int tpt_id) {
		this.tpt_id = tpt_id;
	}
	public Integer getTpt_total() {
		return tpt_total;
	}
	public void setTpt_total(Integer tpt_total) {
		this.tpt_total = tpt_total;
	}
	public Integer getTpt_sci() {
		return tpt_sci;
	}
	public void setTpt_sci(Integer tpt_sci) {
		this.tpt_sci = tpt_sci;
	}
	public Integer getTpt_ssci() {
		return tpt_ssci;
	}
	public void setTpt_ssci(Integer tpt_ssci) {
		this.tpt_ssci = tpt_ssci;
	}
	public Integer getTpt_ei() {
		return tpt_ei;
	}
	public void setTpt_ei(Integer tpt_ei) {
		this.tpt_ei = tpt_ei;
	}
	public Integer getTpt_istp() {
		return tpt_istp;
	}
	public void setTpt_istp(Integer tpt_istp) {
		this.tpt_istp = tpt_istp;
	}
	public Integer getTpt_domesic() {
		return tpt_domesic;
	}
	public void setTpt_domesic(Integer tpt_domesic) {
		this.tpt_domesic = tpt_domesic;
	}

	public String getTpt_college() {
		return tpt_college;
	}

	public void setTpt_college(String tpt_college) {
		this.tpt_college = tpt_college;
	}

	public Date getTpt_deadline() {
		return tpt_deadline;
	}

	public void setTpt_deadline(Date tpt_deadline) {
		this.tpt_deadline = tpt_deadline;
	}

	public int getTpt_serialnumber() {
		return tpt_serialnumber;
	}

	public void setTpt_serialnumber(int tpt_serialnumber) {
		this.tpt_serialnumber = tpt_serialnumber;
	}

	public String getTpt_comments() {
		return tpt_comments;
	}

	public void setTpt_comments(String tpt_comments) {
		this.tpt_comments = tpt_comments;
	}

	public int getTpt_isnull() {
		return tpt_isnull;
	}

	public void setTpt_isnull(int tpt_isnull) {
		this.tpt_isnull = tpt_isnull;
	}
    
    
}
