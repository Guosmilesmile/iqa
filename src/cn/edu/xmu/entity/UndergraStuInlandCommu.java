package cn.edu.xmu.entity;

import java.sql.Date;

/**
 * 
 * @author xiaoping 附表6-2-2-3本科生境内交流情况（学年） date 2015-7-9
 *
 */
public class UndergraStuInlandCommu
{
	private int usic_id;//主键
	private String  usic_institute;//学院
	private Integer usic_outnumber;//本校到外校学生数
	private Integer usic_innumber;//外校到本校学生数
	private int usic_serialnumber;//序列号
	private Date usic_deadline;//截止日期
	private String usic_college;//所属学院
	private String usic_comments;//审核意见
	private int usic_isnull;//记录中是否存在空字段，0为完整   1为存在空字段
	public int getUsic_id()
	{
		return usic_id;
	}
	public void setUsic_id(int usic_id)
	{
		this.usic_id = usic_id;
	}
	public String getUsic_institute()
	{
		return usic_institute;
	}
	public void setUsic_institute(String usic_institute)
	{
		this.usic_institute = usic_institute;
	}
	public Integer getUsic_outnumber()
	{
		return usic_outnumber;
	}
	public void setUsic_outnumber(Integer usic_outnumber)
	{
		this.usic_outnumber = usic_outnumber;
	}
	public Integer getUsic_innumber()
	{
		return usic_innumber;
	}
	public void setUsic_innumber(Integer usic_innumber)
	{
		this.usic_innumber = usic_innumber;
	}
	public int getUsic_serialnumber()
	{
		return usic_serialnumber;
	}
	public void setUsic_serialnumber(int usic_serialnumber)
	{
		this.usic_serialnumber = usic_serialnumber;
	}
	public Date getUsic_deadline()
	{
		return usic_deadline;
	}
	public void setUsic_deadline(Date usic_deadline)
	{
		this.usic_deadline = usic_deadline;
	}
	public String getUsic_college()
	{
		return usic_college;
	}
	public void setUsic_college(String usic_college)
	{
		this.usic_college = usic_college;
	}
	public String getUsic_comments()
	{
		return usic_comments;
	}
	public void setUsic_comments(String usic_comments)
	{
		this.usic_comments = usic_comments;
	}
	public int getUsic_isnull()
	{
		return usic_isnull;
	}
	public void setUsic_isnull(int usic_isnull)
	{
		this.usic_isnull = usic_isnull;
	}
	public UndergraStuInlandCommu()
	{
		super();
	}
	//全参构造函数
	public UndergraStuInlandCommu(int usic_id, String usic_institute, Integer usic_outnumber, Integer usic_innumber,
			int usic_serialnumber, Date usic_deadline, String usic_college, String usic_comments, int usic_isnull)
	{
		super();
		this.usic_id = usic_id;
		this.usic_institute = usic_institute;
		this.usic_outnumber = usic_outnumber;
		this.usic_innumber = usic_innumber;
		this.usic_serialnumber = usic_serialnumber;
		this.usic_deadline = usic_deadline;
		this.usic_college = usic_college;
		this.usic_comments = usic_comments;
		this.usic_isnull = usic_isnull;
	}
	
}
