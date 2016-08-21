package cn.edu.xmu.entity;

public class CourseStatus {
	private String rowTitle;
	private double number;
	private double times;
	private double duolinguae;
	private double avgtime;
	private double avgpeople;
	
	private String college;
	
	public void getDataFromArray(double[] a){
		this.number = a[0];
		this.times = a[1];
		this.duolinguae = a[2];
		this.avgtime = a[3];
		this.avgpeople = a[4];
	}
	
	public CourseStatus() {
		super();
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
	public double getNumber() {
		return number;
	}
	public void setNumber(double number) {
		this.number = number;
	}
	public double getTimes() {
		return times;
	}
	public void setTimes(double times) {
		this.times = times;
	}
	public double getDuolinguae() {
		return duolinguae;
	}
	public void setDuolinguae(double duolinguae) {
		this.duolinguae = duolinguae;
	}
	public double getAvgtime() {
		return avgtime;
	}
	public void setAvgtime(double avgtime) {
		this.avgtime = avgtime;
	}
	public double getAvgpeople() {
		return avgpeople;
	}
	public void setAvgpeople(double avgpeople) {
		this.avgpeople = avgpeople;
	}
	
	
}
