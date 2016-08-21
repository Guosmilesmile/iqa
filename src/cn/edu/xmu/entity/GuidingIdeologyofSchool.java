package cn.edu.xmu.entity;

import java.sql.Date;

/**
 * @author zhantu
 * 办学指导思想(时点)  实体类
 * date 2015-07-09
 */
public class GuidingIdeologyofSchool {
	//ID
	private int gis_id;
	//1.校训 内容
	private String gis_mottocontent;
	//1.校训 备注
	private String gis_mottoremark;
	//2.定位与发展目标 内容
	private String gis_positiongoalcontent;
	//2.定位与发展目标 备注
	private String gis_positiongoalremark;
	//发展战略规划文件上传
	private String gis_strategy;
	//学科建设规划文件上传
	private String gis_discipline;
	//专业建设发展规划文件上传
	private String gis_professional;
	//师资队伍建设规划文件上传
	private String gis_teacher;
	//序列号
	private int gis_serialnumber;
	//截止日期
	private Date gis_deadline;
	//填报学院
	private String gis_college;
	//审核意见
	private String gis_comments;
	//记录是否存在空值
	private int isnull;
	public int getIsnull() {
		return isnull;
	}
	public void setIsnull(int isnull) {
		this.isnull = isnull;
	}
	public int getGis_id() {
		return gis_id;
	}
	public void setGis_id(int gis_id) {
		this.gis_id = gis_id;
	}
	public String getGis_mottocontent() {
		return gis_mottocontent;
	}
	public void setGis_mottocontent(String gis_mottocontent) {
		this.gis_mottocontent = gis_mottocontent;
	}
	public String getGis_mottoremark() {
		return gis_mottoremark;
	}
	public void setGis_mottoremark(String gis_mottoremark) {
		this.gis_mottoremark = gis_mottoremark;
	}
	public String getGis_positiongoalcontent() {
		return gis_positiongoalcontent;
	}
	public void setGis_positiongoalcontent(String gis_positiongoalcontent) {
		this.gis_positiongoalcontent = gis_positiongoalcontent;
	}
	public String getGis_positiongoalremark() {
		return gis_positiongoalremark;
	}
	public void setGis_positiongoalremark(String gis_positiongoalremark) {
		this.gis_positiongoalremark = gis_positiongoalremark;
	}
	public String getGis_strategy() {
		return gis_strategy;
	}
	public void setGis_strategy(String gis_strategy) {
		this.gis_strategy = gis_strategy;
	}
	public String getGis_discipline() {
		return gis_discipline;
	}
	public void setGis_discipline(String gis_discipline) {
		this.gis_discipline = gis_discipline;
	}
	public String getGis_professional() {
		return gis_professional;
	}
	public void setGis_professional(String gis_professional) {
		this.gis_professional = gis_professional;
	}
	public String getGis_teacher() {
		return gis_teacher;
	}
	public void setGis_teacher(String gis_teacher) {
		this.gis_teacher = gis_teacher;
	}
	public int getGis_serialnumber() {
		return gis_serialnumber;
	}
	public void setGis_serialnumber(int gis_serialnumber) {
		this.gis_serialnumber = gis_serialnumber;
	}
	public Date getGis_deadline() {
		return gis_deadline;
	}
	public void setGis_deadline(Date gis_deadline) {
		this.gis_deadline = gis_deadline;
	}
	public String getGis_college() {
		return gis_college;
	}
	public void setGis_college(String gis_college) {
		this.gis_college = gis_college;
	}
	public String getGis_comments() {
		return gis_comments;
	}
	public void setGis_comments(String gis_comments) {
		this.gis_comments = gis_comments;
	}
	public GuidingIdeologyofSchool() {
		super();
	}
	public GuidingIdeologyofSchool(int gis_id, String gis_mottocontent,
			String gis_mottoremark, String gis_positiongoalcontent,
			String gis_positiongoalremark, String gis_strategy,
			String gis_discipline, String gis_professional, String gis_teacher,
			int gis_serialnumber, Date gis_deadline, String gis_college,
			String gis_comments, int isnull) {
		super();
		this.gis_id = gis_id;
		this.gis_mottocontent = gis_mottocontent;
		this.gis_mottoremark = gis_mottoremark;
		this.gis_positiongoalcontent = gis_positiongoalcontent;
		this.gis_positiongoalremark = gis_positiongoalremark;
		this.gis_strategy = gis_strategy;
		this.gis_discipline = gis_discipline;
		this.gis_professional = gis_professional;
		this.gis_teacher = gis_teacher;
		this.gis_serialnumber = gis_serialnumber;
		this.gis_deadline = gis_deadline;
		this.gis_college = gis_college;
		this.gis_comments = gis_comments;
		this.isnull = isnull;
	}
	public GuidingIdeologyofSchool(String gis_mottocontent,
			String gis_mottoremark, String gis_positiongoalcontent,
			String gis_positiongoalremark, String gis_strategy,
			String gis_discipline, String gis_professional, String gis_teacher,
			int gis_serialnumber, String gis_college, String gis_comments, int isnull) {
		super();
		this.gis_mottocontent = gis_mottocontent;
		this.gis_mottoremark = gis_mottoremark;
		this.gis_positiongoalcontent = gis_positiongoalcontent;
		this.gis_positiongoalremark = gis_positiongoalremark;
		this.gis_strategy = gis_strategy;
		this.gis_discipline = gis_discipline;
		this.gis_professional = gis_professional;
		this.gis_teacher = gis_teacher;
		this.gis_serialnumber = gis_serialnumber;
		this.gis_college = gis_college;
		this.gis_comments = gis_comments;
		this.isnull = isnull;
	}
	public GuidingIdeologyofSchool(int gis_id, String gis_mottocontent,
			String gis_mottoremark, String gis_positiongoalcontent,
			String gis_positiongoalremark, String gis_strategy,
			String gis_discipline, String gis_professional, String gis_teacher,
			int gis_serialnumber, String gis_comments, int isnull) {
		super();
		this.gis_id = gis_id;
		this.gis_mottocontent = gis_mottocontent;
		this.gis_mottoremark = gis_mottoremark;
		this.gis_positiongoalcontent = gis_positiongoalcontent;
		this.gis_positiongoalremark = gis_positiongoalremark;
		this.gis_strategy = gis_strategy;
		this.gis_discipline = gis_discipline;
		this.gis_professional = gis_professional;
		this.gis_teacher = gis_teacher;
		this.gis_serialnumber = gis_serialnumber;
		this.gis_comments = gis_comments;
		this.isnull = isnull;
	}
	public GuidingIdeologyofSchool(int gis_id, String gis_mottocontent,
			String gis_mottoremark, String gis_positiongoalcontent,
			String gis_positiongoalremark, String gis_strategy,
			String gis_discipline, String gis_professional, String gis_teacher,
			String gis_comments, int isnull) {
		super();
		this.gis_id = gis_id;
		this.gis_mottocontent = gis_mottocontent;
		this.gis_mottoremark = gis_mottoremark;
		this.gis_positiongoalcontent = gis_positiongoalcontent;
		this.gis_positiongoalremark = gis_positiongoalremark;
		this.gis_strategy = gis_strategy;
		this.gis_discipline = gis_discipline;
		this.gis_professional = gis_professional;
		this.gis_teacher = gis_teacher;
		this.gis_comments = gis_comments;
		this.isnull = isnull;
	}
	public GuidingIdeologyofSchool(String gis_mottocontent,
			String gis_mottoremark, String gis_positiongoalcontent,
			String gis_positiongoalremark, String gis_strategy,
			String gis_discipline, String gis_professional, String gis_teacher,
			String gis_college, int isnull) {
		super();
		this.gis_mottocontent = gis_mottocontent;
		this.gis_mottoremark = gis_mottoremark;
		this.gis_positiongoalcontent = gis_positiongoalcontent;
		this.gis_positiongoalremark = gis_positiongoalremark;
		this.gis_strategy = gis_strategy;
		this.gis_discipline = gis_discipline;
		this.gis_professional = gis_professional;
		this.gis_teacher = gis_teacher;
		this.gis_college = gis_college;
		this.isnull = isnull;
	}
	
	
}
