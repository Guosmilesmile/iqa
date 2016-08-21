package cn.edu.xmu.entity;

/**
 * 
 * @author zsj
 * 2.3各教学单位教师与本科生情况
 */
public class TeachingUnitTeachersAndUnder {
	private String department;//单位
	private int fulltimeteacherTotal;//专任教师总数
	private int highTitleCount;//具有高级职称的专任教师数
	private double highTitlePer;//比例
	private int youngTeacherCount;//年轻的专任教师数
	private double youngTeacherPer;//比例
	private int newIn5yearCount;//5年内新增专任教师数
	private double newIn5yearPer;//比例
	private int externalTeacherCount;//外聘教师数
	private int underCount;//本科生总数
	private double underVsFull;//本科生比专任教师
	
	private String college;

	public String getDepartment() {
		return department;
	}

	public void setDepartment(String department) {
		this.department = department;
	}

	public int getFulltimeteacherTotal() {
		return fulltimeteacherTotal;
	}

	public void setFulltimeteacherTotal(int fulltimeteacherTotal) {
		this.fulltimeteacherTotal = fulltimeteacherTotal;
	}

	public int getHighTitleCount() {
		return highTitleCount;
	}

	public void setHighTitleCount(int highTitleCount) {
		this.highTitleCount = highTitleCount;
	}

	public double getHighTitlePer() {
		return highTitlePer;
	}

	public void setHighTitlePer(double highTitlePer) {
		this.highTitlePer = highTitlePer;
	}

	public int getYoungTeacherCount() {
		return youngTeacherCount;
	}

	public void setYoungTeacherCount(int youngTeacherCount) {
		this.youngTeacherCount = youngTeacherCount;
	}

	public double getYoungTeacherPer() {
		return youngTeacherPer;
	}

	public void setYoungTeacherPer(double youngTeacherPer) {
		this.youngTeacherPer = youngTeacherPer;
	}

	public int getNewIn5yearCount() {
		return newIn5yearCount;
	}

	public void setNewIn5yearCount(int newIn5yearCount) {
		this.newIn5yearCount = newIn5yearCount;
	}

	public double getNewIn5yearPer() {
		return newIn5yearPer;
	}

	public void setNewIn5yearPer(double newIn5yearPer) {
		this.newIn5yearPer = newIn5yearPer;
	}

	public int getExternalTeacherCount() {
		return externalTeacherCount;
	}

	public void setExternalTeacherCount(int externalTeacherCount) {
		this.externalTeacherCount = externalTeacherCount;
	}

	public int getUnderCount() {
		return underCount;
	}

	public void setUnderCount(int underCount) {
		this.underCount = underCount;
	}

	public double getUnderVsFull() {
		return underVsFull;
	}

	public void setUnderVsFull(double underVsFull) {
		this.underVsFull = underVsFull;
	}

	public String getCollege() {
		return college;
	}

	public void setCollege(String college) {
		this.college = college;
	}

	public TeachingUnitTeachersAndUnder(String department,
			int fulltimeteacherTotal, int highTitleCount, double highTitlePer,
			int youngTeacherCount, double youngTeacherPer, int newIn5yearCount,
			double newIn5yearPer, int externalTeacherCount, int underCount,
			double underVsFull, String college) {
		super();
		this.department = department;
		this.fulltimeteacherTotal = fulltimeteacherTotal;
		this.highTitleCount = highTitleCount;
		this.highTitlePer = highTitlePer;
		this.youngTeacherCount = youngTeacherCount;
		this.youngTeacherPer = youngTeacherPer;
		this.newIn5yearCount = newIn5yearCount;
		this.newIn5yearPer = newIn5yearPer;
		this.externalTeacherCount = externalTeacherCount;
		this.underCount = underCount;
		this.underVsFull = underVsFull;
		this.college = college;
	}
	
	
	
}
