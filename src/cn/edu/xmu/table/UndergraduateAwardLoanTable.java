package cn.edu.xmu.table;

/**
 * 表6-1-7  本科生奖贷补（自然年）
 * @author zhantu
 * date 2015-07-05
 */
public class UndergraduateAwardLoanTable {
	//表名
	public static final String TABLE_NAME = "ual_undergraduateawardloan";
	//ID
	public static final String UAL_ID = "ual_id";
	//总计资助金额（万元）
	public static final String UAL_SUMCOST = "ual_sumcost";
	//政府奖、助学金资助金额（万元）
	public static final String UAL_GOVCOST = "ual_govcost";
	//社会奖助学金资助金额（万元）
	public static final String UAL_SOCIETYCOST = "ual_societycost";
	//学校奖学金资助金额（万元）
	public static final String UAL_SCHOOLCOST = "ual_schoolcost";
	//国家助学贷款资助金额（万元）
	public static final String UAL_COUNTRYCOST = "ual_countrycost";
	//勤工助学金资助金额（万元）
	public static final String UAL_WORKSTUDYCOST = "ual_workstudycost";
	//减免学费资助金额（万元）
	public static final String UAL_DERATECOST = "ual_deratecost";
	//临时困难补助资助金额（万元）
	public static final String UAL_TROUBLEAIDCOST = "ual_troubleaidcost";
	
	//总计资助学生数（人次）
	public static final String UAL_SUMCOUNT = "ual_sumcount";
	//政府奖、助学金资助学生数（人次）
	public static final String UAL_GOVCOUNT = "ual_govcount";
	//社会奖助学金资助学生数（人次）
	public static final String UAL_SOCIETYCOUNT = "ual_societycount";
	//学校奖学金资助学生数（人次）
	public static final String UAL_SCHOOLCOUNT = "ual_schoolcount";
	//国家助学贷款资助学生数（人次）
	public static final String UAL_COUNTRYCOUNT = "ual_countrycount";
	//勤工助学金资助学生数（人次）
	public static final String UAL_WORKSTUDYCOUNT = "ual_workstudycount";
	//减免学费资助学生数（人次）
	public static final String UAL_DERATECOUNT = "ual_deratecount";
	//临时困难补助资助学生数（人次）
	public static final String UAL_TROUBLEAIDCOUNT = "ual_troubleaidcount";
	
	//序列号
	public static final String UAL_SERIALNUMBER = "ual_serialnumber";
	//截止日期
	public static final String UAL_DEADLINE = "ual_deadline";
	//所属学院
	public static final String UAL_COLLEGE = "ual_college";
	//审核意见
	public static final String UAL_COMMENTS = "ual_comments";
	//记录是否存在空值
	public static final String ISNULL = "isnull";
}
