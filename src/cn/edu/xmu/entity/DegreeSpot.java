package cn.edu.xmu.entity;

import cn.edu.xmu.table.MajorInfoTable;

/**
 * 
 * @author zsj
 * 学位点概况
 *
 */
public class DegreeSpot {
	
	private final String rowTitle = "合计";
	
	private int mobileStationForPostDoctor;//博士后流动站
	private int doctorFirstLevelDiscipline;//博士学位授权一级学科点
	private int doctorSecondLevelDiscipline;//博士学位授权二级学科点（不含一级覆盖点）
	private int masterFirstLevelDiscipline;//硕士学位授权一级学科点
	private int masterSecondLevelDiscipline;//硕士学位授权二级学科点（不含一级覆盖点）
	private int bachelorDegreeTotal;//本科专业总数
	private int bachelorDegreeNew;//本科专业中的新专业
	private int keyDiscipline;//重点学科数
	
	private String college;//填报学院
	
	public DegreeSpot(int mobileStationForPostDoctor,
			int doctorFirstLevelDiscipline, int doctorSecondLevelDiscipline,
			int masterFirstLevelDiscipline, int masterSecondLevelDiscipline,
			int bachelorDegreeTotal, int bachelorDegreeNew, int keyDiscipline,
			String college) {
		super();
		this.mobileStationForPostDoctor = mobileStationForPostDoctor;
		this.doctorFirstLevelDiscipline = doctorFirstLevelDiscipline;
		this.doctorSecondLevelDiscipline = doctorSecondLevelDiscipline;
		this.masterFirstLevelDiscipline = masterFirstLevelDiscipline;
		this.masterSecondLevelDiscipline = masterSecondLevelDiscipline;
		this.bachelorDegreeTotal = bachelorDegreeTotal;
		this.bachelorDegreeNew = bachelorDegreeNew;
		this.keyDiscipline = keyDiscipline;
		this.college = college;
	}

	public int getMobileStationForPostDoctor() {
		return mobileStationForPostDoctor;
	}

	public void setMobileStationForPostDoctor(int mobileStationForPostDoctor) {
		this.mobileStationForPostDoctor = mobileStationForPostDoctor;
	}

	public int getDoctorFirstLevelDiscipline() {
		return doctorFirstLevelDiscipline;
	}

	public void setDoctorFirstLevelDiscipline(int doctorFirstLevelDiscipline) {
		this.doctorFirstLevelDiscipline = doctorFirstLevelDiscipline;
	}

	public int getDoctorSecondLevelDiscipline() {
		return doctorSecondLevelDiscipline;
	}

	public void setDoctorSecondLevelDiscipline(int doctorSecondLevelDiscipline) {
		this.doctorSecondLevelDiscipline = doctorSecondLevelDiscipline;
	}

	public int getMasterFirstLevelDiscipline() {
		return masterFirstLevelDiscipline;
	}

	public void setMasterFirstLevelDiscipline(int masterFirstLevelDiscipline) {
		this.masterFirstLevelDiscipline = masterFirstLevelDiscipline;
	}

	public int getMasterSecondLevelDiscipline() {
		return masterSecondLevelDiscipline;
	}

	public void setMasterSecondLevelDiscipline(int masterSecondLevelDiscipline) {
		this.masterSecondLevelDiscipline = masterSecondLevelDiscipline;
	}

	public int getBachelorDegreeTotal() {
		return bachelorDegreeTotal;
	}

	public void setBachelorDegreeTotal(int bachelorDegreeTotal) {
		this.bachelorDegreeTotal = bachelorDegreeTotal;
	}

	public int getBachelorDegreeNew() {
		return bachelorDegreeNew;
	}

	public void setBachelorDegreeNew(int bachelorDegreeNew) {
		this.bachelorDegreeNew = bachelorDegreeNew;
	}

	public int getKeyDiscipline() {
		return keyDiscipline;
	}

	public void setKeyDiscipline(int keyDiscipline) {
		this.keyDiscipline = keyDiscipline;
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
	
	

}
