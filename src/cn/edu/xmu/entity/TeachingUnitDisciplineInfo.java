package cn.edu.xmu.entity;

/**
 * 
 * @author zsj
 * 1.6 教学单位学科专业概览
 */
public class TeachingUnitDisciplineInfo {
	private String departmentName;//单位
	private int bachelorDegree;//本科专业数
	private int mobileStationForPostDoctor;//博士后流动站数
	private int doctorFirstLevelDiscipline;//博士学位授权一级学科点
	private int doctorSecondLevelDiscipline;//博士学位授权二级学科点（不含一级覆盖点）
	private int masterFirstLevelDiscipline;//硕士学位授权一级学科点
	private int masterSecondLevelDiscipline;//硕士学位授权二级学科点（不含一级覆盖点）
	private int keyDiscipline;//重点学科数
	
	private String college;//所属学院

	public String getDepartmentName() {
		return departmentName;
	}

	public void setDepartmentName(String departmentName) {
		this.departmentName = departmentName;
	}

	public int getBachelorDegree() {
		return bachelorDegree;
	}

	public void setBachelorDegree(int bachelorDegree) {
		this.bachelorDegree = bachelorDegree;
	}

	public int getMobileStationForPostDoctor() {
		return mobileStationForPostDoctor;
	}

	public void setMobileStationForPostDoctor(int mobileStationForPostDoctor) {
		this.mobileStationForPostDoctor = mobileStationForPostDoctor;
	}

	public int getDoctorFirstLevelDiscipline() {
		return doctorFirstLevelDiscipline;
	}

	public void setDoctorFirstLevelDiscipline(int doctorFirstLevelDiscipline) {
		this.doctorFirstLevelDiscipline = doctorFirstLevelDiscipline;
	}

	public int getDoctorSecondLevelDiscipline() {
		return doctorSecondLevelDiscipline;
	}

	public void setDoctorSecondLevelDiscipline(int doctorSecondLevelDiscipline) {
		this.doctorSecondLevelDiscipline = doctorSecondLevelDiscipline;
	}

	public int getMasterFirstLevelDiscipline() {
		return masterFirstLevelDiscipline;
	}

	public void setMasterFirstLevelDiscipline(int masterFirstLevelDiscipline) {
		this.masterFirstLevelDiscipline = masterFirstLevelDiscipline;
	}

	public int getMasterSecondLevelDiscipline() {
		return masterSecondLevelDiscipline;
	}

	public void setMasterSecondLevelDiscipline(int masterSecondLevelDiscipline) {
		this.masterSecondLevelDiscipline = masterSecondLevelDiscipline;
	}

	public int getKeyDiscipline() {
		return keyDiscipline;
	}

	public void setKeyDiscipline(int keyDiscipline) {
		this.keyDiscipline = keyDiscipline;
	}

	public String getCollege() {
		return college;
	}

	public void setCollege(String college) {
		this.college = college;
	}

	public TeachingUnitDisciplineInfo(String departmentName,
			int bachelorDegree, int mobileStationForPostDoctor,
			int doctorFirstLevelDiscipline, int doctorSecondLevelDiscipline,
			int masterFirstLevelDiscipline, int masterSecondLevelDiscipline,
			int keyDiscipline, String college) {
		super();
		this.departmentName = departmentName;
		this.bachelorDegree = bachelorDegree;
		this.mobileStationForPostDoctor = mobileStationForPostDoctor;
		this.doctorFirstLevelDiscipline = doctorFirstLevelDiscipline;
		this.doctorSecondLevelDiscipline = doctorSecondLevelDiscipline;
		this.masterFirstLevelDiscipline = masterFirstLevelDiscipline;
		this.masterSecondLevelDiscipline = masterSecondLevelDiscipline;
		this.keyDiscipline = keyDiscipline;
		this.college = college;
	}
	
	

}
