package cn.edu.xmu.entity;
/**
 * 
 * @author zshbleaker
 * 3.2.2 教学科研仪器设备情况
 */

public class TeachingResearchTools {
	private String rowTitle;
	private int totalWealth;
	private double avg;
	private int newThisYear;
	private double newThisYearRate;
	
	private String college;

	public void getDataFromArray(double[] a){
		this.totalWealth =  (int)a[0];
		this.avg = a[1];
		this.newThisYear = (int)a[2];
		this.newThisYearRate =  (int)a[3];
	}
	
	public String getRowTitle() {
		return rowTitle;
	}

	public void setRowTitle(String rowTitle) {
		this.rowTitle = rowTitle;
	}

	public int getTotalWealth() {
		return totalWealth;
	}

	public void setTotalWealth(int totalWealth) {
		this.totalWealth = totalWealth;
	}

	public double getAvg() {
		return avg;
	}

	public void setAvg(double avg) {
		this.avg = avg;
	}

	public int getNewThisYear() {
		return newThisYear;
	}

	public void setNewThisYear(int newThisYear) {
		this.newThisYear = newThisYear;
	}

	public double getNewThisYearRate() {
		return newThisYearRate;
	}

	public void setNewThisYearRate(double newThisYearRate) {
		this.newThisYearRate = newThisYearRate;
	}

	public String getCollege() {
		return college;
	}

	public void setCollege(String college) {
		this.college = college;
	}
	
	
}
