package cn.edu.xmu.entity;

import java.sql.Date;

/**
 * 附表6-2-1-8厦门大学学生体质健康情况（学年）
 * @author yue
 *
 */
public class StuPhysicalHealthInfo {

	private int sphi_id;
	private String sphi_grade;//年级
	private Integer sphi_totalnumber;//总人数
	private Integer sphi_freetestnumber;//免于测试人数
	private Integer sphi_testnumber;//测试人数
	private Integer sphi_passnumber;//合格人数
	private Integer sphi_goodnumber;//良人数
	private Integer sphi_excellentnumber;//优秀人数
	private int sphi_serialnumber;//序列号
	private Date sphi_deadline;//截止日期
	private String sphi_college;//所属学院
	private String sphi_comments;//审核意见
	private int isnull;//记录是否存在空值
	public StuPhysicalHealthInfo(int sphi_id, String sphi_grade, Integer sphi_totalnumber, Integer sphi_freetestnumber,
			Integer sphi_testnumber, Integer sphi_passnumber, Integer sphi_goodnumber, Integer sphi_excellentnumber,
			int sphi_serialnumber, Date sphi_deadline, String sphi_college, String sphi_comments, int isnull) {
		super();
		this.sphi_id = sphi_id;
		this.sphi_grade = sphi_grade;
		this.sphi_totalnumber = sphi_totalnumber;
		this.sphi_freetestnumber = sphi_freetestnumber;
		this.sphi_testnumber = sphi_testnumber;
		this.sphi_passnumber = sphi_passnumber;
		this.sphi_goodnumber = sphi_goodnumber;
		this.sphi_excellentnumber = sphi_excellentnumber;
		this.sphi_serialnumber = sphi_serialnumber;
		this.sphi_deadline = sphi_deadline;
		this.sphi_college = sphi_college;
		this.sphi_comments = sphi_comments;
		this.isnull = isnull;
	}
	public StuPhysicalHealthInfo(String sphi_grade, Integer sphi_totalnumber, Integer sphi_freetestnumber,
			Integer sphi_testnumber, Integer sphi_passnumber, Integer sphi_goodnumber, Integer sphi_excellentnumber,
			int sphi_serialnumber, String sphi_college, String sphi_comments, int isnull) {
		super();
		this.sphi_grade = sphi_grade;
		this.sphi_totalnumber = sphi_totalnumber;
		this.sphi_freetestnumber = sphi_freetestnumber;
		this.sphi_testnumber = sphi_testnumber;
		this.sphi_passnumber = sphi_passnumber;
		this.sphi_goodnumber = sphi_goodnumber;
		this.sphi_excellentnumber = sphi_excellentnumber;
		this.sphi_serialnumber = sphi_serialnumber;
		this.sphi_college = sphi_college;
		this.sphi_comments = sphi_comments;
		this.isnull = isnull;
	}
	public StuPhysicalHealthInfo(String sphi_grade, Integer sphi_totalnumber, Integer sphi_freetestnumber,
			Integer sphi_testnumber, Integer sphi_passnumber, Integer sphi_goodnumber, Integer sphi_excellentnumber,
			String sphi_college, int isnull) {
		super();
		this.sphi_grade = sphi_grade;
		this.sphi_totalnumber = sphi_totalnumber;
		this.sphi_freetestnumber = sphi_freetestnumber;
		this.sphi_testnumber = sphi_testnumber;
		this.sphi_passnumber = sphi_passnumber;
		this.sphi_goodnumber = sphi_goodnumber;
		this.sphi_excellentnumber = sphi_excellentnumber;
		this.sphi_college = sphi_college;
		this.sphi_comments = "";
		this.isnull = isnull;
	}
	public int getSphi_id() {
		return sphi_id;
	}
	public void setSphi_id(int sphi_id) {
		this.sphi_id = sphi_id;
	}
	public String getSphi_grade() {
		return sphi_grade;
	}
	public void setSphi_grade(String sphi_grade) {
		this.sphi_grade = sphi_grade;
	}
	public Integer getSphi_totalnumber() {
		return sphi_totalnumber;
	}
	public void setSphi_totalnumber(Integer sphi_totalnumber) {
		this.sphi_totalnumber = sphi_totalnumber;
	}
	public Integer getSphi_freetestnumber() {
		return sphi_freetestnumber;
	}
	public void setSphi_freetestnumber(Integer sphi_freetestnumber) {
		this.sphi_freetestnumber = sphi_freetestnumber;
	}
	public Integer getSphi_testnumber() {
		return sphi_testnumber;
	}
	public void setSphi_testnumber(Integer sphi_testnumber) {
		this.sphi_testnumber = sphi_testnumber;
	}
	public Integer getSphi_passnumber() {
		return sphi_passnumber;
	}
	public void setSphi_passnumber(Integer sphi_passnumber) {
		this.sphi_passnumber = sphi_passnumber;
	}
	public Integer getSphi_goodnumber() {
		return sphi_goodnumber;
	}
	public void setSphi_goodnumber(Integer sphi_goodnumber) {
		this.sphi_goodnumber = sphi_goodnumber;
	}
	public Integer getSphi_excellentnumber() {
		return sphi_excellentnumber;
	}
	public void setSphi_excellentnumber(Integer sphi_excellentnumber) {
		this.sphi_excellentnumber = sphi_excellentnumber;
	}
	public int getSphi_serialnumber() {
		return sphi_serialnumber;
	}
	public void setSphi_serialnumber(int sphi_serialnumber) {
		this.sphi_serialnumber = sphi_serialnumber;
	}
	public Date getSphi_deadline() {
		return sphi_deadline;
	}
	public void setSphi_deadline(Date sphi_deadline) {
		this.sphi_deadline = sphi_deadline;
	}
	public String getSphi_college() {
		return sphi_college;
	}
	public void setSphi_college(String sphi_college) {
		this.sphi_college = sphi_college;
	}
	public String getSphi_comments() {
		return sphi_comments;
	}
	public void setSphi_comments(String sphi_comments) {
		this.sphi_comments = sphi_comments;
	}
	public int getIsnull() {
		return isnull;
	}
	public void setIsnull(int isnull) {
		this.isnull = isnull;
	}
	
	
	
}
