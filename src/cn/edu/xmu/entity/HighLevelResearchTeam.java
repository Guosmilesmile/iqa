package cn.edu.xmu.entity;

import java.sql.Date;

/**
 * 
 * @author xiaoping 表3-4-2  高层次研究团队 (时点)  date 2015-7-9
 *
 */
public class HighLevelResearchTeam
{
	private int hlrt_id;//主键
	private String hlrt_researchdirection;//研究方向
	private String hlrt_head;//负责人
	private String hlrt_headnumber;//负责人工号
	private String hlrt_type;//类型
	private Date hlrt_acquisitiondate;//获得时间
	private int hlrt_serialnumber;//序列号
	private Date hlrt_deadline;//截止日期
	private String hlrt_college;//所属学院
	private String hlrt_comments;//审核意见
	private int hlrt_isnull;//记录中是否存在空字段，0为完整   1为存在空字段
	public int getHlrt_id()
	{
		return hlrt_id;
	}
	public void setHlrt_id(int hlrt_id)
	{
		this.hlrt_id = hlrt_id;
	}
	public String getHlrt_researchdirection()
	{
		return hlrt_researchdirection;
	}
	public void setHlrt_researchdirection(String hlrt_researchdirection)
	{
		this.hlrt_researchdirection = hlrt_researchdirection;
	}
	public String getHlrt_head()
	{
		return hlrt_head;
	}
	public void setHlrt_head(String hlrt_head)
	{
		this.hlrt_head = hlrt_head;
	}
	public String getHlrt_headnumber()
	{
		return hlrt_headnumber;
	}
	public void setHlrt_headnumber(String hlrt_headnumber)
	{
		this.hlrt_headnumber = hlrt_headnumber;
	}
	public String getHlrt_type()
	{
		return hlrt_type;
	}
	public void setHlrt_type(String hlrt_type)
	{
		this.hlrt_type = hlrt_type;
	}
	public Date getHlrt_acquisitiondate()
	{
		return hlrt_acquisitiondate;
	}
	public void setHlrt_acquisitiondate(Date hlrt_acquisitiondate)
	{
		this.hlrt_acquisitiondate = hlrt_acquisitiondate;
	}
	public int getHlrt_serialnumber()
	{
		return hlrt_serialnumber;
	}
	public void setHlrt_serialnumber(int hlrt_serialnumber)
	{
		this.hlrt_serialnumber = hlrt_serialnumber;
	}
	public Date getHlrt_deadline()
	{
		return hlrt_deadline;
	}
	public void setHlrt_deadline(Date hlrt_deadline)
	{
		this.hlrt_deadline = hlrt_deadline;
	}
	public String getHlrt_college()
	{
		return hlrt_college;
	}
	public void setHlrt_college(String hlrt_college)
	{
		this.hlrt_college = hlrt_college;
	}
	public String getHlrt_comments()
	{
		return hlrt_comments;
	}
	public void setHlrt_comments(String hlrt_comments)
	{
		this.hlrt_comments = hlrt_comments;
	}
	public int getHlrt_isnull()
	{
		return hlrt_isnull;
	}
	public void setHlrt_isnull(int hlrt_isnull)
	{
		this.hlrt_isnull = hlrt_isnull;
	}
	public HighLevelResearchTeam()
	{
		super();
	}
	//全参构造函数
	public HighLevelResearchTeam(int hlrt_id, String hlrt_researchdirection, String hlrt_head, String hlrt_headnumber,
			String hlrt_type, Date hlrt_acquisitiondate, int hlrt_serialnumber, Date hlrt_deadline, String hlrt_college,
			String hlrt_comments, int hlrt_isnull)
	{
		super();
		this.hlrt_id = hlrt_id;
		this.hlrt_researchdirection = hlrt_researchdirection;
		this.hlrt_head = hlrt_head;
		this.hlrt_headnumber = hlrt_headnumber;
		this.hlrt_type = hlrt_type;
		this.hlrt_acquisitiondate = hlrt_acquisitiondate;
		this.hlrt_serialnumber = hlrt_serialnumber;
		this.hlrt_deadline = hlrt_deadline;
		this.hlrt_college = hlrt_college;
		this.hlrt_comments = hlrt_comments;
		this.hlrt_isnull = hlrt_isnull;
	}
	
}
