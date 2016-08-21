package cn.edu.xmu.entity;

import java.sql.Date;

/**
 * 附表6-2-1-2  2014-2015学年本科生参加本科生创新活动、技能竞赛情况（省部级及以上）
 * @author chunfeng
 *
 */
public class UndergraduateInnovationAndSkillCompetition {
	private int uiasc_id;
    private String uiasc_collegename; //学院
    private String uiasc_competitionname; //竞赛名称
    private String uiasc_awardgrade; //奖项等级
    private String uiasc_prizelevel; //获奖级别
    private String uiasc_personalorteam; //个人、团队
    private String uiasc_studentname; //获奖学生姓名
    private Integer uiasc_studentnum; //获奖学生人数
    private String uiasc_windate; //获奖时间（年、月）
    
    private String uiasc_college;
    private Date uiasc_deadline;
    private int uiasc_serialnumber;
    private String uiasc_comments;
    private int uiasc_isnull;
    
    public UndergraduateInnovationAndSkillCompetition(int uiasc_id, String uiasc_collegename, String uiasc_competitionname, String uiasc_awardgrade,
            String uiasc_prizelevel, String uiasc_personalorteam, String uiasc_studentname, Integer uiasc_studentnum, String uiasc_windate,
            String uiasc_college, Date uiasc_deadline, int uiasc_serialnumber, String uiasc_comments, int uiasc_isnull){
  	 this.uiasc_id = uiasc_id;
  	 this.uiasc_collegename = uiasc_collegename; //学院
  	 this.uiasc_competitionname = uiasc_competitionname; //竞赛名称
  	 this.uiasc_awardgrade = uiasc_awardgrade; //奖项等级
  	 this.uiasc_prizelevel = uiasc_prizelevel; //获奖级别
  	 this.uiasc_personalorteam = uiasc_personalorteam; //个人、团队
  	 this.uiasc_studentname = uiasc_studentname; //获奖学生姓名
  	 this.uiasc_studentnum = uiasc_studentnum; //获奖学生人数
  	 this.uiasc_windate = uiasc_windate; //获奖时间（年、月）
       
  	 this.uiasc_college = uiasc_college; 
  	 this.uiasc_deadline = uiasc_deadline;
  	 this.uiasc_serialnumber = uiasc_serialnumber;
  	 this.uiasc_comments = uiasc_comments; 
  	 this.uiasc_isnull = uiasc_isnull;
   }
   
   public UndergraduateInnovationAndSkillCompetition(String uiasc_collegename, String uiasc_competitionname, String uiasc_awardgrade,
           String uiasc_prizelevel, String uiasc_personalorteam, String uiasc_studentname, Integer uiasc_studentnum, String uiasc_windate,
           String uiasc_college, int uiasc_serialnumber, int uiasc_isnull){
  	 this.uiasc_collegename = uiasc_collegename; //学院
  	 this.uiasc_competitionname = uiasc_competitionname; //竞赛名称
  	 this.uiasc_awardgrade = uiasc_awardgrade; //奖项等级
  	 this.uiasc_prizelevel = uiasc_prizelevel; //获奖级别
  	 this.uiasc_personalorteam = uiasc_personalorteam; //个人、团队
  	 this.uiasc_studentname = uiasc_studentname; //获奖学生姓名
  	 this.uiasc_studentnum = uiasc_studentnum; //获奖学生人数
  	 this.uiasc_windate = uiasc_windate; //获奖时间（年、月）
         	 
  	this.uiasc_college = uiasc_college; 
  	 this.uiasc_serialnumber = uiasc_serialnumber;
  	 this.uiasc_isnull = uiasc_isnull;
   }

public int getUiasc_id() {
	return uiasc_id;
}

public void setUiasc_id(int uiasc_id) {
	this.uiasc_id = uiasc_id;
}

public String getUiasc_college() {
	return uiasc_college;
}

public void setUiasc_college(String uiasc_college) {
	this.uiasc_college = uiasc_college;
}

public String getUiasc_competitionname() {
	return uiasc_competitionname;
}

public void setUiasc_competitionname(String uiasc_competitionname) {
	this.uiasc_competitionname = uiasc_competitionname;
}

public String getUiasc_awardgrade() {
	return uiasc_awardgrade;
}

public void setUiasc_awardgrade(String uiasc_awardgrade) {
	this.uiasc_awardgrade = uiasc_awardgrade;
}

public String getUiasc_prizelevel() {
	return uiasc_prizelevel;
}

public void setUiasc_prizelevel(String uiasc_prizelevel) {
	this.uiasc_prizelevel = uiasc_prizelevel;
}

public String getUiasc_personalorteam() {
	return uiasc_personalorteam;
}

public void setUiasc_personalorteam(String uiasc_personalorteam) {
	this.uiasc_personalorteam = uiasc_personalorteam;
}

public String getUiasc_studentname() {
	return uiasc_studentname;
}

public void setUiasc_studentname(String uiasc_studentname) {
	this.uiasc_studentname = uiasc_studentname;
}

public Integer getUiasc_studentnum() {
	return uiasc_studentnum;
}

public void setUiasc_studentnum(Integer uiasc_studentnum) {
	this.uiasc_studentnum = uiasc_studentnum;
}

public String getUiasc_windate() {
	return uiasc_windate;
}

public void setUiasc_windate(String uiasc_windate) {
	this.uiasc_windate = uiasc_windate;
}

public Date getUiasc_deadline() {
	return uiasc_deadline;
}

public void setUiasc_deadline(Date uiasc_deadline) {
	this.uiasc_deadline = uiasc_deadline;
}

public int getUiasc_serialnumber() {
	return uiasc_serialnumber;
}

public void setUiasc_serialnumber(int uiasc_serialnumber) {
	this.uiasc_serialnumber = uiasc_serialnumber;
}

public String getUiasc_comments() {
	return uiasc_comments;
}

public void setUiasc_comments(String uiasc_comments) {
	this.uiasc_comments = uiasc_comments;
}

public int getUiasc_isnull() {
	return uiasc_isnull;
}

public void setUiasc_isnull(int uiasc_isnull) {
	this.uiasc_isnull = uiasc_isnull;
}

public String getUiasc_collegename() {
	return uiasc_collegename;
}

public void setUiasc_collegename(String uiasc_collegename) {
	this.uiasc_collegename = uiasc_collegename;
}
   
   
}
