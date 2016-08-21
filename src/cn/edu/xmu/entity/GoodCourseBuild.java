package cn.edu.xmu.entity;
/**
 * 
 * @author zshbleaker
 * 3.9 精品课程建设
 */

public class GoodCourseBuild {
	private String rowTitle;
	private int nation;
	private int province;
	private String college;
	
	public void getDataFromArray(int[] a){
		this.nation = a[0];
		this.province = a[1];
		
	}
	public GoodCourseBuild() {
		super();
	}
	public String getRowTitle() {
		return rowTitle;
	}
	public void setRowTitle(String rowTitle) {
		this.rowTitle = rowTitle;
	}
	public int getNation() {
		return nation;
	}
	public void setNation(int nation) {
		this.nation = nation;
	}
	public int getProvince() {
		return province;
	}
	public void setProvince(int province) {
		this.province = province;
	}
	public String getCollege() {
		return college;
	}
	public void setCollege(String college) {
		this.college = college;
	}
	
	
}
