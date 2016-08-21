package cn.edu.xmu.entity;


/**
 * 3.8 全校课程开设情况
 * @author yue
 *
 */
public class StartClassInfo {

	private String courseCategory;//课程类别
	private int courseCount;//课程门数
	private int courseAmount;//课程门次数
	private int bilingualCount;//双语课程门数
	private float avgHour;//平均学时数
	private float avgStudent;//平均班规模(人)
	private String college;//学院
	public StartClassInfo(String courseCategory, int courseCount, int courseAmount, int bilingualCount, float avgHour,
			float avgStudent, String college) {
		super();
		this.courseCategory = courseCategory;
		this.courseCount = courseCount;
		this.courseAmount = courseAmount;
		this.bilingualCount = bilingualCount;
		this.avgHour = avgHour;
		this.avgStudent = avgStudent;
		this.college = college;
	}
	public String getCourseCategory() {
		return courseCategory;
	}
	public void setCourseCategory(String courseCategory) {
		this.courseCategory = courseCategory;
	}
	public int getCourseCount() {
		return courseCount;
	}
	public void setCourseCount(int courseCount) {
		this.courseCount = courseCount;
	}
	public int getCourseAmount() {
		return courseAmount;
	}
	public void setCourseAmount(int courseAmount) {
		this.courseAmount = courseAmount;
	}
	public int getBilingualCount() {
		return bilingualCount;
	}
	public void setBilingualCount(int bilingualCount) {
		this.bilingualCount = bilingualCount;
	}
	public float getAvgHour() {
		return avgHour;
	}
	public void setAvgHour(float avgHour) {
		this.avgHour = avgHour;
	}
	public float getAvgStudent() {
		return avgStudent;
	}
	public void setAvgStudent(float avgStudent) {
		this.avgStudent = avgStudent;
	}
	public String getCollege() {
		return college;
	}
	public void setCollege(String college) {
		this.college = college;
	}
	

	
	
}
