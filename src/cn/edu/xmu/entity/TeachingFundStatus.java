package cn.edu.xmu.entity;
/**
 * 
 * @author zshbleaker
 * 3.1 教学经费投入情况
 */

public class TeachingFundStatus {

	private String rowTitle;			//教育经费总额
	private double schoolTeachingTotal; //学校教育经费总额		
	private double teachingTotal;		//教学经费总额
	private double schoolRevoBuild;		//学校年度教学改革与建设专项经费
	private double eduIncomeTotal;		//教育事业收入总计
	private double bachelorAvgNation;	//本科生生均拨款总额 国家
	private double bachelorAvgLocale;	//			       地方
	private double bachelorFeeIncome;	//本科生学费收入
	private double revoIncome;			//教改专项拨款
	private double teachingDailyOutcomeTotal; //教学日常运行之处总额
	private double aFuckingLongName;	//教学日常支出占经常性预算内教育事业费拨款与本科学费收入之和的比例（%）
	private double bachelorAvgDailyRunOutcome; //生均本科教学日常运行支出（万元）
	private double teachingRevoOutcome;	//教学改革支出
	private double proBuildOutcome;		//专业建设支出
	private double practiceBuildOutcome; //实践教学支出
	private double practiceTeachingFeeAvg; //生均实践教学经费（万元）
	
	private String college;
	

	public TeachingFundStatus() {
		super();
	}

	public String getRowTitle() {
		return rowTitle;
	}

	public void setRowTitle(String rowTitle) {
		this.rowTitle = rowTitle;
	}

	public double getSchoolTeachingTotal() {
		return schoolTeachingTotal;
	}

	public void setSchoolTeachingTotal(double schoolTeachingTotal) {
		this.schoolTeachingTotal = schoolTeachingTotal;
	}

	public double getTeachingTotal() {
		return teachingTotal;
	}

	public void setTeachingTotal(double teachingTotal) {
		this.teachingTotal = teachingTotal;
	}

	public double getSchoolRevoBuild() {
		return schoolRevoBuild;
	}

	public void setSchoolRevoBuild(double schoolRevoBuild) {
		this.schoolRevoBuild = schoolRevoBuild;
	}

	public double getEduIncomeTotal() {
		return eduIncomeTotal;
	}

	public void setEduIncomeTotal(double eduIncomeTotal) {
		this.eduIncomeTotal = eduIncomeTotal;
	}

	public double getBachelorAvgNation() {
		return bachelorAvgNation;
	}

	public void setBachelorAvgNation(double bachelorAvgNation) {
		this.bachelorAvgNation = bachelorAvgNation;
	}

	public double getBachelorAvgLocale() {
		return bachelorAvgLocale;
	}

	public void setBachelorAvgLocale(double bachelorAvgLocale) {
		this.bachelorAvgLocale = bachelorAvgLocale;
	}

	public double getBachelorFeeIncome() {
		return bachelorFeeIncome;
	}

	public void setBachelorFeeIncome(double bachelorFeeIncome) {
		this.bachelorFeeIncome = bachelorFeeIncome;
	}

	public double getRevoIncome() {
		return revoIncome;
	}

	public void setRevoIncome(double revoIncome) {
		this.revoIncome = revoIncome;
	}

	public double getTeachingDailyOutcomeTotal() {
		return teachingDailyOutcomeTotal;
	}

	public void setTeachingDailyOutcomeTotal(double teachingDailyOutcomeTotal) {
		this.teachingDailyOutcomeTotal = teachingDailyOutcomeTotal;
	}

	public double getaFuckingLongName() {
		return aFuckingLongName;
	}

	public void setaFuckingLongName(double aFuckingLongName) {
		this.aFuckingLongName = aFuckingLongName;
	}

	public double getBachelorAvgDailyRunOutcome() {
		return bachelorAvgDailyRunOutcome;
	}

	public void setBachelorAvgDailyRunOutcome(double bachelorAvgDailyRunOutcome) {
		this.bachelorAvgDailyRunOutcome = bachelorAvgDailyRunOutcome;
	}

	public double getTeachingRevoOutcome() {
		return teachingRevoOutcome;
	}

	public void setTeachingRevoOutcome(double teachingRevoOutcome) {
		this.teachingRevoOutcome = teachingRevoOutcome;
	}

	public double getProBuildOutcome() {
		return proBuildOutcome;
	}

	public void setProBuildOutcome(double proBuildOutcome) {
		this.proBuildOutcome = proBuildOutcome;
	}

	public double getPracticeBuildOutcome() {
		return practiceBuildOutcome;
	}

	public void setPracticeBuildOutcome(double practiceBuildOutcome) {
		this.practiceBuildOutcome = practiceBuildOutcome;
	}

	public double getPracticeTeachingFeeAvg() {
		return practiceTeachingFeeAvg;
	}

	public void setPracticeTeachingFeeAvg(double practiceTeachingFeeAvg) {
		this.practiceTeachingFeeAvg = practiceTeachingFeeAvg;
	}

	public String getCollege() {
		return college;
	}

	public void setCollege(String college) {
		this.college = college;
	}
	
	
	
}
