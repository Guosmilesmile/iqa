package cn.edu.xmu.table;
/**
 * 表3-5-2  教师培训进修、交流情况（学年）
 * @author zhantu
 * date 2015-07-03
 */
public class TeacherTrainingExchangeTable {
	//表名
	public static final String TABLE_NAME = "tte_teachertrainingexchange";
	//id
	public static final String TTE_ID = "tte_id";
	//院（系）名称
	public static final String TTE_DEPARTMENTNAME = "tte_departmentname";
	//单位号
	public static final String TTE_DEPARTMENTNUMBER = "tte_departmentnumber";
	//境内教师培训进修（人次）
	public static final String TTE_TRAINCHURCHYARD = "tte_trainchurchyard";
	//境外教师培训进修总数（人次）
	public static final String TTE_TRAINOVERSEASSUM = "tte_trainoverseassum";
	//境外教师培训进修其中：3个月及以上（人次）
	public static final String TTE_TRAINOVERSEASOVER3 = "tte_trainoverseasover3";
	//到行业培训教师培训进修总数（人次）
	public static final String TTE_TRAINTRADE = "tte_traintrade";
	//攻读学位教师培训进修总数（人次）
	public static final String TTE_TRAINFORDEGREESUM = "tte_trainfordegreesum";
	//攻读学位教师培训进修其中：博士（人次）
	public static final String TTE_TRAINFORDOCTOR = "tte_trainfordoctor";
	//攻读学位教师培训进修其中：硕士（人次）
	public static final String TTE_TRAINFORMASTER = "tte_trainformaster";
	//来访境内交流教师（3个月及以上）（人次）
	public static final String TTE_EXCHANGECOMECHURCHYARD = "tte_exchangecomechurchyard";
	//来访境外交流教师（3个月及以上）（人次）
	public static final String TTE_EXCHANGECOMEOVERSEA = "tte_exchangecomeoversea";
	//出访境内交流教师（3个月及以上）（人次）
	public static final String TTE_EXCHANGEVISITCHURCHYARD = "tte_exchangevisitchurchyard";
	//出访境外交流教师（3个月及以上）（人次）
	public static final String TTE_EXCHANGEVISITOVERSEA = "tte_exchangevisitoversea";
	//序列号
	public static final String TTE_SERIALNUMBER = "tte_serialnumber";
	//截止日期
	public static final String TTE_DEADLINE = "tte_deadline";
	//所属学院
	public static final String TTE_COLLEGE = "tte_college";
	//审核意见
	public static final String TTE_COMMENTS = "tte_comments";
	//记录是否存在空值
	public static final String ISNULL = "isnull";
}
