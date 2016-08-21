package cn.edu.xmu.entity;
/**
 * 附表4各教学单位专业带头人情况
 * @author zhantu
 *
 */
public class TeachingUnitsLeader {
	//序号
	private int serialnumber;
	//单位
	private String departmentname;
	//院系专业数
	private int majorNumber;
	//专业带头人总人数
	private int leaderNumber;
	//高级职称 人数 比例（%）
	private int highTitleNulmer;
	private double highTitleProportion;
	//获得博士学位 人数 比例（%）
	private int doctorNumber;
	private double doctorProportion;
	//学缘为外校 人数 比例（%）
	private int sourceOutsideNumber;
	private double sourceOutsideProportion;
	public int getSerialnumber() {
		return serialnumber;
	}
	public void setSerialnumber(int serialnumber) {
		this.serialnumber = serialnumber;
	}
	public String getDepartmentname() {
		return departmentname;
	}
	public void setDepartmentname(String departmentname) {
		this.departmentname = departmentname;
	}
	public int getMajorNumber() {
		return majorNumber;
	}
	public void setMajorNumber(int majorNumber) {
		this.majorNumber = majorNumber;
	}
	public int getLeaderNumber() {
		return leaderNumber;
	}
	public void setLeaderNumber(int leaderNumber) {
		this.leaderNumber = leaderNumber;
	}
	public int getHighTitleNulmer() {
		return highTitleNulmer;
	}
	public void setHighTitleNulmer(int highTitleNulmer) {
		this.highTitleNulmer = highTitleNulmer;
	}
	public double getHighTitleProportion() {
		return highTitleProportion;
	}
	public void setHighTitleProportion(double highTitleProportion) {
		this.highTitleProportion = highTitleProportion;
	}
	public int getDoctorNumber() {
		return doctorNumber;
	}
	public void setDoctorNumber(int doctorNumber) {
		this.doctorNumber = doctorNumber;
	}
	public double getDoctorProportion() {
		return doctorProportion;
	}
	public void setDoctorProportion(double doctorProportion) {
		this.doctorProportion = doctorProportion;
	}
	public int getSourceOutsideNumber() {
		return sourceOutsideNumber;
	}
	public void setSourceOutsideNumber(int sourceOutsideNumber) {
		this.sourceOutsideNumber = sourceOutsideNumber;
	}
	public double getSourceOutsideProportion() {
		return sourceOutsideProportion;
	}
	public void setSourceOutsideProportion(double sourceOutsideProportion) {
		this.sourceOutsideProportion = sourceOutsideProportion;
	}
	public TeachingUnitsLeader(int serialnumber, String departmentname,
			int majorNumber, int leaderNumber, int highTitleNulmer,
			double highTitleProportion, int doctorNumber,
			double doctorProportion, int sourceOutsideNumber,
			double sourceOutsideProportion) {
		super();
		this.serialnumber = serialnumber;
		this.departmentname = departmentname;
		this.majorNumber = majorNumber;
		this.leaderNumber = leaderNumber;
		this.highTitleNulmer = highTitleNulmer;
		this.highTitleProportion = highTitleProportion;
		this.doctorNumber = doctorNumber;
		this.doctorProportion = doctorProportion;
		this.sourceOutsideNumber = sourceOutsideNumber;
		this.sourceOutsideProportion = sourceOutsideProportion;
	}
	public TeachingUnitsLeader() {
		super();
		this.serialnumber = 0;
		this.departmentname = "";
		this.majorNumber = 0;
		this.leaderNumber = 0;
		this.highTitleNulmer = 0;
		this.highTitleProportion = 0;
		this.doctorNumber = 0;
		this.doctorProportion = 0;
		this.sourceOutsideNumber = 0;
		this.sourceOutsideProportion = 0;
	}
	
	
}
