package cn.edu.xmu.entity;

import java.sql.Date;

/**
 * 
 * @author xiaoping 数据报表2-1 占地与建筑面积(时点) date 2015-6-30
 *
 */
public class CoverBuildingArea
{
	private int cba_id; // ID

	private Date cba_statisticaltime;// 统计时间

	private String cba_fillschool;// 填报学校

	private Float cba_totalcoverarea;// 总占地面积

	private Float cba_propertycov;// 学校产权占地面积

	private Float cba_propertyafforestcov;// 学校产权绿化用地占地面积

	private Float cba_nonpropertycov;// 非学校产权占地面积

	private Float cba_nonpropertyafforestcov;// 非学校产权绿化用地占地面积

	private Float cba_nonproindepusecov;// 非学校产权独立使用占地面积

	private Float cba_nonprocommonusecov;// 非学校产权共同使用占地面积

	private Float cba_totalbuildingarea;// 总建筑面积

	private Float cba_propertybui;// 学校产权建筑面积

	private Float cba_nonpropertybui;// 非学校产权建筑面积

	private Float cba_nonproindepusebui;// 非学校产权独立使用建筑面积

	private Float cba_nonprocommonusebui;// 非学校产权共同使用建筑面积

	private int cba_serialnumber;// 序列号

	private Date cba_deadline;// 截止日期

	private String cba_college;// 所属学院

	private String cba_comments;// 审核意见
	private int cba_isnull;//记录中是否有孔子段，0完整 1存在空字段

	public int getCba_id()
	{
		return cba_id;
	}

	public void setCba_id(int cba_id)
	{
		this.cba_id = cba_id;
	}

	public Date getCba_statisticaltime()
	{
		return cba_statisticaltime;
	}

	public void setCba_statisticaltime(Date cba_statisticaltime)
	{
		this.cba_statisticaltime = cba_statisticaltime;
	}

	public String getCba_fillschool()
	{
		return cba_fillschool;
	}

	public void setCba_fillschool(String cba_fillschool)
	{
		this.cba_fillschool = cba_fillschool;
	}

	public Float getCba_totalcoverarea()
	{
		return cba_totalcoverarea;
	}

	public void setCba_totalcoverarea(Float cba_totalcoverarea)
	{
		this.cba_totalcoverarea = cba_totalcoverarea;
	}

	public Float getCba_propertycov()
	{
		return cba_propertycov;
	}

	public void setCba_propertycov(Float cba_propertycov)
	{
		this.cba_propertycov = cba_propertycov;
	}

	public Float getCba_propertyafforestcov()
	{
		return cba_propertyafforestcov;
	}

	public void setCba_propertyafforestcov(Float cba_propertyafforestcov)
	{
		this.cba_propertyafforestcov = cba_propertyafforestcov;
	}

	public Float getCba_nonpropertycov()
	{
		return cba_nonpropertycov;
	}

	public void setCba_nonpropertycov(Float cba_nonpropertycov)
	{
		this.cba_nonpropertycov = cba_nonpropertycov;
	}

	public Float getCba_nonpropertyafforestcov()
	{
		return cba_nonpropertyafforestcov;
	}

	public void setCba_nonpropertyafforestcov(Float cba_nonpropertyafforestcov)
	{
		this.cba_nonpropertyafforestcov = cba_nonpropertyafforestcov;
	}

	public Float getCba_nonproindepusecov()
	{
		return cba_nonproindepusecov;
	}

	public void setCba_nonproindepusecov(Float cba_nonproindepusecov)
	{
		this.cba_nonproindepusecov = cba_nonproindepusecov;
	}

	public Float getCba_nonprocommonusecov()
	{
		return cba_nonprocommonusecov;
	}

	public void setCba_nonprocommonusecov(Float cba_nonprocommonusecov)
	{
		this.cba_nonprocommonusecov = cba_nonprocommonusecov;
	}

	public Float getCba_totalbuildingarea()
	{
		return cba_totalbuildingarea;
	}

	public void setCba_totalbuildingarea(Float cba_totalbuildingarea)
	{
		this.cba_totalbuildingarea = cba_totalbuildingarea;
	}

	public Float getCba_propertybui()
	{
		return cba_propertybui;
	}

	public void setCba_propertybui(Float cba_propertybui)
	{
		this.cba_propertybui = cba_propertybui;
	}

	public Float getCba_nonpropertybui()
	{
		return cba_nonpropertybui;
	}

	public void setCba_nonpropertybui(Float cba_nonpropertybui)
	{
		this.cba_nonpropertybui = cba_nonpropertybui;
	}

	public Float getCba_nonproindepusebui()
	{
		return cba_nonproindepusebui;
	}

	public void setCba_nonproindepusebui(Float cba_nonproindepusebui)
	{
		this.cba_nonproindepusebui = cba_nonproindepusebui;
	}

	public Float getCba_nonprocommonusebui()
	{
		return cba_nonprocommonusebui;
	}

	public void setCba_nonprocommonusebui(Float cba_nonprocommonusebui)
	{
		this.cba_nonprocommonusebui = cba_nonprocommonusebui;
	}

	public int getCba_serialnumber()
	{
		return cba_serialnumber;
	}

	public void setCba_serialnumber(int cba_serialnumber)
	{
		this.cba_serialnumber = cba_serialnumber;
	}

	public Date getCba_deadline()
	{
		return cba_deadline;
	}

	public void setCba_deadline(Date cba_deadline)
	{
		this.cba_deadline = cba_deadline;
	}

	public String getCba_college()
	{
		return cba_college;
	}

	public void setCba_college(String cba_college)
	{
		this.cba_college = cba_college;
	}

	public String getCba_comments()
	{
		return cba_comments;
	}

	public void setCba_comments(String cba_comments)
	{
		this.cba_comments = cba_comments;
	}

	public int getCba_isnull()
	{
		return cba_isnull;
	}

	public void setCba_isnull(int cba_isnull)
	{
		this.cba_isnull = cba_isnull;
	}

	//构造函数
	public CoverBuildingArea()
	{
		super();
	}

	/**
	 * 
	 * @param cba_id
	 * @param cba_statisticaltime
	 * @param cba_fillschool
	 * @param cba_totalcoverarea
	 * @param cba_propertycov
	 * @param cba_propertyafforestcov
	 * @param cba_nonpropertycov
	 * @param cba_nonpropertyafforestcov
	 * @param cba_nonproindepusecov
	 * @param cba_nonprocommonusecov
	 * @param cba_totalbuildingarea
	 * @param cba_propertybui
	 * @param cba_nonpropertybui
	 * @param cba_nonproindepusebui
	 * @param cba_nonprocommonusebui
	 * @param cba_serialnumber
	 * @param cba_deadline
	 * @param cba_college
	 * @param cba_comments
	 * 全参构造函数
	 */
	public CoverBuildingArea(int cba_id, Date cba_statisticaltime, String cba_fillschool, Float cba_totalcoverarea,
			Float cba_propertycov, Float cba_propertyafforestcov, Float cba_nonpropertycov,
			Float cba_nonpropertyafforestcov, Float cba_nonproindepusecov, Float cba_nonprocommonusecov,
			Float cba_totalbuildingarea, Float cba_propertybui, Float cba_nonpropertybui, Float cba_nonproindepusebui,
			Float cba_nonprocommonusebui, int cba_serialnumber, Date cba_deadline, String cba_college,
			String cba_comments, int cba_isnull)
	{
		super();
		this.cba_id = cba_id;
		this.cba_statisticaltime = cba_statisticaltime;
		this.cba_fillschool = cba_fillschool;
		this.cba_totalcoverarea = cba_totalcoverarea;
		this.cba_propertycov = cba_propertycov;
		this.cba_propertyafforestcov = cba_propertyafforestcov;
		this.cba_nonpropertycov = cba_nonpropertycov;
		this.cba_nonpropertyafforestcov = cba_nonpropertyafforestcov;
		this.cba_nonproindepusecov = cba_nonproindepusecov;
		this.cba_nonprocommonusecov = cba_nonprocommonusecov;
		this.cba_totalbuildingarea = cba_totalbuildingarea;
		this.cba_propertybui = cba_propertybui;
		this.cba_nonpropertybui = cba_nonpropertybui;
		this.cba_nonproindepusebui = cba_nonproindepusebui;
		this.cba_nonprocommonusebui = cba_nonprocommonusebui;
		this.cba_serialnumber = cba_serialnumber;
		this.cba_deadline = cba_deadline;
		this.cba_college = cba_college;
		this.cba_comments = cba_comments;
		this.cba_isnull = cba_isnull;
	}
	
	/**
	 * 
	 * @param cba_statisticaltime
	 * @param cba_fillschool
	 * @param cba_totalcoverarea
	 * @param cba_propertycov
	 * @param cba_propertyafforestcov
	 * @param cba_nonpropertycov
	 * @param cba_nonpropertyafforestcov
	 * @param cba_nonproindepusecov
	 * @param cba_nonprocommonusecov
	 * @param cba_totalbuildingarea
	 * @param cba_propertybui
	 * @param cba_nonpropertybui
	 * @param cba_nonproindepusebui
	 * @param cba_nonprocommonusebui
	 * @param cba_serialnumber
	 * @param cba_deadline
	 * @param cba_college
	 * @param cba_comments
	 * 无id构造函数
	 */
	public CoverBuildingArea(Date cba_statisticaltime, String cba_fillschool, Float cba_totalcoverarea,
			Float cba_propertycov, Float cba_propertyafforestcov, Float cba_nonpropertycov,
			Float cba_nonpropertyafforestcov, Float cba_nonproindepusecov, Float cba_nonprocommonusecov,
			Float cba_totalbuildingarea, Float cba_propertybui, Float cba_nonpropertybui, Float cba_nonproindepusebui,
			Float cba_nonprocommonusebui, int cba_serialnumber, Date cba_deadline, String cba_college,
			String cba_comments, int cba_isnull)
	{
		super();
		this.cba_statisticaltime = cba_statisticaltime;
		this.cba_fillschool = cba_fillschool;
		this.cba_totalcoverarea = cba_totalcoverarea;
		this.cba_propertycov = cba_propertycov;
		this.cba_propertyafforestcov = cba_propertyafforestcov;
		this.cba_nonpropertycov = cba_nonpropertycov;
		this.cba_nonpropertyafforestcov = cba_nonpropertyafforestcov;
		this.cba_nonproindepusecov = cba_nonproindepusecov;
		this.cba_nonprocommonusecov = cba_nonprocommonusecov;
		this.cba_totalbuildingarea = cba_totalbuildingarea;
		this.cba_propertybui = cba_propertybui;
		this.cba_nonpropertybui = cba_nonpropertybui;
		this.cba_nonproindepusebui = cba_nonproindepusebui;
		this.cba_nonprocommonusebui = cba_nonprocommonusebui;
		this.cba_serialnumber = cba_serialnumber;
		this.cba_deadline = cba_deadline;
		this.cba_college = cba_college;
		this.cba_comments = cba_comments;
		this.cba_isnull = cba_isnull;
	}
}
