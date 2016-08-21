package cn.edu.xmu.entity;

public class LeaderAgeDegree {

	private double total;
	private double phd;
	private double master;
	private double bachelor;
	private double sb;
	private double young;
	private double midyoung;
	private double midold;
	private double old;
	private String rowTitle;
	private String college;
	
	public void getDataFromArray(double[] a){
		this.setTotal(a[0] + a[1] + a[2] + a[3]);
		this.setPhd(a[0]);
		this.setMaster(a[1]);
		this.setBachelor(a[2]);
		this.setSb(a[3]);
		this.setOld(a[4]);
		this.setMidold(a[5]);
		this.setMidyoung(a[6]);
		this.setYoung(a[7]);
		this.setCollege("");
	}
	
	
	
	public String getRowTitle() {
		return rowTitle;
	}



	public void setRowTitle(String rowTitle) {
		this.rowTitle = rowTitle;
	}



	public double getTotal() {
		return total;
	}
	public void setTotal(double total) {
		this.total = total;
	}
	public double getPhd() {
		return phd;
	}
	public void setPhd(double phd) {
		this.phd = phd;
	}
	public double getMaster() {
		return master;
	}
	public void setMaster(double master) {
		this.master = master;
	}
	public double getBachelor() {
		return bachelor;
	}
	public void setBachelor(double bachelor) {
		this.bachelor = bachelor;
	}
	public double getSb() {
		return sb;
	}
	public void setSb(double sb) {
		this.sb = sb;
	}
	public double getYoung() {
		return young;
	}
	public void setYoung(double young) {
		this.young = young;
	}
	public double getMidyoung() {
		return midyoung;
	}
	public void setMidyoung(double midyoung) {
		this.midyoung = midyoung;
	}
	public double getMidold() {
		return midold;
	}
	public void setMidold(double midold) {
		this.midold = midold;
	}
	public double getOld() {
		return old;
	}
	public void setOld(double old) {
		this.old = old;
	}
	public String getCollege() {
		return college;
	}
	public void setCollege(String college) {
		this.college = college;
	}
	public LeaderAgeDegree() {
		super();
	}
	
	
}
