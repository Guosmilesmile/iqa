package cn.edu.xmu.entity;

import java.sql.Date;
/**
 * 
 * @author xiaoping 数据报表 附表4-2-2-2教学计划执行情况  date 2015-7-8
 *
 */
public class TeachingPlanImpl
{
	private int tpi_id;//主键
	private String tpi_institute;//学院
	private String tpi_major;//专业
	private String tpi_grade;//年级
	private Integer tpi_plancoursenum;//应开课程门数
	private Float tpi_plancoursecredit;//应开课程学分
	private Integer  tpi_actualcoursenum;//实开课程门数
	private Float tpi_actualcoursecredit;//实开课程学分
	private Integer tpi_newcoursenum;//新增课程门数
	private Float  tpi_newcoursecredit;//新增课程学分
	private Integer tpi_stopcoursenum;//停开课程门数
	private Float tpi_stopcoursecredit;//停开课程学分
	private String tpi_advancelatercoursenum;//提前推后课程门数
	private Float tpi_advancelatercoursecredit;//提前推后课程学分
	private int tpi_serialnumber;//序列号
	private Date tpi_deadline;//截止日期
	private String tpi_college;//所属学院
	private String tpi_comments;//审核意见
	private int tpi_isnull;//记录中是否存在空字段，0为完整   1为存在空字段
	public int getTpi_id()
	{
		return tpi_id;
	}
	public void setTpi_id(int tpi_id)
	{
		this.tpi_id = tpi_id;
	}
	public String getTpi_institute()
	{
		return tpi_institute;
	}
	public void setTpi_institute(String tpi_institute)
	{
		this.tpi_institute = tpi_institute;
	}
	public String getTpi_major()
	{
		return tpi_major;
	}
	public void setTpi_major(String tpi_major)
	{
		this.tpi_major = tpi_major;
	}
	public String getTpi_grade()
	{
		return tpi_grade;
	}
	public void setTpi_grade(String tpi_grade)
	{
		this.tpi_grade = tpi_grade;
	}
	public Integer getTpi_plancoursenum()
	{
		return tpi_plancoursenum;
	}
	public void setTpi_plancoursenum(Integer tpi_plancoursenum)
	{
		this.tpi_plancoursenum = tpi_plancoursenum;
	}
	public Float getTpi_plancoursecredit()
	{
		return tpi_plancoursecredit;
	}
	public void setTpi_plancoursecredit(Float tpi_plancoursecredit)
	{
		this.tpi_plancoursecredit = tpi_plancoursecredit;
	}
	public Integer getTpi_actualcoursenum()
	{
		return tpi_actualcoursenum;
	}
	public void setTpi_actualcoursenum(Integer tpi_actualcoursenum)
	{
		this.tpi_actualcoursenum = tpi_actualcoursenum;
	}
	public Float getTpi_actualcoursecredit()
	{
		return tpi_actualcoursecredit;
	}
	public void setTpi_actualcoursecredit(Float tpi_actualcoursecredit)
	{
		this.tpi_actualcoursecredit = tpi_actualcoursecredit;
	}
	public Integer getTpi_newcoursenum()
	{
		return tpi_newcoursenum;
	}
	public void setTpi_newcoursenum(Integer tpi_newcoursenum)
	{
		this.tpi_newcoursenum = tpi_newcoursenum;
	}
	public Float getTpi_newcoursecredit()
	{
		return tpi_newcoursecredit;
	}
	public void setTpi_newcoursecredit(Float tpi_newcoursecredit)
	{
		this.tpi_newcoursecredit = tpi_newcoursecredit;
	}
	public Integer getTpi_stopcoursenum()
	{
		return tpi_stopcoursenum;
	}
	public void setTpi_stopcoursenum(Integer tpi_stopcoursenum)
	{
		this.tpi_stopcoursenum = tpi_stopcoursenum;
	}
	public Float getTpi_stopcoursecredit()
	{
		return tpi_stopcoursecredit;
	}
	public void setTpi_stopcoursecredit(Float tpi_stopcoursecredit)
	{
		this.tpi_stopcoursecredit = tpi_stopcoursecredit;
	}
	public String getTpi_advancelatercoursenum()
	{
		return tpi_advancelatercoursenum;
	}
	public void setTpi_advancelatercoursenum(String tpi_advancelatercoursenum)
	{
		this.tpi_advancelatercoursenum = tpi_advancelatercoursenum;
	}
	public Float getTpi_advancelatercoursecredit()
	{
		return tpi_advancelatercoursecredit;
	}
	public void setTpi_advancelatercoursecredit(Float tpi_advancelatercoursecredit)
	{
		this.tpi_advancelatercoursecredit = tpi_advancelatercoursecredit;
	}
	public int getTpi_serialnumber()
	{
		return tpi_serialnumber;
	}
	public void setTpi_serialnumber(int tpi_serialnumber)
	{
		this.tpi_serialnumber = tpi_serialnumber;
	}
	public Date getTpi_deadline()
	{
		return tpi_deadline;
	}
	public void setTpi_deadline(Date tpi_deadline)
	{
		this.tpi_deadline = tpi_deadline;
	}
	public String getTpi_college()
	{
		return tpi_college;
	}
	public void setTpi_college(String tpi_college)
	{
		this.tpi_college = tpi_college;
	}
	public String getTpi_comments()
	{
		return tpi_comments;
	}
	public void setTpi_comments(String tpi_comments)
	{
		this.tpi_comments = tpi_comments;
	}
	public int getTpi_isnull()
	{
		return tpi_isnull;
	}
	public void setTpi_isnull(int tpi_isnull)
	{
		this.tpi_isnull = tpi_isnull;
	}
	public TeachingPlanImpl()
	{
		super();
	}
	//全参构造函数
	public TeachingPlanImpl(int tpi_id, String tpi_institute, String tpi_major, String tpi_grade,
			Integer tpi_plancoursenum, Float tpi_plancoursecredit, Integer tpi_actualcoursenum,
			Float tpi_actualcoursecredit, Integer tpi_newcoursenum, Float tpi_newcoursecredit,
			Integer tpi_stopcoursenum, Float tpi_stopcoursecredit, String tpi_advancelatercoursenum,
			Float tpi_advancelatercoursecredit, int tpi_serialnumber, Date tpi_deadline, String tpi_college,
			String tpi_comments, int tpi_isnull)
	{
		super();
		this.tpi_id = tpi_id;
		this.tpi_institute = tpi_institute;
		this.tpi_major = tpi_major;
		this.tpi_grade = tpi_grade;
		this.tpi_plancoursenum = tpi_plancoursenum;
		this.tpi_plancoursecredit = tpi_plancoursecredit;
		this.tpi_actualcoursenum = tpi_actualcoursenum;
		this.tpi_actualcoursecredit = tpi_actualcoursecredit;
		this.tpi_newcoursenum = tpi_newcoursenum;
		this.tpi_newcoursecredit = tpi_newcoursecredit;
		this.tpi_stopcoursenum = tpi_stopcoursenum;
		this.tpi_stopcoursecredit = tpi_stopcoursecredit;
		this.tpi_advancelatercoursenum = tpi_advancelatercoursenum;
		this.tpi_advancelatercoursecredit = tpi_advancelatercoursecredit;
		this.tpi_serialnumber = tpi_serialnumber;
		this.tpi_deadline = tpi_deadline;
		this.tpi_college = tpi_college;
		this.tpi_comments = tpi_comments;
		this.tpi_isnull = tpi_isnull;
	}
}
