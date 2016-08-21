package cn.edu.xmu.entity;

import java.util.Date;

/**
 * 
 * @author Luo
 * 附表7-1-3党政管理干部听课情况（学年） 实体类
 * 全参构造函数
 * date 2015-07-11
 *
 */
public class ListenSituation {
	private int ls_id;
	//学院
	private String ls_importcollege;
	//听课人数
	private Integer ls_personnumber;
	//听课门次
	private Integer ls_lessonnumber;
	//听课人数
	private Integer ls_personnumber2;
	//听课门次
	private Integer ls_lessonnumber2;
	//优
	private Integer ls_excellent;
	//良
	private Integer ls_good;
	//中
	private Integer ls_medium;
	//差
	private Integer ls_bad;
	//序列号
	private int ls_serialnumber;
	//截止日期
	private Date ls_deadline;
	//所属学院
	private String ls_college;
	//审核意见
	private String ls_comments;
	
	private int isnull;
	
	public ListenSituation(int ls_id, String ls_importcollege,
			Integer ls_personnumber, Integer ls_lessonnumber,
			Integer ls_personnumber2, Integer ls_lessonnumber2,
			Integer ls_excellent, Integer ls_good, Integer ls_medium,
			Integer ls_bad, int ls_serialnumber, Date ls_deadline,
			String ls_college, String ls_comments, int isnull) {
		super();
		this.ls_id = ls_id;
		this.ls_importcollege = ls_importcollege;
		this.ls_personnumber = ls_personnumber;
		this.ls_lessonnumber = ls_lessonnumber;
		this.ls_personnumber2 = ls_personnumber2;
		this.ls_lessonnumber2 = ls_lessonnumber2;
		this.ls_excellent = ls_excellent;
		this.ls_good = ls_good;
		this.ls_medium = ls_medium;
		this.ls_bad = ls_bad;
		this.ls_serialnumber = ls_serialnumber;
		this.ls_deadline = ls_deadline;
		this.ls_college = ls_college;
		this.ls_comments = ls_comments;
		this.isnull = isnull;
	}
	
	public ListenSituation(String ls_importcollege, Integer ls_personnumber,
			Integer ls_lessonnumber, Integer ls_personnumber2,
			Integer ls_lessonnumber2, Integer ls_excellent, Integer ls_good,
			Integer ls_medium, Integer ls_bad, int ls_serialnumber,
			String ls_college, String ls_comments, int isnull) {
		super();
		this.ls_importcollege = ls_importcollege;
		this.ls_personnumber = ls_personnumber;
		this.ls_lessonnumber = ls_lessonnumber;
		this.ls_personnumber2 = ls_personnumber2;
		this.ls_lessonnumber2 = ls_lessonnumber2;
		this.ls_excellent = ls_excellent;
		this.ls_good = ls_good;
		this.ls_medium = ls_medium;
		this.ls_bad = ls_bad;
		this.ls_serialnumber = ls_serialnumber;
		this.ls_college = ls_college;
		this.ls_comments = ls_comments;
		this.isnull = isnull;
	}

	
	
	public ListenSituation(int ls_id, String ls_importcollege,
			Integer ls_personnumber, Integer ls_lessonnumber,
			Integer ls_personnumber2, Integer ls_lessonnumber2,
			Integer ls_excellent, Integer ls_good, Integer ls_medium,
			Integer ls_bad, int ls_serialnumber, String ls_comments, int isnull,String ls_college) {
		super();
		this.ls_id = ls_id;
		this.ls_importcollege = ls_importcollege;
		this.ls_personnumber = ls_personnumber;
		this.ls_lessonnumber = ls_lessonnumber;
		this.ls_personnumber2 = ls_personnumber2;
		this.ls_lessonnumber2 = ls_lessonnumber2;
		this.ls_excellent = ls_excellent;
		this.ls_good = ls_good;
		this.ls_medium = ls_medium;
		this.ls_bad = ls_bad;
		this.ls_serialnumber = ls_serialnumber;
		this.ls_comments = ls_comments;
		this.isnull = isnull;
		this.ls_college = ls_college;
	}

	
	public ListenSituation(String ls_importcollege, Integer ls_personnumber,
			Integer ls_lessonnumber, Integer ls_personnumber2,
			Integer ls_lessonnumber2, Integer ls_excellent, Integer ls_good,
			Integer ls_medium, Integer ls_bad, String ls_college, int isnull) {
		super();
		this.ls_importcollege = ls_importcollege;
		this.ls_personnumber = ls_personnumber;
		this.ls_lessonnumber = ls_lessonnumber;
		this.ls_personnumber2 = ls_personnumber2;
		this.ls_lessonnumber2 = ls_lessonnumber2;
		this.ls_excellent = ls_excellent;
		this.ls_good = ls_good;
		this.ls_medium = ls_medium;
		this.ls_bad = ls_bad;
		this.ls_college = ls_college;
		this.isnull = isnull;
	}

	public int getLs_id() {
		return ls_id;
	}

	public void setLs_id(int ls_id) {
		this.ls_id = ls_id;
	}

	public String getLs_importcollege() {
		return ls_importcollege;
	}

	public void setLs_importcollege(String ls_importcollege) {
		this.ls_importcollege = ls_importcollege;
	}

	public Integer getLs_personnumber() {
		return ls_personnumber;
	}

	public void setLs_personnumber(Integer ls_personnumber) {
		this.ls_personnumber = ls_personnumber;
	}

	public Integer getLs_lessonnumber() {
		return ls_lessonnumber;
	}

	public void setLs_lessonnumber(Integer ls_lessonnumber) {
		this.ls_lessonnumber = ls_lessonnumber;
	}

	public Integer getLs_personnumber2() {
		return ls_personnumber2;
	}

	public void setLs_personnumber2(Integer ls_personnumber2) {
		this.ls_personnumber2 = ls_personnumber2;
	}

	public Integer getLs_lessonnumber2() {
		return ls_lessonnumber2;
	}

	public void setLs_lessonnumber2(Integer ls_lessonnumber2) {
		this.ls_lessonnumber2 = ls_lessonnumber2;
	}

	public Integer getLs_excellent() {
		return ls_excellent;
	}

	public void setLs_excellent(Integer ls_excellent) {
		this.ls_excellent = ls_excellent;
	}

	public Integer getLs_good() {
		return ls_good;
	}

	public void setLs_good(Integer ls_good) {
		this.ls_good = ls_good;
	}

	public Integer getLs_medium() {
		return ls_medium;
	}

	public void setLs_medium(Integer ls_medium) {
		this.ls_medium = ls_medium;
	}

	public Integer getLs_bad() {
		return ls_bad;
	}

	public void setLs_bad(Integer ls_bad) {
		this.ls_bad = ls_bad;
	}

	public int getLs_serialnumber() {
		return ls_serialnumber;
	}

	public void setLs_serialnumber(int ls_serialnumber) {
		this.ls_serialnumber = ls_serialnumber;
	}

	public Date getLs_deadline() {
		return ls_deadline;
	}

	public void setLs_deadline(Date ls_deadline) {
		this.ls_deadline = ls_deadline;
	}

	public String getLs_college() {
		return ls_college;
	}

	public void setLs_college(String ls_college) {
		this.ls_college = ls_college;
	}

	public String getLs_comments() {
		return ls_comments;
	}

	public void setLs_comments(String ls_comments) {
		this.ls_comments = ls_comments;
	}

	public int getIsnull() {
		return isnull;
	}

	public void setIsnull(int isnull) {
		this.isnull = isnull;
	}

	
	
}
