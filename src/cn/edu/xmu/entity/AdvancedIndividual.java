package cn.edu.xmu.entity;

import java.util.Date;

/**
 * 
 * @author Luo
 * 全国师德先进个人（时点） 实体类
 * 全参构造函数
 * date 2015-07-08
 *
 */
public class AdvancedIndividual {
	private int ai_id;
	//序号
	private Integer ai_order;
	//学院
	private String ai_importcollege;
	//姓名
	private String ai_name;
	//获奖年份
	private String ai_honoryear;
	//序列号
	private int ai_serialnumber;
	//截止日期
	private Date ai_deadline;
	//所属学院
	private String ai_college;
	//审核意见
	private String ai_comments;
	
	private int isnull;

	public AdvancedIndividual(int ai_id, Integer ai_order,
			String ai_importcollege, String ai_name, String ai_honoryear,
			int ai_serialnumber, Date ai_deadline, String ai_college,
			String ai_comments, int isnull) {
		super();
		this.ai_id = ai_id;
		this.ai_order = ai_order;
		this.ai_importcollege = ai_importcollege;
		this.ai_name = ai_name;
		this.ai_honoryear = ai_honoryear;
		this.ai_serialnumber = ai_serialnumber;
		this.ai_deadline = ai_deadline;
		this.ai_college = ai_college;
		this.ai_comments = ai_comments;
		this.isnull = isnull;
	}

	public AdvancedIndividual(Integer ai_order, String ai_importcollege,
			String ai_name, String ai_honoryear, int ai_serialnumber,
			String ai_college, String ai_comments, int isnull) {
		super();
		this.ai_order = ai_order;
		this.ai_importcollege = ai_importcollege;
		this.ai_name = ai_name;
		this.ai_honoryear = ai_honoryear;
		this.ai_serialnumber = ai_serialnumber;
		this.ai_college = ai_college;
		this.ai_comments = ai_comments;
		this.isnull = isnull;
	}

	public AdvancedIndividual(int ai_id, Integer ai_order,
			String ai_importcollege, String ai_name, String ai_honoryear,
			int ai_serialnumber, String ai_comments, int isnull,String ai_college) {
		super();
		this.ai_id = ai_id;
		this.ai_order = ai_order;
		this.ai_importcollege = ai_importcollege;
		this.ai_name = ai_name;
		this.ai_honoryear = ai_honoryear;
		this.ai_serialnumber = ai_serialnumber;
		this.ai_comments = ai_comments;
		this.isnull = isnull;
		this.ai_college = ai_college;
	}

	public AdvancedIndividual(Integer ai_order, String ai_importcollege,
			String ai_name, String ai_honoryear, String ai_college, int isnull) {
		super();
		this.ai_order = ai_order;
		this.ai_importcollege = ai_importcollege;
		this.ai_name = ai_name;
		this.ai_honoryear = ai_honoryear;
		this.ai_college = ai_college;
		this.isnull = isnull;
	}

	public int getAi_id() {
		return ai_id;
	}

	public void setAi_id(int ai_id) {
		this.ai_id = ai_id;
	}

	public Integer getAi_order() {
		return ai_order;
	}

	public void setAi_order(Integer ai_order) {
		this.ai_order = ai_order;
	}

	public String getAi_importcollege() {
		return ai_importcollege;
	}

	public void setAi_importcollege(String ai_importcollege) {
		this.ai_importcollege = ai_importcollege;
	}

	public String getAi_name() {
		return ai_name;
	}

	public void setAi_name(String ai_name) {
		this.ai_name = ai_name;
	}

	public String getAi_honoryear() {
		return ai_honoryear;
	}

	public void setAi_honoryear(String ai_honoryear) {
		this.ai_honoryear = ai_honoryear;
	}

	public int getAi_serialnumber() {
		return ai_serialnumber;
	}

	public void setAi_serialnumber(int ai_serialnumber) {
		this.ai_serialnumber = ai_serialnumber;
	}

	public Date getAi_deadline() {
		return ai_deadline;
	}

	public void setAi_deadline(Date ai_deadline) {
		this.ai_deadline = ai_deadline;
	}

	public String getAi_college() {
		return ai_college;
	}

	public void setAi_college(String ai_college) {
		this.ai_college = ai_college;
	}

	public String getAi_comments() {
		return ai_comments;
	}

	public void setAi_comments(String ai_comments) {
		this.ai_comments = ai_comments;
	}

	public int getIsnull() {
		return isnull;
	}

	public void setIsnull(int isnull) {
		this.isnull = isnull;
	}
	
	
}
