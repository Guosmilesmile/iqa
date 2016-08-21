package cn.edu.xmu.entity;

import java.sql.Date;

/**
 * @author zhantu
 * 学生社团（时点） 实体类
 * date 2015-07-07
 */
public class StudentAssociation {
	//ID
	private Integer sa_id;
	//社团（个）总数
	private Integer sa_sumcount;
	//社团（个）其中：科技类
	private Integer sa_sciencecount;
	//社团（个）人文社会类
	private Integer sa_humanisticcount;
	//社团（个）体育类
	private Integer sa_sportscount;
	//社团（个）文艺类
	private Integer sa_literatureartcount;
	//社团（个）其他
	private Integer sa_othercount;
	//参与人次数（人次）总数
	private Integer sa_sumperson;
	//参与人次数（人次）其中：科技类
	private Integer sa_scienceperson;
	//参与人次数（人次）人文社会类
	private Integer sa_humanisticperson;
	//参与人次数（人次）体育类
	private Integer sa_sportsperson;
	//参与人次数（人次）文艺类
	private Integer sa_literatureartperson;
	//参与人次数（人次）其他
	private Integer sa_otherperson;
	//序列号
	private int sa_serialnumber;	
	//截止日期
	private Date sa_deadline;	
	//所属学院
	private String sa_college;	
	//审核意见
	private String sa_comments;
	//记录是否存在空值
	private int isnull;
	public int getIsnull() {
		return isnull;
	}
	public void setIsnull(int isnull) {
		this.isnull = isnull;
	}
	public Integer getSa_id() {
		return sa_id;
	}
	public void setSa_id(Integer sa_id) {
		this.sa_id = sa_id;
	}
	public Integer getSa_sumcount() {
		return sa_sumcount;
	}
	public void setSa_sumcount(Integer sa_sumcount) {
		this.sa_sumcount = sa_sumcount;
	}
	public Integer getSa_sciencecount() {
		return sa_sciencecount;
	}
	public void setSa_sciencecount(Integer sa_sciencecount) {
		this.sa_sciencecount = sa_sciencecount;
	}
	public Integer getSa_humanisticcount() {
		return sa_humanisticcount;
	}
	public void setSa_humanisticcount(Integer sa_humanisticcount) {
		this.sa_humanisticcount = sa_humanisticcount;
	}
	public Integer getSa_sportscount() {
		return sa_sportscount;
	}
	public void setSa_sportscount(Integer sa_sportscount) {
		this.sa_sportscount = sa_sportscount;
	}
	public Integer getSa_literatureartcount() {
		return sa_literatureartcount;
	}
	public void setSa_literatureartcount(Integer sa_literatureartcount) {
		this.sa_literatureartcount = sa_literatureartcount;
	}
	public Integer getSa_othercount() {
		return sa_othercount;
	}
	public void setSa_othercount(Integer sa_othercount) {
		this.sa_othercount = sa_othercount;
	}
	public Integer getSa_sumperson() {
		return sa_sumperson;
	}
	public void setSa_sumperson(Integer sa_sumperson) {
		this.sa_sumperson = sa_sumperson;
	}
	public Integer getSa_scienceperson() {
		return sa_scienceperson;
	}
	public void setSa_scienceperson(Integer sa_scienceperson) {
		this.sa_scienceperson = sa_scienceperson;
	}
	public Integer getSa_humanisticperson() {
		return sa_humanisticperson;
	}
	public void setSa_humanisticperson(Integer sa_humanisticperson) {
		this.sa_humanisticperson = sa_humanisticperson;
	}
	public Integer getSa_sportsperson() {
		return sa_sportsperson;
	}
	public void setSa_sportsperson(Integer sa_sportsperson) {
		this.sa_sportsperson = sa_sportsperson;
	}
	public Integer getSa_literatureartperson() {
		return sa_literatureartperson;
	}
	public void setSa_literatureartperson(Integer sa_literatureartperson) {
		this.sa_literatureartperson = sa_literatureartperson;
	}
	public Integer getSa_otherperson() {
		return sa_otherperson;
	}
	public void setSa_otherperson(Integer sa_otherperson) {
		this.sa_otherperson = sa_otherperson;
	}
	public int getSa_serialnumber() {
		return sa_serialnumber;
	}
	public void setSa_serialnumber(int sa_serialnumber) {
		this.sa_serialnumber = sa_serialnumber;
	}
	public Date getSa_deadline() {
		return sa_deadline;
	}
	public void setSa_deadline(Date sa_deadline) {
		this.sa_deadline = sa_deadline;
	}
	public String getSa_college() {
		return sa_college;
	}
	public void setSa_college(String sa_college) {
		this.sa_college = sa_college;
	}
	public String getSa_comments() {
		return sa_comments;
	}
	public void setSa_comments(String sa_comments) {
		this.sa_comments = sa_comments;
	}
	public StudentAssociation() {
		super();
	}
	public StudentAssociation(Integer sa_id, Integer sa_sumcount, Integer sa_sciencecount,
			Integer sa_humanisticcount, Integer sa_sportscount,
			Integer sa_literatureartcount, Integer sa_othercount, Integer sa_sumperson,
			Integer sa_scienceperson, Integer sa_humanisticperson, Integer sa_sportsperson,
			Integer sa_literatureartperson, Integer sa_otherperson,
			int sa_serialnumber, Date sa_deadline, String sa_college,
			String sa_comments, int isnull) {
		super();
		this.sa_id = sa_id;
		this.sa_sumcount = sa_sumcount;
		this.sa_sciencecount = sa_sciencecount;
		this.sa_humanisticcount = sa_humanisticcount;
		this.sa_sportscount = sa_sportscount;
		this.sa_literatureartcount = sa_literatureartcount;
		this.sa_othercount = sa_othercount;
		this.sa_sumperson = sa_sumperson;
		this.sa_scienceperson = sa_scienceperson;
		this.sa_humanisticperson = sa_humanisticperson;
		this.sa_sportsperson = sa_sportsperson;
		this.sa_literatureartperson = sa_literatureartperson;
		this.sa_otherperson = sa_otherperson;
		this.sa_serialnumber = sa_serialnumber;
		this.sa_deadline = sa_deadline;
		this.sa_college = sa_college;
		this.sa_comments = sa_comments;
		this.isnull = isnull;
	}
	public StudentAssociation(Integer sa_sumcount, Integer sa_sciencecount,
			Integer sa_humanisticcount, Integer sa_sportscount,
			Integer sa_literatureartcount, Integer sa_othercount, Integer sa_sumperson,
			Integer sa_scienceperson, Integer sa_humanisticperson, Integer sa_sportsperson,
			Integer sa_literatureartperson, Integer sa_otherperson,
			int sa_serialnumber, String sa_college, String sa_comments, int isnull) {
		super();
		this.sa_sumcount = sa_sumcount;
		this.sa_sciencecount = sa_sciencecount;
		this.sa_humanisticcount = sa_humanisticcount;
		this.sa_sportscount = sa_sportscount;
		this.sa_literatureartcount = sa_literatureartcount;
		this.sa_othercount = sa_othercount;
		this.sa_sumperson = sa_sumperson;
		this.sa_scienceperson = sa_scienceperson;
		this.sa_humanisticperson = sa_humanisticperson;
		this.sa_sportsperson = sa_sportsperson;
		this.sa_literatureartperson = sa_literatureartperson;
		this.sa_otherperson = sa_otherperson;
		this.sa_serialnumber = sa_serialnumber;
		this.sa_college = sa_college;
		this.sa_comments = sa_comments;
		this.isnull = isnull;
	}
	public StudentAssociation(Integer sa_id, Integer sa_sumcount, Integer sa_sciencecount,
			Integer sa_humanisticcount, Integer sa_sportscount,
			Integer sa_literatureartcount, Integer sa_othercount, Integer sa_sumperson,
			Integer sa_scienceperson, Integer sa_humanisticperson, Integer sa_sportsperson,
			Integer sa_literatureartperson, Integer sa_otherperson,
			int sa_serialnumber, String sa_comments, int isnull) {
		super();
		this.sa_id = sa_id;
		this.sa_sumcount = sa_sumcount;
		this.sa_sciencecount = sa_sciencecount;
		this.sa_humanisticcount = sa_humanisticcount;
		this.sa_sportscount = sa_sportscount;
		this.sa_literatureartcount = sa_literatureartcount;
		this.sa_othercount = sa_othercount;
		this.sa_sumperson = sa_sumperson;
		this.sa_scienceperson = sa_scienceperson;
		this.sa_humanisticperson = sa_humanisticperson;
		this.sa_sportsperson = sa_sportsperson;
		this.sa_literatureartperson = sa_literatureartperson;
		this.sa_otherperson = sa_otherperson;
		this.sa_serialnumber = sa_serialnumber;
		this.sa_comments = sa_comments;
		this.isnull = isnull;
	}
	public StudentAssociation(Integer sa_id, Integer sa_sumcount, Integer sa_sciencecount,
			Integer sa_humanisticcount, Integer sa_sportscount,
			Integer sa_literatureartcount, Integer sa_othercount, Integer sa_sumperson,
			Integer sa_scienceperson, Integer sa_humanisticperson, Integer sa_sportsperson,
			Integer sa_literatureartperson, Integer sa_otherperson, String sa_comments, int isnull) {
		super();
		this.sa_id = sa_id;
		this.sa_sumcount = sa_sumcount;
		this.sa_sciencecount = sa_sciencecount;
		this.sa_humanisticcount = sa_humanisticcount;
		this.sa_sportscount = sa_sportscount;
		this.sa_literatureartcount = sa_literatureartcount;
		this.sa_othercount = sa_othercount;
		this.sa_sumperson = sa_sumperson;
		this.sa_scienceperson = sa_scienceperson;
		this.sa_humanisticperson = sa_humanisticperson;
		this.sa_sportsperson = sa_sportsperson;
		this.sa_literatureartperson = sa_literatureartperson;
		this.sa_otherperson = sa_otherperson;
		this.sa_comments = sa_comments;
		this.isnull = isnull;
	}
	public StudentAssociation(Integer sa_sumcount, Integer sa_sciencecount,
			Integer sa_humanisticcount, Integer sa_sportscount,
			Integer sa_literatureartcount, Integer sa_othercount, Integer sa_sumperson,
			Integer sa_scienceperson, Integer sa_humanisticperson, Integer sa_sportsperson,
			Integer sa_literatureartperson, Integer sa_otherperson, String sa_college, int isnull) {
		super();
		this.sa_sumcount = sa_sumcount;
		this.sa_sciencecount = sa_sciencecount;
		this.sa_humanisticcount = sa_humanisticcount;
		this.sa_sportscount = sa_sportscount;
		this.sa_literatureartcount = sa_literatureartcount;
		this.sa_othercount = sa_othercount;
		this.sa_sumperson = sa_sumperson;
		this.sa_scienceperson = sa_scienceperson;
		this.sa_humanisticperson = sa_humanisticperson;
		this.sa_sportsperson = sa_sportsperson;
		this.sa_literatureartperson = sa_literatureartperson;
		this.sa_otherperson = sa_otherperson;
		this.sa_college = sa_college;
		this.isnull = isnull;
	}

	
	
}
