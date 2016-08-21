package cn.edu.xmu.entity;

import java.util.Date;

/**
 * 
 * @author Luo
 * 固定资产 实体类
 * 全参构造函数
 * date 2015-07-07
 *
 */
public class FixedAssets {
	private int fa_id;
	//学院
	private String fa_importcollege;
	//固定资产总值（万元）
	private Float fa_fixedassetssum;
	//教学、科研仪器设备资产总值
	private Float fa_equipmentassetssum;
	//当年新增值
	private Float fa_newassets;
	//序列号
	private int fa_serialnumber;
	//截止日期
	private Date fa_deadline;
	//所属学院
	private String fa_college;
	//审核意见
	private String fa_comments;
	private int isnull;
	public FixedAssets(int fa_id, String fa_importcollege,
			Float fa_fixedassetssum, Float fa_equipmentassetssum,
			Float fa_newassets, int fa_serialnumber, Date fa_deadline,
			String fa_college, String fa_comments, int isnull) {
		super();
		this.fa_id = fa_id;
		this.fa_importcollege = fa_importcollege;
		this.fa_fixedassetssum = fa_fixedassetssum;
		this.fa_equipmentassetssum = fa_equipmentassetssum;
		this.fa_newassets = fa_newassets;
		this.fa_serialnumber = fa_serialnumber;
		this.fa_deadline = fa_deadline;
		this.fa_college = fa_college;
		this.fa_comments = fa_comments;
		this.isnull = isnull;
	}
	public FixedAssets(String fa_importcollege, Float fa_fixedassetssum,
			Float fa_equipmentassetssum, Float fa_newassets,
			int fa_serialnumber, String fa_college, String fa_comments,
			int isnull) {
		super();
		this.fa_importcollege = fa_importcollege;
		this.fa_fixedassetssum = fa_fixedassetssum;
		this.fa_equipmentassetssum = fa_equipmentassetssum;
		this.fa_newassets = fa_newassets;
		this.fa_serialnumber = fa_serialnumber;
		this.fa_college = fa_college;
		this.fa_comments = fa_comments;
		this.isnull = isnull;
	}
	public FixedAssets(int fa_id, String fa_importcollege,
			Float fa_fixedassetssum, Float fa_equipmentassetssum,
			Float fa_newassets, int fa_serialnumber, String fa_comments,
			int isnull,String fa_college) {
		super();
		this.fa_id = fa_id;
		this.fa_importcollege = fa_importcollege;
		this.fa_fixedassetssum = fa_fixedassetssum;
		this.fa_equipmentassetssum = fa_equipmentassetssum;
		this.fa_newassets = fa_newassets;
		this.fa_serialnumber = fa_serialnumber;
		this.fa_comments = fa_comments;
		this.isnull = isnull;
		this.fa_college = fa_college;
	}
	public FixedAssets(String fa_importcollege, Float fa_fixedassetssum,
			Float fa_equipmentassetssum, Float fa_newassets, String fa_college,
			int isnull) {
		super();
		this.fa_importcollege = fa_importcollege;
		this.fa_fixedassetssum = fa_fixedassetssum;
		this.fa_equipmentassetssum = fa_equipmentassetssum;
		this.fa_newassets = fa_newassets;
		this.fa_college = fa_college;
		this.isnull = isnull;
	}
	public int getFa_id() {
		return fa_id;
	}
	public void setFa_id(int fa_id) {
		this.fa_id = fa_id;
	}
	public String getFa_importcollege() {
		return fa_importcollege;
	}
	public void setFa_importcollege(String fa_importcollege) {
		this.fa_importcollege = fa_importcollege;
	}
	public Float getFa_fixedassetssum() {
		return fa_fixedassetssum;
	}
	public void setFa_fixedassetssum(Float fa_fixedassetssum) {
		this.fa_fixedassetssum = fa_fixedassetssum;
	}
	public Float getFa_equipmentassetssum() {
		return fa_equipmentassetssum;
	}
	public void setFa_equipmentassetssum(Float fa_equipmentassetssum) {
		this.fa_equipmentassetssum = fa_equipmentassetssum;
	}
	public Float getFa_newassets() {
		return fa_newassets;
	}
	public void setFa_newassets(Float fa_newassets) {
		this.fa_newassets = fa_newassets;
	}
	public int getFa_serialnumber() {
		return fa_serialnumber;
	}
	public void setFa_serialnumber(int fa_serialnumber) {
		this.fa_serialnumber = fa_serialnumber;
	}
	public Date getFa_deadline() {
		return fa_deadline;
	}
	public void setFa_deadline(Date fa_deadline) {
		this.fa_deadline = fa_deadline;
	}
	public String getFa_college() {
		return fa_college;
	}
	public void setFa_college(String fa_college) {
		this.fa_college = fa_college;
	}
	public String getFa_comments() {
		return fa_comments;
	}
	public void setFa_comments(String fa_comments) {
		this.fa_comments = fa_comments;
	}
	public int getIsnull() {
		return isnull;
	}
	public void setIsnull(int isnull) {
		this.isnull = isnull;
	}
	
	
}
