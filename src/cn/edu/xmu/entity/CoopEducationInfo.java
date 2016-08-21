package cn.edu.xmu.entity;

/**
 * 3.10 合作办学情况
 * @author yue
 *
 */
public class CoopEducationInfo {

	private int agencyAmount;//掐你当合作协议机构总数
	private int academic;//其中：学术机构
	private int industry;//行业机构和企业
	private int government;//地方政府
	private String college;//学院
	public CoopEducationInfo(int agencyAmount, int academic, int industry, int government, String college) {
		super();
		this.agencyAmount = agencyAmount;
		this.academic = academic;
		this.industry = industry;
		this.government = government;
		this.college = college;
	}
	public int getAgencyAmount() {
		return agencyAmount;
	}
	public void setAgencyAmount(int agencyAmount) {
		this.agencyAmount = agencyAmount;
	}
	public int getAcademic() {
		return academic;
	}
	public void setAcademic(int academic) {
		this.academic = academic;
	}
	public int getIndustry() {
		return industry;
	}
	public void setIndustry(int industry) {
		this.industry = industry;
	}
	public int getGovernment() {
		return government;
	}
	public void setGovernment(int government) {
		this.government = government;
	}
	public String getCollege() {
		return college;
	}
	public void setCollege(String college) {
		this.college = college;
	}
	
	
}
