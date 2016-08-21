package cn.edu.xmu.entity;

import java.sql.Date;

/**
 * @author zhantu
 * 教师培训进修、交流情况（学年） 实体类
 * date 2015-07-03
 */
public class TeacherTrainingExchange {
	//id
	public int tte_id;
	//院（系）名称
	public String tte_departmentname;
	//单位号
	public String tte_departmentnumber;
	//境内教师培训进修（人次）
	public Integer tte_trainchurchyard;
	//境外教师培训进修总数（人次）
	public Integer tte_trainoverseassum;
	//境外教师培训进修其中：3个月及以上（人次）
	public Integer tte_trainoverseasover3;
	//到行业培训教师培训进修总数（人次）
	public Integer tte_traintrade;
	//攻读学位教师培训进修总数（人次）
	public Integer tte_trainfordegreesum;
	//攻读学位教师培训进修其中：博士（人次）
	public Integer tte_trainfordoctor;
	//攻读学位教师培训进修其中：硕士（人次）
	public Integer tte_trainformaster;
	//来访境内交流教师（3个月及以上）（人次）
	public Integer tte_exchangecomechurchyard;
	//来访境外交流教师（3个月及以上）（人次）
	public Integer tte_exchangecomeoversea;
	//出访境内交流教师（3个月及以上）（人次）
	public Integer tte_exchangevisitchurchyard;
	//出访境外交流教师（3个月及以上）（人次）
	public Integer tte_exchangevisitoversea;
	//序列号
	public int tte_serialnumber;
	//截止日期
	public Date tte_deadline;
	//所属学院
	public String tte_college;
	//审核意见
	public String tte_comments;
	//记录是否存在空值
	public int isnull;
	public int getTte_id() {
		return tte_id;
	}
	public void setTte_id(int tte_id) {
		this.tte_id = tte_id;
	}
	public String getTte_departmentname() {
		return tte_departmentname;
	}
	public void setTte_departmentname(String tte_departmentname) {
		this.tte_departmentname = tte_departmentname;
	}
	public String getTte_departmentnumber() {
		return tte_departmentnumber;
	}
	public void setTte_departmentnumber(String tte_departmentnumber) {
		this.tte_departmentnumber = tte_departmentnumber;
	}
	public Integer getTte_trainchurchyard() {
		return tte_trainchurchyard;
	}
	public void setTte_trainchurchyard(Integer tte_trainchurchyard) {
		this.tte_trainchurchyard = tte_trainchurchyard;
	}
	public Integer getTte_trainoverseassum() {
		return tte_trainoverseassum;
	}
	public void setTte_trainoverseassum(Integer tte_trainoverseassum) {
		this.tte_trainoverseassum = tte_trainoverseassum;
	}
	public Integer getTte_trainoverseasover3() {
		return tte_trainoverseasover3;
	}
	public void setTte_trainoverseasover3(Integer tte_trainoverseasover3) {
		this.tte_trainoverseasover3 = tte_trainoverseasover3;
	}
	public Integer getTte_traintrade() {
		return tte_traintrade;
	}
	public void setTte_traintrade(Integer tte_traintrade) {
		this.tte_traintrade = tte_traintrade;
	}
	public Integer getTte_trainfordegreesum() {
		return tte_trainfordegreesum;
	}
	public void setTte_trainfordegreesum(Integer tte_trainfordegreesum) {
		this.tte_trainfordegreesum = tte_trainfordegreesum;
	}
	public Integer getTte_trainfordoctor() {
		return tte_trainfordoctor;
	}
	public void setTte_trainfordoctor(Integer tte_trainfordoctor) {
		this.tte_trainfordoctor = tte_trainfordoctor;
	}
	public Integer getTte_trainformaster() {
		return tte_trainformaster;
	}
	public void setTte_trainformaster(Integer tte_trainformaster) {
		this.tte_trainformaster = tte_trainformaster;
	}
	public Integer getTte_exchangecomechurchyard() {
		return tte_exchangecomechurchyard;
	}
	public void setTte_exchangecomechurchyard(Integer tte_exchangecomechurchyard) {
		this.tte_exchangecomechurchyard = tte_exchangecomechurchyard;
	}
	public Integer getTte_exchangecomeoversea() {
		return tte_exchangecomeoversea;
	}
	public void setTte_exchangecomeoversea(Integer tte_exchangecomeoversea) {
		this.tte_exchangecomeoversea = tte_exchangecomeoversea;
	}
	public Integer getTte_exchangevisitchurchyard() {
		return tte_exchangevisitchurchyard;
	}
	public void setTte_exchangevisitchurchyard(Integer tte_exchangevisitchurchyard) {
		this.tte_exchangevisitchurchyard = tte_exchangevisitchurchyard;
	}
	public Integer getTte_exchangevisitoversea() {
		return tte_exchangevisitoversea;
	}
	public void setTte_exchangevisitoversea(Integer tte_exchangevisitoversea) {
		this.tte_exchangevisitoversea = tte_exchangevisitoversea;
	}
	public int getTte_serialnumber() {
		return tte_serialnumber;
	}
	public void setTte_serialnumber(int tte_serialnumber) {
		this.tte_serialnumber = tte_serialnumber;
	}
	public Date getTte_deadline() {
		return tte_deadline;
	}
	public void setTte_deadline(Date tte_deadline) {
		this.tte_deadline = tte_deadline;
	}
	public String getTte_college() {
		return tte_college;
	}
	public void setTte_college(String tte_college) {
		this.tte_college = tte_college;
	}
	public String getTte_comments() {
		return tte_comments;
	}
	public void setTte_comments(String tte_comments) {
		this.tte_comments = tte_comments;
	}
	public int getIsnull() {
		return isnull;
	}
	public void setIsnull(int isnull) {
		this.isnull = isnull;
	}
	public TeacherTrainingExchange() {
		super();
	}
	public TeacherTrainingExchange(int tte_id, String tte_departmentname,
			String tte_departmentnumber, Integer tte_trainchurchyard,
			Integer tte_trainoverseassum, Integer tte_trainoverseasover3,
			Integer tte_traintrade, Integer tte_trainfordegreesum,
			Integer tte_trainfordoctor, Integer tte_trainformaster,
			Integer tte_exchangecomechurchyard,
			Integer tte_exchangecomeoversea,
			Integer tte_exchangevisitchurchyard,
			Integer tte_exchangevisitoversea, int tte_serialnumber,
			Date tte_deadline, String tte_college, String tte_comments,
			int isnull) {
		super();
		this.tte_id = tte_id;
		this.tte_departmentname = tte_departmentname;
		this.tte_departmentnumber = tte_departmentnumber;
		this.tte_trainchurchyard = tte_trainchurchyard;
		this.tte_trainoverseassum = tte_trainoverseassum;
		this.tte_trainoverseasover3 = tte_trainoverseasover3;
		this.tte_traintrade = tte_traintrade;
		this.tte_trainfordegreesum = tte_trainfordegreesum;
		this.tte_trainfordoctor = tte_trainfordoctor;
		this.tte_trainformaster = tte_trainformaster;
		this.tte_exchangecomechurchyard = tte_exchangecomechurchyard;
		this.tte_exchangecomeoversea = tte_exchangecomeoversea;
		this.tte_exchangevisitchurchyard = tte_exchangevisitchurchyard;
		this.tte_exchangevisitoversea = tte_exchangevisitoversea;
		this.tte_serialnumber = tte_serialnumber;
		this.tte_deadline = tte_deadline;
		this.tte_college = tte_college;
		this.tte_comments = tte_comments;
		this.isnull = isnull;
	}
	public TeacherTrainingExchange(String tte_departmentname,
			String tte_departmentnumber, Integer tte_trainchurchyard,
			Integer tte_trainoverseassum, Integer tte_trainoverseasover3,
			Integer tte_traintrade, Integer tte_trainfordegreesum,
			Integer tte_trainfordoctor, Integer tte_trainformaster,
			Integer tte_exchangecomechurchyard,
			Integer tte_exchangecomeoversea,
			Integer tte_exchangevisitchurchyard,
			Integer tte_exchangevisitoversea, int tte_serialnumber,
			String tte_college, String tte_comments, int isnull) {
		super();
		this.tte_departmentname = tte_departmentname;
		this.tte_departmentnumber = tte_departmentnumber;
		this.tte_trainchurchyard = tte_trainchurchyard;
		this.tte_trainoverseassum = tte_trainoverseassum;
		this.tte_trainoverseasover3 = tte_trainoverseasover3;
		this.tte_traintrade = tte_traintrade;
		this.tte_trainfordegreesum = tte_trainfordegreesum;
		this.tte_trainfordoctor = tte_trainfordoctor;
		this.tte_trainformaster = tte_trainformaster;
		this.tte_exchangecomechurchyard = tte_exchangecomechurchyard;
		this.tte_exchangecomeoversea = tte_exchangecomeoversea;
		this.tte_exchangevisitchurchyard = tte_exchangevisitchurchyard;
		this.tte_exchangevisitoversea = tte_exchangevisitoversea;
		this.tte_serialnumber = tte_serialnumber;
		this.tte_college = tte_college;
		this.tte_comments = tte_comments;
		this.isnull = isnull;
	}
	public TeacherTrainingExchange(int tte_id, String tte_departmentname,
			String tte_departmentnumber, Integer tte_trainchurchyard,
			Integer tte_trainoverseassum, Integer tte_trainoverseasover3,
			Integer tte_traintrade, Integer tte_trainfordegreesum,
			Integer tte_trainfordoctor, Integer tte_trainformaster,
			Integer tte_exchangecomechurchyard,
			Integer tte_exchangecomeoversea,
			Integer tte_exchangevisitchurchyard,
			Integer tte_exchangevisitoversea, int tte_serialnumber,
			String tte_comments, int isnull) {
		super();
		this.tte_id = tte_id;
		this.tte_departmentname = tte_departmentname;
		this.tte_departmentnumber = tte_departmentnumber;
		this.tte_trainchurchyard = tte_trainchurchyard;
		this.tte_trainoverseassum = tte_trainoverseassum;
		this.tte_trainoverseasover3 = tte_trainoverseasover3;
		this.tte_traintrade = tte_traintrade;
		this.tte_trainfordegreesum = tte_trainfordegreesum;
		this.tte_trainfordoctor = tte_trainfordoctor;
		this.tte_trainformaster = tte_trainformaster;
		this.tte_exchangecomechurchyard = tte_exchangecomechurchyard;
		this.tte_exchangecomeoversea = tte_exchangecomeoversea;
		this.tte_exchangevisitchurchyard = tte_exchangevisitchurchyard;
		this.tte_exchangevisitoversea = tte_exchangevisitoversea;
		this.tte_serialnumber = tte_serialnumber;
		this.tte_comments = tte_comments;
		this.isnull = isnull;
	}
	public TeacherTrainingExchange(int tte_id, String tte_departmentname,
			String tte_departmentnumber, Integer tte_trainchurchyard,
			Integer tte_trainoverseassum, Integer tte_trainoverseasover3,
			Integer tte_traintrade, Integer tte_trainfordegreesum,
			Integer tte_trainfordoctor, Integer tte_trainformaster,
			Integer tte_exchangecomechurchyard,
			Integer tte_exchangecomeoversea,
			Integer tte_exchangevisitchurchyard,
			Integer tte_exchangevisitoversea, String tte_comments, int isnull) {
		super();
		this.tte_id = tte_id;
		this.tte_departmentname = tte_departmentname;
		this.tte_departmentnumber = tte_departmentnumber;
		this.tte_trainchurchyard = tte_trainchurchyard;
		this.tte_trainoverseassum = tte_trainoverseassum;
		this.tte_trainoverseasover3 = tte_trainoverseasover3;
		this.tte_traintrade = tte_traintrade;
		this.tte_trainfordegreesum = tte_trainfordegreesum;
		this.tte_trainfordoctor = tte_trainfordoctor;
		this.tte_trainformaster = tte_trainformaster;
		this.tte_exchangecomechurchyard = tte_exchangecomechurchyard;
		this.tte_exchangecomeoversea = tte_exchangecomeoversea;
		this.tte_exchangevisitchurchyard = tte_exchangevisitchurchyard;
		this.tte_exchangevisitoversea = tte_exchangevisitoversea;
		this.tte_comments = tte_comments;
		this.isnull = isnull;
	}
	public TeacherTrainingExchange(String tte_departmentname,
			String tte_departmentnumber, Integer tte_trainchurchyard,
			Integer tte_trainoverseassum, Integer tte_trainoverseasover3,
			Integer tte_traintrade, Integer tte_trainfordegreesum,
			Integer tte_trainfordoctor, Integer tte_trainformaster,
			Integer tte_exchangecomechurchyard,
			Integer tte_exchangecomeoversea,
			Integer tte_exchangevisitchurchyard,
			Integer tte_exchangevisitoversea, String tte_college, int isnull) {
		super();
		this.tte_departmentname = tte_departmentname;
		this.tte_departmentnumber = tte_departmentnumber;
		this.tte_trainchurchyard = tte_trainchurchyard;
		this.tte_trainoverseassum = tte_trainoverseassum;
		this.tte_trainoverseasover3 = tte_trainoverseasover3;
		this.tte_traintrade = tte_traintrade;
		this.tte_trainfordegreesum = tte_trainfordegreesum;
		this.tte_trainfordoctor = tte_trainfordoctor;
		this.tte_trainformaster = tte_trainformaster;
		this.tte_exchangecomechurchyard = tte_exchangecomechurchyard;
		this.tte_exchangecomeoversea = tte_exchangecomeoversea;
		this.tte_exchangevisitchurchyard = tte_exchangevisitchurchyard;
		this.tte_exchangevisitoversea = tte_exchangevisitoversea;
		this.tte_college = tte_college;
		this.isnull = isnull;
	}
	
	
}
