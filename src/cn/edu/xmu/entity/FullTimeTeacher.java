package cn.edu.xmu.entity;

/**
 * 附表3 各教学单位专任教师结构
 * @author yue
 *
 */
public class FullTimeTeacher {
	//序号
	private int serialnumber;
	//单位
	private String department;
	//专任教师数
	private int fullTimeTeacherCount;
	//职称
	//教授
	private int professor;
	//副教授
	private int associateProfessor;
	//其他
	private int otherProfessionalTitles;
	//学位
	//博士
	private int doctor;
	//硕士
	private int master;
	//其他
	private int otherDegrees;
	
	//年龄
	//35岁及以下
	private int under35;
	//36-45
	private int between36_45;
	//46-55
	private int between46_55;
	//56岁及以上
	private int above56;
	
	//学缘
	//本校
	private int inSchool;
	//外校
	//境外
	private int abroad;
	//境内
	private int territory;
	
	private String college;

	public FullTimeTeacher(int serialnumber, String department, int fullTimeTeacherCount, int professor,
			int associateProfessor, int otherProfessionalTitles, int doctor, int master, int otherDegrees, int under35,
			int between36_45, int between46_55, int above56, int inSchool, int abroad, int territory, String college) {
		super();
		this.serialnumber = serialnumber;
		this.department = department;
		this.fullTimeTeacherCount = fullTimeTeacherCount;
		this.professor = professor;
		this.associateProfessor = associateProfessor;
		this.otherProfessionalTitles = otherProfessionalTitles;
		this.doctor = doctor;
		this.master = master;
		this.otherDegrees = otherDegrees;
		this.under35 = under35;
		this.between36_45 = between36_45;
		this.between46_55 = between46_55;
		this.above56 = above56;
		this.inSchool = inSchool;
		this.abroad = abroad;
		this.territory = territory;
		this.college = college;
	}


	public int getSerialnumber() {
		return serialnumber;
	}

	public void setSerialnumber(int serialnumber) {
		this.serialnumber = serialnumber;
	}

	public String getDepartment() {
		return department;
	}

	public void setDepartment(String department) {
		this.department = department;
	}

	public int getFullTimeTeacherCount() {
		return fullTimeTeacherCount;
	}

	public void setFullTimeTeacherCount(int fullTimeTeacherCount) {
		this.fullTimeTeacherCount = fullTimeTeacherCount;
	}

	public int getProfessor() {
		return professor;
	}

	public void setProfessor(int professor) {
		this.professor = professor;
	}

	public int getAssociateProfessor() {
		return associateProfessor;
	}

	public void setAssociateProfessor(int associateProfessor) {
		this.associateProfessor = associateProfessor;
	}

	public int getOtherProfessionalTitles() {
		return otherProfessionalTitles;
	}

	public void setOtherProfessionalTitles(int otherProfessionalTitles) {
		this.otherProfessionalTitles = otherProfessionalTitles;
	}

	public int getDoctor() {
		return doctor;
	}

	public void setDoctor(int doctor) {
		this.doctor = doctor;
	}

	public int getMaster() {
		return master;
	}

	public void setMaster(int master) {
		this.master = master;
	}

	public int getOtherDegrees() {
		return otherDegrees;
	}

	public void setOtherDegrees(int otherDegrees) {
		this.otherDegrees = otherDegrees;
	}

	public int getUnder35() {
		return under35;
	}

	public void setUnder35(int under35) {
		this.under35 = under35;
	}

	public int getBetween36_45() {
		return between36_45;
	}

	public void setBetween36_45(int between36_45) {
		this.between36_45 = between36_45;
	}

	public int getBetween46_55() {
		return between46_55;
	}

	public void setBetween46_55(int between46_55) {
		this.between46_55 = between46_55;
	}

	public int getAbove56() {
		return above56;
	}

	public void setAbove56(int above56) {
		this.above56 = above56;
	}

	public int getInSchool() {
		return inSchool;
	}

	public void setInSchool(int inSchool) {
		this.inSchool = inSchool;
	}

	public int getAbroad() {
		return abroad;
	}

	public void setAbroad(int abroad) {
		this.abroad = abroad;
	}

	public int getTerritory() {
		return territory;
	}

	public void setTerritory(int territory) {
		this.territory = territory;
	}

	public String getCollege() {
		return college;
	}

	public void setCollege(String college) {
		this.college = college;
	}
	
	
	
	
}
