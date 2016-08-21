package cn.edu.xmu.entity;
/**
 * 
 * @author zshbleaker
 * 3.10 合作办学情况
 */

public class Partnership {
	private int total;
	private int academic;
	private int enterprise;
	private int gov;
	private String rowTitle;
	
	private String college;
	
	public void getDataFromArray(int[] a){
		this.total = a[0];
		this.academic = a[1];
		this.enterprise = a[2];
		this.gov = a[3];
	}

	
	
	public String getRowTitle() {
		return rowTitle;
	}



	public void setRowTitle(String rowTitle) {
		this.rowTitle = rowTitle;
	}



	public Partnership() {
		super();
	}

	public int getTotal() {
		return total;
	}

	public void setTotal(int total) {
		this.total = total;
	}

	public int getAcademic() {
		return academic;
	}

	public void setAcademic(int academic) {
		this.academic = academic;
	}

	public int getEnterprise() {
		return enterprise;
	}

	public void setEnterprise(int enterprise) {
		this.enterprise = enterprise;
	}

	public int getGov() {
		return gov;
	}

	public void setGov(int gov) {
		this.gov = gov;
	}

	public String getCollege() {
		return college;
	}

	public void setCollege(String college) {
		this.college = college;
	}
	
	
}
