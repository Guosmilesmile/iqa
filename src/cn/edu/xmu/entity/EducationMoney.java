package cn.edu.xmu.entity;

import java.util.Date;

/**
 * 附表2-10-2-1教育经费收支情况
 * @author Gy
 *
 */
public class EducationMoney {
	private int em_id;//姓名
	private String em_colleges;
	private Float em_intotal;
	private Float em_inschoolfunds;
	private Float em_inchange;
	private Float em_instudentactivity;
	private Float em_inbuy;
	private Float em_inselfraise;
	private Float em_indonations;
	private Float em_outtotal;
	private Float em_outdaily;
	private Float em_outchange;
	private Float em_outbuild;
	private Float em_outpractice;
	private Float em_outpracticeexperiment;
	private Float em_outpracticeinter;
	private Float em_outother;
	private Float em_outstudentactivity;
	private Float em_outteacher;
	private int	em_serialnumber;//序列号
	private Date em_deadline;//截止日期
	private String em_college;//所属学院
	private String em_comments;//审核
	private int isnull;
	
	
	

	public EducationMoney(String em_colleges, Float em_intotal,
			Float em_inschoolfunds, Float em_inchange, Float em_instudentactivity,
			Float em_inbuy, Float em_inselfraise, Float em_indonations,
			Float em_outtotal, Float em_outdaily, Float em_outchange,
			Float em_outbuild, Float em_outpractice, Float em_outpracticeexperiment,
			Float em_outpracticeinter, Float em_outother,
			Float em_outstudentactivity, Float em_outteacher, int em_serialnumber,
			String em_college, String em_comments, int isnull) {
		super();
		this.em_colleges = em_colleges;
		this.em_intotal = em_intotal;
		this.em_inschoolfunds = em_inschoolfunds;
		this.em_inchange = em_inchange;
		this.em_instudentactivity = em_instudentactivity;
		this.em_inbuy = em_inbuy;
		this.em_inselfraise = em_inselfraise;
		this.em_indonations = em_indonations;
		this.em_outtotal = em_outtotal;
		this.em_outdaily = em_outdaily;
		this.em_outchange = em_outchange;
		this.em_outbuild = em_outbuild;
		this.em_outpractice = em_outpractice;
		this.em_outpracticeexperiment = em_outpracticeexperiment;
		this.em_outpracticeinter = em_outpracticeinter;
		this.em_outother = em_outother;
		this.em_outstudentactivity = em_outstudentactivity;
		this.em_outteacher = em_outteacher;
		this.em_serialnumber = em_serialnumber;
		this.em_college = em_college;
		this.em_comments = em_comments;
		this.isnull = isnull;
	}

	public EducationMoney(String em_colleges, Float em_intotal,
			Float em_inschoolfunds, Float em_inchange, Float em_instudentactivity,
			Float em_inbuy, Float em_inselfraise, Float em_indonations,
			Float em_outtotal, Float em_outdaily, Float em_outchange,
			Float em_outbuild, Float em_outpractice, Float em_outpracticeexperiment,
			Float em_outpracticeinter, Float em_outother,
			Float em_outstudentactivity, Float em_outteacher, int em_serialnumber,
			String em_college, int isnull) {
		super();
		this.em_colleges = em_colleges;
		this.em_intotal = em_intotal;
		this.em_inschoolfunds = em_inschoolfunds;
		this.em_inchange = em_inchange;
		this.em_instudentactivity = em_instudentactivity;
		this.em_inbuy = em_inbuy;
		this.em_inselfraise = em_inselfraise;
		this.em_indonations = em_indonations;
		this.em_outtotal = em_outtotal;
		this.em_outdaily = em_outdaily;
		this.em_outchange = em_outchange;
		this.em_outbuild = em_outbuild;
		this.em_outpractice = em_outpractice;
		this.em_outpracticeexperiment = em_outpracticeexperiment;
		this.em_outpracticeinter = em_outpracticeinter;
		this.em_outother = em_outother;
		this.em_outstudentactivity = em_outstudentactivity;
		this.em_outteacher = em_outteacher;
		this.em_serialnumber = em_serialnumber;
		this.em_college = em_college;
		this.isnull = isnull;
	}

	

	public EducationMoney(String em_colleges, Float em_intotal,
			Float em_inschoolfunds, Float em_inchange, Float em_instudentactivity,
			Float em_inbuy, Float em_inselfraise, Float em_indonations,
			Float em_outtotal, Float em_outdaily, Float em_outchange,
			Float em_outbuild, Float em_outpractice, Float em_outpracticeexperiment,
			Float em_outpracticeinter, Float em_outother,
			Float em_outstudentactivity, Float em_outteacher, String em_college,
			int isnull) {
		super();
		this.em_colleges = em_colleges;
		this.em_intotal = em_intotal;
		this.em_inschoolfunds = em_inschoolfunds;
		this.em_inchange = em_inchange;
		this.em_instudentactivity = em_instudentactivity;
		this.em_inbuy = em_inbuy;
		this.em_inselfraise = em_inselfraise;
		this.em_indonations = em_indonations;
		this.em_outtotal = em_outtotal;
		this.em_outdaily = em_outdaily;
		this.em_outchange = em_outchange;
		this.em_outbuild = em_outbuild;
		this.em_outpractice = em_outpractice;
		this.em_outpracticeexperiment = em_outpracticeexperiment;
		this.em_outpracticeinter = em_outpracticeinter;
		this.em_outother = em_outother;
		this.em_outstudentactivity = em_outstudentactivity;
		this.em_outteacher = em_outteacher;
		this.em_college = em_college;
		this.isnull = isnull;
	}

	public int getIsnull() {
		return isnull;
	}

	public void setIsnull(int isnull) {
		this.isnull = isnull;
	}

	public EducationMoney() {
		super();
	}
	
	public EducationMoney(String em_colleges, Float em_intotal,
			Float em_inschoolfunds, Float em_inchange, Float em_instudentactivity,
			Float em_inbuy, Float em_inselfraise, Float em_indonations,
			Float em_outtotal, Float em_outdaily, Float em_outchange,
			Float em_outbuild, Float em_outpractice, Float em_outpracticeexperiment,
			Float em_outpracticeinter, Float em_outother,
			Float em_outstudentactivity, Float em_outteacher, String em_college) {
		super();
		this.em_colleges = em_colleges;
		this.em_intotal = em_intotal;
		this.em_inschoolfunds = em_inschoolfunds;
		this.em_inchange = em_inchange;
		this.em_instudentactivity = em_instudentactivity;
		this.em_inbuy = em_inbuy;
		this.em_inselfraise = em_inselfraise;
		this.em_indonations = em_indonations;
		this.em_outtotal = em_outtotal;
		this.em_outdaily = em_outdaily;
		this.em_outchange = em_outchange;
		this.em_outbuild = em_outbuild;
		this.em_outpractice = em_outpractice;
		this.em_outpracticeexperiment = em_outpracticeexperiment;
		this.em_outpracticeinter = em_outpracticeinter;
		this.em_outother = em_outother;
		this.em_outstudentactivity = em_outstudentactivity;
		this.em_outteacher = em_outteacher;
		this.em_college = em_college;
	}

	public EducationMoney(String em_colleges, Float em_intotal,
			Float em_inschoolfunds, Float em_inchange, Float em_instudentactivity,
			Float em_inbuy, Float em_inselfraise, Float em_indonations,
			Float em_outtotal, Float em_outdaily, Float em_outchange,
			Float em_outbuild, Float em_outpractice, Float em_outpracticeexperiment,
			Float em_outpracticeinter, Float em_outother,
			Float em_outstudentactivity, Float em_outteacher, int em_serialnumber,
			String em_college) {
		super();
		this.em_colleges = em_colleges;
		this.em_intotal = em_intotal;
		this.em_inschoolfunds = em_inschoolfunds;
		this.em_inchange = em_inchange;
		this.em_instudentactivity = em_instudentactivity;
		this.em_inbuy = em_inbuy;
		this.em_inselfraise = em_inselfraise;
		this.em_indonations = em_indonations;
		this.em_outtotal = em_outtotal;
		this.em_outdaily = em_outdaily;
		this.em_outchange = em_outchange;
		this.em_outbuild = em_outbuild;
		this.em_outpractice = em_outpractice;
		this.em_outpracticeexperiment = em_outpracticeexperiment;
		this.em_outpracticeinter = em_outpracticeinter;
		this.em_outother = em_outother;
		this.em_outstudentactivity = em_outstudentactivity;
		this.em_outteacher = em_outteacher;
		this.em_serialnumber = em_serialnumber;
		this.em_college = em_college;
	}

	public EducationMoney(String em_colleges, Float em_intotal,
			Float em_inschoolfunds, Float em_inchange, Float em_instudentactivity,
			Float em_inbuy, Float em_inselfraise, Float em_indonations,
			Float em_outtotal, Float em_outdaily, Float em_outchange,
			Float em_outbuild, Float em_outpractice, Float em_outpracticeexperiment,
			Float em_outpracticeinter, Float em_outother,
			Float em_outstudentactivity, Float em_outteacher, int em_serialnumber,
			String em_college, String em_comments) {
		super();
		this.em_colleges = em_colleges;
		this.em_intotal = em_intotal;
		this.em_inschoolfunds = em_inschoolfunds;
		this.em_inchange = em_inchange;
		this.em_instudentactivity = em_instudentactivity;
		this.em_inbuy = em_inbuy;
		this.em_inselfraise = em_inselfraise;
		this.em_indonations = em_indonations;
		this.em_outtotal = em_outtotal;
		this.em_outdaily = em_outdaily;
		this.em_outchange = em_outchange;
		this.em_outbuild = em_outbuild;
		this.em_outpractice = em_outpractice;
		this.em_outpracticeexperiment = em_outpracticeexperiment;
		this.em_outpracticeinter = em_outpracticeinter;
		this.em_outother = em_outother;
		this.em_outstudentactivity = em_outstudentactivity;
		this.em_outteacher = em_outteacher;
		this.em_serialnumber = em_serialnumber;
		this.em_college = em_college;
		this.em_comments = em_comments;
	}
	public EducationMoney(int em_id, String em_colleges, Float em_intotal,
			Float em_inschoolfunds, Float em_inchange, Float em_instudentactivity,
			Float em_inbuy, Float em_inselfraise, Float em_indonations,
			Float em_outtotal, Float em_outdaily, Float em_outchange,
			Float em_outbuild, Float em_outpractice, Float em_outpracticeexperiment,
			Float em_outpracticeinter, Float em_outother,
			Float em_outstudentactivity, Float em_outteacher, int em_serialnumber,
			Date em_deadline, String em_college, String em_comments) {
		super();
		this.em_id = em_id;
		this.em_colleges = em_colleges;
		this.em_intotal = em_intotal;
		this.em_inschoolfunds = em_inschoolfunds;
		this.em_inchange = em_inchange;
		this.em_instudentactivity = em_instudentactivity;
		this.em_inbuy = em_inbuy;
		this.em_inselfraise = em_inselfraise;
		this.em_indonations = em_indonations;
		this.em_outtotal = em_outtotal;
		this.em_outdaily = em_outdaily;
		this.em_outchange = em_outchange;
		this.em_outbuild = em_outbuild;
		this.em_outpractice = em_outpractice;
		this.em_outpracticeexperiment = em_outpracticeexperiment;
		this.em_outpracticeinter = em_outpracticeinter;
		this.em_outother = em_outother;
		this.em_outstudentactivity = em_outstudentactivity;
		this.em_outteacher = em_outteacher;
		this.em_serialnumber = em_serialnumber;
		this.em_deadline = em_deadline;
		this.em_college = em_college;
		this.em_comments = em_comments;
	}
	
	public EducationMoney(int em_id, String em_colleges, Float em_intotal,
			Float em_inschoolfunds, Float em_inchange, Float em_instudentactivity,
			Float em_inbuy, Float em_inselfraise, Float em_indonations,
			Float em_outtotal, Float em_outdaily, Float em_outchange,
			Float em_outbuild, Float em_outpractice, Float em_outpracticeexperiment,
			Float em_outpracticeinter, Float em_outother,
			Float em_outstudentactivity, Float em_outteacher, int em_serialnumber,
			String em_college, String em_comments) {
		super();
		this.em_id = em_id;
		this.em_colleges = em_colleges;
		this.em_intotal = em_intotal;
		this.em_inschoolfunds = em_inschoolfunds;
		this.em_inchange = em_inchange;
		this.em_instudentactivity = em_instudentactivity;
		this.em_inbuy = em_inbuy;
		this.em_inselfraise = em_inselfraise;
		this.em_indonations = em_indonations;
		this.em_outtotal = em_outtotal;
		this.em_outdaily = em_outdaily;
		this.em_outchange = em_outchange;
		this.em_outbuild = em_outbuild;
		this.em_outpractice = em_outpractice;
		this.em_outpracticeexperiment = em_outpracticeexperiment;
		this.em_outpracticeinter = em_outpracticeinter;
		this.em_outother = em_outother;
		this.em_outstudentactivity = em_outstudentactivity;
		this.em_outteacher = em_outteacher;
		this.em_serialnumber = em_serialnumber;
		this.em_college = em_college;
		this.em_comments = em_comments;
	}
	
	public int getEm_id() {
		return em_id;
	}
	public void setEm_id(int em_id) {
		this.em_id = em_id;
	}
	public String getEm_colleges() {
		return em_colleges;
	}
	public void setEm_colleges(String em_colleges) {
		this.em_colleges = em_colleges;
	}
	public Float getEm_intotal() {
		return em_intotal;
	}
	public void setEm_intotal(Float em_intotal) {
		this.em_intotal = em_intotal;
	}
	public Float getEm_inschoolfunds() {
		return em_inschoolfunds;
	}
	public void setEm_inschoolfunds(Float em_inschoolfunds) {
		this.em_inschoolfunds = em_inschoolfunds;
	}
	public Float getEm_inchange() {
		return em_inchange;
	}
	public void setEm_inchange(Float em_inchange) {
		this.em_inchange = em_inchange;
	}
	public Float getEm_instudentactivity() {
		return em_instudentactivity;
	}
	public void setEm_instudentactivity(Float em_instudentactivity) {
		this.em_instudentactivity = em_instudentactivity;
	}
	public Float getEm_inbuy() {
		return em_inbuy;
	}
	public void setEm_inbuy(Float em_inbuy) {
		this.em_inbuy = em_inbuy;
	}
	public Float getEm_inselfraise() {
		return em_inselfraise;
	}
	public void setEm_inselfraise(Float em_inselfraise) {
		this.em_inselfraise = em_inselfraise;
	}
	public Float getEm_indonations() {
		return em_indonations;
	}
	public void setEm_indonations(Float em_indonations) {
		this.em_indonations = em_indonations;
	}
	public Float getEm_outtotal() {
		return em_outtotal;
	}
	public void setEm_outtotal(Float em_outtotal) {
		this.em_outtotal = em_outtotal;
	}
	public Float getEm_outdaily() {
		return em_outdaily;
	}
	public void setEm_outdaily(Float em_outdaily) {
		this.em_outdaily = em_outdaily;
	}
	public Float getEm_outchange() {
		return em_outchange;
	}
	public void setEm_outchange(Float em_outchange) {
		this.em_outchange = em_outchange;
	}
	public Float getEm_outbuild() {
		return em_outbuild;
	}
	public void setEm_outbuild(Float em_outbuild) {
		this.em_outbuild = em_outbuild;
	}
	public Float getEm_outpractice() {
		return em_outpractice;
	}
	public void setEm_outpractice(Float em_outpractice) {
		this.em_outpractice = em_outpractice;
	}
	public Float getEm_outpracticeexperiment() {
		return em_outpracticeexperiment;
	}
	public void setEm_outpracticeexperiment(Float em_outpracticeexperiment) {
		this.em_outpracticeexperiment = em_outpracticeexperiment;
	}
	public Float getEm_outpracticeinter() {
		return em_outpracticeinter;
	}
	public void setEm_outpracticeinter(Float em_outpracticeinter) {
		this.em_outpracticeinter = em_outpracticeinter;
	}
	public Float getEm_outother() {
		return em_outother;
	}
	public void setEm_outother(Float em_outother) {
		this.em_outother = em_outother;
	}
	public Float getEm_outstudentactivity() {
		return em_outstudentactivity;
	}
	public void setEm_outstudentactivity(Float em_outstudentactivity) {
		this.em_outstudentactivity = em_outstudentactivity;
	}
	public Float getEm_outteacher() {
		return em_outteacher;
	}
	public void setEm_outteacher(Float em_outteacher) {
		this.em_outteacher = em_outteacher;
	}
	public int getEm_serialnumber() {
		return em_serialnumber;
	}
	public void setEm_serialnumber(int em_serialnumber) {
		this.em_serialnumber = em_serialnumber;
	}
	public Date getEm_deadline() {
		return em_deadline;
	}
	public void setEm_deadline(Date em_deadline) {
		this.em_deadline = em_deadline;
	}
	public String getEm_college() {
		return em_college;
	}
	public void setEm_college(String em_college) {
		this.em_college = em_college;
	}
	public String getEm_comments() {
		return em_comments;
	}
	public void setEm_comments(String em_comments) {
		this.em_comments = em_comments;
	}
	
	
	
}
