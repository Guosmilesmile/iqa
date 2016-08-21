package cn.edu.xmu.entity;

/**
 * 
 * @author zsj
 * 6.2 教学质量管理队伍结构
 */
public class TeachingQualityManagerStructure {

	private String rowTitle;//行名称
	
	private String total;
	/*职称*/
	private String zhenggaoji;//正高级
	private String fugaoji;//副高级
	private String zhongji;//中级
	private String chuji;//初级
	private String noTitle;//无职称
	
	/*最高学位*/
	private String doctor;//博士
	private String master;//硕士
	private String bachelor;//学士
	private String noDegree;//无学位
	
	/*年龄*/
	private String below35;//35以下
	private String between36_45;
	private String between46_55;
	private String upon56;
	
	private String college;
	
	
	
	public String getTotal() {
		return total;
	}
	public void setTotal(String total) {
		this.total = total;
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
	public String getZhongji() {
		return zhongji;
	}
	public void setZhongji(String zhongji) {
		this.zhongji = zhongji;
	}
	public String getChuji() {
		return chuji;
	}
	public void setChuji(String chuji) {
		this.chuji = chuji;
	}
	public String getNoTitle() {
		return noTitle;
	}
	public void setNoTitle(String noTitle) {
		this.noTitle = noTitle;
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
	public String getBachelor() {
		return bachelor;
	}
	public void setBachelor(String bachelor) {
		this.bachelor = bachelor;
	}
	public String getNoDegree() {
		return noDegree;
	}
	public void setNoDegree(String noDegree) {
		this.noDegree = noDegree;
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
	public TeachingQualityManagerStructure(String rowTitle, String total,
			String zhenggaoji, String fugaoji, String zhongji, String chuji,
			String noTitle, String doctor, String master, String bachelor,
			String noDegree, String below35, String between36_45,
			String between46_55, String upon56, String college) {
		super();
		this.rowTitle = rowTitle;
		this.total = total;
		this.zhenggaoji = zhenggaoji;
		this.fugaoji = fugaoji;
		this.zhongji = zhongji;
		this.chuji = chuji;
		this.noTitle = noTitle;
		this.doctor = doctor;
		this.master = master;
		this.bachelor = bachelor;
		this.noDegree = noDegree;
		this.below35 = below35;
		this.between36_45 = between36_45;
		this.between46_55 = between46_55;
		this.upon56 = upon56;
		this.college = college;
	}


}
