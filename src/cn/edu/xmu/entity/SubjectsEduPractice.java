package cn.edu.xmu.entity;
/**
 * 
 * @author zshbleaker
 * 附表10 各专业实践教学情况
 */

public class SubjectsEduPractice {

	private int number;			//序号
	private String subject;		//专业
	private int practiceScore;	//实践教学学分
	private double practiceScoreRate; //实践教学占总学分
	private int expScore;
	private int classWithExp;
	private int classwithOwnExp;
	private double outRate;
	private int multiExp;
	
	private String college;
	
	

	public SubjectsEduPractice() {
		super();
	}

	public int getNumber() {
		return number;
	}

	public void setNumber(int number) {
		this.number = number;
	}

	public String getSubject() {
		return subject;
	}

	public void setSubject(String subject) {
		this.subject = subject;
	}

	public int getPracticeScore() {
		return practiceScore;
	}

	public void setPracticeScore(int practiceScore) {
		this.practiceScore = practiceScore;
	}

	public double getPracticeScoreRate() {
		return practiceScoreRate;
	}

	public void setPracticeScoreRate(double practiceScoreRate) {
		this.practiceScoreRate = practiceScoreRate;
	}

	public int getExpScore() {
		return expScore;
	}

	public void setExpScore(int expScore) {
		this.expScore = expScore;
	}

	public int getClassWithExp() {
		return classWithExp;
	}

	public void setClassWithExp(int classWithExp) {
		this.classWithExp = classWithExp;
	}

	public int getClasswithOwnExp() {
		return classwithOwnExp;
	}

	public void setClasswithOwnExp(int classwithOwnExp) {
		this.classwithOwnExp = classwithOwnExp;
	}

	public double getOutRate() {
		return outRate;
	}

	public void setOutRate(double outRate) {
		this.outRate = outRate;
	}

	public int getMultiExp() {
		return multiExp;
	}

	public void setMultiExp(int multiExp) {
		this.multiExp = multiExp;
	}

	public String getCollege() {
		return college;
	}

	public void setCollege(String college) {
		this.college = college;
	}
	
	
}
