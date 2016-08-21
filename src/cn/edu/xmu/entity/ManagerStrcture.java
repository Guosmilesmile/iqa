package cn.edu.xmu.entity;

public class ManagerStrcture {
	private double total;
	private double high;
	private double midhigh;
	private double mid;
	private double low;
	private double common;
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
	
	public void getDataFromArray(double a[]){
		this.setTotal(a[0] + a[1] + a[2] + a[3] + a[4]);
		this.setHigh(a[0]);
		this.setMidhigh(a[1]);
		this.setMid(a[2]);
		this.setLow(a[3]);
		this.setCommon(a[4]);
		this.setPhd(a[5]);
		this.setMaster(a[6]);
		this.setBachelor(a[7]);
		this.setSb(a[8]);
		this.setOld(a[9]);
		this.setMidold(a[10]);
		this.setMidyoung(a[11]);
		this.setYoung(a[12]);
		this.setCollege("");
	}
	
	
	
	public String getRowTitle() {
		return rowTitle;
	}



	public void setRowTitle(String rowTitle) {
		this.rowTitle = rowTitle;
	}



	public String getCollege() {
		return college;
	}



	public void setCollege(String college) {
		this.college = college;
	}



	public ManagerStrcture() {
		super();
	}
	public double getTotal() {
		return total;
	}
	public void setTotal(double total) {
		this.total = total;
	}
	public double getHigh() {
		return high;
	}
	public void setHigh(double high) {
		this.high = high;
	}
	public double getMidhigh() {
		return midhigh;
	}
	public void setMidhigh(double midhigh) {
		this.midhigh = midhigh;
	}
	public double getMid() {
		return mid;
	}
	public void setMid(double mid) {
		this.mid = mid;
	}
	public double getLow() {
		return low;
	}
	public void setLow(double low) {
		this.low = low;
	}
	public double getCommon() {
		return common;
	}
	public void setCommon(double common) {
		this.common = common;
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
	
	
	
}
