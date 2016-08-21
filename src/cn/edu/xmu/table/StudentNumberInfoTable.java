package cn.edu.xmu.table;

/**
 * 
 * @author xiaoping 数据报表6-1-1 学生数量基本情况（时点）date 2015-7-5
 *
 */
public class StudentNumberInfoTable
{
	public static final String TABLE_NAME = "sni_studentnumberinfo";//表名
	public static final String SNI_ID = "sni_id";//主键
	public static final String SNI_STUINFOBASELINK = "sni_stuinfobaselink";//学生信息库链接
	public static final String SNI_ORDIUNDERGRASTU = "sni_ordiundergrastu";//普通本科生人数
	public static final String SNI_COUNTRYOVERSEASTU = "sni_countryoverseastu";//普通本科生中：与国（境）外大学联合培养的学生数
	public static final String SNI_HIGHERVOCATIONSTU = "sni_highervocationstu";//普通高职（含专科）学生数（人）
	public static final String SNI_POSTGRADUATE = "sni_postgraduate";//硕士研究生数（人）
	public static final String SNI_FULLTIMEPOST = "sni_fulltimepost";//硕士研究生中：全日制硕士研究生
	public static final String SNI_NOFULLTIMEPOST = "sni_nofulltimepost";//硕士研究生中：非全日制硕士研究生
	public static final String SNI_DOCTORCANDIDATE = "sni_doctorcandidate";//博士研究生数（人）
	public static final String SNI_FULLTIMEDOCT = "sni_fulltimedoct";//博士研究生中：全日制博士研究生
	public static final String SNI_NOFULLTIMEDOCT = "sni_nofulltimedoct";//博士研究生中：非全日制博士研究生
	public static final String SNI_ABROADSTU = "sni_abroadstu";//留学生数（人）
	public static final String SNI_ORDIPREPPIE = "sni_ordipreppie";//普通预科生数（人）
	public static final String SNI_ADVANCEDSTU = "sni_advancedstu";//进修生数（人）
	public static final String SNI_ADULTFULLTIMESTU = "sni_adultfulltimestu";//成人脱产学生数（人）
	public static final String SNI_PARTTIMESTU = "sni_parttimestu";//夜大（业余）学生数（人）
	public static final String SNI_CORRESPONDENCESTU = "sni_correspondencestu";//函授学生数（人）
	public static final String SNI_NETWORKSTU = "sni_networkstu";//网络学生数（人）
	public static final String SNI_SELFTAUGHTSTU = "sni_selftaughtstu";//自考学生数（人）
	public static final String SNI_SERIALNUMBER = "sni_serialnumber";//序列号
	public static final String SNI_DEADLINE = "sni_deadline";//截止日期
	public static final String SNI_COLLEGE = "sni_college";//所属学院
	public static final String SNI_COMMENTS = "sni_comments";//审核意见
	public static final String SNI_ISNULL = "sni_isnull";//记录是否存在空字段，0完整，1存在空字段
}
