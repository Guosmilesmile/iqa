package cn.edu.xmu.entity;

/*
 * 表1-3 学校相关行政单位
 */
public class GovermentalUnit {
	private int gu_id;  //id
	private String gu_name; //行政单位名称
	private String gu_number;  //行政单位号
	private String gu_responsibility; //单位职能
	private String gu_responperson; //单位负责人
	private int gu_serialnumber; //序号
	
	public GovermentalUnit(int gu_id,String gu_name,String gu_number,String gu_responsibility,String respon_person,int gu_serialnumber){
        super();
        this.gu_id=gu_id;
        this.gu_name=gu_name;
        this.gu_number=gu_number;
        this.gu_responsibility=gu_responsibility;
        this.gu_responperson=respon_person;
        this.gu_serialnumber=gu_serialnumber;
    }
	
	public GovermentalUnit(int gu_id,String gu_name,String gu_number,String gu_responsibility,String respon_person){
        super();
        this.gu_id=gu_id;
        this.gu_name=gu_name;
        this.gu_number=gu_number;
        this.gu_responsibility=gu_responsibility;
        this.gu_responperson=respon_person;
    }
	
	public GovermentalUnit(String gu_name,String gu_number,String gu_responsibility,String respon_person){
        super();
        this.gu_name=gu_name;
        this.gu_number=gu_number;
        this.gu_responsibility=gu_responsibility;
        this.gu_responperson=respon_person;
    }
	
	public int getGu_id() {
		return gu_id;
	}
	public void setGu_id(int gu_id) {
		this.gu_id = gu_id;
	}
	public String getGu_name() {
		return gu_name;
	}
	public void setGu_name(String gu_name) {
		this.gu_name = gu_name;
	}
	public String getGu_number() {
		return gu_number;
	}
	public void setGu_number(String gu_number) {
		this.gu_number = gu_number;
	}
	public String getGu_responsibility() {
		return gu_responsibility;
	}
	public void setGu_responsibility(String gu_responsibility) {
		this.gu_responsibility = gu_responsibility;
	}
	public String getGu_responperson() {
		return gu_responperson;
	}
	public void setGu_responperson(String gu_responperson) {
		this.gu_responperson = gu_responperson;
	}
	public int getGu_serialnumber() {
		return gu_serialnumber;
	}
	public void setGu_serialnumber(int gu_serialnumber) {
		this.gu_serialnumber = gu_serialnumber;
	}
	
	
}
