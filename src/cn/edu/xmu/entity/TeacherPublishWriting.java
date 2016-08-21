package cn.edu.xmu.entity;

import java.sql.Date;

/**
 * 表3-6-5  教师出版著作（自然年）
 * @author chunfeng
 *
 */
public class TeacherPublishWriting {
    private int tpw_id;
    private Integer tpw_total;  //总数
    private Integer tpw_monograph;  //专著
    private Integer tpw_translation;  //译著
    private Integer tpw_compile; //编著
    private Integer tpw_other;  //其他
    
    private String tpw_college;
    private Date tpw_deadline;
    private int tpw_serialnumber;
    private String tpw_comments;
    private int tpw_isnull;
    
    public TeacherPublishWriting() {
 		super();
 	}

     public TeacherPublishWriting(Integer tpw_total,Integer tpw_monograph, Integer tpw_translation, Integer tpw_compile, Integer tpw_other, int tpw_isnull){
    	 super();
    	 this.tpw_total = tpw_total;
    	 this.tpw_monograph = tpw_monograph;
    	 this.tpw_translation = tpw_translation;
    	 this.tpw_compile = tpw_compile;
    	 this.tpw_other = tpw_other;
    	 this.tpw_isnull = tpw_isnull;
     }
     public TeacherPublishWriting(Integer tpw_total,Integer tpw_monograph, Integer tpw_translation, Integer tpw_compile, Integer tpw_other,String tpw_college,int tpw_serialnumber, int tpw_isnull){
    	 super();
    	 this.tpw_total = tpw_total;
    	 this.tpw_monograph = tpw_monograph;
    	 this.tpw_translation = tpw_translation;
    	 this.tpw_compile = tpw_compile;
    	 this.tpw_other = tpw_other;
    	 this.tpw_college = tpw_college;
    	 this.tpw_serialnumber = tpw_serialnumber;
    	 this.tpw_isnull = tpw_isnull;
     }
     public TeacherPublishWriting(Integer tpw_total,Integer tpw_monograph, Integer tpw_translation, Integer tpw_compile, Integer tpw_other,String tpw_college,Date tpw_deadline, int tpw_isnull){
    	 super();
    	 this.tpw_total = tpw_total;
    	 this.tpw_monograph = tpw_monograph;
    	 this.tpw_translation = tpw_translation;
    	 this.tpw_compile = tpw_compile;
    	 this.tpw_other = tpw_other;
    	 this.tpw_college = tpw_college;
    	 this.tpw_deadline = tpw_deadline;
    	 this.tpw_isnull = tpw_isnull;
     }
     public TeacherPublishWriting(Integer tpw_total,Integer tpw_monograph, Integer tpw_translation, Integer tpw_compile, Integer tpw_other,String tpw_college,String tpw_comments, int tpw_isnull){
    	 super();
    	 this.tpw_total = tpw_total;
    	 this.tpw_monograph = tpw_monograph;
    	 this.tpw_translation = tpw_translation;
    	 this.tpw_compile = tpw_compile;
    	 this.tpw_other = tpw_other;
    	 this.tpw_college = tpw_college;
    	 this.tpw_comments = tpw_comments;
    	 this.tpw_isnull = tpw_isnull;
     }
     public TeacherPublishWriting(int tpw_id,Integer tpw_total,Integer tpw_monograph, Integer tpw_translation, Integer tpw_compile, Integer tpw_other,String tpw_college,Date tpw_deadline,int tpw_serialnumber,String tpw_comments, int tpw_isnull){
    	 super();
    	 this.tpw_id = tpw_id;
    	 this.tpw_total = tpw_total;
    	 this.tpw_monograph = tpw_monograph;
    	 this.tpw_translation = tpw_translation;
    	 this.tpw_compile = tpw_compile;
    	 this.tpw_other = tpw_other;
    	 this.tpw_college = tpw_college;
    	 this.tpw_deadline = tpw_deadline;
    	 this.tpw_serialnumber = tpw_serialnumber;
    	 this.tpw_comments = tpw_comments;
    	 this.tpw_isnull = tpw_isnull;
     }

	public int getTpw_id() {
		return tpw_id;
	}

	public void setTpw_id(int tpw_id) {
		this.tpw_id = tpw_id;
	}

	public Integer getTpw_total() {
		return tpw_total;
	}

	public void setTpw_total(Integer tpw_total) {
		this.tpw_total = tpw_total;
	}

	public Integer getTpw_monograph() {
		return tpw_monograph;
	}

	public void setTpw_monograph(Integer tpw_monograph) {
		this.tpw_monograph = tpw_monograph;
	}

	public Integer getTpw_translation() {
		return tpw_translation;
	}

	public void setTpw_translation(Integer tpw_translation) {
		this.tpw_translation = tpw_translation;
	}

	public Integer getTpw_compile() {
		return tpw_compile;
	}

	public void setTpw_compile(Integer tpw_compile) {
		this.tpw_compile = tpw_compile;
	}

	public Integer getTpw_other() {
		return tpw_other;
	}

	public void setTpw_other(Integer tpw_other) {
		this.tpw_other = tpw_other;
	}

	public String getTpw_college() {
		return tpw_college;
	}

	public void setTpw_college(String tpw_college) {
		this.tpw_college = tpw_college;
	}

	public Date getTpw_deadline() {
		return tpw_deadline;
	}

	public void setTpw_deadline(Date tpw_deadline) {
		this.tpw_deadline = tpw_deadline;
	}

	public int getTpw_serialnumber() {
		return tpw_serialnumber;
	}

	public void setTpw_serialnumber(int tpw_serialnumber) {
		this.tpw_serialnumber = tpw_serialnumber;
	}

	public String getTpw_comments() {
		return tpw_comments;
	}

	public void setTpw_comments(String tpw_comments) {
		this.tpw_comments = tpw_comments;
	}

	public int getTpw_isnull() {
		return tpw_isnull;
	}

	public void setTpw_isnull(int tpw_isnull) {
		this.tpw_isnull = tpw_isnull;
	}
	
}
