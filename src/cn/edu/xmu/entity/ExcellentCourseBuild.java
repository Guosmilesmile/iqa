package cn.edu.xmu.entity;

/**
 * 3.9 精品（优秀）课程（群）建设
 * @author yue
 *
 */
public class ExcellentCourseBuild {
	private int nationalLevel;//国家级
	private int provicalLevel;//省部级
	private String college;//学院
	public ExcellentCourseBuild(int nationalLevel, int provicalLevel, String college) {
		super();
		this.nationalLevel = nationalLevel;
		this.provicalLevel = provicalLevel;
		this.college = college;
	}
	public int getNationalLevel() {
		return nationalLevel;
	}
	public void setNationalLevel(int nationalLevel) {
		this.nationalLevel = nationalLevel;
	}
	public int getProvicalLevel() {
		return provicalLevel;
	}
	public void setProvicalLevel(int provicalLevel) {
		this.provicalLevel = provicalLevel;
	}
	public String getCollege() {
		return college;
	}
	public void setCollege(String college) {
		this.college = college;
	}

	
	
}
