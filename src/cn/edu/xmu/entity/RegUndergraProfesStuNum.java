package cn.edu.xmu.entity;

import java.sql.Date;

/**
 * 
 * @author xiaoping 数据报表6-1-2 普通本科分专业（大类）学生数（时点） date 2015-7-5
 *
 */
public class RegUndergraProfesStuNum
{
	private int rupsn_id;//主键
	private String rupsn_schprofescode;//校内专业（大类）代码
	private String rupsn_schprofesname;//校内专业（大类）名称
	private Integer  rupsn_edusystem;//学制
	private Integer rupsn_atschtotal;//在校人数总计（人）
	private Integer rupsn_gradeone;//一年级在校人数（人）
	private Integer rupsn_gradetwo;//二年级在校人数（人）
	private Integer rupsn_gradethree;//三年级在校人数（人）
	private Integer  rupsn_gradefour;//四年级在校人数（人）
	private Integer rupsn_abgradefive;//五年级及以上在校人数（人）
	private Integer rupsn_minor;//辅修在校人数（人）
	private Integer rupsn_doubledegree;//双学位在校人数（人）
	private Integer rupsn_intonumber;//本专业转入人数（人）
	private Integer  rupsn_outnumber;//本专业转出人数（人）
	private int rupsn_serialnumber;//序列号
	private Date rupsn_deadline;//截止日期
	private String rupsn_college;//所属学院
	private String rupsn_comments;//审核意见
	private int rupsn_isnull;//记录中是否存在空字段 0为完整  1为存在空字段
	public int getRupsn_id()
	{
		return rupsn_id;
	}
	public void setRupsn_id(int rupsn_id)
	{
		this.rupsn_id = rupsn_id;
	}
	public String getRupsn_schprofescode()
	{
		return rupsn_schprofescode;
	}
	public void setRupsn_schprofescode(String rupsn_schprofescode)
	{
		this.rupsn_schprofescode = rupsn_schprofescode;
	}
	public String getRupsn_schprofesname()
	{
		return rupsn_schprofesname;
	}
	public void setRupsn_schprofesname(String rupsn_schprofesname)
	{
		this.rupsn_schprofesname = rupsn_schprofesname;
	}
	public Integer getRupsn_edusystem()
	{
		return rupsn_edusystem;
	}
	public void setRupsn_edusystem(Integer rupsn_edusystem)
	{
		this.rupsn_edusystem = rupsn_edusystem;
	}
	public Integer getRupsn_atschtotal()
	{
		return rupsn_atschtotal;
	}
	public void setRupsn_atschtotal(Integer rupsn_atschtotal)
	{
		this.rupsn_atschtotal = rupsn_atschtotal;
	}
	public Integer getRupsn_gradeone()
	{
		return rupsn_gradeone;
	}
	public void setRupsn_gradeone(Integer rupsn_gradeone)
	{
		this.rupsn_gradeone = rupsn_gradeone;
	}
	public Integer getRupsn_gradetwo()
	{
		return rupsn_gradetwo;
	}
	public void setRupsn_gradetwo(Integer rupsn_gradetwo)
	{
		this.rupsn_gradetwo = rupsn_gradetwo;
	}
	public Integer getRupsn_gradethree()
	{
		return rupsn_gradethree;
	}
	public void setRupsn_gradethree(Integer rupsn_gradethree)
	{
		this.rupsn_gradethree = rupsn_gradethree;
	}
	public Integer getRupsn_gradefour()
	{
		return rupsn_gradefour;
	}
	public void setRupsn_gradefour(Integer rupsn_gradefour)
	{
		this.rupsn_gradefour = rupsn_gradefour;
	}
	public Integer getRupsn_abgradefive()
	{
		return rupsn_abgradefive;
	}
	public void setRupsn_abgradefive(Integer rupsn_abgradefive)
	{
		this.rupsn_abgradefive = rupsn_abgradefive;
	}
	public Integer getRupsn_minor()
	{
		return rupsn_minor;
	}
	public void setRupsn_minor(Integer rupsn_minor)
	{
		this.rupsn_minor = rupsn_minor;
	}
	public Integer getRupsn_doubledegree()
	{
		return rupsn_doubledegree;
	}
	public void setRupsn_doubledegree(Integer rupsn_doubledegree)
	{
		this.rupsn_doubledegree = rupsn_doubledegree;
	}
	public Integer getRupsn_intonumber()
	{
		return rupsn_intonumber;
	}
	public void setRupsn_intonumber(Integer rupsn_intonumber)
	{
		this.rupsn_intonumber = rupsn_intonumber;
	}
	public Integer getRupsn_outnumber()
	{
		return rupsn_outnumber;
	}
	public void setRupsn_outnumber(Integer rupsn_outnumber)
	{
		this.rupsn_outnumber = rupsn_outnumber;
	}
	public int getRupsn_serialnumber()
	{
		return rupsn_serialnumber;
	}
	public void setRupsn_serialnumber(int rupsn_serialnumber)
	{
		this.rupsn_serialnumber = rupsn_serialnumber;
	}
	public Date getRupsn_deadline()
	{
		return rupsn_deadline;
	}
	public void setRupsn_deadline(Date rupsn_deadline)
	{
		this.rupsn_deadline = rupsn_deadline;
	}
	public String getRupsn_college()
	{
		return rupsn_college;
	}
	public void setRupsn_college(String rupsn_college)
	{
		this.rupsn_college = rupsn_college;
	}
	public String getRupsn_comments()
	{
		return rupsn_comments;
	}
	public void setRupsn_comments(String rupsn_comments)
	{
		this.rupsn_comments = rupsn_comments;
	}
	public int getRupsn_isnull()
	{
		return rupsn_isnull;
	}
	public void setRupsn_isnull(int rupsn_isnull)
	{
		this.rupsn_isnull = rupsn_isnull;
	}
	public RegUndergraProfesStuNum()
	{
		super();
	}
	public RegUndergraProfesStuNum(int rupsn_id, String rupsn_schprofescode, String rupsn_schprofesname,
			Integer rupsn_edusystem, Integer rupsn_atschtotal, Integer rupsn_gradeone, Integer rupsn_gradetwo, Integer rupsn_gradethree,
			Integer rupsn_gradefour, Integer rupsn_abgradefive, Integer rupsn_minor, Integer rupsn_doubledegree, Integer rupsn_intonumber,
			Integer rupsn_outnumber, int rupsn_serialnumber, Date rupsn_deadline, String rupsn_college,
			String rupsn_comments, int rupsn_isnull)
	{
		super();
		this.rupsn_id = rupsn_id;
		this.rupsn_schprofescode = rupsn_schprofescode;
		this.rupsn_schprofesname = rupsn_schprofesname;
		this.rupsn_edusystem = rupsn_edusystem;
		this.rupsn_atschtotal = rupsn_atschtotal;
		this.rupsn_gradeone = rupsn_gradeone;
		this.rupsn_gradetwo = rupsn_gradetwo;
		this.rupsn_gradethree = rupsn_gradethree;
		this.rupsn_gradefour = rupsn_gradefour;
		this.rupsn_abgradefive = rupsn_abgradefive;
		this.rupsn_minor = rupsn_minor;
		this.rupsn_doubledegree = rupsn_doubledegree;
		this.rupsn_intonumber = rupsn_intonumber;
		this.rupsn_outnumber = rupsn_outnumber;
		this.rupsn_serialnumber = rupsn_serialnumber;
		this.rupsn_deadline = rupsn_deadline;
		this.rupsn_college = rupsn_college;
		this.rupsn_comments = rupsn_comments;
		this.rupsn_isnull = rupsn_isnull;
	}
	
}
