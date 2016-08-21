package cn.edu.xmu.table;

/**
 * 
 * @author xiaoping 附表6-1-5-1厦门大学在全国各省（市、自治区）招生出档分数情况（时点） date 2015-7-11
 *
 */
public class RecruitExceedScoreTable
{
	public static final String TABLE_NAME = "res_recruitexceedscore";//表名
	public static final String RES_ID = "res_id";//主键
	public static final String RES_YEAR = "res_year";//年份
	public static final String RES_LIBEXCTWENTYPROPORTION = "res_libexctwentyproportion";//文科超出20分的省份比例(%)
	public static final String RES_LIBEXCTHIRTYPROPORTION = "res_libexcthirtyproportion";//文科超出30分的省份比例(%)
	public static final String RES_LIBEXCLINEAVE = "res_libexclineave";//文科所有生源省份出档线超出本一线的平均值
	public static final String RES_SCIEXCTHIRTYPROPORTION = "res_sciexcthirtyproportion";//理科超出30分的省份比例(%)
	public static final String RES_SCIEXCFORTYPROPORTION = "res_sciexcfortyproportion";//理科超出40分的省份比例(%)
	public static final String RES_SCIEXCLINEAVE = "res_sciexclineave";//理科所有生源省份出档线超出本一线的平均值
	public static final String RES_SERIALNUMBER = "res_serialnumber";//序列号
	public static final String RES_DEADLINE = "res_deadline";//截止日期
	public static final String RES_COLLEGE = "res_college";//所属学院
	public static final String RES_COMMENTS = "res_comments";//审核意见
	public static final String RES_ISNULL = "res_isnull";//记录中是否存在空字段，0为完整   1为存在空字段
}
