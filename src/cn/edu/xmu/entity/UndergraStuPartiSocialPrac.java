package cn.edu.xmu.entity;

import java.sql.Date;

/**
 * 
 * @author xiaoping 附表5-4-3 本科生参与暑期社会实践情况  date 2015-7-10
 *
 */
public class UndergraStuPartiSocialPrac
{
	private int uspsp_id;//主键
	private String  uspsp_department;//单位
	private Integer uspsp_focuspracnum;//参加集中社会实践人数
	private Integer uspsp_scatterpracnum;//分散社会实践人数
	private Integer uspsp_subtotal;//小计
	private int uspsp_serialnumber;//序列号
	private Date uspsp_deadline;//截止日期
	private String uspsp_college;//所属学院
	private String uspsp_comments;//审核意见
	private int uspsp_isnull;//记录中是否存在空字段，0为完整   1为存在空字段
	public int getUspsp_id()
	{
		return uspsp_id;
	}
	public void setUspsp_id(int uspsp_id)
	{
		this.uspsp_id = uspsp_id;
	}
	public String getUspsp_department()
	{
		return uspsp_department;
	}
	public void setUspsp_department(String uspsp_department)
	{
		this.uspsp_department = uspsp_department;
	}
	public Integer getUspsp_focuspracnum()
	{
		return uspsp_focuspracnum;
	}
	public void setUspsp_focuspracnum(Integer uspsp_focuspracnum)
	{
		this.uspsp_focuspracnum = uspsp_focuspracnum;
	}
	public Integer getUspsp_scatterpracnum()
	{
		return uspsp_scatterpracnum;
	}
	public void setUspsp_scatterpracnum(Integer uspsp_scatterpracnum)
	{
		this.uspsp_scatterpracnum = uspsp_scatterpracnum;
	}
	public Integer getUspsp_subtotal()
	{
		return uspsp_subtotal;
	}
	public void setUspsp_subtotal(Integer uspsp_subtotal)
	{
		this.uspsp_subtotal = uspsp_subtotal;
	}
	public int getUspsp_serialnumber()
	{
		return uspsp_serialnumber;
	}
	public void setUspsp_serialnumber(int uspsp_serialnumber)
	{
		this.uspsp_serialnumber = uspsp_serialnumber;
	}
	public Date getUspsp_deadline()
	{
		return uspsp_deadline;
	}
	public void setUspsp_deadline(Date uspsp_deadline)
	{
		this.uspsp_deadline = uspsp_deadline;
	}
	public String getUspsp_college()
	{
		return uspsp_college;
	}
	public void setUspsp_college(String uspsp_college)
	{
		this.uspsp_college = uspsp_college;
	}
	public String getUspsp_comments()
	{
		return uspsp_comments;
	}
	public void setUspsp_comments(String uspsp_comments)
	{
		this.uspsp_comments = uspsp_comments;
	}
	public int getUspsp_isnull()
	{
		return uspsp_isnull;
	}
	public void setUspsp_isnull(int uspsp_isnull)
	{
		this.uspsp_isnull = uspsp_isnull;
	}
	public UndergraStuPartiSocialPrac()
	{
		super();
	}
	//全参构造函数
	public UndergraStuPartiSocialPrac(int uspsp_id, String uspsp_department, Integer uspsp_focuspracnum,
			Integer uspsp_scatterpracnum, Integer uspsp_subtotal, int uspsp_serialnumber, Date uspsp_deadline,
			String uspsp_college, String uspsp_comments, int uspsp_isnull)
	{
		super();
		this.uspsp_id = uspsp_id;
		this.uspsp_department = uspsp_department;
		this.uspsp_focuspracnum = uspsp_focuspracnum;
		this.uspsp_scatterpracnum = uspsp_scatterpracnum;
		this.uspsp_subtotal = uspsp_subtotal;
		this.uspsp_serialnumber = uspsp_serialnumber;
		this.uspsp_deadline = uspsp_deadline;
		this.uspsp_college = uspsp_college;
		this.uspsp_comments = uspsp_comments;
		this.uspsp_isnull = uspsp_isnull;
	}
	
}
