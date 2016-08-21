package cn.edu.xmu.entity;

/**
 * 
 * @author xiaoping 5.4 各教学单位学生管理人员与学生情况 date 2015-7-17
 *
 */
public class TeachingUnitManagerAndStudent
{
	private int serialNumber;//序号
	private String unit;//单位
	private int manager;//学生管理人员数
	private int undergraduate;//本科在校学生数
	private double undergraManagerRate;//本科在校生人数与学生管理人员比例(%)
	private String college;
	
	public int getSerialNumber()
	{
		return serialNumber;
	}
	public void setSerialNumber(int serialNumber)
	{
		this.serialNumber = serialNumber;
	}
	public String getUnit()
	{
		return unit;
	}
	public void setUnit(String unit)
	{
		this.unit = unit;
	}
	public int getManager()
	{
		return manager;
	}
	public void setManager(int manager)
	{
		this.manager = manager;
	}
	public int getUndergraduate()
	{
		return undergraduate;
	}
	public void setUndergraduate(int undergraduate)
	{
		this.undergraduate = undergraduate;
	}

	public double getUndergraManagerRate()
	{
		return undergraManagerRate;
	}
	public void setUndergraManagerRate(double undergraManagerRate)
	{
		this.undergraManagerRate = undergraManagerRate;
	}
	public String getCollege()
	{
		return college;
	}
	public void setCollege(String college)
	{
		this.college = college;
	}
	public TeachingUnitManagerAndStudent(int serialNumber, String unit, int manager, int undergraduate,
			double undergraManagerRate, String college)
	{
		super();
		this.serialNumber = serialNumber;
		this.unit = unit;
		this.manager = manager;
		this.undergraduate = undergraduate;
		this.undergraManagerRate = undergraManagerRate;
		this.college = college;
	}
	
}
