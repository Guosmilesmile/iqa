package cn.edu.xmu.entity;
/**
 * 
 * @author zshbleaker
 * 3.3 专业情况概览
 *
 */

public class ProfessionOverview {
	private int number;
	private String name;
	private int startYear;
	private int selfProfNum;
	private int otherProfNum;
	private int profWithTitle;
	private int bachelorNum;
	private double stuDivTeacher;
	private double stuFlowRate;
	private int graduateNum;
	private double jobRate;
	
	private String college;
	
	

	public ProfessionOverview() {
		super();
	}
	
	

	public ProfessionOverview(double stuDivTeacher) {
		super();
		this.stuDivTeacher = stuDivTeacher;
	}



	public int getNumber() {
		return number;
	}

	public void setNumber(int number) {
		this.number = number;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public int getStartYear() {
		return startYear;
	}

	public void setStartYear(int startYear) {
		this.startYear = startYear;
	}

	public int getSelfProfNum() {
		return selfProfNum;
	}

	public void setSelfProfNum(int selfProfNum) {
		this.selfProfNum = selfProfNum;
	}

	public int getOtherProfNum() {
		return otherProfNum;
	}

	public void setOtherProfNum(int otherProfNum) {
		this.otherProfNum = otherProfNum;
	}

	public int getProfWithTitle() {
		return profWithTitle;
	}

	public void setProfWithTitle(int profWithTitle) {
		this.profWithTitle = profWithTitle;
	}

	public int getBachelorNum() {
		return bachelorNum;
	}

	public void setBachelorNum(int bachelorNum) {
		this.bachelorNum = bachelorNum;
	}

	public double getStuDivTeacher() {
		return stuDivTeacher;
	}

	public void setStuDivTeacher(double stuDivTeacher) {
		this.stuDivTeacher = stuDivTeacher;
	}

	public double getStuFlowRate() {
		return stuFlowRate;
	}

	public void setStuFlowRate(double stuFlowRate) {
		this.stuFlowRate = stuFlowRate;
	}

	public int getGraduateNum() {
		return graduateNum;
	}

	public void setGraduateNum(int graduateNum) {
		this.graduateNum = graduateNum;
	}

	public double getJobRate() {
		return jobRate;
	}

	public void setJobRate(double jobRate) {
		this.jobRate = jobRate;
	}

	public String getCollege() {
		return college;
	}

	public void setCollege(String college) {
		this.college = college;
	}
	
	
}
