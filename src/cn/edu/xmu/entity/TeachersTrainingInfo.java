package cn.edu.xmu.entity;

/**
 * 
 * @author zsj
 * 2.6 教师培养培训情况
 */
public class TeachersTrainingInfo {
	 private String rowTitle;//行名
	 
	 private int total;//总计
	 private int docterStudyCount;//攻读博士学位
	 private double docterStudyPer; // 攻读博士学位占当年培养培训教师的比例（%）
	 private int inside;// 境内进修人次数（人次）
	 private int outside;// 境外进修人次数（人次）
	 private int project;//参与教改立项课题（校级以上）人次数（人次）
	 
	 private String college;//学院 

	public String getRowTitle() {
		return rowTitle;
	}

	public void setRowTitle(String rowTitle) {
		this.rowTitle = rowTitle;
	}

	public int getTotal() {
		return total;
	}

	public void setTotal(int total) {
		this.total = total;
	}

	public int getDocterStudyCount() {
		return docterStudyCount;
	}

	public void setDocterStudyCount(int docterStudyCount) {
		this.docterStudyCount = docterStudyCount;
	}

	public double getDocterStudyPer() {
		return docterStudyPer;
	}

	public void setDocterStudyPer(double docterStudyPer) {
		this.docterStudyPer = docterStudyPer;
	}

	public int getInside() {
		return inside;
	}

	public void setInside(int inside) {
		this.inside = inside;
	}

	public int getOutside() {
		return outside;
	}

	public void setOutside(int outside) {
		this.outside = outside;
	}

	public int getProject() {
		return project;
	}

	public void setProject(int project) {
		this.project = project;
	}

	public String getCollege() {
		return college;
	}

	public void setCollege(String college) {
		this.college = college;
	}

	public TeachersTrainingInfo(String rowTitle, int total,
			int docterStudyCount, double docterStudyPer, int inside,
			int outside, int project, String college) {
		super();
		this.rowTitle = rowTitle;
		this.total = total;
		this.docterStudyCount = docterStudyCount;
		this.docterStudyPer = docterStudyPer;
		this.inside = inside;
		this.outside = outside;
		this.project = project;
		this.college = college;
	}
	 
	 
	
	 

}
