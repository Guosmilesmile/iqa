package cn.edu.xmu.entity;


/**
 * 毕业综合训练情况
 * @author chunfeng
 *
 */
public class GraduateCombinedTrain implements Comparable<GraduateCombinedTrain>{

	 private int serialNumber; //序号
	 private String major;  //专业
	 private Integer projectNum; //课题数
	 private Integer socialFinish; //在实验、实习、工程实践和社会调查等社会实践中完成数
	 private Float rate; //比例（%）
	 private Integer fullTeacher; //专任教师
	 private Integer partTeacher;  //外聘教师
	 private Float stuPerTeacher;  //每名教师平均指导毕业生数
	 private String college;
	 private String rowTitle = "每名教师指导毕业生数最高的五个专业：";//用于最低和最高的五个专业分组显示
	 
	public int getSerialNumber() {
		return serialNumber;
	}


	public void setSerialNumber(int serialNumber) {
		this.serialNumber = serialNumber;
	}


	public String getMajor() {
		return major;
	}


	public void setMajor(String major) {
		this.major = major;
	}


	public Integer getProjectNum() {
		return projectNum;
	}


	public void setProjectNum(Integer projectNum) {
		this.projectNum = projectNum;
	}


	public Integer getSocialFinish() {
		return socialFinish;
	}


	public void setSocialFinish(Integer socialFinish) {
		this.socialFinish = socialFinish;
	}


	public Float getRate() {
		return rate;
	}


	public void setRate(Float rate) {
		this.rate = rate;
	}


	public Integer getFullTeacher() {
		return fullTeacher;
	}


	public void setFullTeacher(Integer fullTeacher) {
		this.fullTeacher = fullTeacher;
	}


	public Integer getPartTeacher() {
		return partTeacher;
	}


	public void setPartTeacher(Integer partTeacher) {
		this.partTeacher = partTeacher;
	}


	public Float getStuPerTeacher() {
		return stuPerTeacher;
	}


	public void setStuPerTeacher(Float stuPerTeacher) {
		this.stuPerTeacher = stuPerTeacher;
	}


	public String getCollege() {
		return college;
	}


	public void setCollege(String college) {
		this.college = college;
	}
    
	public String getRowTitle() {
		return rowTitle;
	}


	public void setRowTitle(String rowTitle) {
		this.rowTitle = rowTitle;
	}


	public GraduateCombinedTrain(int serialNumber, String major, Integer projectNum, Integer socialFinish, Float rate,
			Integer fullTeacher, Integer partTeacher, Float stuPerTeacher, String college, String rowTitle)
	{
		this.serialNumber = serialNumber; //序号
		this.major = major;  //专业
		this.projectNum = projectNum; //课题数
		this.socialFinish = socialFinish; //在实验、实习、工程实践和社会调查等社会实践中完成数
		this.rate = rate; //比例（%）
		this.fullTeacher = fullTeacher; //专任教师
		this.partTeacher = partTeacher;  //外聘教师
		this.stuPerTeacher = stuPerTeacher;  //每名教师平均指导毕业生数
		this.college = college;
		this.rowTitle = rowTitle;
	}

	@Override
	public int compareTo(GraduateCombinedTrain arg0) {
		// TODO Auto-generated method stub
		return this.getStuPerTeacher().compareTo(arg0.getStuPerTeacher());
	}

}
