package cn.edu.xmu.entity;

/**
 * 3.4 优势专业概览
 * @author chunfeng 
 *
 */
public class AdvantageMajorOverview implements Comparable<AdvantageMajorOverview>{
    private int serialNumber;
    private String majorName;
    private String majorType;
    private String majorSetYear;
    private Integer selfTeacher; //本学院授课教师数
    private Integer otherTeacher; //外学院授课教师数
    private Integer highTeacher; //具有高级职称的教师数
    private Integer undergraduates; //本科学生数
    private Float stuVsTeacher; //学生与教师比
    private Integer stuFlow; //学生流动净值
    private Integer graduates; //应届毕业生数
    private Float firstWork; //初次就业率
    
    @Override
	public int compareTo(AdvantageMajorOverview arg0) {
		// TODO Auto-generated method stub
    	Float stuVsTeacher1 = this.getStuVsTeacher();
    	Float stuVsTeacher2 = arg0.getStuVsTeacher();
    	if(stuVsTeacher1 != null && stuVsTeacher2 != null)
    	   return this.getStuVsTeacher().compareTo(arg0.getStuVsTeacher());
    	return -1;
	}
    
    public AdvantageMajorOverview(int serialNumber, String majorName, String majorType, String majorSetYear, Integer selfTeacher, Integer otherTeacher,
           Integer highTeacher, Integer undergraduates, Float stuVsTeacher, Integer stuFlow, Integer graduates, Float firstWork){
    	this.serialNumber = serialNumber;
    	this.majorName = majorName;
    	this.majorType = majorType;
    	this.majorSetYear = majorSetYear;
    	this.selfTeacher = selfTeacher; //本学院授课教师数
    	this.otherTeacher = otherTeacher; //外学院授课教师数
    	this.highTeacher = highTeacher; //具有高级职称的教师数
    	this.undergraduates = undergraduates; //本科学生数
    	this.stuVsTeacher = stuVsTeacher; //学生与教师比
    	this.stuFlow = stuFlow; //学生流动净值
    	this.graduates = graduates; //应届毕业生数
    	this.firstWork = firstWork;
    }

	public int getSerialNumber() {
		return serialNumber;
	}

	public void setSerialNumber(int serialNumber) {
		this.serialNumber = serialNumber;
	}

	public String getMajorName() {
		return majorName;
	}

	public void setMajorName(String majorName) {
		this.majorName = majorName;
	}

	public String getMajorType() {
		return majorType;
	}

	public void setMajorType(String majorType) {
		this.majorType = majorType;
	}

	public String getMajorSetYear() {
		return majorSetYear;
	}

	public void setMajorSetYear(String majorSetYear) {
		this.majorSetYear = majorSetYear;
	}

	public Integer getSelfTeacher() {
		return selfTeacher;
	}

	public void setSelfTeacher(Integer selfTeacher) {
		this.selfTeacher = selfTeacher;
	}

	public Integer getOtherTeacher() {
		return otherTeacher;
	}

	public void setOtherTeacher(Integer otherTeacher) {
		this.otherTeacher = otherTeacher;
	}

	public Integer getHighTeacher() {
		return highTeacher;
	}

	public void setHighTeacher(Integer highTeacher) {
		this.highTeacher = highTeacher;
	}

	public Integer getUndergraduates() {
		return undergraduates;
	}

	public void setUndergraduates(Integer undergraduates) {
		this.undergraduates = undergraduates;
	}

	public Float getStuVsTeacher() {
		return stuVsTeacher;
	}

	public void setStuVsTeacher(Float stuVsTeacher) {
		this.stuVsTeacher = stuVsTeacher;
	}

	public Integer getStuFlow() {
		return stuFlow;
	}

	public void setStuFlow(Integer stuFlow) {
		this.stuFlow = stuFlow;
	}

	public Integer getGraduates() {
		return graduates;
	}

	public void setGraduates(Integer graduates) {
		this.graduates = graduates;
	}

	public Float getFirstWork() {
		return firstWork;
	}

	public void setFirstWork(Float firstWork) {
		this.firstWork = firstWork;
	}
 
    
}
