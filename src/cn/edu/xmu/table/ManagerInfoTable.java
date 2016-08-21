package cn.edu.xmu.table;

/**
 * 3-3 相关管理人员基本信息
 * @author yue
 *
 */
public class ManagerInfoTable {
	public static final String TABLE_NAME ="mi_managerinfo";//表名
	public static final String MI_ID = "mi_id"; //id
	public static final String MI_NAME="mi_name";//姓名
	public static final String MI_WORKNUMBER = "mi_worknumber";//工号
	public static final String MI_SEX = "mi_sex";//性别
	public static final String MI_BIRTHDAY = "mi_birthday";//出生年月:格式：yyyy-mm
	public static final String MI_INSCHOOLDATE = "mi_inschooldate"; //入校时间:格式：yyyy-mm
	public static final String MI_MANAGERTYPE = "mi_managertype";//管理人员类别
	public static final String MI_DEPARTMENTNUMBER = "mi_departmentnumber";//单位号:参考表1-3和1-4，不在此范围的填000
	public static final String MI_DEPARTMENTNAME = "mi_departmentname";//单位名称
	public static final String MI_EDUCATION = "mi_education";//学历
	public static final String MI_DEGREES = "mi_degrees";//最高学位
	public static final String MI_PROFESSIONALTITLE = "mi_professionaltitle";//专业技术职称
	public static final String MI_DUTY = "mi_duty";//职务
	public static final String MI_COLLEGE = "mi_college";//填报学院
	public static final String MI_DEADLINE = "mi_deadline";//截止时间
	public static final String MI_SERIALNUMBER = "mi_serialnumber";//顺序号
	public static final String MI_COMMENTS = "mi_comments";//备注,用于填写审核意见
	public static final String ISNULL = "isnull";
	
}
