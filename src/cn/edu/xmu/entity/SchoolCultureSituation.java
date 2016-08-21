package cn.edu.xmu.entity;

public class SchoolCultureSituation {
	
	private String rowTitle;//行名
	//文化、学术讲座 总数
	private int lectureTotalCount;
	//文化、学术讲座 其中：校级
	private int lectureSchoolCount;
	//文化、学术讲座 院（系）级
	private int lectureInstituteCount;
	
	//本科生课外科技、文化活动项目  总数
	private int projectTotalCount;
	//本科生课外科技、文化活动项目 其中：国家大学生创新性试验计划项目
	private int planProjectCount;
	//本科生课外科技、文化活动项目 省级项目
	private int provinceProjectCount;
	//本科生课外科技、文化活动项目 学校项目
	private int schoolProjectCount;
	
	private String college;

	public SchoolCultureSituation() {
		super();
	}

	public SchoolCultureSituation(String rowTitle, int lectureTotalCount,
			int lectureSchoolCount, int lectureInstituteCount,
			int projectTotalCount, int planProjectCount,
			int provinceProjectCount, int schoolProjectCount, String college) {
		super();
		this.rowTitle = rowTitle;
		this.lectureTotalCount = lectureTotalCount;
		this.lectureSchoolCount = lectureSchoolCount;
		this.lectureInstituteCount = lectureInstituteCount;
		this.projectTotalCount = projectTotalCount;
		this.planProjectCount = planProjectCount;
		this.provinceProjectCount = provinceProjectCount;
		this.schoolProjectCount = schoolProjectCount;
		this.college = college;
	}

	public String getRowTitle() {
		return rowTitle;
	}

	public void setRowTitle(String rowTitle) {
		this.rowTitle = rowTitle;
	}

	public int getLectureTotalCount() {
		return lectureTotalCount;
	}

	public void setLectureTotalCount(int lectureTotalCount) {
		this.lectureTotalCount = lectureTotalCount;
	}

	public int getLectureSchoolCount() {
		return lectureSchoolCount;
	}

	public void setLectureSchoolCount(int lectureSchoolCount) {
		this.lectureSchoolCount = lectureSchoolCount;
	}

	public int getLectureInstituteCount() {
		return lectureInstituteCount;
	}

	public void setLectureInstituteCount(int lectureInstituteCount) {
		this.lectureInstituteCount = lectureInstituteCount;
	}

	public int getProjectTotalCount() {
		return projectTotalCount;
	}

	public void setProjectTotalCount(int projectTotalCount) {
		this.projectTotalCount = projectTotalCount;
	}

	public int getPlanProjectCount() {
		return planProjectCount;
	}

	public void setPlanProjectCount(int planProjectCount) {
		this.planProjectCount = planProjectCount;
	}

	public int getProvinceProjectCount() {
		return provinceProjectCount;
	}

	public void setProvinceProjectCount(int provinceProjectCount) {
		this.provinceProjectCount = provinceProjectCount;
	}

	public int getSchoolProjectCount() {
		return schoolProjectCount;
	}

	public void setSchoolProjectCount(int schoolProjectCount) {
		this.schoolProjectCount = schoolProjectCount;
	}

	public String getCollege() {
		return college;
	}

	public void setCollege(String college) {
		this.college = college;
	}
	
	
	
	

}
