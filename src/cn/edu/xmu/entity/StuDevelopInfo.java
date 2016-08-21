package cn.edu.xmu.entity;


/**
 * 5.6 学生发展情况
 * @author yue
 *
 */
public class StuDevelopInfo {
	/*1.学科竞赛获奖（项）*/
	//总数
	private int  academicCompetitionSum;
	//其中：国家级
	private int academicCompetitionNationLevel;
	//省部级
	private int academicCompetitionProvincialLevel;
	
	
	/*2.本科生创新活动、技能竞赛获奖*/
	//总数
	private int innovationAndSkillCompetitionSum;
	//其中：国家级
	private int innovationAndSkillCompetitionNationLevel;
	//省部级
	private int innovationAndSkillCompetitionProvincialLevel;
	
	/*3.文艺、体育竞赛获奖*/
	//总数
	private int artAndSportCompetitionSum;
	//其中：国家级
	private int artAndSportCompetitionNationLevel;
	//省部级
	private int artAndSportCompetitionProvincialLevel;
	
	
	//4.学生发表学术论文（篇）
	private int academicPaper;
	
	//5.学生发表作品数（篇、册）
	private int publishProduct;
	
	//6.学生获准专利数
	private int studentPatent;
	
	
	/*7.英语等级考试*/
	//英语四级考试累计通过率（%）
	private double englishiLevelFourPassRate;
	//英语六级考试累计通过率（%）
	private double englishiLevelSixPassRate;
	
	//8.体质合格率（%）
	private double stuPhysicalHealPassRate;
	
	//9.参加国际会议（人次）
	private int goInterConferCount;
	
	private String college;

	public StuDevelopInfo(int academicCompetitionSum, int academicCompetitionNationLevel,
			int academicCompetitionProvincialLevel, int innovationAndSkillCompetitionSum,
			int innovationAndSkillCompetitionNationLevel, int innovationAndSkillCompetitionProvincialLevel,
			int artAndSportCompetitionSum, int artAndSportCompetitionNationLevel,
			int artAndSportCompetitionProvincialLevel, int academicPaper, int publishProduct, int studentPatent,
			double englishiLevelFourPassRate, double englishiLevelSixPassRate, double stuPhysicalHealPassRate,
			int goInterConferCount, String college) {
		super();
		this.academicCompetitionSum = academicCompetitionSum;
		this.academicCompetitionNationLevel = academicCompetitionNationLevel;
		this.academicCompetitionProvincialLevel = academicCompetitionProvincialLevel;
		this.innovationAndSkillCompetitionSum = innovationAndSkillCompetitionSum;
		this.innovationAndSkillCompetitionNationLevel = innovationAndSkillCompetitionNationLevel;
		this.innovationAndSkillCompetitionProvincialLevel = innovationAndSkillCompetitionProvincialLevel;
		this.artAndSportCompetitionSum = artAndSportCompetitionSum;
		this.artAndSportCompetitionNationLevel = artAndSportCompetitionNationLevel;
		this.artAndSportCompetitionProvincialLevel = artAndSportCompetitionProvincialLevel;
		this.academicPaper = academicPaper;
		this.publishProduct = publishProduct;
		this.studentPatent = studentPatent;
		this.englishiLevelFourPassRate = englishiLevelFourPassRate;
		this.englishiLevelSixPassRate = englishiLevelSixPassRate;
		this.stuPhysicalHealPassRate = stuPhysicalHealPassRate;
		this.goInterConferCount = goInterConferCount;
		this.college = college;
	}

	public int getAcademicCompetitionSum() {
		return academicCompetitionSum;
	}

	public void setAcademicCompetitionSum(int academicCompetitionSum) {
		this.academicCompetitionSum = academicCompetitionSum;
	}

	public int getAcademicCompetitionNationLevel() {
		return academicCompetitionNationLevel;
	}

	public void setAcademicCompetitionNationLevel(int academicCompetitionNationLevel) {
		this.academicCompetitionNationLevel = academicCompetitionNationLevel;
	}

	public int getAcademicCompetitionProvincialLevel() {
		return academicCompetitionProvincialLevel;
	}

	public void setAcademicCompetitionProvincialLevel(int academicCompetitionProvincialLevel) {
		this.academicCompetitionProvincialLevel = academicCompetitionProvincialLevel;
	}

	public int getInnovationAndSkillCompetitionSum() {
		return innovationAndSkillCompetitionSum;
	}

	public void setInnovationAndSkillCompetitionSum(int innovationAndSkillCompetitionSum) {
		this.innovationAndSkillCompetitionSum = innovationAndSkillCompetitionSum;
	}

	public int getInnovationAndSkillCompetitionNationLevel() {
		return innovationAndSkillCompetitionNationLevel;
	}

	public void setInnovationAndSkillCompetitionNationLevel(int innovationAndSkillCompetitionNationLevel) {
		this.innovationAndSkillCompetitionNationLevel = innovationAndSkillCompetitionNationLevel;
	}

	public int getInnovationAndSkillCompetitionProvincialLevel() {
		return innovationAndSkillCompetitionProvincialLevel;
	}

	public void setInnovationAndSkillCompetitionProvincialLevel(int innovationAndSkillCompetitionProvincialLevel) {
		this.innovationAndSkillCompetitionProvincialLevel = innovationAndSkillCompetitionProvincialLevel;
	}

	public int getArtAndSportCompetitionSum() {
		return artAndSportCompetitionSum;
	}

	public void setArtAndSportCompetitionSum(int artAndSportCompetitionSum) {
		this.artAndSportCompetitionSum = artAndSportCompetitionSum;
	}

	public int getArtAndSportCompetitionNationLevel() {
		return artAndSportCompetitionNationLevel;
	}

	public void setArtAndSportCompetitionNationLevel(int artAndSportCompetitionNationLevel) {
		this.artAndSportCompetitionNationLevel = artAndSportCompetitionNationLevel;
	}

	public int getArtAndSportCompetitionProvincialLevel() {
		return artAndSportCompetitionProvincialLevel;
	}

	public void setArtAndSportCompetitionProvincialLevel(int artAndSportCompetitionProvincialLevel) {
		this.artAndSportCompetitionProvincialLevel = artAndSportCompetitionProvincialLevel;
	}

	public int getAcademicPaper() {
		return academicPaper;
	}

	public void setAcademicPaper(int academicPaper) {
		this.academicPaper = academicPaper;
	}

	public int getPublishProduct() {
		return publishProduct;
	}

	public void setPublishProduct(int publishProduct) {
		this.publishProduct = publishProduct;
	}

	public int getStudentPatent() {
		return studentPatent;
	}

	public void setStudentPatent(int studentPatent) {
		this.studentPatent = studentPatent;
	}

	public double getEnglishiLevelFourPassRate() {
		return englishiLevelFourPassRate;
	}

	public void setEnglishiLevelFourPassRate(double englishiLevelFourPassRate) {
		this.englishiLevelFourPassRate = englishiLevelFourPassRate;
	}

	public double getEnglishiLevelSixPassRate() {
		return englishiLevelSixPassRate;
	}

	public void setEnglishiLevelSixPassRate(double englishiLevelSixPassRate) {
		this.englishiLevelSixPassRate = englishiLevelSixPassRate;
	}

	public double getStuPhysicalHealPassRate() {
		return stuPhysicalHealPassRate;
	}

	public void setStuPhysicalHealPassRate(double stuPhysicalHealPassRate) {
		this.stuPhysicalHealPassRate = stuPhysicalHealPassRate;
	}

	public int getGoInterConferCount() {
		return goInterConferCount;
	}

	public void setGoInterConferCount(int goInterConferCount) {
		this.goInterConferCount = goInterConferCount;
	}

	public String getCollege() {
		return college;
	}

	public void setCollege(String college) {
		this.college = college;
	}

	
}
