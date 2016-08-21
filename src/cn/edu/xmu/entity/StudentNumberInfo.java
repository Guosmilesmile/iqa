package cn.edu.xmu.entity;

import java.sql.Date;

/**
 * 
 * @author xiaoping 数据报表6-1-1 学生数量基本情况（时点）date 2015-7-5
 *
 */
public class StudentNumberInfo
{
	private int sni_id;// 主键
	private String sni_stuinfobaselink;// 学生信息库链接
	private Integer sni_ordiundergrastu;// 普通本科生人数
	private Integer sni_countryoverseastu;// 普通本科生中：与国（境）外大学联合培养的学生数
	private Integer sni_highervocationstu;// 普通高职（含专科）学生数（人）
	private Integer sni_postgraduate;// 硕士研究生数（人）
	private Integer sni_fulltimepost;// 硕士研究生中：全日制硕士研究生
	private Integer sni_nofulltimepost;// 硕士研究生中：非全日制硕士研究生
	private Integer sni_doctorcandidate;// 博士研究生数（人）
	private Integer sni_fulltimedoct;// 博士研究生中：全日制博士研究生
	private Integer sni_nofulltimedoct;// 博士研究生中：非全日制博士研究生
	private Integer sni_abroadstu;// 留学生数（人）
	private Integer sni_ordipreppie;// 普通预科生数（人）
	private Integer sni_advancedstu;// 进修生数（人）
	private Integer sni_adultfulltimestu;// 成人脱产学生数（人）
	private Integer sni_parttimestu;// 夜大（业余）学生数（人）
	private Integer sni_correspondencestu;// 函授学生数（人）
	private Integer sni_networkstu;// 网络学生数（人）
	private Integer sni_selftaughtstu;// 自考学生数（人）
	private int sni_serialnumber;// 序列号
	private Date sni_deadline;// 截止日期
	private String sni_college;// 所属学院
	private String sni_comments;// 审核意见
	private int sni_isnull;//记录是否存在空字段，0完整，1存在空字段
	public int getSni_id()
	{
		return sni_id;
	}
	public void setSni_id(int sni_id)
	{
		this.sni_id = sni_id;
	}
	public String getSni_stuinfobaselink()
	{
		return sni_stuinfobaselink;
	}
	public void setSni_stuinfobaselink(String sni_stuinfobaselink)
	{
		this.sni_stuinfobaselink = sni_stuinfobaselink;
	}
	public Integer getSni_ordiundergrastu()
	{
		return sni_ordiundergrastu;
	}
	public void setSni_ordiundergrastu(Integer sni_ordiundergrastu)
	{
		this.sni_ordiundergrastu = sni_ordiundergrastu;
	}
	public Integer getSni_countryoverseastu()
	{
		return sni_countryoverseastu;
	}
	public void setSni_countryoverseastu(Integer sni_countryoverseastu)
	{
		this.sni_countryoverseastu = sni_countryoverseastu;
	}
	public Integer getSni_highervocationstu()
	{
		return sni_highervocationstu;
	}
	public void setSni_highervocationstu(Integer sni_highervocationstu)
	{
		this.sni_highervocationstu = sni_highervocationstu;
	}
	public Integer getSni_postgraduate()
	{
		return sni_postgraduate;
	}
	public void setSni_postgraduate(Integer sni_postgraduate)
	{
		this.sni_postgraduate = sni_postgraduate;
	}
	public Integer getSni_fulltimepost()
	{
		return sni_fulltimepost;
	}
	public void setSni_fulltimepost(Integer sni_fulltimepost)
	{
		this.sni_fulltimepost = sni_fulltimepost;
	}
	public Integer getSni_nofulltimepost()
	{
		return sni_nofulltimepost;
	}
	public void setSni_nofulltimepost(Integer sni_nofulltimepost)
	{
		this.sni_nofulltimepost = sni_nofulltimepost;
	}
	public Integer getSni_doctorcandidate()
	{
		return sni_doctorcandidate;
	}
	public void setSni_doctorcandidate(Integer sni_doctorcandidate)
	{
		this.sni_doctorcandidate = sni_doctorcandidate;
	}
	public Integer getSni_fulltimedoct()
	{
		return sni_fulltimedoct;
	}
	public void setSni_fulltimedoct(Integer sni_fulltimedoct)
	{
		this.sni_fulltimedoct = sni_fulltimedoct;
	}
	public Integer getSni_nofulltimedoct()
	{
		return sni_nofulltimedoct;
	}
	public void setSni_nofulltimedoct(Integer sni_nofulltimedoct)
	{
		this.sni_nofulltimedoct = sni_nofulltimedoct;
	}
	public Integer getSni_abroadstu()
	{
		return sni_abroadstu;
	}
	public void setSni_abroadstu(Integer sni_abroadstu)
	{
		this.sni_abroadstu = sni_abroadstu;
	}
	public Integer getSni_ordipreppie()
	{
		return sni_ordipreppie;
	}
	public void setSni_ordipreppie(Integer sni_ordipreppie)
	{
		this.sni_ordipreppie = sni_ordipreppie;
	}
	public Integer getSni_advancedstu()
	{
		return sni_advancedstu;
	}
	public void setSni_advancedstu(Integer sni_advancedstu)
	{
		this.sni_advancedstu = sni_advancedstu;
	}
	public Integer getSni_adultfulltimestu()
	{
		return sni_adultfulltimestu;
	}
	public void setSni_adultfulltimestu(Integer sni_adultfulltimestu)
	{
		this.sni_adultfulltimestu = sni_adultfulltimestu;
	}
	public Integer getSni_parttimestu()
	{
		return sni_parttimestu;
	}
	public void setSni_parttimestu(Integer sni_parttimestu)
	{
		this.sni_parttimestu = sni_parttimestu;
	}
	public Integer getSni_correspondencestu()
	{
		return sni_correspondencestu;
	}
	public void setSni_correspondencestu(Integer sni_correspondencestu)
	{
		this.sni_correspondencestu = sni_correspondencestu;
	}
	public Integer getSni_networkstu()
	{
		return sni_networkstu;
	}
	public void setSni_networkstu(Integer sni_networkstu)
	{
		this.sni_networkstu = sni_networkstu;
	}
	public Integer getSni_selftaughtstu()
	{
		return sni_selftaughtstu;
	}
	public void setSni_selftaughtstu(Integer sni_selftaughtstu)
	{
		this.sni_selftaughtstu = sni_selftaughtstu;
	}
	public int getSni_serialnumber()
	{
		return sni_serialnumber;
	}
	public void setSni_serialnumber(int sni_serialnumber)
	{
		this.sni_serialnumber = sni_serialnumber;
	}
	public Date getSni_deadline()
	{
		return sni_deadline;
	}
	public void setSni_deadline(Date sni_deadline)
	{
		this.sni_deadline = sni_deadline;
	}
	public String getSni_college()
	{
		return sni_college;
	}
	public void setSni_college(String sni_college)
	{
		this.sni_college = sni_college;
	}
	public String getSni_comments()
	{
		return sni_comments;
	}
	public void setSni_comments(String sni_comments)
	{
		this.sni_comments = sni_comments;
	}
	public int getSni_isnull()
	{
		return sni_isnull;
	}
	public void setSni_isnull(int sni_isnull)
	{
		this.sni_isnull = sni_isnull;
	}
	public StudentNumberInfo()
	{
		super();
	}
	/**
	 * 全参构造函数
	 * @param sni_id
	 * @param sni_stuinfobaselink
	 * @param sni_ordiundergrastu
	 * @param sni_countryoverseastu
	 * @param sni_highervocationstu
	 * @param sni_postgraduate
	 * @param sni_fulltimepost
	 * @param sni_nofulltimepost
	 * @param sni_doctorcandidate
	 * @param sni_fulltimedoct
	 * @param sni_nofulltimedoct
	 * @param sni_abroadstu
	 * @param sni_ordipreppie
	 * @param sni_advancedstu
	 * @param sni_adultfulltimestu
	 * @param sni_parttimestu
	 * @param sni_correspondencestu
	 * @param sni_networkstu
	 * @param sni_selftaughtstu
	 * @param sni_serialnumber
	 * @param sni_deadline
	 * @param sni_college
	 * @param sni_comments
	 */
	public StudentNumberInfo(int sni_id, String sni_stuinfobaselink, Integer sni_ordiundergrastu, Integer sni_countryoverseastu,
			Integer sni_highervocationstu, Integer sni_postgraduate, Integer sni_fulltimepost, Integer sni_nofulltimepost,
			Integer sni_doctorcandidate, Integer sni_fulltimedoct, Integer sni_nofulltimedoct, Integer sni_abroadstu,
			Integer sni_ordipreppie, Integer sni_advancedstu, Integer sni_adultfulltimestu, Integer sni_parttimestu,
			Integer sni_correspondencestu, Integer sni_networkstu, Integer sni_selftaughtstu, int sni_serialnumber,
			Date sni_deadline, String sni_college, String sni_comments, int sni_isnull)
	{
		super();
		this.sni_id = sni_id;
		this.sni_stuinfobaselink = sni_stuinfobaselink;
		this.sni_ordiundergrastu = sni_ordiundergrastu;
		this.sni_countryoverseastu = sni_countryoverseastu;
		this.sni_highervocationstu = sni_highervocationstu;
		this.sni_postgraduate = sni_postgraduate;
		this.sni_fulltimepost = sni_fulltimepost;
		this.sni_nofulltimepost = sni_nofulltimepost;
		this.sni_doctorcandidate = sni_doctorcandidate;
		this.sni_fulltimedoct = sni_fulltimedoct;
		this.sni_nofulltimedoct = sni_nofulltimedoct;
		this.sni_abroadstu = sni_abroadstu;
		this.sni_ordipreppie = sni_ordipreppie;
		this.sni_advancedstu = sni_advancedstu;
		this.sni_adultfulltimestu = sni_adultfulltimestu;
		this.sni_parttimestu = sni_parttimestu;
		this.sni_correspondencestu = sni_correspondencestu;
		this.sni_networkstu = sni_networkstu;
		this.sni_selftaughtstu = sni_selftaughtstu;
		this.sni_serialnumber = sni_serialnumber;
		this.sni_deadline = sni_deadline;
		this.sni_college = sni_college;
		this.sni_comments = sni_comments;
		this.sni_isnull = sni_isnull;
	}
}
