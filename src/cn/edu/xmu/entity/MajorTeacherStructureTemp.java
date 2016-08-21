package cn.edu.xmu.entity;

public class MajorTeacherStructureTemp {
	//用去存储 select专业,学位,职称,count(*)后的resultset的list的实体型
	private String subject;
	private String title;
	private String degree;
	private int count;
	public String getSubject() {
		return subject;
	}
	public void setSubject(String subject) {
		this.subject = subject;
	}
	public String getTitle() {
		return title;
	}
	public void setTitle(String title) {
		this.title = title;
	}
	public String getDegree() {
		return degree;
	}
	public void setDegree(String degree) {
		this.degree = degree;
	}
	public int getCount() {
		return count;
	}
	public void setCount(int count) {
		this.count = count;
	}
	public MajorTeacherStructureTemp(String subject, String title,
			String degree, int count) {
		super();
		this.subject = subject;
		this.title = title;
		this.degree = degree;
		this.count = count;
	}
	
}
