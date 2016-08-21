package cn.edu.xmu.entity;

/**
 * 
 * @author zsj
 * 2.7 专业带头人情况
 */
public class MajorHeaderInfo {

	private String rowName;//行名称
	private String total;
	/*职称*/
	private String zhenggaoji;//正高级
	private String fugaoji;//副高级
	private String otherTitle;//其他职称
	
	/*最高学位*/
	private String doctor;//博士
	private String master;//硕士
	private String otherDegree;//其他
	
	/*年龄*/
	private String below35;//35以下
	private String between36_45;
	private String between46_55;
	private String upon56;
	
	/*学缘*/
	private String selfSchool;//本校
	private String inside;//境内
	private String outside;//境外
	
	private String college;

	public String getRowName() {
		return rowName;
	}

	public void setRowName(String rowName) {
		this.rowName = rowName;
	}

	public String getTotal() {
		return total;
	}

	public void setTotal(String total) {
		this.total = total;
	}

	public String getZhenggaoji() {
		return zhenggaoji;
	}

	public void setZhenggaoji(String zhenggaoji) {
		this.zhenggaoji = zhenggaoji;
	}

	public String getFugaoji() {
		return fugaoji;
	}

	public void setFugaoji(String fugaoji) {
		this.fugaoji = fugaoji;
	}

	public String getOtherTitle() {
		return otherTitle;
	}

	public void setOtherTitle(String otherTitle) {
		this.otherTitle = otherTitle;
	}

	public String getDoctor() {
		return doctor;
	}

	public void setDoctor(String doctor) {
		this.doctor = doctor;
	}

	public String getMaster() {
		return master;
	}

	public void setMaster(String master) {
		this.master = master;
	}

	public String getOtherDegree() {
		return otherDegree;
	}

	public void setOtherDegree(String otherDegree) {
		this.otherDegree = otherDegree;
	}

	public String getBelow35() {
		return below35;
	}

	public void setBelow35(String below35) {
		this.below35 = below35;
	}

	public String getBetween36_45() {
		return between36_45;
	}

	public void setBetween36_45(String between36_45) {
		this.between36_45 = between36_45;
	}

	public String getBetween46_55() {
		return between46_55;
	}

	public void setBetween46_55(String between46_55) {
		this.between46_55 = between46_55;
	}

	public String getUpon56() {
		return upon56;
	}

	public void setUpon56(String upon56) {
		this.upon56 = upon56;
	}

	public String getSelfSchool() {
		return selfSchool;
	}

	public void setSelfSchool(String selfSchool) {
		this.selfSchool = selfSchool;
	}

	public String getInside() {
		return inside;
	}

	public void setInside(String inside) {
		this.inside = inside;
	}

	public String getOutside() {
		return outside;
	}

	public void setOutside(String outside) {
		this.outside = outside;
	}

	public String getCollege() {
		return college;
	}

	public void setCollege(String college) {
		this.college = college;
	}

	public MajorHeaderInfo(String rowName, String total, String zhenggaoji,
			String fugaoji, String otherTitle, String doctor, String master,
			String otherDegree, String below35, String between36_45,
			String between46_55, String upon56, String selfSchool,
			String inside, String outside, String college) {
		super();
		this.rowName = rowName;
		this.total = total;
		this.zhenggaoji = zhenggaoji;
		this.fugaoji = fugaoji;
		this.otherTitle = otherTitle;
		this.doctor = doctor;
		this.master = master;
		this.otherDegree = otherDegree;
		this.below35 = below35;
		this.between36_45 = between36_45;
		this.between46_55 = between46_55;
		this.upon56 = upon56;
		this.selfSchool = selfSchool;
		this.inside = inside;
		this.outside = outside;
		this.college = college;
	}

}
