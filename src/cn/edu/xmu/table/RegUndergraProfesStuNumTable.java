package cn.edu.xmu.table;

/**
 * 
 * @author xiaoping 数据报表6-1-2 普通本科分专业（大类）学生数（时点） date 2015-7-5
 *
 */
public class RegUndergraProfesStuNumTable
{
	public static final String TABLE_NAME = "rupsn_regundergraprofesstunum";//表名
	public static final String RUPSN_ID = "rupsn_id";//主键
	public static final String RUPSN_SCHPROFESCODE = "rupsn_schprofescode";//校内专业（大类）代码
	public static final String RUPSN_SCHPROFESNAME = "rupsn_schprofesname";//校内专业（大类）名称
	public static final String RUPSN_EDUSYSTEM = "rupsn_edusystem";//学制
	public static final String RUPSN_ATSCHTOTAL = "rupsn_atschtotal";//在校人数总计（人）
	public static final String RUPSN_GRADEONE = "rupsn_gradeone";//一年级在校人数（人）
	public static final String RUPSN_GRADETWO = "rupsn_gradetwo";//二年级在校人数（人）
	public static final String RUPSN_GRADETHREE = "rupsn_gradethree";//三年级在校人数（人）
	public static final String RUPSN_GRADEFOUR = "rupsn_gradefour";//四年级在校人数（人）
	public static final String RUPSN_ABGRADEFIVE = "rupsn_abgradefive";//五年级及以上在校人数（人）
	public static final String RUPSN_MINOR = "rupsn_minor";//辅修在校人数（人）
	public static final String RUPSN_DOUBLEDEGREE = "rupsn_doubledegree";//双学位在校人数（人）
	public static final String RUPSN_INTONUMBER = "rupsn_intonumber";//本专业转入人数（人）
	public static final String RUPSN_OUTNUMBER = "rupsn_outnumber";//本专业转出人数（人）
	public static final String RUPSN_SERIALNUMBER = "rupsn_serialnumber";//序列号
	public static final String RUPSN_DEADLINE = "rupsn_deadline";//截止日期
	public static final String RUPSN_COLLEGE = "rupsn_college";//所属学院
	public static final String RUPSN_COMMENTS = "rupsn_comments";//审核意见
	public static final String RUPSN_ISNULL = "rupsn_isnull";//记录中是否存在空字段 0为完整  1为存在空字段
}
