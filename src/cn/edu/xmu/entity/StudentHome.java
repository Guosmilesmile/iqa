package cn.edu.xmu.entity;

import java.util.Date;

/**
 * 
 * @author Luo
 * 学生用房 实体类
 * 全参构造函数
 * date 2015-07-04
 *
 */
public class StudentHome {
	private int sh_id;
	//食堂面积
	private Float sh_diningroomarea;
	//食堂数量
	private Integer sh_diningrooncount;
	//宿舍面积
	private Float sh_dormitoryarea;
	//宿舍数量
	private Integer sh_dormitorycount;
	//序列号
	private int sh_serialnumber;
	//截止日期
	private Date sh_deadline;
	//所属学院
	private String sh_college;
	//审核意见
	private String sh_comments;
	private int isnull;
	public StudentHome(int sh_id, Float sh_diningroomarea,
			Integer sh_diningrooncount, Float sh_dormitoryarea,
			Integer sh_dormitorycount, int sh_serialnumber, Date sh_deadline,
			String sh_college, String sh_comments, int isnull) {
		super();
		this.sh_id = sh_id;
		this.sh_diningroomarea = sh_diningroomarea;
		this.sh_diningrooncount = sh_diningrooncount;
		this.sh_dormitoryarea = sh_dormitoryarea;
		this.sh_dormitorycount = sh_dormitorycount;
		this.sh_serialnumber = sh_serialnumber;
		this.sh_deadline = sh_deadline;
		this.sh_college = sh_college;
		this.sh_comments = sh_comments;
		this.isnull = isnull;
	}
	public StudentHome(Float sh_diningroomarea, Integer sh_diningrooncount,
			Float sh_dormitoryarea, Integer sh_dormitorycount,
			int sh_serialnumber, String sh_college, String sh_comments,
			int isnull) {
		super();
		this.sh_diningroomarea = sh_diningroomarea;
		this.sh_diningrooncount = sh_diningrooncount;
		this.sh_dormitoryarea = sh_dormitoryarea;
		this.sh_dormitorycount = sh_dormitorycount;
		this.sh_serialnumber = sh_serialnumber;
		this.sh_college = sh_college;
		this.sh_comments = sh_comments;
		this.isnull = isnull;
	}
	public StudentHome(int sh_id, Float sh_diningroomarea,
			Integer sh_diningrooncount, Float sh_dormitoryarea,
			Integer sh_dormitorycount, int sh_serialnumber, String sh_comments,
			int isnull,String sh_college) {
		super();
		this.sh_id = sh_id;
		this.sh_diningroomarea = sh_diningroomarea;
		this.sh_diningrooncount = sh_diningrooncount;
		this.sh_dormitoryarea = sh_dormitoryarea;
		this.sh_dormitorycount = sh_dormitorycount;
		this.sh_serialnumber = sh_serialnumber;
		this.sh_comments = sh_comments;
		this.isnull = isnull;
		this.sh_college = sh_college;
	}
	public StudentHome(Float sh_diningroomarea, Integer sh_diningrooncount,
			Float sh_dormitoryarea, Integer sh_dormitorycount,
			String sh_college, int isnull) {
		super();
		this.sh_diningroomarea = sh_diningroomarea;
		this.sh_diningrooncount = sh_diningrooncount;
		this.sh_dormitoryarea = sh_dormitoryarea;
		this.sh_dormitorycount = sh_dormitorycount;
		this.sh_college = sh_college;
		this.isnull = isnull;
	}
	public int getSh_id() {
		return sh_id;
	}
	public void setSh_id(int sh_id) {
		this.sh_id = sh_id;
	}
	public Float getSh_diningroomarea() {
		return sh_diningroomarea;
	}
	public void setSh_diningroomarea(Float sh_diningroomarea) {
		this.sh_diningroomarea = sh_diningroomarea;
	}
	public Integer getSh_diningrooncount() {
		return sh_diningrooncount;
	}
	public void setSh_diningrooncount(Integer sh_diningrooncount) {
		this.sh_diningrooncount = sh_diningrooncount;
	}
	public Float getSh_dormitoryarea() {
		return sh_dormitoryarea;
	}
	public void setSh_dormitoryarea(Float sh_dormitoryarea) {
		this.sh_dormitoryarea = sh_dormitoryarea;
	}
	public Integer getSh_dormitorycount() {
		return sh_dormitorycount;
	}
	public void setSh_dormitorycount(Integer sh_dormitorycount) {
		this.sh_dormitorycount = sh_dormitorycount;
	}
	public int getSh_serialnumber() {
		return sh_serialnumber;
	}
	public void setSh_serialnumber(int sh_serialnumber) {
		this.sh_serialnumber = sh_serialnumber;
	}
	public Date getSh_deadline() {
		return sh_deadline;
	}
	public void setSh_deadline(Date sh_deadline) {
		this.sh_deadline = sh_deadline;
	}
	public String getSh_college() {
		return sh_college;
	}
	public void setSh_college(String sh_college) {
		this.sh_college = sh_college;
	}
	public String getSh_comments() {
		return sh_comments;
	}
	public void setSh_comments(String sh_comments) {
		this.sh_comments = sh_comments;
	}
	public int getIsnull() {
		return isnull;
	}
	public void setIsnull(int isnull) {
		this.isnull = isnull;
	}
	
}
