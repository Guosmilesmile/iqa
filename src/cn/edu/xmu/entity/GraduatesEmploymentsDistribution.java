package cn.edu.xmu.entity;

/**
 * 
 * @author zsj
 * 5.8 毕业生就业去向分布情况
 */
public class GraduatesEmploymentsDistribution {
	private String rowTitle;
	private String total;
	//政府机构
	private String  gov;
	//事业单位
	private String  institution;
	//企业
	private String  enterprise;
	//部队
	private String  troops;
	//灵活就业
	private String  flexibleemployment;
	//升学
	private String  entrance;
	//参加国家地方项目就业
	private String  nationallocalprojectemployment;
	//其他
	private String  others;
	
	private String college;

	public String getRowTitle() {
		return rowTitle;
	}

	public void setRowTitle(String rowTitle) {
		this.rowTitle = rowTitle;
	}

	public String getTotal() {
		return total;
	}

	public void setTotal(String total) {
		this.total = total;
	}

	public String getGov() {
		return gov;
	}

	public void setGov(String gov) {
		this.gov = gov;
	}

	public String getInstitution() {
		return institution;
	}

	public void setInstitution(String institution) {
		this.institution = institution;
	}

	public String getEnterprise() {
		return enterprise;
	}

	public void setEnterprise(String enterprise) {
		this.enterprise = enterprise;
	}

	public String getTroops() {
		return troops;
	}

	public void setTroops(String troops) {
		this.troops = troops;
	}

	public String getFlexibleemployment() {
		return flexibleemployment;
	}

	public void setFlexibleemployment(String flexibleemployment) {
		this.flexibleemployment = flexibleemployment;
	}

	public String getEntrance() {
		return entrance;
	}

	public void setEntrance(String entrance) {
		this.entrance = entrance;
	}

	public String getNationallocalprojectemployment() {
		return nationallocalprojectemployment;
	}

	public void setNationallocalprojectemployment(
			String nationallocalprojectemployment) {
		this.nationallocalprojectemployment = nationallocalprojectemployment;
	}

	public String getOthers() {
		return others;
	}

	public void setOthers(String others) {
		this.others = others;
	}

	public String getCollege() {
		return college;
	}

	public void setCollege(String college) {
		this.college = college;
	}

	public GraduatesEmploymentsDistribution(String rowTitle, String total,
			String gov, String institution, String enterprise, String troops,
			String flexibleemployment, String entrance,
			String nationallocalprojectemployment, String others, String college) {
		super();
		this.rowTitle = rowTitle;
		this.total = total;
		this.gov = gov;
		this.institution = institution;
		this.enterprise = enterprise;
		this.troops = troops;
		this.flexibleemployment = flexibleemployment;
		this.entrance = entrance;
		this.nationallocalprojectemployment = nationallocalprojectemployment;
		this.others = others;
		this.college = college;
	}
	
	

}
