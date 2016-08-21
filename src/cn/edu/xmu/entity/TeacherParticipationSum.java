package cn.edu.xmu.entity;

import java.sql.Date;

/**
 * 
 * @author xiaoping 数据报表 附表3-5-1-3 教师参加院级及以上教学竞赛情况汇总表（自然年） date 2015-7-8
 *
 */
public class TeacherParticipationSum
{

	private int tps_id;// 主键
	private String tps_particollege;// 参赛学院
	private Integer tps_schskillcompecourtyardnum;// 校级青年教师教学技能比赛院级选拔参赛人数
	private Integer tps_schskillcompeschoolnum;// 校级青年教师教学技能比赛校级比赛参赛人数
	private Integer tps_schskillcompespecialnum;// 校级青年教师教学技能比赛特等奖人数
	private Integer tps_schskillcompefirstnum;// 校级青年教师教学技能比赛一等奖人数
	private Integer tps_schskillcompesecnum;// 校级青年教师教学技能比赛二等奖人数
	private Integer tps_provinskillcompepartinum;// 省级青年教师教学技能比赛参赛人数
	private Integer tps_provinskillcompespecialnum;// 省级青年教师教学技能比赛特等奖人数
	private Integer tps_provinskillcompefirstnum;// 省级青年教师教学技能比赛一等奖人数
	private Integer tps_provinskillcompesecnum;// 省级青年教师教学技能比赛二等奖人数
	private Integer tps_countryskillcompepartinum;// 全国青年教师教学技能比赛参赛人数
	private Integer tps_countryskillcompespecialnum;// 全国青年教师教学技能比赛特等奖人数
	private Integer tps_countryskillcompefirstnum;// 全国青年教师教学技能比赛一等奖人数
	private Integer tps_countryskillcompesecnum;// 全国青年教师教学技能比赛二等奖人数
	private Integer tps_countrymicrocompepartinum;// 全国微课教学比赛参赛人数
	private Integer tps_countrymicrocompespecialnum;// 全国微课教学比赛特等奖人数
	private Integer tps_countrymicrocompefirstnum;// 全国微课教学比赛一等奖人数
	private Integer tps_countrymicrocompesecnum;// 全国微课教学比赛二等奖人数
	private String tps_preparer;// 填表人
	private int tps_serialnumber;// 序列号
	private Date tps_deadline;// 截止日期
	private String tps_college;// 所属学院
	private String tps_comments;// 审核意见
	private int tps_isnull;//记录中是否存在空字段，0为完整   1为存在空字段
	public int getTps_id()
	{
		return tps_id;
	}
	public void setTps_id(int tps_id)
	{
		this.tps_id = tps_id;
	}
	public String getTps_particollege()
	{
		return tps_particollege;
	}
	public void setTps_particollege(String tps_particollege)
	{
		this.tps_particollege = tps_particollege;
	}
	public Integer getTps_schskillcompecourtyardnum()
	{
		return tps_schskillcompecourtyardnum;
	}
	public void setTps_schskillcompecourtyardnum(Integer tps_schskillcompecourtyardnum)
	{
		this.tps_schskillcompecourtyardnum = tps_schskillcompecourtyardnum;
	}
	public Integer getTps_schskillcompeschoolnum()
	{
		return tps_schskillcompeschoolnum;
	}
	public void setTps_schskillcompeschoolnum(Integer tps_schskillcompeschoolnum)
	{
		this.tps_schskillcompeschoolnum = tps_schskillcompeschoolnum;
	}
	public Integer getTps_schskillcompespecialnum()
	{
		return tps_schskillcompespecialnum;
	}
	public void setTps_schskillcompespecialnum(Integer tps_schskillcompespecialnum)
	{
		this.tps_schskillcompespecialnum = tps_schskillcompespecialnum;
	}
	public Integer getTps_schskillcompefirstnum()
	{
		return tps_schskillcompefirstnum;
	}
	public void setTps_schskillcompefirstnum(Integer tps_schskillcompefirstnum)
	{
		this.tps_schskillcompefirstnum = tps_schskillcompefirstnum;
	}
	public Integer getTps_schskillcompesecnum()
	{
		return tps_schskillcompesecnum;
	}
	public void setTps_schskillcompesecnum(Integer tps_schskillcompesecnum)
	{
		this.tps_schskillcompesecnum = tps_schskillcompesecnum;
	}
	public Integer getTps_provinskillcompepartinum()
	{
		return tps_provinskillcompepartinum;
	}
	public void setTps_provinskillcompepartinum(Integer tps_provinskillcompepartinum)
	{
		this.tps_provinskillcompepartinum = tps_provinskillcompepartinum;
	}
	public Integer getTps_provinskillcompespecialnum()
	{
		return tps_provinskillcompespecialnum;
	}
	public void setTps_provinskillcompespecialnum(Integer tps_provinskillcompespecialnum)
	{
		this.tps_provinskillcompespecialnum = tps_provinskillcompespecialnum;
	}
	public Integer getTps_provinskillcompefirstnum()
	{
		return tps_provinskillcompefirstnum;
	}
	public void setTps_provinskillcompefirstnum(Integer tps_provinskillcompefirstnum)
	{
		this.tps_provinskillcompefirstnum = tps_provinskillcompefirstnum;
	}
	public Integer getTps_provinskillcompesecnum()
	{
		return tps_provinskillcompesecnum;
	}
	public void setTps_provinskillcompesecnum(Integer tps_provinskillcompesecnum)
	{
		this.tps_provinskillcompesecnum = tps_provinskillcompesecnum;
	}
	public Integer getTps_countryskillcompepartinum()
	{
		return tps_countryskillcompepartinum;
	}
	public void setTps_countryskillcompepartinum(Integer tps_countryskillcompepartinum)
	{
		this.tps_countryskillcompepartinum = tps_countryskillcompepartinum;
	}
	public Integer getTps_countryskillcompespecialnum()
	{
		return tps_countryskillcompespecialnum;
	}
	public void setTps_countryskillcompespecialnum(Integer tps_countryskillcompespecialnum)
	{
		this.tps_countryskillcompespecialnum = tps_countryskillcompespecialnum;
	}
	public Integer getTps_countryskillcompefirstnum()
	{
		return tps_countryskillcompefirstnum;
	}
	public void setTps_countryskillcompefirstnum(Integer tps_countryskillcompefirstnum)
	{
		this.tps_countryskillcompefirstnum = tps_countryskillcompefirstnum;
	}
	public Integer getTps_countryskillcompesecnum()
	{
		return tps_countryskillcompesecnum;
	}
	public void setTps_countryskillcompesecnum(Integer tps_countryskillcompesecnum)
	{
		this.tps_countryskillcompesecnum = tps_countryskillcompesecnum;
	}
	public Integer getTps_countrymicrocompepartinum()
	{
		return tps_countrymicrocompepartinum;
	}
	public void setTps_countrymicrocompepartinum(Integer tps_countrymicrocompepartinum)
	{
		this.tps_countrymicrocompepartinum = tps_countrymicrocompepartinum;
	}
	public Integer getTps_countrymicrocompespecialnum()
	{
		return tps_countrymicrocompespecialnum;
	}
	public void setTps_countrymicrocompespecialnum(Integer tps_countrymicrocompespecialnum)
	{
		this.tps_countrymicrocompespecialnum = tps_countrymicrocompespecialnum;
	}
	public Integer getTps_countrymicrocompefirstnum()
	{
		return tps_countrymicrocompefirstnum;
	}
	public void setTps_countrymicrocompefirstnum(Integer tps_countrymicrocompefirstnum)
	{
		this.tps_countrymicrocompefirstnum = tps_countrymicrocompefirstnum;
	}
	public Integer getTps_countrymicrocompesecnum()
	{
		return tps_countrymicrocompesecnum;
	}
	public void setTps_countrymicrocompesecnum(Integer tps_countrymicrocompesecnum)
	{
		this.tps_countrymicrocompesecnum = tps_countrymicrocompesecnum;
	}
	public String getTps_preparer()
	{
		return tps_preparer;
	}
	public void setTps_preparer(String tps_preparer)
	{
		this.tps_preparer = tps_preparer;
	}
	public int getTps_serialnumber()
	{
		return tps_serialnumber;
	}
	public void setTps_serialnumber(int tps_serialnumber)
	{
		this.tps_serialnumber = tps_serialnumber;
	}
	public Date getTps_deadline()
	{
		return tps_deadline;
	}
	public void setTps_deadline(Date tps_deadline)
	{
		this.tps_deadline = tps_deadline;
	}
	public String getTps_college()
	{
		return tps_college;
	}
	public void setTps_college(String tps_college)
	{
		this.tps_college = tps_college;
	}
	public String getTps_comments()
	{
		return tps_comments;
	}
	public void setTps_comments(String tps_comments)
	{
		this.tps_comments = tps_comments;
	}
	public int getTps_isnull()
	{
		return tps_isnull;
	}
	public void setTps_isnull(int tps_isnull)
	{
		this.tps_isnull = tps_isnull;
	}
	public TeacherParticipationSum()
	{
		super();
	}
	//全参构造函数
	public TeacherParticipationSum(int tps_id, String tps_particollege, Integer tps_schskillcompecourtyardnum,
			Integer tps_schskillcompeschoolnum, Integer tps_schskillcompespecialnum, Integer tps_schskillcompefirstnum,
			Integer tps_schskillcompesecnum, Integer tps_provinskillcompepartinum, Integer tps_provinskillcompespecialnum,
			Integer tps_provinskillcompefirstnum, Integer tps_provinskillcompesecnum, Integer tps_countryskillcompepartinum,
			Integer tps_countryskillcompespecialnum, Integer tps_countryskillcompefirstnum, Integer tps_countryskillcompesecnum,
			Integer tps_countrymicrocompepartinum, Integer tps_countrymicrocompespecialnum, Integer tps_countrymicrocompefirstnum,
			Integer tps_countrymicrocompesecnum, String tps_preparer, int tps_serialnumber, Date tps_deadline,
			String tps_college, String tps_comments, int tps_isnull)
	{
		super();
		this.tps_id = tps_id;
		this.tps_particollege = tps_particollege;
		this.tps_schskillcompecourtyardnum = tps_schskillcompecourtyardnum;
		this.tps_schskillcompeschoolnum = tps_schskillcompeschoolnum;
		this.tps_schskillcompespecialnum = tps_schskillcompespecialnum;
		this.tps_schskillcompefirstnum = tps_schskillcompefirstnum;
		this.tps_schskillcompesecnum = tps_schskillcompesecnum;
		this.tps_provinskillcompepartinum = tps_provinskillcompepartinum;
		this.tps_provinskillcompespecialnum = tps_provinskillcompespecialnum;
		this.tps_provinskillcompefirstnum = tps_provinskillcompefirstnum;
		this.tps_provinskillcompesecnum = tps_provinskillcompesecnum;
		this.tps_countryskillcompepartinum = tps_countryskillcompepartinum;
		this.tps_countryskillcompespecialnum = tps_countryskillcompespecialnum;
		this.tps_countryskillcompefirstnum = tps_countryskillcompefirstnum;
		this.tps_countryskillcompesecnum = tps_countryskillcompesecnum;
		this.tps_countrymicrocompepartinum = tps_countrymicrocompepartinum;
		this.tps_countrymicrocompespecialnum = tps_countrymicrocompespecialnum;
		this.tps_countrymicrocompefirstnum = tps_countrymicrocompefirstnum;
		this.tps_countrymicrocompesecnum = tps_countrymicrocompesecnum;
		this.tps_preparer = tps_preparer;
		this.tps_serialnumber = tps_serialnumber;
		this.tps_deadline = tps_deadline;
		this.tps_college = tps_college;
		this.tps_comments = tps_comments;
		this.tps_isnull = tps_isnull;
	}
	
}
