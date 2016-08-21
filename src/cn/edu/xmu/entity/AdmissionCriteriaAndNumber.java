package cn.edu.xmu.entity;

import java.sql.Date;

/**
 * 附表6-1-5-4  近一届本科生录取标准及人数（时点）
 * @author yue
 *
 */
public class AdmissionCriteriaAndNumber {
	private int acn_id;
	private String acn_province;//省份
	private String acn_batch;//批次
	private Integer acn_artsadmission;//文科（录取数（人））
	private Integer acn_scienceadmission;//理科（录取数（人））
	private Double acn_artsminctrline;//文科（批次最低控制线（分））
	private Double acn_scienceminctrline;//理科（批次最低控制线（分））
	private Double acn_artsavgscore;//文科（当年录取平均分数（分））
	private Double acn_scienceavgscore;//理科（当年录取平均分数（分））
	private String acn_instruction;//说明
	private int acn_serialnumber;//序列号
	private Date acn_deadline;//截止日期
	private String acn_college;//所属学院
	private String acn_comments;//审核意见
	private int isnull; //记录是否存在空值
	public AdmissionCriteriaAndNumber(int acn_id, String acn_province, String acn_batch, Integer acn_artsadmission,
			Integer acn_scienceadmission, Double acn_artsminctrline, Double acn_scienceminctrline,
			Double acn_artsavgscore, Double acn_scienceavgscore, String acn_instruction, int acn_serialnumber,
			Date acn_deadline, String acn_college, String acn_comments, int isnull) {
		super();
		this.acn_id = acn_id;
		this.acn_province = acn_province;
		this.acn_batch = acn_batch;
		this.acn_artsadmission = acn_artsadmission;
		this.acn_scienceadmission = acn_scienceadmission;
		this.acn_artsminctrline = acn_artsminctrline;
		this.acn_scienceminctrline = acn_scienceminctrline;
		this.acn_artsavgscore = acn_artsavgscore;
		this.acn_scienceavgscore = acn_scienceavgscore;
		this.acn_instruction = acn_instruction;
		this.acn_serialnumber = acn_serialnumber;
		this.acn_deadline = acn_deadline;
		this.acn_college = acn_college;
		this.acn_comments = acn_comments;
		this.isnull = isnull;
	}
	public AdmissionCriteriaAndNumber(String acn_province, String acn_batch, Integer acn_artsadmission,
			Integer acn_scienceadmission, Double acn_artsminctrline, Double acn_scienceminctrline,
			Double acn_artsavgscore, Double acn_scienceavgscore, String acn_instruction, int acn_serialnumber,
			String acn_college, String acn_comments, int isnull) {
		super();
		this.acn_province = acn_province;
		this.acn_batch = acn_batch;
		this.acn_artsadmission = acn_artsadmission;
		this.acn_scienceadmission = acn_scienceadmission;
		this.acn_artsminctrline = acn_artsminctrline;
		this.acn_scienceminctrline = acn_scienceminctrline;
		this.acn_artsavgscore = acn_artsavgscore;
		this.acn_scienceavgscore = acn_scienceavgscore;
		this.acn_instruction = acn_instruction;
		this.acn_serialnumber = acn_serialnumber;
		this.acn_college = acn_college;
		this.acn_comments = acn_comments;
		this.isnull = isnull;
	}
	public AdmissionCriteriaAndNumber(String acn_province, String acn_batch, Integer acn_artsadmission,
			Integer acn_scienceadmission, Double acn_artsminctrline, Double acn_scienceminctrline,
			Double acn_artsavgscore, Double acn_scienceavgscore, String acn_instruction, String acn_college,
			int isnull) {
		super();
		this.acn_province = acn_province;
		this.acn_batch = acn_batch;
		this.acn_artsadmission = acn_artsadmission;
		this.acn_scienceadmission = acn_scienceadmission;
		this.acn_artsminctrline = acn_artsminctrline;
		this.acn_scienceminctrline = acn_scienceminctrline;
		this.acn_artsavgscore = acn_artsavgscore;
		this.acn_scienceavgscore = acn_scienceavgscore;
		this.acn_instruction = acn_instruction;
		this.acn_college = acn_college;
		this.acn_comments = "";
		this.isnull = isnull;
	}
	public int getAcn_id() {
		return acn_id;
	}
	public void setAcn_id(int acn_id) {
		this.acn_id = acn_id;
	}
	public String getAcn_province() {
		return acn_province;
	}
	public void setAcn_province(String acn_province) {
		this.acn_province = acn_province;
	}
	public String getAcn_batch() {
		return acn_batch;
	}
	public void setAcn_batch(String acn_batch) {
		this.acn_batch = acn_batch;
	}
	public Integer getAcn_artsadmission() {
		return acn_artsadmission;
	}
	public void setAcn_artsadmission(Integer acn_artsadmission) {
		this.acn_artsadmission = acn_artsadmission;
	}
	public Integer getAcn_scienceadmission() {
		return acn_scienceadmission;
	}
	public void setAcn_scienceadmission(Integer acn_scienceadmission) {
		this.acn_scienceadmission = acn_scienceadmission;
	}
	public Double getAcn_artsminctrline() {
		return acn_artsminctrline;
	}
	public void setAcn_artsminctrline(Double acn_artsminctrline) {
		this.acn_artsminctrline = acn_artsminctrline;
	}
	public Double getAcn_scienceminctrline() {
		return acn_scienceminctrline;
	}
	public void setAcn_scienceminctrline(Double acn_scienceminctrline) {
		this.acn_scienceminctrline = acn_scienceminctrline;
	}
	public Double getAcn_artsavgscore() {
		return acn_artsavgscore;
	}
	public void setAcn_artsavgscore(Double acn_artsavgscore) {
		this.acn_artsavgscore = acn_artsavgscore;
	}
	public Double getAcn_scienceavgscore() {
		return acn_scienceavgscore;
	}
	public void setAcn_scienceavgscore(Double acn_scienceavgscore) {
		this.acn_scienceavgscore = acn_scienceavgscore;
	}
	public String getAcn_instruction() {
		return acn_instruction;
	}
	public void setAcn_instruction(String acn_instruction) {
		this.acn_instruction = acn_instruction;
	}
	public int getAcn_serialnumber() {
		return acn_serialnumber;
	}
	public void setAcn_serialnumber(int acn_serialnumber) {
		this.acn_serialnumber = acn_serialnumber;
	}
	public Date getAcn_deadline() {
		return acn_deadline;
	}
	public void setAcn_deadline(Date acn_deadline) {
		this.acn_deadline = acn_deadline;
	}
	public String getAcn_college() {
		return acn_college;
	}
	public void setAcn_college(String acn_college) {
		this.acn_college = acn_college;
	}
	public String getAcn_comments() {
		return acn_comments;
	}
	public void setAcn_comments(String acn_comments) {
		this.acn_comments = acn_comments;
	}
	public int getIsnull() {
		return isnull;
	}
	public void setIsnull(int isnull) {
		this.isnull = isnull;
	}
	
	
	
	
	
}
