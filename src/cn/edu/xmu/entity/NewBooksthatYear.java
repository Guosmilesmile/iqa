package cn.edu.xmu.entity;

import java.sql.Date;

/**
 * @author zhantu
 * 图书当年新增情况 实体类
 * date 2015-06-30
 */
public class NewBooksthatYear {
	private int nby_id;
	//当年新增纸质图书（册）
	private Integer nby_paperbooksnumber;
	//当年新增电子图书（种)
	private Integer nby_ebooksnumber;
	//当年文献购置费（万元）
	private Float nby_documentacquisitioncost;
	//当年图书流通量（本次）
	private Integer nby_bookcirculation;
	//当年电子资源访问量（次）
	private Integer nby_electronicresourceaccess;
	//序列号
	private int nby_serialnumber;
	//截止日期
	private Date nby_deadline;
	//所属学院
	private String nby_college;
	//审核意见
	private String nby_comments;
	//记录是否存在空值
	private int isnull;
	public int getNby_id() {
		return nby_id;
	}
	public void setNby_id(int nby_id) {
		this.nby_id = nby_id;
	}
	public Integer getNby_paperbooksnumber() {
		return nby_paperbooksnumber;
	}
	public void setNby_paperbooksnumber(Integer nby_paperbooksnumber) {
		this.nby_paperbooksnumber = nby_paperbooksnumber;
	}
	public Integer getNby_ebooksnumber() {
		return nby_ebooksnumber;
	}
	public void setNby_ebooksnumber(Integer nby_ebooksnumber) {
		this.nby_ebooksnumber = nby_ebooksnumber;
	}
	public Float getNby_documentacquisitioncost() {
		return nby_documentacquisitioncost;
	}
	public void setNby_documentacquisitioncost(Float nby_documentacquisitioncost) {
		this.nby_documentacquisitioncost = nby_documentacquisitioncost;
	}
	public Integer getNby_bookcirculation() {
		return nby_bookcirculation;
	}
	public void setNby_bookcirculation(Integer nby_bookcirculation) {
		this.nby_bookcirculation = nby_bookcirculation;
	}
	public Integer getNby_electronicresourceaccess() {
		return nby_electronicresourceaccess;
	}
	public void setNby_electronicresourceaccess(Integer nby_electronicresourceaccess) {
		this.nby_electronicresourceaccess = nby_electronicresourceaccess;
	}
	public int getNby_serialnumber() {
		return nby_serialnumber;
	}
	public void setNby_serialnumber(int nby_serialnumber) {
		this.nby_serialnumber = nby_serialnumber;
	}
	public Date getNby_deadline() {
		return nby_deadline;
	}
	public void setNby_deadline(Date nby_deadline) {
		this.nby_deadline = nby_deadline;
	}
	public String getNby_college() {
		return nby_college;
	}
	public void setNby_college(String nby_college) {
		this.nby_college = nby_college;
	}
	public String getNby_comments() {
		return nby_comments;
	}
	public void setNby_comments(String nby_comments) {
		this.nby_comments = nby_comments;
	}
	public int getIsnull() {
		return isnull;
	}
	public void setIsnull(int isnull) {
		this.isnull = isnull;
	}
	public NewBooksthatYear() {
		super();
	}
	public NewBooksthatYear(int nby_id, Integer nby_paperbooksnumber,
			Integer nby_ebooksnumber, Float nby_documentacquisitioncost,
			Integer nby_bookcirculation, Integer nby_electronicresourceaccess,
			Integer nby_serialnumber, Date nby_deadline, String nby_college,
			String nby_comments, int isnull) {
		super();
		this.nby_id = nby_id;
		this.nby_paperbooksnumber = nby_paperbooksnumber;
		this.nby_ebooksnumber = nby_ebooksnumber;
		this.nby_documentacquisitioncost = nby_documentacquisitioncost;
		this.nby_bookcirculation = nby_bookcirculation;
		this.nby_electronicresourceaccess = nby_electronicresourceaccess;
		this.nby_serialnumber = nby_serialnumber;
		this.nby_deadline = nby_deadline;
		this.nby_college = nby_college;
		this.nby_comments = nby_comments;
		this.isnull = isnull;
	}
	public NewBooksthatYear(Integer nby_paperbooksnumber,
			Integer nby_ebooksnumber, Float nby_documentacquisitioncost,
			Integer nby_bookcirculation, Integer nby_electronicresourceaccess,
			Integer nby_serialnumber, String nby_college, String nby_comments,
			int isnull) {
		super();
		this.nby_paperbooksnumber = nby_paperbooksnumber;
		this.nby_ebooksnumber = nby_ebooksnumber;
		this.nby_documentacquisitioncost = nby_documentacquisitioncost;
		this.nby_bookcirculation = nby_bookcirculation;
		this.nby_electronicresourceaccess = nby_electronicresourceaccess;
		this.nby_serialnumber = nby_serialnumber;
		this.nby_college = nby_college;
		this.nby_comments = nby_comments;
		this.isnull = isnull;
	}
	public NewBooksthatYear(int nby_id, Integer nby_paperbooksnumber,
			Integer nby_ebooksnumber, Float nby_documentacquisitioncost,
			Integer nby_bookcirculation, Integer nby_electronicresourceaccess,
			Integer nby_serialnumber, String nby_comments, int isnull) {
		super();
		this.nby_id = nby_id;
		this.nby_paperbooksnumber = nby_paperbooksnumber;
		this.nby_ebooksnumber = nby_ebooksnumber;
		this.nby_documentacquisitioncost = nby_documentacquisitioncost;
		this.nby_bookcirculation = nby_bookcirculation;
		this.nby_electronicresourceaccess = nby_electronicresourceaccess;
		this.nby_serialnumber = nby_serialnumber;
		this.nby_comments = nby_comments;
		this.isnull = isnull;
	}
	public NewBooksthatYear(int nby_id, Integer nby_paperbooksnumber,
			Integer nby_ebooksnumber, Float nby_documentacquisitioncost,
			Integer nby_bookcirculation, Integer nby_electronicresourceaccess,
			String nby_comments, int isnull) {
		super();
		this.nby_id = nby_id;
		this.nby_paperbooksnumber = nby_paperbooksnumber;
		this.nby_ebooksnumber = nby_ebooksnumber;
		this.nby_documentacquisitioncost = nby_documentacquisitioncost;
		this.nby_bookcirculation = nby_bookcirculation;
		this.nby_electronicresourceaccess = nby_electronicresourceaccess;
		this.nby_comments = nby_comments;
		this.isnull = isnull;
	}
	public NewBooksthatYear(Integer nby_paperbooksnumber,
			Integer nby_ebooksnumber, Float nby_documentacquisitioncost,
			Integer nby_bookcirculation, Integer nby_electronicresourceaccess,
			String nby_college, int isnull) {
		super();
		this.nby_paperbooksnumber = nby_paperbooksnumber;
		this.nby_ebooksnumber = nby_ebooksnumber;
		this.nby_documentacquisitioncost = nby_documentacquisitioncost;
		this.nby_bookcirculation = nby_bookcirculation;
		this.nby_electronicresourceaccess = nby_electronicresourceaccess;
		this.nby_college = nby_college;
		this.isnull = isnull;
	}
	
	
	
	
}
