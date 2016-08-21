package cn.edu.xmu.entity;
/**
 * @author zshbleaker
 */


public class TeachUnitSubjectOverview {

	private int number;
	private String unit;					//单位
	private int bachelorSubjectNumber;		//本科专业数
	private int postPhdFlowSpotNumber;		//博士后流动站数
	private int phdAuthFir;					//博士学位 授权一级学科点
	private int phdAuthSec;					//博士学位二级学科点
	private int masterAuthFir;				//硕士
	private int masterAuthSec;
	private int importantSubject;			//重点学科数
	
	private String college;

	
	public void getDataFromArray(String[] a){
		this.setNumber(Integer.parseInt(a[0]));
		this.setUnit(a[1]);
		this.setBachelorSubjectNumber(Integer.parseInt(a[2]));
		this.setPostPhdFlowSpotNumber(Integer.parseInt(a[3]));
		this.setPhdAuthFir(Integer.parseInt(a[4]));
		this.setPhdAuthSec(Integer.parseInt(a[5]));
		this.setMasterAuthFir(Integer.parseInt(a[6]));
		this.setMasterAuthSec(Integer.parseInt(a[7]));
		this.setImportantSubject(Integer.parseInt(a[8]));
		this.setCollege("");
	}
	
	public TeachUnitSubjectOverview() {
		super();
	}

	public int getNumber() {
		return number;
	}

	public void setNumber(int number) {
		this.number = number;
	}

	public String getUnit() {
		return unit;
	}

	public void setUnit(String unit) {
		this.unit = unit;
	}

	public int getBachelorSubjectNumber() {
		return bachelorSubjectNumber;
	}

	public void setBachelorSubjectNumber(int bachelorSubjectNumber) {
		this.bachelorSubjectNumber = bachelorSubjectNumber;
	}

	public int getPostPhdFlowSpotNumber() {
		return postPhdFlowSpotNumber;
	}

	public void setPostPhdFlowSpotNumber(int postPhdFlowSpotNumber) {
		this.postPhdFlowSpotNumber = postPhdFlowSpotNumber;
	}

	public int getPhdAuthFir() {
		return phdAuthFir;
	}

	public void setPhdAuthFir(int phdAuthFir) {
		this.phdAuthFir = phdAuthFir;
	}

	public int getPhdAuthSec() {
		return phdAuthSec;
	}

	public void setPhdAuthSec(int phdAuthSec) {
		this.phdAuthSec = phdAuthSec;
	}

	public int getMasterAuthFir() {
		return masterAuthFir;
	}

	public void setMasterAuthFir(int masterAuthFir) {
		this.masterAuthFir = masterAuthFir;
	}

	public int getMasterAuthSec() {
		return masterAuthSec;
	}

	public void setMasterAuthSec(int masterAuthSec) {
		this.masterAuthSec = masterAuthSec;
	}

	public int getImportantSubject() {
		return importantSubject;
	}

	public void setImportantSubject(int importantSubject) {
		this.importantSubject = importantSubject;
	}

	public String getCollege() {
		return college;
	}

	public void setCollege(String college) {
		this.college = college;
	}
	
	
	
}
