package cn.edu.xmu.table;

/**
 * 表4-1-1学科建设
 * 
 * @author Gy
 * 
 */
public class DisciplineConstructionTable {
	public static String TABLE_NAME = "dc_disciplineconstruction";

	public static String DC_ID = "dc_id";

	// 博士后流动站（个）
	public static String DC_DOCTORSTATION = "dc_doctorstation";

	// 博士学位授权一级学科点
	public static String DC_DOCGRANTONE = "dc_docgrantone";
	// 博士学位授权二级学科点（不含一级覆盖）
	public static String DC_DOCGRANTTWO = "dc_docgranttwo";
	// 硕士学位授权一级学科点
	public static String DC_MASGRANTONE = "dc_masgrantone";
	// 硕士学位授权二级学科点（不含一级覆盖）
	public static String DC_MASGRANTTWO = "dc_masgranttwo";
	// 本科专业总数
	public static String DC_UNDERTOTAL = "dc_undertotal";
	// 本科专业新专业
	public static String DC_UNDERNEW = "dc_undernew";
	// 专科专业（个）
	public static String DC_JUNIOR = "dc_junior";

	// 序列号
	public static final String DC_SERIALNUMBER = "dc_serialnumber";
	// 截止日期
	public static final String DC_DEADLINE = "dc_deadline";
	// 所属学院
	public static final String DC_COLLEGE = "dc_college";
	// 审核意见
	public static final String DC_COMMENTS = "dc_comments";

}
