package cn.edu.xmu.table;

/**
 * 
 * @author xiaoping 表3-4-2  高层次研究团队 (时点)  date 2015-7-9
 *
 */
public class HighLevelResearchTeamTable
{
	public static final String TABLE_NAME = "hlrt_highlevelresearchteam";//表名
	public static final String HLRT_ID = "hlrt_id";//主键
	public static final String HLRT_RESEARCHDIRECTION = "hlrt_researchdirection";//研究方向
	public static final String HLRT_HEAD = "hlrt_head";//负责人
	public static final String HLRT_HEADNUMBER = "hlrt_headnumber";//负责人工号
	public static final String HLRT_TYPE = "hlrt_type";//类型
	public static final String HLRT_ACQUISITIONDATE = "hlrt_acquisitiondate";//获得时间
	public static final String HLRT_SERIALNUMBER = "hlrt_serialnumber";//序列号
	public static final String HLRT_DEADLINE = "hlrt_deadline";//截止日期
	public static final String HLRT_COLLEGE = "hlrt_college";//所属学院
	public static final String HLRT_COMMENTS = "hlrt_comments";//审核意见
	public static final String HLRT_ISNULL = "hlrt_isnull";//记录中是否存在空字段，0为完整   1为存在空字段
}
