package cn.edu.xmu.entity;

/**
 * 5.5 本科生奖贷补情况
 * @author yue
 *
 */
public class UndergraduateAwardLoanInfo {
	
	//资助金额（万元）
	//政府奖、助学金
	private float govcost;
	//社会奖、助学金
	private float societycost;
	//学校奖学金
	private float schoolcost;
	//国家助学贷款
	private float countrycost;
	//勤工助学金
	private float workstudycost;
	//减免学费
	private float deratecost;
	//临时困难补助
	private float troubleaidcost;
	
	//资助学生数（人次）
	//政府奖、助学金
	private int govcount;
	//社会奖、助学金
	private int societycount;
	//学校奖学金
	private int schoolcount;
	//国家助学贷款
	private int countrycount;
	//勤工助学金
	private int workstudycount;
	//减免学费
	private int deratecount;
	//临时困难补助
	private int troubleaidcount;
	private String college;
	public UndergraduateAwardLoanInfo(float govcost, float societycost, float schoolcost, float countrycost,
			float workstudycost, float deratecost, float troubleaidcost, int govcount, int societycount,
			int schoolcount, int countrycount, int workstudycount, int deratecount, int troubleaidcount,
			String college) {
		super();
		this.govcost = govcost;
		this.societycost = societycost;
		this.schoolcost = schoolcost;
		this.countrycost = countrycost;
		this.workstudycost = workstudycost;
		this.deratecost = deratecost;
		this.troubleaidcost = troubleaidcost;
		this.govcount = govcount;
		this.societycount = societycount;
		this.schoolcount = schoolcount;
		this.countrycount = countrycount;
		this.workstudycount = workstudycount;
		this.deratecount = deratecount;
		this.troubleaidcount = troubleaidcount;
		this.college = college;
	}
	public float getGovcost() {
		return govcost;
	}
	public void setGovcost(float govcost) {
		this.govcost = govcost;
	}
	public float getSocietycost() {
		return societycost;
	}
	public void setSocietycost(float societycost) {
		this.societycost = societycost;
	}
	public float getSchoolcost() {
		return schoolcost;
	}
	public void setSchoolcost(float schoolcost) {
		this.schoolcost = schoolcost;
	}
	public float getCountrycost() {
		return countrycost;
	}
	public void setCountrycost(float countrycost) {
		this.countrycost = countrycost;
	}
	public float getWorkstudycost() {
		return workstudycost;
	}
	public void setWorkstudycost(float workstudycost) {
		this.workstudycost = workstudycost;
	}
	public float getDeratecost() {
		return deratecost;
	}
	public void setDeratecost(float deratecost) {
		this.deratecost = deratecost;
	}
	public float getTroubleaidcost() {
		return troubleaidcost;
	}
	public void setTroubleaidcost(float troubleaidcost) {
		this.troubleaidcost = troubleaidcost;
	}
	public int getGovcount() {
		return govcount;
	}
	public void setGovcount(int govcount) {
		this.govcount = govcount;
	}
	public int getSocietycount() {
		return societycount;
	}
	public void setSocietycount(int societycount) {
		this.societycount = societycount;
	}
	public int getSchoolcount() {
		return schoolcount;
	}
	public void setSchoolcount(int schoolcount) {
		this.schoolcount = schoolcount;
	}
	public int getCountrycount() {
		return countrycount;
	}
	public void setCountrycount(int countrycount) {
		this.countrycount = countrycount;
	}
	public int getWorkstudycount() {
		return workstudycount;
	}
	public void setWorkstudycount(int workstudycount) {
		this.workstudycount = workstudycount;
	}
	public int getDeratecount() {
		return deratecount;
	}
	public void setDeratecount(int deratecount) {
		this.deratecount = deratecount;
	}
	public int getTroubleaidcount() {
		return troubleaidcount;
	}
	public void setTroubleaidcount(int troubleaidcount) {
		this.troubleaidcount = troubleaidcount;
	}
	public String getCollege() {
		return college;
	}
	public void setCollege(String college) {
		this.college = college;
	}
	
	
}
