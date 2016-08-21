package cn.edu.xmu.entity;

import java.sql.Date;

/**
 * 
 * @author xiaoping 表3-4-1 高层次人才(时点) date 2015-7-3
 *
 */
public class HighLevelTalent
{
	private int hlt_id;//主键
	private String hlt_name;//姓名
	private String hlt_worknumber;//工号
	private String hlt_type;//类型
	private String hlt_researchdirection;//研究方向
	private Date hlt_acquisitiondate;//获得时间
	private int hlt_serialnumber;//序列号
	private Date hlt_deadline;//截止日期
	private String hlt_college;//所属学院
	private String hlt_comments;//审核意见
	private int hlt_isnull;//0完整 1记录中有空子段
	/**
	 * @return the hlt_id
	 */
	public int getHlt_id()
	{
		return hlt_id;
	}
	/**
	 * @param hlt_id the hlt_id to set
	 */
	public void setHlt_id(int hlt_id)
	{
		this.hlt_id = hlt_id;
	}
	/**
	 * @return the hlt_name
	 */
	public String getHlt_name()
	{
		return hlt_name;
	}
	/**
	 * @param hlt_name the hlt_name to set
	 */
	public void setHlt_name(String hlt_name)
	{
		this.hlt_name = hlt_name;
	}
	/**
	 * @return the hlt_worknumber
	 */
	public String getHlt_worknumber()
	{
		return hlt_worknumber;
	}
	/**
	 * @param hlt_worknumber the hlt_worknumber to set
	 */
	public void setHlt_worknumber(String hlt_worknumber)
	{
		this.hlt_worknumber = hlt_worknumber;
	}
	/**
	 * @return the hlt_type
	 */
	public String getHlt_type()
	{
		return hlt_type;
	}
	/**
	 * @param hlt_type the hlt_type to set
	 */
	public void setHlt_type(String hlt_type)
	{
		this.hlt_type = hlt_type;
	}
	/**
	 * @return the hlt_researchdirection
	 */
	public String getHlt_researchdirection()
	{
		return hlt_researchdirection;
	}
	/**
	 * @param hlt_researchdirection the hlt_researchdirection to set
	 */
	public void setHlt_researchdirection(String hlt_researchdirection)
	{
		this.hlt_researchdirection = hlt_researchdirection;
	}
	/**
	 * @return the hlt_acquisitiondate
	 */
	public Date getHlt_acquisitiondate()
	{
		return hlt_acquisitiondate;
	}
	/**
	 * @param hlt_acquisitiondate the hlt_acquisitiondate to set
	 */
	public void setHlt_acquisitiondate(Date hlt_acquisitiondate)
	{
		this.hlt_acquisitiondate = hlt_acquisitiondate;
	}
	/**
	 * @return the hlt_serialnumber
	 */
	public int getHlt_serialnumber()
	{
		return hlt_serialnumber;
	}
	/**
	 * @param hlt_serialnumber the hlt_serialnumber to set
	 */
	public void setHlt_serialnumber(int hlt_serialnumber)
	{
		this.hlt_serialnumber = hlt_serialnumber;
	}
	/**
	 * @return the hlt_deadline
	 */
	public Date getHlt_deadline()
	{
		return hlt_deadline;
	}
	/**
	 * @param hlt_deadline the hlt_deadline to set
	 */
	public void setHlt_deadline(Date hlt_deadline)
	{
		this.hlt_deadline = hlt_deadline;
	}
	/**
	 * @return the hlt_college
	 */
	public String getHlt_college()
	{
		return hlt_college;
	}
	/**
	 * @param hlt_college the hlt_college to set
	 */
	public void setHlt_college(String hlt_college)
	{
		this.hlt_college = hlt_college;
	}
	/**
	 * @return the hlt_comments
	 */
	public String getHlt_comments()
	{
		return hlt_comments;
	}
	/**
	 * @param hlt_comments the hlt_comments to set
	 */
	public void setHlt_comments(String hlt_comments)
	{
		this.hlt_comments = hlt_comments;
	}
	
	public int getHlt_isnull()
	{
		return hlt_isnull;
	}
	public void setHlt_isnull(int hlt_isnull)
	{
		this.hlt_isnull = hlt_isnull;
	}
	public HighLevelTalent()
	{
		super();
	}
	
	/**
	 * 全参构造函数
	 * @param hlt_id
	 * @param hlt_name
	 * @param hlt_worknumber
	 * @param hlt_type
	 * @param hlt_researchdirection
	 * @param hlt_acquisitiondate
	 * @param hlt_serialnumber
	 * @param hlt_deadline
	 * @param hlt_college
	 * @param hlt_comments
	 */
	public HighLevelTalent(int hlt_id, String hlt_name, String hlt_worknumber, String hlt_type,
			String hlt_researchdirection, Date hlt_acquisitiondate, int hlt_serialnumber, Date hlt_deadline,
			String hlt_college, String hlt_comments, int hlt_isnull)
	{
		super();
		this.hlt_id = hlt_id;
		this.hlt_name = hlt_name;
		this.hlt_worknumber = hlt_worknumber;
		this.hlt_type = hlt_type;
		this.hlt_researchdirection = hlt_researchdirection;
		this.hlt_acquisitiondate = hlt_acquisitiondate;
		this.hlt_serialnumber = hlt_serialnumber;
		this.hlt_deadline = hlt_deadline;
		this.hlt_college = hlt_college;
		this.hlt_comments = hlt_comments;
		this.hlt_isnull = hlt_isnull;
	}
	
}
