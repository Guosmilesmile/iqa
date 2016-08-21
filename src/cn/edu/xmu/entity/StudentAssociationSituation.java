package cn.edu.xmu.entity;

public class StudentAssociationSituation {
	private String rowTitle;//社团类别
	
	private int science;//科技类社团
	
	private int literatureart;//文艺类社团
	
	private int humanistic;//人文社会类社团
	
	private int sports;//体育类社团
	
	private int total;//总数
	
	private String college;

	
	public StudentAssociationSituation() {
		super();
	}

	public StudentAssociationSituation(String rowTitle, int science,
			int literatureart, int humanistic, int sports, int total,
			String college) {
		super();
		this.rowTitle = rowTitle;
		this.science = science;
		this.literatureart = literatureart;
		this.humanistic = humanistic;
		this.sports = sports;
		this.total = total;
		this.college = college;
	}

	public String getRowTitle() {
		return rowTitle;
	}

	public void setRowTitle(String rowTitle) {
		this.rowTitle = rowTitle;
	}

	public int getScience() {
		return science;
	}

	public void setScience(int science) {
		this.science = science;
	}

	public int getLiteratureart() {
		return literatureart;
	}

	public void setLiteratureart(int literatureart) {
		this.literatureart = literatureart;
	}

	public int getHumanistic() {
		return humanistic;
	}

	public void setHumanistic(int humanistic) {
		this.humanistic = humanistic;
	}

	public int getSports() {
		return sports;
	}

	public void setSports(int sports) {
		this.sports = sports;
	}

	public int getTotal() {
		return total;
	}

	public void setTotal(int total) {
		this.total = total;
	}

	public String getCollege() {
		return college;
	}

	public void setCollege(String college) {
		this.college = college;
	}
	
	
	
	

}
