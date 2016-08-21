package cn.edu.xmu.entity;
/**
 * @author zhantu
 * 附表5 各教学单位实验系列职称人员结构
 */
public class UnitPersonnelStructure {
	//序号
	private int serialnumber;
	//单位
	private String departmentname;
	//总计
	private int sum;
	//职称	正高级	副高级	中级		初级		无职称
	private int titlePositiveSenior;
	private int titleViceSenior;
	private int titleIntermediate;
	private int titlePrimary;
	private int titlenone;
	//学位	博士		硕士		学位		无学位
	private int doctor;
	private int master;
	private int degree;
	private int nonedegree;
	//年龄	35岁及以下	36-45	46-55	56岁及以上
	private int under35;
	private int between36and45;
	private int between46and55;
	private int over55;
	public int getSerialnumber() {
		return serialnumber;
	}
	public void setSerialnumber(int serialnumber) {
		this.serialnumber = serialnumber;
	}
	
	public String getDepartmentname() {
		return departmentname;
	}
	public void setDepartmentname(String departmentname) {
		this.departmentname = departmentname;
	}
	public int getSum() {
		return sum;
	}
	public void setSum(int sum) {
		this.sum = sum;
	}
	public int getTitlePositiveSenior() {
		return titlePositiveSenior;
	}
	public void setTitlePositiveSenior(int titlePositiveSenior) {
		this.titlePositiveSenior = titlePositiveSenior;
	}
	public int getTitleViceSenior() {
		return titleViceSenior;
	}
	public void setTitleViceSenior(int titleViceSenior) {
		this.titleViceSenior = titleViceSenior;
	}
	public int getTitleIntermediate() {
		return titleIntermediate;
	}
	public void setTitleIntermediate(int titleIntermediate) {
		this.titleIntermediate = titleIntermediate;
	}
	public int getTitlePrimary() {
		return titlePrimary;
	}
	public void setTitlePrimary(int titlePrimary) {
		this.titlePrimary = titlePrimary;
	}
	public int getTitlenone() {
		return titlenone;
	}
	public void setTitlenone(int titlenone) {
		this.titlenone = titlenone;
	}
	public int getDoctor() {
		return doctor;
	}
	public void setDoctor(int doctor) {
		this.doctor = doctor;
	}
	public int getMaster() {
		return master;
	}
	public void setMaster(int master) {
		this.master = master;
	}
	public int getDegree() {
		return degree;
	}
	public void setDegree(int degree) {
		this.degree = degree;
	}
	public int getNonedegree() {
		return nonedegree;
	}
	public void setNonedegree(int nonedegree) {
		this.nonedegree = nonedegree;
	}
	public int getUnder35() {
		return under35;
	}
	public void setUnder35(int under35) {
		this.under35 = under35;
	}
	public int getBetween36and45() {
		return between36and45;
	}
	public void setBetween36and45(int between36and45) {
		this.between36and45 = between36and45;
	}
	public int getBetween46and55() {
		return between46and55;
	}
	public void setBetween46and55(int between46and55) {
		this.between46and55 = between46and55;
	}
	public int getOver55() {
		return over55;
	}
	public void setOver55(int over55) {
		this.over55 = over55;
	}
	public UnitPersonnelStructure() {
		super();
		this.departmentname = "";
		this.sum = 0;
		this.titlePositiveSenior = 0;
		this.titleViceSenior = 0;
		this.titleIntermediate = 0;
		this.titlePrimary = 0;
		this.titlenone = 0;
		this.doctor = 0;
		this.master = 0;
		this.degree = 0;
		this.nonedegree = 0;
		this.under35 = 0;
		this.between36and45 = 0;
		this.between46and55 = 0;
		this.over55 = 0;
	}
	public UnitPersonnelStructure(int serialnumber, String departmentname,
			int sum, int titlePositiveSenior, int titleViceSenior,
			int titleIntermediate, int titlePrimary, int titlenone, int doctor,
			int master, int degree, int nonedegree, int under35,
			int between36and45, int between46and55, int over55) {
		super();
		this.serialnumber = serialnumber;
		this.departmentname = departmentname;
		this.sum = sum;
		this.titlePositiveSenior = titlePositiveSenior;
		this.titleViceSenior = titleViceSenior;
		this.titleIntermediate = titleIntermediate;
		this.titlePrimary = titlePrimary;
		this.titlenone = titlenone;
		this.doctor = doctor;
		this.master = master;
		this.degree = degree;
		this.nonedegree = nonedegree;
		this.under35 = under35;
		this.between36and45 = between36and45;
		this.between46and55 = between46and55;
		this.over55 = over55;
	}
	
	
}
