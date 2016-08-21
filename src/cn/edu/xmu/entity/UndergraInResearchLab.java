package cn.edu.xmu.entity;

import java.sql.Date;

public class UndergraInResearchLab
{
	private int uirl_id;// 主键
	private String uirl_institute;// 学院
	private String uirl_major;// 专业
	private String uirl_grade;// 年级
	private String uirl_name;// 姓名
	private Integer uirl_days;// 天数
	private Integer uirl_times;// 次数
	private Float uirl_totalhours;// 累计时间（小时）
	private String uirl_laboratoryname;// 科研实验室名称
	private String uirl_laboratorydirector;// 科研实验室负责人
	private String uirl_tutor;// 科研项目指导教师
	private String uirl_tutortitle;// 科研项目指导教师职称
	private String uirl_ispartiresearchproject;// 科研项目是否参与教师科研项目
	private String uirl_researchprojectname;// 科研项目名称
	private String uirl_researchprojectlevel;// 科研项目级别
	private String uirl_createprojectname;// 本科生主持或参与科创项目名称
	private String uirl_createprojecttype;// 本科生主持或参与科创项目类型
	private String uirl_createprojectlevel;// 本科生主持或参与科创项目级别
	private String uirl_paper;// 论文
	private String uirl_patent;// 专利
	private String uirl_note;// 备注
	private int uirl_serialnumber;// 序列号
	private Date uirl_deadline;// 截止日期
	private String uirl_college;// 所属学院
	private String uirl_comments;// 审核意见
	private int uirl_isnull;// 记录中是否存在空字段，0为完整 1为存在空字段

	public int getUirl_id()
	{
		return uirl_id;
	}

	public void setUirl_id(int uirl_id)
	{
		this.uirl_id = uirl_id;
	}

	public String getUirl_institute()
	{
		return uirl_institute;
	}

	public void setUirl_institute(String uirl_institute)
	{
		this.uirl_institute = uirl_institute;
	}

	public String getUirl_major()
	{
		return uirl_major;
	}

	public void setUirl_major(String uirl_major)
	{
		this.uirl_major = uirl_major;
	}

	public String getUirl_grade()
	{
		return uirl_grade;
	}

	public void setUirl_grade(String uirl_grade)
	{
		this.uirl_grade = uirl_grade;
	}

	public String getUirl_name()
	{
		return uirl_name;
	}

	public void setUirl_name(String uirl_name)
	{
		this.uirl_name = uirl_name;
	}

	public Integer getUirl_days()
	{
		return uirl_days;
	}

	public void setUirl_days(Integer uirl_days)
	{
		this.uirl_days = uirl_days;
	}

	public Integer getUirl_times()
	{
		return uirl_times;
	}

	public void setUirl_times(Integer uirl_times)
	{
		this.uirl_times = uirl_times;
	}

	public Float getUirl_totalhours()
	{
		return uirl_totalhours;
	}

	public void setUirl_totalhours(Float uirl_totalhours)
	{
		this.uirl_totalhours = uirl_totalhours;
	}

	public String getUirl_laboratoryname()
	{
		return uirl_laboratoryname;
	}

	public void setUirl_laboratoryname(String uirl_laboratoryname)
	{
		this.uirl_laboratoryname = uirl_laboratoryname;
	}

	public String getUirl_laboratorydirector()
	{
		return uirl_laboratorydirector;
	}

	public void setUirl_laboratorydirector(String uirl_laboratorydirector)
	{
		this.uirl_laboratorydirector = uirl_laboratorydirector;
	}

	public String getUirl_tutor()
	{
		return uirl_tutor;
	}

	public void setUirl_tutor(String uirl_tutor)
	{
		this.uirl_tutor = uirl_tutor;
	}

	public String getUirl_tutortitle()
	{
		return uirl_tutortitle;
	}

	public void setUirl_tutortitle(String uirl_tutortitle)
	{
		this.uirl_tutortitle = uirl_tutortitle;
	}

	public String getUirl_ispartiresearchproject()
	{
		return uirl_ispartiresearchproject;
	}

	public void setUirl_ispartiresearchproject(String uirl_ispartiresearchproject)
	{
		this.uirl_ispartiresearchproject = uirl_ispartiresearchproject;
	}

	public String getUirl_researchprojectname()
	{
		return uirl_researchprojectname;
	}

	public void setUirl_researchprojectname(String uirl_researchprojectname)
	{
		this.uirl_researchprojectname = uirl_researchprojectname;
	}

	public String getUirl_researchprojectlevel()
	{
		return uirl_researchprojectlevel;
	}

	public void setUirl_researchprojectlevel(String uirl_researchprojectlevel)
	{
		this.uirl_researchprojectlevel = uirl_researchprojectlevel;
	}

	public String getUirl_createprojectname()
	{
		return uirl_createprojectname;
	}

	public void setUirl_createprojectname(String uirl_createprojectname)
	{
		this.uirl_createprojectname = uirl_createprojectname;
	}

	public String getUirl_createprojecttype()
	{
		return uirl_createprojecttype;
	}

	public void setUirl_createprojecttype(String uirl_createprojecttype)
	{
		this.uirl_createprojecttype = uirl_createprojecttype;
	}

	public String getUirl_createprojectlevel()
	{
		return uirl_createprojectlevel;
	}

	public void setUirl_createprojectlevel(String uirl_createprojectlevel)
	{
		this.uirl_createprojectlevel = uirl_createprojectlevel;
	}

	public String getUirl_paper()
	{
		return uirl_paper;
	}

	public void setUirl_paper(String uirl_paper)
	{
		this.uirl_paper = uirl_paper;
	}

	public String getUirl_patent()
	{
		return uirl_patent;
	}

	public void setUirl_patent(String uirl_patent)
	{
		this.uirl_patent = uirl_patent;
	}

	public String getUirl_note()
	{
		return uirl_note;
	}

	public void setUirl_note(String uirl_note)
	{
		this.uirl_note = uirl_note;
	}

	public int getUirl_serialnumber()
	{
		return uirl_serialnumber;
	}

	public void setUirl_serialnumber(int uirl_serialnumber)
	{
		this.uirl_serialnumber = uirl_serialnumber;
	}

	public Date getUirl_deadline()
	{
		return uirl_deadline;
	}

	public void setUirl_deadline(Date uirl_deadline)
	{
		this.uirl_deadline = uirl_deadline;
	}

	public String getUirl_college()
	{
		return uirl_college;
	}

	public void setUirl_college(String uirl_college)
	{
		this.uirl_college = uirl_college;
	}

	public String getUirl_comments()
	{
		return uirl_comments;
	}

	public void setUirl_comments(String uirl_comments)
	{
		this.uirl_comments = uirl_comments;
	}

	public int getUirl_isnull()
	{
		return uirl_isnull;
	}

	public void setUirl_isnull(int uirl_isnull)
	{
		this.uirl_isnull = uirl_isnull;
	}

	public UndergraInResearchLab()
	{
		super();
	}

	// 全参构造函数
	public UndergraInResearchLab(int uirl_id, String uirl_institute, String uirl_major, String uirl_grade,
			String uirl_name, Integer uirl_days, Integer uirl_times, Float uirl_totalhours,
			String uirl_laboratoryname, String uirl_laboratorydirector, String uirl_tutor, String uirl_tutortitle,
			String uirl_ispartiresearchproject, String uirl_researchprojectname, String uirl_researchprojectlevel,
			String uirl_createprojectname, String uirl_createprojecttype, String uirl_createprojectlevel,
			String uirl_paper, String uirl_patent, String uirl_note, int uirl_serialnumber, Date uirl_deadline,
			String uirl_college, String uirl_comments, int uirl_isnull)
	{
		super();
		this.uirl_id = uirl_id;
		this.uirl_institute = uirl_institute;
		this.uirl_major = uirl_major;
		this.uirl_grade = uirl_grade;
		this.uirl_name = uirl_name;
		this.uirl_days = uirl_days;
		this.uirl_times = uirl_times;
		this.uirl_totalhours = uirl_totalhours;
		this.uirl_laboratoryname = uirl_laboratoryname;
		this.uirl_laboratorydirector = uirl_laboratorydirector;
		this.uirl_tutor = uirl_tutor;
		this.uirl_tutortitle = uirl_tutortitle;
		this.uirl_ispartiresearchproject = uirl_ispartiresearchproject;
		this.uirl_researchprojectname = uirl_researchprojectname;
		this.uirl_researchprojectlevel = uirl_researchprojectlevel;
		this.uirl_createprojectname = uirl_createprojectname;
		this.uirl_createprojecttype = uirl_createprojecttype;
		this.uirl_createprojectlevel = uirl_createprojectlevel;
		this.uirl_paper = uirl_paper;
		this.uirl_patent = uirl_patent;
		this.uirl_note = uirl_note;
		this.uirl_serialnumber = uirl_serialnumber;
		this.uirl_deadline = uirl_deadline;
		this.uirl_college = uirl_college;
		this.uirl_comments = uirl_comments;
		this.uirl_isnull = uirl_isnull;
	}

}
