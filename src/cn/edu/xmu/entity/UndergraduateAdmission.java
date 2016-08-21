package cn.edu.xmu.entity;

/**
 * 
 * @author xiaoping 附表13 各专业（大类）本科生招生情况 date 2015-7-17
 *
 */
public class UndergraduateAdmission implements Comparable<UndergraduateAdmission>
{
	private int serialNumber; //序号
	private String major; //专业（大类）
	private int recruitPlan; //招生计划数（人）
	private int admissionNumber; //实际录取数(人)
	private int registerNumber; //实际报到数（人）
	private Double registerRate; //报到率（%） = 实际报到数 / 实际录取数 * 100%
	private String college;
	private String rowTitle;//用于显示分组
	public int getSerialNumber()
	{
		return serialNumber;
	}
	public void setSerialNumber(int serialNumber)
	{
		this.serialNumber = serialNumber;
	}
	public String getMajor()
	{
		return major;
	}
	public void setMajor(String major)
	{
		this.major = major;
	}
	public int getRecruitPlan()
	{
		return recruitPlan;
	}
	public void setRecruitPlan(int recruitPlan)
	{
		this.recruitPlan = recruitPlan;
	}
	public int getAdmissionNumber()
	{
		return admissionNumber;
	}
	public void setAdmissionNumber(int admissionNumber)
	{
		this.admissionNumber = admissionNumber;
	}
	public int getRegisterNumber()
	{
		return registerNumber;
	}
	public void setRegisterNumber(int registerNumber)
	{
		this.registerNumber = registerNumber;
	}
	public Double getRegisterRate()
	{
		return registerRate;
	}
	public void setRegisterRate(Double registerRate)
	{
		this.registerRate = registerRate;
	}
	public String getCollege()
	{
		return college;
	}
	public void setCollege(String college)
	{
		this.college = college;
	}
	public String getRowTitle()
	{
		return rowTitle;
	}
	public void setRowTitle(String rowTitle)
	{
		this.rowTitle = rowTitle;
	}
	public UndergraduateAdmission(int serialNumber, String major, int recruitPlan, int admissionNumber,
			int registerNumber, double registerRate, String college)
	{
		super();
		this.serialNumber = serialNumber;
		this.major = major;
		this.recruitPlan = recruitPlan;
		this.admissionNumber = admissionNumber;
		this.registerNumber = registerNumber;
		this.registerRate = registerRate;
		this.college = college;
		this.rowTitle = "报到率最低的十个专业";
	}
	@Override
	public int compareTo(UndergraduateAdmission arg0)
	{
		return this.getRegisterRate().compareTo(arg0.getRegisterRate());
	}
}