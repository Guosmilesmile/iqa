package cn.edu.xmu.entity;
/*
 * luo
 * 4.8各教学单位教育教学研究与改革情况
 */
public class TeachResearchReform {
	//单位号码
	private int departmentnumber;
	//单位
	private String unitName;
	//项目总数
	private int projectTotalCount;
	//国家级
	private int countryCount;
	//省部级
	private int provinceCount;
	//经费(万元)
	private double funding;
	//当年成果奖总数
	private int priceCount;
	//国家级
	private int countryPriceCount;
	//省部级
	private int provincePriceCount;
	
	private String college;

	public TeachResearchReform() {
		super();
	}
	

	public TeachResearchReform(int departmentnumber, String unitName,
			int projectTotalCount, int countryCount, int provinceCount,
			double funding, int priceCount, int countryPriceCount,
			int provincePriceCount, String college) {
		super();
		this.departmentnumber = departmentnumber;
		this.unitName = unitName;
		this.projectTotalCount = projectTotalCount;
		this.countryCount = countryCount;
		this.provinceCount = provinceCount;
		this.funding = funding;
		this.priceCount = priceCount;
		this.countryPriceCount = countryPriceCount;
		this.provincePriceCount = provincePriceCount;
		this.college = college;
	}


	public TeachResearchReform(String unitName, int projectTotalCount,
			int countryCount, int provinceCount, double funding,
			int priceCount, int countryPriceCount, int provincePriceCount,
			String college) {
		super();
		this.unitName = unitName;
		this.projectTotalCount = projectTotalCount;
		this.countryCount = countryCount;
		this.provinceCount = provinceCount;
		this.funding = funding;
		this.priceCount = priceCount;
		this.countryPriceCount = countryPriceCount;
		this.provincePriceCount = provincePriceCount;
		this.college = college;
	}

	
	public TeachResearchReform(int departmentnumber, String unitName,
			int projectTotalCount, int countryCount, int provinceCount,
			double funding, int priceCount, int countryPriceCount,
			int provincePriceCount) {
		super();
		this.departmentnumber = departmentnumber;
		this.unitName = unitName;
		this.projectTotalCount = projectTotalCount;
		this.countryCount = countryCount;
		this.provinceCount = provinceCount;
		this.funding = funding;
		this.priceCount = priceCount;
		this.countryPriceCount = countryPriceCount;
		this.provincePriceCount = provincePriceCount;
	}


	public String getUnitName() {
		return unitName;
	}

	public void setUnitName(String unitName) {
		this.unitName = unitName;
	}

	public int getProjectTotalCount() {
		return projectTotalCount;
	}

	public void setProjectTotalCount(int projectTotalCount) {
		this.projectTotalCount = projectTotalCount;
	}

	public int getCountryCount() {
		return countryCount;
	}

	public void setCountryCount(int countryCount) {
		this.countryCount = countryCount;
	}

	public int getProvinceCount() {
		return provinceCount;
	}

	public void setProvinceCount(int provinceCount) {
		this.provinceCount = provinceCount;
	}

	public double getFunding() {
		return funding;
	}

	public void setFunding(double funding) {
		this.funding = funding;
	}

	public int getPriceCount() {
		return priceCount;
	}

	public void setPriceCount(int priceCount) {
		this.priceCount = priceCount;
	}

	public int getCountryPriceCount() {
		return countryPriceCount;
	}

	public void setCountryPriceCount(int countryPriceCount) {
		this.countryPriceCount = countryPriceCount;
	}

	public int getProvincePriceCount() {
		return provincePriceCount;
	}

	public void setProvincePriceCount(int provincePriceCount) {
		this.provincePriceCount = provincePriceCount;
	}

	public String getCollege() {
		return college;
	}

	public void setCollege(String college) {
		this.college = college;
	}

	public int getDepartmentnumber() {
		return departmentnumber;
	}

	public void setDepartmentnumber(int departmentnumber) {
		this.departmentnumber = departmentnumber;
	}
	
	
	

}
