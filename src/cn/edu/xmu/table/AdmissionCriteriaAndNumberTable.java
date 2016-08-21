package cn.edu.xmu.table;


/**
 * 附表6-1-5-4  近一届本科生录取标准及人数（时点）
 * @author yue
 *
 */
public class AdmissionCriteriaAndNumberTable {

	public static final String TABLE_NAME = "acn_admissioncriteriaandnumber";//表名
	public static final String ACN_ID = "acn_id";//id
	public static final String ACN_PROVINCE = "acn_province";//省份
	public static final String ACN_BATCH = "acn_batch";//批次
	public static final String ACN_ARTSADMISSION = "acn_artsadmission";//文科（录取数（人））
	public static final String ACN_SCIENCEADMISSION = "acn_scienceadmission";//理科（录取数（人））
	public static final String ACN_ARTSMINCTRLINE = "acn_artsminctrline";//文科（批次最低控制线（分））
	public static final String ACN_SCIENCEMINCTRLINE = "acn_scienceminctrline";//理科（批次最低控制线（分））
	public static final String ACN_ARTSAVGSCORE = "acn_artsavgscore";//文科（当年录取平均分数（分））
	public static final String ACN_SCIENCEAVGSCORE = "acn_scienceavgscore";//理科（当年录取平均分数（分））
	public static final String ACN_INSTRUCTION = "acn_instruction";//说明
	public static final String ACN_SERIALNUMBER = "acn_serialnumber";//序列号
	public static final String ACN_DEADLINE = "acn_deadline";//截止日期
	public static final String ACN_COLLEGE = "acn_college";//所属学院
	public static final String ANC_COMMENTS = "acn_comments";//审核意见
	public static final String ISNULL = "isnull";//记录是否存在空值
}
