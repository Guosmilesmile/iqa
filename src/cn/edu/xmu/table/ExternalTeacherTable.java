package cn.edu.xmu.table;
/**
 * 3-1-2外聘教师基本信息
 * @author zhantu
 *
 */
public class ExternalTeacherTable {
	public static final String TABLE_NAME = "et_externalteacher";
	public static final String ET_ID = "et_id"; 
	public static final String ET_NAME = "et_name";//姓名
	public static final String ET_WORKNUMBER = "et_worknumber";//工号
	public static final String ET_SEX = "et_sex";//性别
	public static final String ET_BIRTH = "et_birth";//出生年月
	public static final String ET_APPOINTMENT = "et_appointment";//聘任时间
	public static final String ET_SITUATION= "et_situation";//任职状态
	public static final String ET_TERM = "et_term";//聘期
	public static final String ET_DEPARTMENTNUMBER = "et_departmentnumber";//所属单位
	public static final String ET_EDUCATION = "et_education";//学历
	public static final String ET_TOPEDUCATION = "et_topeducation";//最高学位
	public static final String ET_PROFESSIONAL = "et_professional";//专业技术职称
	public static final String ET_SUBJECT = "et_subject";//学科类别
	public static final String ET_JOB = "et_job";//工作单位类别
	public static final String ET_TEACHER = "et_teacher";//导师类别
	public static final String ET_AREA = "et_area";//地区
	public static final String ET_SERIALNUMBER = "et_serialnumber";//序列号
	public static final String ET_DEADLINE = "et_deadline";//截止日期
	public static final String ET_COMMENTS = "et_comments";//审核意见
	public static final String ET_COLLEGE = "et_college";//所属学院
	public static final String ET_DOCUMENTNUMBER = "et_documentnumber";//证件号
	public static final String ET_DEPARTMENTNAME = "et_departmentname";//部门名字

	//记录是否存在空值
	public static final String ISNULL = "isnull";
}
