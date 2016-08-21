package cn.edu.xmu.table;
/**
 * 
 * @author xiaoping 数据报表 附表4-2-2-2教学计划执行情况  date 2015-7-8
 *
 */
public class TeachingPlanImplTable
{
	public static final String TABLE_NAME = "tpi_teachingplanimpl";//表名
	public static final String TPI_ID = "tpi_id";//主键
	public static final String TPI_INSTITUTE = "tpi_institute";//学院
	public static final String TPI_MAJOR = "tpi_major";//专业
	public static final String TPI_GRADE = "tpi_grade";//年级
	public static final String TPI_PLANCOURSENUM = "tpi_plancoursenum";//应开课程门数
	public static final String TPI_PLANCOURSECREDIT = "tpi_plancoursecredit";//应开课程学分
	public static final String TPI_ACTUALCOURSENUM = "tpi_actualcoursenum";//实开课程门数
	public static final String TPI_ACTUALCOURSECREDIT = "tpi_actualcoursecredit";//实开课程学分
	public static final String TPI_NEWCOURSENUM = "tpi_newcoursenum";//新增课程门数
	public static final String TPI_NEWCOURSECREDIT = "tpi_newcoursecredit";//新增课程学分
	public static final String TPI_STOPCOURSENUM = "tpi_stopcoursenum";//停开课程门数
	public static final String TPI_STOPCOURSECREDIT = "tpi_stopcoursecredit";//停开课程学分
	public static final String TPI_ADVANCELATERCOURSENUM = "tpi_advancelatercoursenum";//提前推后课程门数
	public static final String TPI_ADVANCELATERCOURSECREDIT = "tpi_advancelatercoursecredit";//提前推后课程学分
	public static final String TPI_SERIALNUMBER = "tpi_serialnumber";//序列号
	public static final String TPI_DEADLINE = "tpi_deadline";//截止日期
	public static final String TPI_COLLEGE = "tpi_college";//所属学院
	public static final String TPI_COMMENTS = "tpi_comments";//审核意见
	public static final String TPI_ISNULL = "tpi_isnull";//记录中是否存在空字段，0为完整   1为存在空字段
}
