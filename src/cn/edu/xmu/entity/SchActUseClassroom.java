package cn.edu.xmu.entity;

import java.sql.Date;
/**
 * 
 * @author xiaoping 数据报表2-3-1全校性实际使用的教室（时点） date 2015-7-6
 *
 */
public class SchActUseClassroom
{
	private int sauc_id;//主键
	private String sauc_region;//校区
	private String sauc_site;//地点
	private Integer  sauc_subtotalroom;//小计间数
	private Integer sauc_subtotalseat;//小计座位数
	private Integer sauc_multiroom;//多媒体教室间数
	private Integer sauc_multiseat;//多媒体教室座位数
	private Integer sauc_regularroom;//普通教室间数
	private Integer  sauc_regularseat;//普通教室座位数
	private Integer sauc_computerroom;//计算机房间数
	private Integer sauc_computerseat;//计算机房座位数
	private Integer sauc_voiceroom;//语音教室间数
	private Integer sauc_voiceseat;//语音教室座位数
	private Integer  sauc_otherroom;//其他类型间数
	private Integer  sauc_otherseat;//其他类型座位数
	private Integer sauc_serialnumber;//序列号
	private Date sauc_deadline;//截止日期
	private String sauc_college;//所属学院
	private String sauc_comments;//审核意见
	private int sauc_isnull;//记录中是否存在空字段，0为完整   1为存在空字段
	public int getSauc_id()
	{
		return sauc_id;
	}
	public void setSauc_id(int sauc_id)
	{
		this.sauc_id = sauc_id;
	}
	public String getSauc_region()
	{
		return sauc_region;
	}
	public void setSauc_region(String sauc_region)
	{
		this.sauc_region = sauc_region;
	}
	public String getSauc_site()
	{
		return sauc_site;
	}
	public void setSauc_site(String sauc_site)
	{
		this.sauc_site = sauc_site;
	}
	public Integer getSauc_subtotalroom()
	{
		return sauc_subtotalroom;
	}
	public void setSauc_subtotalroom(Integer sauc_subtotalroom)
	{
		this.sauc_subtotalroom = sauc_subtotalroom;
	}
	public Integer getSauc_subtotalseat()
	{
		return sauc_subtotalseat;
	}
	public void setSauc_subtotalseat(Integer sauc_subtotalseat)
	{
		this.sauc_subtotalseat = sauc_subtotalseat;
	}
	public Integer getSauc_multiroom()
	{
		return sauc_multiroom;
	}
	public void setSauc_multiroom(Integer sauc_multiroom)
	{
		this.sauc_multiroom = sauc_multiroom;
	}
	public Integer getSauc_multiseat()
	{
		return sauc_multiseat;
	}
	public void setSauc_multiseat(Integer sauc_multiseat)
	{
		this.sauc_multiseat = sauc_multiseat;
	}
	public Integer getSauc_regularroom()
	{
		return sauc_regularroom;
	}
	public void setSauc_regularroom(Integer sauc_regularroom)
	{
		this.sauc_regularroom = sauc_regularroom;
	}
	public Integer getSauc_regularseat()
	{
		return sauc_regularseat;
	}
	public void setSauc_regularseat(Integer sauc_regularseat)
	{
		this.sauc_regularseat = sauc_regularseat;
	}
	public Integer getSauc_computerroom()
	{
		return sauc_computerroom;
	}
	public void setSauc_computerroom(Integer sauc_computerroom)
	{
		this.sauc_computerroom = sauc_computerroom;
	}
	public Integer getSauc_computerseat()
	{
		return sauc_computerseat;
	}
	public void setSauc_computerseat(Integer sauc_computerseat)
	{
		this.sauc_computerseat = sauc_computerseat;
	}
	public Integer getSauc_voiceroom()
	{
		return sauc_voiceroom;
	}
	public void setSauc_voiceroom(Integer sauc_voiceroom)
	{
		this.sauc_voiceroom = sauc_voiceroom;
	}
	public Integer getSauc_voiceseat()
	{
		return sauc_voiceseat;
	}
	public void setSauc_voiceseat(Integer sauc_voiceseat)
	{
		this.sauc_voiceseat = sauc_voiceseat;
	}
	public Integer getSauc_otherroom()
	{
		return sauc_otherroom;
	}
	public void setSauc_otherroom(Integer sauc_otherroom)
	{
		this.sauc_otherroom = sauc_otherroom;
	}
	public Integer getSauc_otherseat()
	{
		return sauc_otherseat;
	}
	public void setSauc_otherseat(Integer sauc_otherseat)
	{
		this.sauc_otherseat = sauc_otherseat;
	}
	public int getSauc_serialnumber()
	{
		return sauc_serialnumber;
	}
	public void setSauc_serialnumber(int sauc_serialnumber)
	{
		this.sauc_serialnumber = sauc_serialnumber;
	}
	public Date getSauc_deadline()
	{
		return sauc_deadline;
	}
	public void setSauc_deadline(Date sauc_deadline)
	{
		this.sauc_deadline = sauc_deadline;
	}
	public String getSauc_college()
	{
		return sauc_college;
	}
	public void setSauc_college(String sauc_college)
	{
		this.sauc_college = sauc_college;
	}
	public String getSauc_comments()
	{
		return sauc_comments;
	}
	public void setSauc_comments(String sauc_comments)
	{
		this.sauc_comments = sauc_comments;
	}
	public int getSauc_isnull()
	{
		return sauc_isnull;
	}
	public void setSauc_isnull(int sauc_isnull)
	{
		this.sauc_isnull = sauc_isnull;
	}
	public SchActUseClassroom()
	{
		super();
	}
	//全参构造函数
	public SchActUseClassroom(int sauc_id, String sauc_region, String sauc_site, Integer sauc_subtotalroom,
			Integer sauc_subtotalseat, Integer sauc_multiroom, Integer sauc_multiseat, Integer sauc_regularroom, Integer sauc_regularseat,
			Integer sauc_computerroom, Integer sauc_computerseat, Integer sauc_voiceroom, Integer sauc_voiceseat, Integer sauc_otherroom,
			Integer sauc_otherseat, int sauc_serialnumber, Date sauc_deadline, String sauc_college, String sauc_comments, int sauc_isnull)
	{
		super();
		this.sauc_id = sauc_id;
		this.sauc_region = sauc_region;
		this.sauc_site = sauc_site;
		this.sauc_subtotalroom = sauc_subtotalroom;
		this.sauc_subtotalseat = sauc_subtotalseat;
		this.sauc_multiroom = sauc_multiroom;
		this.sauc_multiseat = sauc_multiseat;
		this.sauc_regularroom = sauc_regularroom;
		this.sauc_regularseat = sauc_regularseat;
		this.sauc_computerroom = sauc_computerroom;
		this.sauc_computerseat = sauc_computerseat;
		this.sauc_voiceroom = sauc_voiceroom;
		this.sauc_voiceseat = sauc_voiceseat;
		this.sauc_otherroom = sauc_otherroom;
		this.sauc_otherseat = sauc_otherseat;
		this.sauc_serialnumber = sauc_serialnumber;
		this.sauc_deadline = sauc_deadline;
		this.sauc_college = sauc_college;
		this.sauc_comments = sauc_comments;
		this.sauc_isnull = sauc_isnull;
	}
	
}
