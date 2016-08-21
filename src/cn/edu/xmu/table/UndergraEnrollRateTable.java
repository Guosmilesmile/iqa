package cn.edu.xmu.table;

/**
 * 附表6-1-5-3本科生招生志愿满足率（时点）
 * @author yue
 *
 */
public class UndergraEnrollRateTable {
		public static final String TABLE_NAME = "uer_undergraenrollrate";//表名
		public static final String UER_ID = "uer_id";//id
		public static final String UER_INSTITUTE = "uer_institute";//学院
		public static final String UER_ADMISSION = "uer_admission";//录取专业
		public static final String UER_FIRSTMAJORRATE = "uer_firstmajorrate";//第一专业志愿满足率
		public static final String UER_UNFIRSTMAJORRATE = "uer_unfirstmajorrate";//非第一专业志愿满足率
		public static final String UER_ADJUSTRATE = "uer_adjustrate";//调剂率
		public static final String UER_SERIALNUMBER = "uer_serialnumber";//序列号
		public static final String UER_DEADLINE = "uer_deadline";//截止日期
		public static final String UER_COLLEGEG = "uer_college";//所属学院
		public static final String UER_COMMENTS = "uer_comments";//审核意见
		public static final String ISNULL = "isnull";//记录是否存在空值
}
