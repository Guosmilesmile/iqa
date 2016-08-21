package cn.edu.xmu.entity;

/**
 * 附表11 各专业教学情况一览表
 * @author yue
 *
 */
public class MajorTeachingInfo {
	//序号
	private int serialNumber;
	//专业名称
	private String majorName;
	//优势专业
	private String advantageMajor;
	//专业设置时间，年
	private String setupYear;
	//总学时
	private int allHours;
	//总学分
	private float allCredits;
	//必修课学分
	private float mustCredits; 
	//选修课学分
	private float selectedCredits;
	//集中实践环节学分
	private float concentratedPracticeCredits;
	//课内教学学分
	private float inclassCredits;
	//实验教学学分
	private float experimentCredits;
	//课外科技活动学分
	private float outclassActivityCredits;
	//实践环节学分比例
	private float  practiceCreditsPercent;
	//学院
	private String college;
	public MajorTeachingInfo(int serialNumber, String majorName, String advantageMajor, String setupYear, int allHours,
			float allCredits, float mustCredits, float selectedCredits, float concentratedPracticeCredits,
			float inclassCredits, float experimentCredits, float outclassActivityCredits, float practiceCreditsPercent,
			String college) {
		super();
		this.serialNumber = serialNumber;
		this.majorName = majorName;
		this.advantageMajor = advantageMajor;
		this.setupYear = setupYear;
		this.allHours = allHours;
		this.allCredits = allCredits;
		this.mustCredits = mustCredits;
		this.selectedCredits = selectedCredits;
		this.concentratedPracticeCredits = concentratedPracticeCredits;
		this.inclassCredits = inclassCredits;
		this.experimentCredits = experimentCredits;
		this.outclassActivityCredits = outclassActivityCredits;
		this.practiceCreditsPercent = practiceCreditsPercent;
		this.college = college;
	}
	public int getSerialNumber() {
		return serialNumber;
	}
	public void setSerialNumber(int serialNumber) {
		this.serialNumber = serialNumber;
	}
	public String getMajorName() {
		return majorName;
	}
	public void setMajorName(String majorName) {
		this.majorName = majorName;
	}
	public String getAdvantageMajor() {
		return advantageMajor;
	}
	public void setAdvantageMajor(String advantageMajor) {
		this.advantageMajor = advantageMajor;
	}
	public String getSetupYear() {
		return setupYear;
	}
	public void setSetupYear(String setupYear) {
		this.setupYear = setupYear;
	}
	public int getAllHours() {
		return allHours;
	}
	public void setAllHours(int allHours) {
		this.allHours = allHours;
	}
	public float getAllCredits() {
		return allCredits;
	}
	public void setAllCredits(float allCredits) {
		this.allCredits = allCredits;
	}
	public float getMustCredits() {
		return mustCredits;
	}
	public void setMustCredits(float mustCredits) {
		this.mustCredits = mustCredits;
	}
	public float getSelectedCredits() {
		return selectedCredits;
	}
	public void setSelectedCredits(float selectedCredits) {
		this.selectedCredits = selectedCredits;
	}
	public float getConcentratedPracticeCredits() {
		return concentratedPracticeCredits;
	}
	public void setConcentratedPracticeCredits(float concentratedPracticeCredits) {
		this.concentratedPracticeCredits = concentratedPracticeCredits;
	}
	public float getInclassCredits() {
		return inclassCredits;
	}
	public void setInclassCredits(float inclassCredits) {
		this.inclassCredits = inclassCredits;
	}
	public float getExperimentCredits() {
		return experimentCredits;
	}
	public void setExperimentCredits(float experimentCredits) {
		this.experimentCredits = experimentCredits;
	}
	public float getOutclassActivityCredits() {
		return outclassActivityCredits;
	}
	public void setOutclassActivityCredits(float outclassActivityCredits) {
		this.outclassActivityCredits = outclassActivityCredits;
	}
	public float getPracticeCreditsPercent() {
		return practiceCreditsPercent;
	}
	public void setPracticeCreditsPercent(float practiceCreditsPercent) {
		this.practiceCreditsPercent = practiceCreditsPercent;
	}
	public String getCollege() {
		return college;
	}
	public void setCollege(String college) {
		this.college = college;
	}
	
	
}
