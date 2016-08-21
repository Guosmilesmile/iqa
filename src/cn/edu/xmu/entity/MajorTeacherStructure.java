package cn.edu.xmu.entity;

public class MajorTeacherStructure implements Comparable<MajorTeacherStructure>{
	//序号
	private int serialNumber;
	//专业
	private String major;
	//授课教师数
	private int teacherNumber;
	//教授	
	//数量	比例(%)
	private int professorNumber;
	private double professorProportion;
	//其中为低年级授课教授
	//数量	比例(%)
	private int lowProfessorNumber;
	private double lowProfessorProportion;
	//副教授、教授
	//数量	比例(%)
	private int posiViceProfessorNumber;
	private Double posiViceProfessorProportion;
	//具有硕士、博士学位
	//数量	比例(%)
	private int masterDoctorNumber;
	private double masterDoctorProportion;
	private String group;
	
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
	public int getTeacherNumber() {
		return teacherNumber;
	}
	public void setTeacherNumber(int teacherNumber) {
		this.teacherNumber = teacherNumber;
	}
	public int getProfessorNumber() {
		return professorNumber;
	}
	public void setProfessorNumber(int professorNumber) {
		this.professorNumber = professorNumber;
	}
	public double getProfessorProportion() {
		return professorProportion;
	}
	public void setProfessorProportion(double professorProportion) {
		this.professorProportion = professorProportion;
	}
	public int getLowProfessorNumber() {
		return lowProfessorNumber;
	}
	public void setLowProfessorNumber(int lowProfessorNumber) {
		this.lowProfessorNumber = lowProfessorNumber;
	}
	public double getLowProfessorProportion() {
		return lowProfessorProportion;
	}
	public void setLowProfessorProportion(double lowProfessorProportion) {
		this.lowProfessorProportion = lowProfessorProportion;
	}
	public int getPosiViceProfessorNumber() {
		return posiViceProfessorNumber;
	}
	public void setPosiViceProfessorNumber(int posiViceProfessorNumber) {
		this.posiViceProfessorNumber = posiViceProfessorNumber;
	}
	public Double getPosiViceProfessorProportion() {
		return posiViceProfessorProportion;
	}
	public void setPosiViceProfessorProportion(Double posiViceProfessorProportion) {
		this.posiViceProfessorProportion = posiViceProfessorProportion;
	}
	public String getGroup() {
		return group;
	}
	public void setGroup(String group) {
		this.group = group;
	}
	public int getMasterDoctorNumber() {
		return masterDoctorNumber;
	}
	public void setMasterDoctorNumber(int masterDoctorNumber) {
		this.masterDoctorNumber = masterDoctorNumber;
	}
	public double getMasterDoctorProportion() {
		return masterDoctorProportion;
	}
	public void setMasterDoctorProportion(double masterDoctorProportion) {
		this.masterDoctorProportion = masterDoctorProportion;
	}
	public MajorTeacherStructure(int serialNumber, String major,
			int teacherNumber, int professorNumber, double professorProportion,
			int lowProfessorNumber, double lowProfessorProportion,
			int posiViceProfessorNumber, double posiViceProfessorProportion,
			int masterDoctorNumber, double masterDoctorProportion, String group) {
		super();
		this.serialNumber = serialNumber;
		this.major = major;
		this.teacherNumber = teacherNumber;
		this.professorNumber = professorNumber;
		this.professorProportion = professorProportion;
		this.lowProfessorNumber = lowProfessorNumber;
		this.lowProfessorProportion = lowProfessorProportion;
		this.posiViceProfessorNumber = posiViceProfessorNumber;
		this.posiViceProfessorProportion = posiViceProfessorProportion;
		this.masterDoctorNumber = masterDoctorNumber;
		this.masterDoctorProportion = masterDoctorProportion;
		this.group = group;
	}
	public MajorTeacherStructure() {
		super();
		this.serialNumber = 0;
		this.major = "";
		this.teacherNumber = 0;
		this.professorNumber = 0;
		this.professorProportion = 0;
		this.lowProfessorNumber = 0;
		this.lowProfessorProportion = 0;
		this.posiViceProfessorNumber = 0;
		this.posiViceProfessorProportion = (double) 0;
		this.masterDoctorNumber = 0;
		this.masterDoctorProportion = 0;
		this.group = "";
	}
	@Override
	public int compareTo(MajorTeacherStructure arg0) {
		return this.posiViceProfessorProportion.compareTo(arg0.getPosiViceProfessorProportion()); 
	}
	
}
