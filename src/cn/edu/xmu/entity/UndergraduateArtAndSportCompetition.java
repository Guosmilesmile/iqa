package cn.edu.xmu.entity;

import java.sql.Date;

/**
 * 附表6-2-1-3   2014-2015学年本科生参加文艺、体育竞赛情况（省部级及以上）
 * @author chunfeng
 *
 */
public class UndergraduateArtAndSportCompetition {
	private int uaasc_id;
    private String uaasc_collegename; //学院
    private String uaasc_competitionname; //竞赛名称
    private String uaasc_competitioncategory; //竞赛类型
    private String uaasc_awardgrade; //奖项等级
    private String uaasc_prizelevel; //获奖级别
    private String uaasc_personalorteam; //个人、团队
    private String uaasc_studentname; //获奖学生姓名
    private Integer uaasc_studentnum; //获奖学生人数
    private String uaasc_windate; //获奖时间（年、月）
    
    private String uaasc_college;
    private Date uaasc_deadline;
    private int uaasc_serialnumber;
    private String uaasc_comments;
    private int uaasc_isnull;
    
    public UndergraduateArtAndSportCompetition(int uaasc_id, String uaasc_collegename, String uaasc_competitionname, String uaasc_competitioncategory,
    		String uaasc_awardgrade, String uaasc_prizelevel, String uaasc_personalorteam, String uaasc_studentname, 
    		Integer uaasc_studentnum, String uaasc_windate, String uaasc_college, Date uaasc_deadline, int uaasc_serialnumber, String uaasc_comments, int uaasc_isnull){
  	 this.uaasc_id = uaasc_id;
  	 this.uaasc_collegename = uaasc_collegename; //学院
  	 this.uaasc_competitionname = uaasc_competitionname; //竞赛名称
  	 this.uaasc_competitioncategory = uaasc_competitioncategory;
  	 this.uaasc_awardgrade = uaasc_awardgrade; //奖项等级
  	 this.uaasc_prizelevel = uaasc_prizelevel; //获奖级别
  	 this.uaasc_personalorteam = uaasc_personalorteam; //个人、团队
  	 this.uaasc_studentname = uaasc_studentname; //获奖学生姓名
  	 this.uaasc_studentnum = uaasc_studentnum; //获奖学生人数
  	 this.uaasc_windate = uaasc_windate; //获奖时间（年、月）
       
  	 this.uaasc_college = uaasc_college;
  	 this.uaasc_deadline = uaasc_deadline;
  	 this.uaasc_serialnumber = uaasc_serialnumber;
  	 this.uaasc_comments = uaasc_comments; 
  	 this.uaasc_isnull = uaasc_isnull;
   }
   
   public UndergraduateArtAndSportCompetition(String uaasc_collegename, String uaasc_competitionname, String uaasc_competitioncategory, String uaasc_awardgrade,
           String uaasc_prizelevel, String uaasc_personalorteam, String uaasc_studentname, Integer uaasc_studentnum, String uaasc_windate,
           String uaasc_college,int uaasc_serialnumber, int uaasc_isnull){
  	 this.uaasc_collegename = uaasc_collegename; //学院
  	 this.uaasc_competitionname = uaasc_competitionname; //竞赛名称
     this.uaasc_competitioncategory = uaasc_competitioncategory;
  	 this.uaasc_awardgrade = uaasc_awardgrade; //奖项等级
  	 this.uaasc_prizelevel = uaasc_prizelevel; //获奖级别
  	 this.uaasc_personalorteam = uaasc_personalorteam; //个人、团队
  	 this.uaasc_studentname = uaasc_studentname; //获奖学生姓名
  	 this.uaasc_studentnum = uaasc_studentnum; //获奖学生人数
  	 this.uaasc_windate = uaasc_windate; //获奖时间（年、月）
         	 
  	 this.uaasc_college = uaasc_college;
  	 this.uaasc_serialnumber = uaasc_serialnumber;
  	 this.uaasc_isnull = uaasc_isnull;
   }

public int getUaasc_id() {
	return uaasc_id;
}

public void setUaasc_id(int uaasc_id) {
	this.uaasc_id = uaasc_id;
}

public String getUaasc_college() {
	return uaasc_college;
}

public void setUaasc_college(String uaasc_college) {
	this.uaasc_college = uaasc_college;
}

public String getUaasc_competitionname() {
	return uaasc_competitionname;
}

public void setUaasc_competitionname(String uaasc_competitionname) {
	this.uaasc_competitionname = uaasc_competitionname;
}

public String getUaasc_competitioncategory() {
	return uaasc_competitioncategory;
}

public void setUaasc_competitioncategory(String uaasc_competitioncategory) {
	this.uaasc_competitioncategory = uaasc_competitioncategory;
}

public String getUaasc_awardgrade() {
	return uaasc_awardgrade;
}

public void setUaasc_awardgrade(String uaasc_awardgrade) {
	this.uaasc_awardgrade = uaasc_awardgrade;
}

public String getUaasc_prizelevel() {
	return uaasc_prizelevel;
}

public void setUaasc_prizelevel(String uaasc_prizelevel) {
	this.uaasc_prizelevel = uaasc_prizelevel;
}

public String getUaasc_personalorteam() {
	return uaasc_personalorteam;
}

public void setUaasc_personalorteam(String uaasc_personalorteam) {
	this.uaasc_personalorteam = uaasc_personalorteam;
}

public String getUaasc_studentname() {
	return uaasc_studentname;
}

public void setUaasc_studentname(String uaasc_studentname) {
	this.uaasc_studentname = uaasc_studentname;
}

public Integer getUaasc_studentnum() {
	return uaasc_studentnum;
}

public void setUaasc_studentnum(Integer uaasc_studentnum) {
	this.uaasc_studentnum = uaasc_studentnum;
}

public String getUaasc_windate() {
	return uaasc_windate;
}

public void setUaasc_windate(String uaasc_windate) {
	this.uaasc_windate = uaasc_windate;
}

public Date getUaasc_deadline() {
	return uaasc_deadline;
}

public void setUaasc_deadline(Date uaasc_deadline) {
	this.uaasc_deadline = uaasc_deadline;
}

public int getUaasc_serialnumber() {
	return uaasc_serialnumber;
}

public void setUaasc_serialnumber(int uaasc_serialnumber) {
	this.uaasc_serialnumber = uaasc_serialnumber;
}

public String getUaasc_comments() {
	return uaasc_comments;
}

public void setUaasc_comments(String uaasc_comments) {
	this.uaasc_comments = uaasc_comments;
}

public int getUaasc_isnull() {
	return uaasc_isnull;
}

public void setUaasc_isnull(int uaasc_isnull) {
	this.uaasc_isnull = uaasc_isnull;
}

public String getUaasc_collegename() {
	return uaasc_collegename;
}

public void setUaasc_collegename(String uaasc_collegename) {
	this.uaasc_collegename = uaasc_collegename;
}
   
   
}
