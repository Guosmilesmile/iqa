package cn.edu.xmu.table;

/**
 * 附表6-1-6-1  各专业（大类）招生情况（时点）
 * @author yue
 *
 */
public class MajorEnrollInfoTable {
	public static String TABLE_NAME = "mei_majorenrollinfo";//表名
	public static String MEI_ID = "mei_id";//id
	public static String MEI_MAJORCODE = "mei_majorcode";//校内专业（大类）代码
	public static String MEI_MAJORNAME = "mei_majorname";//校内专业（大类）名称
	public static String MEI_PLANNUMBER = "mei_plannumber";//招生计划数（人）；
	public static String MEI_ENROLLNUMBER = "mei_enrollnumber";//实际录取数（人）
	public static String MEI_SERIALNUMBER = "mei_serialnumber";//序列号
	public static String MEI_DEADLINE = "mei_deadline";//截止日期
	public static String MEI_COLLEGE = "mei_college";//所属学院
	public static String MEI_COMMENTS = "mei_comments";//审核意见
	public static String ISNULL = "isnull";//记录是否存在空值
}
