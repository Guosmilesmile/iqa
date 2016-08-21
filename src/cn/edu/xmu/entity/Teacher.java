package cn.edu.xmu.entity;

public class Teacher {
	
	private String teachername;
	private String teachernumber;
	public String getTeachername() {
		return teachername;
	}
	public void setTeachername(String teachername) {
		this.teachername = teachername;
	}
	public String getTeachernumber() {
		return teachernumber;
	}
	public void setTeachernumber(String teachernumber) {
		this.teachernumber = teachernumber;
	}
	
	public Teacher() {
		super();
	}
	public Teacher(String teachername, String teachernumber) {
		super();
		this.teachername = teachername;
		this.teachernumber = teachernumber;
	}
	
	public Teacher(String teachername) {
		super();
		this.teachername = teachername;
	}
	

}
