package cn.edu.xmu.table;


/**
 * 
 * @author Lee
 * 附表6-2-1-10 本科生受处分情况(按处分等级）（学年）
 * date 2015-07-13
 */
public class BenkePunishTable {
	
	//表名
	public static final String TABLE_NAME = "bp_benkepunish";
	
	//序号
	public static final String BP_ID = "bp_id";

	//学院
	public static final String BP_COLLEGE1 = "bp_college1";
	
	//严重警告
	public static final String BP_WARNING = "bp_warning";
	
	//记过处分
	public static final String BP_DEMERIT = "bp_demerit";
	
	//留校察看处分
	public static final String BP_PROBATION = "bp_probation";
	
	//开除学籍处分
	public static final String BP_EXPULSION = "bp_expulsion";
	
	//累计人数
	public static final String BP_TOTALMOUNT = "bp_totalmount";
	
	//序列号
	public static final String BP_SERIALNUMBER = "bp_serialnumber";
	
	//截止日期
	public static final String BP_DEADLINE = "bp_deadline";
	
	//审核意见
	public static final String BP_COMMENTS = "bp_comments";
	
	//操作学院
	public static final String BP_COLLEGE = "bp_college";
	
	//记录是否存在空值
	public static final String ISNULL = "isnull";
	
}
