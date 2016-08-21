package cn.edu.xmu.entity;

/**
 * 
 * @author xiaoping 3.2.3本科实验、实习、实训场所情况 date 2015-8-4
 *
 */
public class BachelorExpPlace
{

	// 本科实验、实习、实训场所
	private String area; // 面积（平方米）
	private String exphour; // 学年度承担的实验教学人时数（人时）
	private String expcount; // 学年度承担的实验教学人次数（人次）
	private String projectnum; // 本科生实验、实习、实训项目数（个）
	private String capacity; // 每次可容纳的学生数（人）

	// 校外实习、实训基地
	private String outschoolnum; // 校外基地个数
	private String outcapacity; // 校外基地平均每次可接纳学生数（个）
	private String rowTitle;

	private String college;

	public String getArea()
	{
		return area;
	}

	public void setArea(String area)
	{
		this.area = area;
	}

	public String getExphour()
	{
		return exphour;
	}

	public void setExphour(String exphour)
	{
		this.exphour = exphour;
	}

	public String getExpcount()
	{
		return expcount;
	}

	public void setExpcount(String expcount)
	{
		this.expcount = expcount;
	}

	public String getProjectnum()
	{
		return projectnum;
	}

	public void setProjectnum(String projectnum)
	{
		this.projectnum = projectnum;
	}

	public String getCapacity()
	{
		return capacity;
	}

	public void setCapacity(String capacity)
	{
		this.capacity = capacity;
	}

	public String getOutschoolnum()
	{
		return outschoolnum;
	}

	public void setOutschoolnum(String outschoolnum)
	{
		this.outschoolnum = outschoolnum;
	}

	public String getOutcapacity()
	{
		return outcapacity;
	}

	public void setOutcapacity(String outcapacity)
	{
		this.outcapacity = outcapacity;
	}

	public String getRowTitle()
	{
		return rowTitle;
	}

	public void setRowTitle(String rowTitle)
	{
		this.rowTitle = rowTitle;
	}

	public String getCollege()
	{
		return college;
	}

	public void setCollege(String college)
	{
		this.college = college;
	}

	public BachelorExpPlace(String area, String exphour, String expcount, String projectnum, String capacity,
			String outschoolnum, String outcapacity, String rowTitle, String college)
	{
		super();
		this.area = area;
		this.exphour = exphour;
		this.expcount = expcount;
		this.projectnum = projectnum;
		this.capacity = capacity;
		this.outschoolnum = outschoolnum;
		this.outcapacity = outcapacity;
		this.rowTitle = rowTitle;
		this.college = college;
	}

	public BachelorExpPlace()
	{
		this.area ="";
		this.exphour ="";
		this.expcount ="";
		this.projectnum ="";
		this.capacity ="";
		this.outschoolnum ="";
		this.outcapacity ="";
		this.rowTitle ="";
		this.college ="";
	}

}
