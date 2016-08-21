package cn.edu.xmu.entity;

public class StudentExchangeSituation {
	private String rowTitle;//行名称
	//国外及港澳台学生在校本科生数
	private int foreignPersonCount;
	//当年交流本科生数
	private int exchangePersonCount;
	//占全日制本科生比例（%）
	private double percent;
	//本校到境外
	private int schoolToOverseasCount;
	//本校到境内
	private int schoolToTerritoryCount;
	//境内到本校
	private int territoryToSchoolCount;
	//境外到本校
	private int overseasToSchoolCount;
	
	private String college;

	public StudentExchangeSituation() {
		super();
	}

	public StudentExchangeSituation(String rowTitle, int foreignPersonCount,
			int exchangePersonCount, double percent, int schoolToOverseasCount,
			int schoolToTerritoryCount, int territoryToSchoolCount,
			int overseasToSchoolCount, String college) {
		super();
		this.rowTitle = rowTitle;
		this.foreignPersonCount = foreignPersonCount;
		this.exchangePersonCount = exchangePersonCount;
		this.percent = percent;
		this.schoolToOverseasCount = schoolToOverseasCount;
		this.schoolToTerritoryCount = schoolToTerritoryCount;
		this.territoryToSchoolCount = territoryToSchoolCount;
		this.overseasToSchoolCount = overseasToSchoolCount;
		this.college = college;
	}

	public String getRowTitle() {
		return rowTitle;
	}

	public void setRowTitle(String rowTitle) {
		this.rowTitle = rowTitle;
	}

	public int getForeignPersonCount() {
		return foreignPersonCount;
	}

	public void setForeignPersonCount(int foreignPersonCount) {
		this.foreignPersonCount = foreignPersonCount;
	}

	public int getExchangePersonCount() {
		return exchangePersonCount;
	}

	public void setExchangePersonCount(int exchangePersonCount) {
		this.exchangePersonCount = exchangePersonCount;
	}

	public double getPercent() {
		return percent;
	}

	public void setPercent(double percent) {
		this.percent = percent;
	}

	public int getSchoolToOverseasCount() {
		return schoolToOverseasCount;
	}

	public void setSchoolToOverseasCount(int schoolToOverseasCount) {
		this.schoolToOverseasCount = schoolToOverseasCount;
	}

	public int getSchoolToTerritoryCount() {
		return schoolToTerritoryCount;
	}

	public void setSchoolToTerritoryCount(int schoolToTerritoryCount) {
		this.schoolToTerritoryCount = schoolToTerritoryCount;
	}

	public int getTerritoryToSchoolCount() {
		return territoryToSchoolCount;
	}

	public void setTerritoryToSchoolCount(int territoryToSchoolCount) {
		this.territoryToSchoolCount = territoryToSchoolCount;
	}

	public int getOverseasToSchoolCount() {
		return overseasToSchoolCount;
	}

	public void setOverseasToSchoolCount(int overseasToSchoolCount) {
		this.overseasToSchoolCount = overseasToSchoolCount;
	}

	public String getCollege() {
		return college;
	}

	public void setCollege(String college) {
		this.college = college;
	}

	
	

}
