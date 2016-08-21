package cn.edu.xmu.entity;
/**
 * 
 * @author zshbleaker
 * 3.4 优势专业概览
 */

public class GoodProfessionOverview {
	private int number;
	private String name;
	private String type;
	private int startYear;
	private int selfTeacher;
	private int outTeacher;
	private int teacherWithTitle;
	private int bachelorNum;
	private double stuDivTeacher;
	private double flowRate;
	private int graduateNum;
	private double jobRate;
	
	private String college;
	
	

	public GoodProfessionOverview() {
		super();
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

	public String getType() {
		return type;
	}

	public void setType(String type) {
		this.type = type;
	}

	public int getStartYear() {
		return startYear;
	}

	public void setStartYear(int startYear) {
		this.startYear = startYear;
	}

	public int getSelfTeacher() {
		return selfTeacher;
	}

	public void setSelfTeacher(int selfTeacher) {
		this.selfTeacher = selfTeacher;
	}

	public int getOutTeacher() {
		return outTeacher;
	}

	public void setOutTeacher(int outTeacher) {
		this.outTeacher = outTeacher;
	}

	public int getTeacherWithTitle() {
		return teacherWithTitle;
	}

	public void setTeacherWithTitle(int teacherWithTitle) {
		this.teacherWithTitle = teacherWithTitle;
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

	public double getFlowRate() {
		return flowRate;
	}

	public void setFlowRate(double flowRate) {
		this.flowRate = flowRate;
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
