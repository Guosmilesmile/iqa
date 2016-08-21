package cn.edu.xmu.entity;
/*
 * @author luo
 */
public class TeachRevoResult {
	private String rowTitle;//行名称
	/*教师主持教学成果奖（项）*/
	private int achieveTotal;//总数
	private int nationAchieve;//国家级
	private int provinceAchieve;//省部级
	
	/*教师主持教育教学研究与改革项目*/
	private int projectTotalCount;//项目总数
	private int nationProjectCount;//国家级
	private int provinceProjectCount;//省部级
	private int schoolProjectCount;//校级
	
	/*教师主持教育教学研究与改革项目(经费)*/
	private float projectTotalFee;//经费总数
	private String college;
	public TeachRevoResult() {
		super();
	}
	public String getRowTitle() {
		return rowTitle;
	}
	public void setRowTitle(String rowTitle) {
		this.rowTitle = rowTitle;
	}
	public int getAchieveTotal() {
		return achieveTotal;
	}
	public void setAchieveTotal(int achieveTotal) {
		this.achieveTotal = achieveTotal;
	}
	public int getNationAchieve() {
		return nationAchieve;
	}
	public void setNationAchieve(int nationAchieve) {
		this.nationAchieve = nationAchieve;
	}
	public int getProvinceAchieve() {
		return provinceAchieve;
	}
	public void setProvinceAchieve(int provinceAchieve) {
		this.provinceAchieve = provinceAchieve;
	}
	public int getProjectTotalCount() {
		return projectTotalCount;
	}
	public void setProjectTotalCount(int projectTotalCount) {
		this.projectTotalCount = projectTotalCount;
	}
	public int getNationProjectCount() {
		return nationProjectCount;
	}
	public void setNationProjectCount(int nationProjectCount) {
		this.nationProjectCount = nationProjectCount;
	}
	public int getProvinceProjectCount() {
		return provinceProjectCount;
	}
	public void setProvinceProjectCount(int provinceProjectCount) {
		this.provinceProjectCount = provinceProjectCount;
	}
	public int getSchoolProjectCount() {
		return schoolProjectCount;
	}
	public void setSchoolProjectCount(int schoolProjectCount) {
		this.schoolProjectCount = schoolProjectCount;
	}
	public float getProjectTotalFee() {
		return projectTotalFee;
	}
	public void setProjectTotalFee(float projectTotalFee) {
		this.projectTotalFee = projectTotalFee;
	}
	public String getCollege() {
		return college;
	}
	public void setCollege(String college) {
		this.college = college;
	}
	
	
}
