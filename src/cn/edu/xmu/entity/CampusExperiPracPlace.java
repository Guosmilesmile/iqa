package cn.edu.xmu.entity;
/**
 * 
 * @author xiaoping 附表9 校内实验实习实训场所情况 date 2015-7-20
 *
 */
public class CampusExperiPracPlace
{
	private int serialNumber;//序号
	private String placeName;//名称
	private float area;//面积（平方米）
	private String majors;//面向专业
	private int projectNum;//项目数
	private int receiveStuNum;//接待学生数
	private int hours;//年度承担的实验教学人时数（人时）
	private String college;
	public int getSerialNumber()
	{
		return serialNumber;
	}
	public void setSerialNumber(int serialNumber)
	{
		this.serialNumber = serialNumber;
	}
	public String getPlaceName()
	{
		return placeName;
	}
	public void setPlaceName(String placeName)
	{
		this.placeName = placeName;
	}
	public float getArea()
	{
		return area;
	}
	public void setArea(float area)
	{
		this.area = area;
	}
	public String getMajors()
	{
		return majors;
	}
	public void setMajors(String majors)
	{
		this.majors = majors;
	}
	public int getProjectNum()
	{
		return projectNum;
	}
	public void setProjectNum(int projectNum)
	{
		this.projectNum = projectNum;
	}
	public int getReceiveStuNum()
	{
		return receiveStuNum;
	}
	public void setReceiveStuNum(int receiveStuNum)
	{
		this.receiveStuNum = receiveStuNum;
	}
	public int getHours()
	{
		return hours;
	}
	public void setHours(int hours)
	{
		this.hours = hours;
	}
	public String getCollege()
	{
		return college;
	}
	public void setCollege(String college)
	{
		this.college = college;
	}
	//全参构造函数
	public CampusExperiPracPlace(int serialNumber, String placeName, float area, String majors, int projectNum,
			int receiveStuNum, int hours, String college)
	{
		super();
		this.serialNumber = serialNumber;
		this.placeName = placeName;
		this.area = area;
		this.majors = majors;
		this.projectNum = projectNum;
		this.receiveStuNum = receiveStuNum;
		this.hours = hours;
		this.college = college;
	}
	
}
