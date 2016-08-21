package cn.edu.xmu.entity;

import java.sql.Date;

/**
 * 
 * @author xiaoping 表3-2 校领导基本信息(时点) date 2015-7-3
 *
 */
public class SchoolLeaderInfo
{
	private int sli_id;//主键
	private Integer sli_sequencenumber;//序号
	private String sli_name;//姓名
	private String sli_worknumber;//工号
	private String sli_position;//职务
	private String sli_gender;//性别
	private Date sli_birthday;//出生年月
	private Date sli_inschooldate;//入校时间
	private String sli_education;//学历
	private String sli_degree;//最高学位
	private String sli_professionaltitle;//专业技术职称
	private String sli_responsibility;//校内分管工作
	private String sli_studyworkresume;//学习和工作简历
	private int sli_serialnumber;//序列号
	private Date sli_deadline;//截止日期
	private String  sli_college;//所属学院
	private String sli_comments;//审核意见
	private int sli_isnull;//记录中是否有空字段，0为完整 1为存在空字段
	public int getSli_id()
	{
		return sli_id;
	}
	public void setSli_id(int sli_id)
	{
		this.sli_id = sli_id;
	}
	public Integer getSli_sequencenumber()
	{
		return sli_sequencenumber;
	}
	public void setSli_sequencenumber(Integer sli_sequencenumber)
	{
		this.sli_sequencenumber = sli_sequencenumber;
	}
	public String getSli_name()
	{
		return sli_name;
	}
	public void setSli_name(String sli_name)
	{
		this.sli_name = sli_name;
	}
	public String getSli_worknumber()
	{
		return sli_worknumber;
	}
	public void setSli_worknumber(String sli_worknumber)
	{
		this.sli_worknumber = sli_worknumber;
	}
	public String getSli_position()
	{
		return sli_position;
	}
	public void setSli_position(String sli_position)
	{
		this.sli_position = sli_position;
	}
	public String getSli_gender()
	{
		return sli_gender;
	}
	public void setSli_gender(String sli_gender)
	{
		this.sli_gender = sli_gender;
	}
	public Date getSli_birthday()
	{
		return sli_birthday;
	}
	public void setSli_birthday(Date sli_birthday)
	{
		this.sli_birthday = sli_birthday;
	}
	public Date getSli_inschooldate()
	{
		return sli_inschooldate;
	}
	public void setSli_inschooldate(Date sli_inschooldate)
	{
		this.sli_inschooldate = sli_inschooldate;
	}
	public String getSli_education()
	{
		return sli_education;
	}
	public void setSli_education(String sli_education)
	{
		this.sli_education = sli_education;
	}
	public String getSli_degree()
	{
		return sli_degree;
	}
	public void setSli_degree(String sli_degree)
	{
		this.sli_degree = sli_degree;
	}
	public String getSli_professionaltitle()
	{
		return sli_professionaltitle;
	}
	public void setSli_professionaltitle(String sli_professionaltitle)
	{
		this.sli_professionaltitle = sli_professionaltitle;
	}
	public String getSli_responsibility()
	{
		return sli_responsibility;
	}
	public void setSli_responsibility(String sli_responsibility)
	{
		this.sli_responsibility = sli_responsibility;
	}
	public String getSli_studyworkresume()
	{
		return sli_studyworkresume;
	}
	public void setSli_studyworkresume(String sli_studyworkresume)
	{
		this.sli_studyworkresume = sli_studyworkresume;
	}
	public int getSli_serialnumber()
	{
		return sli_serialnumber;
	}
	public void setSli_serialnumber(int sli_serialnumber)
	{
		this.sli_serialnumber = sli_serialnumber;
	}
	public Date getSli_deadline()
	{
		return sli_deadline;
	}
	public void setSli_deadline(Date sli_deadline)
	{
		this.sli_deadline = sli_deadline;
	}
	public String getSli_college()
	{
		return sli_college;
	}
	public void setSli_college(String sli_college)
	{
		this.sli_college = sli_college;
	}
	public String getSli_comments()
	{
		return sli_comments;
	}
	public void setSli_comments(String sli_comments)
	{
		this.sli_comments = sli_comments;
	}
	public int getSli_isnull()
	{
		return sli_isnull;
	}
	public void setSli_isnull(int sli_isnull)
	{
		this.sli_isnull = sli_isnull;
	}
	public SchoolLeaderInfo()
	{
		super();
	}
	
	/**
	 * 全参构造函数
	 * @param sli_id
	 * @param sli_sequencenumber
	 * @param sli_name
	 * @param sli_worknumber
	 * @param sli_position
	 * @param sli_gender
	 * @param sli_birthday
	 * @param sli_inschooldate
	 * @param sli_education
	 * @param sli_degree
	 * @param sli_professionaltitle
	 * @param sli_responsibility
	 * @param sli_studyworkresume
	 * @param sli_serialnumber
	 * @param sli_deadline
	 * @param sli_college
	 * @param sli_comments
	 */
	public SchoolLeaderInfo(int sli_id, Integer sli_sequencenumber, String sli_name, String sli_worknumber,
			String sli_position, String sli_gender, Date sli_birthday, Date sli_inschooldate, String sli_education,
			String sli_degree, String sli_professionaltitle, String sli_responsibility, String sli_studyworkresume,
			int sli_serialnumber, Date sli_deadline, String sli_college, String sli_comments, int sli_isnull)
	{
		super();
		this.sli_id = sli_id;
		this.sli_sequencenumber = sli_sequencenumber;
		this.sli_name = sli_name;
		this.sli_worknumber = sli_worknumber;
		this.sli_position = sli_position;
		this.sli_gender = sli_gender;
		this.sli_birthday = sli_birthday;
		this.sli_inschooldate = sli_inschooldate;
		this.sli_education = sli_education;
		this.sli_degree = sli_degree;
		this.sli_professionaltitle = sli_professionaltitle;
		this.sli_responsibility = sli_responsibility;
		this.sli_studyworkresume = sli_studyworkresume;
		this.sli_serialnumber = sli_serialnumber;
		this.sli_deadline = sli_deadline;
		this.sli_college = sli_college;
		this.sli_comments = sli_comments;
		this.sli_isnull = sli_isnull;
	}
}
