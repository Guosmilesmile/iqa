package cn.edu.xmu.entity;

import java.sql.Date;

/**
 * 
 * @author xiaoping 附表6-1-5-1厦门大学在全国各省（市、自治区）招生出档分数情况（时点） date 2015-7-11
 *
 */
public class RecruitExceedScore
{
	private int res_id;//主键
	private String res_year;//年份
	private Float res_libexctwentyproportion;//文科超出20分的省份比例(%)
	private Float res_libexcthirtyproportion;//文科超出30分的省份比例(%)
	private Float res_libexclineave;//文科所有生源省份出档线超出本一线的平均值
	private Float res_sciexcthirtyproportion;//理科超出30分的省份比例(%)
	private Float res_sciexcfortyproportion;//理科超出40分的省份比例(%)
	private Float res_sciexclineave;//理科所有生源省份出档线超出本一线的平均值
	private int res_serialnumber;//序列号
	private Date res_deadline;//截止日期
	private String res_college;//所属学院
	private String res_comments;//审核意见
	private int res_isnull;//记录中是否存在空字段，0为完整   1为存在空字段
	public int getRes_id()
	{
		return res_id;
	}
	public void setRes_id(int res_id)
	{
		this.res_id = res_id;
	}
	public String getRes_year()
	{
		return res_year;
	}
	public void setRes_year(String res_year)
	{
		this.res_year = res_year;
	}
	public Float getRes_libexctwentyproportion()
	{
		return res_libexctwentyproportion;
	}
	public void setRes_libexctwentyproportion(Float res_libexctwentyproportion)
	{
		this.res_libexctwentyproportion = res_libexctwentyproportion;
	}
	public Float getRes_libexcthirtyproportion()
	{
		return res_libexcthirtyproportion;
	}
	public void setRes_libexcthirtyproportion(Float res_libexcthirtyproportion)
	{
		this.res_libexcthirtyproportion = res_libexcthirtyproportion;
	}
	public Float getRes_libexclineave()
	{
		return res_libexclineave;
	}
	public void setRes_libexclineave(Float res_libexclineave)
	{
		this.res_libexclineave = res_libexclineave;
	}
	public Float getRes_sciexcthirtyproportion()
	{
		return res_sciexcthirtyproportion;
	}
	public void setRes_sciexcthirtyproportion(Float res_sciexcthirtyproportion)
	{
		this.res_sciexcthirtyproportion = res_sciexcthirtyproportion;
	}
	public Float getRes_sciexcfortyproportion()
	{
		return res_sciexcfortyproportion;
	}
	public void setRes_sciexcfortyproportion(Float res_sciexcfortyproportion)
	{
		this.res_sciexcfortyproportion = res_sciexcfortyproportion;
	}
	public Float getRes_sciexclineave()
	{
		return res_sciexclineave;
	}
	public void setRes_sciexclineave(Float res_sciexclineave)
	{
		this.res_sciexclineave = res_sciexclineave;
	}
	public int getRes_serialnumber()
	{
		return res_serialnumber;
	}
	public void setRes_serialnumber(int res_serialnumber)
	{
		this.res_serialnumber = res_serialnumber;
	}
	public Date getRes_deadline()
	{
		return res_deadline;
	}
	public void setRes_deadline(Date res_deadline)
	{
		this.res_deadline = res_deadline;
	}
	public String getRes_college()
	{
		return res_college;
	}
	public void setRes_college(String res_college)
	{
		this.res_college = res_college;
	}
	public String getRes_comments()
	{
		return res_comments;
	}
	public void setRes_comments(String res_comments)
	{
		this.res_comments = res_comments;
	}
	public int getRes_isnull()
	{
		return res_isnull;
	}
	public void setRes_isnull(int res_isnull)
	{
		this.res_isnull = res_isnull;
	}
	public RecruitExceedScore()
	{
		super();
	}
	public RecruitExceedScore(int res_id, String res_year, Float res_libexctwentyproportion,
			Float res_libexcthirtyproportion, Float res_libexclineave, Float res_sciexcthirtyproportion,
			Float res_sciexcfortyproportion, Float res_sciexclineave, int res_serialnumber, Date res_deadline,
			String res_college, String res_comments, int res_isnull)
	{
		super();
		this.res_id = res_id;
		this.res_year = res_year;
		this.res_libexctwentyproportion = res_libexctwentyproportion;
		this.res_libexcthirtyproportion = res_libexcthirtyproportion;
		this.res_libexclineave = res_libexclineave;
		this.res_sciexcthirtyproportion = res_sciexcthirtyproportion;
		this.res_sciexcfortyproportion = res_sciexcfortyproportion;
		this.res_sciexclineave = res_sciexclineave;
		this.res_serialnumber = res_serialnumber;
		this.res_deadline = res_deadline;
		this.res_college = res_college;
		this.res_comments = res_comments;
		this.res_isnull = res_isnull;
	}
	
}
