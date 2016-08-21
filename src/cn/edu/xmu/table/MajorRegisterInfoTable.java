package cn.edu.xmu.table;

/**
 * 表6-1-6-2 各专业（大类）报到情况（时点）
 * @author yue
 *
 */
public class MajorRegisterInfoTable {

	public static final String TABLE_NAME = "mri_majorregisterinfo";//表名
	public static final String MRI_ID = "mri_id";//id
	public static final String MRI_MAJORCODE = "mri_majorcode";//校内专业（大类）代码
	public static final String MRI_MAJORNAME = "mri_majorname";//校内专业（大类）名称
	public static final String MRI_REGISTERNUMBER = "mri_registernumber";//实际报到人数（人）
	public static final String MRI_SERIALNUMBER = "mri_serialnumber";//序列号
	public static final String MRI_DEADLINE = "mri_deadline";//截止日期
	public static final String MRI_COLLEGE = "mri_college";//所属学院
	public static final String MRI_COMMETNS = "mri_comments";//审核意见
	public static final String ISNULL = "isnull";//记录是否存在空值
}
	