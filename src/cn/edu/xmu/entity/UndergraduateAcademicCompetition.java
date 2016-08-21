package cn.edu.xmu.entity;

import java.sql.Date;

/**
 * 附表6-2-1-1   2014-2015学年本科生参加学科竞赛情况（省部级及以上）
 * @author chunfeng 
 *
 */
public class UndergraduateAcademicCompetition {
     private int uac_id;
     private String uac_collegename; //学院
     private String uac_competitionname; //竞赛名称
     private String uac_awardgrade; //奖项等级
     private String uac_prizelevel; //获奖级别
     private String uac_personalorteam; //个人、团队
     private String uac_studentname; //获奖学生姓名
     private Integer uac_studentnum; //获奖学生人数
     private String uac_windate; //获奖时间（年、月）
     
     private String uac_college;
     private Date uac_deadline;
     private int uac_serialnumber;
     private String uac_comments;
     private int uac_isnull;
     
     public UndergraduateAcademicCompetition(int uac_id, String uac_collegename, String uac_competitionname, String uac_awardgrade,
              String uac_prizelevel, String uac_personalorteam, String uac_studentname, Integer uac_studentnum, String uac_windate,
              String uac_college, Date uac_deadline, int uac_serialnumber, String uac_comments, int uac_isnull){
    	 this.uac_id = uac_id;
    	 this.uac_collegename = uac_collegename; //学院
    	 this.uac_competitionname = uac_competitionname; //竞赛名称
    	 this.uac_awardgrade = uac_awardgrade; //奖项等级
    	 this.uac_prizelevel = uac_prizelevel; //获奖级别
    	 this.uac_personalorteam = uac_personalorteam; //个人、团队
    	 this.uac_studentname = uac_studentname; //获奖学生姓名
    	 this.uac_studentnum = uac_studentnum; //获奖学生人数
    	 this.uac_windate = uac_windate; //获奖时间（年、月）
         
    	 this.uac_college = uac_college;
    	 this.uac_deadline = uac_deadline;
    	 this.uac_serialnumber = uac_serialnumber;
    	 this.uac_comments = uac_comments; 
    	 this.uac_isnull = uac_isnull;
     }
     
     public UndergraduateAcademicCompetition(String uac_collegename, String uac_competitionname, String uac_awardgrade,
             String uac_prizelevel, String uac_personalorteam, String uac_studentname, Integer uac_studentnum, String uac_windate,
             String uac_college, int uac_serialnumber, int uac_isnull){
    	 this.uac_collegename = uac_collegename; //学院
    	 this.uac_competitionname = uac_competitionname; //竞赛名称
    	 this.uac_awardgrade = uac_awardgrade; //奖项等级
    	 this.uac_prizelevel = uac_prizelevel; //获奖级别
    	 this.uac_personalorteam = uac_personalorteam; //个人、团队
    	 this.uac_studentname = uac_studentname; //获奖学生姓名
    	 this.uac_studentnum = uac_studentnum; //获奖学生人数
    	 this.uac_windate = uac_windate; //获奖时间（年、月）
           	 
    	 this.uac_college = uac_college; 
    	 this.uac_serialnumber = uac_serialnumber;
    	 this.uac_isnull = uac_isnull;
     }

	public int getUac_id() {
		return uac_id;
	}
	public void setUac_id(int uac_id) {
		this.uac_id = uac_id;
	}
	public String getUac_college() {
		return uac_college;
	}
	public void setUac_college(String uac_college) {
		this.uac_college = uac_college;
	}
	public String getUac_competitionname() {
		return uac_competitionname;
	}
	public void setUac_competitionname(String uac_competitionname) {
		this.uac_competitionname = uac_competitionname;
	}
	public String getUac_awardgrade() {
		return uac_awardgrade;
	}
	public void setUac_awardgrade(String uac_awardgrade) {
		this.uac_awardgrade = uac_awardgrade;
	}
	public String getUac_prizelevel() {
		return uac_prizelevel;
	}
	public void setUac_prizelevel(String uac_prizelevel) {
		this.uac_prizelevel = uac_prizelevel;
	}
	public String getUac_personalorteam() {
		return uac_personalorteam;
	}
	public void setUac_personalorteam(String uac_personalorteam) {
		this.uac_personalorteam = uac_personalorteam;
	}
	public String getUac_studentname() {
		return uac_studentname;
	}
	public void setUac_studentname(String uac_studentname) {
		this.uac_studentname = uac_studentname;
	}
	public Integer getUac_studentnum() {
		return uac_studentnum;
	}
	public void setUac_studentnum(Integer uac_studentnum) {
		this.uac_studentnum = uac_studentnum;
	}
	public String getUac_windate() {
		return uac_windate;
	}
	public void setUac_windate(String uac_windate) {
		this.uac_windate = uac_windate;
	}
	public Date getUac_deadline() {
		return uac_deadline;
	}
	public void setUac_deadline(Date uac_deadline) {
		this.uac_deadline = uac_deadline;
	}
	public int getUac_serialnumber() {
		return uac_serialnumber;
	}
	public void setUac_serialnumber(int uac_serialnumber) {
		this.uac_serialnumber = uac_serialnumber;
	}
	public String getUac_comments() {
		return uac_comments;
	}
	public void setUac_comments(String uac_comments) {
		this.uac_comments = uac_comments;
	}

	public int getUac_isnull() {
		return uac_isnull;
	}

	public void setUac_isnull(int uac_isnull) {
		this.uac_isnull = uac_isnull;
	}

	public String getUac_collegename() {
		return uac_collegename;
	}

	public void setUac_collegename(String uac_collegename) {
		this.uac_collegename = uac_collegename;
	}
     
     
}
