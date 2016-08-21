package cn.edu.xmu.entity;

/**
 * 
 * @author zsj
 * 2.5 教授、副教授讲授本科课程比例
 */
public class ProfessorAndAssociateForUnderSub {
	private String rowTitle;//行标题
	
	private String lessonTotal;//开课总数
	private String proLesson;//教授授课门次数（门次）
	private String assoLesson;//副教授授课门次数（门次）
	private String proproLesson;//教授所授专业课程门次数（门次）
	private String proCommLesson;//教授所授公共必修课门次数（门次）
	
	private String college;

	public String getRowTitle() {
		return rowTitle;
	}

	public void setRowTitle(String rowTitle) {
		this.rowTitle = rowTitle;
	}

	public String getLessonTotal() {
		return lessonTotal;
	}

	public void setLessonTotal(String lessonTotal) {
		this.lessonTotal = lessonTotal;
	}

	public String getProLesson() {
		return proLesson;
	}

	public void setProLesson(String proLesson) {
		this.proLesson = proLesson;
	}

	public String getAssoLesson() {
		return assoLesson;
	}

	public void setAssoLesson(String assoLesson) {
		this.assoLesson = assoLesson;
	}

	public String getProproLesson() {
		return proproLesson;
	}

	public void setProproLesson(String proproLesson) {
		this.proproLesson = proproLesson;
	}

	public String getProCommLesson() {
		return proCommLesson;
	}

	public void setProCommLesson(String proCommLesson) {
		this.proCommLesson = proCommLesson;
	}

	public String getCollege() {
		return college;
	}

	public void setCollege(String college) {
		this.college = college;
	}

	public ProfessorAndAssociateForUnderSub(String rowTitle,
			String lessonTotal, String proLesson, String assoLesson,
			String proproLesson, String proCommLesson, String college) {
		super();
		this.rowTitle = rowTitle;
		this.lessonTotal = lessonTotal;
		this.proLesson = proLesson;
		this.assoLesson = assoLesson;
		this.proproLesson = proproLesson;
		this.proCommLesson = proCommLesson;
		this.college = college;
	}
	
	
}
