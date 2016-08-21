package cn.edu.xmu.entity;

import java.util.Date;

/**
 * 表4-1-1 学科建设 (时点)
 * 
 * @author Gy
 * 
 */
public class DisciplineConstruction {
	public DisciplineConstruction(int dc_doctorstation, int dc_docgrantone,
			int dc_docgranttwo, int dc_masgrantone, int dc_masgranttwo,
			int dc_undertotal, int dc_undernew, int dc_junior,
			String dc_college, int isnull) {
		super();
		this.dc_doctorstation = dc_doctorstation;
		this.dc_docgrantone = dc_docgrantone;
		this.dc_docgranttwo = dc_docgranttwo;
		this.dc_masgrantone = dc_masgrantone;
		this.dc_masgranttwo = dc_masgranttwo;
		this.dc_undertotal = dc_undertotal;
		this.dc_undernew = dc_undernew;
		this.dc_junior = dc_junior;
		this.dc_college = dc_college;
		this.isnull = isnull;
	}

	private int dc_id;
	// 博士后流动站（个）
	private int dc_doctorstation;
	// 博士学位授权一级学科点
	private int dc_docgrantone;
	// 博士学位授权二级学科点（不含一级覆盖）
	private int dc_docgranttwo;
	// 硕士学位授权一级学科点
	private int dc_masgrantone;
	// 硕士学位授权二级学科点（不含一级覆盖）
	private int dc_masgranttwo;
	// 本科专业（个）总数
	private int dc_undertotal;
	// 本科专业（个）新专业
	private int dc_undernew;
	// 专科专业（个）
	private int dc_junior;

	// 序列号
	private int dc_serialnumber;
	// 截止日期
	private Date dc_deadline;
	// 所属学院
	private String dc_college;
	// 审核意见
	private String dc_comments;
	private int isnull;

	public DisciplineConstruction(int dc_doctorstation, int dc_docgrantone,
			int dc_docgranttwo, int dc_masgrantone, int dc_masgranttwo,
			int dc_undertotal, int dc_undernew, int dc_junior,
			int dc_serialnumber, String dc_college, int isnull) {
		super();
		this.dc_doctorstation = dc_doctorstation;
		this.dc_docgrantone = dc_docgrantone;
		this.dc_docgranttwo = dc_docgranttwo;
		this.dc_masgrantone = dc_masgrantone;
		this.dc_masgranttwo = dc_masgranttwo;
		this.dc_undertotal = dc_undertotal;
		this.dc_undernew = dc_undernew;
		this.dc_junior = dc_junior;
		this.dc_serialnumber = dc_serialnumber;
		this.dc_college = dc_college;
		this.isnull = isnull;
	}

	public int getIsnull() {
		return isnull;
	}

	public void setIsnull(int isnull) {
		this.isnull = isnull;
	}

	public int getDc_id() {
		return dc_id;
	}

	public void setDc_id(int dc_id) {
		this.dc_id = dc_id;
	}

	public int getDc_doctorstation() {
		return dc_doctorstation;
	}

	public void setDc_doctorstation(int dc_doctorstation) {
		this.dc_doctorstation = dc_doctorstation;
	}

	public int getDc_docgrantone() {
		return dc_docgrantone;
	}

	public void setDc_docgrantone(int dc_docgrantone) {
		this.dc_docgrantone = dc_docgrantone;
	}

	public int getDc_docgranttwo() {
		return dc_docgranttwo;
	}

	public void setDc_docgranttwo(int dc_docgranttwo) {
		this.dc_docgranttwo = dc_docgranttwo;
	}

	public int getDc_masgrantone() {
		return dc_masgrantone;
	}

	public void setDc_masgrantone(int dc_masgrantone) {
		this.dc_masgrantone = dc_masgrantone;
	}

	public int getDc_masgranttwo() {
		return dc_masgranttwo;
	}

	public void setDc_masgranttwo(int dc_masgranttwo) {
		this.dc_masgranttwo = dc_masgranttwo;
	}

	public int getDc_undertotal() {
		return dc_undertotal;
	}

	public void setDc_undertotal(int dc_undertotal) {
		this.dc_undertotal = dc_undertotal;
	}

	public int getDc_undernew() {
		return dc_undernew;
	}

	public void setDc_undernew(int dc_undernew) {
		this.dc_undernew = dc_undernew;
	}

	public int getDc_junior() {
		return dc_junior;
	}

	public void setDc_junior(int dc_junior) {
		this.dc_junior = dc_junior;
	}

	public int getDc_serialnumber() {
		return dc_serialnumber;
	}

	public void setDc_serialnumber(int dc_serialnumber) {
		this.dc_serialnumber = dc_serialnumber;
	}

	public Date getDc_deadline() {
		return dc_deadline;
	}

	public void setDc_deadline(Date dc_deadline) {
		this.dc_deadline = dc_deadline;
	}

	public String getDc_college() {
		return dc_college;
	}

	public void setDc_college(String dc_college) {
		this.dc_college = dc_college;
	}

	public String getDc_comments() {
		return dc_comments;
	}

	public void setDc_comments(String dc_comments) {
		this.dc_comments = dc_comments;
	}

	public DisciplineConstruction(int dc_id, int dc_doctorstation,
			int dc_docgrantone, int dc_docgranttwo, int dc_masgrantone,
			int dc_masgranttwo, int dc_undertotal, int dc_undernew,
			int dc_junior, int dc_serialnumber, Date dc_deadline,
			String dc_college, String dc_comments) {
		super();
		this.dc_id = dc_id;
		this.dc_doctorstation = dc_doctorstation;
		this.dc_docgrantone = dc_docgrantone;
		this.dc_docgranttwo = dc_docgranttwo;
		this.dc_masgrantone = dc_masgrantone;
		this.dc_masgranttwo = dc_masgranttwo;
		this.dc_undertotal = dc_undertotal;
		this.dc_undernew = dc_undernew;
		this.dc_junior = dc_junior;
		this.dc_serialnumber = dc_serialnumber;
		this.dc_deadline = dc_deadline;
		this.dc_college = dc_college;
		this.dc_comments = dc_comments;
	}

	public DisciplineConstruction(int dc_doctorstation, int dc_docgrantone,
			int dc_docgranttwo, int dc_masgrantone, int dc_masgranttwo,
			int dc_undertotal, int dc_undernew, int dc_junior, String dc_college) {
		super();
		this.dc_doctorstation = dc_doctorstation;
		this.dc_docgrantone = dc_docgrantone;
		this.dc_docgranttwo = dc_docgranttwo;
		this.dc_masgrantone = dc_masgrantone;
		this.dc_masgranttwo = dc_masgranttwo;
		this.dc_undertotal = dc_undertotal;
		this.dc_undernew = dc_undernew;
		this.dc_junior = dc_junior;
		this.dc_college = dc_college;
	}

	public DisciplineConstruction(int dc_id, int dc_doctorstation,
			int dc_docgrantone, int dc_docgranttwo, int dc_masgrantone,
			int dc_masgranttwo, int dc_undertotal, int dc_undernew,
			int dc_junior, int dc_serialnumber, String dc_comments) {
		super();
		this.dc_id = dc_id;
		this.dc_doctorstation = dc_doctorstation;
		this.dc_docgrantone = dc_docgrantone;
		this.dc_docgranttwo = dc_docgranttwo;
		this.dc_masgrantone = dc_masgrantone;
		this.dc_masgranttwo = dc_masgranttwo;
		this.dc_undertotal = dc_undertotal;
		this.dc_undernew = dc_undernew;
		this.dc_junior = dc_junior;
		this.dc_serialnumber = dc_serialnumber;
		this.dc_comments = dc_comments;
	}

	public DisciplineConstruction() {
		super();
		// TODO Auto-generated constructor stub
	}

	public DisciplineConstruction(int dc_doctorstation, int dc_docgrantone,
			int dc_docgranttwo, int dc_masgrantone, int dc_masgranttwo,
			int dc_undertotal, int dc_undernew, int dc_junior,
			int dc_serialnumber, String dc_college) {
		super();
		this.dc_doctorstation = dc_doctorstation;
		this.dc_docgrantone = dc_docgrantone;
		this.dc_docgranttwo = dc_docgranttwo;
		this.dc_masgrantone = dc_masgrantone;
		this.dc_masgranttwo = dc_masgranttwo;
		this.dc_undertotal = dc_undertotal;
		this.dc_undernew = dc_undernew;
		this.dc_junior = dc_junior;
		this.dc_serialnumber = dc_serialnumber;
		this.dc_college = dc_college;
	}

}
