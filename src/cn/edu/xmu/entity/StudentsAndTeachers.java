package cn.edu.xmu.entity;
/**
 * 
 * @author zsj
 * 2.1 学校生师比及教师情况
 */
public class StudentsAndTeachers {
	private String rowName;//行名称
	/*专任教师*/
	private String fullTotal;
	private String master;//具有硕士学位
	private String doctor;//具有博士学位
	private String doubleType;//双师型
	private String engineeringBack;//具有工程背景
	private String industryBack;//具有行业背景
	/*外聘教师*/
	private String foreignTotal;
	private String outside;//境外
	/*折合在校生*/
	private String studentsNum;
	/*师生比*/
	private String proportion;
	/*本科课程授课教师数*/
	private String underLessonTeacherNum;
	
	private String college;

	public String getRowName() {
		return rowName;
	}

	public void setRowName(String rowName) {
		this.rowName = rowName;
	}

	public String getFullTotal() {
		return fullTotal;
	}

	public void setFullTotal(String fullTotal) {
		this.fullTotal = fullTotal;
	}

	public String getMaster() {
		return master;
	}

	public void setMaster(String master) {
		this.master = master;
	}

	public String getDoctor() {
		return doctor;
	}

	public void setDoctor(String doctor) {
		this.doctor = doctor;
	}

	public String getDoubleType() {
		return doubleType;
	}

	public void setDoubleType(String doubleType) {
		this.doubleType = doubleType;
	}

	public String getEngineeringBack() {
		return engineeringBack;
	}

	public void setEngineeringBack(String engineeringBack) {
		this.engineeringBack = engineeringBack;
	}

	public String getIndustryBack() {
		return industryBack;
	}

	public void setIndustryBack(String industryBack) {
		this.industryBack = industryBack;
	}

	public String getForeignTotal() {
		return foreignTotal;
	}

	public void setForeignTotal(String foreignTotal) {
		this.foreignTotal = foreignTotal;
	}

	public String getOutside() {
		return outside;
	}

	public void setOutside(String outside) {
		this.outside = outside;
	}

	public String getStudentsNum() {
		return studentsNum;
	}

	public void setStudentsNum(String studentsNum) {
		this.studentsNum = studentsNum;
	}

	public String getProportion() {
		return proportion;
	}

	public void setProportion(String proportion) {
		this.proportion = proportion;
	}

	public String getUnderLessonTeacherNum() {
		return underLessonTeacherNum;
	}

	public void setUnderLessonTeacherNum(String underLessonTeacherNum) {
		this.underLessonTeacherNum = underLessonTeacherNum;
	}

	public String getCollege() {
		return college;
	}

	public void setCollege(String college) {
		this.college = college;
	}

	public StudentsAndTeachers(String rowName, String fullTotal, String master,
			String doctor, String doubleType, String engineeringBack,
			String industryBack, String foreignTotal, String outside,
			String studentsNum, String proportion,
			String underLessonTeacherNum, String college) {
		super();
		this.rowName = rowName;
		this.fullTotal = fullTotal;
		this.master = master;
		this.doctor = doctor;
		this.doubleType = doubleType;
		this.engineeringBack = engineeringBack;
		this.industryBack = industryBack;
		this.foreignTotal = foreignTotal;
		this.outside = outside;
		this.studentsNum = studentsNum;
		this.proportion = proportion;
		this.underLessonTeacherNum = underLessonTeacherNum;
		this.college = college;
	}
	
	
	
	
}
