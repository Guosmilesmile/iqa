package cn.edu.xmu.entity;

import java.sql.Date;

/**
 * 
 * @author lee 数据报表3-1-1 专任教师基本信息表 date 2015-8-5
 * 数据报表分析
 *
 */
public class FullTimeTeacherInfoAna
{
	private int ftti_id;

	// 姓名
	private String ftti_name;

	// 工号
	private String ftti_worknumber;

	// 性别
	private String ftti_gender;

	// 出生年月
	private Date ftti_birthday;

	// 入校时间
	private Date ftti_inschooldate;

	// 任职状态
	private String ftti_workstate;

	// 单位号
	private String ftti_departmentnumber;

	// 单位名称
	private String ftti_departmentname;

	// 学历
	private String ftti_education;

	// 最高学位
	private String ftti_degree;

	// 学缘
	private String ftti_educationsource;

	// 专业技术职称
	private String ftti_professionaltitle;

	// 学科类别
	private String ftti_subjectcategory;

	// 是否双师型
	private String ftti_ifdoublequalifiedteacher;

	// 是否具有工程背景
	private String ftti_ifengineeringbackground;

	// 是否具有行业背景
	private String ftti_ifindustrybackground;

	// 导师类别
	private String ftti_tutortype;

	// 序列号
	private int ftti_serialnumber;

	// 截止日期
	private Date ftti_deadline;

	// 所属学院
	private String ftti_college;

	// 审核意见
	private String ftti_comments;
	
	private int ftti_isnull;//记录是否存在控制，0为完整，1为存在空值

	public int getFtti_id()
	{
		return ftti_id;
	}

	public void setFtti_id(int ftti_id)
	{
		this.ftti_id = ftti_id;
	}

	public String getFtti_name()
	{
		return ftti_name;
	}

	public void setFtti_name(String ftti_name)
	{
		this.ftti_name = ftti_name;
	}

	public String getFtti_worknumber()
	{
		return ftti_worknumber;
	}

	public void setFtti_worknumber(String ftti_worknumber)
	{
		this.ftti_worknumber = ftti_worknumber;
	}

	public String getFtti_gender()
	{
		return ftti_gender;
	}

	public void setFtti_gender(String ftti_gender)
	{
		this.ftti_gender = ftti_gender;
	}

	public Date getFtti_birthday()
	{
		return ftti_birthday;
	}

	public void setFtti_birthday(Date ftti_birthday)
	{
		this.ftti_birthday = ftti_birthday;
	}

	public Date getFtti_inschooldate()
	{
		return ftti_inschooldate;
	}

	public void setFtti_inschooldate(Date ftti_inschooldate)
	{
		this.ftti_inschooldate = ftti_inschooldate;
	}

	public String getFtti_workstate()
	{
		return ftti_workstate;
	}

	public void setFtti_workstate(String ftti_workstate)
	{
		this.ftti_workstate = ftti_workstate;
	}

	public String getFtti_departmentnumber()
	{
		return ftti_departmentnumber;
	}

	public void setFtti_departmentnumber(String ftti_departmentnumber)
	{
		this.ftti_departmentnumber = ftti_departmentnumber;
	}

	public String getFtti_departmentname()
	{
		return ftti_departmentname;
	}

	public void setFtti_departmentname(String ftti_departmentname)
	{
		this.ftti_departmentname = ftti_departmentname;
	}

	public String getFtti_education()
	{
		return ftti_education;
	}

	public void setFtti_education(String ftti_education)
	{
		this.ftti_education = ftti_education;
	}

	public String getFtti_degree()
	{
		return ftti_degree;
	}

	public void setFtti_degree(String ftti_degree)
	{
		this.ftti_degree = ftti_degree;
	}

	public String getFtti_educationsource()
	{
		return ftti_educationsource;
	}

	public void setFtti_educationsource(String ftti_educationsource)
	{
		this.ftti_educationsource = ftti_educationsource;
	}

	public String getFtti_professionaltitle()
	{
		return ftti_professionaltitle;
	}

	public void setFtti_professionaltitle(String ftti_professionaltitle)
	{
		this.ftti_professionaltitle = ftti_professionaltitle;
	}

	public String getFtti_subjectcategory()
	{
		return ftti_subjectcategory;
	}

	public void setFtti_subjectcategory(String ftti_subjectcategory)
	{
		this.ftti_subjectcategory = ftti_subjectcategory;
	}

	public String getFtti_ifdoublequalifiedteacher()
	{
		return ftti_ifdoublequalifiedteacher;
	}

	public void setFtti_ifdoublequalifiedteacher(String ftti_ifdoublequalifiedteacher)
	{
		this.ftti_ifdoublequalifiedteacher = ftti_ifdoublequalifiedteacher;
	}

	public String getFtti_ifengineeringbackground()
	{
		return ftti_ifengineeringbackground;
	}

	public void setFtti_ifengineeringbackground(String ftti_ifengineeringbackground)
	{
		this.ftti_ifengineeringbackground = ftti_ifengineeringbackground;
	}

	public String getFtti_ifindustrybackground()
	{
		return ftti_ifindustrybackground;
	}

	public void setFtti_ifindustrybackground(String ftti_ifindustrybackground)
	{
		this.ftti_ifindustrybackground = ftti_ifindustrybackground;
	}

	public String getFtti_tutortype()
	{
		return ftti_tutortype;
	}

	public void setFtti_tutortype(String ftti_tutortype)
	{
		this.ftti_tutortype = ftti_tutortype;
	}

	public int getFtti_serialnumber()
	{
		return ftti_serialnumber;
	}

	public void setFtti_serialnumber(int ftti_serialnumber)
	{
		this.ftti_serialnumber = ftti_serialnumber;
	}

	public Date getFtti_deadline()
	{
		return ftti_deadline;
	}

	public void setFtti_deadline(Date ftti_deadline)
	{
		this.ftti_deadline = ftti_deadline;
	}

	public String getFtti_college()
	{
		return ftti_college;
	}

	public void setFtti_college(String ftti_college)
	{
		this.ftti_college = ftti_college;
	}

	public String getFtti_comments()
	{
		return ftti_comments;
	}

	public void setFtti_comments(String ftti_comments)
	{
		this.ftti_comments = ftti_comments;
	}

	public FullTimeTeacherInfoAna()
	{
		super();
	}

	public FullTimeTeacherInfoAna(int ftti_id, String ftti_name, String ftti_worknumber, String ftti_gender,
			Date ftti_birthday, Date ftti_inschooldate, String ftti_workstate, String ftti_departmentnumber,
			String ftti_departmentname, String ftti_education, String ftti_degree, String ftti_educationsource,
			String ftti_professionaltitle, String ftti_subjectcategory, String ftti_ifdoublequalifiedteacher,
			String ftti_ifengineeringbackground, String ftti_ifindustrybackground, String ftti_tutortype,
			int ftti_serialnumber, Date ftti_deadline, String ftti_college, String ftti_comments, int ftti_isnull)
	{
		super();
		this.ftti_id = ftti_id;
		this.ftti_name = ftti_name;
		this.ftti_worknumber = ftti_worknumber;
		this.ftti_gender = ftti_gender;
		this.ftti_birthday = ftti_birthday;
		this.ftti_inschooldate = ftti_inschooldate;
		this.ftti_workstate = ftti_workstate;
		this.ftti_departmentnumber = ftti_departmentnumber;
		this.ftti_departmentname = ftti_departmentname;
		this.ftti_education = ftti_education;
		this.ftti_degree = ftti_degree;
		this.ftti_educationsource = ftti_educationsource;
		this.ftti_professionaltitle = ftti_professionaltitle;
		this.ftti_subjectcategory = ftti_subjectcategory;
		this.ftti_ifdoublequalifiedteacher = ftti_ifdoublequalifiedteacher;
		this.ftti_ifengineeringbackground = ftti_ifengineeringbackground;
		this.ftti_ifindustrybackground = ftti_ifindustrybackground;
		this.ftti_tutortype = ftti_tutortype;
		this.ftti_serialnumber = ftti_serialnumber;
		this.ftti_deadline = ftti_deadline;
		this.ftti_college = ftti_college;
		this.ftti_comments = ftti_comments;
		this.ftti_isnull = ftti_isnull;
	}

	public int getFtti_isnull()
	{
		return ftti_isnull;
	}

	public void setFtti_isnull(int ftti_isnull)
	{
		this.ftti_isnull = ftti_isnull;
	}
	
}
