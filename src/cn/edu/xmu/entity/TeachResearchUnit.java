package cn.edu.xmu.entity;

import java.sql.Date;

/**
 * 
 * @author luo
 * 学校教学科研单位 实体类
 * date 2015-06-29
 */
public class TeachResearchUnit {
	private int tr_id;  //id
	private String tr_name; //行政单位名称
	private String tr_number;  //行政单位号
	private String tr_responperson; //单位负责人
	private int tr_serialnumber; //序号
	private Date tr_deadline; //截止日期
	private String tr_college;//所属学院
	
	//无参构造函数
	public TeachResearchUnit(){
		super();
	}
	
	//全参构造函数
	public TeachResearchUnit(int tr_id, String tr_name, String tr_number,
			String tr_responperson, int tr_serialnumber, Date tr_deadline,
			String tr_college) {
		super();
		this.tr_id = tr_id;
		this.tr_name = tr_name;
		this.tr_number = tr_number;
		this.tr_responperson = tr_responperson;
		this.tr_serialnumber = tr_serialnumber;
		this.tr_deadline = tr_deadline;
		this.tr_college = tr_college;
	}
	
	public TeachResearchUnit(int tr_id,String tr_name,String tr_number,String respon_person,int tr_serialnumber){
        super();
        this.tr_id=tr_id;
        this.tr_name=tr_name;
        this.tr_number=tr_number;
        this.tr_responperson=respon_person;
        this.tr_serialnumber=tr_serialnumber;
    }

	public TeachResearchUnit(int tr_id,String tr_name,String tr_number,String respon_person){
        super();
        this.tr_id=tr_id;
        this.tr_name=tr_name;
        this.tr_number=tr_number;
        this.tr_responperson=respon_person;
    }
	
	public TeachResearchUnit(String tr_name,String tr_number,String respon_person){
        super();
        this.tr_name=tr_name;
        this.tr_number=tr_number;
        this.tr_responperson=respon_person;
    }
	public int getTr_id() {
		return tr_id;
	}
	public void setTr_id(int tr_id) {
		this.tr_id = tr_id;
	}
	public String getTr_name() {
		return tr_name;
	}
	public void setTr_name(String tr_name) {
		this.tr_name = tr_name;
	}
	public String getTr_number() {
		return tr_number;
	}
	public void setTr_number(String tr_number) {
		this.tr_number = tr_number;
	}
	public String getTr_responperson() {
		return tr_responperson;
	}
	public void setTr_responperson(String tr_responperson) {
		this.tr_responperson = tr_responperson;
	}
	public int getTr_serialnumber() {
		return tr_serialnumber;
	}
	public void setTr_serialnumber(int tr_serialnumber) {
		this.tr_serialnumber = tr_serialnumber;
	}
	public Date getTr_deadline() {
		return tr_deadline;
	}
	public void setTr_deadline(Date tr_deadline) {
		this.tr_deadline = tr_deadline;
	}
	public String getTr_college() {
		return tr_college;
	}
	public void setTr_college(String tr_college) {
		this.tr_college = tr_college;
	}
	
	
}
