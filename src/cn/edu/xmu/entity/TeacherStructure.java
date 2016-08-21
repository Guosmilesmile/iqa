package cn.edu.xmu.entity;

/**
 * 2.2 教师队伍结构
 * @author zsj
 *
 */
public class TeacherStructure {
	private String rowName;//行名称
	
	/*职称*/
	private String professor;//教授
	private String associateProfessor;//副教授
	private String lecturer;//讲师
	private String assistant;//助教
	private String noTitle;//无职称
	
	/*最高学位*/
	private String doctor;//博士
	private String master;//硕士
	private String bachelor;//学士
	private String noDegree;//无学位
	
	/*年龄*/
	private String below35;//35以下
	private String between36_45;
	private String between46_55;
	private String upon56;
	
	/*学缘*/
	private String selfSchool;//本校
	private String inside;//境内
	private String outside;//境外
	
	private String college;

	public String getRowName() {
		return rowName;
	}

	public void setRowName(String rowName) {
		this.rowName = rowName;
	}

	public String getProfessor() {
		return professor;
	}

	public void setProfessor(String professor) {
		this.professor = professor;
	}

	public String getAssociateProfessor() {
		return associateProfessor;
	}

	public void setAssociateProfessor(String associateProfessor) {
		this.associateProfessor = associateProfessor;
	}

	public String getLecturer() {
		return lecturer;
	}

	public void setLecturer(String lecturer) {
		this.lecturer = lecturer;
	}

	public String getAssistant() {
		return assistant;
	}

	public void setAssistant(String assistant) {
		this.assistant = assistant;
	}

	public String getNoTitle() {
		return noTitle;
	}

	public void setNoTitle(String noTitle) {
		this.noTitle = noTitle;
	}

	public String getDoctor() {
		return doctor;
	}

	public void setDoctor(String doctor) {
		this.doctor = doctor;
	}

	public String getMaster() {
		return master;
	}

	public void setMaster(String master) {
		this.master = master;
	}

	public String getBachelor() {
		return bachelor;
	}

	public void setBachelor(String bachelor) {
		this.bachelor = bachelor;
	}

	public String getNoDegree() {
		return noDegree;
	}

	public void setNoDegree(String noDegree) {
		this.noDegree = noDegree;
	}

	public String getBelow35() {
		return below35;
	}

	public void setBelow35(String below35) {
		this.below35 = below35;
	}

	public String getBetween36_45() {
		return between36_45;
	}

	public void setBetween36_45(String between36_45) {
		this.between36_45 = between36_45;
	}

	public String getBetween46_55() {
		return between46_55;
	}

	public void setBetween46_55(String between46_55) {
		this.between46_55 = between46_55;
	}

	public String getUpon56() {
		return upon56;
	}

	public void setUpon56(String upon56) {
		this.upon56 = upon56;
	}

	public String getSelfSchool() {
		return selfSchool;
	}

	public void setSelfSchool(String selfSchool) {
		this.selfSchool = selfSchool;
	}

	public String getInside() {
		return inside;
	}

	public void setInside(String inside) {
		this.inside = inside;
	}

	public String getOutside() {
		return outside;
	}

	public void setOutside(String outside) {
		this.outside = outside;
	}

	public String getCollege() {
		return college;
	}

	public void setCollege(String college) {
		this.college = college;
	}

	public TeacherStructure(String rowName, String professor,
			String associateProfessor, String lecturer, String assistant,
			String noTitle, String doctor, String master, String bachelor,
			String noDegree, String below35, String between36_45,
			String between46_55, String upon56, String selfSchool,
			String inside, String outside, String college) {
		super();
		this.rowName = rowName;
		this.professor = professor;
		this.associateProfessor = associateProfessor;
		this.lecturer = lecturer;
		this.assistant = assistant;
		this.noTitle = noTitle;
		this.doctor = doctor;
		this.master = master;
		this.bachelor = bachelor;
		this.noDegree = noDegree;
		this.below35 = below35;
		this.between36_45 = between36_45;
		this.between46_55 = between46_55;
		this.upon56 = upon56;
		this.selfSchool = selfSchool;
		this.inside = inside;
		this.outside = outside;
		this.college = college;
	}
	
	
	
	
}
