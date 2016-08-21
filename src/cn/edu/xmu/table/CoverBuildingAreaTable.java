package cn.edu.xmu.table;

/**
 * 
 * @author xiaoping 数据报表2-1 占地与建筑面积(时点) date 2015-6-30
 *
 */
public class CoverBuildingAreaTable
{
	// 表名
	public static final String TABLE_NAME = "cba_coverbuildingarea";

	// ID
	public static final String CBA_ID = "cba_id";

	// 统计时间
	public static final String CBA_STATISTICALTIME = "cba_statisticaltime";

	// 填报学校
	public static final String CBA_FILLSCHOOL = "cba_fillschool";

	// 总占地面积
	public static final String CBA_TOTALCOVERAREA = "cba_totalcoverarea";

	// 学校产权占地面积
	public static final String CBA_PROPERTYCOV = "cba_propertycov";

	// 学校产权绿化用地占地面积
	public static final String CBA_PROPERTYAFFORESTCOV = "cba_propertyafforestcov";

	// 非学校产权占地面积
	public static final String CBA_NONPROPERTYCOV = "cba_nonpropertycov";

	// 非学校产权绿化用地占地面积
	public static final String CBA_NONPROPERTYAFFORESTCOV = "cba_nonpropertyafforestcov";

	// 非学校产权独立使用占地面积
	public static final String CBA_NONPROINDEPUSECOV = "cba_nonproindepusecov";

	// 非学校产权共同使用占地面积
	public static final String CBA_NONPROCOMMONUSECOV = "cba_nonprocommonusecov";

	// 总建筑面积
	public static final String CBA_TOTALBUILDINGAREA = "cba_totalbuildingarea";

	// 学校产权建筑面积
	public static final String CBA_PROPERTYBUI = "cba_propertybui";

	// 非学校产权建筑面积
	public static final String CBA_NONPROPERTYBUI = "cba_nonpropertybui";

	// 非学校产权独立使用建筑面积
	public static final String CBA_NONPROINDEPUSEBUI = "cba_nonproindepusebui";

	// 非学校产权共同使用建筑面积
	public static final String CBA_NONPROCOMMONUSEBUI = "cba_nonprocommonusebui";

	// 序列号
	public static final String CBA_SERIALNUMBER = "cba_serialnumber";

	// 截止日期
	public static final String CBA_DEADLINE = "cba_deadline";

	// 所属学院
	public static final String CBA_COLLEGE = "cba_college";

	// 审核意见
	public static final String CBA_COMMENTS = "cba_comments";
	
	//记录中是否有孔子段，0完整 1存在空字段
	public static final String CBA_ISNULL = "cba_isnull";
}
