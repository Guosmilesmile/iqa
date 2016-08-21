package cn.edu.xmu.entity;
/**
 * 
 * @author zhantu
 * 6.3 教学管理队伍教学研究情况
 */
public class ManagementTeamResearch {
	/*行名称*/
	private String rowTitle;
	/*教学管理人员主持教学成果奖（项）*/
	private int achieveTotal;//总数
	private int nationAchieve;//国家级
	private int provinceAchieve;//省部级
	
	/*教学管理人员主持教育教学研究与改革项目*/
	private int projectTotalCount;//项目总数
	private int nationProjectCount;//国家级
	private int provinceProjectCount;//省部级
	private int schoolProjectCount;//校级
	
	/*教学管理人员主持教育教学研究与改革项目(经费)*/
	private float projectTotalFee;//经费总数
	private float nationProjectFee;//国家级
	private float provinceProjectFee;//省部级
	private float schoolProjectFee;//校级
	
	private String college;

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

	public float getNationProjectFee() {
		return nationProjectFee;
	}

	public void setNationProjectFee(float nationProjectFee) {
		this.nationProjectFee = nationProjectFee;
	}

	public float getProvinceProjectFee() {
		return provinceProjectFee;
	}

	public void setProvinceProjectFee(float provinceProjectFee) {
		this.provinceProjectFee = provinceProjectFee;
	}

	public float getSchoolProjectFee() {
		return schoolProjectFee;
	}

	public void setSchoolProjectFee(float schoolProjectFee) {
		this.schoolProjectFee = schoolProjectFee;
	}

	public String getCollege() {
		return college;
	}

	public void setCollege(String college) {
		this.college = college;
	}

	public ManagementTeamResearch(String rowTitle, int achieveTotal,
			int nationAchieve, int provinceAchieve, int projectTotalCount,
			int nationProjectCount, int provinceProjectCount,
			int schoolProjectCount, float projectTotalFee,
			float nationProjectFee, float provinceProjectFee,
			float schoolProjectFee, String college) {
		super();
		this.rowTitle = rowTitle;
		this.achieveTotal = achieveTotal;
		this.nationAchieve = nationAchieve;
		this.provinceAchieve = provinceAchieve;
		this.projectTotalCount = projectTotalCount;
		this.nationProjectCount = nationProjectCount;
		this.provinceProjectCount = provinceProjectCount;
		this.schoolProjectCount = schoolProjectCount;
		this.projectTotalFee = projectTotalFee;
		this.nationProjectFee = nationProjectFee;
		this.provinceProjectFee = provinceProjectFee;
		this.schoolProjectFee = schoolProjectFee;
		this.college = college;
	}
	
	
	
}
